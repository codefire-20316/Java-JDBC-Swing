/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
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

        Properties dbprops = new Properties();
        
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            dbprops.load(fis);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        String hostname = dbprops.getProperty("hostname");

        try {
            System.out.println("Try connect to server...");
            Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://%s", hostname), dbprops);
            System.out.println("Connection established!");

            conn.close();
            System.out.println("Disconnected!");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
