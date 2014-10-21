package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.domain.Language;

import java.sql.*;

/**
 * Created by Sergo on 21.10.2014.
 */
public class LanguageDAOImpl extends DAOImpl implements LanguageDAO {
    @Override
    public Language getById(int id) throws DBException {
        Language language =  null;
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT LANGUAGE_ID, NAME, LAST_UPDATE FROM LANGUAGE WHERE LANGUAGE_ID = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                language = new Language(resultSet.getInt("LANGUAGE_ID"), resultSet.getString("NAME"), new Date(resultSet.getTimestamp("LAST_UPDATE").getTime()));
            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute LanguageDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return language;
    }

    @Override
    public void create(Language language) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO LANGUAGE(LANGUAGE_ID, NAME, LAST_UPDATE) " +
                            "VALUES (default ,? ,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, language.getName());
            preparedStatement.setTimestamp(2,new Timestamp(language.getLast_update().getTime()));
            preparedStatement.executeUpdate();


            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next())
            {
                language.setLanguage_id(resultSet.getInt(1));
            }
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute LanguageDAOImpl.create()");
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
                    connection.prepareStatement("DELETE FROM LANGUAGE WHERE LANGUAGE_ID = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();



        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute LanguageDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Language language) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE LANGUAGE SET NAME = ?, LAST_UPDATE = ? WHERE LANGUAGE_ID = ?");
            preparedStatement.setString(1, language.getName());
            preparedStatement.setTimestamp(2, new Timestamp(language.getLast_update().getTime()));
            preparedStatement.setInt(3,language.getLanguage_id());

            preparedStatement.executeUpdate();



        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute LanguageDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }
}
