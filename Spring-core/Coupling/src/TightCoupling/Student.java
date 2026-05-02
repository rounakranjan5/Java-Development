package TightCoupling;

public class Student {


    Student() {
        System.out.println("Student constructor called...");
    }

    void study(){
        // Tight coupling: Student is directly dependent on LearningDSA
        LearningDSA dsa=new LearningDSA();
//        LearningSpring lsp=new LearningSpring();

        dsa.learn();

//        lsp.learn();

//        In this example, the Student class is tightly coupled with LearningDSA because it directly creates its object using the new keyword. If we want to change the learning behavior to LearningSpring, we must modify the Student class. This makes the system rigid and less flexible

    }

}
