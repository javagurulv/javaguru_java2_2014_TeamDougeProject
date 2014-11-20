package lv.javaguru.java2.database.jdbc.deprecated_implementation;

import lv.javaguru.java2.database.deprecated_dao.AddressDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.deprecated_classes.Address;
import lv.javaguru.java2.domain.deprecated_classes.City;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juris on 19.10.2014.
 */
@Deprecated
public class AddressDAOImpl extends DAOImpl implements AddressDAO {
    @Override
    public void create(Address address) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ADDRESS VALUES (default ,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, address.getAddress());
            preparedStatement.setString(2, address.getAddress2());
            preparedStatement.setString(3, address.getDistrict());
            preparedStatement.setShort(4, address.getCity_id());
            preparedStatement.setString(5, address.getPostal_code());
            preparedStatement.setString(6, address.getPhone());
            preparedStatement.setTimestamp(7, new Timestamp(address.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                address.setAddress_id(resultSet.getShort(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute AddressDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }

    }

    @Override
    public Address getById(Short address_id) throws DBException {
        Address address = null;
        Connection connection=null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from ADDRESS where address_id = ?");
            preparedStatement.setShort(1,address_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                address = new Address();
                address.setAddress_id(resultSet.getShort(1));
                address.setAddress(resultSet.getString(2));
                address.setAddress2(resultSet.getString(3));
                address.setDistrict(resultSet.getString(4));
                address.setCity_id(resultSet.getShort(5));
                address.setPostal_code(resultSet.getString(6));
                address.setPhone(resultSet.getString(7));
                address.setLast_update(resultSet.getTimestamp(8));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute AddressDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return address;
    }

    @Override
    public void update(Address address) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE address SET address = ?, address2 = ?, district = ?, city_id = ?, postal_code = ?, phone = ?,last_update = ? where address_id = ?");
            preparedStatement.setString(1, address.getAddress());
            preparedStatement.setString(2, address.getAddress2());
            preparedStatement.setString(3, address.getDistrict());
            preparedStatement.setShort(4, address.getCity_id());
            preparedStatement.setString(5, address.getPostal_code());
            preparedStatement.setString(6, address.getPhone());
            preparedStatement.setTimestamp(7,  new Timestamp(address.getLast_update().getTime()));
            preparedStatement.setShort(8, address.getAddress_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute AddressDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Short address_id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ADDRESS WHERE ADDRESS_ID = ?");
            preparedStatement.setShort(1, address_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute AddressDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Address> getAll() throws DBException {
        List<Address> addresses = new ArrayList<Address>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ADDRESS ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Address address = new Address();
                address.setAddress_id(resultSet.getShort("ADDRESS_ID"));
                address.setAddress(resultSet.getString("ADDRESS"));
                address.setAddress2(resultSet.getString("ADDRESS2"));
                address.setDistrict(resultSet.getString("DISTRICT"));
                address.setCity_id(resultSet.getShort("CITY_ID"));
                address.setPostal_code(resultSet.getString("POSTAL_CODE"));
                address.setPhone(resultSet.getString("PHONE"));
                address.setLast_update(resultSet.getDate("LAST_UPDATE"));
                addresses.add(address);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute AddressDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return addresses;
    }

    @Override
    public List<Address> getAllForCity(City city) throws DBException {

        List<Address> addresses = new ArrayList<Address>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from ADDRESS where CITY_ID = ?");
            preparedStatement.setShort(1, city.getCity_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Address address = new Address();
                address.setAddress_id(resultSet.getShort(1));
                address.setAddress(resultSet.getString(2));
                address.setAddress2(resultSet.getString(3));
                address.setDistrict(resultSet.getString(4));
                address.setCity_id(resultSet.getShort(5));
                address.setPostal_code(resultSet.getString(6));
                address.setPhone(resultSet.getString(7));
                address.setLast_update(resultSet.getDate(8));
                addresses.add(address);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute AddressDAOImpl.getAllForCity()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return addresses;
    }
}
