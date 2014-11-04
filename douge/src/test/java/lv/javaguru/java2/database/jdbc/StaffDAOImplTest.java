package lv.javaguru.java2.database.jdbc;

import com.mysql.jdbc.Blob;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StaffDAO;
import lv.javaguru.java2.domain.Address;
import lv.javaguru.java2.domain.Inventory;
import lv.javaguru.java2.domain.Staff;
import lv.javaguru.java2.domain.Store;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import sun.net.ResourceManager;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;

/**
 * Created by Juris on 25.10.2014.
 */
@Ignore
public class StaffDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private StaffDAO staffDA= new StaffDAOImpl();

    private Staff createStaff(String first_name, String last_name, Short address_id, SerialBlob picture, String email, int store_id, int active, String username, String password) {
        Staff staff = new Staff();
        staff.setFirst_name(first_name);
        staff.setLast_name(last_name);
        staff.setAddress_id((address_id));
        staff.setPicture(picture);
        staff.setEmail(email);
        staff.setStore_id(store_id);
        staff.setActive(active);
        staff.setUsername(username);
        staff.setPassword(password);
        staff.setLast_update(new Date());

        return staff;
    }

    private SerialBlob getPic(String filename) throws IOException,SQLException {
        InputStream fin =   StaffDAOImplTest.class.getClassLoader().getResourceAsStream(filename);
        BufferedInputStream bins = new BufferedInputStream(fin);
        ArrayList<Byte> bytes =  new ArrayList<Byte>();
        int  b = 0;
        while ((b = bins.read()) != -1){
            bytes.add((byte) b);
        }

        byte[] bb = new byte[(int)bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            bb[i] = bytes.get(i);
        }
        SerialBlob serialBlob = new SerialBlob(bb);
        fin.close();
        bins.close();
        return serialBlob;

    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException, IOException, SQLException {

        SerialBlob serialBlob = getPic("pic1.jpg");
        Staff staff = createStaff("first_name", "last_name", (short) 1, serialBlob, "email", 1, 1, "username", "password");

        staffDA.create(staff);
        Staff staffFromDB = staffDA.getById((short) staff.getStaff_id());
        assertNotNull(staffFromDB);
        assertEquals(staff.getFirst_name(), staffFromDB.getFirst_name());
        assertEquals(staff.getLast_name(), staffFromDB.getLast_name());
        assertEquals(staff.getActive(), staffFromDB.getActive());
        assertEquals(staff.getAddress_id(), staffFromDB.getAddress_id());
        assertEquals(staff.getEmail(), staffFromDB.getEmail());
        assertEquals(staff.getStore_id(), staffFromDB.getStore_id());
        assertEquals(staff.getActive(),staffFromDB.getActive());
        assertEquals(staff.getUsername(), staffFromDB.getUsername());
        assertEquals(staff.getPassword(), staffFromDB.getPassword());
        assertTrue(staff.getPicture().equals(staffFromDB.getPicture()));
        assertionEqualsDateCustom(staff.getLast_update(), staffFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException, IOException, SQLException {
        SerialBlob serialBlob = getPic("pic1.jpg");
        Staff staff = createStaff("first_name", "last_name", (short) 1, serialBlob, "email", 1, 1, "username", "password");

        staffDA.create(staff);
        staffDA.delete(staff.getStaff_id());

        Staff staffFromDb = staffDA.getById((short) staff.getStaff_id());
        assertNull(staffFromDb);
    }

    @Test
    public void testUpdate() throws DBException, IOException, SQLException {
        /**/

        SerialBlob serialBlob = getPic("pic1.jpg");
        Staff staff = createStaff("first_name", "last_name", (short) 1, serialBlob, "email", 1, 1, "username", "password");
        staffDA.create(staff);

      Staff staff1 = createStaff("aaa", "aaa", (short) 55, getPic("pic3.jpg"), "fff",10, 10, "ggg", "ggg");
      staff1.setStaff_id(staff.getStaff_id());

      staffDA.update(staff1);

      Staff staffFromDB = staffDA.getById((short) staff.getStaff_id());
      asserts(staff1, staffFromDB);


    }

    @Test
    public void testGetAll() throws DBException, IOException, SQLException {
        Staff staff = createStaff("first_name", "last_name", (short) 1, getPic("pic1.jpg"), "email", 1, 1, "username", "password");
        staffDA.create(staff);
        Staff staff1 = createStaff("aaa", "aaa", (short) 55, getPic("pic3.jpg"), "fff",10, 10, "ggg", "ggg");
        staffDA.create(staff1);

        List<Staff> staffList = staffDA.getAll();
        assertEquals(staffList.size(),2);
    }

    @Test
    public void testGetAllByAddress() throws DBException,IOException, SQLException
    {
        Address address = new Address();
        Staff staff = createStaff("first_name", "last_name", (short) 1, getPic("pic1.jpg"), "email", 1, 1, "username", "password");
        staffDA.create(staff);
        Staff staff1 = createStaff("aaa", "aaa", (short) 55, getPic("pic3.jpg"), "fff",10, 10, "ggg", "ggg");
        staffDA.create(staff1);
        Staff staff2 = createStaff("hhh", "jjj", (short) 55, getPic("pic3.jpg"), "kkk",10, 10, "lll", "bbb");
        staffDA.create(staff2);

        address.setAddress_id((short) 1);
        List<Staff> staffList = staffDA.getAllForAddress(address);
        assertEquals(staffList.size(),1);
        address.setAddress_id((short) 55);
        staffList = staffDA.getAllForAddress(address);
        assertEquals(staffList.size(),2);
    }


    @Test
    public void testGetAllByStore() throws DBException, IOException, SQLException {
        Staff staff = createStaff("first_name", "last_name", (short) 1, getPic("pic1.jpg"), "email", 1, 1, "username", "password");
        staffDA.create(staff);
        Staff staff1 = createStaff("aaa", "aaa", (short) 55, getPic("pic3.jpg"), "fff",10, 10, "ggg", "ggg");
        staffDA.create(staff1);
        Staff staff2 = createStaff("hhh", "jjj", (short) 55, getPic("pic3.jpg"), "kkk",10, 10, "lll", "bbb");
        staffDA.create(staff2);
        Store store = new Store();
        store.setStore_id(1);
        List<Staff> staffList = staffDA.getAllForStore(store);
        assertEquals(staffList.size(),1);
        store.setStore_id(10);
        staffList = staffDA.getAllForStore(store);
        assertEquals(staffList.size(),2);
    }

    private void asserts(Staff staff, Staff staffFromDB){
        assertNotNull(staffFromDB);
        assertEquals(staff.getFirst_name(), staffFromDB.getFirst_name());
        assertEquals(staff.getLast_name(), staffFromDB.getLast_name());
        assertEquals(staff.getActive(), staffFromDB.getActive());
        assertEquals(staff.getAddress_id(), staffFromDB.getAddress_id());
        assertEquals(staff.getEmail(), staffFromDB.getEmail());
        assertEquals(staff.getStore_id(), staffFromDB.getStore_id());
        assertEquals(staff.getActive(),staffFromDB.getActive());
        assertEquals(staff.getUsername(), staffFromDB.getUsername());
        assertEquals(staff.getPassword(), staffFromDB.getPassword());
        assertTrue(staff.getPicture().equals(staffFromDB.getPicture()));
        assertionEqualsDateCustom(staff.getLast_update(), staffFromDB.getLast_update());
    }

    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd h:m");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }
}
