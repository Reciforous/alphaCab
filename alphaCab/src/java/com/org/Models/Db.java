package com.org.Models;

import com.org.Helpers.Functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
    private static final String CONNECTION_URI = "jdbc:derby://localhost:1527/alphacab";

    public java.sql.Connection connection;

    public Db(){
        this.connection = null;
    }

    public void getConnection(){
        try{
            this.connection = DriverManager.getConnection(CONNECTION_URI, "alphacab", "alphacab");
        }
        catch(SQLException e){
            System.out.println("state: " + e.getSQLState());
            System.out.println("message: " + e.getMessage());
            System.out.println("error code:" + e.getErrorCode());
            this.connection = null;
        }
    }

    // USE WITH CAUTION!
    public void nuke(){
        // This is the mother load of roflmao
        String sql = "DROP Database";
        this.getConnection();

        try(Statement stmt = this.connection.createStatement()){
            stmt.executeUpdate(sql);
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(this.connection);
        }
    }
}
