package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.AmountRentalCountDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.AmountRentalCount;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 21.11.2014.
 */
@Component
public class AmountRentalCountDAOImpl extends DAOImpl implements AmountRentalCountDAO {


    private AmountRentalCount buildAmountRentalCount(ResultSet resultSet) throws SQLException {
        AmountRentalCount amountRentalCount = new AmountRentalCount();
        amountRentalCount.setAmount(resultSet.getDouble("Amount"));
        amountRentalCount.setFilmCategory(resultSet.getString("FilmCategory"));
        amountRentalCount.setRentalCount(resultSet.getInt("RentalCount"));
        return amountRentalCount;
    }


    @Override
    public List<AmountRentalCount> getAmountRentalCountData() throws DBException {
        List<AmountRentalCount> amountRentalCounts = new ArrayList<AmountRentalCount>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT\n" +
                            "    c.name AS FilmCategory,\n" +
                            "    SUM(p.amount) AS Amount,\n" +
                            "    COUNT(r.rental_id) AS RentalCount\n" +
                            "FROM payment p\n" +
                            "LEFT JOIN rental r\n" +
                            "    ON r.rental_id = p.payment_id\n" +
                            "LEFT JOIN inventory i\n" +
                            "    ON i.inventory_id = r.inventory_id\n" +
                            "LEFT JOIN film f\n" +
                            "    ON f.film_id = i.film_id\n" +
                            "LEFT JOIN film_category fc\n" +
                            "    ON fc.film_id = i.film_id\n" +
                            "LEFT JOIN category c\n" +
                            "    ON c.category_id = fc.category_id\n" +
                            "GROUP BY\n" +
                            "    c.name\n" +
                            "ORDER BY\n" +
                            "    SUM(p.amount) DESC\n" +
                            "LIMIT 6");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                amountRentalCounts.add(buildAmountRentalCount(resultSet));
            }
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute AmountRentalCountDAOImpl.getAmountRentalCountData()");
        }
        finally {
            closeConnection(connection);
        }

        return amountRentalCounts;
    }
}
