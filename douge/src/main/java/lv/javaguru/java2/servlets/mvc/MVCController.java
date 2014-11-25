package lv.javaguru.java2.servlets.mvc;

import com.google.visualization.datasource.base.TypeMismatchException;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 08.11.2014.
 */
public interface MVCController {

    MVCModel processRequest(HttpServletRequest request,
                            HttpServletResponse response) throws TypeMismatchException;

}
