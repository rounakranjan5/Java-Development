public class CustomException {

    public static void main(String[] args) {
        int marks=200;
        try{
            if(marks<0 || marks>100){
                throw new InvalidExamMarks("Marks can't be less than 0 or greater than 100");
            }else{
                System.out.println("Your marks : "+marks);
            }
        }
        catch (InvalidExamMarks e){
            System.out.println(e.getMessage());
        }
    }
}

class InvalidExamMarks extends Exception{
    public InvalidExamMarks(String message){
        super(message);
    }
}
