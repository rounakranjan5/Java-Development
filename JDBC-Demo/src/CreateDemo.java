import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDemo {

    public static void main(String[] args) {
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(DBConfig.url,DBConfig.user,DBConfig.password);
            Statement statement=connection.createStatement();
            String query="INSERT INTO employees VALUES(3,'Peter','Lead-1',87654),(4,'parker','SDET-1',34500)";
            int res=statement.executeUpdate(query);

            System.out.println("Success ! Row Inserted: "+res);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
