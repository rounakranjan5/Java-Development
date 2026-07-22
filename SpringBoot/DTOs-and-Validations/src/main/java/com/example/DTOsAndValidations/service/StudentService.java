package com.example.DTOsAndValidations.service;

import com.example.DTOsAndValidations.dto.CreateStudentRequestDto;
import com.example.DTOsAndValidations.dto.CreateStudentResponseDto;
import com.example.DTOsAndValidations.entity.Student;
import com.example.DTOsAndValidations.repository.StudentRepository;
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

    public CreateStudentResponseDto enrollNewStudent(CreateStudentRequestDto studentRequestDto){
//        newUserdata.setDeleted(false);

        Student newUserdata=mapToEntity(studentRequestDto);


        Student addedUser=studentRepository.save(newUserdata);
        return mapToDto(addedUser);
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

    private Student mapToEntity(CreateStudentRequestDto studentRequestDto){
        Student student=new Student();

        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setAge(studentRequestDto.getAge());
        student.setCourse(studentRequestDto.getCourse());
        student.setRollNumb(studentRequestDto.getRollNumb());


        student.setDeleted(false);

        return student;
    }

    private CreateStudentResponseDto mapToDto(Student student){
        CreateStudentResponseDto responseDto=new CreateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setAge(student.getAge());
        responseDto.setCourse(student.getCourse());
        responseDto.setFirstName(student.getFirstName());
        responseDto.setLastName(student.getLastName());
        responseDto.setRollNumb(student.getRollNumb());
        responseDto.setEmail(student.getEmail());
        responseDto.setDeleted(student.isDeleted());
        responseDto.setMessage("Student Created Successfully !!");

        return responseDto;

    }

}
