package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.InventoryDAO;
import lv.javaguru.java2.domain.Film;
import lv.javaguru.java2.domain.Inventory;
import lv.javaguru.java2.domain.Store;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Juris on 24.10.2014.
 */

public class InventoryDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private InventoryDAO inventoryDAO= new InventoryDAOImpl();

    private Inventory createInventory(Short film_id, int store_id) {
        Inventory inventory = new Inventory();
        inventory.setFilm_id(film_id);
        inventory.setStore_id(store_id);
        inventory.setLast_update(new Date());

        return inventory;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        Inventory inventory = createInventory((short)1,1);

        inventoryDAO.create(inventory);
        Inventory inventoryFromDB = inventoryDAO.getByID(inventory.getInventory_id());
        assertNotNull(inventoryFromDB);
        assertEquals(inventory.getFilm_id(), inventoryFromDB.getFilm_id());
        assertEquals(inventory.getStore_id(), inventoryFromDB.getStore_id());
        assertionEqualsDateCustom(inventory.getLast_update(), inventory.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        Inventory inventory = createInventory((short) 1,1);
        inventoryDAO.create(inventory);
        Inventory inventoryFromDB = inventoryDAO.getByID(inventory.getInventory_id());

        assertNotNull(inventoryFromDB);

        inventoryDAO.delete(inventory.getInventory_id());

        inventoryFromDB = inventoryDAO.getByID(inventory.getInventory_id());

        assertNull(inventoryFromDB);
    }

    @Test
    public void testUpdate() throws DBException
    {
        Inventory inventory = createInventory((short)1,1);
        inventoryDAO.create(inventory);

        inventory.setFilm_id((short)2);
        inventory.setStore_id(2);
        inventory.setLast_update(new Date());

        inventoryDAO.update(inventory);

        Inventory inventoryFromDB = inventoryDAO.getByID(inventory.getInventory_id());

        assertNotNull(inventoryFromDB);
        assertEquals(inventory.getFilm_id(), inventoryFromDB.getFilm_id());
        assertEquals(inventory.getStore_id(), inventoryFromDB.getStore_id());
        assertionEqualsDateCustom(inventory.getLast_update(),inventoryFromDB.getLast_update());
    }

    @Test
    public void testGetAll() throws DBException
    {
        Inventory inventory1 = createInventory((short) 1, 1);
        Inventory inventory2 = createInventory((short) 2, 2);
        inventoryDAO.create(inventory1);
        inventoryDAO.create(inventory2);

        List<Inventory> inventories = inventoryDAO.getAll();

        assertEquals(inventories.size(),2);
    }

    @Test
    public void testGetAllForCategory()throws DBException
    {
        Inventory inventory1 = createInventory((short) 1, 1);
        inventoryDAO.create(inventory1);

        Inventory inventory2 = createInventory((short) 1, 2);
        inventoryDAO.create(inventory2);

        Inventory inventory3 = createInventory((short) 1, 3);
        inventoryDAO.create(inventory3);

        Inventory inventory4 = createInventory((short) 2, 4);
        inventoryDAO.create(inventory4);


        Film film=new Film();

        film.setFilm_id((short) 1);

        List<Inventory> inventories = inventoryDAO.getAllForFilm(film);

        assertEquals(inventories.size(), 3);

        film.setFilm_id((short) 2);

        inventories = inventoryDAO.getAllForFilm(film);

        assertEquals(inventories.size(), 1);

    }

    @Test
    public void testGetAllForStore()throws DBException
    {
        Inventory inventory1 = createInventory((short) 1, 1);
        inventoryDAO.create(inventory1);

        Inventory inventory2 = createInventory((short) 2, 1);
        inventoryDAO.create(inventory2);

        Inventory inventory3 = createInventory((short) 3, 1);
        inventoryDAO.create(inventory3);

        Inventory inventory4 = createInventory((short) 4, 2);
        inventoryDAO.create(inventory4);


        Store store=new Store();

        store.setStore_id(1);

        List<Inventory> inventories = inventoryDAO.getAllForStore(store);

        assertEquals(inventories.size(), 3);

        store.setStore_id(2);

        inventories = inventoryDAO.getAllForStore(store);

        assertEquals(inventories.size(), 1);

    }

    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }
}
