<%@ page import="com.org.Models.Demand" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.org.Helpers.Configs" %>

<!DOCTYPE html>
<html>
<head>
	<title>View outstanding demands</title>
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
	<% ArrayList<Demand> demands = (ArrayList<Demand>)request.getAttribute("demands"); %>
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header" style="background-color: black;">
            <div class="mdl-layout__header-row">
                <span class="mdl-layout-title">Dashboard</span>
                <div class="mdl-layout-spacer"></div>
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                    <a href="/logout" class="mdl-navigation__link">Logout</a>
                </nav>
            </div>
        </header>
        <div class="mdl-layout__drawer">
		    <span class="mdl-layout-title">Dashboard</span>
		    <nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="/driver/orders">View outstanding orders</a>
				<a class="mdl-navigation__link" href="/driver/order/accepted">View Accepted Order</a>
		    </nav>
		</div>
		<main class="mdl-layout__content" style="background-color: grey;">
			<div class="mdl-grid" style="display: flex; justify-content: center;">
				<div class="mdl-cell--4-col" style="display: flex; justify-content: center;">
					<% if(!demands.isEmpty()){ %>
					<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
						<thead>
							<th class="mdl-data-table__cell--non-numeric">Customer Name</th>
							<th class="mdl-data-table__cell--non-numeric">Address</th>
							<th class="mdl-data-table__cell--non-numeric">Destination</th>
							<th class="mdl-data-table__cell--non-numeric">Details</th>
							<th class="mdl-data-table__cell--non-numeric">Status(Press to accept)</th>
						</thead>
						<tbody style="text-align: left !important;">
							<% for(Demand demand: demands){ %>
							<tr>
								<td class="mdl-data-table__cell--non-numeric"><% out.print(demand.name); %></td>
								<td class="mdl-data-table__cell--non-numeric"><% out.print(demand.address); %></td>
								<td class="mdl-data-table__cell--non-numeric"><% out.print(demand.destination); %></td>
								<td class="mdl-data-table__cell--non-numeric">
									<a href='<% out.print(Configs.url_prefix + "driver/order/outstanding/" + demand.id); %>' class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" style="text-align: left;">View</a>
								</td>
								<td class="mdl-data-table__cell--non-numeric">
									<a href='<% out.print(Configs.url_prefix + "driver/order/accept/" + demand.id); %>' class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" style="text-align: left;">
										<% out.print(demand.status); %>
									</a>
								</td>
							</tr>
							<% } %>
						</tbody>
					</table>
					<% } else { %>
						<div class="mdl-card mdl-shadow--2dp">
        				<div class="mdl-card__title">
        					<h4 class="mdl-card__title-text">No Outstanding Orders!</h4>
        				</div>
        				<div class="mdl-card__supporting-text">
        					There are currently no orders available.
        				</div>
        				<div class="mdl-card__actions mdl-card--border">
        					<a href="/driver/home" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Go Back</a>
        				</div>
        			</div>
					<% } %>
				</div>
			</div>
		</main>
	</div>
</body>
</html>
