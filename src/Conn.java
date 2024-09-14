
import java.sql.*;

public class Conn {
    
    private final String USER = "root";
    private final String PASS = ""; //Enter your database password here.
    Connection connection;
    Statement statement;


    public Conn(){
        
        try {
            connection = DriverManager.getConnection("", USER, PASS); //Enter your database link here.
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
