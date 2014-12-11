package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by user on 11-Dec-14.
 */
@Component
public class EditWidgetControllerImpl implements EditWidgetController {

    @Override
    @Transactional
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        String sessionLogin = null;
        HttpSession session = request.getSession();

        return new MVCModel("/jsp/editwidget.jsp", "test");

    }

}
