package com.example.FolderService.repository;

import com.example.FolderService.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {

    List<Folder> findByParentId(Long parentId);
}
