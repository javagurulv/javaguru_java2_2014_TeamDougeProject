package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Juris on 09.11.2014.
 */
@Controller
public class IndexController{

    @Autowired @Qualifier("ORM_UserDAO")
    UserDAO userDAO;

    @RequestMapping(value = "index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) {

        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        String ErrorString = "";
        final String incorrectLoginString = "<font color=\"red\">Login and/or password incorrect!</font>";
        final String emptyLoginString ="<font color=\"red\">Name and/or password can't be empty!</font>";

        //check that submit button was pressed and POST data received
        if (req.getParameter("submit") != null) {

            //check that fields are not empty
            if (isParametersValid(req)) {
                String login = req.getParameter("login");
                String password = MD5(req.getParameter("passwd"));

                User userFromDB;

                try {
                    userFromDB = userDAO.getByLogin(login);

                    //check that login exists and password matches
                    if (userFromDB != null && password.equals(userFromDB.getPassword())) {
                        HttpSession session = req.getSession();
                        session.setAttribute("sessionLogin", login);
                        session.setMaxInactiveInterval(300);

                        model.setViewName("securearea");
                    } else {
                        ErrorString = incorrectLoginString;
                        model.setViewName("login");
                    }
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else {
                ErrorString = emptyLoginString;
                model.setViewName("login");
            }
        }
        model.addObject("model",ErrorString);
        return model;
    }

    private boolean isParametersValid(HttpServletRequest req) {
        return !req.getParameter("login").trim().isEmpty() &&
                !req.getParameter("passwd").trim().isEmpty();
    }

    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
