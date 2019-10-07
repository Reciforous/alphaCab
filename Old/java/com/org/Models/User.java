package com.org.Models;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;
import com.org.Models.Db;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class User {
    public Integer id;
    public String email;
    public String password;
    public String type;

    public User(){
        this.id = null;
        this.email = null;
        this.password = null;
        this.type = null;
    }

    public User(Integer id, String email, String password, String type){
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String email){
        this.email = email;
    }

    public Message createTable(){
        Message message = new Message();
        String sql = "create table users(" +
            "id int NOT NULL AUTO_INCREMENT," +
            "email varchar(300) NOT NULL," +
            "password varchar(300) NOT NULL," +
            "type text NOT NULL," +
            "PRIMARY KEY (id))";

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

    public void get(){
        if(this.id == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        String sql = "select id, email, type from users where id = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id);

            ResultSet rs = pstmt.executeQuery();

            this.id = rs.getInt("id");
            this.email = rs.getString("email");
            this.type = rs.getString("type");
        }
        catch (SQLException e){
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
    }

    public void get_by_email(){
        if(this.email == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        String sql = "Select id, email, password, type from users where email = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.email);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                this.id = rs.getInt("id");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
                this.type = rs.getString("type");
            }
            else{
                System.out.println("WHY ARE YOU EMPTY FUCK BITCH?!");
            }
        }
        catch (SQLException e){
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
    }

    public Boolean authenticated(String checkPassword){
        if(this.password == null){
            return false;
        }

        if(checkPassword.equals(this.password)){
            return true;
        }

        return false;
    }

    public Message add(){
        Message message = new Message();
        String sql = "insert into users(email, password, type) values(?, ?, ?)";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.email);
            pstmt.setString(2, this.password);
            pstmt.setString(3, this.type);
            pstmt.executeUpdate();

            message = new Message(
                true,
                "User added successfully",
                "success"
            );
        }
        catch (SQLException e){
            message = new Message(
                false,
                "Error: " + e.getMessage(),
                "success"
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

    public Cookie login(){
        Cookie cookie = null;

        User check_user = new User(this.email);
        check_user.get_by_email();

        if(check_user.email == null){
            check_user = null;
        }
        if(check_user == null){
            return cookie;
        }

        Boolean authenticated = check_user.authenticated(this.password);

        if(authenticated){
            cookie = new Cookie("Authentication", this.email);
            this.type = check_user.type;
        }

        return cookie;
    }

    public Cookie logout(){
        Cookie cookie = new Cookie("Authentication", "");
        cookie.setMaxAge(0);

        return cookie;
    }
}
