import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ThrowsDemo {

    public static void readFile() throws FileNotFoundException {
        FileInputStream fis=new FileInputStream("data.txt");
        System.out.println("File opened Successfully...");
    }

    public static void main(String[] args) {
        try{
            readFile();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
