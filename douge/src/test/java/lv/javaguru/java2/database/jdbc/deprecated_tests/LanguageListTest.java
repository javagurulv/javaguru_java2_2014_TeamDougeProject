package lv.javaguru.java2.database.jdbc.deprecated_tests;

/**
 * Created by Radchuk on 11/3/2014.
 */

import lv.javaguru.java2.Controller.infoClasses.LanguagesList;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.LanguageDAOImpl;
import lv.javaguru.java2.domain.Language;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
@Ignore
public class LanguageListTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();
    private LanguageDAO languageDAO = new LanguageDAOImpl();

    @Before
    public void cleanDB() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test

    public void testFormLangInfo() throws DBException {



        List<Language> languages = languageDAO.getAll();

        LanguagesList languagesList = new LanguagesList(languages);

       /* assertEquals(languagesList.getLanguageNameById(language.getLanguage_id()),"LANGUAGE1");
        assertEquals(languagesList.getLanguageNameById(language1.getLanguage_id()),"LANGUAGE2");
        assertEquals(languagesList.getLanguageNameById(language2.getLanguage_id()),"LANGUAGE3");*/

    }
}
