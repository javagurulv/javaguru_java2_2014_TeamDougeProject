package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Juris on 23.10.2014.
 */
public class CategoryDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    private Category createCategory(String name, java.sql.Date lase_update) {
        Category category = new Category();
        category.setName(name);
        category.setLast_update(lase_update);

        return category;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {

        /*избаляемся от времени в дате*/
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String inputDate = sqlDate.toString();
        String outputDate = inputDate.substring(0, 10);

        Category category = createCategory("name", sqlDate.valueOf(outputDate));

        categoryDAO.create(category);
        Category categoryFromDB = categoryDAO.getById(category.getCategory_id());
        assertNotNull(categoryFromDB);
        assertEquals(category.getName(), categoryFromDB.getName());
        assertEquals(category.getCategory_id(), categoryFromDB.getCategory_id());
        assertEquals(category.getLast_update(), categoryFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Category category = createCategory("Name", sqlDate);
        categoryDAO.create(category);
        Category categoryFromDB = categoryDAO.getById(category.getCategory_id());

        assertNotNull(categoryFromDB);

        categoryDAO.delete(category.getCategory_id());

        categoryFromDB = categoryDAO.getById(category.getCategory_id());

        assertNull(categoryFromDB);
    }

    @Test
    public void testUpdate() throws DBException
    {

        /*избавляемся от времени в дате*/
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String inputDate=sqlDate.toString();
        String outputDate=inputDate.substring(0,10);

        Category category = createCategory("Name", sqlDate.valueOf(outputDate));
        categoryDAO.create(category);

        category.setName("Newname");
        category.setLast_update(sqlDate.valueOf(outputDate));

        categoryDAO.update(category);

        Category categoryFromDB = categoryDAO.getById(category.getCategory_id());

        assertNotNull(categoryFromDB);
        assertEquals(category.getName(), categoryFromDB.getName());
        assertEquals(category.getCategory_id(), categoryFromDB.getCategory_id());
        assertEquals(category.getLast_update(),categoryFromDB.getLast_update());
    }

    @Test
    public void testGetAll() throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Category category1 = createCategory("category1", sqlDate);
        Category category2 = createCategory("category2", sqlDate);
        categoryDAO.create(category1);
        categoryDAO.create(category2);

        List<Category> categories = categoryDAO.getAll();

        assertEquals(categories.size(),2);
    }
}