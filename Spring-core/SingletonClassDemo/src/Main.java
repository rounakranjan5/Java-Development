public class Main {

    public static void main(String[] args) {
        // ek jaisi requirement ke liye same object banna => singleton

        // Method 1 of creating a Singleton Class
        Student s1=Student.getInstance();
        Student s2=Student.getInstance();
        Student s3=Student.getInstance();
        System.out.println("=== Student Objects ====");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        // Method 2 of creating a Singleton Class
        System.out.println("\n=== Learner Objects ====");
        Learner l1=Learner.getLearnerInstance();
        Learner l2=Learner.getLearnerInstance();
        Learner l3=Learner.getLearnerInstance();
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);

    }
}
