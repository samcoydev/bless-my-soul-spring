package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image save(MultipartFile image);

    List<Image> findAll();
}
