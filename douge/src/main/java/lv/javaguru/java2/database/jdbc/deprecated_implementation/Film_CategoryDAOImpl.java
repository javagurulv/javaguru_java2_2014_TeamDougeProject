package lv.javaguru.java2.database.jdbc.deprecated_implementation;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.deprecated_dao.Film_CategoryDAO;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.deprecated_classes.Category;
import lv.javaguru.java2.domain.deprecated_classes.Film_Category;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juris on 24.10.2014.
 */

@Deprecated
public class Film_CategoryDAOImpl extends DAOImpl implements Film_CategoryDAO {
    @Override
    public Film_Category getByID(Short film_id) throws DBException {
        Film_Category film_category = null;
        Connection connection=null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from FILM_CATEGORY where film_id = ?");
            preparedStatement.setShort(1,film_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                film_category = new Film_Category();
                film_category.setFilm_id(resultSet.getShort(1));
                film_category.setCategory_id(resultSet.getInt(2));
                film_category.setLast_update(resultSet.getTimestamp(3));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_CategoryDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return film_category;
    }

    @Override
    public void create(Film_Category film_category) throws DBException {

        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FILM_CATEGORY VALUES (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setShort(1, film_category.getFilm_id());
            preparedStatement.setInt(2, film_category.getCategory_id());
            preparedStatement.setTimestamp(3, new Timestamp(film_category.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                film_category.setFilm_id(resultSet.getShort(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_CategoryDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }

    }

    @Override
    public void delete(Short film_id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FILM_CATEGORY WHERE FILM_ID = ?");
            preparedStatement.setShort(1, film_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Fim_categoryDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Film_Category film_category) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE film_category SET category_id = ?, last_update = ? where film_id = ?");
            preparedStatement.setInt(1, film_category.getCategory_id());
            preparedStatement.setTimestamp(2, new Timestamp(film_category.getLast_update().getTime()));
            preparedStatement.setShort(3, film_category.getFilm_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_CategoryDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Film_Category> getAll() throws DBException {
        List<Film_Category> film_categories=new ArrayList<Film_Category>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM_CATEGORY ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Film_Category film_category=new Film_Category();
                film_category.setFilm_id(resultSet.getShort("FILM_ID"));
                film_category.setCategory_id(resultSet.getInt("CATEGORY_ID"));
                film_category.setLast_update(resultSet.getDate("LAST_UPDATE"));
                film_categories.add(film_category);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute Film_CategoryDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return film_categories;
    }

    @Override
    public List<Film_Category> getAllForCategory(Category category) throws DBException {
        List<Film_Category> film_categories = new ArrayList<Film_Category>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from FILM_CATEGORY where CATEGORY_ID = ?");
            preparedStatement.setInt(1, category.getCategory_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Film_Category film_category = new Film_Category();
                film_category.setFilm_id(resultSet.getShort(1));
                film_category.setCategory_id(resultSet.getInt(2));
                film_category.setLast_update(resultSet.getDate(3));
                film_categories.add(film_category);
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Throwable e){
            System.out.println("Exception while execute Film_CategoryDAOImpl.getAllForCategory()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return film_categories;
    }
}
