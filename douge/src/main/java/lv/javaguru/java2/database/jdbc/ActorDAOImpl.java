package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Juris on 17.10.2014.
 */
@Component
public class ActorDAOImpl extends DAOImpl implements ActorDAO {

    private Actor buildActorData(ResultSet resultSet) throws SQLException {
        Actor actor = new Actor();
        actor.setActor_id(resultSet.getInt("actor_id"));
        actor.setFirst_name(resultSet.getString("first_name"));
        actor.setLast_name(resultSet.getString("last_name"));
        actor.setLast_update(resultSet.getDate("last_update"));
        return  actor;
    }

    @Override
    public Actor getByID(Short id) throws DBException {
        Actor actor = null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select actor_id, first_name, last_name, last_update from actor where actor_id = ?");
            preparedStatement.setShort(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                actor  = buildActorData(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute ActorDAOImpl.getByID()" );
        }
        finally {
            closeConnection(connection);
        }

        return actor;
    }

    @Override
    public List<Actor> getAll() throws DBException {
        Connection connection = null;
        List<Actor> actors = new ArrayList<Actor>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select actor_id, first_name, last_name, last_update from actor");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                actors.add(buildActorData(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            handleException(e, "Exception while execute ActorDAOImpl.getAll()");
        }
        finally {
            closeConnection(connection);
        }
        return actors;
    }

    @Override
    public List<Actor> getAllFromRange(int from, int amount) throws DBException {
        List<Actor> actors = new ArrayList<Actor>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select actor_id, first_name, last_name, last_update from actor limit " + from + "," + amount);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                actors.add(buildActorData(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute ActorDAOImpl.getAllFromRange()" );
        }
        finally {
            closeConnection(connection);
        }

        return actors;
    }

    @Override
    public Integer getActorsAmount() throws DBException {
        Integer amount = 0;
        Connection connection = null;
        try
        {
            /*select count(film_id) as count from film*/
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select count(actor_id) as count from actor");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) amount = resultSet.getInt("count");
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute ActorDAOImpl.getFilmsAmount" );
        }
        finally {
            closeConnection(connection);
        }
        return amount;
    }
}
