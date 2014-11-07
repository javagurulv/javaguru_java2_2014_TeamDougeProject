package lv.javaguru.java2.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by user on 07-Nov-14.
 */
public class LogoutServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        //invalidate the session if exists
        HttpSession session = req.getSession(false);
        //System.out.println();
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect("/login.html");
    }
}
