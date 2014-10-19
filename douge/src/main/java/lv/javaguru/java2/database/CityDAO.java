package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.City;
import lv.javaguru.java2.domain.Country;

import java.util.List;

/**
 * Created by Juris on 19.10.2014.
 */
public interface CityDAO {

    void create(City city) throws DBException;

    City getById(Short city_id) throws  DBException;

    void update (City city) throws DBException;

    void delete (Short city_id) throws DBException;

    List<City> getAll() throws DBException;

    List<City> getAllForCountry(Country country) throws DBException;
}
