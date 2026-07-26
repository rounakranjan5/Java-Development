package com.example.DTOsAndValidations.dto;

import jakarta.validation.constraints.*;

public class UpdateStudentRequestDto {

    @NotBlank(message = "Name cannot be Empty")
    @Size(min = 2,max = 10)
    private String firstName;

    @NotBlank(message = "Name cannot be Empty")
    @Size(min = 2,max = 10)
    private String lastName;


    @NotNull(message = "Age can't be null")
    @Min(value = 18,message = "Student must be at least 18 years Old")
    private Integer age;

    @NotNull(message = "Roll Number is a required field")
    private Integer rollNumb;

    @NotNull(message = "Course is a required field")
    private String course;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRollNumb() {
        return rollNumb;
    }

    public void setRollNumb(Integer rollNumb) {
        this.rollNumb = rollNumb;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }




}
