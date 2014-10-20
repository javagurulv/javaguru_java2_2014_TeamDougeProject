package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.AddressDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Address;
import lv.javaguru.java2.domain.City;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Juris on 19.10.2014.
 */
public class AddressDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private AddressDAO addressDAO= new AddressDAOImpl();

    private Address createAddress(String address, String address2, String district, Short city_id, String postal_code, String phone, java.sql.Date lase_update) {
        Address address0 = new Address();
        address0.setAddress(address);
        address0.setAddress2(address2);
        address0.setDistrict(district);
        address0.setCity_id(city_id);
        address0.setPostal_code(postal_code);
        address0.setPhone(phone);
        address0.setLast_update(lase_update);

        return address0;
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

        Address address = createAddress("address", "address2", "district", (short) 1, "postalcode", "phone", sqlDate.valueOf(outputDate));

        addressDAO.create(address);
        Address addressFromDB = addressDAO.getById(address.getAddress_id());
        assertNotNull(addressFromDB);
        assertEquals(address.getAddress_id(), addressFromDB.getAddress_id());
        assertEquals(address.getAddress(), addressFromDB.getAddress());
        assertEquals(address.getAddress2(), addressFromDB.getAddress2());
        assertEquals(address.getDistrict(), addressFromDB.getDistrict());
        assertEquals(address.getCity_id(), addressFromDB.getCity_id());
        assertEquals(address.getPostal_code(), addressFromDB.getPostal_code());
        assertEquals(address.getPhone(), addressFromDB.getPhone());
        assertEquals(address.getLast_update(),addressFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Address address = createAddress("address", "address2", "district", (short) 1, "postalcode", "phone", sqlDate);
        addressDAO.create(address);
        Address addressFromDB = addressDAO.getById(address.getAddress_id());

        assertNotNull(addressFromDB);

        addressDAO.delete(address.getAddress_id());

        addressFromDB = addressDAO.getById(address.getAddress_id());

        assertNull(addressFromDB);
    }

    @Test
    public void testUpdate() throws DBException
    {

        /*избавляемся от времени в дате*/
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String inputDate=sqlDate.toString();
        String outputDate=inputDate.substring(0,10);

        Address address = createAddress("address", "address2", "district", (short) 1, "postalcode", "phone", sqlDate);
        addressDAO.create(address);

        address.setAddress("NewAddress");
        address.setAddress2("NewAddress2");
        address.setDistrict("NewDistrict");
        address.setCity_id((short) 2);
        address.setPostal_code("NewCode");
        address.setPhone("NewPhone");
        address.setLast_update(sqlDate.valueOf(outputDate));

        addressDAO.update(address);

        Address addressFromDB = addressDAO.getById(address.getAddress_id());

        assertNotNull(addressFromDB);
        assertEquals(address.getAddress_id(), addressFromDB.getAddress_id());
        assertEquals(address.getAddress(), addressFromDB.getAddress());
        assertEquals(address.getAddress2(), addressFromDB.getAddress2());
        assertEquals(address.getDistrict(), addressFromDB.getDistrict());
        assertEquals(address.getCity_id(), addressFromDB.getCity_id());
        assertEquals(address.getPostal_code(), addressFromDB.getPostal_code());
        assertEquals(address.getPhone(), addressFromDB.getPhone());
        assertEquals(address.getLast_update(),address.getLast_update());

    }

    @Test
    public void testGetAll() throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Address address1 = createAddress("address1", "address21", "district1", (short) 1, "postalcod1", "phone1", sqlDate);
        Address address2 = createAddress("address2", "address22", "district2", (short) 2, "postalcod2", "phone2", sqlDate);
        addressDAO.create(address1);
        addressDAO.create(address2);

        List<Address> addresses = addressDAO.getAll();

        assertEquals(addresses.size(),2);
    }

    @Test
    public void testGetAllForCity()throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Address address1 = createAddress("address1", "address21", "district1", (short) 1, "postalcod1", "phone1", sqlDate);
        addressDAO.create(address1);

        Address address2 = createAddress("address2", "address22", "district2", (short) 1, "postalcod2", "phone2", sqlDate);
        addressDAO.create(address2);

        Address address3 = createAddress("address3", "address23", "district3", (short) 1, "postalcod3", "phone3", sqlDate);
        addressDAO.create(address3);

        Address address4 = createAddress("address4", "address24", "district4", (short) 2, "postalcod4", "phone4", sqlDate);
        addressDAO.create(address4);


        City city=new City();

        city.setCity_id((short)1);

        List<Address> addresses = addressDAO.getAllForCity(city);

        assertEquals(addresses.size(), 3);

        city.setCity_id((short)2);

        addresses = addressDAO.getAllForCity(city);

        assertEquals(addresses.size(), 1);

    }
}
