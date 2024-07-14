package it.epicode.phronesis.presentationlayer.api;

import it.epicode.phronesis.businesslayer.dto.AdvertRequestDto;
import it.epicode.phronesis.businesslayer.dto.AdvertResponseDto;
import it.epicode.phronesis.businesslayer.dto.AdvertResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.AdvertService;
import it.epicode.phronesis.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.phronesis.presentationlayer.api.models.AdvertRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/adverts")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @GetMapping
    public ResponseEntity<Page<AdvertResponsePrj>> getAllAdverts(Pageable pageable) {
        var allAdverts = advertService.getAll(pageable);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allAdverts.getTotalElements()));
        return new ResponseEntity<>(allAdverts, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AdvertResponseDto> getAdvert(@PathVariable Long id) {
        var advert = advertService.getById(id);
        return new ResponseEntity<>(advert, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdvertResponseDto> createAdvert(
            @RequestPart("advert") @Validated AdvertRequestModel model,
            @RequestPart(value = "advertImage", required = false) MultipartFile advertImage,
            BindingResult validator) throws IOException {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var createdAdvert = advertService.save(AdvertRequestDto.builder()
                .withTitle(model.title())
                .withDescription(model.description())
                .withImage(advertImage)
                        .withCreatedById(model.createdById())
                .build());
        return new ResponseEntity<>(createdAdvert, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AdvertResponseDto> updateAdvert(
            @PathVariable Long id,
            @RequestPart("advert") @Validated AdvertRequestModel model,
            @RequestPart(value = "advertImage", required = false) MultipartFile advertImage,
            BindingResult validator) throws IOException {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var updatedAdvert = advertService.update(id, AdvertRequestDto.builder()
                .withTitle(model.title())
                .withDescription(model.description())
                        .withImage(advertImage)
                .build());
        return new ResponseEntity<>(updatedAdvert, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AdvertResponseDto> deleteAdvert(@PathVariable Long id) {
        var deletedAdvert = advertService.delete(id);
        return new ResponseEntity<>(deletedAdvert, HttpStatus.OK);
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<AdvertResponseDto> updateAdvertImage(
            @PathVariable Long id,
            @RequestParam("advertImage") MultipartFile file) {
        try {
            var updatedAdvert = advertService.updateAdvertImage(id, file);
            return new ResponseEntity<>(updatedAdvert, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
