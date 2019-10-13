package com.org.Models;

import com.org.Helpers.Message;
import com.org.Helpers.Functions;
import com.org.Models.Db;

import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
    public Integer id = null;
    public String name = null;
    public String Address = null;

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
}
