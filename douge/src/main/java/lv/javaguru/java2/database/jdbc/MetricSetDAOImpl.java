package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricSetDAO;
import lv.javaguru.java2.domain.Metric;
import lv.javaguru.java2.domain.MetricSet;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radchuk on 11/10/2014.
 */
@Component
public class MetricSetDAOImpl extends DAOImpl implements MetricSetDAO {
    @Override
    public void create(MetricSet metricSet) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO METRICS_SETS(id, metric_id) VALUES (?,?)");
            preparedStatement.setInt(1,metricSet.getId());
            preparedStatement.setInt(2,metricSet.getMetric_id());
            preparedStatement.executeUpdate();
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricSetDAOImpl.create()");
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(MetricSet metricSet) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM METRICS_SETS where id = ? and metric_id = ?");
            preparedStatement.setInt(1,metricSet.getId());
            preparedStatement.setInt(2, metricSet.getMetric_id());
            preparedStatement.executeUpdate();
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricSetDAOImpl.delete()");
        }
        finally {
            closeConnection(connection);
        }

    }



    @Override
    public List<MetricSet> getAllByMetricSetId(Integer id) throws DBException {
        Connection connection = null;
        List<MetricSet> metricSets = new ArrayList<MetricSet>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id, metric_id from metrics_sets where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                MetricSet metricSet = new MetricSet();
                metricSet.setId(resultSet.getInt("id"));
                metricSet.setMetric_id(resultSet.getInt("metric_id"));
                metricSets.add(metricSet);
            }
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricSetDAOImpl.getAllByMetricSetId()");
        }
        finally {
            closeConnection(connection);
        }
        return metricSets;
    }

    @Override
    public List<MetricSet> getAllByMetricId(Integer id) throws DBException {
        Connection connection = null;
        List<MetricSet> metricSets = new ArrayList<MetricSet>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id, metric_id from metrics_sets where metric_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                MetricSet metricSet = new MetricSet();
                metricSet.setId(resultSet.getInt("id"));
                metricSet.setMetric_id(resultSet.getInt("metric_id"));
                metricSets.add(metricSet);
            }
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute MetricSetDAOImpl.getAllByMetricId()");
        }
        finally {
            closeConnection(connection);
        }
        return metricSets;
    }
}
