package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricSetDAO;
import lv.javaguru.java2.domain.MetricSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by user on 30-Nov-14.
 */

@Component("ORM_MetricSetDAO")
public class MetricSetDAOImpl implements MetricSetDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(MetricSet metricSet) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(metricSet);
    }

    @Override
    public void delete(MetricSet metricSet) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(metricSet);
    }

    @Override
    public MetricSet getById(Integer id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (MetricSet) session.get(MetricSet.class, id);
    }

    /*
    @Override
    public List<MetricSet> getAllByMetricSetId(Integer id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM MetricSet WHERE id = :id");
        query.setParameter("id", id);
        return query.list();
    }

    @Override
    public List<MetricSet> getAllByMetricId(Integer id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM MetricSet WHERE metric_id = :id");
        query.setParameter("metric_id", id);
        return query.list();
    }
    */
}
