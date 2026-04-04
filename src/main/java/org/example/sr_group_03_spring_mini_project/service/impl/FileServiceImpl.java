package org.example.sr_group_03_spring_mini_project.service.impl;
import lombok.SneakyThrows;
import org.example.sr_group_03_spring_mini_project.model.entity.FileMetadata;
import org.example.sr_group_03_spring_mini_project.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${spring.file-upload-path}")
    private String pathName;

    @SneakyThrows
    @Override
    public FileMetadata uploadFile(MultipartFile file) {

        Path rootPath = Paths.get(pathName);
        String fileName = file.getOriginalFilename();

        if (!Files.exists(rootPath)) {
            Files.createDirectories(rootPath);
        }

        fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
        Files.copy(file.getInputStream(), rootPath.resolve(fileName),
                StandardCopyOption.REPLACE_EXISTING);
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/files/preview-file/" + fileName)
                .toUriString();

        return FileMetadata.builder()
                .fileName(fileName)
                .fileUrl("Image coming soon")
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .build();
    }

    @SneakyThrows
    @Override
    public Resource getFileByFileName(String fileName) {
        Path rootPath = Paths.get(pathName);
        return new InputStreamResource(Files.newInputStream(rootPath.resolve(fileName)));
    }
}
