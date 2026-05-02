import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteDemo {


    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(DBConfig.url,DBConfig.user,DBConfig.password);
            Statement statement=connection.createStatement();
            String query="DELETE FROM employees WHERE salary=34500 ";
            int res=statement.executeUpdate(query);

            System.out.println("Success ! Deleted : "+res);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


