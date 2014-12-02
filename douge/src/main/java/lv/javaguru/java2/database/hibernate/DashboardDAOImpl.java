package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 02-Dec-14.
 */
@Component("ORM_DashboardDAO")
public class DashboardDAOImpl implements DashboardDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Dashboard dashboard) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(dashboard);
    }

    @Override
    public Dashboard getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (Dashboard) session.get(Dashboard.class, id);
    }

    @Override
    public void update(Dashboard dashboard) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(dashboard);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Dashboard dashboard = (Dashboard) session.get(Dashboard.class, id);
        session.delete(dashboard);
    }

    @Override
    public List<Dashboard> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Dashboard.class).list();
    }

    @Override
    public List<Dashboard> getAllForUser(User user) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Dashboard WHERE user_id = :user_id");
        query.setParameter("user_id", user.getUserId());
        return query.list();
    }
}
