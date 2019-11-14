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
import java.util.function.Function;

/*
    TODO: Move this to git

    WHATS NEW:
        getByStatus(String status), type - ArrayList<Demand>
        getDemandsByCustomer() --> getByCustomer()
        getLastAccepted(), type - void
 */

public class Demand {
    public Integer id = null;
    public String name = null;
    public String address = null;
    public String destination = null;
    public Date date;
    public String status = "Outstanding";

    public Demand(){
        this.id = null;
        this.name = null;
        this.address = null;
        this.destination = null;

    }
    public Demand(Integer id){
        this.id = id;
    }

    public Demand(String name, String address, String destination){
        this.name = name;
        this.address = address;
        this.destination = destination;
    }

    public Demand(Integer id, String name, String address, String destination, Date date, String status){
        this.id = id;
        this.name = name;
        this.address = address;
        this.destination = destination;
        this.date = date;
        this.status = status;
    }

    public Demand(String name){
        this.name = name;
        this.status = status;
    }

    public static Message createTable(){
        Message message = new Message();
        String sql = "CREATE TABLE Demands (\n" +
                "  id int NOT NULL,\n" +
                "  Name varchar(20),\n" +
                "  Address varchar(60),\n" +
                "  Destination varchar(60),\n" +
                "  Date date DEFAULT NULL,\n" +
                "  Time time DEFAULT NULL,\n" +
                "  Status varchar(15) NOT NULL,\n" +
                "  PRIMARY KEY (id)\n" +
                ");";

        Db db = new Db();
        db.getConnection();

        try(Statement stmt = db.connection.createStatement()){
            stmt.executeUpdate(sql);
            message = new Message(
                    true,
                    "Table Created Successfully",
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

    public static ArrayList<Demand> getDemands(){
        ArrayList<Demand> demands = new ArrayList<>();

        String sql = "Select id, Name, Address, Destination, Date, Time, Status from Demands";

        Db db = new Db();
        db.getConnection();

        try(Statement stmt = db.connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String destination = rs.getString("Destination");

                // Who does this anymore ?? For sanity purposes in the future DO NOT keep these two separate UWE....
                // Timestamp is also a thing look it up UWE
                java.sql.Date dbdate = rs.getDate("Date");
                java.sql.Time dbtime = rs.getTime("Time");
                String datestring = dbdate.toString() + " " + dbtime.toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(datestring);
                }
                catch (ParseException  e){
                    System.out.println("Error: Cannot parse date!");
                }

                String status = rs.getString("Status");
                Demand demand = new Demand(id, name, address, destination, date, status);

                demands.add(demand);
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }

        return demands;
    }

    public static ArrayList<Demand> getByCustomer(Customer customer){
        if(customer.name == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        ArrayList<Demand> demands = new ArrayList<>();
        String sql = "SELECT id, Address, Destination, Date, Time, Status FROM Demands WHERE Name = ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, customer.name);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Integer id = rs.getInt("id");
                String address = rs.getString("Address");
                String destination = rs.getString("Destination");

                // MORE BAD IMPLEMENTATIONS
                java.sql.Date dbdate = rs.getDate("Date");
                java.sql.Time dbtime = rs.getTime("Time");

                Date date = null;

                try{
                    date = sdf.parse(dbdate.toString() + " " + dbtime.toString());
                }
                catch (ParseException e){
                    System.out.println("Error: Cannot parse date");
                }

                String status = rs.getString("Status");

                Demand demand = new Demand(id, customer.name, address, destination, date, status);
                demands.add(demand);
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }

        return demands;
    }

    public static Demand getAccepted(Customer customer){
        if(customer.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        Demand demand = null;
        String sql = "SELECT demand_id FROM DemandQueue WHERE customer_id = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, customer.id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                Integer id = rs.getInt("demand_id");
                demand = new Demand(id);
                demand.getById();
            }
        }
        catch(SQLException e){
            Functions.printSQLError(e);
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }

        return demand;
    }

    public static Demand getPending(Driver driver){
        if(driver.registration == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        Demand demand = null;
        String sql = "SELECT demand_id FROM DemandQueue WHERE driver_id = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, driver.registration);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                Integer id = rs.getInt("demand_id");
                demand = new Demand(id);
                demand.getById();
            }
        }
        catch(SQLException e){
            Functions.printSQLError(e);
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }
        return demand;
    }

    public static Boolean checkIfOrdered(Customer customer){
        if(customer.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        Boolean ordered = true;

        String sql = "SELECT id FROM DemandQueue WHERE customer_id = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, customer.id);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                ordered = false;
            }
        }
        catch(SQLException e){
            Functions.printSQLError(e);
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }

