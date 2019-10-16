package com.org.Helpers;

import javax.servlet.http.Part;
import java.sql.Connection;
import java.sql.SQLException;

public class Functions {

    public static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }

        return null;
    }

    public static void closeDbConnection(Connection conn){
        try{
            conn.close();
        }
        catch (SQLException e){
            Functions.printSQLError(e);
        }
    }

    public static void printSQLError(SQLException e){
        System.out.println(
                Crayons.red("status: ") + e.getSQLState() + "\n" +
                Crayons.red("Message: ") + e.getMessage() + "\n" +
                Crayons.red("Code: ") + e.getErrorCode()
        );
    }
}
