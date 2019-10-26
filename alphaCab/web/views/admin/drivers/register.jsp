<%-- 
    Document   : register
    Created on : Oct 26, 2019, 5:01:36 PM
    Author     : shaaik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Drivers Register</title>
    </head>
    <body>
        <h1>Register Driver</h1>
        <form action="/alphaCab/drivers/register" method="POST">
            <div>
               <div>
                   <label>Name</label>
                    <input type="text" name="name" required>
               </div>
                <br>
                <div>
                    <label>Registration</label>
                    <input type="text" name="registration" required>
                </div>
            </div>
            <div>
                <button type="submit">
                    Submit
                </button>
            </div>
        </form>
    </body>
</html>
