<%-- 
    Document   : register
    Created on : Oct 26, 2019, 5:01:36 PM
    Author     : shaaik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Register</title>
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
                height: 100% !important;
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
        
        
        <div id="container" style="height: 100%">
        <div class="center">
            <div class="mdl-card mdl-shadow--2dp mdl-card__transparent" style="width: 25%; padding-bottom: 10px; padding-top: 10px; align-items: center;">
                <div class="mdl-card__title" style="height: 80px">
                    <h3 class="mdl-card__title text" style="color: #3f51b5; font-weight: 100;">
                        &#945; Cab
                    </h3>
                </div>
                <form action="/register" method="POST" style="width: 100%; padding-right: 0px;">
                    <div class="mdl-card__supporting-text">
                        <h5 style="margin-top: 0">Registration</h5>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%">
                            <input type="text" class="mdl-textfield__input" name="name" required>
                            <label class="mdl-textfield__label">Name</label>
                        </div>
                        <br>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%">
                            <input type="text" class="mdl-textfield__input" name="address" required>
                            <label class="mdl-textfield__label">Address</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%">
                            <input type="email" class="mdl-textfield__input" name="email" required>
                            <label class="mdl-textfield__label">Email</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%">
                            <input type="password" class="mdl-textfield__input" name="password" required>
                            <label class="mdl-textfield__label">Password</label>
                        </div>
                    </div>
                    <div class="mdl-card__actions mdl-card--border">
                        <button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
        
        
        
        
        
        
<!--        <h1>Customer Driver</h1>
        <form action="/register" method="POST">
            <div>
               <div>
                   <label>Name</label>
                    <input type="text" name="name" required>
               </div>
                <br>
                <div>
                    <label>Address</label>
                    <input type="text" name="address" required>
                </div>
                <div>
                    <label>Email</label>
                    <input type="text" name="email" required>
                </div>
                <div>
                    <label>Password</label>
                    <input type="text" name="password" required>
                </div>
            </div>
            <div>
                <button type="submit">
                    Submit
                </button>
            </div>
        </form>-->
    </body>
</html>
