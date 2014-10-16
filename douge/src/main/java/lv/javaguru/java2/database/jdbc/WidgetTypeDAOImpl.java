package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.WidgetTypeDAO;
import lv.javaguru.java2.domain.WidgetType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radchuk on 10/11/2014.
 */
public class WidgetTypeDAOImpl extends  DAOImpl implements WidgetTypeDAO {

    public void create (WidgetType widgetType)throws DBException{
        if (widgetType == null){
            return;
        }
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into WIDGET_TYPES values (default ,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,widgetType.getName());
            preparedStatement.setString(2,widgetType.getComments());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                widgetType.setId(resultSet.getLong(1));
            }

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute WidgetTypeDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public WidgetType getById(Long id)throws DBException{
        WidgetType widgetType = null;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from WIDGET_TYPES where ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                widgetType = new WidgetType();
                widgetType.setId(resultSet.getLong(1));
                widgetType.setName(resultSet.getString(2));
                widgetType.setComments(resultSet.getString(3));
            }

        }
        catch (Throwable e){
            System.out.println("Exception while execute WidgetTypeDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

        return  widgetType;
    }

    public void update (WidgetType widgetType) throws DBException{
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE WIDGET_TYPES set NAME = ?, COMMENTS = ? where ID = ?");
            preparedStatement.setString(1, widgetType.getName());
            preparedStatement.setString(2, widgetType.getComments());
            preparedStatement.setLong(3,widgetType.getId());
            preparedStatement.executeUpdate();

        }
        catch (Throwable e){
            System.out.println("Exception while execute WidgetTypeDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }


    public void delete(Long id) throws DBException{
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from WIDGET_TYPES where ID = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute WidgetTypeDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        ;
    }

    public List<WidgetType> getAll()throws DBException{
        List<WidgetType> widgetTypes = new ArrayList<WidgetType>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from WIDGET_TYPES");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                WidgetType widgetType = new WidgetType();
                widgetType.setId(resultSet.getLong(1));
                widgetType.setName(resultSet.getString(2));
                widgetType.setComments(resultSet.getString(3));
                widgetTypes.add(widgetType);
            }
        }
        catch (Throwable e){
            System.out.println("Exception while execute WidgetTypeDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }



        return  widgetTypes;
    }
}
