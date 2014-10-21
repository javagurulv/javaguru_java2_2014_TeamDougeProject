package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Film_Text;

/**
 * Created by Sergo on 21.10.2014.
 */
public interface Film_TextDAO {

    Film_Text getById(int id) throws DBException;

    void create(Film_Text film_text) throws DBException;
    void update(Film_Text film_text) throws DBException;
    void delete(int id) throws DBException;
}
