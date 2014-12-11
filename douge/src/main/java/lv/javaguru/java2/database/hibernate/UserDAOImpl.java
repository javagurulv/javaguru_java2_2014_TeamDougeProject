package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.DomainWidgetContent;
import lv.javaguru.java2.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user on 02-Dec-14.
 */
@Component("ORM_UserDAO")
@Transactional
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public User getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.get(User.class, id);
    }

    @Override
    public User getByLogin(String login) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
    }

    @Override
    public void delete(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
    }

    @Override
    public void update(User user) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public List<DomainWidgetContent> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(User.class).list();
    }
}
