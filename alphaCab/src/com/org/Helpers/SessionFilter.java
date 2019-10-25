package com.org.Helpers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SessionFilter implements Filter{
    private FilterConfig config = null;


    public void init(FilterConfig config) throws ServletException{
        this.config = config;
    }

    public void destroy(){
        this.config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter_chain) throws IOException, ServletException {
        long before = System.currentTimeMillis();
        filter_chain.doFilter(request, response);
        long after = System.currentTimeMillis();

        String uri = " ";

        if(request instanceof HttpServletRequest){
            uri = ((HttpServletRequest) request).getRequestURI();
            HttpSession session = ((HttpServletRequest) request).getSession();

            if(session == null){
                request.getRequestDispatcher("/bank/login").forward(request, response);
            }
        }

        System.out.println(uri + ": " + (after - before) + "ms");
    }
}
