package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.ImageUploadDto;
import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.Image;
import blessmysoulbackend.rest.model.ImageUpload;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> getAllImages() {
        log.info("[GET] All Images");
        return imageService.findAll();
    }

    @PostMapping
    public ResponseEntity<Image> saveImage(@Valid @ModelAttribute ImageUploadDto imageUpload) {
        log.info("[POST] NEW IMAGE: " + imageUpload);

        MultipartFile imageFile = imageUpload.getImage();
        if (imageFile == null || checkIfNotImage(imageFile))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.save(imageUpload));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateItem(@PathVariable long id, @Valid @RequestBody ImageUploadDto imageUpload) {
        log.info("[PUT] Image: " + imageUpload.getName());

        MultipartFile imageFile = imageUpload.getImage();
        if (imageFile != null && checkIfNotImage(imageFile))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.update(id, imageUpload));
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable long id) {
        log.info("[DELETE] Image with ID: " + id);
        imageService.delete(id);
    }

    public boolean checkIfNotImage(MultipartFile file) {
        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
            log.error("Did not receive an image. Content type was " + file.getContentType());
            return true;
        }
        return false;
    }

}
