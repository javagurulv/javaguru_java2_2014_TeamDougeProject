package lv.javaguru.java2.database.jdbc;

/**
 * Created by Radchuk Sergey on 11/3/2014.
 */

import static org.junit.Assert.*;

import lv.javaguru.java2.Controller.infoClasses.FilmFullInfo;
import lv.javaguru.java2.Controller.infoClasses.LanguagesList;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.domain.Film;
import lv.javaguru.java2.domain.Language;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

public class FilmFullInfoTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private Film createFilm(String title, String description, int release_year,int language_id ,int original_language_id,
                            int rental_duration,float rental_rate, int length,float replacement_cost)
    {
        /*private String title;
    private String description;
    private int release_year;
    private int language_id;
    private int original_language_id;
    private int rental_duration;
    private float rental_rate;
    private int length;
    private float replacement_cost;
    private String rating;*/;
        // String special_features;
        //    private Date last_update;
        Film film = new Film();
        film.setTitle(title);
        film.setDescription(description);
        film.setRelease_year(release_year);
        film.setLanguage_id(language_id);
        film.setOriginal_language_id(original_language_id);
        film.setRental_duration(rental_duration);
        film.setRental_rate(rental_rate);
        film.setLength(length);
        film.setReplacement_cost(replacement_cost);
        film.setRating("PG");
        film.setSpecial_features("Trailers");
        film.setLast_update(new Date());
        return film;


    }

    @Before
    public void cleanDB() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testFullInfoCreation() throws DBException {

        FilmDAO  filmDAO = DAOFactory.getInstance().getFilmDAO();
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
        assertEquals(info.get("Original Language"),"English");
    }

}
