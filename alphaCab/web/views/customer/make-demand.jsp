<%-- TODO: Add to git --%>
<%@ page import="com.org.Helpers.Message" %>
<%@ page import="com.org.Helpers.Configs" %>
<!DOCTYPE html>
<html>
<head>
	<title>Order A Cab</title>
	<style>
		.map {
			width: 50%;
			height: 20em;
			margin-top: 10px;
			margin-bottom: 10px;
		}

		html, body{
			height: 100%;
			width: 100%;
		}
	</style>
</head>
<body>
	<% Message message = (Message) request.getAttribute("message"); %>
	<div id="app">
		<form action="<% out.print(Configs.url_prefix + "order"); %>" method="POST">
			<% if(message != null){ %>
			<div>
				<p>Message: <% out.print(message.content); %></p>
			</div>
			<% } %>
			<div>
				<label for="address">Address</label>
				<div>
					<input type="text" id="addressSearch">
					<button id="addressSearchButton" >Search</button>
				</div>
				<input id="address" type="text" placeholder="Enter Address" name="address" required hidden>
				<div class="map" id="addressMap"></div>
			</div>
			<div>
				<label for="destination">Destination</label>
				<div>
					<input type="text" id="destinationSearch">
					<button id="destinationSearchButton" >Search</button>
				</div>
				<input id="destination" type="text" placeholder="Enter Destination" name="destination" required hidden>
				<div id="destinationMap" class="map"></div>
			</div>
			<div>
				<button type="submit">Order</button>
			</div>
			<div>
			    <a href="<% out.print(Configs.url_prefix + "home"); %>">Go back</a>
			</div>
		</form>
	</div>
	<script src="/alphaCab/static/js/jquery-3.2.1.min.js"></script>
	<script src="/alphaCab/static/js/makeOrder.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD5c-jK4r3n2qvCE5UU-b4r5A_m5KVaOos&libraries=places&callback=mapRender" async defer></script>
</body>
</html>
