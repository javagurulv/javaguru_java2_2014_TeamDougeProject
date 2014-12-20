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
import static org.mockito.Mockito.*;


public class LoginControllerImplTest extends MockitoTest{

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private LoginControllerImpl controller = new LoginControllerImpl();


    @Test
    public void IsUserLogedIn() throws DBException{
       /* HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        //HttpSession session = req.getSession();
        doReturn("submit").when(req).getParameter("submit");
        doReturn("juras").when(req).getParameter("login");
        doReturn("root").when(req).getParameter("passwd");

        MVCModel model=controller.processRequest(req, null);

       // verify(userDAO, times(1)).getByLogin("juras");
        assertNotNull(model);

        //assertEquals("", model.getData());
       // assertEquals("/jsp/securearea.jsp", model.getView());*/

    }

    @Test
    public void IsUserLogedIn2() throws DBException{

       /* HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        doReturn("submit").when(req).getParameter("submit");
        doReturn("juras").when(req).getParameter("login");
        doReturn("root").when(req).getParameter("passwd");

        controller = spy(controller);
        doReturn(true).when(controller).isParametersValid(req);

        MVCModel model=controller.processRequest(req, null);

        verify(userDAO, times(1)).getByLogin("juras");
        assertNotNull(model);
        //assertEquals("/jsp/securearea.jsp", model.getView());
       // assertEquals("", model.getData());*/
    }

}