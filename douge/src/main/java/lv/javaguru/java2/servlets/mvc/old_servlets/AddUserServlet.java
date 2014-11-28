package lv.javaguru.java2.servlets.mvc.old_servlets;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by user on 06-Nov-14.
 */
public class AddUserServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* //set content type
        resp.setContentType("text/html");


        PrintWriter out = resp.getWriter();

        //check that POST values are not empty
        if (!req.getParameter("user_type").trim().isEmpty() &&
                !req.getParameter("login").trim().isEmpty() &&
                !req.getParameter("passwd").trim().isEmpty()) {

            //get parameters from the POST method
            String user_type = req.getParameter("user_type");
            String login = req.getParameter("login");
            String password = req.getParameter("passwd");
            String comments = req.getParameter("comments");

            //create user using above parameters
            UserDAO userDAO = new UserDAOImpl();
            User user = createUser(Integer.parseInt(user_type), login, password, comments);

            try {
                userDAO.create(user);
                out.println("User successfully added. <a href=\"/adduser\">Back</a>");
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            out.println("<font color=\"red\">Login and/or Password can't be empty!</font> <a href=\"/adduser\">Back</a>");
        }*/

    }

    private User createUser(int user_type, String login, String password, String comments) {
        User user = new User();
        /*user.setComments(comments);
        user.setLogin(login);
        user.setPassword(password);
        user.setUser_type(user_type);*/
        return user;
    }
}
