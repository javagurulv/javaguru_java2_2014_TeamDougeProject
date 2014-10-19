package lv.javaguru.java2.database.jdbc;

/**
 * Created by Sergo on 19.10.2014.
 */
import static org.junit.Assert.*;

import lv.javaguru.java2.database.CustomerDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CustomerDAO customerDAO = new CustomerDAOImpl();


    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }

    private Customer createCustomer(int store_id,String first_name, String last_name,String email,int address_id)
    {

        Customer customer = new Customer();
        customer.setStore_id(store_id);
        customer.setFirst_name(first_name);
        customer.setLast_name(last_name);
        customer.setEmail(email);
        customer.setAddress_id(address_id);
        customer.setActive(0);
        customer.setCreate_date(new Date());
        customer.setLast_update(new Date());
        return customer;
    }

    private void assertsCustomers(Customer customer, Customer customerFromDB)
    {
        assertEquals(customer.getFirst_name(), customerFromDB.getFirst_name());
        assertEquals(customer.getLast_name(), customerFromDB.getLast_name());
        assertEquals(customer.getStore_id(), customerFromDB.getStore_id());
        assertEquals(customer.getAddress_id(), customerFromDB.getAddress_id());
        assertEquals(customer.getActive(), customerFromDB.getActive());
        assertEquals(customer.getCustomer_id(), customerFromDB.getCustomer_id());
        assertEquals(customer.getEmail(), customerFromDB.getEmail());
        assertionEqualsDateCustom(customer.getCreate_date(), customerFromDB.getCreate_date());
        assertionEqualsDateCustom(customer.getLast_update(), customerFromDB.getLast_update());
    }

    @Before
    public void cleanDatabase() throws DBException
    {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        Customer customer = createCustomer(1,"aaa","fff","eee",50);
        customerDAO.create(customer);

        Customer customerFromDB = customerDAO.getById(customer.getCustomer_id());

        assertNotNull(customerFromDB);
        assertsCustomers(customer, customerFromDB);
    }


    @Test
    public void testDelete() throws DBException
    {
        Customer customer = createCustomer(1,"aaa","fff","eee",50);
        customerDAO.create(customer);
        customerDAO.delete(customer.getCustomer_id());

        customer = customerDAO.getById(customer.getCustomer_id());
        assertNull(customer);
    }
    @Test
    public void testUpdate() throws DBException
    {
        Customer customer = createCustomer(1,"aaa","fff","eee",50);
        customerDAO.create(customer);

        customer.setAddress_id(50);
        customer.setStore_id(250);
        customerDAO.update(customer);

        Customer customerFromDB = customerDAO.getById(customer.getCustomer_id());
        assertsCustomers(customer, customerFromDB);
    }

    @Test
    public void testGetAll() throws DBException
    {
        Customer customer = createCustomer(1,"aaa","fff","eee",50);
        customerDAO.create(customer);
        Customer customer1 = createCustomer(1,"ggg","hhh","eee",50);
        customerDAO.create(customer1);

        List<Customer> customerList = customerDAO.getAll();
        assertEquals(customerList.size(), 2);
    }

    @Test
    public void testGetAllByStoreId() throws DBException
    {
        Customer customer = createCustomer(1,"aaa","fff","eee",50);
        customerDAO.create(customer);
        Customer customer1 = createCustomer(5,"ggg","hhh","eee",50);
        customerDAO.create(customer1);
        Customer customer3 = createCustomer(5,"aaa","fff","eee",50);
        customerDAO.create(customer3);
        Customer customer4 = createCustomer(5,"ggg","hhh","eee",50);
        customerDAO.create(customer4);
        List<Customer> customerList = customerDAO.getAllByStoreID(5);
        assertEquals(customerList.size(), 3);

    }

    @Test
    public void testGetAllByAddressID() throws DBException
    {
        Customer customer = createCustomer(1,"aaa","fff","eee",50);
        customerDAO.create(customer);
        Customer customer1 = createCustomer(5,"ggg","hhh","eee",15);
        customerDAO.create(customer1);
        Customer customer3 = createCustomer(5,"aaa","fff","eee",50);
        customerDAO.create(customer3);
        Customer customer4 = createCustomer(5,"ggg","hhh","eee",50);
        customerDAO.create(customer4);
        List<Customer> customerList = customerDAO.getAllByAddressID(50);
        assertEquals(customerList.size(), 3);

    }


}
