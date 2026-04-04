package org.example.sr_group_03_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.FileMetaData;
import org.example.sr_group_03_spring_mini_project.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${spring.file-upload-path}")
    private String pathName;

    @Override
    public FileMetaData fileUpload(MultipartFile file) throws IOException {
        Path rootPath = Paths.get(pathName);
        String fileName = file.getOriginalFilename();

        // Create directory if not exists
        if (!Files.exists(rootPath)) {
            Files.createDirectories(rootPath);
        }

        // Rename file with UUID to avoid duplicates
        fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);

        // Save file to disk
        Files.copy(file.getInputStream(), rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        // Build preview URL
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/files/preview-file/" + fileName)
                .toUriString();

        return FileMetaData.builder()
                .fileName(fileName)
                .fileUrl(fileUrl)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .build();
    }

    @Override
    public Resource getFileByFileName(String fileName) throws IOException {
        Path rootPath = Paths.get(pathName);
        return new InputStreamResource(Files.newInputStream(rootPath.resolve(fileName)));
    }
}