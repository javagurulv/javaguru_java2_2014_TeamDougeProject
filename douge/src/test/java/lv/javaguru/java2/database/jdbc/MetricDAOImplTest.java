package lv.javaguru.java2.database.jdbc;

/**
 * Created by Radchuk on 11/10/2014.
 */

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricDAO;
import lv.javaguru.java2.domain.Metric;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MetricDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private MetricDAO metricDAO = new MetricDAOImpl();

    private Metric createMetric(String type, String name, Long compatibility)
    {
        Metric metric = new Metric();
        metric.setCompatibility(compatibility);
        metric.setName(name);
        metric.setType(type);
        return metric;
    }

    private void matchMetrics(Metric metric, Metric metricFromDB)
    {
        assertEquals(metric.getName(), metricFromDB.getName());
        assertEquals(metric.getCompatibility(), metricFromDB.getCompatibility());
        assertEquals(metric.getId(), metricFromDB.getId());
        assertEquals(metric.getType(), metricFromDB.getType());
    }
    @Before
    public void cleanDb() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        Metric metric = createMetric("Primary", "Count",30L);
        metricDAO.create(metric);

        Metric metricFromDB = metricDAO.getById(metric.getId());
        assertNotNull(metricFromDB);
        matchMetrics(metric, metricFromDB);

    }

    @Test
    public void testDelete() throws DBException
    {
        Metric metric = createMetric("Primary", "Count",30L);
        metricDAO.create(metric);
        metricDAO.delete(metric.getId());

        Metric metricFromDB = metricDAO.getById(metric.getId());
        assertNull(metricFromDB);
    }

    @Test
    public void testUpdate() throws DBException
    {
        Metric metric = createMetric("Primary", "Count",30L);
        metricDAO.create(metric);

        metric.setType("Secondary");
        metric.setName("DAY");
        metric.setCompatibility(8L);

        metricDAO.update(metric);

        Metric metricFromDB = metricDAO.getById(metric.getId());
        matchMetrics(metric, metricFromDB);
    }

    @Test
    public void testGetAll() throws DBException
    {


        List<Metric> metrics = metricDAO.getAll();
        assertEquals(metrics.size(), 2);

    }



}
