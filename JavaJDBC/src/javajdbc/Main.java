/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author human
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        // Load JDBC driver.
        Class.forName(com.mysql.jdbc.Driver.class.getName());
        
        String hostname = "192.168.1.99:3306";
        String username = "student";
        String password = "12345";
        
        try {
            System.out.println("Try connect to server...");
            Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://%s", hostname), username, password);
            System.out.println("Connection established!");
            
            conn.close();
            System.out.println("Disconnected!");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
