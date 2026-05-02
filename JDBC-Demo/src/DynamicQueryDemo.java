import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DynamicQueryDemo {
    public static void main(String[] args) {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(DBConfig.url,DBConfig.user,DBConfig.password);

            Scanner sc=new Scanner(System.in);

            System.out.println("Enter id");
            int id=sc.nextInt();

            System.out.println("Enter name");
            String name=sc.next();

            System.out.println("Enter Designation");
            String des=sc.next();

            System.out.println("Enter Salary");
            double sal=sc.nextDouble();

            String query="INSERT INTO employess VALUES (?,?,?,?)";
            PreparedStatement ps= connection.prepareStatement(query);
            ps.setInt();
            ps.setString(name);
            ps.setString(des);
            ps.setDouble(sal);

            int res=ps.executeUpdate();
            System.out.println(res+" row inserted successfully!!");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
