package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 08.11.2014.
 */
@Component
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {
        return new MVCModel("/jsp/hello.jsp", "Hello!");
    }

}
