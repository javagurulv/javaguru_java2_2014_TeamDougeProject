package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Film;

import java.util.List;

/**
 * Created by Sergo on 17.10.2014.
 */
public interface FilmDAO {
    void create(Film film) throws DBException;

    void delete(Long id) throws DBException;

    Film getByID(Long id) throws DBException;

    void update(Film film)throws DBException;

    List<Film> getAll() throws DBException;

    List<Film> getAllByLanguageId(Long languageID) throws DBException;

    List<Film> getAllByOriginalLanguageID(Long originalLanguageId) throws DBException;
}
