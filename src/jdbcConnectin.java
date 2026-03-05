import com.sun.source.tree.BreakTree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// non main method to connect to mysql using connectin and drivers
public class jdbcConnectin {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/FirRecords";
    private static final String user ="root";
    private static final String password ="root";
    void jbbcConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.driver");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private static Connection safegetSQl(){
        try {
            Connection connection = DriverManager.getConnection(url, user,password);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    // safe garding the connection statement

    
    public Connection getConnected(){
        return safegetSQl();
    }




}
