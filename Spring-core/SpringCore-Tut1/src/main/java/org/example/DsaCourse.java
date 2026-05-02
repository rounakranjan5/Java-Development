package org.example;

public class DsaCourse implements Course{
    @Override
    public void learn(){
        System.out.println("learning dsa...");
    }

    public DsaCourse() {
        System.out.println("DSA Course default constructor...");
    }
}
