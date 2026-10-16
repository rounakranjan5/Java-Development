package com.example.FolderService.dto;

public class FolderRequestDto {

    private Long parentId;
    private String name;

    public FolderRequestDto() {
    }

    public FolderRequestDto(Long parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
