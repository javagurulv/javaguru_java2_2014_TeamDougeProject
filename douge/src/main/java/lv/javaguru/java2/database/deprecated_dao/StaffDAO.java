package lv.javaguru.java2.database.deprecated_dao;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.deprecated_classes.Address;
import lv.javaguru.java2.domain.deprecated_classes.Staff;
import lv.javaguru.java2.domain.deprecated_classes.Store;

import java.util.List;

/**
 * Created by Sergo on 27.10.2014.
 */
@Deprecated
public interface StaffDAO {
    void create(Staff staff) throws DBException;
    Staff getById(Short staff_id) throws DBException;
    void update(Staff staff) throws DBException;
    void delete(int staff_id) throws DBException;
    List<Staff> getAll() throws DBException;
    List<Staff> getAllForAddress(Address address) throws DBException;
    List<Staff> getAllForStore(Store store) throws DBException;


}
