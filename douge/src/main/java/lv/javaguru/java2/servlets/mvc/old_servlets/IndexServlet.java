package lv.javaguru.java2.servlets.mvc.old_servlets;

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
        out.println("<h3><a href=\"/login_form.html\">Sign in</h3>");
        out.println("<h3><a href=\"/adduser\">Add User</h3>");
        out.println("<h3><a href=\"/films\">Show film list</a></h3>");
        out.println("<h3><a href=\"/actors\">Show actor list</a></h3>");

    }
}
