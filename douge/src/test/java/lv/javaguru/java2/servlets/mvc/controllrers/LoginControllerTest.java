package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class LoginControllerTest extends MockitoTest{

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private LoginController controller = new LoginController();

    @Test
    public void IsUserLogedIn() throws DBException{
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        doReturn("submit").when(req).getParameter("submit");
        doReturn("juras").when(req).getParameter("login");
        doReturn("root").when(req).getParameter("passwd");

        MVCModel model=controller.processRequest(req, null);

        assertEquals("/jsp/securearea.jsp", model.getView());
        assertEquals("", model.getData());

    }

}