package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.Film_ActorDAO;
import lv.javaguru.java2.domain.Film_Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radchuk on 10/20/2014.
 */
public class Film_ActorDAOImpl extends DAOImpl implements Film_ActorDAO{
    @Override
    public List<Film_Actor> getAllByActorID(int id) throws DBException {
        List<Film_Actor> film_actors = new ArrayList<Film_Actor>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT ACTOR_ID,FILM_ID, LAST_UPDATE FROM FILM_ACTOR WHERE ACTOR_ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Film_Actor film_actor = new Film_Actor();
                film_actor.setActor_id(resultSet.getInt("ACTOR_ID"));
                film_actor.setFilm_id(resultSet.getInt("FILM_ID"));
                film_actor.setLast_update(resultSet.getDate("LAST_UPDATE"));
                film_actors.add(film_actor);
            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_ActorDAOImpl.getAllByActorID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

        return film_actors;
    }

    @Override
    public void create(Film_Actor film_actor) throws DBException {

        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO FILM_ACTOR(ACTOR_ID,FILM_ID,LAST_UPDATE) VALUES (?,?,?)");
            preparedStatement.setInt(1,film_actor.getActor_id());
            preparedStatement.setInt(2, film_actor.getFilm_id());
           // preparedStatement.setDate(3, new Date(film_actor.getLast_update().getTime()));
            preparedStatement.setTimestamp(3, new Timestamp(film_actor.getLast_update().getTime()));
            preparedStatement.executeUpdate();



        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_ActorDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }



    @Override
    public void deleteByFilmID(int id) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM FILM_ACTOR where FILM_ID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_ActorDAOImpl.deleteByFilm()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Film_Actor> getAllByFilmID(int id) throws DBException {
        List<Film_Actor> film_actors = new ArrayList<Film_Actor>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT ACTOR_ID,FILM_ID, LAST_UPDATE FROM FILM_ACTOR WHERE FILM_ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Film_Actor film_actor = new Film_Actor();
                film_actor.setActor_id(resultSet.getInt("ACTOR_ID"));
                film_actor.setFilm_id(resultSet.getInt("FILM_ID"));
                film_actor.setLast_update(resultSet.getDate("LAST_UPDATE"));
                film_actors.add(film_actor);
            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_ActorDAOImpl.getAllByFilmID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

        return film_actors;
    }


}
