package it.epicode.phronesis.businesslayer.services.impl;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.image.ImageService;
import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.FileSizeExceededException;
import it.epicode.phronesis.presentationlayer.api.exceptions.ImageDeletionException;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class CloudinaryServiceImpl implements ImageService {

    @Autowired
    Cloudinary cloudinary;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    Mapper<User, UserResponsePartialDTO> mapUserToPartialResponseDTO;

    @Override
    public UserResponsePartialDTO updateProfilePicture(Long id, MultipartFile file) throws IOException {
        long maxFileSize = getMaxFileSizeInBytes();
        if (file.getSize() > maxFileSize) {
            throw new FileSizeExceededException();
        }
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        var urlExistingImage = user.getProfilePicture();

        if (urlExistingImage != null) {
            var publicId = extractPublicIdFromUrl(urlExistingImage);
            deleteImage(publicId);
        }
        var url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        user.setProfilePicture(url);
        return mapUserToPartialResponseDTO.map(usersRepository.save(user));
    }

    @Override
    public void deleteImage(String publicId) throws IOException {
        if (publicId == null || publicId.isEmpty()) {
            throw new ImageDeletionException("L'ID pubblico non può essere nullo o vuoto");
        }
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new ImageDeletionException("Eliminazione dell'immagine fallita con ID pubblico: " + publicId, e);
        }
    }

    @Override
    public String extractPublicIdFromUrl(String url) {
        String[] urlParts = url.split("/");
        String fileName = urlParts[urlParts.length - 1];
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new IllegalArgumentException("URL does not contain a valid file extension");
        }
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    public void verifyMaxSizeOfFile(MultipartFile file){
        long maxFileSize = getMaxFileSizeInBytes();
        if (file.getSize() > maxFileSize) {
            throw new FileSizeExceededException();
        }
    }

    @Override
    public long getMaxFileSizeInBytes() {
        String[] parts = maxFileSize.split("(?i)(?<=[0-9])(?=[a-z])");
        long size = Long.parseLong(parts[0]);
        String unit = parts[1].toUpperCase();
        switch (unit) {
            case "KB":
                size *= 1024;
                break;
            case "MB":
                size *= 1024 * 1024;
                break;
            case "GB":
                size *= 1024 * 1024 * 1024;
                break;
        }
        return size;
    }
}
