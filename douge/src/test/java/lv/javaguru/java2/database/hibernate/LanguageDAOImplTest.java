package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.domain.Language;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by user on 30-Nov-14.
 */
public class LanguageDAOImplTest extends SpringIntegrationTest {

    @Autowired
    @Qualifier("ORM_LanguageDAO")
    private LanguageDAO languageDAO;

    @Test
    @Transactional
    public void createNewLanguage() throws DBException {
        Language language = new Language();

        language.setName("Klingon");
        language.setLast_update(new Date());

        languageDAO.create(language);
        Language languageFromDB = languageDAO.getById(language.getLanguage_id());
        assertEquals("Klingon", languageFromDB.getName());
    }
}
