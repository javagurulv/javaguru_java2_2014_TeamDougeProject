package lv.javaguru.java2.database.jdbc;

import junit.framework.TestCase;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.domain.CountByMonths;

import java.util.List;

public class CountByMonthsDAOImplTest extends TestCase {

    public void testGeCountByMonths() throws Exception {
        List<CountByMonths> countByMonths = DAOFactory.getInstance().getCountByMonthsDAO().geCountByMonths();
        assertTrue(countByMonths.size() > 0);

        for (int i = 0; i < countByMonths.size() ; i++) {
            try {
                //System.out.println(countByMonths.get(i).getInfoMap().toString());
                System.out.println(countByMonths.get(i).getFullInfoMap().toString());
            }
            catch (Throwable throwable)
            {
                System.out.println(throwable.getMessage());
                assertTrue(false);
            }
        }

    }
}