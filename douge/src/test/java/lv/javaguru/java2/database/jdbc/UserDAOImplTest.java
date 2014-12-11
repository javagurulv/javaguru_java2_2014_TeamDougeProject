package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.UserDAO;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;


public class UserDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private UserDAO userDAO = new UserDAOImpl();


    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        User user = createUser(0,"F", "L", "test_comment");

        userDAO.create(user);

        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
        assertEquals(user.getComments(), userFromDB.getComments());
        assertEquals(user.getUser_type(), userFromDB.getUser_type());
    }
/*
    @Test
    public void testMultipleUserCreation() throws DBException {
        User user1 = createUser(0,"F1", "L1","C1");
        User user2 = createUser(0,"F2", "L2","C2");
        userDAO.create(user1);
        userDAO.create(user2);
        List<User> users = userDAO.getAll();
        assertEquals(2, users.size());
    }
*/
    @Test
    public void testUserUpdate() throws DBException
    {
        User user  = createUser(0,"F", "L", "test_comment");
        userDAO.create(user);

        user.setLogin("TT");
        user.setPassword("PP");
        user.setUser_type(1);
        user.setComments("new comment");
        userDAO.update(user);
        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
        assertEquals(user.getComments(), userFromDB.getComments());
        assertEquals(user.getUser_type(), userFromDB.getUser_type());

    }

    @Test
    public void testUserDelete() throws DBException
    {
        User user  = createUser(0,"F", "L", "test_comment");
        userDAO.create(user);

        userDAO.delete(user.getUserId());

        User userFromDB = userDAO.getById(user.getUserId());
        assertNull(userFromDB);

    }


    private User createUser(int user_type,String login, String password, String comments) {
        User user = new User();
        user.setComments(comments);
        user.setLogin(login);
        user.setPassword(password);
        user.setUser_type(user_type);
        return user;
    }

}