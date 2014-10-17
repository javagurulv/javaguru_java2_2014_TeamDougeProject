package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juris on 17.10.2014.
 */
public class ActorDAOImpl extends DAOImpl implements ActorDAO {
    @Override
    public Actor getByID(Long id) throws DBException {
        Actor actor = null;
        Connection connection=null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from ACTORS where id = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                actor = new Actor();
                actor.setActor_id(resultSet.getLong(1));
                actor.setFirst_name(resultSet.getString(2));
                actor.setLast_name(resultSet.getString(3));
                actor.setLast_update(resultSet.getDate(4));
            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute ActorDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return actor;
    }

    @Override
    public void create(Actor actor) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACTORS VALUES (default ,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,actor.getFirst_name());
            preparedStatement.setString(2,actor.getLast_name());
            preparedStatement.setDate(3, (java.sql.Date) actor.getLast_update());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                actor.setActor_id(resultSet.getLong(1));
            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute ActorDAOImpl.create()");
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ACTORS WHERE ID = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute ActorDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Actor actor) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE WIDGETS SET first_name = ?, last_name = ?, last_update = ? where id = ?");
            preparedStatement.setString(1, actor.getFirst_name());
            preparedStatement.setString(2, actor.getLast_name());
            preparedStatement.setDate(3, (java.sql.Date) actor.getLast_update());
            preparedStatement.executeUpdate();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute ActorDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Actor> getAll() throws DBException {
        List<Actor> actors = new ArrayList<Actor>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACTORS ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Actor actor = new Actor();
                actor.setActor_id(resultSet.getLong("ID"));
                actor.setFirst_name(resultSet.getString("FIRST_NAME"));
                actor.setLast_name(resultSet.getString("LAST_NAME"));
                actor.setLast_update(resultSet.getDate("LAST_UPDATE"));
                actors.add(actor);
            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute ActorDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return actors;
    }


}
