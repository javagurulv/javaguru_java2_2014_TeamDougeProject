package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

/**
 * Created by user on 06-Dec-14.
 */
public interface UserDashboardDAO {

    public User getById(Long id) throws DBException;

}
