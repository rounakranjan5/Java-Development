package org.example;

public class SpringCourse implements  Course{
    @Override
    public void learn() {
        System.out.println("learning Spring...");
    }

    public SpringCourse() {
        System.out.println("Spring Course default constructor...");
    }
}
