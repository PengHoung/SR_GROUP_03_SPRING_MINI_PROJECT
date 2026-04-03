package org.example.sr_group_03_spring_mini_project.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetadata {
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long fileSize;

}
