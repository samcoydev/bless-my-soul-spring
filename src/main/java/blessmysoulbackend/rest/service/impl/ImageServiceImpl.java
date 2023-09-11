package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.ImageDao;
import blessmysoulbackend.rest.dto.ImageUploadDto;
import blessmysoulbackend.rest.helpers.exceptions.FileSaveException;
import blessmysoulbackend.rest.model.Image;
import blessmysoulbackend.rest.model.ImageUpload;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.ImageService;
import com.google.common.io.Files;
import com.jlefebure.spring.boot.minio.MinioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Transactional
@Service(value = "imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDao imageDao;

    @Autowired
    private MinioService minioService;

    @Value("${PUBLIC_IMAGE_URL}/${spring.minio.bucket}")
    private String minioBucketPath;

    public List<Image> findAll() {
        List<Image> imageList = new ArrayList<>();
        imageDao.findByOrderById().iterator().forEachRemaining(imageList::add);
        return imageList;
    }

    @Override
    public Image findById(long id) {
        return imageDao.findById(id).get();
    }

    private String getAndFormatImageName(String name) throws FileSaveException {
        if (name.contains("..."))
            throw new FileSaveException("Invalid Filename.");

        if (!Files.getFileExtension(name).equals("png") && !Files.getFileExtension(name).equals("jpg"))
            throw new FileSaveException("Invalid File.");

        return name.replace(" ", "_");
    }

    private String getImageName(ImageUploadDto imageUpload) throws FileSaveException {
        if (!imageUpload.getName().isEmpty())
            return getAndFormatImageName(imageUpload.getName() + getFileExtension(imageUpload));

        if (imageUpload.getImage() != null)
            return getAndFormatImageName(StringUtils.cleanPath(Objects.requireNonNull(imageUpload.getImage().getOriginalFilename())));

        throw new FileSaveException("Files did not have suitable names.");
    }

    private String getFileExtension(ImageUploadDto imageUpload) throws FileSaveException {
        if (imageUpload.getFileExtension() != null)
            return imageUpload.getFileExtension();
        if (imageUpload.getImage() != null)
            return "." + Files.getFileExtension(imageUpload.getImage().getName());
        else
            throw new FileSaveException("No way to tell the file extension.");
    }

    public Image save(ImageUploadDto imageUpload) {
        MultipartFile imageFile = imageUpload.getImage();

        try {
            String fileName = getImageName(imageUpload);

            // First we need to post the image to Minio
            minioService.upload(
                    Paths.get(fileName),
                    imageFile.getInputStream(),
                    imageFile.getContentType()
            );

            // Then save the reference object to our db to keep track of the relationships
            Image newImage = new Image();
            newImage.setName(fileName);
            newImage.setType(imageUpload.getType());
            newImage.setUrl(minioBucketPath + "/" + fileName);
            newImage.setFileExtension(getFileExtension(imageUpload));

            log.info(newImage.getUrl());

            return imageDao.save(newImage);
        } catch (Exception e) {
            throw new FileSaveException("File not stored", e);
        }
    }

    public Image update(long id, ImageUploadDto image) {
        Optional<Image> optionalImage = imageDao.findById(id);

        if (optionalImage.isPresent()) {
            try {
                Image existingImage = optionalImage.get();
                if (!(image.getName() + image.getFileExtension()).equals(existingImage.getName()))
                {
                    String fileName = getImageName(image);
                    Path oldPath = Paths.get(existingImage.getName());

                    InputStream oldMinioObject = minioService.get(oldPath);

                    minioService.upload(Paths.get(fileName), oldMinioObject, existingImage.getFileExtension());
                    minioService.remove(oldPath);

                    existingImage.setName(fileName);
                    existingImage.setUrl(minioBucketPath + "/" + fileName);
                }

                existingImage.setType(image.getType());

                return imageDao.save(existingImage);
            } catch (Exception e) {
                throw new FileSaveException("File not stored to Minio.", e);
            }
        }
        return null;
    }

    public void delete(long id) {
        Image image = imageDao.findById(id).get();

        try {
            minioService.remove(Paths.get(image.getName()));
        } catch (Exception e) {
            throw new FileSaveException("Could not remove image from Minio.", e);
        }

        imageDao.delete(image);
    }

}
