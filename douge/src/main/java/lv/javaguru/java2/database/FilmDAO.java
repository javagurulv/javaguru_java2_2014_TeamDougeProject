package lv.javaguru.java2.database;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Film;

import java.util.List;

/**
 * Created by Sergo on 17.10.2014.
 */
public interface FilmDAO {

    Film getByID(Long id) throws DBException;

    List<Film> getAll() throws DBException;

    List<Film> getAllFromRange(int from, int amount) throws DBException;
    Integer getFilmsAmount() throws DBException;
}
