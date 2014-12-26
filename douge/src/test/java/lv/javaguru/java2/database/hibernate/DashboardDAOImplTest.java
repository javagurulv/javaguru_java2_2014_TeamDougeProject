package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by user on 02-Dec-14.
 */
@WebAppConfiguration
public class DashboardDAOImplTest extends SpringIntegrationTest {

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    private DashboardDAO dashboardDAO;
    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void cleanDb() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void createNewDashboardInstance() throws DBException {

        //User user = userDAO.getById(1L);
        User user = new User();
        user.setComments("aaa");
        user.setUser_type(0L);
        user.setPassword("aaaa");
        user.setLogin("aaa");
        userDAO.create(user);
        Dashboard dashboard = new Dashboard();
        dashboard.setName("Rental Count");
        dashboard.setUser(user);

        assertNull(dashboard.getId());
        dashboardDAO.create(dashboard);
        assertNotNull(dashboard.getId());
    }



}
