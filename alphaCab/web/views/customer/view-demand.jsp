<%-- TODO: Add to git --%>
<%@ page import="com.org.Models.Demand" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.org.Helpers.*" %>

<!DOCTYPE html>
<html>
<head>
	<title>Pending Order</title>
        <link rel="stylesheet" href="/alphaCab/static/css/material.min.css">
        <script src="/alphaCab/static/js/material.min.js"></script>
        <link rel="stylesheet" href="/alphaCab/static/css/customCss.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
	<% 
	Demand demand = (Demand) request.getAttribute("demand"); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<div id="app">
            
            <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
                <header class="mdl-layout__header">
                    <div class="mdl-layout__header-row bg-navy-blue" style="padding-left: 40px;">
                    <!-- Title -->
                    <span>
                      <i class="material-icons font-yellow">
                          local_taxi &nbsp;
                      </i>
                    </span>
                    <span class="mdl-layout-title">&#945;-Cab</span>
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

                      <div class="mdl-grid center-items">
                          <div class="mdl-cell mdl-cell--8-col">
                              <div class="row">
                                  <div>
                                    <% if(demand != null){ %>
                                    <form action="<% out.print(Configs.url_prefix + "order/complete"); %>" method="POST">
                                            <div>
                                                    <input type="number" hidden readonly name="demandId" value="<% out.print(demand.id); %>">
                                                    <label>Ordered By: </label>
                                                    <input name="customerName" type="text" readonly value="<% out.print(demand.name); %>">
                                            </div>
                                            <div>
                                                    <label>Address: </label>
                                                    <input name="address" id="address" type="text" readonly value="<% out.print(demand.address); %>">
                                            </div>
                                            <div>
                                                    <label>Destination: </label>
                                                    <input name="destination" id="destination" type="text" readonly value="<% out.print(demand.destination); %>">
                                            </div>
                                            <div>
                                                    <p>Date ordered: <% out.print(sdf.format(demand.date)); %></p>
                                            </div>

                                            <%-- This parts needs to be done with javascript, use address and destination to make a post request to --%>
                                            <%-- maps api, fix value for distance and amount through javascript current set rate on backend for journey is 2.75--%>
                                            <div>
                                                    <label for="distance">Distance in meters:</label>
                                                    <input type="number" name="distance" readonly v-model="distance">
                                            </div>
                                            <div>
                                                    <label for="amount">Amount: </label>
                                                    <input readonly v-model="amount" type="number" step="0.01" min="0.00">
                                            </div>
                                            <% if(demand.status.equals("Accepted")){ %>
                                            <div>
                                                    <button type="submit">Accept Charges</button>
                                            </div>
                                            <% } %>

                                    </form>
                                    <div>
                                            <form action="<% out.print(Configs.url_prefix + "order/cancel/" + demand.id); %>" method="POST">
                                                    <button type="submit">Cancel Order</button>
                                            </form>
                                    </div>
                                    <div>
                                            <a href="<% out.print(Configs.url_prefix + "home"); %>">Go back</a>
                                    </div>
                                    <%
                                    }
                                    else{
                                    %>
                                    <p>You have no orders pending do you want to <a href="<% out.print(Configs.url_prefix + "order"); %>">place an order?</a></p>
                                    <%}%>
                                </div>
                              </div>      
                          </div>

                      </div>

                  </div>
                </main>
            </div>
            
            
		
	</div>
	<script src="/alphaCab/static/js/jquery-3.2.1.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD5c-jK4r3n2qvCE5UU-b4r5A_m5KVaOos"></script>
	<script src="/alphaCab/static/js/axios.min.js"></script>
	<script src="/alphaCab/static/js/vue.js"></script>
	<script src="/alphaCab/static/js/getDistance.js"></script>
</body>
</html>
