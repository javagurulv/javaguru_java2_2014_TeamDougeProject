package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.Controller.Builders.DataGenerator;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.WidgetDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Widget;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;

import static org.junit.Assert.*;


@WebAppConfiguration
public class WidgetDAOImplTest extends SpringIntegrationTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Autowired
    @Qualifier("ORM_WidgetDAO")
    private WidgetDAO widgetDAO;

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    private DashboardDAO dashboardDAO;

    @Before
    public void cleanDB() throws  DBException
    {
        databaseCleaner.cleanDatabase();
        dataGenerator.generateWidgetTypes();
        dataGenerator.generateMetrics();
    }

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Autowired
    private DataGenerator dataGenerator;

    @Test
    @Transactional
    public void createNewWidgetInstance() throws DBException {
        User user = new User();
        user.setLogin("aaa");
        user.setPassword("aaa");
        user.setUser_type(0);
        user.setComments("ttt");
        userDAO.create(user);

        Dashboard dashboard = new Dashboard();
        dashboard.setName("dashboard");
        dashboard.setUser(user);
        dashboardDAO.create(dashboard);
        assertTrue(dashboard.getId() > 0);


        Widget widget = new Widget();
        widget.setComments("Widget 1");
        widget.setDashboard(dashboard);
        widget.setMetric_set_id(8L);
        widget.setWidget_type_id(1);
        widget.setPosition(3L);

        assertNull(widget.getWidget_id());
        widgetDAO.create(widget);
        assertNotNull(widget.getWidget_id());
    }

}