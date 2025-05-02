package com.michel.mall_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    @Value("${application.storage.path}")
    private String STORAGE_DIRECTORY;

    @Autowired
    private DateService dateService;

    @Autowired
    private GeneralService generalService;

    public String saveFile(MultipartFile file) throws IOException {
        if(file == null){
            throw new NullPointerException("File is null");
        }
        String fileName = "image_" + dateService.getTDateTime().replaceAll("-", "_").replaceAll(":", "_")+ "_" + generalService.generateRandomString() + "." + getFileExtension(file);
        System.out.println("FileName: " + fileName);
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + fileName);
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

    public Boolean deleteFile(String filePath){
        try {
            Path path = Paths.get(filePath);
            boolean deleted = Files.deleteIfExists(path);
            if (deleted) {
                System.out.println("File deleted successfully.");
                return true;
            } else {
                System.out.println("File not found.");
                return false;
            }
        } catch (IOException e) {
            System.err.println("Error deleting file: " + e.getMessage());
            return false;
        }
    }

    public static String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }
        return ""; // No extension
    }
}
