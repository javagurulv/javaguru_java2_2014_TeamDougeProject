package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by user on 14-Nov-14.
 */
@Component
public class LogoutController implements MVCController {
    public MVCModel processRequest(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(false);

        //invalidate the session if exists
        if (session != null) {
            session.invalidate();
            return new MVCModel("/jsp/logout.jsp", 1); //logout and redirect
        } else {
            return new MVCModel("/jsp/logout.jsp", 0); //just redirect
        }
    }
}
