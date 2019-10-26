package com.org.Controllers;

import com.org.Helpers.Message;
import com.org.Models.Demand;
import com.org.Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUser extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            String email = "example@example.com";
            String password = "thisisapassword";
            String type = "admin";
            User user = new User(email, password, type);
            Message message = user.add();
            System.out.println(message.content);

            email = "test@example.com";
            String driver_id = "AX0092";
            type = "driver";
            user = new User(email, password, type, driver_id);
            message = user.add();
            System.out.println(message.content);

            email = "almunafiq@example.com";
            int customer_id = 8;
            type = "customer";
            user = new User(email, password, type, customer_id);
            message = user.add();
            System.out.println(message.content);

            request.getRequestDispatcher("/views/admin/home.html").forward(request, response);
        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!\n" + e.getMessage());
        }
    }
}
