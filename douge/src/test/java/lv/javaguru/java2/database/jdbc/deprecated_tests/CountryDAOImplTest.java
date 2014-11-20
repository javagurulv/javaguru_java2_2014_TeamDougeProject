package lv.javaguru.java2.database.jdbc.deprecated_tests;

/**
 * Created by Sergo on 18.10.2014.
 */

import static org.junit.Assert.*;

import lv.javaguru.java2.database.deprecated_dao.CountryDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.deprecated_implementation.CountryDAOImpl;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.deprecated_classes.Country;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Ignore
public class CountryDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CountryDAO countryDAO = new CountryDAOImpl();

    private Country createCountry(String country_name)
    {
        Country country = new Country();
        country.setCountry(country_name);
        country.setLast_update(new Date());
        return country;
    }

    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }

    @Before
    public void  cleanData() throws DBException
    {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        Country country = createCountry("aaa");
        countryDAO.create(country);

        Country countryFromDB = countryDAO.getById((long) country.getCountry_id());

        assertNotNull(countryFromDB);
        assertEquals(country.getCountry(), countryFromDB.getCountry());
        assertionEqualsDateCustom(country.getLast_update(), countryFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        Country country = createCountry("aaa");
        countryDAO.create(country);

        Country countryFromDB = countryDAO.getById((long) country.getCountry_id());

        assertNotNull(countryFromDB);

        countryDAO.delete((long) country.getCountry_id());
        countryFromDB = countryDAO.getById((long) country.getCountry_id());

        assertNull(countryFromDB);

    }

    public void testUpdate() throws DBException
    {
        Country country = createCountry("aaa");
        countryDAO.create(country);
        country.setLast_update(new Date());
        country.setCountry("ppppppp");
        countryDAO.update(country);
        Country countryFromDB = countryDAO.getById((long) country.getCountry_id());
        assertNotNull(countryFromDB);
        assertEquals(country.getCountry(), countryFromDB.getCountry());
        assertionEqualsDateCustom(country.getLast_update(), countryFromDB.getLast_update());

    }

    @Test
    public void testGetAll() throws DBException
    {
        Country country = createCountry("aaa");
        countryDAO.create(country);
        Country country1 = createCountry("bbb");
        countryDAO.create(country1);
        Country country3 = createCountry("ccc");
        countryDAO.create(country3);
        List<Country> countryList = countryDAO.getAll();
        assertEquals(countryList.size(),3);
    }

}
