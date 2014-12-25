package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 * Created by Radchuk on 11/14/2014.
 */
@Controller
public class LoginControllerImpl {

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @RequestMapping(value = "login", method = {RequestMethod.GET,RequestMethod.POST})
    @Transactional
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) {

        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        Integer errorType = null;

        //check that submit button was pressed and POST data received
        if (req.getParameter("submit") != null) {

            //check that fields are not empty
            if (isParametersValid(req)) {
                String login = req.getParameter("login");
                String password = req.getParameter("passwd");

                User user;

                try {
                    user = userDAO.getByLogin(login);

                    //check that login exists and password matches
                    if (user != null && password.equals(user.getPassword())) {
                        HttpSession session = req.getSession();
                        session.setAttribute("sessionLogin", login);
                        session.setAttribute("userType",user.getUser_type());
                        session.setAttribute("user", user);
                        session.setMaxInactiveInterval(300);
                        errorType = 0; //Login successful
                    } else {
                        errorType = 1; //Login or password incorrect
                    }
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else {
                errorType = 2; //Login or password is empty;
            }
        }
        model.addObject("model",errorType);
        return model;
    }

    protected boolean isParametersValid(HttpServletRequest req) {
        return !req.getParameter("login").trim().isEmpty() &&
                !req.getParameter("passwd").trim().isEmpty();
    }

}
