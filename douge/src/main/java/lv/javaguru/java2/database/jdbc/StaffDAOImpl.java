package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StaffDAO;
import lv.javaguru.java2.domain.Address;
import lv.javaguru.java2.domain.Staff;
import lv.javaguru.java2.domain.Store;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Juris on 25.10.2014.
 */
public class StaffDAOImpl extends DAOImpl implements StaffDAO {

    private void setPacketSizeInDB() throws DBException
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SET GLOBAL max_allowed_packet = 1024*1024*14");
            preparedStatement.execute();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StaffDAOImpl.setPacketSizeInDB()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }
    @Override
    public void create(Staff staff) throws DBException {
        setPacketSizeInDB();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO STAFF VALUES (default,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, staff.getFirst_name());
            preparedStatement.setString(2, staff.getLast_name());
            preparedStatement.setShort(3, staff.getAddress_id());
            preparedStatement.setBlob(4, staff.getPicture());
           // preparedStatement.setBytes(4,staff.getPicture().getBytes(1, (int) staff.getPicture().length()));
            preparedStatement.setString(5, staff.getEmail());
            preparedStatement.setInt(6, staff.getStore_id());
            preparedStatement.setInt(7, staff.getActive());
            preparedStatement.setString(8, staff.getUsername());
            preparedStatement.setString(9, staff.getPassword());
            preparedStatement.setTimestamp(10, new Timestamp(staff.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                staff.setStaff_id(resultSet.getInt(1));
            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StaffDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public Staff getById(Short staff_id) throws DBException {
        Connection connection = null;
        Staff staff = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT STAFF_ID, FIRST_NAME, LAST_NAME, ADDRESS_ID, PICTURE,EMAIL,STORE_ID," +
                            "ACTIVE, USERNAME, PASSWORD, LAST_UPDATE FROM STAFF WHERE STAFF_ID = ?");
            preparedStatement.setInt(1,staff_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                staff = new Staff();
                staff.setStaff_id(resultSet.getShort("STAFF_ID"));
                staff.setFirst_name(resultSet.getString("FIRST_NAME"));
                staff.setLast_name(resultSet.getString("LAST_NAME"));
                staff.setAddress_id(resultSet.getShort("ADDRESS_ID"));
                staff.setPicture(new SerialBlob(resultSet.getBlob("PICTURE")));
                staff.setEmail(resultSet.getString("EMAIL"));
                staff.setStore_id(resultSet.getInt("STORE_ID"));
                staff.setActive(resultSet.getInt("ACTIVE"));
                staff.setUsername(resultSet.getString("USERNAME"));
                staff.setPassword(resultSet.getString("PASSWORD"));
                staff.setLast_update(new Date(resultSet.getTimestamp("LAST_UPDATE").getTime()));


            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StaffDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

        return staff;
    }

    @Override
    public void update(Staff staff) throws DBException {
        setPacketSizeInDB();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE STAFF SET FIRST_NAME = ?, LAST_NAME = ?, ADDRESS_ID = ?," +
                            "PICTURE = ?, EMAIL = ?, STORE_ID = ?, ACTIVE = ?, USERNAME = ?, PASSWORD = ?, LAST_UPDATE = ?" +
                            "WHERE STAFF_ID = ?");
            preparedStatement.setString(1, staff.getFirst_name());
            preparedStatement.setString(2, staff.getLast_name());
            preparedStatement.setShort(3, staff.getAddress_id());
            preparedStatement.setBlob(4, staff.getPicture());
            preparedStatement.setString(5, staff.getEmail());
            preparedStatement.setInt(6, staff.getStore_id());
            preparedStatement.setInt(7, staff.getActive());
            preparedStatement.setString(8, staff.getUsername());
            preparedStatement.setString(9, staff.getPassword());
            preparedStatement.setTimestamp(10, new Timestamp(staff.getLast_update().getTime()));
            preparedStatement.setInt(11,staff.getStaff_id());
            preparedStatement.executeUpdate();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StaffDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(int staff_id) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM STAFF WHERE  STAFF_ID = ?");

            preparedStatement.setInt(1,staff_id);
            preparedStatement.executeUpdate();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StaffDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public List<Staff> getAll() throws DBException {
        Connection connection = null;
       List<Staff> staffList = new ArrayList<Staff>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT STAFF_ID, FIRST_NAME, LAST_NAME, ADDRESS_ID, PICTURE,EMAIL,STORE_ID," +
                            "ACTIVE, USERNAME, PASSWORD, LAST_UPDATE FROM STAFF");

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Staff staff = new Staff();
                staff.setStaff_id(resultSet.getShort("STAFF_ID"));
                staff.setFirst_name(resultSet.getString("FIRST_NAME"));
                staff.setLast_name(resultSet.getString("LAST_NAME"));
                staff.setAddress_id(resultSet.getShort("ADDRESS_ID"));
                staff.setPicture(new SerialBlob(resultSet.getBlob("PICTURE")));
                staff.setEmail(resultSet.getString("EMAIL"));
                staff.setStore_id(resultSet.getInt("STORE_ID"));
                staff.setActive(resultSet.getInt("ACTIVE"));
                staff.setUsername(resultSet.getString("USERNAME"));
                staff.setPassword(resultSet.getString("PASSWORD"));
                staff.setLast_update(new Date(resultSet.getTimestamp("LAST_UPDATE").getTime()));
                staffList.add(staff);

            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StaffDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return  staffList;
    }

    @Override
    public List<Staff> getAllForAddress(Address address) throws DBException {
        Connection connection = null;
        List<Staff> staffList = new ArrayList<Staff>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT STAFF_ID, FIRST_NAME, LAST_NAME, ADDRESS_ID, PICTURE,EMAIL,STORE_ID," +
                            "ACTIVE, USERNAME, PASSWORD, LAST_UPDATE FROM STAFF WHERE ADDRESS_ID = ?");
            preparedStatement.setInt(1,address.getAddress_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Staff staff = new Staff();
                staff.setStaff_id(resultSet.getShort("STAFF_ID"));
                staff.setFirst_name(resultSet.getString("FIRST_NAME"));
                staff.setLast_name(resultSet.getString("LAST_NAME"));
                staff.setAddress_id(resultSet.getShort("ADDRESS_ID"));
                staff.setPicture(new SerialBlob(resultSet.getBlob("PICTURE")));
                staff.setEmail(resultSet.getString("EMAIL"));
                staff.setStore_id(resultSet.getInt("STORE_ID"));
                staff.setActive(resultSet.getInt("ACTIVE"));
                staff.setUsername(resultSet.getString("USERNAME"));
                staff.setPassword(resultSet.getString("PASSWORD"));
                staff.setLast_update(new Date(resultSet.getTimestamp("LAST_UPDATE").getTime()));
                staffList.add(staff);

            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StaffDAOImpl.getAllForAddress()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return  staffList;
    }

    @Override
    public List<Staff> getAllForStore(Store store) throws DBException {
        Connection connection = null;
        List<Staff> staffList = new ArrayList<Staff>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT STAFF_ID, FIRST_NAME, LAST_NAME, ADDRESS_ID, PICTURE,EMAIL,STORE_ID," +
                            "ACTIVE, USERNAME, PASSWORD, LAST_UPDATE FROM STAFF WHERE STORE_ID = ?");
            preparedStatement.setInt(1,store.getStore_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Staff staff = new Staff();
                staff.setStaff_id(resultSet.getShort("STAFF_ID"));
                staff.setFirst_name(resultSet.getString("FIRST_NAME"));
                staff.setLast_name(resultSet.getString("LAST_NAME"));
                staff.setAddress_id(resultSet.getShort("ADDRESS_ID"));
                staff.setPicture(new SerialBlob(resultSet.getBlob("PICTURE")));
                staff.setEmail(resultSet.getString("EMAIL"));
                staff.setStore_id(resultSet.getInt("STORE_ID"));
                staff.setActive(resultSet.getInt("ACTIVE"));
                staff.setUsername(resultSet.getString("USERNAME"));
                staff.setPassword(resultSet.getString("PASSWORD"));
                staff.setLast_update(new Date(resultSet.getTimestamp("LAST_UPDATE").getTime()));
                staffList.add(staff);

            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute StaffDAOImpl.getAllForAddress()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return  staffList;
    }
}
