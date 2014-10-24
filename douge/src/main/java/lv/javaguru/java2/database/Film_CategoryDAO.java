package lv.javaguru.java2.database;


import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Film_Category;

import java.util.List;

/**
 * Created by Juris on 24.10.2014.
 */
public interface Film_CategoryDAO {

    Film_Category getByID(Short film_id) throws DBException;

    void create(Film_Category film_category) throws DBException;

    void delete(Short film_id) throws DBException;

    void update(Film_Category film_category) throws  DBException;

    List<Film_Category> getAll() throws  DBException;

    List<Film_Category> getAllForCategory(Category category) throws DBException;

}
