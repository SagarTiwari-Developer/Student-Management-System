
package student;

import DB.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;

/**
 *
 * @author Sagar Tiwari
 */
public class MarksSheet {
     Connection con = MyConnection.getConnection();
    PreparedStatement ps;

    // check MarksSheet Id number is already exist or not

    public boolean isIdExist(int sid) {
        try {
            ps = con.prepareStatement("SELECT * FROM SCORE WHERE STUDENT_ID = ?");
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarksSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // get all the Score values from database MarksSheet table
    public void getScoreValue(JTable table, int sid) {
            String sql = "SELECT * FROM SCORE WHERE STUDENT_ID = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getString(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getString(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getDouble(11);
                row[11] = rs.getString(12);
                row[12] = rs.getDouble(13);
                row[13] = rs.getDouble(14);

                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MarksSheet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public double getCGPA(int sid){
        double cgpa = 0.0;
        Statement st;
        
         try {
             st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT AVG(AVERAGE) FROM SCORE WHERE STUDENT_ID = " + sid + "");
             if(rs.next()){
                 double averageMarks = rs.getDouble(1);
            cgpa = calculateCGPA(averageMarks);
                // cgpa = rs.getDouble(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(MarksSheet.class.getName()).log(Level.SEVERE, null, ex);
         }return cgpa;
    }
    private double calculateCGPA(double averageMarks) {
    // Define conditions to map marks to CGPA
    if (averageMarks >= 91) {
        return 10;
    } else if (averageMarks >= 81) {
        return 9;
    } else if (averageMarks >= 71) {
        return 8;
    } else if (averageMarks >= 61) {
        return 7;
    } else if (averageMarks >= 51) {
        return 6;
    } else if (averageMarks >= 41) {
        return 5;
    } else if (averageMarks >= 31) {
        return 4;
    } else if (averageMarks >= 21) {
        return 3;
    } else if (averageMarks >= 11) {
        return 2;
    } else if (averageMarks >= 0) {
        return 1;
    } else {
        return 0; // Invalid marks
    }
}
}
