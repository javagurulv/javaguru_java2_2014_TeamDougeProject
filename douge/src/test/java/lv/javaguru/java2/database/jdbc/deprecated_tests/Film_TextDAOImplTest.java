package lv.javaguru.java2.database.jdbc.deprecated_tests;

/**
 * Created by Sergo on 21.10.2014.
 */
import static org.junit.Assert.*;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.Film_TextDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.Film_TextDAOImpl;
import lv.javaguru.java2.domain.Film_Text;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class Film_TextDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private Film_TextDAO film_textDAO = new Film_TextDAOImpl();

    @Before
    public void cleanDB() throws DBException
    {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        Film_Text film_text = new Film_Text(1,"TITLE","DESCRIPTION");

        film_textDAO.create(film_text);

        Film_Text film_textFromDB = film_textDAO.getById(film_text.getFilm_id());
        assertNotNull(film_textFromDB);
        assertEquals(film_text.getFilm_id(), film_textFromDB.getFilm_id());
        assertEquals(film_text.getTitle(), film_textFromDB.getTitle());
        assertEquals(film_text.getDescription(), film_textFromDB.getDescription());
    }
    @Test
    public void testUpdate() throws DBException
    {
        Film_Text film_text = new Film_Text(1,"TITLE","DESCRIPTION");

        film_textDAO.create(film_text);
        film_text.setTitle("NEW TITLE");
        film_text.setDescription("NEW DESCRIPTION");
        film_textDAO.update(film_text);
        Film_Text film_textFromDB = film_textDAO.getById(film_text.getFilm_id());
        assertNotNull(film_textFromDB);
        assertEquals(film_text.getFilm_id(), film_textFromDB.getFilm_id());
        assertEquals(film_text.getTitle(), film_textFromDB.getTitle());
        assertEquals(film_text.getDescription(), film_textFromDB.getDescription());
    }
    @Test
    public void testDelete() throws DBException
    {
        Film_Text film_text = new Film_Text(1,"TITLE","DESCRIPTION");

        film_textDAO.create(film_text);
        film_textDAO.delete(film_text.getFilm_id());

        assertNull(film_textDAO.getById(film_text.getFilm_id()));
    }
}
