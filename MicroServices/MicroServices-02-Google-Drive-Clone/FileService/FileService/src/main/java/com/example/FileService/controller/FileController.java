package com.example.FileService.controller;

import com.example.FileService.dto.FileRequestDto;
import com.example.FileService.dto.FileResponseDto;
import com.example.FileService.entity.File;
import com.example.FileService.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {

    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/create")
    public ResponseEntity<FileResponseDto> createFile(@RequestBody FileRequestDto fileRequestDto){
        return ResponseEntity.ok(fileService.createFile(fileRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<FileResponseDto>> getAllFiles(){
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileResponseDto> getFile(@PathVariable Long id){
        return ResponseEntity.ok(fileService.getFile(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
        return ResponseEntity.ok("Request File Deleted!!");
    }

    @GetMapping("/folder/{folderId}")
    public ResponseEntity<List<FileResponseDto>> getFilesByFolder(@PathVariable Long folderId){
        return ResponseEntity.ok(fileService.getFilesByFolder(folderId));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("name") String name, @RequestParam("folderId") Long folderId, @RequestParam("file")MultipartFile file){
        return ResponseEntity.ok(fileService.uploadFile(name,folderId,file));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id){
        return ResponseEntity.ok(fileService.downloadFile(id));
    }

}
