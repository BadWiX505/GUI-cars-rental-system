package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
  private final static String url = "jdbc:mysql://localhost:3306/CARSRENTALDB";
    
    
    public static Connection getConnection()  {
    	Connection con = null;
         try {
			 con = DriverManager.getConnection(url,"root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return con;
    }
}
