package it.epicode.phronesis.businesslayer.services.interfaces;

import it.epicode.phronesis.businesslayer.dto.AdvertRequestDto;
import it.epicode.phronesis.businesslayer.dto.AdvertResponseDto;
import it.epicode.phronesis.businesslayer.dto.AdvertResponsePrj;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdvertService extends CRUDService<AdvertResponseDto, AdvertRequestDto, AdvertResponsePrj>{

    AdvertResponseDto updateAdvertImage(Long id, MultipartFile file)throws IOException;
}
