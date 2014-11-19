package lv.javaguru.java2.database.jdbc.deprecated_tests;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.RentalDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.RentalDAOImpl;
import lv.javaguru.java2.domain.Customer;
import lv.javaguru.java2.domain.Inventory;
import lv.javaguru.java2.domain.Rental;
import lv.javaguru.java2.domain.Staff;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Juris on 25.10.2014.
 */
@Ignore
public class RentalDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private RentalDAO rentalDAO= new RentalDAOImpl();

    private Rental createRental(Date rental_date,int inventory_id,Short customer_id,Date return_date,int staff_id) {
        Rental rental = new Rental();
        rental.setRental_date(rental_date);
        rental.setInventory_id(inventory_id);
        rental.setCustomer_id(customer_id);
        rental.setReturn_date(return_date);
        rental.setStaff_id(staff_id);
        rental.setLast_update(new Date());

        return rental;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        Rental rental = createRental(new Date(), 1, (short) 1, new Date(),1);

        rentalDAO.create(rental);
        Rental rentalFromDB = rentalDAO.getByID(rental.getRental_id());
        assertNotNull(rentalFromDB);
        assertionEqualsDateCustom(rental.getRental_date(), rental.getRental_date());
        assertEquals(rental.getInventory_id(), rentalFromDB.getInventory_id());
        assertEquals(rental.getCustomer_id(), rentalFromDB.getCustomer_id());
        assertionEqualsDateCustom(rental.getReturn_date(), rental.getReturn_date());
        assertEquals(rental.getStaff_id(), rentalFromDB.getStaff_id());
        assertionEqualsDateCustom(rental.getLast_update(), rental.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        Rental rental = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental);
        Rental rentalFromDB = rentalDAO.getByID(rental.getRental_id());

        assertNotNull(rentalFromDB);

        rentalDAO.delete(rental.getRental_id());

        rentalFromDB = rentalDAO.getByID(rental.getRental_id());

        assertNull(rentalFromDB);
    }

    @Test
    public void testUpdate() throws DBException
    {
        Rental rental = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental);

        rental.setRental_date(new Date());
        rental.setInventory_id(2);
        rental.setCustomer_id((short) 2);
        rental.setReturn_date(new Date());
        rental.setStaff_id(2);
        rental.setLast_update(new Date());

        rentalDAO.update(rental);

        Rental rentalFromDB = rentalDAO.getByID(rental.getRental_id());

        assertNotNull(rentalFromDB);
        assertionEqualsDateCustom(rental.getRental_date(), rental.getRental_date());
        assertEquals(rental.getInventory_id(), rentalFromDB.getInventory_id());
        assertEquals(rental.getCustomer_id(), rentalFromDB.getCustomer_id());
        assertionEqualsDateCustom(rental.getReturn_date(), rental.getReturn_date());
        assertEquals(rental.getStaff_id(), rentalFromDB.getStaff_id());
        assertionEqualsDateCustom(rental.getLast_update(), rental.getLast_update());
    }

    @Test
    public void testGetAll() throws DBException
    {
        Rental rental1 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        Rental rental2 = createRental(new Date(), 2, (short) 2, new Date(), 2);
        rentalDAO.create(rental1);
        rentalDAO.create(rental2);

        List<Rental> rentals = rentalDAO.getAll();

        assertEquals(rentals.size(),2);
    }

    @Test
    public void testGetAllForInventory() throws DBException, InterruptedException {
        Rental rental1 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental1);
        Thread.sleep(1000);
        Rental rental2 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental2);
        Thread.sleep(1000);
        Rental rental3 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental3);
        Thread.sleep(1000);
        Rental rental4 = createRental(new Date(), 2, (short) 1, new Date(), 1);
        rentalDAO.create(rental4);


        Inventory inventory=new Inventory();

        inventory.setInventory_id(1);

        List<Rental> rentals = rentalDAO.getAllForInventory(inventory);

        assertEquals(rentals.size(), 3);

        inventory.setInventory_id(2);

        rentals = rentalDAO.getAllForInventory(inventory);

        assertEquals(rentals.size(), 1);
    }

    @Test
    public void testGetAllForCustomer() throws DBException, InterruptedException {
        Rental rental1 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental1);
        Thread.sleep(1000);
        Rental rental2 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental2);
        Thread.sleep(1000);
        Rental rental3 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental3);
        Thread.sleep(1000);
        Rental rental4 = createRental(new Date(), 1, (short) 2, new Date(), 1);
        rentalDAO.create(rental4);


        Customer customer=new Customer();

        customer.setCustomer_id(1);

        List<Rental> rentals = rentalDAO.getAllForCustomer(customer);

        assertEquals(rentals.size(), 3);

        customer.setCustomer_id(2);

        rentals = rentalDAO.getAllForCustomer(customer);

        assertEquals(rentals.size(), 1);
    }

    @Test
    public void testGetAllForStaff() throws DBException, InterruptedException {
        Rental rental1 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental1);
        Thread.sleep(1000);
        Rental rental2 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental2);
        Thread.sleep(1000);
        Rental rental3 = createRental(new Date(), 1, (short) 1, new Date(), 1);
        rentalDAO.create(rental3);
        Thread.sleep(1000);
        Rental rental4 = createRental(new Date(), 1, (short) 1, new Date(), 2);
        rentalDAO.create(rental4);


        Staff staff=new Staff();

        staff.setStaff_id(1);

        List<Rental> rentals = rentalDAO.getAllForStaff(staff);

        assertEquals(rentals.size(), 3);

        staff.setStaff_id(2);

        rentals = rentalDAO.getAllForStaff(staff);

        assertEquals(rentals.size(), 1);
    }

    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }
}
