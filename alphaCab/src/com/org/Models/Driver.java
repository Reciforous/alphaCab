package com.org.Models;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Function;

public class Driver {
    public String registration = null;
    public String name = null;

    public Driver(){

    }

    public Driver(String registration){
        this.registration = registration;
    }

    public Driver(String registration, String name){
        this.registration = registration;
        this.name = name;
    }

    public static Message createTable(){
        Message message = new Message();
        String sql = "CREATE TABLE Drivers (\n" +
                "  Registration varchar(10) NOT NULL,\n" +
                "  Name varchar(20),\n" +
                "  PRIMARY KEY (Registration)\n" +
                ");";

        Db db = new Db();
        db.getConnection();

        try(Statement stmt = db.connection.createStatement()){
            stmt.executeUpdate(sql);
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
            Functions.closeDbConnection(db.connection);
        }

        return message;
    }

    public void get(){
        if(this.registration == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }
        String sql = "SELECT Name FROM Drivers WHERE Registration = ?";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.registration);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                this.name = rs.getString("Name");
            }
            else{
                System.out.println("No result!");
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }
    }

    public static ArrayList<Driver> getDrivers(){
        ArrayList<Driver> drivers = new ArrayList<>();
        String sql = "SELECT Registration, Name FROM Drivers";

        Db db = new Db();
        db.getConnection();

        try(Statement stmt = db.connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String registration = rs.getString("Registration");
                String name = rs.getString("Name");

                drivers.add(new Driver(registration, name));
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }

        return drivers;
    }

    public Message add(){
        if(this.registration == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        Message message = new Message();
        String sql = "INSERT INTO Drivers(Registration, Name) VALUES (?, ?)";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.registration);
            pstmt.setString(2, this.name);
            message = new Message(
                    true,
                    "Driver added successfully",
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
            Functions.closeDbConnection(db.connection);
        }

        return message;
    }

    public Message update(){
        if(this.registration == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        Message message = new Message();
        String sql = "UPDATE Drivers SET Name = ? WHERE Registration = ?";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.registration);
            pstmt.executeUpdate();
            message = new Message(
                    true,
                    "Driver updated successfully",
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
            Functions.closeDbConnection(db.connection);
        }

        return message;
    }
}
