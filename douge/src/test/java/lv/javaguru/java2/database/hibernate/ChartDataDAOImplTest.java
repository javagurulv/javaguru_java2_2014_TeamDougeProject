package lv.javaguru.java2.database.hibernate;


import lv.javaguru.java2.database.ChartDataDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.ChartData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.List;


import static org.junit.Assert.*;

public class ChartDataDAOImplTest extends SpringIntegrationTest {
    @Autowired
    @Qualifier("ORM_ChartDataDAO")
    private ChartDataDAO chartDataDAO;

    @Test
    @Transactional
    public void testGetAll() throws DBException
    {
        List<ChartData> chartDatas = chartDataDAO.getAll();
        assertNotEquals(chartDatas.size(),0);
    }

    @Test
    @Transactional
    public void testGetAmount() throws DBException
    {
        Integer amount = chartDataDAO.getRecordsAmount();
        System.out.println(amount);
        assertTrue(amount > 0);
    }


}