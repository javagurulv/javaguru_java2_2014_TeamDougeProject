package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by user on 02-Dec-14.
 */
public class UserDAOImplTest extends SpringIntegrationTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void createNewUser() throws DBException {
        User user = createUser(0, "Login1", "Password1", "Comment1");

        assertNull(user.getUserId());
        userDAO.create(user);
        assertNotNull(user.getUserId());
    }

    @Test
    @Transactional
    public void getUserByLogin() throws DBException {
        User user = createUser(1, "Login2", "Passord2", "Comment2");
        userDAO.create(user);

        User userFromDB = userDAO.getByLogin("Login2");
        assertEquals(user.getLogin(), userFromDB.getLogin());
    }

    private User createUser(int user_type, String login, String password, String comments){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setUser_type(user_type);
        user.setComments(comments);
        return user;
    }


}
