package lv.javaguru.java2.database.jdbc.deprecated_implementation;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.deprecated_dao.InventoryDAO;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.Film;
import lv.javaguru.java2.domain.deprecated_classes.Inventory;
import lv.javaguru.java2.domain.deprecated_classes.Store;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juris on 24.10.2014.
 */
@Deprecated
public class InventoryDAOImpl extends DAOImpl implements InventoryDAO {
    @Override
    public Inventory getByID(int inventory_id) throws DBException {
        Inventory inventory = null;
        Connection connection=null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from INVENTORY where inventory_id = ?");
            preparedStatement.setInt(1, inventory_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                inventory = new Inventory();
                inventory.setInventory_id(resultSet.getInt(1));
                inventory.setFilm_id(resultSet.getShort(2));
                inventory.setStore_id(resultSet.getInt(3));
                inventory.setLast_update(resultSet.getTimestamp(4));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute InventoryDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return inventory;
    }

    @Override
    public void create(Inventory inventory) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO INVENTORY VALUES (default,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setShort(1, inventory.getFilm_id());
            preparedStatement.setInt(2, inventory.getStore_id());
            preparedStatement.setTimestamp(3, new Timestamp(inventory.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                inventory.setInventory_id(resultSet.getInt(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute InventoryDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(int inventory_id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM INVENTORY WHERE INVENTORY_ID = ?");
            preparedStatement.setInt(1, inventory_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute InventoryDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Inventory inventory) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE inventory SET film_id = ?, store_id = ?, last_update = ? where inventory_id = ?");
            preparedStatement.setInt(1, inventory.getFilm_id());
            preparedStatement.setInt(2, inventory.getStore_id());
            preparedStatement.setTimestamp(3, new Timestamp(inventory.getLast_update().getTime()));
            preparedStatement.setInt(4, inventory.getInventory_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute InventoryDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Inventory> getAll() throws DBException {
        List<Inventory> inventories=new ArrayList<Inventory>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM INVENTORY ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Inventory inventory=new Inventory();
                inventory.setInventory_id(resultSet.getInt("INVENTORY_ID"));
                inventory.setFilm_id(resultSet.getShort("FILM_ID"));
                inventory.setStore_id(resultSet.getInt("STORE_ID"));
                inventory.setLast_update(resultSet.getDate("LAST_UPDATE"));
                inventories.add(inventory);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute InventoryDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return inventories;
    }

    @Override
    public List<Inventory> getAllForFilm(Film film) throws DBException {
        List<Inventory> inventories = new ArrayList<Inventory>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from INVENTORY where FILM_ID = ?");
            preparedStatement.setInt(1, film.getFilm_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Inventory inventory = new Inventory();
                inventory.setInventory_id(resultSet.getInt(1));
                inventory.setFilm_id(resultSet.getShort(2));
                inventory.setStore_id(resultSet.getInt(3));
                inventory.setLast_update(resultSet.getDate(4));
                inventories.add(inventory);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute InventoryDAOImpl.getAllForFilm()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return inventories;
    }

    @Override
    public List<Inventory> getAllForStore(Store store) throws DBException {
        List<Inventory> inventories = new ArrayList<Inventory>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from INVENTORY where STORE_ID = ?");
            preparedStatement.setInt(1, store.getStore_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Inventory inventory = new Inventory();
                inventory.setInventory_id(resultSet.getInt(1));
                inventory.setFilm_id(resultSet.getShort(2));
                inventory.setStore_id(resultSet.getInt(3));
                inventory.setLast_update(resultSet.getDate(4));
                inventories.add(inventory);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute InventoryDAOImpl.getAllForStore()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return inventories;
    }
}
