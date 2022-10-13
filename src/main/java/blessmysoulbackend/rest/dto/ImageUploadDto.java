package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.helpers.ImageType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private MultipartFile image;

    private String name;

    private String fileExtension;

    private ImageType type;

}

