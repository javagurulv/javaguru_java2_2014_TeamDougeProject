package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.domain.Dashboard;
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

    @Test
    @Transactional
    public void createNewDashboardInstance() throws DBException {
        Dashboard dashboard = new Dashboard();
        dashboard.setName("Sales Count");
        dashboard.setUser_id(1L);

        //assertNull(dashboard.getId());
        dashboardDAO.create(dashboard);
        assertNotNull(dashboard.getId());
    }



}
