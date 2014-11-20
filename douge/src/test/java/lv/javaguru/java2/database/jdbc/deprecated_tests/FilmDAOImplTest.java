package lv.javaguru.java2.database.jdbc.deprecated_tests;

/**
 * Created by Sergo on 17.10.2014.
 */
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.FilmDAOImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.domain.Film;

@Ignore
public class FilmDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private  FilmDAO filmDAO = new FilmDAOImpl();

    @Before
    public void clearDatabase() throws DBException
    {
        databaseCleaner.cleanDatabase();
    }

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

    private void assertionFilms(Film film, Film filmFromDB){
        assertNotNull(filmFromDB);
        assertEquals(film.getDescription(), filmFromDB.getDescription());
        assertEquals(film.getRelease_year(), filmFromDB.getRelease_year());
        assertEquals(film.getLanguage_id(),filmFromDB.getLanguage_id());
        assertEquals(film.getOriginal_language_id(),filmFromDB.getOriginal_language_id());
        assertEquals(film.getRental_duration(), filmFromDB.getRental_duration());
        assertEquals(film.getRental_rate().toString(), filmFromDB.getRental_rate().toString());
        assertEquals(film.getLength(), filmFromDB.getLength());
        assertEquals(film.getReplacement_cost().toString(), filmFromDB.getReplacement_cost().toString());
        assertEquals(film.getRating(), filmFromDB.getRating());
        assertEquals(film.getSpecial_features(), filmFromDB.getSpecial_features());
        // assertEquals(film.getLast_update().toString().substring(),filmFromDB.getLast_update());
        System.out.println(film.getLast_update());
        System.out.println(filmFromDB.getLast_update());
    }

    @Test
    public void testCreate() throws DBException
    {
        Film film;
        film = createFilm("TITLE","DESCRIPTION",2004,1,2,55,(float)22.56,16,(float)55.6);
        filmDAO.create(film);

        Film filmFromDB = filmDAO.getByID((long) film.getFilm_id());
        assertionFilms(film, filmFromDB);

    }
    @Test

    public void testDelete() throws DBException
    {
        Film film;
        film = createFilm("TITLE","DESCRIPTION",2004,1,2,55,(float)22.56,16,(float)55.6);
        filmDAO.create(film);

        Film filmFromDB = filmDAO.getByID((long) film.getFilm_id());
        assertionFilms(film, filmFromDB);

        filmDAO.delete((long) film.getFilm_id());
        filmFromDB = filmDAO.getByID((long) film.getFilm_id());
        assertNull(filmFromDB);

    }

    @Test
    public void testUpdate() throws DBException
    {
        Film film;
        film = createFilm("TITLE","DESCRIPTION",2004,1,2,55,(float)22.56,16,(float)55.6);
        filmDAO.create(film);
        film.setDescription("ddddddddd");
        film.setTitle("fffff");
        film.setRelease_year(2001);

        filmDAO.update(film);

        Film filmFromDB = filmDAO.getByID((long) film.getFilm_id());

        assertionFilms(film, filmFromDB);
    }

    @Test
    public void testGetAll() throws DBException
    {
        Film film1 = createFilm("TITLE", "DESCRIPTION", 2004, 1, 2, 55, (float) 22.56, 16, (float) 55.6);
        Film film2 = createFilm("TITLE1","DESCRIPTION1",2004,1,2,55,(float)22.56,16,(float)55.6);
        Film film3 = createFilm("TITLE2","DESCRIPTION2",2004,1,2,55,(float)22.56,16,(float)55.6);
        filmDAO.create(film1);
        filmDAO.create(film2);
        filmDAO.create(film3);

        List<Film> films = filmDAO.getAll();
        assertEquals(films.size(),3);
    }

    @Test
    public void testGetAllByLanguageId() throws DBException
    {
        Film film1 = createFilm("TITLE","DESCRIPTION",2004,1,2,55,(float)22.56,16,(float)55.6);
        Film film2 = createFilm("TITLE1","DESCRIPTION1",2004,5,2,55,(float)22.56,16,(float)55.6);
        Film film3 = createFilm("TITLE2","DESCRIPTION2",2004,5,2,55,(float)22.56,16,(float)55.6);
        filmDAO.create(film1);
        filmDAO.create(film2);
        filmDAO.create(film3);
        List<Film> films = filmDAO.getAllByLanguageId((long) 5);
        assertEquals(films.size(),2);

    }

    @Test
    public void testGetAllByOriginalLanguageID() throws DBException
    {
        Film film1 = createFilm("TITLE","DESCRIPTION",2004,1,2,55,(float)22.56,16,(float)55.6);
        Film film2 = createFilm("TITLE1","DESCRIPTION1",2004,5,2,55,(float)22.56,16,(float)55.6);
        Film film3 = createFilm("TITLE2","DESCRIPTION2",2004,5,2,55,(float)22.56,16,(float)55.6);
        filmDAO.create(film1);
        filmDAO.create(film2);
        filmDAO.create(film3);
        Film film4 = createFilm("TITLE","DESCRIPTION",2004,1,3,66,(float)22.56,16,(float)55.6);
        Film film5 = createFilm("TITLE1","DESCRIPTION1",2004,3,3,66,(float)22.56,16,(float)55.6);
        filmDAO.create(film4);
        filmDAO.create(film5);

        List<Film> films = filmDAO.getAllByOriginalLanguageID((long) 2);
        assertEquals(films.size(), 3);

        films = filmDAO.getAllByOriginalLanguageID((long) 3);
        assertEquals(films.size(), 2);
    }
}
