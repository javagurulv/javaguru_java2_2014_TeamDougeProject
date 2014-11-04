package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radchuk on 10/11/2014.
 */
public class DashboardDAOImpl extends DAOImpl implements DashboardDAO {

    public void create(Dashboard dashboard)throws DBException{
        if(dashboard == null){
            return;
        }
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into DASHBOARDS VALUES(default ,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1,dashboard.getUser_id());
            preparedStatement.setString(2,dashboard.getComments());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                dashboard.setId(resultSet.getLong(1));
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute DashboardDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public Dashboard getById(Long id) throws DBException{
        Dashboard dashboard = null;
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from DASHBOARDS where id = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                dashboard = new Dashboard();
                dashboard.setId(resultSet.getLong(1));
                dashboard.setUser_id(resultSet.getLong(2));
                dashboard.setComments(resultSet.getString(3));
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute DashboardDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return dashboard;
    }


    public void update (Dashboard dashboard) throws DBException{
        if (dashboard == null){
            return;
        }
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update DASHBOARDS set USER_ID = ?, COMMENTS = ? where ID = ?");
            preparedStatement.setLong(1,dashboard.getUser_id());
            preparedStatement.setString(2,dashboard.getComments());
            preparedStatement.setLong(3, dashboard.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e){
            System.out.println("Exception while execute DashboardDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public void delete (Long id) throws DBException{
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from DASHBOARDS where ID = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e){
            System.out.println("Exception while execute DashboardDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public List<Dashboard> getAll() throws DBException{
        List<Dashboard> dashboards = new ArrayList<Dashboard>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from DASHBOARDS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Dashboard dashboard = new Dashboard();
                dashboard.setId(resultSet.getLong(1));
                dashboard.setUser_id(resultSet.getLong(2));
                dashboard.setComments(resultSet.getString(3));
                dashboards.add(dashboard);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e){
            System.out.println("Exception while execute DashboardDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return dashboards;
    }

    public List<Dashboard> getAllForUser(User user) throws DBException{
        List<Dashboard> dashboards = new ArrayList<Dashboard>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from DASHBOARDS where USER_ID = ?");
            preparedStatement.setLong(1,user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Dashboard dashboard = new Dashboard();
                dashboard.setId(resultSet.getLong(1));
                dashboard.setUser_id(resultSet.getLong(2));
                dashboard.setComments(resultSet.getString(3));
                dashboards.add(dashboard);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute DashboardDAOImpl.getAllForUser()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return dashboards;
    }

}
