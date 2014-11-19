package lv.javaguru.java2.database.jdbc.deprecated_tests;

/**
 * Created by Sergo on 20.10.2014.
 *
 *
 */
import static org.junit.Assert.*;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StoreDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.StoreDAOImpl;
import lv.javaguru.java2.domain.Store;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Ignore
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

    @Test
    public void testUpdate() throws DBException
    {
        Store store = createStore(12,15);
        storeDAO.create(store);
        store.setAddress_id(88);
        store.setManager_staff_id(99);
        storeDAO.update(store);

        Store storeFromDB = storeDAO.getByID(store.getStore_id());
        assertNotNull(storeFromDB);
        assertEquals(store.getAddress_id(), storeFromDB.getAddress_id());
        assertEquals(store.getManager_staff_id(), storeFromDB.getManager_staff_id());
        assertionEqualsDateCustom(store.getLast_update(), storeFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        Store store = createStore(12,15);
        storeDAO.create(store);
        storeDAO.delete(store.getStore_id());

        Store storeFromDB = storeDAO.getByID(store.getStore_id());
        assertNull(storeFromDB);

    }

    @Test
    public void testGetAll() throws DBException
    {
        Store store = createStore(12,15);
        storeDAO.create(store);
        Store store1 = createStore(13,18);
        storeDAO.create(store1);
        List<Store> storeList = storeDAO.getAll();
        assertEquals(storeList.size(), 2);
    }

    @Test
    public void testGetAllByAddressId() throws DBException
    {
        Store store = createStore(12,15);
        storeDAO.create(store);
        Store store1 = createStore(13,18);
        storeDAO.create(store1);
        List<Store> storeList = storeDAO.getAllByAddressId(12);
        assertEquals(storeList.size(), 1);
    }

    public void testGetAllByManagerStaffId() throws DBException
    {
        Store store = createStore(12,15);
        storeDAO.create(store);
        Store store1 = createStore(13,18);
        storeDAO.create(store1);
        List<Store> storeList = storeDAO.getAllByManagerStaffID(18);
        assertEquals(storeList.size(), 1);
    }
}
