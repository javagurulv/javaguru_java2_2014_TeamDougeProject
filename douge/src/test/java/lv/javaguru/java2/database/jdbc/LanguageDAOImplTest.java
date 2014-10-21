package lv.javaguru.java2.database.jdbc;

/**
 * Created by Sergo on 21.10.2014.
 */

import static org.junit.Assert.*;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.domain.Language;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LanguageDAOImplTest {
    private DatabaseCleaner databaseCleaner  = new DatabaseCleaner();

    private LanguageDAO languageDAO = new LanguageDAOImpl();

    private void compareDates(Date date1, Date date2){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm ");
        assertEquals(simpleDateFormat.format(date1), simpleDateFormat.format(date2));
    }
    @Before
    public void cleanDB() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        Language language = new Language("LANGUAGE1", new Date());
        languageDAO.create(language);

        Language languageFromDB = languageDAO.getById(language.getLanguage_id());
        assertNotNull(languageFromDB);
        assertEquals(languageFromDB.getLanguage_id(), language.getLanguage_id());
        assertEquals(language.getName(), languageFromDB.getName());

        compareDates(language.getLast_update(), languageFromDB.getLast_update());
    }

    @Test
    public void testUpdate() throws DBException
    {
        Language language = new Language("LANGUAGE1", new Date());
        languageDAO.create(language);

        language.setLast_update(new Date());
        language.setName("LANG_NAME");
        languageDAO.update(language);

        Language languageFromDB = languageDAO.getById(language.getLanguage_id());
        assertNotNull(languageFromDB);
        assertEquals(languageFromDB.getLanguage_id(), language.getLanguage_id());
        assertEquals(language.getName(), languageFromDB.getName());

        compareDates(language.getLast_update(), languageFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        Language language = new Language("LANGUAGE1", new Date());
        languageDAO.create(language);
        languageDAO.delete(language.getLanguage_id());
        Language languageFromDB = languageDAO.getById(language.getLanguage_id());
        assertNull(languageFromDB);
    }

}
