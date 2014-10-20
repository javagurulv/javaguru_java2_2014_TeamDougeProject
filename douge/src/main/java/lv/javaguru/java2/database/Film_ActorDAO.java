package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Actor;
import lv.javaguru.java2.domain.Film;
import lv.javaguru.java2.domain.Film_Actor;

import java.util.List;

/**
 * Created by Radchuk on 10/20/2014.
 */
public interface Film_ActorDAO {



    void create(Film_Actor film_actor) throws DBException;
    void deleteByFilmID(int id) throws DBException;

    List<Film_Actor> getAllByFilmID(int id) throws DBException;
    List<Film_Actor> getAllByActorID(int id) throws DBException;

}
