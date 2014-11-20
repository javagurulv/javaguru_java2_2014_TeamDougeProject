package lv.javaguru.java2.database.jdbc.deprecated_implementation;

import lv.javaguru.java2.database.deprecated_dao.CountryDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.deprecated_classes.Country;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 18.10.2014.
 */
@Deprecated
public class CountryDAOImpl extends DAOImpl implements CountryDAO {
    @Override
    public void create(Country country) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO COUNTRY VALUES (default ,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,country.getCountry());
            preparedStatement.setDate(2, new Date(country.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet  = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                country.setCountry_id(resultSet.getInt(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CountryDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public Country getById(Long id) throws DBException {
        Country country = null;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM COUNTRY WHERE COUNTRY_ID = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet =preparedStatement.executeQuery();

            if(resultSet.next()){
                country = new Country();
                country.setCountry_id(resultSet.getInt(1));
                country.setCountry(resultSet.getString(2));
                country.setLast_update(resultSet.getDate(3));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CountryDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return country;
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE  FROM COUNTRY WHERE COUNTRY_ID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CountryDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Country country) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE COUNTRY SET COUNTRY = ?, LAST_UPDATE = ? WHERE COUNTRY_ID = ?");
            preparedStatement.setString(1,country.getCountry());
            preparedStatement.setDate(2, new Date(country.getLast_update().getTime()));
            preparedStatement.setLong(3, country.getCountry_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CountryDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Country> getAll() throws DBException {
        List<Country> countries = new ArrayList<Country>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM COUNTRY");

            ResultSet resultSet =preparedStatement.executeQuery();

            while(resultSet.next()){
                Country country = new Country();
                country.setCountry_id(resultSet.getInt(1));
                country.setCountry(resultSet.getString(2));
                country.setLast_update(resultSet.getDate(3));
                countries.add(country);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CountryDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return countries;
    }
}
