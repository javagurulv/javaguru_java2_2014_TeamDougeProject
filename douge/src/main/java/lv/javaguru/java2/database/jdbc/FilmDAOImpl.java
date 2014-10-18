package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.domain.Film;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 17.10.2014.
 */
public class FilmDAOImpl extends DAOImpl implements FilmDAO {
    @Override
    public void create(Film film) throws DBException {
        Connection connection = null;
        String sqlString = "INSERT INTO film " +
                "(film_id,title,description,release_year,language_id,original_language_id," +
                "rental_duration,rental_rate,length,replacement_cost,rating,special_features," +
                "last_update) " +
                "VALUES( default,?,?,?,?,?,?,?,?,?,?,?,?);";
        try
        {

            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sqlString, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,film.getTitle());
            preparedStatement.setString(2,film.getDescription());
            preparedStatement.setLong(3,film.getRelease_year());
            preparedStatement.setLong(4,film.getLanguage_id());
            preparedStatement.setLong(5,film.getOriginal_language_id());
            preparedStatement.setLong(6,film.getRental_duration());
            preparedStatement.setFloat(7,film.getRental_rate());
            preparedStatement.setLong(8,film.getLength());
            preparedStatement.setFloat(9,film.getReplacement_cost());
            preparedStatement.setString(10,film.getRating());
            preparedStatement.setString(11,film.getSpecial_features());
            //preparedStatement.setDate(12, (Date)film.getLast_update());
            preparedStatement.setDate(12,new Date(film.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next())
            {
                film.setFilm_id((int) resultSet.getLong(1));
            }
        }
        catch (Throwable e)
        {

            System.out.println("Exception while execute FilmDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

    }

    @Override
    public void delete(Long id) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM FILM WHERE FILM_ID = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute FilmDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }

    }

    @Override
    public Film getByID(Long id) throws DBException {
        Film film = null;
        Connection connection = null;
       /* String sqlString = "SELECT film_id,title,description,release_year, language_id,original_language_id,rental_duration," +
                           "rental_rate,length,replacement_cost,rating,special_features,last_update" +
                           "FROM film WHERE FILM_ID = ? ";*/
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID = ?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                film = new Film();
                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setDescription(resultSet.getString("description"));
                film.setRelease_year(resultSet.getInt("release_year"));
                film.setLanguage_id(resultSet.getInt("language_id"));
                film.setOriginal_language_id(resultSet.getInt("original_language_id"));
                film.setRental_duration(resultSet.getInt("rental_duration"));
                film.setRental_rate(resultSet.getFloat("rental_rate"));
                film.setLength(resultSet.getInt("length"));
                film.setReplacement_cost(resultSet.getFloat("replacement_cost"));
                film.setRating(resultSet.getString("rating"));
                film.setSpecial_features(resultSet.getString("special_features"));
                film.setLast_update(resultSet.getDate("last_update"));

            }

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute FilmDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

