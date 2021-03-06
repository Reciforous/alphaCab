<%-- TODO: Add to git --%>
<%@ page import="com.org.Models.Demand" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.org.Helpers.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>View Order</title>
	<link rel="stylesheet" href="/alphaCab/static/css/material.min.css">
    <script src="/alphaCab/static/js/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <style>
    	.text-center {
    		text-align: center;
    	}
    </style>
</head>
<body>
	<% 
	Demand demand = (Demand) request.getAttribute("demand"); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Message message = null;
	if(request.getAttribute("message") != null){
		message = (Message) request.getAttribute("message");
	}
	%>
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header" id="app">
		<header class="mdl-layout__header" style="background-color: black;">
            <div class="mdl-layout__header-row">
                <span class="mdl-layout-title">Dashboard</span>
                <div class="mdl-layout-spacer"></div>
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                    <a href="<% out.print(Configs.url_prefix + "logout"); %>" class="mdl-navigation__link">Logout</a>
                </nav>
            </div>
        </header>
        <div class="mdl-layout__drawer">
		    <span class="mdl-layout-title">Dashboard</span>
		    <nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="<% out.print(Configs.url_prefix + "driver/orders"); %>">View outstanding orders</a>
				<a class="mdl-navigation__link" href="<% out.print(Configs.url_prefix + "driver/order/accepted"); %>">View Accepted Order</a>
		    </nav>
		</div>
		<main class="mdl-layout__content" style="background-color: grey;">
			<div class="mdl-grid" style="display: flex; justify-content: center;">
				<div class="mdl-cell--8-col" style="display: flex; justify-content: center;">
					<% if(demand != null){ %>
					<div class="mdl-card mdl-shadow--2dp" style="width: 100%">
						<div class="mdl-card__title">
							<h3 class="mdl-card__title-text" style="text-align: center;">Order #<% out.print(demand.id); %></h3>
							<hr>
						</div>
						<div class="mdl-card__supporting-text">
							<div class="mdl-grid">
								<div class="mdl-cell--3-col"><h6>Customer Name </h6><% out.print(demand.name); %></div>
							</div>
							<div class="mdl-grid">
								<input id="address" type="text" hidden value="<% out.print(demand.address); %>" v-model="address">
								<div class="mdl-cell--3-col"><h6>Address </h6>{{ addressText }}</div>
								<input id="destination" type="text" hidden value="<% out.print(demand.destination); %>" v-model="destination">
								<div class="mdl-cell--3-col"><h6>Destination </h6>{{ destinationText }}</div>
								<div class="mdl-cell--3-col"><h6>Requested Date </h6><b><% out.print(sdf.format(demand.date)); %></b></div>
							</div>
							<div class="mdl-grid">
								<div class="mdl-cell--3-col"><h6>Status </h6><% out.print(demand.status); %></div>
							</div>
						</div>
						<div class="mdl-card__actions mdl-card--border">
							<% if(demand.status.equals("Outstanding")){ %>
							<a href="<% out.print(Configs.url_prefix + "driver/order/accept/" + demand.id); %>" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Accept Demand</a>
							<% } %>
							<% if(demand.status.equals("Accepted")){ %>
							<form action="<% out.print(Configs.url_prefix + "driver/order/cancel/" + demand.id); %>" method="POST">
								<button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Cancel Order</button>	
							</form>
							<% } %>
                                                <a href="<% out.print(Configs.url_prefix + "driver/home"); %>" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Go Back</a>
        				</div>
					</div>
					<% } else{ %>
					<div class="mdl-card mdl-shadow--2dp">
						<div class="mdl-card__title">
							<h3 class="mdl-card__title-text" style="text-align: center;">No Order accepted</h3>
							<hr>
						</div>
						<div class="mdl-card__supporting-text">
							Go back and accept an order!
						</div>
						<div class="mdl-card__actions mdl-card--border">
        					<a href="<% out.print(Configs.url_prefix + "driver/home"); %>" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Go Back</a>
        				</div>
					<% } %>
				</div>
			</div>
		</main>
	</div>
	<script src="/alphaCab/static/js/jquery-3.2.1.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD5c-jK4r3n2qvCE5UU-b4r5A_m5KVaOos"></script>
	<script src="/alphaCab/static/js/axios.min.js"></script>
	<script src="/alphaCab/static/js/vue.js"></script>
	<script src="/alphaCab/static/js/getJourneyDetails.js"></script>
</body>
</html>
