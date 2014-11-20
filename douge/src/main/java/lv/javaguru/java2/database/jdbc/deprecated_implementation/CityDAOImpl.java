package lv.javaguru.java2.database.jdbc.deprecated_implementation;

import lv.javaguru.java2.database.deprecated_dao.CityDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.deprecated_classes.City;
import lv.javaguru.java2.domain.deprecated_classes.Country;
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
public class CityDAOImpl extends DAOImpl implements CityDAO {
    @Override
    public void create(City city) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CITY VALUES (default ,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, city.getCity());
            preparedStatement.setShort(2, city.getCountry_id());
            preparedStatement.setTimestamp(3, new Timestamp(city.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                city.setCity_id(resultSet.getShort(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CityDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }

    }

    @Override
    public City getById(Short city_id) throws DBException {
        City city = null;
        Connection connection=null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from CITY where city_id = ?");
            preparedStatement.setShort(1,city_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                city = new City();
                city.setCity_id(resultSet.getShort(1));
                city.setCity(resultSet.getString(2));
                city.setCountry_id(resultSet.getShort(3));
                city.setLast_update(resultSet.getTimestamp(4));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CityDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return city;
    }

    @Override
    public void update(City city) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE CITY SET city = ?, country_id = ?, last_update = ? where city_id = ?");
            preparedStatement.setString(1, city.getCity());
            preparedStatement.setShort(2, city.getCountry_id());
            preparedStatement.setTimestamp(3,  new Timestamp(city.getLast_update().getTime()));
            preparedStatement.setShort(4, city.getCity_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CityDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Short city_id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CITY WHERE CITY_ID = ?");
            preparedStatement.setShort(1, city_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CityDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<City> getAll() throws DBException {
        List<City> cities = new ArrayList<City>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CITY ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                City city = new City();
                city.setCity_id(resultSet.getShort("CITY_ID"));
                city.setCity(resultSet.getString("CITY"));
                city.setCountry_id(resultSet.getShort("COUNTRY_ID"));
                city.setLast_update(resultSet.getDate("LAST_UPDATE"));
                cities.add(city);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CityDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return cities;
    }

    @Override
    public List<City> getAllForCountry(Country country) throws DBException {
        List<City> cities = new ArrayList<City>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from CITY where COUNTRY_ID = ?");
            preparedStatement.setLong(1,country.getCountry_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                City city = new City();
                city.setCity_id(resultSet.getShort(1));
                city.setCity(resultSet.getString(2));
                city.setCountry_id(resultSet.getShort(3));
                city.setLast_update(resultSet.getDate(4));
                cities.add(city);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute CityDAOImpl.getAllForCountry()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return cities;
    }
}
