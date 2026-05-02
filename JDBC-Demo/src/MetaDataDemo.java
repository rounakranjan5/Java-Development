import java.sql.*;

public class MetaDataDemo {
    public static void main(String[] args) {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(DBConfig.url,DBConfig.user,DBConfig.password);

            // DB Meta data ( software info)
            DatabaseMetaData dbMeta=connection.getMetaData();
            System.out.println("------- DB Info -------");
            System.out.println("Driver Name - "+dbMeta.getDriverName());
            System.out.println("Driver Version - "+dbMeta.getDriverVersion());
            System.out.println("DB URL - "+dbMeta.getURL());
            System.out.println("Logged In User - "+dbMeta.getUserName());

            // Result MetaData

            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM employees");
            ResultSetMetaData rsmd=rs.getMetaData();
            System.out.println("---- ResultSet Info ----");

            int totalCols=rsmd.getColumnCount();
            System.out.println("Total No of Columns : "+totalCols);

            //2. What are the names and types of columns ?
            System.out.println("--------------------------------------------");
            System.out.println("Col Index  | Column Name  | Column Type");
            System.out.println("--------------------------------------------");

            for(int i=1;i<=totalCols;i++){
                System.out.println(" "+i+"  |  "+rsmd.getColumnName(i)+"  |  "+rsmd.getColumnTypeName(i));
            }

            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
