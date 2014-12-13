package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Metric;

import java.util.List;

/**
 * Created by Radchuk on 11/10/2014.
 */
public interface MetricDAO {

    void create(Metric metric) throws DBException;
    void delete(Long id) throws DBException;
    void update(Metric metric) throws DBException;
    Metric getById(Long id) throws DBException;
    List<Metric> getAll() throws DBException;
    List<Metric> getAllByType(String type) throws DBException;
    void detachMetricFromSession(Metric metric);

}
