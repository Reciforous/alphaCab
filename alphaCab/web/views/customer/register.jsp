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
        <title>Customer Register</title>
    </head>
    <body>
        <h1>Customer Driver</h1>
        <form action="/register" method="POST">
            <div>
               <div>
                   <label>Name</label>
                    <input type="text" name="name" required>
               </div>
                <br>
                <div>
                    <label>Address</label>
                    <input type="text" name="address" required>
                </div>
                <div>
                    <label>Email</label>
                    <input type="text" name="email" required>
                </div>
                <div>
                    <label>Password</label>
                    <input type="text" name="password" required>
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
