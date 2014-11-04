package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserTypeDAO;
import lv.javaguru.java2.domain.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

/**
 * Created by Radchuk on 10/11/2014.
 */
public class UserTypeDAOImpl extends DAOImpl implements UserTypeDAO {

    public void create(UserType userType) throws DBException{

        if (userType == null){
            return;
        }
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into USER_TYPES values (default, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userType.getTypeName());
           // preparedStatement.setString(2, user.getLastName());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                userType.setTypeId(rs.getLong(1));
            }
            rs.close();
            preparedStatement.close();
        }
        catch (Throwable e) {
            System.out.println("Exception while execute UserTypeDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public UserType getById(Long id) throws DBException{
        UserType userType = null;
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from USER_TYPES where ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userType = new UserType();
                userType.setTypeId(resultSet.getLong(1));
                userType.setTypeName(resultSet.getString(2));
            }
            resultSet.close();
            preparedStatement.close();
            return userType;
        }
        catch (Throwable e){
            System.out.println("Exception while execute UserTypeDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public void delete(Long id) throws DBException{
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from USER_TYPES where ID = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e){
            System.out.println("Exception while execute UserTypeDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public void update(UserType userType) throws DBException{
        if(userType == null){
            return;
        }
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update USER_TYPES set NAME= ? where ID = ?");
            preparedStatement.setString(1,userType.getTypeName());
            preparedStatement.setLong(2,userType.getTypeId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e){
            System.out.println("Exception while execute UserTypeDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public List<UserType> getAll() throws DBException{
        Connection connection = null;
        List<UserType> typesList = new ArrayList<UserType>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select *  from USER_TYPES");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UserType userType = new UserType();
                userType.setTypeId(resultSet.getLong(1));
                userType.setTypeName(resultSet.getString(2));
                typesList.add(userType);
            }
            resultSet.close();
            preparedStatement.close();
            return typesList;
        }
        catch (Throwable e){
            System.out.println("Exception while execute UserTypeDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }
}
