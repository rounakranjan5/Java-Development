package LooseCoupling;

import LooseCoupling.Student;

public class MainLC {

    public static void main(String[] args) {

//        Constructor Dependency Injection
        Learn c1=new LearnDSA();
//        Learn c2=new LearnSpring();

//        Student s=new Student(c1);
//        Student s=new Student(c2);




//        Setter Dependency Injection

        Student s2=new Student();
        s2.setCourse(c1);

        s2.study();

//        "In this example, I achieved loose coupling by introducing an interface Learn, and making Student depend on it instead of concrete classes. The dependency is injected using constructor and setter injection. This allows me to switch implementations like LearnDSA or LearnSpring without modifying the Student class."

    }

}
