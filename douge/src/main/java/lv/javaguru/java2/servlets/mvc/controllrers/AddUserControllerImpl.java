package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * Created by Viktor on 02/12/2014.
 */
@Component
public class AddUserControllerImpl implements AddUserController {

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Override
    @Transactional
    public MVCModel processRequest(HttpServletRequest req,
                                   HttpServletResponse resp) {

        String infoString = "";
        final String successString = "User successfully added!";
        final String errorString = "<font color=\"red\">Name and/or password can't be empty!</font>";

        //check that submit button was pressed and POST data received
        if (req.getParameter("submit") != null) {

            //check that fields are not empty
            if (isParametersValid(req)) {
                //create user and add to database
                User user = createUserFromRequest(req);
                storeUserToDatabase(user);

                infoString = successString;
            } else {
                infoString =  errorString;
            }
        }

        return new MVCModel("/jsp/adduser.jsp", infoString);
    }

    protected boolean isParametersValid(HttpServletRequest req) {
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

    protected User createUserFromRequest(HttpServletRequest req) {
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
