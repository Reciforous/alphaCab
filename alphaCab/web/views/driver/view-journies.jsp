<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="com.org.Models.Journey"%>
<%@page import="com.org.Helpers.Configs"%>

<!DOCTYPE html>
<html>
<head>
    <title>View Journies</title>
	<link rel="stylesheet" href="/alphaCab/static/css/material.min.css">
    <script src="/alphaCab/static/js/material.min.js"></script>

    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <style>
    	.text-center {
    		text-align: center;
    	}
    </style>
</head>
<body>
	<%
		ArrayList<Journey>journies = (ArrayList<Journey>) request.getAttribute("journies");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
    <div class="mdl-layout__drawer">
	    <span class="mdl-layout-title">Dashboard</span>
	    <nav class="mdl-navigation">
			<a class="mdl-navigation__link" href="<% out.print(Configs.url_prefix + "driver/orders"); %>">View outstanding orders</a>
			<a class="mdl-navigation__link" href="<% out.print(Configs.url_prefix + "driver/order/accepted"); %>">View Accepted Order</a>
	    </nav>
	</div>
	<main class="mdl-layout__content" style="background-color: grey;">
			<div class="mdl-grid" style="display: flex; justify-content: center">
				<div class="mdl-cell--8-col" style="display: flex; justify-content: center">
					<div class="mdl-card" style="width: 49%">
						<div class="mdl-card__title text-align: center;">
							<h5 class="mdl-card__title-text">Filter</h5>
						</div>
						<form action="<% out.print(Configs.url_prefix + "driver/journies"); %>" method="POST">
							<div class="mdl-card__supporting-text" style="display: flex;">
								<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%; margin-right: 20px;">
			                        <input type="date" class="mdl-textfield__input" name="startDate" placeholder="" required>
			                        <label class="mdl-textfield__label">Start Date</label>
			                    </div>
			                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%; margin-right: 20px;">
			                        <input type="date" class="mdl-textfield__input" name="endDate" placeholder="" required>
			                        <label class="mdl-textfield__label">End Date</label>
			                    </div>
							</div>
							<div class="mdl-card__actions mdl-card--border">
								<button class="mdl-button mdl-button--primary mdl-button--raised mdl-js-button mdl-js-ripple-effect" type="submit">Search</button>
								<a href="<% out.print(Configs.url_prefix + "driver/home"); %>" class="mdl-button mdl-button--primary mdl-button--raised mdl-js-button mdl-js-ripple-effect">Go Back</a>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="mdl-grid" style="display: flex; justify-content: center;">
				<div class="mdl-cell--4-col" style="display: flex; justify-content: center;">
					<% if(!journies.isEmpty()){ %>
					<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
						<thead>
							<th class="mdl-data-table__cell">#</th>
							<th class="mdl-data-table__cell">Distance</th>
							<th class="mdl-data-table__cell--non-numeric">Date</th>
							<th class="mdl-data-table__cell--non-numeric">Details</th>
						</thead>
						<tbody>
							<% for(Journey journey : journies){ %>
							<tr>
								<td class="mdl-data-table__cell"><% out.print(journey.id); %></td>
								<td class="mdl-data-table__cell"><% out.print(journey.distance); %></td>
								<td class="mdl-data-table__cell--non-numeric"><% out.print(sdf.format(journey.date)); %></td>
								<td class="mdl-data-table__cell--non-numeric">
									<a href='<% out.print(Configs.url_prefix + "driver/journey/view/" + journey.id); %>' class="mdl-button mdl-button--raised mdl-button--primary mdl-js-button mdl-js-ripple-effect">
										View
									</a>
								</td>
							</tr>
							<% } %>
						</tbody>
					</table>
					<% } else { %>
					<div class="mdl-card mdl-shadow--2dp">
        				<div class="mdl-card__title">
        					<h4 class="mdl-card__title-text">No Journies Made!</h4>
        				</div>
        				<div class="mdl-card__supporting-text">
        					You currently have not made any journies!
        				</div>
        				<div class="mdl-card__actions mdl-card--border">
        					<a href="<% out.print(Configs.url_prefix + "driver/home"); %>" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Go Back</a>
        				</div>
    				</div>
					<% } %>
				</div>
			</div>
	</main>
	</div>	
</body>
</html>
