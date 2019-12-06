<%-- TODO: Add to git --%>
<%@ page import="com.org.Helpers.Message" %>
<%@ page import="com.org.Helpers.Configs" %>
<!DOCTYPE html>
<html>
<head>
	<title>Order A Cab</title>
        <link rel="stylesheet" href="/alphaCab/static/css/material.min.css">
        <script src="/alphaCab/static/js/material.min.js"></script>
        <link rel="stylesheet" href="/alphaCab/static/css/customCss.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<style>
		.map {
			width: 100%;
			height: 20em;
			margin-top: 10px;
			margin-bottom: 10px;
		}

		html, body{
			height: 100%;
			width: 100%;
		}
                .demo-card-wide.mdl-card {
                width: 500px;
                height: auto;
                }

                #mycard {
                    width:100%;
                    display:flex;
                    flex-direction: row;
                    justify-content: center;
                    align-items: center;
                    text-align: left;
                }
                
                #addressSearchButton, #destinationSearchButton{
                    width: 100% !important;
                    margin-top: 10px !important;
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
                            
                            <div class="mdl-card__supporting-text" style="width: auto;">
                                
                                <h4>Order A Cab!</h4>


                                
                                <% Message message = (Message) request.getAttribute("message"); %>
                                <div id="app">
                                        <form action="<% out.print(Configs.url_prefix + "order"); %>" method="POST">
                                                <% if(message != null){ %>
                                                <div id="alertMessage">
                                                    <h6 class="title" style="margin: 15px; text-align: center;"><span class="">Message:</span> <% out.print(message.content); %></h6>
                                                </div>
                                                <% } %>
                                                <div>
                                                    <div class="row">
                                                        <div class="mdl-grid" style="padding-left: 0; height: 60px;">
                                                            
                                                                <div class="mdl-cell mdl-cell--8-col" style="margin-left: 0;">
                                                                    <div class="mdl-textfield mdl-js-textfield">
                                                                        <input class="mdl-textfield__input" type="text" id="addressSearch">
                                                                    <label class="mdl-textfield__label" for="address">Address</label>
                                                                    </div>
                                                                </div>
                                                                <div class="mdl-cell mdl-cell--4-col">
                                                                    <button id="addressSearchButton" class="mdl-button mdl-js-button mdl-js-ripple-effect">Search</button>
                                                                </div>
                                                            
                                                        </div>
                                                               
                                                    </div>
                                                        <input id="address" type="text" placeholder="Enter Address" name="address" required hidden>
                                                        <div class="map" id="addressMap"></div>
                                                </div>
                                                <div>
                                                    <div class="row">
                                                        <div class="mdl-grid" style="padding-left: 0; height: 60px;">
                                                            
                                                                <div class="mdl-cell mdl-cell--8-col" style="margin-left: 0;">
                                                                    <div class="mdl-textfield mdl-js-textfield">
                                                                        <input class="mdl-textfield__input" type="text" id="destinationSearch">
                                                                    <label class="mdl-textfield__label" for="destination">Destination</label>
                                                                    </div>
                                                                </div>
                                                                <div class="mdl-cell mdl-cell--4-col">
                                                                    <button id="destinationSearchButton" class="mdl-button mdl-js-button mdl-js-ripple-effect">Search</button>
                                                                </div>
                                                            
                                                        </div>    
                                                    </div>
                                                    <input id="destination" type="text" placeholder="Enter Destination" name="destination" required hidden>
                                                    <div id="destinationMap" class="map"></div>
                                                </div>
                                                <br>
                                                <div class="mdl-card__actions mdl-card--border" style="text-align: right;">
                                                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect" href="<% out.print(Configs.url_prefix + "home"); %>">Go back</a>
                                                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent bg-green" type="submit">Order</button>
                                                </div>
                                               
                                        </form>
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
	<script src="/alphaCab/static/js/makeOrder.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD5c-jK4r3n2qvCE5UU-b4r5A_m5KVaOos&libraries=places&callback=mapRender" async defer></script>
</body>
</html>
