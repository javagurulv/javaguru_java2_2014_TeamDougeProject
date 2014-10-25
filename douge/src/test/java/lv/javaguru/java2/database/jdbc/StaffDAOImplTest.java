package lv.javaguru.java2.database.jdbc;

import com.mysql.jdbc.Blob;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StaffDAO;
import lv.javaguru.java2.domain.Inventory;
import lv.javaguru.java2.domain.Staff;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Juris on 25.10.2014.
 */
public class StaffDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private StaffDAO staffDA= new StaffDAOImpl();

    private Staff createStaff(String first_name, String last_name, Short address_id, Blob picture, String email, int store_id, int active, String username, String password) {
        Staff staff = new Staff();
        staff.setFirst_name(first_name);
        staff.setLast_name(last_name);
        staff.setAddress_id((address_id));
        staff.setPicture(picture);
        staff.setEmail(email);
        staff.setStore_id(store_id);
        staff.setActive(active);
        staff.setUsername(username);
        staff.setPassword(password);
        staff.setLast_update(new Date());

        return staff;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        Staff staff = createStaff("first_name", "last_name", (short) 1, "picture", "email", 1, 1, "username", "password");

    }

    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }
}
