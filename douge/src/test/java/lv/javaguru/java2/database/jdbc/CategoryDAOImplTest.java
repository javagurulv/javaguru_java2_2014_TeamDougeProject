package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Juris on 23.10.2014.
 */
public class CategoryDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    private Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category.setLast_update(new Date());

        return category;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {

        Category category = createCategory("name");

        categoryDAO.create(category);
        Category categoryFromDB = categoryDAO.getById(category.getCategory_id());
        assertNotNull(categoryFromDB);
        assertEquals(category.getName(), categoryFromDB.getName());
        assertEquals(category.getCategory_id(), categoryFromDB.getCategory_id());
        assertionEqualsDateCustom(category.getLast_update(), categoryFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException {
        Category category = createCategory("Name");
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

        Category category = createCategory("Name");
        categoryDAO.create(category);

        category.setName("Newname");
        category.setLast_update(new Date());

        categoryDAO.update(category);

        Category categoryFromDB = categoryDAO.getById(category.getCategory_id());

        assertNotNull(categoryFromDB);
        assertEquals(category.getName(), categoryFromDB.getName());
        assertEquals(category.getCategory_id(), categoryFromDB.getCategory_id());
        assertionEqualsDateCustom(category.getLast_update(),categoryFromDB.getLast_update());
    }

    @Test
    public void testGetAll() throws DBException
    {

        Category category1 = createCategory("category1");
        Category category2 = createCategory("category2");
        categoryDAO.create(category1);
        categoryDAO.create(category2);

        List<Category> categories = categoryDAO.getAll();

        assertEquals(categories.size(),2);
    }
    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }

}