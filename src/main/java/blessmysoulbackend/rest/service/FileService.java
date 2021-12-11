package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.model.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    File save(MultipartFile file);

}
