<%@page import="com.org.Helpers.Configs"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/static/css/material.min.css">
    <script src="/static/js/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <style>
    	.text-center {
    		text-align: center;
    	}
    </style>
</head>
<body>
    <%
        Float amount = (Float) request.getAttribute("amount");
        int total_journies = (Integer) request.getAttribute("total_journies");
    %>
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
        <header class="mdl-layout__header" style="background-color: black;">
            <div class="mdl-layout__header-row">
                <span class="mdl-layout-title">Dashboard</span>
                <div class="mdl-layout-spacer"></div>
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                    <a href="<% out.print(Configs.url_prefix + "logout"); %>" class="mdl-navigation__link">Logout</a>
                </nav>
            </div>
        </header>
        <div class="mdl-layout__drawer" style="background:color: grey !important;">
		    <span class="mdl-layout-title">Dashboard</span>
		    <nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="<% out.print(Configs.url_prefix + "driver/orders"); %>">View outstanding orders</a>
				<a class="mdl-navigation__link" href="<% out.print(Configs.url_prefix + "driver/order/accepted"); %>">View Accepted Order</a>
		    </nav>
		</div>
        <main class="mdl-layout__content" style="background-color: grey;">
        	<div class="mdl-grid" style="display: flex; justify-content: center">
        		<div class="mdl-cell mdl-cell--3-col">
        			<div class="mdl-card mdl-shadow--2dp">
        				<div class="mdl-card__title">
        					<h4 class="mdl-card__title-text">Outstanding Orders</h4>
        				</div>
        				<div class="mdl-card__supporting-text">
        					View all outstanding orders
        				</div>
        				<div class="mdl-card__actions mdl-card--border">
        					<a href="<% out.print(Configs.url_prefix + "driver/orders"); %>" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">View</a>
        				</div>
        			</div>
        		</div>
        		<div class="mdl-cell mdl-cell--3-col">
        			<div class="mdl-card mdl-shadow--2dp">
        				<div class="mdl-card__title">
        					<h4 class="mdl-card__title-text">Accepted Order</h4>
        				</div>
        				<div class="mdl-card__supporting-text">
        					View accepted order
        				</div>
        				<div class="mdl-card__actions mdl-card--border">
        					<a href="<% out.print(Configs.url_prefix + "driver/order/accepted"); %>" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">View</a>
        				</div>
        			</div>
        		</div>
        	</div>
        	<div class="mdl-grid" style="display: flex; justify-content: center;">
        	    <div class="mdl-cell mdl-cell--3-col">
        	        <div class="mdl-card mdl-shadow--2dp">
                        <div class="mdl-card__title">
                            <h4 class="mdl-card__title-text">Journies</h4>
                        </div>
                        <div class="mdl-card__supporting-text">
                            Total Journies Made This Month: <% out.print(total_journies); %>
                            <br>
                            Total Amount Made: <% out.print(amount); %>
                        </div>
                        <div class="mdl-card__actions mdl-card--border">
                            <a href="<% out.print(Configs.url_prefix + "driver/journies"); %>" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">View</a>
                        </div>
        	        </div>
        	    </div>
        		<div class="mdl-cell mdl-cell--3-col">
        			<div class="mdl-card mdl-shadow--2dp">
        				<div class="mdl-card__title">
        					<h4 class="mdl-card__title-text">Logout</h4>
        				</div>
        				<div class="mdl-card__supporting-text">
        					Good bye!
        				</div>
        				<div class="mdl-card__actions mdl-card--border">
        					<a href="<% out.print(Configs.url_prefix + "logout"); %>" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Logout</a>
        				</div>
        			</div>
        		</div>
        	</div>
        </main>
    </div>
</body>
</html>
