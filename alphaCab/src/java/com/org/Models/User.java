package com.org.Models;

import com.org.Helpers.Functions;
import com.org.Helpers.Message;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
    TODO: Move to git

    WHATS NEW:
        - redid User.login(), returns an ArrayList<Cookie> type now
        - .add() now updates object.id with db id
        - redid .logout()
 */
public class User {
    public Integer id = null;
    public String email = null;
    public String password = null;
    public String type = null;
    public Integer customer_id = null;
    public String driver_id = null;

    public User(){
        this.id = null;
        this.email = null;
        this.password = null;
        this.type = null;
        this.customer_id = null;
        this.driver_id = null;
    }

    public User(Integer id, String email, String password, String type){
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(String email, String password, String type){
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(String email, String password, String type, Integer customer_id){
        this.email = email;
        this.password = password;
        this.type = type;
        this.customer_id = customer_id;
    }

    public User(String email, String password, String type, String driver_id){
        this.email = email;
        this.password = password;
        this.type = type;
        this.driver_id = driver_id;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String email){
        this.email = email;
    }

    public static Message createTable(){
        Message message = new Message();
        String sql = "CREATE TABLE Users (\n" +
                "   id int NOT NULL AUTO_INCREMENT,\n" +
                "   email varchar(300) NOT NULL UNIQUE,\n" +
                "   password varchar(300) NOT NULL,\n" +
                "   type text NOT NULL,\n" +
                "   cid int,\n" +
                "   did varchar(10),\n" +
                "   FOREIGN KEY (cid) REFERENCES Customer (id),\n" +
                "   FOREIGN KEY (did) REFERENCES Drivers (Registration),\n" +
                "   PRIMARY KEY (id)\n" +
                ");\n";

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

        String sql = "select id, email, type, cid, did from users where id = ?";
        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setInt(1, this.id);

            ResultSet rs = pstmt.executeQuery();

            this.id = rs.getInt("id");
            this.email = rs.getString("email");
            this.type = rs.getString("type");
            this.customer_id = rs.getInt("cid");
            this.driver_id = rs.getString("did");
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

    public void getByEmail(){
        if(this.email == null){
            throw new IllegalArgumentException("Error: Missing required fields");
        }

        String sql = "Select id, email, password, type, cid, did from Users where email = ?";
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
                this.customer_id = rs.getInt("cid");
                this.driver_id = rs.getString("did");
            }
            else{
                System.out.println("No such email address!");
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

        // Handle this here since jdbc doesnt really handle null types well
        String sql = "INSERT INTO Users(email, password, type) VALUES(?, ?, ?)";
        if(this.customer_id != null){
            sql = "insert into Users(email, password, type, cid) values(?, ?, ?, ?)";
        }
        else if(this.driver_id != null){
            sql = "insert into Users(email, password, type, did) values(?, ?, ?, ?)";
        }

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, this.email);
            pstmt.setString(2, this.password);
            pstmt.setString(3, this.type);

            if(this.customer_id != null){
                pstmt.setInt(4, this.customer_id);
            }
            else if(this.driver_id != null){
                pstmt.setString(4, this.driver_id);
            }

            int result = pstmt.executeUpdate();

            if(result == 1){
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    this.id = rs.getInt(1);
                }
            }

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

    public Message update(){
        if(this.id == null){
            throw new IllegalArgumentException("Error: Missing required fields!");
        }

        Message message = new Message();
        String sql = "UPDATE Users SET email = ?, password = ?, type = ? where id = ?";

        Db db = new Db();
        db.getConnection();

        try(PreparedStatement pstmt = db.connection.prepareStatement(sql)){
            pstmt.setString(1, this.email);
            pstmt.setString(2, this.password);
            pstmt.setString(3, this.type);
            pstmt.executeUpdate();

            message = new Message(
                    true,
                    "User updated successfully",
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

    public ArrayList<Cookie> login(){
        ArrayList<Cookie> cookies = new ArrayList<>();

        Cookie auth_cookie = new Cookie("authentication", null);
        Cookie id_cookie = new Cookie("id", null);
        Cookie type_cookie = new Cookie("type", null);
        Boolean authenticated = false;

        User check_user = new User(this.email);
        check_user.getByEmail();

        if(check_user.password != null){
            authenticated = check_user.authenticated(this.password);
        }

        if(authenticated){
            auth_cookie = new Cookie("authentication", this.email);
            this.type = check_user.type;
            type_cookie = new Cookie("type", this.type);

            switch (this.type) {
                case "customer":
                    this.customer_id = check_user.customer_id;
                    id_cookie = new Cookie("id", this.customer_id.toString());
                    break;
                case "driver":
                    this.driver_id = check_user.driver_id;
                    id_cookie = new Cookie("id", this.driver_id);
                    break;
                default:
                    id_cookie = new Cookie("id", null);
                    break;
            }
        }

        cookies.add(auth_cookie);
        cookies.add(type_cookie);
        cookies.add(id_cookie);
        return cookies;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response){
        for(Cookie cookie : request.getCookies()){
            cookie.setValue("");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
    }
}
