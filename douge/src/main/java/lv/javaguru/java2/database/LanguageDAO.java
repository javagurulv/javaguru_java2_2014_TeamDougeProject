package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Language;

import java.util.List;

/**
 * Created by Sergo on 21.10.2014.
 */
public interface LanguageDAO {
    Language getById(int id) throws DBException;

    void create(Language language) throws DBException;
    void delete(int id) throws DBException;
    void update(Language language) throws DBException;
    List<Language> getAll() throws DBException;

}
