public class Learner {

    // Method 2 of creating a Singleton Class

    static Learner learner;

    static Learner getLearnerInstance(){
        if(learner==null){
            learner=new Learner();
        }
        return learner;
    }

}
