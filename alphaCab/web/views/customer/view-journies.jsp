<%@ page import="java.util.ArrayList" %>
<%@ page import="com.org.Models.Journey" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
<head>
	<title>Unpaid Journies</title>
        
        <link rel="stylesheet" href="/alphaCab/static/css/material.min.css">
        <script src="/alphaCab/static/js/material.min.js"></script>
        <link rel="stylesheet" href="/alphaCab/static/css/customCss.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
            .text-left{
                text-align: left !important;
            }
            .text-center{
                text-align: center !important;
            }
        </style>
</head>
<body>
    
    
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
        <header class="mdl-layout__header">
            <div class="mdl-layout__header-row bg-navy-blue" style="padding-left: 40px;">
            <!-- Title -->
            <span>
              <i class="material-icons font-yellow">
                  local_taxi &nbsp;
              </i>
            </span>
            <span class="mdl-layout-title"><a href="/alphaCab/home" style="text-decoration: none; color: #fff; font-weight: 400;">&#945;-Cab</a></span>
            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
            <!-- Navigation. We hide it in small screens. -->
            <nav class="mdl-navigation mdl-layout--large-screen-only">
              <a class="mdl-navigation__link" href="/alphaCab/journies">Journies</a>
              <a class="mdl-navigation__link" href="/alphaCab/order/pending">Order status</a>
              <a class="mdl-navigation__link" href="/alphaCab/logout">Logout</a>
            </nav>
          </div>
        </header>

        <main class="mdl-layout__content">
            <div class="page-content">

                <div class="mdl-grid center-items" style="text-align: left;">
                <div class="mdl-cell mdl-cell--8-col">
                    
                    <h4>Journies</h4>
                    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
                        <thead>
                            <tr>
                                <th class="text-center">ID</th>
                                <th class="text-left">Destination</th>
                                <th class="text-left">Date</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <tr>
                                <% 
                                        ArrayList<Journey> journies = (ArrayList<Journey>) request.getAttribute("journies");
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                %>
                                <% if(!journies.isEmpty()){ %>
                                        <% for(Journey journey : journies){ %>
                              <td class="text-center"><% out.print(journey.id); %></td>
                              <td class="text-left"><% out.print(journey.destination); %></td>
                              <td class="text-left"><% out.print(sdf.format(journey.date)); %> </td>
                              <td><a href=<% out.print("/alphaCab/journey/view/" + journey.id); %>>View Details</a></td>
                            </tr>
                            <% } %>
                        <% } else{ %>
                        <p><b>No Payments pending</b></p>
                        <% } %>

                        </tbody>
                    </table>
                    
                    
                    <div class="row">

                        
                        
                        
                        
                       
                        


                    </div>      
                </div>

            </div>

        </div>
        </main>
    </div>
    
 
</body>
</html>
