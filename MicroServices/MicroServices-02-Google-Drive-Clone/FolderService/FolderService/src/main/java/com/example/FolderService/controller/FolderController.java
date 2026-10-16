package com.example.FolderService.controller;

import com.example.FolderService.dto.FolderRequestDto;
import com.example.FolderService.dto.FolderResponseDto;
import com.example.FolderService.service.FolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/folder")
public class FolderController {

    FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping
    public ResponseEntity<List<FolderResponseDto>> getAllFolders(){
        return ResponseEntity.ok(folderService.getAllFolders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolderResponseDto> getFolder(@PathVariable Long id){
        return ResponseEntity.ok(folderService.getFolder(id));
    }

    @PostMapping
    public ResponseEntity<FolderResponseDto> createFolder(@RequestBody FolderRequestDto folderRequestDto){
        return ResponseEntity.ok(folderService.createFolder(folderRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFolder(@PathVariable Long id){
        folderService.deleteFolder(id);
        return ResponseEntity.ok("Request Folder Deleted!!");
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<FolderResponseDto>> getFolderByParent(@PathVariable Long parentId){
        return ResponseEntity.ok(folderService.getFolderByParent(parentId));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createNewFolder(@RequestBody Map<String,Object> request){
        return ResponseEntity.ok(folderService.createNewFolder(request));
    }

}
