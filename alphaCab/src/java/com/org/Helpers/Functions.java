package com.org.Helpers;

import com.org.Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* TODO - Move to git

    WHATS NEW:
        redirect(HttpServletResponse response, String url), type: void
        authenticateRoute(HttpServlet request, String account_type), type: Boolean
 */
public class Functions {

    /***
     * Generic function for getting file name during a user upload
     * @param part javax.servlet.http.Part
     * @return name of file as String
     */
    public static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }

        return null;
    }

    /***
     * Generic function to close the database connection instance
     * @param conn java.sql.Connection object from the database
     */
    public static void closeDbConnection(Connection conn){
        try{
            conn.close();
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
    }

    /***
     * Print pretty formatted errors where SQLExceptions are raised
     * mainly for debugging purposes
     * @param e java.sql.SQLException
     */
    public static void printSQLError(SQLException e){
        System.out.println(
                Crayons.red("status: ") + e.getSQLState() + "\n" +
                Crayons.red("Message: ") + e.getMessage() + "\n" +
                Crayons.red("Code: ") + e.getErrorCode()
        );
    }

    /***
     * Simplified redirect function using HttpServletResponse
     * @param response javax.servlet.HttpServletResponse object from servlet
     * @param url redirect route string
     */
    public static void redirect(HttpServletResponse response, String url){
        try{
            response.sendRedirect(Configs.url_prefix + url);
        }
        catch (IOException e){
            System.out.println(Crayons.red("Error sending redirect response!", true));
            e.printStackTrace();
        }
    }

    /***
     * Basic route permission grant function
     * @param request javax.servlet.HttpServletRequest object from servlet
     * @param account_type allowed account type string
     * @return Boolean
     */
    public static Boolean authenticateRoute(HttpServletRequest request, String account_type){
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            // get authentication and type cookie from request
            Cookie auth_cookie = Functions.getCookie(cookies, "authentication");
            Cookie type_cookie = Functions.getCookie(cookies, "type");

            try{
                // assert both cookies are not null
                assert type_cookie != null;
                assert auth_cookie != null;

                String email = auth_cookie.getValue();

                // get user by email
                User user = new User(email);
                user.getByEmail();

                // check if user present and type_cookie value matches account_type
                return user.id != null && type_cookie.getValue().equals(account_type);
            }
            // if null auto return falls and print error stack
            catch(NullPointerException e){
                System.out.println("Error: Type cookie value empty!");
                e.printStackTrace();
                return false;
            }
        }
        else{
            // return false if no cookies are set
            return false;
        }
    }

    /***
     * Searches for a single cookie instance in a primitive cookie type array
     * @param cookies primitive array of type javax.servlet.http.Cookie
     * @param cookie_name search string
     * @return javax.servlet.http.Cookie || null
     */
    public static Cookie getCookie(Cookie[] cookies, String cookie_name){
        for(Cookie cookie : cookies){
            // Do a string match
            if(cookie.getName().equals(cookie_name)){
                // stop if fount and return cookie
                return cookie;
            }
        }
        // Return null if not present
        return null;
    }

    public static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
}
