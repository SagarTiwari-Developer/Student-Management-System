
package DB;

//import com.sun.jdi.connect.spi.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.Connection;



/**
 *
 * @author Sagar Tiwari
 */
public class MyConnection {
    private static final String username="root";
    private static final String password="";
    private static final String dataConn="jdbc:mysql://localhost:3306/student_management";
    private static Connection con= null;
    
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(dataConn,username,password);
                    } catch (Exception ex) {
                        ex.printStackTrace();
          // System.out.println(ex.getMessage());
        }
        return con;
    }
}
