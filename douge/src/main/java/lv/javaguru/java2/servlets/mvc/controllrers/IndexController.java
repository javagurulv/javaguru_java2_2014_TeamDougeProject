package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 09.11.2014.
 */
public class IndexController implements MVCController {
    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {


        String indexPageCode;

        indexPageCode="<br>" + "<br>" + "\n" +
                      "<h3><a href=\"/login\">Sign in</h3>" + "\n" +
                      "<h3><a href=\"/adduser\">Add User</h3>" + "\n" +
                      "<h3><a href=\"/films\">Show film list</a></h3>" + "\n" +
                      "<h3><a href=\"/actors\">Show actor list</a></h3>" + "\n";


        return new MVCModel("/jsp/index.jsp", indexPageCode);
    }
}
