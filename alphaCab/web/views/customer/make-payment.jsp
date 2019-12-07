<%-- TODO: Add to git --%>
<%@ page import="com.org.Models.Transaction" %>
<%@ page import="com.org.Models.Customer" %>
<%@ page import="com.org.Models.Journey" %>
<%@ page import="com.org.Helpers.*" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
	<title>Payment Gateway</title>
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
          #alertMessage{
                    border: 1px solid transparent;
                    border-radius: 3px;
                    background-color: #d4edda;
                    border-color: #c3e6cb;
                    color: #00808a;
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

                            <div id="mycard">
                                <div class="demo-card-wide mdl-card mdl-shadow--2dp">

                                <div class="mdl-card__supporting-text">
                                    
                                    <div>

                                        <h4>Payment Gateway</h4>
                                        
                                        
                                        <% 
                                        Message message = (Message) request.getAttribute("message");
                                        Journey journey = null;
                                        Customer customer = null;
                                        Float amount = null;
                                        Boolean paid = (Boolean) request.getAttribute("paid");

                                        if(message.status){
                                                journey = (Journey) request.getAttribute("journey");
                                                customer = (Customer) request.getAttribute("customer");
                                        %>
                                            <% if(paid){ %>
                                                        <div id="alertMessage">
                                                            <h6 class="title" style="margin: 15px; text-align: center;"><% out.print(message.content); %></h6>
                                                        </div>
                                            <% } %>
                                                <form action='<% out.print(Configs.url_prefix + "pay/" + journey.id); %>' method="POST">
                                                    
                                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                            <input class="mdl-textfield__input" name="customer" type="text" readonly value='<% out.print(customer.name); %>'>
                                                            <label class="mdl-textfield__label" for="customer">Customer Name</label>
                                                        </div>
                                                        
                                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                            <input class="mdl-textfield__input" type="number" readonly name="journeyId" value='<% out.print(journey.id); %>'>
                                                            <label class="mdl-textfield__label" for="journeyId">ID</label>
                                                        </div>

                                                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                            <input class="mdl-textfield__input" type="text" name="amount" readonly value='<% out.print(journey.distance * 2.75); %>'>
                                                            <label class="mdl-textfield__label" for="amount">Amount</label>
                                                        </div>


                                                        <div class="mdl-card__actions mdl-card--border" style="display: flex; justify-content: flex-end; border-top: none; padding-right: 0;">
                                                            <a class="mdl-button mdl-js-button mdl-js-ripple-effect" href='<% out.print(Configs.url_prefix + "home"); %>'>Go Back</a>
                                                            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent bg-green" type="submit">Pay Now</button> 
                                                        </div>
                                                </form>
                                        <%} else{%>
                                               
                                                <div id="alertMessage">
                                                        <h6 class="title" style="margin: 15px; text-align: center;"><% out.print(message.content); %></h6>
                                                </div>
                                        <% } %>

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
    
    

	
</body>
</html>
