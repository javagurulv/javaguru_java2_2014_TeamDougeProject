package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.domain.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 30-Nov-14.
 */
@Component("ORM_LanguageDAO")
public class LanguageDAOImpl implements LanguageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Language getById(int id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return (Language) session.get(Language.class, id);
    }

    @Override
    public void create(Language language) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(language);
    }

    @Override
    public void delete(int id) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        Language language = (Language) session.get(Language.class, id);
        session.delete(language);
    }

    @Override
    public void update(Language language) throws DBException {
        Session session = sessionFactory.getCurrentSession();
        session.update(language);
    }

    @Override
    public List<Language> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Language.class).list();
    }
}
