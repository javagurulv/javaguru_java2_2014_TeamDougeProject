package lv.javaguru.java2.database.deprecated_dao;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.deprecated_classes.Film_Actor;

import java.util.List;

/**
 * Created by Radchuk on 10/20/2014.
 */
@Deprecated
public interface Film_ActorDAO {



    void create(Film_Actor film_actor) throws DBException;
    void deleteByFilmID(int id) throws DBException;
    Film_Actor getByRecordId(int id) throws DBException;
    List<Film_Actor> getAllByFilmID(int id) throws DBException;
    List<Film_Actor> getAllByActorID(int id) throws DBException;

}
