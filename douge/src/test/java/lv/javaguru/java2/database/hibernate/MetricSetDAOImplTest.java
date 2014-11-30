package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricSetDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.MetricSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

public class MetricSetDAOImplTest extends SpringIntegrationTest{

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Autowired
    @Qualifier("ORM_MetricSetDAO")
    private MetricSetDAO metricSetDAO;

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testGetAllByMetricSetId() throws DBException {

        MetricSet metricSet1 = new MetricSet();
        metricSet1.setId(1);
        metricSet1.setMetric_id(1);

        MetricSet metricSet2 = new MetricSet();
        metricSet2.setId(1);
        metricSet2.setMetric_id(2);

        MetricSet metricSet3 = new MetricSet();
        metricSet3.setId(2);
        metricSet3.setMetric_id(1);

        MetricSet metricSet4 = new MetricSet();
        metricSet4.setId(2);
        metricSet4.setMetric_id(2);

        metricSetDAO.create(metricSet1);
        metricSetDAO.create(metricSet2);
        metricSetDAO.create(metricSet3);
        metricSetDAO.create(metricSet4);

        List<MetricSet> metricList1 = metricSetDAO.getAllByMetricSetId(0);
        List<MetricSet> metricList2 = metricSetDAO.getAllByMetricSetId(1);
        List<MetricSet> metricList3 = metricSetDAO.getAllByMetricSetId(2);

        assertEquals(0, metricList1.size());
        assertEquals(2, metricList2.size());
        assertEquals(2, metricList3.size());
    }
}
