package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.ImageUploadDto;
import blessmysoulbackend.rest.model.Image;
import blessmysoulbackend.rest.model.ImageUpload;
import blessmysoulbackend.rest.model.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image save(ImageUploadDto imageUpload);
    Image update(long id, ImageUploadDto imageUpload);
    void delete (long id);

    List<Image> findAll();
    Image findById(long id);
}
