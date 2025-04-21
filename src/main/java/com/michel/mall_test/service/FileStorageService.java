package com.michel.mall_test.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {
    private static final String STORAGE_DIRECTORY = "/Users/michel/SpringProjects/mall_test/src/main/resources/files";

    public String saveFile(MultipartFile file) throws IOException {
        if(file == null){
            throw new NullPointerException("File is null");
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + file.getOriginalFilename());
        /// Check if the parent folder of the file is the same as STORAGE_DIRECTORY for security matter
        if(!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)){
            throw new SecurityException("Unsupported file name");
        }
        Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return targetFile.getPath();
    }

    public File getDownloadFile(String fileName) throws Exception {
        if(fileName == null){
            throw new NullPointerException("File name is null");
        }
        var fileToDownload = new File(STORAGE_DIRECTORY + File.separator + fileName);
        /// Check if the parent folder of the file is the same as STORAGE_DIRECTORY for security matter
        if(!Objects.equals(fileToDownload.getParent(), STORAGE_DIRECTORY)){
            throw new SecurityException("Unsupported file name");
        }
        if(!fileToDownload.exists()){
            throw new FileNotFoundException("Requested file not found");
        }
        return fileToDownload;
    }

}
