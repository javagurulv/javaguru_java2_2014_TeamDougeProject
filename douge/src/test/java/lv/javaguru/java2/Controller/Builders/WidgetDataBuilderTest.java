package lv.javaguru.java2.Controller.Builders;


import lv.javaguru.java2.database.*;
import lv.javaguru.java2.database.hibernate.SpringIntegrationTest;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.MetricSet;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Widget;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
@WebAppConfiguration
public class WidgetDataBuilderTest extends SpringIntegrationTest {

    @Autowired
    private WidgetDataBuilder widgetDataBuilder;

    @Autowired
    private DataGenerator dataGenerator;

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    DashboardDAO dashboardDAO;

    @Autowired
    @Qualifier("ORM_WidgetDAO")
    WidgetDAO widgetDAO;

    @Autowired
    @Qualifier("ORM_MetricSetDAO")
    MetricSetDAO metricSetDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void cleanDB() throws DBException
    {
        databaseCleaner.cleanDatabase();
        dataGenerator.generateMetrics();
        dataGenerator.generateWidgetTypes();

    }

    @Test
    @Transactional
    public void testBuildWidgetdata() throws Exception {
        User user = new User();
        user.setLogin("aaa");
        user.setPassword("aaa");
        user.setComments("aaa");
        user.setUser_type(0L);
        userDAO.create(user);

        Dashboard dashboard = new Dashboard();
        dashboard.setUser(user);
        dashboard.setName("dashboard");
        dashboardDAO.create(dashboard);
        dataGenerator.generateMetrics();

        MetricSet metricSet = new MetricSet();
        metricSet.setPrimary_id(1L);
        metricSet.setGroupby_id(3L);
        metricSet.setLimit_id(10L);
        metricSetDAO.create(metricSet);

        Widget widget = new Widget();
        widget.setDashboard(dashboard);
        widget.setWidget_type_id(1L);
        widget.setMetric_set_id(metricSet.getId());
        widget.setPosition(1L);
        widget.setComments("widget comment");
        widgetDAO.create(widget);
        widgetDataBuilder.buildWidgetdata(widget);
        System.out.println(widget.getJsonWidgetDAta());
    }
}