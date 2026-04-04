package org.example.sr_group_03_spring_mini_project.controller;

import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.FileMetaData;
import org.example.sr_group_03_spring_mini_project.model.response.ApiResponse;
import org.example.sr_group_03_spring_mini_project.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<FileMetaData>> uploadFile(
            @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "File uploaded successfully!",
                        HttpStatus.CREATED,
                        fileService.fileUpload(file)
                ));
    }

    @GetMapping("/preview-file/{file-name}")
    public ResponseEntity<Resource> previewFile(
            @PathVariable("file-name") String fileName) throws IOException {
        Resource resource = fileService.getFileByFileName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}