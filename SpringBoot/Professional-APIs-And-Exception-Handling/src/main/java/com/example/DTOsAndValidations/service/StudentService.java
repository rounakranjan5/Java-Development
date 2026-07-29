package com.example.DTOsAndValidations.service;

import com.example.DTOsAndValidations.Exception.DuplicateResourceException;
import com.example.DTOsAndValidations.Exception.ResourceNotFoundException;
import com.example.DTOsAndValidations.dto.CreateStudentRequestDto;
import com.example.DTOsAndValidations.dto.CreateStudentResponseDto;
import com.example.DTOsAndValidations.dto.UpdateStudentRequestDto;
import com.example.DTOsAndValidations.dto.UpdateStudentResponseDto;
import com.example.DTOsAndValidations.entity.Student;
import com.example.DTOsAndValidations.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.lang.Boolean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<CreateStudentResponseDto> displayAllStudents(){
        List<Student> data=studentRepository.findAll();

        List<CreateStudentResponseDto> createStudentResponseDtos=new ArrayList<>();

        for(int i=0;i<data.size();i++){
            createStudentResponseDtos.add(mapToDto(data.get(i)));
        }

        return createStudentResponseDtos;
    }

    public CreateStudentResponseDto getStudent(Long id){
        Student student= studentRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResourceNotFoundException("Student With id :"+id+" Doesn't exists"));

        return mapToDto(student);

    }

    public CreateStudentResponseDto enrollNewStudent(CreateStudentRequestDto studentRequestDto){
        Student newUserdata=mapToEntity(studentRequestDto);

        if(emailExists(newUserdata)){
            throw new DuplicateResourceException("User with email : "+newUserdata.getEmail()+" already exists");
        }

        Student addedUser=studentRepository.save(newUserdata);
        return mapToDto(addedUser);
    }

    public UpdateStudentResponseDto updateStudentData(Long id, UpdateStudentRequestDto newData){

       Student presentStudentState=studentRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResourceNotFoundException("Student with id : "+id+" doesn't exists"));

        presentStudentState.setAge(newData.getAge());
        presentStudentState.setCourse(newData.getCourse());
        presentStudentState.setFirstName(newData.getFirstName());
        presentStudentState.setLastName(newData.getLastName());
        presentStudentState.setRollNumb(newData.getRollNumb());

        Student savedStudent=studentRepository.save(presentStudentState);

        return mapToUpdateDto(savedStudent);

    }

    public void deleteStudentData(Long id){
        Student studentExists =studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id : "+id+" doesn't exists"));

        studentRepository.delete(studentExists);

    }

    public void deleteStudentSoftly(Long id){
        Student stud=studentRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResourceNotFoundException("Student with id : "+id+" doesn't exists"));

        stud.setDeleted(true);
        studentRepository.save(stud);

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

    private UpdateStudentResponseDto mapToUpdateDto(Student student){
        UpdateStudentResponseDto responseDto=new UpdateStudentResponseDto();

        responseDto.setAge(student.getAge());
        responseDto.setCourse(student.getCourse());
        responseDto.setFirstName(student.getFirstName());
        responseDto.setLastName(student.getLastName());
        responseDto.setRollNumb(student.getRollNumb());

        responseDto.setMessage("Student Updated Successfully !!");

        return responseDto;

    }

    private boolean emailExists(Student student){
        return studentRepository.existsByEmail(student.getEmail());
    }

}
