package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juris on 23.10.2014.
 */

@Component
public class CategoryDAOImpl extends DAOImpl implements CategoryDAO {
    @Override
    public void create(Category category) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CATEGORY VALUES (default ,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setTimestamp(2, new Timestamp(category.getLast_update().getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                category.setCategory_id(resultSet.getShort(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CategoryDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public Category getById(int category_id) throws DBException {
        Category category = null;
        Connection connection=null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from category where category_id = ?");
            preparedStatement.setInt(1, category_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                category = new Category();
                category.setCategory_id(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setLast_update(resultSet.getDate(3));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CategoryDAOImpl.getByID()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return category;
    }

    @Override
    public void update(Category category) throws DBException {
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE CATEGORY SET name = ?, last_update = ? where category_id = ?");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setTimestamp(2,  new Timestamp(category.getLast_update().getTime()));
            preparedStatement.setInt(3, category.getCategory_id());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CategoryDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(int category_id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CATEGORY WHERE CATEGORY_ID = ?");
            preparedStatement.setInt(1, category_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CategoryDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Category> getAll() throws DBException {
        List<Category> categories = new ArrayList<Category>();
        Connection connection = null;
        try
        {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CATEGORY ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Category category = new Category();
                category.setCategory_id(resultSet.getShort("CATEGORY_ID"));
                category.setName(resultSet.getString("NAME"));
                category.setLast_update(resultSet.getDate("LAST_UPDATE"));
                categories.add(category);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (Throwable e)
        {
            System.out.println("Exception while execute CategoryDAOImpl.getAll()");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return categories;
    }
}
