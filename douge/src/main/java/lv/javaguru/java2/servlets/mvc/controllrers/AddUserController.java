package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Radchuk on 11/14/2014.
 */
public class AddUserController implements MVCController {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public MVCModel processRequest(HttpServletRequest req, HttpServletResponse resp) {

         String ErrorString = "";

        //check that submit button was pressed and POST data received
        if (req.getParameter("submit") != null) {

            //create user and add to database
            User user = createUserFromRequest(req);
            storeUserToDatabase(user);

            //check that fields are not empty
            if (isParametersValid(req)) {
                ErrorString = "User successfully added!";
            } else {
                ErrorString =  "<font color=\"red\">Name and/or password can't be empty!</font>";
            }
        }

        return new MVCModel("/jsp/adduser.jsp", ErrorString);
    }

    private boolean isParametersValid(HttpServletRequest req) {
        return !req.getParameter("user_type").trim().isEmpty() &&
                !req.getParameter("login").trim().isEmpty() &&
                !req.getParameter("passwd").trim().isEmpty();
    }

    private void storeUserToDatabase(User user) {
        try {
            userDAO.create(user);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private User createUserFromRequest(HttpServletRequest req) {
        User user = new User();
        String user_type = req.getParameter("user_type");
        String login = req.getParameter("login");
        String password = req.getParameter("passwd");
        String comments = req.getParameter("comments");

        user.setComments(comments);
        user.setLogin(login);
        user.setPassword(password);
        user.setUser_type(Long.parseLong(user_type));
        return user;
    }
}
