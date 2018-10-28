package com.tfg2018.ws.rest.database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tomas
 */
public class DatabaseManager {
    
    private static Connection Conexion;
    
    public void MySQLConnection() throws Exception {
    	try
    	{
    		Class.forName("com.mysql.cj.jdbc.Driver");
           	Conexion = DriverManager.getConnection("jdbc:mysql://104.248.247.74:3306/TFT_BLOCKCHAIN", "tomas", "Jyyhcwbr.9");
           	Conexion.close();
           
    	} catch (Exception e)
    	{
    	   e.printStackTrace();
    	}
    }
    
}
