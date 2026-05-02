package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");

        Student s1=context.getBean(Student.class);
//        Student s2=context.getBean(Student.class);
//
        s1.study();

        // Note : By default singleton objects of student is created so s1 and s2 are singleton objects here you can verify by printing ( by default scope of bean is singleton ) to make its scope prototype you can add scope="prototype" in the bean then new objects will be created for s1 and s2
//        System.out.println(s1);
//        System.out.println(s2);
    }

}
