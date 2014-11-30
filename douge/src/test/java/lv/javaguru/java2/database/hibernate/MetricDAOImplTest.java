package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricDAO;
import lv.javaguru.java2.domain.Metric;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by user on 30-Nov-14.
 */
public class MetricDAOImplTest extends SpringIntegrationTest {

    @Autowired
    @Qualifier("ORM_MetricDAO")
    private MetricDAO metricDAO;

    @Test
    @Transactional
    public void createNewLanguage() throws DBException {
        Metric metric = new Metric();

        metric.setType("Pie Chart");
        metric.setName("Metric1");
        metric.setCompatibility(1);

        metricDAO.create(metric);
        Metric languageFromDB = metricDAO.getById(metric.getId());
        assertEquals("Pie Chart", languageFromDB.getType());
        assertEquals("Metric1", languageFromDB.getName());
    }

}
