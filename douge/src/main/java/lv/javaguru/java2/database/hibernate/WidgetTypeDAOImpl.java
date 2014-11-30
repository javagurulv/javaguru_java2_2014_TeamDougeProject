package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.WidgetTypeDAO;
import lv.javaguru.java2.domain.WidgetType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Juris on 30.11.2014.
 */
@Component("ORM_WidgetTypeDAO")
public class WidgetTypeDAOImpl implements WidgetTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(WidgetType widgetType) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(widgetType);
    }

    @Override
    public WidgetType getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (WidgetType) session.get(WidgetType.class, id);
    }

    @Override
    public void update(WidgetType widgetType) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(widgetType);
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        WidgetType widgetType = (WidgetType) session.get(WidgetType.class, id);
        session.delete(widgetType);
    }

    @Override
    public List<WidgetType> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(WidgetType.class).list();
    }
}
