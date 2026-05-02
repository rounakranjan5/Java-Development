package LooseCoupling;

public class Student {
    Learn course;

//    Constructor Dependency Injection
    public Student(Learn course) {
        System.out.println("Student Constructor called ...");
        this.course =course;
    }

    void study(){
        course.learning();
    }

//    Setter Dependency Injection

    public Student(){
        System.out.println("No params Student Constructor called...");
    }

    public Learn getCourse() {
        return course;
    }

    public void setCourse(Learn course) {
        System.out.println("Setter DI called...");
        this.course = course;
    }
}
