package lv.javaguru.java2.database.deprecated_dao;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.deprecated_classes.City;
import lv.javaguru.java2.domain.deprecated_classes.Country;

import java.util.List;

/**
 * Created by Juris on 19.10.2014.
 */
@Deprecated
public interface CityDAO {

    void create(City city) throws DBException;

    City getById(Short city_id) throws  DBException;

    void update (City city) throws DBException;

    void delete (Short city_id) throws DBException;

    List<City> getAll() throws DBException;

    List<City> getAllForCountry(Country country) throws DBException;
}
