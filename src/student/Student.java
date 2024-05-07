package student;

import DB.MyConnection;
//import com.mysql.cj.xdevapi.Statement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.sql.*;
//import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sagar Tiwari
 *
 *
 *
 *
 *
 *
 */
public class Student {

//    public Student() {
//        // Initialize the connection
//        con = (Connection) MyConnection.getConnection();
//        if (con == null) {
//            throw new IllegalStateException("Failed to initialize database connection");
//        }
//    }
//    
//    
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;

    // get table max row
    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from student");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;

    }

//public class Student {
//    Connection con = (Connection) MyConnection.getConnection();
//    PreparedStatement ps;
//
//    // get table max row
//    public int getMax() {
//        int id = 0;
//        Statement st = null;
//        ResultSet rs = null;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery("SELECT MAX(id) FROM student");
//            if (rs.next()) {
//                id = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            // Close resources in finally block
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (st != null) {
//                    st.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return id + 1;
//    }
//}
//Insert Data Into Student Table
    public void insert(int id, String sname, String date, String gender, String email,
            String phone, String father, String mother, String address1, String address2, String imagePath) {
        String sql = "iNSERT INTO STUDENT VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, sname);
            ps.setString(3, date);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, father);
            ps.setString(8, mother);
            ps.setString(9, address1);
            ps.setString(10, address2);
            ps.setString(11, imagePath);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New Student Added Succesfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// check email is already exist or not

    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("SELECT * FROM STUDENT WHERE EMAIL = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
// check phone number is already exist or not

    public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("SELECT * FROM STUDENT WHERE PHONE = ?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // check Student Id number is already exist or not

    public boolean isIdExist(int id) {
        try {
            ps = con.prepareStatement("SELECT * FROM STUDENT WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
// get all the student values from database student table

    public void getStudentValue(JTable table, String searchValue) {
        String sql = "SELECT * FROM STUDENT WHERE CONCAT(ID,NAME,EMAIL,PHONE)LIKE ? ORDER BY ID DESC";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[11];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Update Stusdent Values....

    public void update(int id, String sname, String date, String gender, String email,
            String phone, String father, String mother, String address1, String address2, String imagePath) {
        String sql = "UPDATE STUDENT SET NAME=?, DATE_OF_BIRTH=?, GENDER=?, EMAIL=?, PHONE=?, "
                + "FATHER_NAME=?, MOTHER_NAME=?, ADDRESS1=?, ADDRESS2=?, IMAGE_PATH=? WHERE ID=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, sname);
            ps.setString(2, date);
            ps.setString(3, gender);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, father);
            ps.setString(7, mother);
            ps.setString(8, address1);
            ps.setString(9, address2);

            ps.setString(10, imagePath);
            ps.setInt(11, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Student Information Updated Successfully !");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Student Date Delete Using Delete Button On Student Page:

    public void delete(int id) {
        int yesOrNo = JOptionPane.showConfirmDialog(null, "Courses And Score Records Will Also Be Deleted",
                "Student Delete", JOptionPane.OK_CANCEL_OPTION, 0);
        if (yesOrNo == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("DELETE FROM STUDENT WHERE ID = ?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Student Data Deleted Successfully");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
