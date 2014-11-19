package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.Film_TextDAO;
import lv.javaguru.java2.domain.Film_Text;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Sergo on 21.10.2014.
 */
@Component
public class Film_TextDAOImpl extends DAOImpl implements Film_TextDAO {

    @Override
    public Film_Text getById(int id) throws DBException {
        Film_Text film_text = null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT FILM_ID, TITLE, DESCRIPTION FROM FILM_TEXT WHERE" +
                            " FILM_ID = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                film_text = new Film_Text();
                film_text.setFilm_id(resultSet.getInt("FILM_ID"));
                film_text.setTitle(resultSet.getString("TITLE"));
                film_text.setDescription(resultSet.getString("DESCRIPTION"));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_TextDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

        return film_text;
    }

    @Override
    public void create(Film_Text film_text) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO FILM_TEXT(FILM_ID, TITLE, DESCRIPTION) " +
                            "VALUES (?,?,?)");
            preparedStatement.setInt(1,film_text.getFilm_id());
            preparedStatement.setString(2, film_text.getTitle());
            preparedStatement.setString(3, film_text.getDescription());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_TextDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Film_Text film_text) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE FILM_TEXT SET TITLE = ?, DESCRIPTION = ? " +
                            "WHERE FILM_ID = ?");

            preparedStatement.setString(1, film_text.getTitle());
            preparedStatement.setString(2, film_text.getDescription());
            preparedStatement.setInt(3,film_text.getFilm_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_TextDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM FILM_TEXT WHERE FILM_ID = ?");

            preparedStatement.setInt(1,id );

            preparedStatement.executeUpdate();

            preparedStatement.close();

        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_TextDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }
}
