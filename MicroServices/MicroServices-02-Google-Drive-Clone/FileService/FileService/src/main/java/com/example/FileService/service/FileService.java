package com.example.FileService.service;

import com.example.FileService.dto.FileRequestDto;
import com.example.FileService.dto.FileResponseDto;
import com.example.FileService.entity.File;
import com.example.FileService.repository.FileRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    FileRepository fileRepository;

    private static final String UPLOAD_DIR="uploads";

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileResponseDto createFile(FileRequestDto fileRequestDto) {
        File file=mapToEntity(fileRequestDto);
        fileRepository.save(file);
        return mapToDto(file);
    }

    private FileResponseDto mapToDto(File file){
        FileResponseDto fileResponseDto=new FileResponseDto();
        fileResponseDto.setName(file.getName());
        fileResponseDto.setSize(file.getSize());
        fileResponseDto.setFolderId(file.getFolderId());
        fileResponseDto.setPath(file.getPath());
        return fileResponseDto;
    }

    public FileResponseDto getFile(Long id) {
        File file=fileRepository.findById(id).orElseThrow(()-> new RuntimeException("Requested File Not Found!!"));
        return mapToDto(file);
    }

    private File mapToEntity(FileRequestDto fileRequestDto){
        File file=new File();
        file.setName(fileRequestDto.getName());
        file.setSize(fileRequestDto.getSize());
        file.setFolderId(fileRequestDto.getFolderId());
        file.setPath(fileRequestDto.getPath());
        return file;
    }

    public List<FileResponseDto> getAllFiles() {
        List<File> files=fileRepository.findAll();
        List<FileResponseDto> fileResponseDtoList=new ArrayList<>();
        for (File f : files){
            FileResponseDto fileResponseDto=mapToDto(f);
            fileResponseDtoList.add(fileResponseDto);
        }
        return fileResponseDtoList;
    }

    public void deleteFile(Long id) {
        File file=fileRepository.findById(id).orElseThrow(()-> new RuntimeException("Requested File Not Found!!"));
        fileRepository.delete(file);
    }

    public List<FileResponseDto> getFilesByFolder(Long folderId) {
        List<File> files=fileRepository.findByFolderId(folderId);

        List<FileResponseDto> fileResponseDtoList=new ArrayList<>();

        for (File f : files){
            FileResponseDto fileResponseDto=mapToDto(f);
            fileResponseDtoList.add(fileResponseDto);
        }

        return fileResponseDtoList;
    }

    public String uploadFile(String name, Long folderId, MultipartFile file) {

        try{
            long fileSize=file.getSize();
            String fileName=file.getOriginalFilename();

            File newFile=new File();
            newFile.setName(fileName!=null ? fileName : name);
            newFile.setFolderId(folderId);
            newFile.setPath("/files/"+newFile.getId());

            String uploadDirPath=UPLOAD_DIR+ java.io.File.separator+newFile.getId();
            Files.createDirectories(Paths.get(uploadDirPath));
            Path filePath=Paths.get(uploadDirPath, newFile.getName());
            Files.write(filePath,file.getBytes());

            File saved=fileRepository.save(newFile);

            return "File Uploaded!!";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    public Object downloadFile(Long id) {
        try{
            File file=fileRepository.findById(id).orElseThrow(()-> new RuntimeException("Requested File Not Found!!"));
            if (file==null){
                return ResponseEntity.notFound().build();
            }

            Path filePath=Paths.get(UPLOAD_DIR,id.toString(),file.getName());

            if (!Files.exists(filePath)){
                return ResponseEntity.notFound().build();
            }

            byte[] fileContent=Files.readAllBytes(filePath);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getName()+"\"")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_LENGTH,String.valueOf(fileContent.length))
                    .body(fileContent);
        }catch (IOException e){
            return ResponseEntity.internalServerError().body("Some error Occurred!! Try again : "+e.getMessage());
        }
    }
}
