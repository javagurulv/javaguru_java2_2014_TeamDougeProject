package lv.javaguru.java2.database.jdbc;

/**
 * Created by Radchuk on 11/10/2014.
 */
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricSetDAO;
import lv.javaguru.java2.domain.MetricSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MetricSetDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private MetricSetDAO metricSetDAO = new MetricSetDAOImpl();

    @Before
    public void cleanDB() throws DBException
    {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        MetricSet metricSet = new MetricSet();
        metricSet.setMetric_id(10);
        metricSet.setId(1);
        metricSetDAO.create(metricSet);

        MetricSet metricSetFromDB = metricSetDAO.getAllByMetricSetId(metricSet.getId()).get(0);

        assertNotNull(metricSetFromDB);
        assertEquals(metricSet.getId(), metricSetFromDB.getId());
        assertEquals(metricSet.getMetric_id(), metricSetFromDB.getMetric_id());


        MetricSet metricSetFromDB1 = metricSetDAO.getAllByMetricId(metricSet.getMetric_id()).get(0);

        assertNotNull(metricSetFromDB1);
        assertEquals(metricSet.getId(), metricSetFromDB1.getId());
        assertEquals(metricSet.getMetric_id(), metricSetFromDB1.getMetric_id());
    }

    @Test
    public void testDelete() throws DBException
    {
        MetricSet metricSet = new MetricSet();
        metricSet.setMetric_id(10);
        metricSet.setId(1);
        metricSetDAO.create(metricSet);

        metricSetDAO.delete(metricSet);

        assertEquals(metricSetDAO.getAllByMetricSetId(metricSet.getId()).size(), 0);

    }

    @Test
    public void testMultipleAppending() throws DBException
    {
        MetricSet metricSet = new MetricSet();
        metricSet.setMetric_id(10);
        metricSet.setId(1);

        MetricSet metricSet1 = new MetricSet();
        metricSet1.setMetric_id(10);
        metricSet1.setId(2);

        MetricSet metricSet2 = new MetricSet();
        metricSet2.setMetric_id(3);
        metricSet2.setId(1);

        metricSetDAO.create(metricSet);
        metricSetDAO.create(metricSet1);
        metricSetDAO.create(metricSet2);

        assertEquals(metricSetDAO.getAllByMetricSetId(metricSet.getId()).size(),2);
        assertEquals(metricSetDAO.getAllByMetricSetId(metricSet1.getId()).size(),1);
        assertEquals(metricSetDAO.getAllByMetricId(metricSet.getMetric_id()).size(),2);
        assertEquals(metricSetDAO.getAllByMetricId(metricSet2.getMetric_id()).size(),1);

    }
}
