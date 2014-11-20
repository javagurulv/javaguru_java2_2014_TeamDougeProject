package lv.javaguru.java2.database.deprecated_dao;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.deprecated_classes.Customer;
import lv.javaguru.java2.domain.deprecated_classes.Inventory;
import lv.javaguru.java2.domain.deprecated_classes.Rental;
import lv.javaguru.java2.domain.deprecated_classes.Staff;

import java.util.List;

/**
 * Created by Juris on 25.10.2014.
 */
@Deprecated
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
