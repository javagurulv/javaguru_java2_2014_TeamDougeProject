package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by user on 14-Nov-14.
 */
@Component
@RequestMapping(value = "logout", method = {RequestMethod.GET,RequestMethod.POST})
public class LogoutController{
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) {

        ModelAndView model = new ModelAndView();
        model.setViewName("logout");
        HttpSession session = req.getSession(false);

        //invalidate the session if exists
        if (session != null) {
            session.invalidate();
            model.addObject("model",1);
        } else {
            model.addObject("model",0);
        }
        return model;
    }
}
