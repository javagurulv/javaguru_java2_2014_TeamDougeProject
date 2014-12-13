package lv.javaguru.java2.Controller.Builders;


import lv.javaguru.java2.database.ChartDataDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.hibernate.SpringIntegrationTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class WidgetQueryBuilderTest extends SpringIntegrationTest {
    @Autowired
    @Qualifier("WidgetQueryBuilder")
    private WidgetQueryBuilder widgetQueryBuilder;
    @Autowired
    @Qualifier("JDBC_ChartDataDAO")
    private ChartDataDAO chartDataDAO ;

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

}