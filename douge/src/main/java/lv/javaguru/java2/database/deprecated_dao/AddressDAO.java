package lv.javaguru.java2.database.deprecated_dao;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.deprecated_classes.Address;
import lv.javaguru.java2.domain.deprecated_classes.City;

import java.util.List;

/**
 * Created by Juris on 19.10.2014.
 */
@Deprecated
public interface AddressDAO {
    void create(Address address) throws DBException;

    Address getById(Short address_id) throws  DBException;

    void update (Address address) throws DBException;

    void delete (Short address_id) throws DBException;

    List<Address> getAll() throws DBException;

    List<Address> getAllForCity(City city) throws DBException;
}
