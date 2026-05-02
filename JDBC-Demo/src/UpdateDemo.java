import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateDemo {


        public static void main(String[] args) {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection= DriverManager.getConnection(DBConfig.url,DBConfig.user,DBConfig.password);
                Statement statement=connection.createStatement();
                String query="UPDATE employees SET name='JAI' WHERE salary=34500 ";
                int res=statement.executeUpdate(query);

                System.out.println("Success ! Updated : "+res);
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


