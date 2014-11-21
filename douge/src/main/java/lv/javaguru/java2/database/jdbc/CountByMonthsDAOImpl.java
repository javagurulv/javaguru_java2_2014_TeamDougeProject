package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CountByMonthsDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.CountByMonths;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 21.11.2014.
 */
public class CountByMonthsDAOImpl extends DAOImpl implements CountByMonthsDAO {

    private CountByMonths buildCountByMonths(ResultSet resultSet) throws SQLException {
        CountByMonths countByMonths = new CountByMonths();
        countByMonths.setCount(resultSet.getString("Count"));
        countByMonths.setMonthName(resultSet.getString("Month"));
        return countByMonths;
    }


    @Override
    public List<CountByMonths> geCountByMonths() throws DBException {
        List<CountByMonths> countByMonths = new ArrayList<CountByMonths>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT\n" +
                            "    MONTHNAME(rental_date) as Month,\n" +
                            "    COUNT(rental_id) as Count\n" +
                            "FROM rental\n" +
                            "GROUP BY\n" +
                            "    MONTHNAME(rental_date),\n" +
                            "    MONTH(rental_date)\n" +
                            "ORDER BY\n" +
                            "    MONTH(rental_date);");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                countByMonths.add(buildCountByMonths(resultSet));
            }
        }
        catch (Throwable e)
        {
            handleException(e,"CountByMonthsDAOImpl.geCountByMonths");

        }
        finally {
            closeConnection(connection);
        }

        return countByMonths;
    }
}
