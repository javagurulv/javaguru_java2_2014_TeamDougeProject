package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Radchuk on 11/14/2014.
 */
@Component
public class LoginController implements MVCController {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public MVCModel processRequest(HttpServletRequest req, HttpServletResponse resp) {

        String Model = "/jsp/login.jsp";
        String ErrorString = "";

        //check that submit button was pressed and POST data received
        if (req.getParameter("submit") != null) {

            //check that fields are not empty
            if (isParametersValid(req)) {
                String login = req.getParameter("login");
                String password = req.getParameter("passwd");

                User userFromDB;

                try {
                    userFromDB = userDAO.getByLogin(login);

                    //check that login exists and password matches
                    if (userFromDB != null && password.equals(userFromDB.getPassword())) {
                        HttpSession session = req.getSession();
                        session.setAttribute("sessionLogin", login);
                        session.setMaxInactiveInterval(300);

                        Model = "/jsp/securearea.jsp";
                    } else {
                        ErrorString = "<font color=\"red\">Login and/or password incorrect!</font>";
                    }
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else {
                ErrorString = "<font color=\"red\">Name and/or password can't be empty!</font>";
            }
        }
        return new MVCModel(Model, ErrorString);
    }

    private boolean isParametersValid(HttpServletRequest req) {
        return !req.getParameter("login").trim().isEmpty() &&
                !req.getParameter("passwd").trim().isEmpty();
    }

}
