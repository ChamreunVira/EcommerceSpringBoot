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
import java.util.Locale;
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
            String originalFilename = Objects.requireNonNull(file.getOriginalFilename(), "File name is required");
            int extensionIndex = originalFilename.lastIndexOf(".");
            if(extensionIndex < 0) {
                throw new IllegalArgumentException("File extension is required");
            }
            String extension = originalFilename.substring(extensionIndex + 1).toLowerCase(Locale.ROOT);
            if(!ALLOW_EXTENSIONS.contains(extension)) {
                throw new IllegalArgumentException("File is not allowed");
            }
            Path uploadPath = Paths.get(uploadDir);
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String uniqueName = System.currentTimeMillis() + "_" + originalFilename;
            Path filePath = uploadPath.resolve(uniqueName)
                    .toAbsolutePath();
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return uniqueName;
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(String fileName) {
        if(fileName == null || fileName.isBlank()) {
            return;
        }
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).toAbsolutePath();
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
