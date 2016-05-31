/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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

        System.out.println("Try connect to server...");
        try (Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://%s", hostname), dbprops)) {
            System.out.println("Connection established!");
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM test.articles");
            
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString("title"); // 2
                String content = rs.getString(3); // content
                String author = rs.getString("author"); // 4
                Date timestamp = rs.getDate("timestamp"); // 5
                
                System.out.printf("#%03d %s %s %s\n", id, title, author, timestamp);
            }

            conn.close();
            System.out.println("Disconnected!");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
