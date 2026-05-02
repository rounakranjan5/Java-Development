public class Student {

    // Method 1 of creating a singleton class
    static Student student=new Student();

    static Student getInstance(){
        return student;
    }

}
