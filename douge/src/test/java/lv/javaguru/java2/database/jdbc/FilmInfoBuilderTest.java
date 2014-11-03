package lv.javaguru.java2.database.jdbc;

/**
 * Created by Radchuk on 11/3/2014.
 */
import static org.junit.Assert.*;

import lv.javaguru.java2.Controller.Builders.FilmInfoBuilder;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.database.LanguageDAO;
import lv.javaguru.java2.domain.Film;
import lv.javaguru.java2.domain.Language;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class FilmInfoBuilderTest {

    private Film createFilm(String title, String description, int release_year,int language_id ,int original_language_id,
                            int rental_duration,float rental_rate, int length,float replacement_cost)
    {

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

   @Test
    public void testBuildFilmTable() throws DBException {
       new DatabaseCleaner().cleanDatabase();

       FilmDAO filmDAO = DAOFactory.getInstance().getFilmDAO();
       LanguageDAO languageDAO = DAOFactory.getInstance().getLanguageDAO();
       //filmDAO.create(film);

       Language language = new Language("Russian", new Date());
       languageDAO.create(language);

       Language language1 = new Language("English", new Date());
       languageDAO.create(language1);

       Film film = createFilm("TITLE","DESCRIPTION",2004,language.getLanguage_id(),language1.getLanguage_id(),55,(float)22.56,16,(float)55.6);
       filmDAO.create(film);

       Film film1 = createFilm("TITLE1","DESCRIPTION1",2005,language.getLanguage_id(),language1.getLanguage_id(),55,(float)22.56,16,(float)55.6);
       filmDAO.create(film1);


       FilmInfoBuilder filmInfoBuilder = new FilmInfoBuilder();
       filmInfoBuilder.buildTableData();

       ArrayList<Map<String, String>> tableData = filmInfoBuilder.getTableData();

       assertEquals(tableData.size(),2);


   }

}
