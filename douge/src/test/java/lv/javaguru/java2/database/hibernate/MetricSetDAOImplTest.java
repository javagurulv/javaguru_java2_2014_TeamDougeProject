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

        MetricSet metricSet = new MetricSet();
        metricSet.setPrimary_id(1);
        metricSet.setGroupby_id(1);
        metricSet.setLimit_id(6);

        metricSetDAO.create(metricSet);


        MetricSet metricSetFromDB = metricSetDAO.getById(metricSet.getId());
        assertEquals(metricSet.getGroupby_id(), metricSetFromDB.getGroupby_id());

    }
}
