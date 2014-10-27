package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Address;
import lv.javaguru.java2.domain.Staff;
import lv.javaguru.java2.domain.Store;

import java.util.List;

/**
 * Created by Sergo on 27.10.2014.
 */
public interface StaffDAO {
    void create(Staff staff) throws DBException;
    Staff getById(Short staff_id) throws DBException;
    void update(Staff staff) throws DBException;
    void delete(int staff_id) throws DBException;
    List<Staff> getAll() throws DBException;
    List<Staff> getAllForAddress(Address address) throws DBException;
    List<Staff> getAllForStore(Store store) throws DBException;


}
