package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.MetricSet;

import java.util.List;

/**
 * Created by Radchuk on 11/10/2014.
 */
public interface MetricSetDAO {
    void create(MetricSet metricSet) throws DBException;
    void delete(MetricSet metricSet) throws DBException;
    void update(MetricSet metricSet) throws DBException;
    MetricSet getById(Long id) throws DBException;
    //List<MetricSet> getAllByMetricSetId(Integer id) throws DBException;
    //List<MetricSet> getAllByMetricId(Integer id) throws DBException;
}
