package lv.javaguru.java2.database.jdbc.deprecated_tests;

import lv.javaguru.java2.database.deprecated_dao.AddressDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.deprecated_implementation.AddressDAOImpl;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.deprecated_classes.Address;
import lv.javaguru.java2.domain.deprecated_classes.City;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Juris on 19.10.2014.
 */
@Ignore
public class AddressDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private AddressDAO addressDAO= new AddressDAOImpl();

    private Address createAddress(String address, String address2, String district, Short city_id, String postal_code, String phone) {
        Address address0 = new Address();
        address0.setAddress(address);
        address0.setAddress2(address2);
        address0.setDistrict(district);
        address0.setCity_id(city_id);
        address0.setPostal_code(postal_code);
        address0.setPhone(phone);
        address0.setLast_update(new Date());

        return address0;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        Address address = createAddress("address", "address2", "district", (short) 1, "postalcode", "phone");

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
        assertionEqualsDateCustom(address.getLast_update(), address.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        Address address = createAddress("address", "address2", "district", (short) 1, "postalcode", "phone");
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

        Address address = createAddress("address", "address2", "district", (short) 1, "postalcode", "phone");
        addressDAO.create(address);

        address.setAddress("NewAddress");
        address.setAddress2("NewAddress2");
        address.setDistrict("NewDistrict");
        address.setCity_id((short) 2);
        address.setPostal_code("NewCode");
        address.setPhone("NewPhone");
        address.setLast_update(new Date());

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
        assertionEqualsDateCustom(address.getLast_update(), address.getLast_update());

    }

    @Test
    public void testGetAll() throws DBException
    {
        Address address1 = createAddress("address1", "address21", "district1", (short) 1, "postalcod1", "phone1");
        Address address2 = createAddress("address2", "address22", "district2", (short) 2, "postalcod2", "phone2");
        addressDAO.create(address1);
        addressDAO.create(address2);

        List<Address> addresses = addressDAO.getAll();

        assertEquals(addresses.size(),2);
    }

    @Test
    public void testGetAllForCity()throws DBException
    {
        Address address1 = createAddress("address1", "address21", "district1", (short) 1, "postalcod1", "phone1");
        addressDAO.create(address1);

        Address address2 = createAddress("address2", "address22", "district2", (short) 1, "postalcod2", "phone2");
        addressDAO.create(address2);

        Address address3 = createAddress("address3", "address23", "district3", (short) 1, "postalcod3", "phone3");
        addressDAO.create(address3);

        Address address4 = createAddress("address4", "address24", "district4", (short) 2, "postalcod4", "phone4");
        addressDAO.create(address4);


        City city=new City();

        city.setCity_id((short)1);

        List<Address> addresses = addressDAO.getAllForCity(city);

        assertEquals(addresses.size(), 3);

        city.setCity_id((short)2);

        addresses = addressDAO.getAllForCity(city);

        assertEquals(addresses.size(), 1);

    }
    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }
}
