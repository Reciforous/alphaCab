package com.org.Helpers;

import javax.servlet.http.Part;
import java.sql.SQLException;

public class Functions {

    public static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }

        return "dummy.txt";
    }

    public static void printSQLError(SQLException e){
        System.out.println(
                Crayons.red("status: ") + e.getSQLState() + "\n" +
                Crayons.red("Message: ") + e.getMessage() + "\n" +
                Crayons.red("Code: ") + e.getErrorCode()
        );
    }
}
