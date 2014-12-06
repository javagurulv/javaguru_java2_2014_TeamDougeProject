package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.UserDashboardDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Widget;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

public class UserDashboardDAOImplTest extends SpringIntegrationTest {

    @Autowired
    @Qualifier("ORM_UserDashboardDAO")

    private UserDashboardDAO userDashboardDAO;

    @Test
    @Transactional
    public void testGetById() throws Exception {

        User user = userDashboardDAO.getById(1L);
        assertNotNull(user.getUserId());
        assertTrue(user.getDashboards().size() > 0);

        List<Widget> widgetList = user.getDashboards().get(0).getWidgets();
        assertTrue(widgetList.size() > 0);

    }
}