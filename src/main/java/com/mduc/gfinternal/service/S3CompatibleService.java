package com.mduc.gfinternal.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class S3CompatibleService {
    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public void uploadFile(MultipartFile file,String uniqueFileName) {
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            PutObjectRequest putRequest = new PutObjectRequest(bucketName, uniqueFileName, inputStream, new ObjectMetadata());
            s3Client.putObject(putRequest);
            s3Client.setObjectAcl(bucketName, uniqueFileName, CannedAccessControlList.PublicRead);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload file");
        }
    }
}
