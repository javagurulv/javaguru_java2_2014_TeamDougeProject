package lv.javaguru.java2.database;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Customer;

import java.util.List;

/**
 * Created by Sergo on 18.10.2014.
 */
public interface CustomerDAO {
    void create(Customer customer)throws DBException;
    void delete(int id) throws DBException;
    void update(Customer customer)throws DBException;

    Customer getById(int id) throws DBException;
    List<Customer> getAll() throws DBException;
    List<Customer> getAllByStoreID(int id) throws DBException;
    List<Customer> getAllByAddressID(int id) throws DBException;

}
