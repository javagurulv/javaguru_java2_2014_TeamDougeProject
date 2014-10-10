package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.UserType;
import java.util.List;
/**
 * Created by Radchuk on 10/10/2014.
 */
public interface UserTypeDAO {
    void create(UserType userType) throws DBException;

    UserType getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(UserType userType) throws DBException;

    List<UserType> getAll() throws DBException;

}
