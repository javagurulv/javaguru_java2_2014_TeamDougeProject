package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by user on 02-Dec-14.
 */
public class DashboardDAOImplTest extends SpringIntegrationTest {

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    private DashboardDAO dashboardDAO;
    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Test
    @Transactional
    public void createNewDashboardInstance() throws DBException {
        User user = userDAO.getById(2L);

        Dashboard dashboard = new Dashboard();
        dashboard.setName("Sales Count");
        dashboard.setUser(user);

        assertNull(dashboard.getId());
        dashboardDAO.create(dashboard);
        assertNotNull(dashboard.getId());
    }



}
