package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Actor;

import java.util.List;

/**
 * Created by Juris on 17.10.2014.
 */
public interface ActorDAO {

    Actor getByID(Short actor_id) throws DBException;

    void create(Actor actor) throws DBException;

    void delete(Short actor_id) throws DBException;

    void update(Actor actor) throws  DBException;

    List<Actor> getAll() throws  DBException;
}
