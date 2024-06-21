package it.epicode.phronesis.businesslayer.services.interfaces.image;


import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService{

    UserResponsePartialDTO updateProfilePicture(Long id, MultipartFile file) throws IOException;

    long getMaxFileSizeInBytes();

    String extractPublicIdFromUrl(String url);

    void deleteImage(String publicId) throws IOException;

    void verifyMaxSizeOfFile(MultipartFile file);
}
