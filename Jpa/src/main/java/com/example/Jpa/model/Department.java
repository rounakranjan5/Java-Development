package com.example.Jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.REMOVE
    )
    private List<Student> students=new ArrayList<>();

    public List<Student> addStudent(Student student){
        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(student);
        student.setDepartment(this);
        return students;
    }

}
