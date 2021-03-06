package com.org.Models;

import com.org.Helpers.Message;
import com.org.Helpers.Functions;
import com.org.Models.Db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public class Customer {
    public Integer id = null;
    public String name = null;
    public String address = null;

    public static Message createTable(){
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

    public void get(Integer id){
        if(this.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        String sql = "SELECT Name, Address from Customer WHERE id = ?";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                this.name = rs.getString("Name");
                this.address = rs.getString("Address");
            }
            else{
                System.out.println("Error: No Result");
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }

    }

    public void getByName(){
        if(this.name == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        String sql = "Select id, Address from Customer where Name = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.name);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                this.id = rs.getInt("id");
                this.address = rs.getString("Address");
            }
            else{
                System.out.println("Error: No Such Customer");
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }

    }

    public Message add(){
        Message message = new Message();
        String sql = "insert into Customer (id, Name, Address) values ((SELECT MAX(id) FROM Customer as x) + 1 ,?, ?)";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
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
