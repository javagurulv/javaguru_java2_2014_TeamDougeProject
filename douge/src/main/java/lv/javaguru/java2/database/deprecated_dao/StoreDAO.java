package lv.javaguru.java2.database.deprecated_dao;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.deprecated_classes.Store;

import java.util.List;

/**
 * Created by Radchuk Sergey on 20.10.2014.
 */
@Deprecated
public interface StoreDAO {

    Store getByID(int id) throws DBException;
    void create(Store store) throws DBException;
    void update(Store store) throws DBException;
    void delete(int id) throws DBException;

    List<Store> getAllByManagerStaffID(int id) throws DBException;
    List<Store> getAllByAddressId(int id) throws DBException;
    List<Store> getAll() throws DBException;
}
