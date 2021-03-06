package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricSetDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.MetricSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@WebAppConfiguration
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

        MetricSet metricSet = new MetricSet();
        metricSet.setPrimary_id(1L);
        metricSet.setGroupby_id(1L);
        metricSet.setLimit_id(6L);

        metricSetDAO.create(metricSet);


        MetricSet metricSetFromDB = metricSetDAO.getById(metricSet.getId());
        assertEquals(metricSet.getGroupby_id(), metricSetFromDB.getGroupby_id());

    }
}
