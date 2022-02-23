package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.ImageDao;
import blessmysoulbackend.rest.helpers.exceptions.FileSaveException;
import blessmysoulbackend.rest.model.Image;
import blessmysoulbackend.rest.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDao imageDao;

    public List<Image> findAll() {
        List<Image> imageList = new ArrayList<>();
        imageDao.findByOrderById().iterator().forEachRemaining(imageList::add);
        return imageList;
    }

    public Image save(MultipartFile image) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        try {
            if (fileName.contains("...")) {
                throw new FileSaveException("Invalid File..");
            }
            Image newImage = new Image();
            newImage.setName(fileName);
            newImage.setType(image.getContentType());
            newImage.setData(image.getBytes());

            return imageDao.save(newImage);
        } catch (Exception e) {
            throw new FileSaveException("File not stored", e);
        }
    }

}
