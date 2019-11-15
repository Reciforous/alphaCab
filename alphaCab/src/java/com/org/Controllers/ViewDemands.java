package com.org.Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import com.org.Models.Demand;

/*
    TODO: Move to git

    WHATS NEW:
        - since response writes HTML instead of \n you need <br>
 */
public class ViewDemands extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            ArrayList<Demand> demands = Demand.getDemands();
            request.setAttribute("demands", demands);
            request.getRequestDispatcher("/views/tests/demands-test.jsp").forward(request, response);

        }
        catch (ServletException e){
            response.getWriter().print("There was an error handling your request, please go back!<br>" + e.getMessage());
        }
    }
}
