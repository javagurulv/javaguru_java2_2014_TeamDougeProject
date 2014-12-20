package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * Created by Viktor on 02/12/2014.
 */
@Controller
public class AddUserControllerImpl {

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @RequestMapping(value = "adduser", method = {RequestMethod.GET,RequestMethod.POST})
    @Transactional
    public ModelAndView processRequest(HttpServletRequest req,
                                   HttpServletResponse resp) {

        ModelAndView model = new ModelAndView();
        model.setViewName("adduser");

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

        model.addObject("model",infoString);

        return model;
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
