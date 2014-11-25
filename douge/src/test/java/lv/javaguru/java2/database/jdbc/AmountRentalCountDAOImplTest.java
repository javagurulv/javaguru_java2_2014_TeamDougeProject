package lv.javaguru.java2.database.jdbc;

import junit.framework.TestCase;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.AmountRentalCount;

import java.util.List;

public class AmountRentalCountDAOImplTest extends TestCase {

    public void testGetAmountRentalCountData() throws Exception {
        List<AmountRentalCount> amountRentalCounts = DAOFactory.getInstance().getAmountRentalCountDAO().getAmountRentalCountData();

        assertTrue(amountRentalCounts.size() > 0);

    }

    public void testGetInfoMap() throws DBException
    {
        List<AmountRentalCount> amountRentalCounts = DAOFactory.getInstance().getAmountRentalCountDAO().getAmountRentalCountData();

        for (int i = 0; i < amountRentalCounts.size(); i++) {
            try {
                //System.out.println(amountRentalCounts.get(i).getInfoMap().toString());
                System.out.println(amountRentalCounts.get(i).getFullInfoMap().toString());
            }
            catch (Throwable e)
            {
                System.out.println(e.getMessage());
                assertTrue(false);
            }
        }
    }
}