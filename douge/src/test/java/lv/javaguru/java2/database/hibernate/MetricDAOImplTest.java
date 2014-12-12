package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Metric;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by user on 30-Nov-14.
 */
public class MetricDAOImplTest extends SpringIntegrationTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Autowired
    @Qualifier("ORM_MetricDAO")
    private MetricDAO metricDAO;

    private Metric createMetric(String type, String name, Long compatibility)
    {
        Metric metric = new Metric();
        metric.setCompatibility(compatibility);
        metric.setName(name);
        metric.setType(type);
        return metric;
    }

    @Before
    public  void cleanDb() throws DBException
    {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreate() throws DBException {
        Metric metric = createMetric("FlowChart", "Metric1", 1L);
        metricDAO.create(metric);
        Metric metricFromDB = metricDAO.getById(metric.getId());
        assertEquals("FlowChart", metricFromDB.getType());
        assertEquals("Metric1", metricFromDB.getName());
    }

    @Test
    @Transactional
    public void testDelete() throws DBException
    {
        Metric metric = createMetric("FlowChart", "Metric1", 1L);
        metricDAO.create(metric);
        metricDAO.delete(metric.getId());

        Metric metricFromDB = metricDAO.getById(metric.getId());
        assertNull(metricFromDB);
    }

}
