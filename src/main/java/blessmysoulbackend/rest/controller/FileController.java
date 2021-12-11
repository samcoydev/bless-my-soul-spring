package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.model.File;
import blessmysoulbackend.rest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public File saveFile(@Valid @RequestBody MultipartFile file) {
        System.out.println("[POST] NEW FILE: ");
        return fileService.save(file);
    }

}
