package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CustomerDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Customer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 18.10.2014.
 */
@Component
public class CustomerDAOImpl extends DAOImpl implements CustomerDAO {
    @Override
    public void create(Customer customer) throws DBException {

       Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO CUSTOMER VALUES(default ,?,?,?,?,?,?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,customer.getStore_id());
            preparedStatement.setString(2,customer.getFirst_name());
            preparedStatement.setString(3,customer.getLast_name());
            preparedStatement.setString(4,customer.getEmail());
            preparedStatement.setLong(5,customer.getAddress_id());
            preparedStatement.setLong(6,customer.getActive());
            preparedStatement.setDate(7, new Date(customer.getCreate_date().getTime()));
            preparedStatement.setDate(8,new Date(customer.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                customer.setCustomer_id((int) resultSet.getLong(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CustomerDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

    }



    private void fillCustomer(Customer customer, ResultSet resultSet) throws SQLException {
        customer.setCustomer_id(resultSet.getInt("CUSTOMER_ID"));
        customer.setFirst_name(resultSet.getString("FIRST_NAME"));
        customer.setLast_name(resultSet.getString("LAST_NAME"));
        customer.setStore_id(resultSet.getInt("STORE_ID"));
        customer.setEmail(resultSet.getString("EMAIL"));
        customer.setAddress_id(resultSet.getInt("ADDRESS_ID"));
        customer.setActive(resultSet.getInt("ACTIVE"));
        customer.setCreate_date(resultSet.getDate("CREATE_DATE"));
        customer.setLast_update(resultSet.getDate("LAST_UPDATE"));
    }

    @Override
    public void delete(int id) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ? ");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CustomerDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

    }

    @Override
    public void update(Customer customer) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE  CUSTOMER SET STORE_ID = ?," +
                            " FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, " +
                            "ADDRESS_ID = ?, ACTIVE = ?, CREATE_DATE = ?, LAST_UPDATE = ? " +
                            "WHERE  CUSTOMER_ID = ?");
            preparedStatement.setLong(1,customer.getStore_id());
            preparedStatement.setString(2, customer.getFirst_name());
            preparedStatement.setString(3,customer.getLast_name());
            preparedStatement.setString(4,customer.getEmail());
            preparedStatement.setLong(5, customer.getAddress_id());
            preparedStatement.setLong(6,customer.getActive());
            preparedStatement.setDate(7, new Date(customer.getCreate_date().getTime()));
            preparedStatement.setDate(8,new Date(customer.getLast_update().getTime()));
            preparedStatement.setLong(9,customer.getCustomer_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CustomerDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

    }

    @Override
    public Customer getById(int id) throws DBException {
        Customer customer = null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT CUSTOMER_ID,STORE_ID,FIRST_NAME,LAST_NAME,EMAIL," +
                            "ADDRESS_ID, ACTIVE,CREATE_DATE,LAST_UPDATE FROM CUSTOMER WHERE CUSTOMER_ID = ?");
            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                customer = new Customer();
                fillCustomer(customer, resultSet);

            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CustomerDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

        return customer;
    }

    @Override
    public List<Customer> getAll() throws DBException {
        List<Customer> customers = new ArrayList<Customer>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT CUSTOMER_ID,STORE_ID,FIRST_NAME,LAST_NAME,EMAIL," +
                            "ADDRESS_ID, ACTIVE,CREATE_DATE,LAST_UPDATE FROM CUSTOMER ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Customer customer = new Customer();
                fillCustomer(customer, resultSet);
                customers.add(customer);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CustomerDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return customers;
    }

    @Override
    public List<Customer> getAllByStoreID(int id) throws DBException {
        List<Customer> customers = new ArrayList<Customer>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT CUSTOMER_ID,STORE_ID,FIRST_NAME,LAST_NAME,EMAIL," +
                            "ADDRESS_ID, ACTIVE,CREATE_DATE,LAST_UPDATE FROM CUSTOMER WHERE STORE_ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Customer customer = new Customer();
                fillCustomer(customer, resultSet);
                customers.add(customer);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CustomerDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return customers;
    }

    @Override
    public List<Customer> getAllByAddressID(int id) throws DBException {
        List<Customer> customers = new ArrayList<Customer>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT CUSTOMER_ID,STORE_ID,FIRST_NAME,LAST_NAME,EMAIL," +
                            "ADDRESS_ID, ACTIVE,CREATE_DATE,LAST_UPDATE FROM CUSTOMER WHERE ADDRESS_ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Customer customer = new Customer();
                fillCustomer(customer, resultSet);
                customers.add(customer);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CustomerDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return customers;
    }
}
