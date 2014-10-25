package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Film;
import lv.javaguru.java2.domain.Inventory;
import lv.javaguru.java2.domain.Store;

import java.util.List;

/**
 * Created by Juris on 24.10.2014.
 */
public interface InventoryDAO {

    Inventory getByID(int inventory_id) throws DBException;

    void create(Inventory inventory) throws DBException;

    void delete(int inventory_id) throws DBException;

    void update(Inventory inventory) throws  DBException;

    List<Inventory> getAll() throws  DBException;

    List<Inventory> getAllForFilm(Film film) throws DBException;

    List<Inventory> getAllForStore(Store store) throws DBException;

}
