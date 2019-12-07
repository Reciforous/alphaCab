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
        <link href="/alphaCab/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">

        <script src="/alphaCab/static/js/bootstrap.min.js"></script>

        <title>Admin - Add Driver</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/alphaCab/admin">AlphaCab</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/alphaCab/admin/drivers">Drivers<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/customers">Customers<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/journeys">Journeys<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/demands">Demands<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/transactions">Transactions<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/alphaCab/admin/report">Reports<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
                <span class="navbar-text pb-0 pt-0" >
                    <a class="nav-link" href="/alphaCab/logout">Logout<span class="sr-only">(current)</span></a>
                </span>
            </div>
        </nav>
        <% ArrayList<Driver> drivers = (ArrayList<Driver>) request.getAttribute("drivers"); %>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-6"><h3>Drivers</h3></div>
                <div class="col-6 text-right">
                    <a href="/alphaCab/admin/drivers/register">
                        <button class="btn btn-primary">Add driver</button>
                    </a>
                </div>
            </div>
              
            <br>
           
            <table class="table table-striped">
            <thead>
                <tr>
                  <th scope="col">Name</th>
                  <th scope="col">Registration</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <% for(Driver driver : drivers){%>
                    <tr>
                        <td>
                            <% out.print(driver.name); %>
                        </td> 
                        <td>                
                            <% out.print(driver.registration); %>
                        </td>
                        <td>
                            <a href="/alphaCab/admin/drivers?driver=<% out.print(driver.registration); %>">View Details</a>
                        </td>
                    </tr>
                <%}%>
              </tbody>
            </table>
        </div>
    </body>
</html>
