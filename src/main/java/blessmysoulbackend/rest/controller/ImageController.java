package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.model.Image;
import blessmysoulbackend.rest.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> getAllImages() {
        System.out.println("[GET] All Images");
        return imageService.findAll();
    }

    @PostMapping
    public ResponseEntity<Image> saveImage(@Valid @RequestBody MultipartFile image) {
        System.out.println("[POST] NEW IMAGE: ");
        if (!image.getContentType().equals("image/jpeg")) {
            System.out.println("[ERROR] TRIED TO POST A NON-JPEG FILE!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.save(image));
    }

}
