/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.Models;
import com.org.Helpers.Functions;
import java.util.ArrayList;
import com.org.Models.Driver;
import com.org.Models.Db;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author rasen
 */
public class Report {
    public ArrayList<Driver> drivers = Driver.getDrivers();
    public ArrayList<Float> amounts = new ArrayList<>();
    
    public Report(){
        
    }
    
    public void getAmounts(String start_date, String end_date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        java.sql.Timestamp db_start_date = null;
        java.sql.Timestamp db_end_date = null;
        try{            
            db_start_date = new java.sql.Timestamp(sdf.parse(start_date).getTime());
            db_end_date = new java.sql.Timestamp(sdf.parse(end_date).getTime());
        }
        catch(ParseException e){
            
        }
        String sql = "SELECT t.amount FROM Transactions as t INNER JOIN Journey AS j ON j.jid = t.journey_id WHERE j.registration = ? and t.transaction_date >= ? and t.transactiion_date <= ?";
        Db db = new Db();
        db.getConnection();
        
        // Populate report object
        for(Driver driver : this.drivers){
            Float driver_earnings = 0.00f;
            try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
                pstmt.setString(1, driver.registration);
                pstmt.setTimestamp(2, db_start_date);
                pstmt.setTimestamp(3, db_end_date);
                
                ResultSet rs = pstmt.executeQuery();
                
                while(rs.next()){
                    driver_earnings += rs.getFloat("amount");
                }
                this.amounts.add(driver_earnings);
            }
            catch(SQLException e){
                Functions.printSQLError(e);
                break;
            }
        }
        
        Functions.closeDbConnection(db.connection);
    } 
}
