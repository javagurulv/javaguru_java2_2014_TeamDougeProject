package lv.javaguru.java2.database.jdbc.deprecated_tests;

/**
 * Created by Radchuk Sergey on 20.10.2014.
 */
import static org.junit.Assert.*;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.Film_ActorDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.database.jdbc.Film_ActorDAOImpl;
import lv.javaguru.java2.domain.Film_Actor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
@Ignore
public class Film_ActorDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private Film_ActorDAO film_actorDAO = new Film_ActorDAOImpl();
    private Film_Actor createFilm_Actor(int actorId, int filmId)
    {
        Film_Actor film_actor = new Film_Actor();
        film_actor.setLast_update(new Date());
        film_actor.setFilm_id(filmId);
        film_actor.setActor_id(actorId);
        return film_actor;
    }

    @Before
    public void cleanDB() throws DBException
    {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
       Film_Actor film_actor = createFilm_Actor(10,50);
        film_actorDAO.create(film_actor);
        Film_Actor film_actorFromDB = film_actorDAO.getByRecordId(film_actor.getRecord_id());
        //Film_Actor film_actorFromDB = film_actorDAO.getAllByActorID(film_actor.getActor_id()).get(0);
        assertNotNull(film_actorFromDB);
        assertEquals(film_actor.getActor_id(), film_actorFromDB.getActor_id());
        assertEquals(film_actor.getFilm_id(), film_actorFromDB.getFilm_id());
    }

    @Test
    public void testDelete()throws DBException
    {
        Film_Actor film_actor = createFilm_Actor(10,50);
        film_actorDAO.create(film_actor);

        film_actorDAO.deleteByFilmID(50);

        int fCount = film_actorDAO.getAllByActorID(10).size();

        assertEquals(fCount, 0);
    }

    @Test
    public void testGetAll() throws DBException
    {
        Film_Actor film_actor = createFilm_Actor(10,50);
        film_actorDAO.create(film_actor);
        Film_Actor film_actor1 = createFilm_Actor(11,50);
        film_actorDAO.create(film_actor1);
        Film_Actor film_actor2 = createFilm_Actor(10,60);
        film_actorDAO.create(film_actor2);
        Film_Actor film_actor3 = createFilm_Actor(11,60);
        film_actorDAO.create(film_actor3);

        int fCount = film_actorDAO.getAllByActorID(11).size();
        assertEquals(fCount,2);

        fCount  = film_actorDAO.getAllByActorID(10).size();
        assertEquals(fCount,2);

        fCount = film_actorDAO.getAllByFilmID(60).size();

        assertEquals(fCount,2);
    }
}
