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
    <style>
        .demo-card-wide.mdl-card {
            width: 500px;
            height: auto;
            padding-left: 10px;
        }

        #mycard {
            width:100%;
            display:flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            text-align: left;
        }

          .mdl-card__title-text {
            position: relative;
            top: 6px;

          }

          .mdl-textfield {
            width: 100%;
      
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
                            <% 
                            Demand demand = (Demand) request.getAttribute("demand"); 
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            %>
                            <div id="mycard">
                                <div class="demo-card-wide mdl-card mdl-shadow--2dp">
                                    <div class="mdl-card__title">
                                        <h2 class="mdl-card__title-text">Pending Order Details</h2>
                                    </div>
                                    <div class="mdl-card__supporting-text">
                                        <div id="app">
                                                    <div>
                                                        <% if(demand != null){ %>
                                                        <form action="<% out.print(Configs.url_prefix + "order/complete"); %>" method="POST">
                                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                                <input type="number" hidden readonly name="demandId" value="<% out.print(demand.id); %>" >
                                                                <input class="mdl-textfield__input" name="customerName" type="text" readonly value="<% out.print(demand.name); %>">
                                                                <label class="mdl-textfield__label" for="demandId">Ordered By</label>
                                                            </div>
                                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                                <input name="address" id="address" type="text" readonly value="<% out.print(demand.address); %>" hidden>
                                                                <input class="mdl-textfield__input" type="text" v-model="addressText">
                                                                <label class="mdl-textfield__label" for="address">Address</label>
                                                            </div>
                                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                                <input name="destination" id="destination" type="text" readonly value="<% out.print(demand.destination); %>" hidden>
                                                                <input class="mdl-textfield__input" type="text" v-model="destinationText">
                                                                <label class="mdl-textfield__label" for="destination">Destination</label>
                                                            </div>
                                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                                <input class="mdl-textfield__input" name="dateOrdered" id="dateOrdered" type="text" readonly value="<% out.print(sdf.format(demand.date)); %>">
                                                                <label class="mdl-textfield__label" for="dateOrdered">Date Ordered</label>
                                                            </div>
                                                            
                                                            <%-- This parts needs to be done with javascript, use address and destination to make a post request to --%>
                                                            <%-- maps api, fix value for distance and amount through javascript current set rate on backend for journey is 2.75--%>
                                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                                <input class="mdl-textfield__input" id="distance" type="number" name="distance" readonly v-model="distance">
                                                                <label class="mdl-textfield__label" for="distance">Distance in Meters</label>
                                                            </div>
                                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                                <input class="mdl-textfield__input" id="amount" name="amount" readonly v-model="amount" type="number" step="0.01" min="0.00">
                                                                <label class="mdl-textfield__label" for="amount">Amount</label>
                                                            </div>
                                                            
                                                            <% if(demand.status.equals("Accepted")){ %>
                                                            <div>
                                                                <button type="submit">Accept Charges</button>
                                                            </div>
                                                            <% } %>
                                                        </form>
                                                        <div class="mdl-card__actions mdl-card--border" style="display: flex; justify-content: flex-end; border-top: none; padding-right: 0;">
                                                            <a class="mdl-button mdl-js-button mdl-js-ripple-effect" href="<% out.print(Configs.url_prefix + "home"); %>">Go back</a>
                                                            <form action="<% out.print(Configs.url_prefix + "order/cancel/" + demand.id); %>" method="POST" style="padding-left: 10px;">
                                                                <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" style="background-color: #b0001f">Cancel Order</button>
                                                                
                                                            </form>
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
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
                                                    
                                                    
    <script src="/alphaCab/static/js/jquery-3.2.1.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD5c-jK4r3n2qvCE5UU-b4r5A_m5KVaOos"></script>
    <script src="/alphaCab/static/js/axios.min.js"></script>
    <script src="/alphaCab/static/js/vue.js"></script>
    <script src="/alphaCab/static/js/getDistance.js"></script>
</body>

</html>
