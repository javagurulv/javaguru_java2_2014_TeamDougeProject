package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 08.11.2014.
 */
@Controller
public class HelloWorldController {

    @RequestMapping (value = "hello", method = RequestMethod.GET)
    public ModelAndView processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("model","Hello!");

        return model;
    }

}
