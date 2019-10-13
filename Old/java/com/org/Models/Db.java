package com.org.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static final String CONNECTION_URI = "jdbc:mysql://localhost:3306/alphacab";

    public java.sql.Connection connection;

    public Db(){
        this.connection = null;
    }

    public void getConnection(){

        try{
            this.connection = DriverManager.getConnection(CONNECTION_URI, "root", "FragMent@44569");
        }
        catch(SQLException e){
            System.out.println("state: " + e.getSQLState());
            System.out.println("message: " + e.getMessage());
            System.out.println("error code:" + e.getErrorCode());
            this.connection = null;
        }
    }
}
