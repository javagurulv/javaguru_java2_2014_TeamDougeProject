package lv.javaguru.java2.database.jdbc.deprecated_tests;

import static org.junit.Assert.*;

import lv.javaguru.java2.database.deprecated_dao.CityDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.deprecated_implementation.CityDAOImpl;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.deprecated_classes.City;
import lv.javaguru.java2.domain.deprecated_classes.Country;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Juris on 19.10.2014.
 */
@Ignore
public class CityDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CityDAO cityDAO= new CityDAOImpl();

    private City createCity(String city, Short country_id) {
        City city0 = new City();
        city0.setCity(city);
        city0.setCountry_id(country_id);
        city0.setLast_update(new Date());

        return city0;
    }



    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        City city = createCity("city",(short)1);

        cityDAO.create(city);
        City cityFromDB = cityDAO.getById(city.getCity_id());
        assertNotNull(cityFromDB);
        assertEquals(city.getCity(), cityFromDB.getCity());
        assertEquals(city.getCity_id(), cityFromDB.getCity_id());
        assertEquals(city.getCountry_id(), cityFromDB.getCountry_id());
        assertionEqualsDateCustom(city.getLast_update(),cityFromDB.getLast_update());

    }

    @Test
    public void testDelete() throws DBException
    {
        City city = createCity("City",(short)2);
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

        City city = createCity("City", (short) 1);
        cityDAO.create(city);

        city.setCity("Newcity");
        city.setCountry_id((short) 2);
        city.setLast_update(new Date());

        cityDAO.update(city);

        City cityFromDB = cityDAO.getById(city.getCity_id());

        assertNotNull(cityFromDB);
        assertEquals(city.getCity(), cityFromDB.getCity());
        assertEquals(city.getCountry_id(), cityFromDB.getCountry_id());
        assertEquals(city.getCity_id(), cityFromDB.getCity_id());
        assertionEqualsDateCustom(city.getLast_update(),cityFromDB.getLast_update());

    }

    @Test
    public void testGetAll() throws DBException
    {

        City city1 = createCity("city1", (short) 1);
        City city2 = createCity("city2", (short) 2);
        cityDAO.create(city1);
        cityDAO.create(city2);

        List<City> cities = cityDAO.getAll();

        assertEquals(cities.size(),2);
    }

    @Test
    public void testGetAllForCountry()throws DBException
    {

        City city1 = createCity("City1", (short) 1);
        cityDAO.create(city1);

        City city2 = createCity("city2", (short) 1);
        cityDAO.create(city2);

        City city3 = createCity("city3", (short) 1);
        cityDAO.create(city3);

        City city4 = createCity("city4", (short) 2);
        cityDAO.create(city4);


        Country country=new Country();

        country.setCountry_id(1);

        List<City> cities = cityDAO.getAllForCountry(country);

        assertEquals(cities.size(), 3);

        country.setCountry_id(2);

        cities = cityDAO.getAllForCountry(country);

        assertEquals(cities.size(), 1);

    }

    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }

}
