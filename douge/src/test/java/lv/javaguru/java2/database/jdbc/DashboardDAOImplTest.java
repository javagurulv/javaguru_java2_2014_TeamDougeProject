package lv.javaguru.java2.database.jdbc;

/**
 * Created by Sergo on 16.10.2014.
 */

import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.WidgetTypeDAO;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.domain.Widget;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;

public class DashboardDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private DashboardDAO dashboardDAO = new DashboardDAOImpl();

    private Dashboard createDashboard(Long userId, String comments)
    {
        Dashboard dashboard = new Dashboard();
        dashboard.setUser_id(userId);
        dashboard.setName(comments);
        return dashboard;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        Dashboard dashboard = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard);

        Dashboard dashboardFromDB = dashboardDAO.getById(dashboard.getId());

        assertNotNull(dashboardFromDB);
        assertEquals(dashboard.getUser_id(), dashboardFromDB.getUser_id());
        assertEquals(dashboard.getName(), dashboardFromDB.getName());
    }

    @Test
    public void testDelete() throws DBException
    {
        Dashboard dashboard = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard);

        Dashboard dashboardFromDB = dashboardDAO.getById(dashboard.getId());

        assertNotNull(dashboardFromDB);

        dashboardDAO.delete(dashboard.getId());

        dashboardFromDB = dashboardDAO.getById(dashboard.getId());
        assertNull(dashboardFromDB);

    }

    @Test
    public void testUpdate() throws DBException
    {
        Dashboard dashboard = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard);
        dashboard.setUser_id((long)50);
        dashboard.setName("TEST TEST");
        dashboardDAO.update(dashboard);

        Dashboard dashboardFromDB = dashboardDAO.getById(dashboard.getId());

        assertNotNull(dashboardFromDB);
        assertEquals(dashboard.getUser_id(), dashboardFromDB.getUser_id());
        assertEquals(dashboard.getName(), dashboardFromDB.getName());
    }

    @Test
    public void testGetAll() throws DBException
    {
        Dashboard dashboard1 = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard1);

        Dashboard dashboard2 = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard2);

        Dashboard dashboard3 = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard3);

        List<Dashboard> dashboards = dashboardDAO.getAll();

        assertEquals(dashboards.size(), 3);
    }


    @Test
    public void testGetAllForUser()throws DBException
    {
        Dashboard dashboard1 = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard1);

        Dashboard dashboard2 = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard2);

        Dashboard dashboard3 = createDashboard((long)2,"TEST!");
        dashboardDAO.create(dashboard3);

        Dashboard dashboard4 = createDashboard((long)8,"TEST!");
        dashboardDAO.create(dashboard4);


        User user = new User();

        user.setUserId(2L);

        List<Dashboard> dashboards = dashboardDAO.getAllForUser(user);

        assertEquals(dashboards.size(), 3);

        user.setUserId(8L);

        dashboards = dashboardDAO.getAllForUser(user);

        assertEquals(dashboards.size(), 1);

    }

}
