package lv.javaguru.java2.servlets;

/**
 * Created by Radchuk on 11/3/2014.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FilmTableServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
        resp.setContentType("text/html");
        HttpSession httpSession = req.getSession();

        // Prepare output html
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "Films servlet running!" + "</h1>");

    }
}
