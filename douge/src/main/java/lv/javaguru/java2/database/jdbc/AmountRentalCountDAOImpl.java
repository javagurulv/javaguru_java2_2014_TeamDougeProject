package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.AmountRentalCountDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.AmountRentalCount;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 21.11.2014.
 */
public class AmountRentalCountDAOImpl extends DAOImpl implements AmountRentalCountDAO {
    @Override
    public List<AmountRentalCount> getAmountRentalCountData() throws DBException {
        List<AmountRentalCount> amountRentalCounts = new ArrayList<AmountRentalCount>();
        Connection connection = null;
        try
        {

        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute AmountRentalCountDAOImpl.getAmountRentalCountData()");
        }
        finally {
            closeConnection(connection);
        }

        return null;
    }
}
