package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Country;

import java.util.List;

/**
 * Created by Sergo on 18.10.2014.
 */
public interface CountryDAO {
    void create(Country country) throws DBException;

    Country getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(Country country) throws DBException;

    List<Country> getAll() throws DBException;
}
