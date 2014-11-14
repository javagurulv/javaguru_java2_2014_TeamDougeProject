package lv.javaguru.java2.servlets.mvc.old_servlets;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by user on 07-Nov-14.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        //check that login and password are not empty
        if (!req.getParameter("login").trim().isEmpty() &&
                !req.getParameter("passwd").trim().isEmpty()) {

            //get parameters from the POST method
            String login = req.getParameter("login");
            String password = req.getParameter("passwd");

            UserDAO userDAO = new UserDAOImpl();
            User userFromDB;

            try {
                userFromDB = userDAO.getByLogin(login);

                //check that login exists and password matches
                if (userFromDB != null && password.equals(userFromDB.getPassword())) {

                    HttpSession session = req.getSession();
                    session.setAttribute("sessionLogin", login);
                    session.setMaxInactiveInterval(300);

                    resp.sendRedirect("securearea.jsp");
                } else {
                    out.println("<font color=\"red\">Login and/or password incorrect!</font><a href=\"/login.html\">Back</a>");
                }
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            out.println("<font color=\"red\">Login and/or password can't be empty!</font><a href=\"/login.html\">Back</a>");
        }
        /*
        //debug
        out.println("<br><br>Debug<br><br>");
        out.println("Login:" + req.getParameter("login"));
        out.println("<br><br>");
        out.println("Password:" + req.getParameter("passwd"));
        */
    }
}
