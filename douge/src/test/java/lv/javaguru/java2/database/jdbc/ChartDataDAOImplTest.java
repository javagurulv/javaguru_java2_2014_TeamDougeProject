package lv.javaguru.java2.database.jdbc;

import junit.framework.TestCase;
import lv.javaguru.java2.database.ChartDataDAO;
import org.junit.Test;

import java.util.List;

public class ChartDataDAOImplTest extends TestCase {
    private ChartDataDAO chartDataDAO = new ChartDataDAOImpl();

    public void testGetAll() throws Exception {

    }

    public void testGetByParams() throws Exception {

    }

    @Test
    public void testGetRecordsAmount() throws Exception {
        Integer amount = chartDataDAO.getRecordsAmount();
        assertTrue(amount > 0);
    }
    @Test
    public void testGetByQueryText() throws Exception {
      String queryText = "select count(rental_id) as RentalCount, FilmCategory from data_united group by FilmCategory Order by RentalCount desc limit 10";
        List<Object[]> objects = chartDataDAO.getByQueryText(queryText);
        //assertTrue(objects.size() == 10);
        for (Object[] o : objects){
            for (int i = 0; i < o.length ; i++) {
                System.out.println(o[i].toString() + " " + o[i].getClass().getSimpleName());
            }
        }
    }
}