package com.org.Controllers;

import com.org.Helpers.Functions;
import com.org.Models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    TODO: Move to git
 */
public class Logout extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Cookie[] cookies = request.getCookies();
        Cookie auth_cookie = Functions.getCookie(cookies, "authentication");

        assert auth_cookie != null;
        String email = auth_cookie.getValue();

        // Since we dont technically store user objects, get email from cookie and get object
        User user = new User(email);
        user.getByEmail();

        // log out
        user.logout(request, response);

        Functions.redirect(response, "login");
    }
}