        return film;
    }

    @Override
    public void update(Film film) throws DBException {
        Connection connection = null;
        String sqlString = "UPDATE film SET "+
                "title = ?, description = ?, release_year = ?, language_id = ?, original_language_id = ?, rental_duration = ?,"+
                "rental_rate = ?, length = ?, replacement_cost = ?,  rating = ?, special_features = ?, last_update = ? " +
                "WHERE film_id = ?";
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sqlString);
            preparedStatement.setString(1,film.getTitle());
            preparedStatement.setString(2,film.getDescription());
            preparedStatement.setLong(3,film.getRelease_year());
            preparedStatement.setLong(4,film.getLanguage_id());
            preparedStatement.setLong(5,film.getOriginal_language_id());
            preparedStatement.setLong(6,film.getRental_duration());
            preparedStatement.setFloat(7,film.getRental_rate());
            preparedStatement.setLong(8,film.getLength());
            preparedStatement.setFloat(9,film.getReplacement_cost());
            preparedStatement.setString(10,film.getRating());
            preparedStatement.setString(11,film.getSpecial_features());
            preparedStatement.setDate(12, new Date(film.getLast_update().getTime()));//(Date)film.getLast_update());
            preparedStatement.setInt(13, film.getFilm_id());
            preparedStatement.executeUpdate();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute FilmDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

    }

    @Override
    public List<Film> getAll() throws DBException {
        /*String sqlString = "SELECT film_id,title,description,release_year, language_id,original_language_id,rental_duration," +
                "rental_rate,length,replacement_cost,rating,special_features,last_update" +
                "FROM film ";*/
        Connection connection = null;
        List<Film> films = new ArrayList<Film>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Film film = new Film();
                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setDescription(resultSet.getString("description"));
                film.setRelease_year(resultSet.getInt("release_year"));
                film.setLanguage_id(resultSet.getInt("language_id"));
                film.setOriginal_language_id(resultSet.getInt("original_language_id"));
                film.setRental_duration(resultSet.getInt("rental_duration"));
                film.setRental_rate(resultSet.getFloat("rental_rate"));
                film.setLength(resultSet.getInt("length"));
                film.setReplacement_cost(resultSet.getFloat("replacement_cost"));
                film.setRating(resultSet.getString("rating"));
                film.setSpecial_features(resultSet.getString("special_features"));
                film.setLast_update(resultSet.getDate("last_update"));
                films.add(film);

            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute FilmDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return films;
    }

    @Override
    public List<Film> getAllByLanguageId(Long languageID) throws DBException {
        /*String sqlString = "SELECT film_id,title,description,release_year, language_id,original_language_id,rental_duration," +
                "rental_rate,length,replacement_cost,rating,special_features,last_update" +
                "FROM film WHERE language_id = ?";*/
        Connection connection = null;
        List<Film> films = new ArrayList<Film>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM WHERE LANGUAGE_ID = ?");
            preparedStatement.setLong(1,languageID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Film film = new Film();
                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setDescription(resultSet.getString("description"));
                film.setRelease_year(resultSet.getInt("release_year"));
                film.setLanguage_id(resultSet.getInt("language_id"));
                film.setOriginal_language_id(resultSet.getInt("original_language_id"));
                film.setRental_duration(resultSet.getInt("rental_duration"));
                film.setRental_rate(resultSet.getFloat("rental_rate"));
                film.setLength(resultSet.getInt("length"));
                film.setReplacement_cost(resultSet.getFloat("replacement_cost"));
                film.setRating(resultSet.getString("rating"));
                film.setSpecial_features(resultSet.getString("special_features"));
                film.setLast_update(resultSet.getDate("last_update"));
                films.add(film);

            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute FilmDAOImpl.getAllByLanguageId()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return films;
    }

    @Override
    public List<Film> getAllByOriginalLanguageID(Long originalLanguageId) throws DBException {
       /* String sqlString = "SELECT film_id,title,description,release_year, language_id,original_language_id,rental_duration," +
                "rental_rate,length,replacement_cost,rating,special_features,last_update" +
                "FROM film WHERE original_language_id = ?";*/
        Connection connection = null;
        List<Film> films = new ArrayList<Film>();
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM WHERE ORIGINAL_LANGUAGE_ID = ?");
            preparedStatement.setLong(1,originalLanguageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Film film = new Film();
                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setDescription(resultSet.getString("description"));
                film.setRelease_year(resultSet.getInt("release_year"));
                film.setLanguage_id(resultSet.getInt("language_id"));
                film.setOriginal_language_id(resultSet.getInt("original_language_id"));
                film.setRental_duration(resultSet.getInt("rental_duration"));
                film.setRental_rate(resultSet.getFloat("rental_rate"));
                film.setLength(resultSet.getInt("length"));
                film.setReplacement_cost(resultSet.getFloat("replacement_cost"));
                film.setRating(resultSet.getString("rating"));
                film.setSpecial_features(resultSet.getString("special_features"));
                film.setLast_update(resultSet.getDate("last_update"));
                films.add(film);

            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute FilmDAOImpl.getAllByOriginalLanguageID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return films;
    }
}
