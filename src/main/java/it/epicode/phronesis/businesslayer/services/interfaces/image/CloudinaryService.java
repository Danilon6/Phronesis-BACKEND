package it.epicode.phronesis.businesslayer.services.interfaces.image;


import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostPartialResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService extends ImageService {

    UserResponsePartialDTO updateProfilePicture(Long id, MultipartFile file) throws IOException;
    PostPartialResponseDTO updatePostImage(Long id, MultipartFile file) throws IOException;

}
