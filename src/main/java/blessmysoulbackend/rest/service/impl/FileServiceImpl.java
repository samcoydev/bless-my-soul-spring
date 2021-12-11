package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.FileDao;
import blessmysoulbackend.rest.helpers.exceptions.FileSaveException;
import blessmysoulbackend.rest.model.File;
import blessmysoulbackend.rest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service(value = "fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    public File save(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("...")) {
                throw new FileSaveException("Invalid File..");
            }
            File newFile = new File();
            newFile.setName(fileName);
            newFile.setType(file.getContentType());
            newFile.setData(file.getBytes());

            return fileDao.save(newFile);
        } catch (Exception e) {
            throw new FileSaveException("File not stored", e);
        }
    }

}
