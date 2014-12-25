package lv.javaguru.java2.servlets.mvc.controllrers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sergo on 24.12.2014.
 */

@Controller
public class RootController {
    @RequestMapping(value = "", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/index");
        return null;
    }
}
