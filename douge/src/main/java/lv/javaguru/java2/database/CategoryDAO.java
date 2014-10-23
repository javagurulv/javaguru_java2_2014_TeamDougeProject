package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Category;

import java.util.List;

/**
 * Created by Juris on 23.10.2014.
 */
public interface CategoryDAO {

    void create(Category category) throws DBException;

    Category getById(int category_id) throws  DBException;

    void update (Category category) throws DBException;

    void delete (int category_id) throws DBException;

    List<Category> getAll() throws DBException;
}
