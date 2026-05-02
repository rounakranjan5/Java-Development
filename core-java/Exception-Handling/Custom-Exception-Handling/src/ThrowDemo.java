public class ThrowDemo {

    public static void main(String[] args) {
        int age=14;
        validate(age);
    }

    public static void validate(int age){
        try{
            if(age<18){
                throw new IllegalArgumentException("Age Can't be less than 18");
            }
            else System.out.println("You are ready to go buddy...");
        }
        catch (Exception e){
            System.out.println("inside catch block...");
            System.out.println(e);
        }
        finally{
            System.out.println("inside finally\nEnd of the Program...");
        }
    }
}
