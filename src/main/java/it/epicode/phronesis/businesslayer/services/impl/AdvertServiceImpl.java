package it.epicode.phronesis.businesslayer.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.phronesis.businesslayer.dto.AdvertRequestDto;
import it.epicode.phronesis.businesslayer.dto.AdvertResponseDto;
import it.epicode.phronesis.businesslayer.dto.AdvertResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.AdvertService;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.image.ImageService;
import it.epicode.phronesis.datalayer.entities.Advert;
import it.epicode.phronesis.datalayer.repositories.AdvertRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.phronesis.presentationlayer.api.exceptions.duplicated.DuplicateTitleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    AdvertRepository advertRepository ;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    Mapper<AdvertRequestDto, Advert> mapAdvertRequestDTOToAdvert;

    @Autowired
    Mapper<Advert, AdvertResponseDto> mapAdvertEntityToAdvertResponseDto;

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    ImageService cloudinaryService;

    @Value("${spring.servlet.multipart.max-file-size}")
    String maxFileSize;

    @Override
    public Page<AdvertResponsePrj> getAll(Pageable p) {
        return advertRepository.findAllBy(p);
    }

    @Override
    public AdvertResponseDto getById(Long id) {
        return mapAdvertEntityToAdvertResponseDto.map(advertRepository.findById(id).orElseThrow(()-> new NotFoundException(id)));
    }

    @Override
    public AdvertResponseDto save(AdvertRequestDto e) throws IOException {
        var titleDuplicated = advertRepository.findOneByTitle(e.getTitle());
        if (titleDuplicated.isPresent()) {
            throw new DuplicateTitleException(e.getTitle());
        }

        var createdBy = usersRepository.findById(e.getCreatedById()).orElseThrow(()-> new NotFoundException(e.getCreatedById()));

        var advertEntity = mapAdvertRequestDTOToAdvert.map(e);

        advertEntity.setCreatedBy(createdBy);

        var imageFile = e.getImage();

        cloudinaryService.verifyMaxSizeOfFile(imageFile);

        var url = (String) cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap()).get("url");

        advertEntity.setImageUrl(url);

        return mapAdvertEntityToAdvertResponseDto.map(advertRepository.save(advertEntity));

    }

    @Override
    public AdvertResponseDto update(Long id, AdvertRequestDto e) throws IOException {
        var a = advertRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        var titleDuplicated = advertRepository.findOneByTitle(e.getTitle());
        if (titleDuplicated.isPresent() && !Objects.equals(titleDuplicated.get().getTitle(), a.getTitle())) {
            throw new DuplicateTitleException(e.getTitle());
        }
        BeanUtils.copyProperties(e,a);

        if (e.getImage() != null && !e.getImage().isEmpty()) {
        var imageFile = e.getImage();
        cloudinaryService.verifyMaxSizeOfFile(imageFile);
        var url = (String) cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap()).get("url");
        a.setImageUrl(url);
        }
        return mapAdvertEntityToAdvertResponseDto.map(a);
    }

    @Override
    public AdvertResponseDto updateAdvertImage(Long id, MultipartFile file) throws IOException {
        return cloudinaryService.updateAdvertImage(id, file);
    }

    @Override
    public AdvertResponseDto delete(Long id) {
        try {
            var a = advertRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

            var publicID = cloudinaryService.extractPublicIdFromUrl(a.getImageUrl());

            cloudinaryService.deleteImage(publicID);

            advertRepository.delete(a);
            return mapAdvertEntityToAdvertResponseDto.map(a);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Cannot find user...");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


}
