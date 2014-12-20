package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by user on 11-Dec-14.
 */
@Controller
public class EditWidgetControllerImpl{

    @RequestMapping(value = "editwidget", method = {RequestMethod.GET,RequestMethod.POST})
    @Transactional
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        model.setViewName("editwidget");

        String sessionLogin = null;
        HttpSession session = request.getSession();

        model.addObject("model","test");

        return model;

    }

}
