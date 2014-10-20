package lv.javaguru.java2.database.jdbc;

/**
 * Created by Sergo on 20.10.2014.
 *
 *
 */
import static org.junit.Assert.*;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StoreDAO;
import lv.javaguru.java2.domain.Store;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class StoreDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private StoreDAO storeDAO = new StoreDAOImpl();

    private Store createStore(int addressId, int managerStaffId )
    {
        Store store = new Store();
        store.setAddress_id(addressId);
        store.setManager_staff_id(managerStaffId);
        store.setLast_update(new Date());
        return store;
    }

    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }

    @Before
    public void cleanDataBase() throws DBException
    {
        databaseCleaner.cleanDatabase();
    }
    @Test
    public void testCreate() throws DBException
    {
        Store store = createStore(12,15);
        storeDAO.create(store);
        Store storeFromDB = storeDAO.getByID(store.getStore_id());
        assertNotNull(storeFromDB);
        assertEquals(store.getAddress_id(), storeFromDB.getAddress_id());
        assertEquals(store.getManager_staff_id(), storeFromDB.getManager_staff_id());
        assertionEqualsDateCustom(store.getLast_update(), storeFromDB.getLast_update());

    }
}
