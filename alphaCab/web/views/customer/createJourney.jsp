<%-- 
    Document   : createJourney
    Created on : Oct 26, 2019, 11:03:45 PM
    Author     : nasrusaeid
--%>

<%@page import="com.org.Models.Journey"%>
<%@page import="com.org.Models.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Journey</title>
    </head>
    <body>
        <h1>Register Driver</h1>
        <form action="/alphaCab/customer/create-journey" method="POST">
            <div>
               <div>
                   <label>Destination</label>
                    <input type="text" name="destination" required>
               </div>
                <br>
                <div>
                    <label>Distance</label>
                    <input type="number" name="distance" required>
                </div>
                <br>
                <div>
                    <label>Registration Number</label>
                    <input type="number" name="registration" required>
                </div>
            </div>
            <div>
                <button type="submit">
                    Submit
                </button>
            </div>
    </body>
</html>
