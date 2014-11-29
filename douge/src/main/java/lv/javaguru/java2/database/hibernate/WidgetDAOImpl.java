package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.WidgetDAO;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.Widget;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Juris on 29.11.2014.
 */
@Component("ORM_WidgetDAO")
public class WidgetDAOImpl implements WidgetDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Widget getByID(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (Widget) session.get(Widget.class, id);
    }

    @Override
    public void create(Widget widget) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(widget);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Widget widget = (Widget) session.get(Widget.class, id);
        session.delete(widget);
    }

    @Override
    public void update(Widget widget) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(widget);
    }

    @Override
    public List<Widget> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Widget.class).list();
    }

    @Override
    public List<Widget> getAllForDashboard(Dashboard dashboard) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Widget where dashboard_id = :dashboard_id");
        query.setParameter("dashboard_id", dashboard.getId());
        return query.list();
    }

}
