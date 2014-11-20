package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.Film;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 17.10.2014.
 */
@Component
public class FilmDAOImpl extends DAOImpl implements FilmDAO {

    private Film buildFilmData(ResultSet resultSet) throws SQLException {
        Film film = new Film();
        film.setFilm_id(resultSet.getInt("film_id"));
        film.setTitle(resultSet.getString("title"));
        film.setDescription(resultSet.getString("description"));
        film.setRelease_year(resultSet.getInt("release_year"));
        film.setLanguage(resultSet.getString("language"));
        film.setOriginal_language(resultSet.getString("original_language"));
        film.setRental_duration(resultSet.getInt("rental_duration"));
        film.setRental_rate(resultSet.getFloat("rental_rate"));
        film.setLength(resultSet.getInt("length"));
        film.setReplacement_cost(resultSet.getFloat("replacement_cost"));
        film.setRating(resultSet.getString("rating"));
        film.setSpecial_features(resultSet.getString("special_features"));
        film.setLast_update(resultSet.getDate("last_update"));
        return  film;
    }

    @Override
    public List<Film> getAllFromRange(int from, int to) throws DBException {
        List<Film> films = new ArrayList<Film>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select f.film_id,"+
                    "f.title, f.description, f.release_year, l.name as language, l1.name as original_language, "+
                    "f.rating, f.special_features, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, "+
                    "f.last_update "+
                    "from film f " +
                    "left join language l on f.language_id = l.language_id "+
                    "left join language l1 on (f.original_language_id = l1.language_id or f.language_id = l1.language_id )" +
                    " limit " + from + "," + to);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                films.add(buildFilmData(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute FilmDAOImpl.getAllFromRange()" );
        }
        finally {
            closeConnection(connection);
        }

        return films;
    }


    @Override
    public Film getByID(Long id) throws DBException {
        Film film = null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select f.film_id,"+
                    "f.title, f.description, f.release_year, l.name as language, l1.name as original_language, "+
                    "f.rating, f.special_features, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, "+
                    "f.last_update "+
                    "from film f " +
                    "left join language l on f.language_id = l.language_id "+
                    "left join language l1 on (f.original_language_id = l1.language_id or f.language_id = l1.language_id)" +
                    " where f.film_id = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                film  = buildFilmData(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute FilmDAOImpl.getByID()" );
        }
        finally {
            closeConnection(connection);
        }

        return film;
    }



    @Override
    public List<Film> getAll() throws DBException {
        Connection connection = null;
        List<Film> films = new ArrayList<Film>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select f.film_id, "+
                    "f.title, f.description, f.release_year, l.name as language, l1.name as original_language, "+
                    "f.rating, f.special_features, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, "+
                    "f.last_update "+
                    "from film f " +
                    "left join language l on f.language_id = l.language_id "+
                    "left join language l1 on (f.original_language_id = l1.language_id or f.language_id = l1.language_id)");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                films.add(buildFilmData(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            handleException(e, "Exception while execute FilmDAOImpl.getAll()");
        }
        finally {
            closeConnection(connection);
        }
        return films;
    }

    @Override
    public Integer getFilmsAmount() throws DBException {
        Integer amount = 0;
        Connection connection = null;
        try
        {
            /*select count(film_id) as count from film*/
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select count(film_id) as count from film");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) amount = resultSet.getInt("count");
        }
        catch (Throwable e)
        {
            handleException(e,"Exception while execute FilmDAOImpl.getFilmsAmount" );
        }
        finally {
            closeConnection(connection);
        }
        return amount;
    }
}
