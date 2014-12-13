package lv.javaguru.java2.Controller.Builders;


import lv.javaguru.java2.database.*;
import lv.javaguru.java2.database.hibernate.SpringIntegrationTest;

import static org.junit.Assert.*;

import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.List;

public class WidgetQueryBuilderTest extends SpringIntegrationTest {
    @Autowired
    @Qualifier("WidgetQueryBuilder")
    private WidgetQueryBuilder widgetQueryBuilder;
    @Autowired
    @Qualifier("JDBC_ChartDataDAO")
    private ChartDataDAO chartDataDAO ;

    @Autowired
    @Qualifier("ORM_MetricDAO")
    MetricDAO metricDAO;

    @Autowired
    @Qualifier("ORM_MetricSetDAO")
    MetricSetDAO metricSetDAO;


    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    DashboardDAO dashboardDAO;

    @Autowired
    @Qualifier("ORM_WidgetDAO")
    WidgetDAO widgetDAO;

    @Test
    public void testBuildQuery() throws DBException {
        String primaryMetric = "rental_id";
        String secondaryMetric = "FilmCategory";
        Long limit = Long.valueOf(5);
        String queryString = widgetQueryBuilder.buidQuery(primaryMetric, secondaryMetric,limit);
        System.out.println(queryString);
        List<Object[]> objects = chartDataDAO.getByQueryText(queryString);
        assertTrue(objects.size() == 5);
    }

    @Test
    public void testBuildQueryByID() throws DBException
    {
        String queryString = widgetQueryBuilder.buildQuery(1L, 4L, 10L);
        System.out.println(queryString);
        queryString = widgetQueryBuilder.buildQuery(2L, 5L, 10L);
        System.out.println(queryString);
    }


    /*type	name	compatibility
1	Primary	"Item Count"	30
2	Primary	"Amount EUR"	30
3	GroupBy	"Staff Name"	30
4	GroupBy	"Film Category"	30
5	GroupBy	"Film Rating"	30
6	GroupBy	Day	30
7	GroupBy	Week	30
8	GroupBy	Month	30
9	Limit	1	30
10	Limit	2	30
11	Limit	3	30
12	Limit	4	30
13	Limit	5	30
14	Limit	6	30
*/
    @Transactional
    private void generateMetrics() throws DBException {
        metricDAO.create( new Metric("Primary","Item Count",30L));
        metricDAO.create( new Metric("Primary","Amount EUR",30L));
        metricDAO.create( new Metric("GroupBy","Staff Name",30L));
        metricDAO.create( new Metric("GroupBy","Film Category",30L));
        metricDAO.create( new Metric("GroupBy","Film Rating",30L));
        metricDAO.create( new Metric("GroupBy","Day",30L));
        metricDAO.create( new Metric("GroupBy","Week",30L));
        metricDAO.create( new Metric("GroupBy","Month",30L));
        metricDAO.create( new Metric("Limit","1",30L));
        metricDAO.create( new Metric("Limit","2",30L));
        metricDAO.create( new Metric("Limit","3",30L));
        metricDAO.create( new Metric("Limit","4",30L));
        metricDAO.create( new Metric("Limit","5",30L));
        metricDAO.create( new Metric("Limit","6",30L));
    }

    @Test
    @Transactional
    public void testBuildQueryFUll() throws DBException
    {
        new DatabaseCleaner().cleanDatabase();
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
        generateMetrics();

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

        String query = widgetQueryBuilder.buildQuery(widget);
        System.out.println(query);

        List<Object[]> resultList = chartDataDAO.getByQueryText(query);
        assertTrue(resultList.size() > 0);
    }

}