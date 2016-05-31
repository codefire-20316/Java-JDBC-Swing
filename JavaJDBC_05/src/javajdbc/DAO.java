/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author human
 */
public class DAO {
    
    private String hostname;
    private String username;
    private String password;

    public DAO(String hostname, String username, String password) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:mysql://%s", hostname), username, password);
    }
    
    public List<String> getDatabases() {
        List<String> databaseList = new  ArrayList<>();
        
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW DATABASES");
            
            while (rs.next()) {
                String tableName = rs.getString(1);
                databaseList.add(tableName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return databaseList;
    }
    
}
