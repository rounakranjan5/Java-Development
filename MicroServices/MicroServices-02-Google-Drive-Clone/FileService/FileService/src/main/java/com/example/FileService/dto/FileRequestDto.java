package com.example.FileService.dto;

public class FileRequestDto {

    private String name;
    private Long size;
    private Long folderId;
    private String path;

    public FileRequestDto(){
    }

    public FileRequestDto(String name, Long size, Long folderId, String path) {
        this.name = name;
        this.size = size;
        this.folderId = folderId;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
