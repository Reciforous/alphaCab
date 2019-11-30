<%@page import="com.org.Models.Journey" %>

<!DOCTYPE html>
<html>
<head>
	<title>View Journey</title>
        <link rel="stylesheet" href="/alphaCab/static/css/material.min.css">
        <script src="/alphaCab/static/js/material.min.js"></script>
        <link rel="stylesheet" href="/alphaCab/static/css/customCss.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
            .demo-card-wide.mdl-card {
            width: auto;
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
                                <% Journey journey = (Journey) request.getAttribute("journey"); %>
                                <div>
                                    
                                    <h4 style="padding-left: 10px;">Journey Details</h4>
                                    <ul class="demo-list-item mdl-list">
                                        <li class="mdl-list__item">
                                          <span class="mdl-list__item-primary-content">
                                            ID: <% out.print(journey.id); %>
                                          </span>
                                        </li>
                                        <li class="mdl-list__item">
                                          <span class="mdl-list__item-primary-content">
                                            Destination: <% out.print(journey.destination); %>
                                          </span>
                                        </li>
                                        <li class="mdl-list__item">
                                          <span class="mdl-list__item-primary-content">
                                            Distance Travelled: <% out.print(journey.distance); %> m
                                          </span>
                                        </li>
                                        <li class="mdl-list__item">
                                          <span class="mdl-list__item-primary-content">
                                            Amount: <% out.print(journey.distance * 2.75f); %> &euro;
                                          </span>
                                        </li>
                                    </ul>
                                    
                                </div>
                            </div>
                            <div class="mdl-card__actions mdl-card--border" style="text-align: right;">
                                <a class="mdl-button mdl-js-button mdl-js-ripple-effect" href="/alphaCab/journies">Go back</a>
                                <a class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent bg-green" href=<% out.print("/pay/" + journey.id); %>>
                                  pay now
                                </a>
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
