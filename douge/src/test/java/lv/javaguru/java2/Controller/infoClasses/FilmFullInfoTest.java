package lv.javaguru.java2.Controller.infoClasses;

import junit.framework.TestCase;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Film;
import lv.javaguru.java2.domain.Language;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
@Ignore
public class FilmFullInfoTest extends TestCase {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();



    @Before
    public void cleanDB() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testFullInfoCreation() throws DBException {
/*
        FilmDAO filmDAO = DAOFactory.getInstance().getFilmDAO();
        LanguageDAO languageDAO = DAOFactory.getInstance().getLanguageDAO();
        //filmDAO.create(film);

        Language language = new Language("Russian", new Date());
        languageDAO.create(language);

        Language language1 = new Language("English", new Date());
        languageDAO.create(language1);

        Film film = createFilm("TITLE","DESCRIPTION",2004,language.getLanguage_id(),language1.getLanguage_id(),55,(float)22.56,16,(float)55.6);
        filmDAO.create(film);
        LanguagesList languagesList = new LanguagesList(languageDAO.getAll());
        FilmFullInfo filmFullInfo = new FilmFullInfo(film, languagesList);
        Map<String,String> info = filmFullInfo.getFilmInfo();
        Integer tmp;
        assertEquals(info.get("Title"), film.getTitle());
        assertEquals(info.get("Description"), film.getDescription());
        tmp = film.getRelease_year();
        assertEquals(info.get("Release Year"), tmp.toString());
        assertEquals(info.get("Language"),"Russian" );
        assertEquals(info.get("Original Language"),"English");*/
    }
}