<%-- 
    Document   : drivers
    Created on : Oct 26, 2019, 5:42:10 PM
    Author     : shaaik
--%>

<%@page import="com.org.Models.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Drivers!</h1>
        <% ArrayList<Driver> drivers = (ArrayList<Driver>) request.getAttribute("drivers"); %>
        
    <table style="border: 1px solid black;">
        <tr>
            <th style="border: 1px solid black;">Name</th>
            <th style="border: 1px solid black;">Registration</th>
        </tr>
        <% for(Driver driver : drivers){%>
            <tr style="border: 1px solid black;">
                <td style="border: 1px solid black;">
                    <% out.print(driver.name); %>
                </td> 
                <td style="border: 1px solid black;">                
                    <% out.print(driver.registration); %>
                </td>
                <td style="border: 1px solid black;">
                    <a href="/alphaCab/drivers">View Details</a>
                </td>
            </tr>
        <%}%>
    </table>

    </body>
</html>
