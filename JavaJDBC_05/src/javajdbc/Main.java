/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
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
        String username = dbprops.getProperty("user");
        String password = dbprops.getProperty("password");
        
        DAO dao = new DAO(hostname, username, password);
        
        List<String> databases = dao.getDatabases();
        
        for (String database : databases) {
            System.out.println(database);
        }

    }

}
