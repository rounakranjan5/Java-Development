package com.example.FolderService.service;

import com.example.FolderService.dto.FolderRequestDto;
import com.example.FolderService.dto.FolderResponseDto;
import com.example.FolderService.entity.Folder;
import com.example.FolderService.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FolderService {

    FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public List<FolderResponseDto> getAllFolders() {
        List<Folder> folders=folderRepository.findAll();

        List<FolderResponseDto> folderResponseDtoList=new ArrayList<>();

        for (Folder f : folders){
            FolderResponseDto folderResponseDto=mapToDto(f);
            folderResponseDtoList.add(folderResponseDto);
        }
        return folderResponseDtoList;
    }

    private Folder mapToEntity(FolderRequestDto folderRequestDto){
        Folder folder=new Folder();
        folder.setName(folderRequestDto.getName());
        folder.setParentId(folderRequestDto.getParentId());
        return folder;
    }

    private FolderResponseDto mapToDto(Folder folder){
        FolderResponseDto folderResponseDto=new FolderResponseDto();
        folderResponseDto.setId(folder.getId());
        folderResponseDto.setName(folder.getName());
        folderResponseDto.setParentId(folder.getParentId());
        return folderResponseDto;
    }

    public FolderResponseDto getFolder(Long id) {
        Folder folder=folderRepository.findById(id).orElseThrow(()-> new RuntimeException("Requested Folder Not Found"));
        return mapToDto(folder);
    }

    public FolderResponseDto createFolder(FolderRequestDto folderRequestDto) {
        Folder newFolder=mapToEntity(folderRequestDto);
        Folder folder=folderRepository.save(newFolder);
        return mapToDto(folder);
    }

    public void deleteFolder(Long id) {
        Folder folder=folderRepository.findById(id).orElseThrow(()->new RuntimeException("Requested Folder Not Found"));
        folderRepository.delete(folder);
    }

    public List<FolderResponseDto> getFolderByParent(Long parentId) {
        List<Folder> folders=folderRepository.findByParentId(parentId);
        List<FolderResponseDto> folderResponseDtoList=new ArrayList<>();

        for (Folder f : folders){
            FolderResponseDto folderResponseDto=mapToDto(f);
            folderResponseDtoList.add(folderResponseDto);
        }
        return folderResponseDtoList;
    }

    public Map<String, Object> createNewFolder(Map<java.lang.String,java.lang.Object> request) {
        try{
            String name=(String) request.get("name");
            Long parentId=request.get("parentId")!=null ?
                    Long.valueOf(request.get("parentId").toString() ): null;

            Folder newFolder=new Folder();
            newFolder.setName(name);
            newFolder.setParentId(parentId);

            Folder savedFolder=folderRepository.save(newFolder);
            return Map.of("Success",true,"folder",savedFolder);

        } catch (Exception e) {
            return Map.of("Success",false,"error",e.getMessage());
        }
    }
}
