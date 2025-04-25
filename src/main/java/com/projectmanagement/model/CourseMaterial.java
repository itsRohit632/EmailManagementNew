package com.projectmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String title;
    private String filePath;
    private LocalDateTime uploadTime;

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getFilePath() { return filePath; }

    public void setFilePath(String filePath) { this.filePath = filePath; }

    public LocalDateTime getUploadTime() { return uploadTime; }

    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }
}
