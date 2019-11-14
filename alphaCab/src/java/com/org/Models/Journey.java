package com.org.Models;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
    TODO: Move this to git

    WHATS NEW:
        - .add() now updates object.id with db id
        - Fixed .add() table name JOURNEY instead of Journey
 */

/**
 * @author  Zain Riyaz <mohamedzainriyaz@gmail.com
 * @version 1.0
 * Journey model uses an ORM <b>Like</b> approach to do CRUD operations on the database
 */

public class Journey {
    /***
     * Object id assigned from database Type: public
     */
    public Integer id = null;

    /**
     * Customer id used to get Customer obj
     */
    public Integer customer_id = null;

    /**
     * Journey destination
     */
    public String destination = null;

    /**
     * Distance traveled on journey
     */
    public Integer distance = null;

    /**
     * Registration number of driver used to get Driver obj
     */
    public String registration = null;

    /**
     * Date journey was requested
     */
    public Date date = null;

    // Empty constructor
    public Journey(){

    }

    // Constructor for getAll and update
    public Journey(Integer id, Integer customer_id, String destination, Integer distance, String registration, Date date){
        this.id = id;
        this.customer_id = customer_id;
        this.destination = destination;
        this.distance = distance;
        this.registration = registration;
        this.date = date;
    }

    // Constructor for insert
    public Journey(Integer customer_id, String destination, Integer distance, String registration){
        this.customer_id = customer_id;
        this.destination = destination;
        this.distance = distance;
        this.registration = registration;
        this.date = new Date();
    }

    // Constructor for getting single record
    public Journey(Integer id){
        this.id = id;
    }

    /**
     * Gets all journies NOT IN Transaction table ie. Unpaid for
     * @param   customer    Type: Customer
     * @return  ArrayList   Type: Customer
     * @throws  IllegalArgumentException    Thrown when customer.id is null
     */
    public static ArrayList<Journey> getUnpaidJournies(Customer customer){
        if(customer.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        ArrayList<Journey> journies = new ArrayList<>();
        // Amazing SQL queries we have to do because whoever designed the database is retarded
        String sql = "SELECT Journey.jid," +
                " Journey.Destination," +
                " Journey.Distance," +
                " Journey.Registration," +
                " Journey.Date," +
                " Journey.Time FROM " +
                "Journey LEFT OUTER JOIN Transactions on Journey.jid = Transactions.journey_id" +
                " WHERE Transactions.journey_id is NULL and Journey.id = ?";
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

    /**
     * Gets journeys made by a driver
     * Usage eg:
     * {@code
     * Driver driver = new Driver('AXK298');
     * driver.get();
     *
     * // Populate ArrayList with Journey obj's filtered by driver registation
     * ArrayList<Journey> journies = Journey.getByRegistration(driver);
     * }
     * @param   driver    Type: com.org.Models.Driver
     * @return  ArrayList   Type: Journey
     * @throws  IllegalArgumentException    thrown when driver.registration is null
     */
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

    /**
     * Get journies related to a customer using customer id
     * Usage eg:
     * {@code
     * Customer customer = new Customer(1);
     * customer.get();
     *
     * // Populate ArrayList with Journey obj's filtered by customer id
     * ArrayList<Journey> journies = Journey.getByCustomerId(customer);
     * }
     * @param   customer    Type: com.org.Models.Customer
     * @return  ArrayList   Type: Journey
     * @throws  IllegalArgumentException    Thrown when customer id is null
     */
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

    /***
     * Get single journey record using journey id
     * Usage eg:
     * {@code
     * Journey journey = new Journey(1);
     *
     * // gets record from journey table filtered by id
     * journey.get();
     * }
     */
    public void get(){
        if(this.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        String sql = "SELECT id, Destination, Distance, Registration, Date, Time FROM Journey WHERE jid = ?";

        Db db = new Db();
        db.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                this.customer_id = rs.getInt("id");
                this.destination = rs.getString("Destination");
                this.distance = rs.getInt("Distance");
                java.sql.Date dbdate = rs.getDate("Date");
                java.sql.Time dbtime = rs.getTime("Time");

                try{
                    this.date = sdf.parse(dbdate.toString() + " " + dbtime.toString());
                }
                catch (ParseException e){
                    System.out.println("Error: Could not parse date");
                }
            }
            else{
                System.out.println("No result");
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }
    }

    /**
     * Inserts new record to database
     * Usage eg:
     * {@code
     * Integer customerId = 1
     * String driverRegistration = "AK929"
     * String destination = "";
     * Integer distance = 23;
     *
     * Journey journey = new Journey(customerId, destination, distance, driverRegistration);
     * Message message = journey.add();
     * }
     * @return  Message Type: com.org.Helpers.Message
     */
    public Message add(){
        if(this.customer_id == null || this.destination == null || this.registration == null){
            System.out.println(this.customer_id);
            System.out.println(this.destination);
            System.out.println(this.registration);
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        // Also why do you guys hate auto incrementing DB's? Even derby has autoincrementation...
        Message message = new Message();
        String sql = "INSERT INTO Journey(id, Destination, Distance, Registration, Date, Time) VALUES(" +
                "?, ?, ?, ?, ?, ?)";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, this.customer_id);
            pstmt.setString(2, this.destination);
            pstmt.setInt(3, this.distance);
            pstmt.setString(4, this.registration);

            // BAD IMPLEMENTATION 101 SANE PEOPLE DONT DO THIS
            java.sql.Date dbdate = new java.sql.Date(this.date.getYear(), this.date.getMonth(), this.date.getDay());
            java.sql.Time dbtime = new java.sql.Time(this.date.getTime());

            pstmt.setDate(5, dbdate);
            pstmt.setTime(6, dbtime);

            int result = pstmt.executeUpdate();

            if(result == 1){
                ResultSet rs = pstmt.getGeneratedKeys();

                if(rs.next()){
                    this.id = rs.getInt(1);
                }
            }

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
