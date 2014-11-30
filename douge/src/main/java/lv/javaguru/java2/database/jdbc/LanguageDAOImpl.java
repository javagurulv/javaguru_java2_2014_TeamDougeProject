package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.Language;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 21.10.2014.
 */
@Component("JDBC_LanguageDAO")
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
            resultSet.close();
            preparedStatement.close();
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
            resultSet.close();
            preparedStatement.close();
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

            preparedStatement.close();


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

            preparedStatement.close();


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

    @Override
    public List<Language> getAll() throws DBException {
        List<Language> languages = new ArrayList<Language>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT LANGUAGE_ID, NAME, LAST_UPDATE FROM LANGUAGE");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Language language = new Language();
                language.setLanguage_id(resultSet.getInt("LANGUAGE_ID"));
                language.setName(resultSet.getString("NAME"));
                language.setLast_update(new Date(resultSet.getTimestamp("LAST_UPDATE").getTime()));
                languages.add(language);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute LanguageDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }


        return languages;
    }
}
