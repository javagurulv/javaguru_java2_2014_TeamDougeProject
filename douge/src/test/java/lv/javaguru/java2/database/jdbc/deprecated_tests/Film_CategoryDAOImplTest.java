package lv.javaguru.java2.database.jdbc.deprecated_tests;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.deprecated_dao.Film_CategoryDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.deprecated_implementation.Film_CategoryDAOImpl;
import lv.javaguru.java2.domain.deprecated_classes.Category;
import lv.javaguru.java2.domain.deprecated_classes.Film_Category;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Juris on 24.10.2014.
 */
@Ignore
public class Film_CategoryDAOImplTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private Film_CategoryDAO film_categoryDAO= new Film_CategoryDAOImpl();

    private Film_Category createFilm_Category(Short film_id, int category_id) {
        Film_Category film_category = new Film_Category();
        film_category.setFilm_id(film_id);
        film_category.setCategory_id(category_id);
        film_category.setLast_update(new Date());

        return film_category;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        Film_Category film_category = createFilm_Category((short)1,1);

        film_categoryDAO.create(film_category);
        Film_Category film_categoryFromDB = film_categoryDAO.getByID(film_category.getFilm_id());
        assertNotNull(film_categoryFromDB);
        assertEquals(film_category.getFilm_id(), film_categoryFromDB.getFilm_id());
        assertEquals(film_category.getCategory_id(), film_categoryFromDB.getCategory_id());
        assertionEqualsDateCustom(film_category.getLast_update(), film_categoryFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        Film_Category film_category = createFilm_Category((short) 1,1);
        film_categoryDAO.create(film_category);
        Film_Category film_categoryFromDB = film_categoryDAO.getByID(film_category.getFilm_id());

        assertNotNull(film_categoryFromDB);

        film_categoryDAO.delete(film_category.getFilm_id());

        film_categoryFromDB = film_categoryDAO.getByID(film_category.getFilm_id());

        assertNull(film_categoryFromDB);
    }
    @Test
    public void testUpdate() throws DBException
    {
        Film_Category film_category = createFilm_Category((short)1,1);
        film_categoryDAO.create(film_category);

        film_category.setCategory_id(2);
        film_category.setLast_update(new Date());

        film_categoryDAO.update(film_category);

        Film_Category film_categoryFromDB = film_categoryDAO.getByID(film_category.getFilm_id());

        assertNotNull(film_categoryFromDB);
        assertEquals(film_category.getCategory_id(), film_categoryFromDB.getCategory_id());
        assertionEqualsDateCustom(film_category.getLast_update(),film_categoryFromDB.getLast_update());
    }

    @Test
    public void testGetAll() throws DBException
    {
        Film_Category film_category1 = createFilm_Category((short) 1, 2);
        Film_Category film_category2 = createFilm_Category((short) 2, 3);
        film_categoryDAO.create(film_category1);
        film_categoryDAO.create(film_category2);

        List<Film_Category> addresses = film_categoryDAO.getAll();

        assertEquals(addresses.size(),2);
    }

    @Test
    public void testGetAllForCategory()throws DBException
    {
        Film_Category film_category1 = createFilm_Category((short) 1, 1);
        film_categoryDAO.create(film_category1);

        Film_Category film_category2 = createFilm_Category((short) 2,1);
        film_categoryDAO.create(film_category2);

        Film_Category film_category3 = createFilm_Category((short) 3,1);
        film_categoryDAO.create(film_category3);

        Film_Category film_category4 = createFilm_Category((short) 4,2);
        film_categoryDAO.create(film_category4);


        Category category=new Category();

        category.setCategory_id((short)1);

        List<Film_Category> film_categories = film_categoryDAO.getAllForCategory(category);

        assertEquals(film_categories.size(), 3);

        category.setCategory_id((short)2);

        film_categories = film_categoryDAO.getAllForCategory(category);

        assertEquals(film_categories.size(), 1);

    }

    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }

}
