package lv.javaguru.java2.database.jdbc;


import lv.javaguru.java2.database.ChartDataDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.ChartData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergo on 09.12.2014.
 */
public class ChartDataDAOImpl extends DAOImpl implements ChartDataDAO {
    @Override
    public List<ChartData> getAll() throws DBException {
        return null;
    }

    @Override
    public List<ChartData> getByParams(Map<String, String> params) throws DBException {
        return null;
    }

    @Override
    public Integer getRecordsAmount() throws DBException {
        Integer result = 0;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(rental_id) as  records_count from data_united");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                result = resultSet.getInt("records_count");
            }

        }
        catch (Throwable e)
        {
            handleException(e, "ChartDataDAOImpl JDBC");
        }
        finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Object> getByQueryText(String queryText) throws DBException {
        /*List<Object[]> objects = new ArrayList<Object[]>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(queryText);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            while (resultSet.next())
            {
                Object[] obj = new Object[numberOfColumns];
                for (int i = 1; i <=numberOfColumns ; i++) {
                   obj[i-1] = resultSet.getObject(i);
                }
                objects.add(obj);
            }
        }
        catch (Throwable e)
        {
            handleException(e, "\"ChartDataDAOImpl JDBC , getByQueryText");
        }
        finally {
            closeConnection(connection);
        }

        return objects;*/
        return null;
    }
}
