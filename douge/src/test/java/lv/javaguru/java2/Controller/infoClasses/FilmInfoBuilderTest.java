package lv.javaguru.java2.Controller.infoClasses;

/**
 * Created by Radchuk on 11/3/2014.
 */
import static org.junit.Assert.*;

import lv.javaguru.java2.Controller.Builders.FilmInfoBuilder;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Film;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
@Ignore
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

       int count = filmDAO.getAll().size();


       FilmInfoBuilder filmInfoBuilder = new FilmInfoBuilder();
       filmInfoBuilder.buildTableData();

       ArrayList<Map<String, String>> tableData = filmInfoBuilder.getTableData();

       assertEquals(tableData.size(),count);


   }

}
