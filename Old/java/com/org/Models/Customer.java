package com.org.Models;

import com.org.Helpers.Message;
import com.org.Helpers.Functions;
import com.org.Models.Db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
    public Integer id = null;
    public String name = null;
    public String address = null;

    public Message createTable(){
        Message message = new Message();
        String sql = "CREATE TABLE Customer (\n" +
                "  Name varchar(20),\n" +
                "  Address varchar(60),\n" +
                "  id int not null,\n" +
                "  PRIMARY KEY (id)\n" +
                ");";

        Db db = new Db();
        db.getConnection();

        try(Statement stmt = db.connection.createStatement()){
            stmt.execute(sql);
            message = new Message(
                    true,
                    "Table created successfully",
                    "success"
            );
        }
        catch (SQLException e){
            message = new Message(
                    false,
                    "Error: " + e.getMessage(),
                    "error"
            );
            Functions.printSQLError(e);
        }
        finally {
            try{
                db.connection.close();
            }
            catch (SQLException e){
                Functions.printSQLError(e);
            }
        }
        return message;
    }

//    public static Customer getById(Integer id){
//        Customer customer = new Customer;
//        String sql = "select name, address from Customer where id = ?";
//
//    }

    public Message add(){
        Message message = new Message();
        String sql = "insert into Customer (id, name, address) values (?, ?, ?)";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id);
            pstmt.setString(2, this.name);
            pstmt.setString(3, this.address);
            pstmt.executeUpdate();

            message = new Message(
                    true,
                    "Customer added successfully",
                    "success"
            );
        }
        catch (SQLException e){
            message = new Message(
                    false,
                    "Error: " + e.getMessage(),
                    "error"
            );
            Functions.printSQLError(e);
        }
        finally {
            try{
                db.connection.close();
            }
            catch (SQLException e){
                Functions.printSQLError(e);
            }
        }

        return message;
    }

    public Message update(){
        Message message = new Message();
        String sql = "update Customer SET name = ?, address = ? where id = ?";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.address);
            pstmt.setInt(3, this.id);
            pstmt.executeUpdate();

            message = new Message(
                    true,
                    "Customer " + this.id +  " details updated successfully",
                    "success"
            );
        }
        catch (SQLException e){
            message = new Message(
                    false,
                    "Error: " + e.getMessage(),
                    "error"
            );
            Functions.printSQLError(e);
        }
        finally {
            try{
                db.connection.close();
            }
            catch (SQLException e){
                Functions.printSQLError(e);
            }
        }

        return message;
    }
}
