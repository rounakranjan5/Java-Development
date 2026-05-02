import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Basic {
    public static void main(String[] args) {

        try{
            // step 1 : load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // step 2 : get connection
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db","","");

            // step 3 : create statement
            Statement statement=connection.createStatement();

            // step 4 : execute query
            // READ
            ResultSet resultSet =statement.executeQuery("SELECT * from employees");

            while(resultSet.next()){
                System.out.println(resultSet.getString("name")+" - "+resultSet.getString("position")+" - "+resultSet.getInt("salary"));
            }

            // step 5 : close connection
            connection.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}