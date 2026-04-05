package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.SneakyThrows;
import org.example.sr_group_03_spring_mini_project.model.entity.FileMetaData;
import org.example.sr_group_03_spring_mini_project.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${rustfs.endpoint}")
    private String endpoint;

    @Value("${rustfs.bucket}")
    private String bucket;

    private final S3Client s3Client;

    public FileServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @SneakyThrows
    @Override
    public FileMetaData uploadFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalName);


        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(fileName)
                        .contentType(file.getContentType())
                        .contentLength(file.getSize())
                        .build(),
                RequestBody.fromBytes(file.getBytes())
        );

        String fileUrl = endpoint + "/" + bucket + "/" + fileName;

        return FileMetaData.builder()
                .fileName(fileName)
                .fileUrl(fileUrl)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .build();
    }

    @SneakyThrows
    @Override
    public Resource getFileByFileName(String fileName) {
        ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(fileName)
                        .build()
        );
        return new InputStreamResource(s3Object);
    }
}
