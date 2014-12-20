package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AddUserControllerImplTest extends MockitoTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private AddUserControllerImpl controller = new AddUserControllerImpl();


    @Test
    public void shouldStoreUserToDB() throws DBException {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        doReturn("submit").when(req).getParameter("submit");
        doReturn("0").when(req).getParameter("user_type");
        doReturn("vasja").when(req).getParameter("login");
        doReturn("123").when(req).getParameter("passwd");

        controller.processRequest(req, null);

        verify(userDAO, times(1)).create(any(User.class));
    }

    @Test
    public void shouldStoreUserToDB2() throws DBException {
        /*HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        doReturn("submit").when(req).getParameter("submit");

        controller = spy(controller);
        doReturn(true).when(controller).isParametersValid(req);
        doReturn(new User()).when(controller).createUserFromRequest(req);

        MVCModel model = controller.processRequest(req, null);

        verify(userDAO, times(1)).create(any(User.class));
        assertNotNull(model);
        assertEquals("/jsp/adduser.jsp", model.getView());
        assertEquals("User successfully added!", model.getData()); */
    }

}