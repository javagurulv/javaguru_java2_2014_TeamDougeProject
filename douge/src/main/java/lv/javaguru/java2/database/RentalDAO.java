package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Customer;
import lv.javaguru.java2.domain.Inventory;
import lv.javaguru.java2.domain.Rental;
import lv.javaguru.java2.domain.Staff;

import java.util.List;

/**
 * Created by Juris on 25.10.2014.
 */
public interface RentalDAO {
    Rental getByID(int rental_id) throws DBException;

    void create(Rental rental) throws DBException;

    void delete(int rental_id) throws DBException;

    void update(Rental rental) throws  DBException;

    List<Rental> getAll() throws  DBException;

    List<Rental> getAllForInventory(Inventory inventory) throws DBException;

    List<Rental> getAllForCustomer(Customer customer) throws DBException;

    List<Rental> getAllForStaff(Staff staff) throws DBException;
}
