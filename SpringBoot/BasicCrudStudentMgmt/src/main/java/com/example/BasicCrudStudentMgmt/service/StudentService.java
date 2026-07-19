package com.example.BasicCrudStudentMgmt.service;

import com.example.BasicCrudStudentMgmt.entity.Student;
import com.example.BasicCrudStudentMgmt.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.lang.Boolean;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> displayAllStudents(){
        List<Student> data=studentRepository.findAll();

        return data;
    }

    public Optional<Student> getStudent(Long id){
        Optional<Student> student= studentRepository.findByIdAndDeletedFalse(id);
        return student;
    }

    public Student enrollNewStudent(Student newUserdata){
        newUserdata.setDeleted(false);
        Student addedUser=studentRepository.save(newUserdata);
        return addedUser;
    }

    public Student updateStudentData(Long id, Student newData){

        Optional<Student> presentStudentState=studentRepository.findByIdAndDeletedFalse(id);

        if(presentStudentState.isEmpty()) return null;

        Student existingStud=presentStudentState.get();

        existingStud.setAge(newData.getAge());
        existingStud.setCourse(newData.getCourse());
        existingStud.setEmail(newData.getEmail());
        existingStud.setFirstName(newData.getFirstName());
        existingStud.setLastName(newData.getLastName());
        existingStud.setRollNumb(newData.getRollNumb());

        return studentRepository.save(existingStud);

    }

    public Boolean deleteStudentData(Long id){
        Boolean studentExists =studentRepository.existsById(id);

        if(!studentExists) return null;

        studentRepository.deleteById(id);

        return true;

    }

    public Boolean deleteStudentSoftly(Long id){
        Optional<Student> stud=studentRepository.findByIdAndDeletedFalse(id);

        if(stud.isEmpty()) return false;

        Student existingStudent=stud.get();

        existingStudent.setDeleted(true);
        studentRepository.save(existingStudent);

        return true;
    }

}
