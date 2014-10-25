package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.StaffDAO;
import lv.javaguru.java2.domain.Address;
import lv.javaguru.java2.domain.Staff;
import lv.javaguru.java2.domain.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Juris on 25.10.2014.
 */
public class StaffDAOImpl extends DAOImpl implements StaffDAO {
    @Override
    public void create(Staff staff) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO STAFF VALUES (default,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
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
        return null;
    }

    @Override
    public void update(Staff staff) throws DBException {

    }

    @Override
    public void delete(int staff_id) throws DBException {

    }

    @Override
    public List<Staff> getAll() throws DBException {
        return null;
    }

    @Override
    public List<Staff> getAllForAddress(Address address) throws DBException {
        return null;
    }

    @Override
    public List<Staff> getAllForStore(Store store) throws DBException {
        return null;
    }
}
