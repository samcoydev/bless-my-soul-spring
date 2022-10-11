package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.ImageDao;
import blessmysoulbackend.rest.helpers.ImageType;
import blessmysoulbackend.rest.helpers.exceptions.FileSaveException;
import blessmysoulbackend.rest.model.Image;
import blessmysoulbackend.rest.service.ImageService;
import com.jlefebure.spring.boot.minio.MinioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service(value = "imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDao imageDao;

    @Autowired
    private MinioService minioService;

    @Value("${spring.minio.url}/${spring.minio.bucket}")
    private String minioBucketPath;

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
            // First we need to post the image to Minio
            minioService.upload(
                    Paths.get(fileName),
                    image.getInputStream(),
                    image.getContentType()
            );

            minioService.get(Paths.get(fileName));

            // Then save the reference object to our db to keep track of the relationships
            Image newImage = new Image();
            newImage.setName(fileName);
            newImage.setType(ImageType.CATALOG);
            newImage.setUrl(minioBucketPath + "/" + fileName);

            log.info(newImage.getUrl());

            return imageDao.save(newImage);
        } catch (Exception e) {
            throw new FileSaveException("File not stored", e);
        }
    }

}
