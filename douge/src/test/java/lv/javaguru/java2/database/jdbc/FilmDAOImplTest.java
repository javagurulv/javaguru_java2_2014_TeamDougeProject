package lv.javaguru.java2.database.jdbc;

/**
 * Created by Sergo on 17.10.2014.
 */
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;


import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.FilmDAOImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.domain.Film;


public class FilmDAOImplTest {

    private  FilmDAO filmDAO = DAOFactory.getInstance().getFilmDAO();


    @Test
    public void testGetAll() throws DBException
    {
        int count = filmDAO.getFilmsAmount();

        List<Film> films = filmDAO.getAll();
        assertEquals(films.size(),count);
    }

    @Test
    public void testGetAllFromRange() throws DBException
    {
        List<Film> films = filmDAO.getAllFromRange(50,10);
        assertEquals(films.size(),10);
    }

    @Test
    public void testGetById() throws DBException {
        Film film = filmDAO.getByID((long) 55);
        assertNotNull(film);
    }

    @Test
    public void testInitInfoMap() throws DBException, NoSuchFieldException, IllegalAccessException {
       Film film = filmDAO.getByID((long) 55);
       System.out.println(film.getInfoMap().toString());
       assertEquals(film.getInfoMap().keySet().size(),13);

    }



}
