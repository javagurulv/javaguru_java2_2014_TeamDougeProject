package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Store;

import java.util.List;

/**
 * Created by Radchuk Sergey on 20.10.2014.
 */
public interface StoreDAO {

    Store getByID(int id) throws DBException;
    void create(Store store) throws DBException;
    void update(Store store) throws DBException;
    void delete(in id) throws DBException;

    List<Store> getAllByManagerStaffID(int id) throws DBException;
    List<Store> getAllByAddressId(int id) throws DBException;
    List<Store> getAll() throws DBException;
}
