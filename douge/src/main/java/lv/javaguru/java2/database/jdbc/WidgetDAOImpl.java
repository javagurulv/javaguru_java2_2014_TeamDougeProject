package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.WidgetDAO;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.Widget;
import java.sql.Connection;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 16.10.2014.
 */
public class WidgetDAOImpl extends DAOImpl implements WidgetDAO {
    @Override
    public Widget getByID(Long id) throws DBException {
        Widget widget = null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from WIDGETS where id = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                widget = new Widget();
                widget.setWidget_id(resultSet.getLong(1));
                widget.setDashboard_id(resultSet.getLong(2));
                widget.setComments(resultSet.getString(3));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute WidgetDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return widget;
    }

    @Override
    public void create(Widget widget) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO WIDGETS VALUES (default ,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,widget.getDashboard_id());
            preparedStatement.setString(2,widget.getComments());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                widget.setWidget_id(resultSet.getLong(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute WidgetDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM WIDGETS WHERE ID = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute WidgetDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

    }

    @Override
    public void update(Widget widget) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE WIDGETS SET dashboard_id = ?, comments = ? where id = ?");
            preparedStatement.setLong(1,widget.getDashboard_id());
            preparedStatement.setString(2,widget.getComments());
            preparedStatement.setLong(3,widget.getWidget_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute WidgetDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

    }

    @Override
    public List<Widget> getAll() throws DBException {
        List<Widget> widgets = new ArrayList<Widget>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WIDGETS ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Widget widget = new Widget();
                widget.setWidget_id(resultSet.getLong("ID"));
                widget.setDashboard_id(resultSet.getLong("DASHBOARD_ID"));
                widget.setComments(resultSet.getString("COMMENTS"));
                widgets.add(widget);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute WidgetDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return widgets;
    }

    @Override
    public List<Widget> getAllForDashboard(Dashboard dashboard) throws DBException {
        List<Widget> widgets = new ArrayList<Widget>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WIDGETS WHERE DASHBOARD_ID = ?");
            preparedStatement.setLong(1,dashboard.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Widget widget = new Widget();
                widget.setWidget_id(resultSet.getLong("ID"));
                widget.setDashboard_id(resultSet.getLong("DASHBOARD_ID"));
                widget.setComments(resultSet.getString("COMMENTS"));
                widgets.add(widget);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute WidgetDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return widgets;
    }
}