        return ordered;
    }

    public static ArrayList<Demand> getByStatus(String status){
        ArrayList<Demand> demands = new ArrayList<>();
        String sql = "SELECT id, Name, Address, Destination, Date, Time, Status FROM Demands WHERE status=?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Date date = null;
                java.sql.Date db_date = rs.getDate("Date");
                java.sql.Time db_time = rs.getTime("Time");
                String date_string = db_date.toString() + " " + db_time.toString();
                try{
                    date = sdf.parse(date_string);
                }
                catch(ParseException e){
                    System.out.println("Error: Could not parse date!");
                }

                Demand demand = new Demand(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Destination"),
                        date,
                        rs.getString("status")
                );

                demands.add(demand);
            }
        }
        catch(SQLException e){
            Functions.printSQLError(e);
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }

        return demands;
    }

    public void getById(){
        if(this.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        String sql = "SELECT Name, Address, Destination, Date, Time, Status from Demands WHERE id = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                this.name = rs.getString("Name");
                this.address = rs.getString("Address");
                this.destination = rs.getString("Destination");

                // AGAIN THIS IS EXTREMELY UN-NECESSARY!
                java.sql.Date dbdate = rs.getDate("Date");
                java.sql.Time dbtime = rs.getTime("Time");
                String datestring = dbdate.toString() + " " + dbtime.toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;

                try{
                    date = sdf.parse(datestring);
                }
                catch (ParseException e){
                    System.out.println("Error: Cannot parse date!");
                }
                this.date = date;
                this.status = rs.getString("Status");
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
        if(this.name == null || this.address == null || this.destination == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        Message message = new Message();
        String sql = "INSERT INTO Demands(Name, Address, Destination, Date, Time, Status)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Db db = new Db();
        db.getConnection();

        // THIS IS REDUNDANT AND JUST BAD IMPLEMENTATION
        this.date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestring = sdf.format(this.date);
        System.out.println(this.date);
        java.sql.Date dbdate = new java.sql.Date(this.date.getYear(), this.date.getMonth(), this.date.getDate());
        java.sql.Time dbtime = new java.sql.Time(this.date.getTime());

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.address);
            pstmt.setString(3, this.destination);
            pstmt.setDate(4, dbdate);
            pstmt.setTime(5, dbtime);
            pstmt.setString(6, this.status);
            int result = pstmt.executeUpdate();

            // This bit updates the object creating a more ORM like approach so now we have ID whenever we add
            if(result == 1){
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    this.id = rs.getInt(1);
                }
            }

            message = new Message(
                    true,
                    "Demand added successfully",
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

    public static Message addToDemandQueue(Demand demand, Customer customer){
        if(demand.id == null || customer.id == null){
            return new Message(
                    false,
                    "Error: Missing required fields",
                    "error"
            );
        }

        String sql = "INSERT INTO DemandQueue (demand_id, customer_id) VALUES (?, ?)";
        Db db = new Db();
        db.getConnection();
        Message message;

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, demand.id);
            pstmt.setInt(2, customer.id);
            pstmt.executeUpdate();
            message = new Message(
                    true,
                    "Demand added to queue",
                    "success"
            );
        }
        catch(SQLException e){
            Functions.printSQLError(e);
            message = new Message(
                    false,
                    "Error: " + e.getMessage(),
                    "error"
            );
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }

        return message;
    }

    public Message update(){
        if(this.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        Message message = new Message();
        String sql = "UPDATE Demands SET Name = ?, Address = ?, Destination = ?, Status = ? WHERE id = ?";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.address);
            pstmt.setString(3, this.destination);
            pstmt.setString(4, this.status);
            pstmt.setInt(5, this.id);
            pstmt.executeUpdate();

            message = new Message(
                    true,
                    "Demand updated successfully",
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

    public Message cancel(){
        this.status = "Canceled";
        String sql = "DELETE FROM DemandQueue WHERE demand_id = ?";
        Message message;
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id );
            pstmt.executeUpdate();
            message = this.update();
        }
        catch(SQLException e){
            Functions.printSQLError(e);
            message = new Message(
                    false,
                    "Error: " + e.getMessage(),
                    "error"
            );
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }
        return message;
    }

    public Message accept(Driver driver){
        this.status = "Accepted";
        String sql = "UPDATE DemandQueue SET driver_id=? WHERE demand_id=?";
        Message message;
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, driver.registration);
            pstmt.setInt(2, this.id);
            pstmt.executeUpdate();
            message = this.update();
        }
        catch(SQLException e){
            Functions.printSQLError(e);
            if(e.getErrorCode() == 1062){
                message = new Message(
                        false,
                        "You have a pending demand in queue! Cannot accept more than 1",
                        "error"
                );
            }
            else{
                message = new Message(
                        false,
                        "Error: failed to update demand queue",
                        "error"
                );
            }
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }

        return message;
    }

    // Pass an empty journey object here to for so that journey is generated and returned to journey instance
    public Message completed(Integer distance, Journey journey) {
        this.status = "Completed";
        Message message;

        String sql = "SELECT customer_id, driver_id FROM DemandQueue WHERE demand_id = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Integer customer_id = rs.getInt("customer_id");
                String driver_id = rs.getString("driver_id");


                // Remove from demand queue so that user is free to create a request and driver is free to accept another demand
                sql = "DELETE FROM DemandQueue WHERE demand_id = ?";
                PreparedStatement pstmt2 = db.connection.prepareStatement(sql);
                pstmt2.setInt(1, this.id);
                pstmt2.executeUpdate();

                message = this.update();

                if(message.status){
                    System.out.println("Generating journey");
                    journey = new Journey(customer_id, this.destination, distance, driver_id);
                    message = journey.add();

                    if(message. status){
                        message = new Message(
                                true,
                                "Journey generated successfully",
                                "success"
                        );
                    }else{
                        message = new Message(
                                false,
                                message.content,
                                "error"
                        );
                    }
                }
            }
            else{
                message = new Message(
                        false,
                        "Error: Could not find demand or missing journey details",
                        "error"
                );
            }
        }
        catch(SQLException e){
            Functions.printSQLError(e);
            message = new Message(
                    false,
                    "Error: " + e.getMessage(),
                    "error"
            );
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }

        return message;
    }
}
