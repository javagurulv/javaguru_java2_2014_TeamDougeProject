package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juris on 17.10.2014.
 */
public class ActorDAOImpl extends DAOImpl implements ActorDAO {
    @Override
    public Actor getByID(Short actor_id) throws DBException {
        Actor actor = null;
        Connection connection=null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from ACTOR where actor_id = ?");
            preparedStatement.setShort(1,actor_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                actor = new Actor();
                actor.setActor_id(resultSet.getShort(1));
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACTOR VALUES (default ,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,actor.getFirst_name());
            preparedStatement.setString(2,actor.getLast_name());
            preparedStatement.setDate(3,  actor.getLast_update());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                actor.setActor_id(resultSet.getShort(1));
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
    public void delete(Short actor_id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ACTOR WHERE ACTOR_ID = ?");
            preparedStatement.setLong(1,actor_id);
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
                    connection.prepareStatement("UPDATE ACTOR SET first_name = ?, last_name = ?, last_update = ? where actor_id = ?");
            preparedStatement.setString(1, actor.getFirst_name());
            preparedStatement.setString(2, actor.getLast_name());
            preparedStatement.setDate(3, actor.getLast_update());
            preparedStatement.setShort(4, actor.getActor_id());
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACTOR ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Actor actor = new Actor();
                actor.setActor_id(resultSet.getShort("ACTOR_ID"));
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
