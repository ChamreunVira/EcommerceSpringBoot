package com.kh.vira_dev.ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class FileUploadService {

    @Value("${uploads.product}")
    private String uploadDir;

    private final List<String> ALLOW_EXTENSIONS = List.of("jpg", "jpeg", "png");

    public String upload(MultipartFile file) {
        try {
            if(Objects.isNull(file)) {
                throw new IllegalArgumentException("File is null");
            }
            if(!ALLOW_EXTENSIONS.contains(Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1))) {
                throw new IllegalArgumentException("File is not allowed");
            }
            Path uploadPath = Paths.get(uploadDir);
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = file.getOriginalFilename();
            String uniqueName = System.currentTimeMillis() + "_" + fileName;
            Path filePath = uploadPath.resolve(uniqueName)
                    .toAbsolutePath();
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return uniqueName;
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
