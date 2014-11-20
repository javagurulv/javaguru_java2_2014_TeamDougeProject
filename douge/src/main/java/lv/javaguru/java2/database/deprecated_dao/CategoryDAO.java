package lv.javaguru.java2.database.deprecated_dao;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.deprecated_classes.Category;

import java.util.List;

/**
 * Created by Juris on 23.10.2014.
 */
@Deprecated
public interface CategoryDAO {

    void create(Category category) throws DBException;

    Category getById(int category_id) throws  DBException;

    void update (Category category) throws DBException;

    void delete (int category_id) throws DBException;

    List<Category> getAll() throws DBException;
}
