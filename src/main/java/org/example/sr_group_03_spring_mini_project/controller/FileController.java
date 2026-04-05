package org.example.sr_group_03_spring_mini_project.controller;
import lombok.RequiredArgsConstructor;
import org.example.sr_group_03_spring_mini_project.model.entity.FileMetaData;
import org.example.sr_group_03_spring_mini_project.model.entity.FileMetaData;
import org.example.sr_group_03_spring_mini_project.model.response.APIResponse;
import org.example.sr_group_03_spring_mini_project.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse<FileMetaData>> uploadFile(@RequestParam MultipartFile file)
            throws IOException, URISyntaxException
    {
        FileMetaData fileMetadata = fileService.uploadFile(file);
        APIResponse<FileMetaData> response = APIResponse.<FileMetaData>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("File upload successfully!")
                .payload(fileMetadata)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/preview-file/{file-name}")
    public ResponseEntity<Resource> getFileByFileName(@PathVariable("file-name") String fileName) {
        Resource resource = fileService.getFileByFileName(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(resource);
    }
}