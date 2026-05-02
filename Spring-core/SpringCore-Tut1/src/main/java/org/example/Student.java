package org.example;

public class Student {

    Course course;

    public Student() {
        System.out.println("Student default constructor...");
    }

    public Student(Course course) {
        System.out.println("Constructor injection");
        this.course = course;
    }

    public void study(){
        course.learn();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        System.out.println("using setter");
        this.course = course;
    }
}
