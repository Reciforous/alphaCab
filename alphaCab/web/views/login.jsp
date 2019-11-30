<%@page import="com.org.Helpers.Message" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/alphaCab/static/css/material.min.css">
    <script src="/alphaCab/static/js/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <style>
        html, body{
                height: 100%;
                background: url(/alphaCab/static/img/background.jpg) !important;
                background-repeat: no-repeat !important;
                background-size: cover !important;
                background-position: center;
            }

        .center{
            height: 100% !important;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .mdl-card__transparent{
            background-color: rgba(255, 255, 255, 0.7);
        }
    </style>
</head>
<body>
    <%
        Message message = (Message) request.getAttribute("message");
    %>
    <div id="container" style="height: 100%">
        <div class="center">
            <div class="mdl-card mdl-shadow--2dp mdl-card__transparent" style="width: 25%; padding-bottom: 10px; padding-top: 10px; align-items: center;">
                <div class="mdl-card__title" style="height: 80px">
                    <h3 class="mdl-card__title text" style="color: #3f51b5; font-weight: 100;">
                        &#945; Cab
                    </h3>
                </div>
                <form action="/login" method="POST" style="width: 100%; padding-right: 0px;">
                    <div class="mdl-card__supporting-text">
                        <% if(message != null){ %>
                            <div class="mdl-textfield mdl-js-textfield">
                                <label class="mdl-textfield__label" style="color: red"><% out.print(message.content); %></label>
                            </div>
                        <% } %>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%">
                            <input type="text" class="mdl-textfield__input" name="email" required>
                            <label class="mdl-textfield__label">Email</label>
                        </div>
                        <br>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%">
                            <input type="password" class="mdl-textfield__input" name="password" required>
                            <label class="mdl-textfield__label">Password</label>
                        </div>
                    </div>
                    <div class="mdl-card__actions mdl-card--border">
                        <button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            Login
                        </button>
                        <a href="/register" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            Register
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
