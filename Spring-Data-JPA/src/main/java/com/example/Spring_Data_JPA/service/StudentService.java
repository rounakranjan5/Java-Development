package com.example.Spring_Data_JPA.service;

import com.example.Spring_Data_JPA.model.Student;
import com.example.Spring_Data_JPA.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    StudentRepository studentRepository;

    public void createStudent(Student student){
        studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public Student getStudentByEmail(String email){
        Optional<Student> stud=studentRepository.fetchStudentByEmail(email);

        return stud.get();
    }


    //pagination
    public List<Student> getStudentBySameName(String name,int page,int size){

//        Sort sort=Sort.by("name").ascending();

        Pageable pageable= PageRequest.of(page,size);

        Page<Student> studentList=studentRepository.findAllByName(name,pageable);

        return studentList.getContent();

    }
}
