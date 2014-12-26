package lv.javaguru.java2.database.hibernate;


import lv.javaguru.java2.database.ChartDataDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.ChartData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;


import static org.junit.Assert.*;

@WebAppConfiguration
public class ChartDataDAOImplTest extends SpringIntegrationTest {
    @Autowired
    @Qualifier("JDBC_ChartDataDAO")
    private ChartDataDAO chartDataDAO;

    @Test
    @Transactional
    public void testGetAll() throws DBException
    {
        List<ChartData> chartDatas = chartDataDAO.getAll();
        assertNull(chartDatas);
    }

    @Test
    @Transactional
    public void testGetAmount() throws DBException
    {
        Integer amount = chartDataDAO.getRecordsAmount();
        System.out.println(amount);
        assertTrue(amount > 0);
    }

    @Test
    @Transactional
    public void testGetByQueryText() throws DBException
    {
        List list = chartDataDAO.getByQueryText("SELECT rental_id as '1', StaffName as '2', FilmRating as '3', FilmCategory as '4', amount as '5' FROM data_united limit 1");
        assertTrue(list.size() > 0);
        for (int i = 0; i < list.size(); i++) {
            Object[] o = (Object[])list.get(i);

            for (Object j : o ){
                System.out.println(j.toString());
            }
        }
    }


}