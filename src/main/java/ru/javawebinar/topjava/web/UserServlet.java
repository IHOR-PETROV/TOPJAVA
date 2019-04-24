package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("redirect to users");
        //   request.getRequestDispatcher("/meals.jsp").forward(request, response);
        ServletContext sevletContext =getServletContext();
        Enumeration<String> attributeNames = sevletContext.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String s = attributeNames.nextElement();
            System.out.println(s+" = "+ getServletContext().getAttribute(s));
        }
            response.sendRedirect("users.jsp");
    }
}
