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
        .demo-card-wide.mdl-card {
            width: auto;
            height: 300px;
        }



        #mycard {
            width:100%;
            display:flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            text-align: left;
        }

    </style>
</head>
<body>
    
    
    <!--navigation-->
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
                          
                        <div id ="mycard">

                            <div class="demo-card-wide mdl-card mdl-shadow--2dp">
                                <div class="mdl-card__title">
                                    <h2 class="mdl-card__title-text font-orange">Book a Cab!</h2>
                                </div>

                                <div class="mdl-card__supporting-text">
                                <% Message message = (Message) request.getAttribute("message"); %>
                                    <form action="<% out.print(Configs.url_prefix + "order"); %>" method="POST">
                                    <% if(message != null){ %>
                                    <div>
                                            <p>Message: <% out.print(message.content); %></p>
                                    </div>
                                    <% } %>

                                        <div id="address">
                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                              <input class="mdl-textfield__input" type="text" id="address">
                                              <label class="mdl-textfield__label" for="sample3">Address</label>
                                            </div>
                                        </div>

                                        <div id ="destination">
                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                              <input class="mdl-textfield__input" type="text" id="destination">
                                              <label class="mdl-textfield__label" for="sample3">Destination</label>
                                            </div>
                                        </div>
                                        <br>
                                        <div style="text-align:right;">
                                             <!-- Flat button with ripple -->
                                            <a class="mdl-button mdl-js-button mdl-js-ripple-effect" href="<% out.print(Configs.url_prefix + "home"); %>">Go back</a>
                                            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent bg-green">
                                              Order
                                            </button>
                                        </div>
                                   
                                        
                                    </form>
                                    
                                    
                                    
                                    
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
