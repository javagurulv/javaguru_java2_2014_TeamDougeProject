package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricDAO;
import lv.javaguru.java2.domain.Metric;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 30-Nov-14.
 */
@Component("ORM_MetricDAO")
public class MetricDAOImpl implements MetricDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Metric getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (Metric) session.get(Metric.class, id);
    }

    @Override
    public void create(Metric metric) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(metric);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Metric metric = (Metric) session.get(Metric.class, id);
        session.delete(metric);
    }

    @Override
    public void update(Metric metric) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(metric);
    }

    @Override
    public List<Metric> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Metric.class).list();
    }
}
