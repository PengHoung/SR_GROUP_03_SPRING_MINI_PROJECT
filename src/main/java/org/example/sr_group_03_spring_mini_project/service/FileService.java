package org.example.sr_group_03_spring_mini_project.service;
import org.example.sr_group_03_spring_mini_project.model.entity.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileMetadata uploadFile(MultipartFile file);
    Resource getFileByFileName(String fileName);
}
