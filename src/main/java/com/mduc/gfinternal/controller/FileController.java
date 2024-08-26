package com.mduc.gfinternal.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.mduc.gfinternal.service.S3CompatibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private S3CompatibleService s3CompatibleService;
    @Autowired
    private AmazonS3 s3Client;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadMultipartFile(@RequestParam("uploadfile") MultipartFile[] files) {
        List<String> errorMessages = new ArrayList<>();
        List<String> fileUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                if (file.getOriginalFilename().isEmpty()) {
                    errorMessages.add("Fail! -> Empty File Name");
                    continue;
                }
                String contentType = file.getContentType();
                if (!"application/pdf".equals(contentType)) {
                    errorMessages.add("Only PDF files are allowed");
                    continue;
                }
                String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                s3CompatibleService.uploadFile(file, uniqueFileName);
                String fileUrl = s3Client.getUrl(bucketName, uniqueFileName).toString();
                fileUrls.add(fileUrl);
            } catch (Exception e) {
                errorMessages.add(e.getMessage());
            }
        }
        if (errorMessages .isEmpty()) {
            return ResponseEntity.ok().body(Map.of(
                    "success", true,
                    "fileUrls", fileUrls
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "errors", errorMessages
            ));
        }

    }
    @PostMapping("/upload/image")
    public ResponseEntity<?> uploadImage(@RequestParam("uploadfile") MultipartFile[] files) {
        List<String> errorMessages  = new ArrayList<>();
        List<String> fileUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                if (file.getOriginalFilename().isEmpty()) {
                    errorMessages .add("Fail! -> Empty File Name");
                    continue;
                }
                String contentType = file.getContentType();
                List<String> allowedImageTypes = Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/gif", "image/svg+xml");
                if (!allowedImageTypes.contains(contentType)) {
                    errorMessages .add("Only image files (JPEG, PNG, JPG, GIF) are allowed");
                    continue;
                }
                String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                s3CompatibleService.uploadFile(file, uniqueFileName);
                String fileUrl = s3Client.getUrl(bucketName, uniqueFileName).toString();
                fileUrls.add(fileUrl);
            } catch (Exception e) {
                errorMessages .add("Failed to upload: " + file.getOriginalFilename() + " -> " + e.getMessage());
            }
        }
        if (errorMessages .isEmpty()) {
            return ResponseEntity.ok().body(Map.of(
                    "success", true,
                    "fileUrls", fileUrls
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "errors", errorMessages
            ));
        }
    }
}
