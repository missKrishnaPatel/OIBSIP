/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservationsystem;

/**
 *
 * @author anujp
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class UserLogin {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserLogin() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservationsystem", "root", "123456");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean authenticateUser(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            return rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
