package lv.javaguru.java2.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Juris on 02.11.2014.
 */
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        // Set response content type
        resp.setContentType("text/html");

        // Prepare output html
        PrintWriter out = resp.getWriter();
        out.println("<br>" + "<br>");
        out.println("<h3>" + "Sign in" + "</h3>");
        out.println("<h3>" + "New user" + "</h3>");
        out.println("<h3>" + "Show film list" + "</h3>");
        out.println("<h3>" + "Show actor list" + "</h3>");

    }
}