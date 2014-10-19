package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import lv.javaguru.java2.database.CityDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;
import lv.javaguru.java2.domain.City;
import lv.javaguru.java2.domain.Country;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * Created by Juris on 19.10.2014.
 */
public class CityDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CityDAO cityDAO= new CityDAOImpl();

    private City createCity(String city, Short country_id, java.sql.Date lase_update) {
        City city0 = new City();
        city0.setCity(city);
        city0.setCountry_id(country_id);
        city0.setLast_update(lase_update);

        return city0;
    }



    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        /*избаляемся от времени в дате*/
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String inputDate=sqlDate.toString();
        String outputDate=inputDate.substring(0,10);

        City city = createCity("city",(short)1, sqlDate.valueOf(outputDate));

        cityDAO.create(city);
        City cityFromDB = cityDAO.getById(city.getCity_id());
        assertNotNull(cityFromDB);
        assertEquals(city.getCity(), cityFromDB.getCity());
        assertEquals(city.getCity_id(), cityFromDB.getCity_id());
        assertEquals(city.getCountry_id(), cityFromDB.getCountry_id());
        assertEquals(city.getLast_update(),cityFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        City city = createCity("City",(short)2, sqlDate);
        cityDAO.create(city);
        City cityFromDB = cityDAO.getById(city.getCity_id());

        assertNotNull(cityFromDB);

        cityDAO.delete(city.getCity_id());

        cityFromDB = cityDAO.getById(city.getCity_id());

        assertNull(cityFromDB);
    }

    @Test
    public void testUpdate() throws DBException
    {

        /*избавляемся от времени в дате*/
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String inputDate=sqlDate.toString();
        String outputDate=inputDate.substring(0,10);

        City city = createCity("City", (short) 1, sqlDate.valueOf(outputDate));
        cityDAO.create(city);

        city.setCity("Newcity");
        city.setCountry_id((short) 2);
        city.setLast_update(sqlDate.valueOf(outputDate));

        cityDAO.update(city);

        City cityFromDB = cityDAO.getById(city.getCity_id());

        assertNotNull(cityFromDB);
        assertEquals(city.getCity(), cityFromDB.getCity());
        assertEquals(city.getCountry_id(), cityFromDB.getCountry_id());
        assertEquals(city.getCity_id(), cityFromDB.getCity_id());
        assertEquals(city.getLast_update(),cityFromDB.getLast_update());

    }

    @Test
    public void testGetAll() throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        City city1 = createCity("city1", (short) 1, sqlDate);
        City city2 = createCity("city2", (short) 2, sqlDate);
        cityDAO.create(city1);
        cityDAO.create(city2);

        List<City> cities = cityDAO.getAll();

        assertEquals(cities.size(),2);
    }

    @Test
    public void testGetAllForCountry()throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        City city1 = createCity("City1", (short) 1, sqlDate);
        cityDAO.create(city1);

        City city2 = createCity("city2", (short) 1, sqlDate);
        cityDAO.create(city2);

        City city3 = createCity("city3", (short) 1, sqlDate);
        cityDAO.create(city3);

        City city4 = createCity("city4", (short) 2, sqlDate);
        cityDAO.create(city4);


        Country country=new Country();

        country.setCountry_id(1);

        List<City> cities = cityDAO.getAllForCountry(country);

        assertEquals(cities.size(), 3);

        country.setCountry_id(2);

        cities = cityDAO.getAllForCountry(country);

        assertEquals(cities.size(), 1);

    }

}
