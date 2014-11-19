package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StoreDAO;
import lv.javaguru.java2.domain.Store;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 20.10.2014.
 */
@Component
public class StoreDAOImpl extends DAOImpl implements StoreDAO {

    @Override
    public Store getByID(int id) throws DBException {
        Store store = null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT STORE_ID,ADDRESS_ID, MANAGER_STAFF_ID, LAST_UPDATE from STORE WHERE  STORE_ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                store = new Store();
                store.setStore_id(resultSet.getInt("STORE_ID"));
                store.setAddress_id(resultSet.getInt("ADDRESS_ID"));
                store.setManager_staff_id(resultSet.getInt("MANAGER_STAFF_ID"));
                store.setLast_update(resultSet.getDate("LAST_UPDATE"));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StoreDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return store;
    }

    @Override
    public void create(Store store) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO STORE(ADDRESS_ID, MANAGER_STAFF_ID, LAST_UPDATE) " +
                            "VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,store.getAddress_id());
            preparedStatement.setInt(2, store.getManager_staff_id());
            preparedStatement.setDate(3, new Date(store.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
            {
               store.setStore_id(resultSet.getInt(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StoreDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Store store) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE STORE SET ADDRESS_ID = ?, MANAGER_STAFF_ID = ?, LAST_UPDATE = ?" +
                            "WHERE STORE_ID = ?");
            preparedStatement.setInt(1,store.getAddress_id());
            preparedStatement.setInt(2, store.getManager_staff_id());
            preparedStatement.setDate(3, new Date(store.getLast_update().getTime()));
            preparedStatement.setInt(4,store.getStore_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StoreDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM STORE WHERE STORE_ID = ?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();


            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StoreDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Store> getAllByManagerStaffID(int id) throws DBException {
        List<Store> stores = new ArrayList<Store>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT STORE_ID,ADDRESS_ID, MANAGER_STAFF_ID, LAST_UPDATE from STORE WHERE  MANAGER_STAFF_ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Store store = new Store();
                store.setStore_id(resultSet.getInt("STORE_ID"));
                store.setAddress_id(resultSet.getInt("ADDRESS_ID"));
                store.setManager_staff_id(resultSet.getInt("MANAGER_STAFF_ID"));
                store.setLast_update(resultSet.getDate("LAST_UPDATE"));
                stores.add(store);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StoreDAOImpl.getAllByManagerStaffID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return stores;
    }

    @Override
    public List<Store> getAllByAddressId(int id) throws DBException {
        List<Store> stores = new ArrayList<Store>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT STORE_ID,ADDRESS_ID, MANAGER_STAFF_ID, LAST_UPDATE from STORE WHERE  ADDRESS_ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Store store = new Store();
                store.setStore_id(resultSet.getInt("STORE_ID"));
                store.setAddress_id(resultSet.getInt("ADDRESS_ID"));
                store.setManager_staff_id(resultSet.getInt("MANAGER_STAFF_ID"));
                store.setLast_update(resultSet.getDate("LAST_UPDATE"));
                stores.add(store);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StoreDAOImpl.getAllByAddressId()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return stores;
    }

    @Override
    public List<Store> getAll() throws DBException {
        List<Store> stores = new ArrayList<Store>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT STORE_ID,ADDRESS_ID, MANAGER_STAFF_ID, LAST_UPDATE from STORE");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Store store = new Store();
                store.setStore_id(resultSet.getInt("STORE_ID"));
                store.setAddress_id(resultSet.getInt("ADDRESS_ID"));
                store.setManager_staff_id(resultSet.getInt("MANAGER_STAFF_ID"));
                store.setLast_update(resultSet.getDate("LAST_UPDATE"));
                stores.add(store);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StoreDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return stores;
    }
}
