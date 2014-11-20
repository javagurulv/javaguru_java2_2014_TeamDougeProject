package lv.javaguru.java2.database.jdbc.deprecated_implementation;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.deprecated_dao.RentalDAO;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.deprecated_classes.Customer;
import lv.javaguru.java2.domain.deprecated_classes.Inventory;
import lv.javaguru.java2.domain.deprecated_classes.Rental;
import lv.javaguru.java2.domain.deprecated_classes.Staff;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juris on 25.10.2014.
 */
@Deprecated
public class RentalDAOImpl extends DAOImpl implements RentalDAO {

    @Override
    public Rental getByID(int rental_id) throws DBException {
        Rental rental = null;
        Connection connection=null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from RENTAL where rental_id = ?");
            preparedStatement.setInt(1, rental_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                rental = new Rental();
                rental.setRental_id(resultSet.getInt(1));
                rental.setRental_date(resultSet.getDate(2));
                rental.setInventory_id(resultSet.getInt(3));
                rental.setCustomer_id(resultSet.getShort(4));
                rental.setReturn_date(resultSet.getDate(5));
                rental.setStaff_id(resultSet.getInt(6));
                rental.setLast_update(resultSet.getTimestamp(7));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute RentalDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return rental;
    }

    @Override
    public void create(Rental rental) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RENTAL VALUES (default,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, new Timestamp(rental.getRental_date().getTime()));
            preparedStatement.setInt(2, rental.getInventory_id());
            preparedStatement.setShort(3, rental.getCustomer_id());
            preparedStatement.setDate(4,  new java.sql.Date(rental.getReturn_date().getTime()));
            preparedStatement.setInt(5, rental.getStaff_id());
            preparedStatement.setTimestamp(6, new Timestamp(rental.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                rental.setRental_id(resultSet.getInt(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute RentalDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(int rental_id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM RENTAL WHERE RENTAL_ID = ?");
            preparedStatement.setInt(1, rental_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute RentalDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Rental rental) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE rental SET rental_date = ?, inventory_id = ?, customer_id = ?, return_date = ?, staff_id = ?, last_update = ? where rental_id = ?");
            preparedStatement.setDate(1, new java.sql.Date(rental.getRental_date().getTime()));
            preparedStatement.setInt(2, rental.getInventory_id());
            preparedStatement.setShort(3, rental.getCustomer_id());
            preparedStatement.setDate(4, new java.sql.Date(rental.getReturn_date().getTime()));
            preparedStatement.setInt(5, rental.getStaff_id());
            preparedStatement.setTimestamp(6, new Timestamp(rental.getLast_update().getTime()));
            preparedStatement.setInt(7, rental.getRental_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute RentalDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Rental> getAll() throws DBException {
        List<Rental> rentals=new ArrayList<Rental>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM RENTAL ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Rental rental=new Rental();
                rental.setRental_id(resultSet.getInt("RENTAL_ID"));
                rental.setRental_date(resultSet.getDate("RENTAL_DATE"));
                rental.setInventory_id(resultSet.getInt("INVENTORY_ID"));
                rental.setCustomer_id(resultSet.getShort("CUSTOMER_ID"));
                rental.setRental_date(resultSet.getDate("RETURN_DATE"));
                rental.setStaff_id(resultSet.getInt("STAFF_ID"));
                rental.setLast_update(resultSet.getDate("LAST_UPDATE"));
                rentals.add(rental);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute RentalDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return rentals;
    }

    @Override
    public List<Rental> getAllForInventory(Inventory inventory) throws DBException {
        List<Rental> rentals = new ArrayList<Rental>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from RENTAL where INVENTORY_ID = ?");
            preparedStatement.setInt(1, inventory.getInventory_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Rental rental = new Rental();
                rental.setRental_id(resultSet.getInt(1));
                rental.setRental_date(resultSet.getDate(2));
                rental.setInventory_id(resultSet.getInt(3));
                rental.setCustomer_id(resultSet.getShort(4));
                rental.setReturn_date(resultSet.getDate(5));
                rental.setStaff_id(resultSet.getInt(6));
                rental.setLast_update(resultSet.getDate(7));
                rentals.add(rental);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute RentalDAOImpl.getAllForInventory()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return rentals;
    }

    @Override
    public List<Rental> getAllForCustomer(Customer customer) throws DBException {
        List<Rental> rentals = new ArrayList<Rental>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from RENTAL where CUSTOMER_ID = ?");
            preparedStatement.setInt(1, customer.getCustomer_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Rental rental = new Rental();
                rental.setRental_id(resultSet.getInt(1));
                rental.setRental_date(resultSet.getDate(2));
                rental.setInventory_id(resultSet.getInt(3));
                rental.setCustomer_id(resultSet.getShort(4));
                rental.setReturn_date(resultSet.getDate(5));
                rental.setStaff_id(resultSet.getInt(6));
                rental.setLast_update(resultSet.getDate(7));
                rentals.add(rental);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute RentalDAOImpl.getAllForCustomer()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return rentals;
    }

    @Override
    public List<Rental> getAllForStaff(Staff staff) throws DBException {
        List<Rental> rentals = new ArrayList<Rental>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from RENTAL where STAFF_ID = ?");
            preparedStatement.setInt(1, staff.getStaff_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Rental rental = new Rental();
                rental.setRental_id(resultSet.getInt(1));
                rental.setRental_date(resultSet.getDate(2));
                rental.setInventory_id(resultSet.getInt(3));
                rental.setCustomer_id(resultSet.getShort(4));
                rental.setReturn_date(resultSet.getDate(5));
                rental.setStaff_id(resultSet.getInt(6));
                rental.setLast_update(resultSet.getDate(7));
                rentals.add(rental);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute RentalDAOImpl.getAllForStaff()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return rentals;
    }
}
