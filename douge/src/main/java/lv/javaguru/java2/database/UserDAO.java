package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.DomainWidgetContent;
import lv.javaguru.java2.domain.User;

import java.util.List;

/**
 * Created by Viktor on 01/07/2014.
 */
public interface UserDAO {

    void create(User user) throws DBException;

    User getById(Long id) throws DBException;

    User getByLogin(String login) throws DBException;

    void delete(Long id) throws DBException;

    void update(User user) throws DBException;

    List<DomainWidgetContent> getAll() throws DBException;

}
