package com.org.Models;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/*
    TODO: Move this to git

    WHATS NEW:
        - .add() now updates object.id with db id
        - .getTransactionForDriver 7/12/2019
 */
public class Transaction {
    public Integer id = null;
    public Integer journey_id = null;
    public Date transaction_date = null;
    public Float amount = null;

    public Transaction(Integer id, Integer journey_id, Date transaction_date, Float amount){
        this.id = id;
        this.journey_id = journey_id;
        this.transaction_date = transaction_date;
        this.amount = amount;
    }

    public Transaction(Integer id){
        this.id = id;
    }

    public Transaction(Integer journey_id, Float amount){
        this.journey_id = journey_id;
        this.transaction_date = new Date();
        this.amount = amount;
    }

    public Transaction(){

    }

    public static Message createTable(){
        Message message = new Message();
        String sql = "CREATE TABLE Transactions (\n" +
                "   id int NOT NULL AUTO_INCREMENT,\n" +
                "   journey_id int NOT NULL,\n" +
                "   transaction_date TIMESTAMP NOT NULL,\n" +
                "   amount FLOAT NOT NULL,\n" +
                "   FOREIGN KEY (journey_id) REFERENCES Journey (jid),\n" +
                "   PRIMARY KEY (id)\n" +
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

    public static ArrayList<Transaction> getTransactionsForDriver(Driver driver){
        ArrayList<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT t.id, t.journey_id, t.amount, t.transaction_date FROM Transactions as t INNER JOIN Journey AS j ON j.jid = t.journey_id WHERE j.registration = ? and t.transaction_date >= ? and t.transaction_date <= ?";

        Date begining, end;

        // Get start date of month
        {
            Calendar calendar = Functions.getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            Functions.setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        // Get end date of month
        {
            Calendar calendar = Functions.getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Functions.setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, driver.registration);
            pstmt.setTimestamp(2, new java.sql.Timestamp(begining.getTime()));
            pstmt.setTimestamp(3, new java.sql.Timestamp(end.getTime()));

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("journey_id"),
                        new Date(rs.getTimestamp("transaction_date").getTime()),
                        rs.getFloat("amount")
                );

                transactions.add(transaction);
            }
        }
        catch(SQLException e){
            Functions.printSQLError(e);
        }
        finally{
            Functions.closeDbConnection(db.connection);
        }

        return transactions;
    }

    public static ArrayList<Transaction> getTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, journey_id, amount, transaction_date from Transactions";

        Db db = new Db();
        db.getConnection();

        try(Statement stmt = db.connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Integer id = rs.getInt("id");
                Integer journey_id = rs.getInt("journey_id");
                Float amount = rs.getFloat("amount");
                java.sql.Timestamp ts = rs.getTimestamp("transaction_date");
                Date transaction_date = new Date(ts.getTime());

                Transaction transaction = new Transaction(id, journey_id, transaction_date, amount);
                transactions.add(transaction);
            }
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
        finally {
            Functions.closeDbConnection(db.connection);
        }

        return transactions;
    }

    public void get(){
        if(this.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        String sql = "SELECT journey_id, transaction_date, amount FROM Transactions WHERE id = ?";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                this.journey_id = rs.getInt("journey_id");
                this.transaction_date = new Date(rs.getTimestamp("transaction_date").getTime());
                this.amount = rs.getFloat("amount");
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
        if(this.journey_id == null || this.amount == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        Message message = new Message();
        String sql = "INSERT INTO Transactions (journey_id, amount, transaction_date) Values(?, ?, ?)";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, this.journey_id);
            pstmt.setFloat(2, this.amount);

            java.sql.Timestamp ts = new java.sql.Timestamp(this.transaction_date.getTime());
            pstmt.setTimestamp(3, ts);
            int result = pstmt.executeUpdate();

            if(result == 1){
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    this.id = rs.getInt(1);
                }
            }
            message = new Message(
                    true,
                    "Transaction successfully added",
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
