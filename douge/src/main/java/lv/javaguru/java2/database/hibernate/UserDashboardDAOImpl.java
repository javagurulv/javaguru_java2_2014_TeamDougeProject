package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDashboardDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 06-Dec-14.
 */
@Component("ORM_UserDashboardDAO")
public class UserDashboardDAOImpl implements UserDashboardDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getById(Long id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.get(User.class, id);
    }
}
