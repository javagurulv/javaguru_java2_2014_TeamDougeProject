package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricDAO;
import lv.javaguru.java2.domain.Metric;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radchuk on 11/10/2014.
 */
@Component("JDBC_MetricDAO")
public class MetricDAOImpl extends DAOImpl implements MetricDAO {
    @Override
    public void create(Metric metric) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO METRICS(id, type, name, compatibility)" +
                            " VALUES (default ,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,metric.getType());
            preparedStatement.setString(2,metric.getName());
            preparedStatement.setLong(3,metric.getCompatibility());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next())
            {
                metric.setId( resultSet.getLong(1));
            }
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricDAOImpl.create()");
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM METRICS WHERE ID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricDAOImpl.delete()");
        }
        finally {
            closeConnection(connection);
        }


    }

    @Override
    public void update(Metric metric) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE METRICS SET TYPE = ?, NAME = ?, COMPATIBILITY = ? " +
                            "WHERE ID = ?");
            preparedStatement.setString(1,metric.getType());
            preparedStatement.setString(2,metric.getName());
            preparedStatement.setLong(3,metric.getCompatibility());
            preparedStatement.setLong(4, metric.getId());
            preparedStatement.executeUpdate();

        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricDAOImpl.update()");
        }
        finally {
            closeConnection(connection);
        }

    }

    private Metric buildMetric(ResultSet resultSet) throws SQLException {
        Metric metric = new Metric();
        metric.setId(resultSet.getLong("id"));
        metric.setType(resultSet.getString("type"));
        metric.setName(resultSet.getString("name"));
        metric.setCompatibility( resultSet.getLong("compatibility"));
        return metric;
    }

    @Override
    public Metric getById(Long id) throws DBException {
        Metric metric = null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id,type,name,compatibility from METRICS where id = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                metric = buildMetric(resultSet);
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricDAOImpl.getById()");
        }
        finally {
            closeConnection(connection);
        }


        return metric;
    }

    @Override
    public List<Metric> getAll() throws DBException {
        List<Metric> metrics = new ArrayList<Metric>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id,type,name,compatibility from METRICS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                metrics.add(buildMetric(resultSet));
            }

        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricDAOImpl.getAll()");
        }
        finally {
            closeConnection(connection);
        }
        return metrics;
    }

    @Override
    public List<Metric> getAllByType(String type) throws DBException {
        return null;
    }

    @Override
    public void detachMetricFromSession(Metric metric) {
        ;
    }
}
