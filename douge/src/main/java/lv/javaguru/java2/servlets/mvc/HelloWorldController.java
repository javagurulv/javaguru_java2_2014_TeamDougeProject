package lv.javaguru.java2.servlets.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 08.11.2014.
 */
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request,
                                   HttpServletResponse response) {
        return new MVCModel("/hello.jsp", "Hello World from MVC!");
    }

}
