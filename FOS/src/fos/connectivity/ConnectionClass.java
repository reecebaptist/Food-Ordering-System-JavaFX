/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fos.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class ConnectionClass {
    public Connection connection;
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        
        String dbName = "fos";
        String username = "root";
        String password = "";
        
        
        Class.forName("com.mysql.jdbc.Driver");
        
        connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,username, password);
        
       
        
        return connection;
    }
    
}
