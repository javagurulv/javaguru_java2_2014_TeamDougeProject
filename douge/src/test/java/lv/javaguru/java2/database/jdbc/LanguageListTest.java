package lv.javaguru.java2.database.jdbc;

/**
 * Created by Radchuk on 11/3/2014.
 */
import static org.junit.Assert.*;

import lv.javaguru.java2.Controller.infoClasses.LanguagesList;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.domain.Language;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class LanguageListTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private LanguageDAO languageDAO = new LanguageDAOImpl();

    @Before
    public void cleanDB() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test

    public void testFormLangInfo() throws DBException {

        Language language = new Language("LANGUAGE1", new Date());
        languageDAO.create(language);

        Language language1 = new Language("LANGUAGE2", new Date());
        languageDAO.create(language1);

        Language language2 = new Language("LANGUAGE3", new Date());
        languageDAO.create(language2);

        List<Language> languages = languageDAO.getAll();

        LanguagesList languagesList = new LanguagesList(languages);

        assertEquals(languagesList.getLanguageNameById(language.getLanguage_id()),"LANGUAGE1");
        assertEquals(languagesList.getLanguageNameById(language1.getLanguage_id()),"LANGUAGE2");
        assertEquals(languagesList.getLanguageNameById(language2.getLanguage_id()),"LANGUAGE3");

    }
}
