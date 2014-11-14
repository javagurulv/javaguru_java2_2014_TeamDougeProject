package lv.javaguru.java2.servlets.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Radchuk on 11/14/2014.
 */
public class LoginController  implements MVCController{
    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {
        return new MVCModel("/jsp/login.jsp");
    }
}
