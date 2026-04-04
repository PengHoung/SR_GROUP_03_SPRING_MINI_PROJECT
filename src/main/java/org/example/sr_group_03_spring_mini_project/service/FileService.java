package org.example.sr_group_03_spring_mini_project.service;

import org.example.sr_group_03_spring_mini_project.model.entity.FileMetaData;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileMetaData fileUpload(MultipartFile file) throws IOException;
    Resource getFileByFileName(String fileName) throws IOException;
}