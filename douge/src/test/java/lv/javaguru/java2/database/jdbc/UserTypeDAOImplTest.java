package lv.javaguru.java2.database.jdbc;

/**
 * Created by Sergo on 12.10.2014.
 */

import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.UserTypeDAO;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.UserType;

public class UserTypeDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private UserTypeDAO userTypeDAO = new UserTypeDAOImpl();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        UserType userType = createUserType("test type");

        userTypeDAO.create(userType);

        UserType userTypeFromDB = userTypeDAO.getById(userType.getTypeId());
        assertNotNull(userTypeFromDB);
        assertEquals(userType.getTypeId(), userTypeFromDB.getTypeId());
        assertEquals(userType.getTypeName(), userTypeFromDB.getTypeName());
    }


    @Test
    public void testDelete() throws DBException
    {
        UserType userType = createUserType("test type");

        userTypeDAO.create(userType);
        userTypeDAO.delete(userType.getTypeId());
        UserType userTypeFromDB = userTypeDAO.getById(userType.getTypeId());
        assertNull(userTypeFromDB);

    }

    @Test
    public void testUpdate() throws DBException
    {
        UserType userType = createUserType("test type");

        userTypeDAO.create(userType);

        userType.setTypeName("test type 2");
        userTypeDAO.update(userType);
        UserType userTypeFromDB = userTypeDAO.getById(userType.getTypeId());

        assertNotNull(userTypeFromDB);
        assertEquals(userType.getTypeId(), userTypeFromDB.getTypeId());
        assertEquals(userType.getTypeName(), userTypeFromDB.getTypeName());

    }

    @Test

    public void testMultipleInsert() throws DBException
    {
        UserType userType = createUserType("test type");
        UserType userType1 = createUserType("test type1");
        userTypeDAO.create(userType);
        userTypeDAO.create(userType1);
        List<UserType> userTypes = userTypeDAO.getAll();
        assertEquals(userTypes.size(),2);
    }

    private UserType createUserType(String name)
    {
        UserType userType = new UserType();
        userType.setTypeName(name);
        return userType;
    }

}
