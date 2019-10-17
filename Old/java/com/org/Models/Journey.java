package com.org.Models;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;

public class Journey {
    Integer id = null;
    Integer customer_id = null;
    String destination = null;
    Integer distance = null;
    String registration = null;
    Date date = null;

    public Journey(){

    }

    public Journey(Integer id, Integer customer_id, String destination, Integer distance, String registration, Date date){
        this.id = id;
        this.customer_id = customer_id;
        this.destination = destination;
        this.distance = distance;
        this.registration = registration;
        this.date = date;
    }

    public Journey(Integer customer_id, String destination, Integer distance, String registration){
        this.customer_id = customer_id;
        this.destination = destination;
        this.distance = distance;
        this.registration = registration;
        this.date = new Date();
    }

    public static ArrayList<Journey> getByRegistration(Driver driver){
        if(driver.registration == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        ArrayList<Journey> journies = new ArrayList<>();
        String sql = "SELECT jid, id, Destination, Distance, Date, Time FROM Journey WHERE id = ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, driver.registration);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Integer id = rs.getInt("jid");
                Integer customer_id = rs.getInt("id");
                String destination = rs.getString("Destination");
                Integer distance = rs.getInt("Distance");

                // MESSY AND BAD IMPLEMENTATION
                java.sql.Date dbdate = rs.getDate("Date");
                java.sql.Time dbtime = rs.getTime("Time");
                Date date = null;
                try{
                    date = sdf.parse(dbdate.toString() + " " + dbtime.toString());
                }
                catch (ParseException e){
                    System.out.println("Error: Could not parse date");
                }

                Journey journey = new Journey(id, customer_id, destination, distance, driver.registration, date);
                journies.add(journey);
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }

        return journies;
    }

    public static ArrayList<Journey> getByCustomerId(Customer customer){
        if(customer.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        ArrayList<Journey> journies = new ArrayList<>();
        String sql = "SELECT jid, Destination, Distance, Registration, Date, Time FROM Journey WHERE id = ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, customer.id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Integer id = rs.getInt("jid");
                String destination = rs.getString("Destination");
                Integer distance = rs.getInt("Distance");
                String registration = rs.getString("Registration");

                // MESSY AND BAD IMPLEMENTATION
                java.sql.Date dbdate = rs.getDate("Date");
                java.sql.Time dbtime = rs.getTime("Time");
                Date date = null;
                try{
                    date = sdf.parse(dbdate.toString() + " " + dbtime.toString());
                }
                catch (ParseException e){
                    System.out.println("Error: Could not parse date");
                }

                Journey journey = new Journey(id, customer.id, destination, distance, registration, date);
                journies.add(journey);
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }

        return journies;
    }

    public Message add(){
        if(this.customer_id == null || this.destination == null || this.registration == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        // Also why do you guys hate auto incrementing DB's? Even derby has autoincrementation...
        Message message = new Message();
        String sql = "INSERT INTO JOURNEY(jid, id, Destination, Distance, Registration, Date, Time) VALUES(" +
                "(SELECT MAX(jid) FROM Journey as x) + 1, ?, ?, ?, ?, ?, ?)";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.customer_id);
            pstmt.setString(2, this.destination);
            pstmt.setInt(3, this.distance);
            pstmt.setString(4, this.registration);

            // BAD IMPLEMENTATION 101 SANE PEOPLE DONT DO THIS
            java.sql.Date dbdate = new java.sql.Date(this.date.getYear(), this.date.getMonth(), this.date.getDay());
            java.sql.Time dbtime = new java.sql.Time(this.date.getTime());

            pstmt.setDate(5, dbdate);
            pstmt.setTime(6, dbtime);

            pstmt.executeUpdate();

            message = new Message(
                    true,
                    "Journey added successfully",
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
