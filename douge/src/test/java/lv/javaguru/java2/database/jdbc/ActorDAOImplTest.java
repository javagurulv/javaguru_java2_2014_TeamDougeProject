package lv.javaguru.java2.database.jdbc;

import static org.junit.Assert.*;

import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;
import lv.javaguru.java2.domain.Widget;
import org.junit.Before;
import org.junit.Test;
import java.util.List;



/**
 * Created by Juris on 17.10.2014.
 */
public class ActorDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private ActorDAO actorDAO = new ActorDAOImpl();

    private Actor createActor(String first_name, String last_name, java.sql.Date last_update) {
        Actor actor = new Actor();
        actor.setFirst_name(first_name);
        actor.setLast_name(last_name);
        actor.setLast_update(last_update);

        return actor;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        /*избаляемся от времени в дате*/
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String inputDate=sqlDate.toString();
        String outputDate=inputDate.substring(0,10);

        Actor actor = createActor("Name", "Surname",sqlDate.valueOf(outputDate));

        actorDAO.create(actor);
        Actor actorFromDB = actorDAO.getByID(actor.getActor_id());
        assertNotNull(actorFromDB);
        assertEquals(actor.getFirst_name(), actorFromDB.getFirst_name());
        assertEquals(actor.getLast_name(), actorFromDB.getLast_name());
        assertEquals(actor.getActor_id(), actorFromDB.getActor_id());
        assertEquals(actor.getLast_update(),actorFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Actor actor = createActor("Name", "Surname", sqlDate);
        actorDAO.create(actor);
        Actor actorFromDB = actorDAO.getByID(actor.getActor_id());

        assertNotNull(actorFromDB);

        actorDAO.delete(actor.getActor_id());

        actorFromDB = actorDAO.getByID(actor.getActor_id());

        assertNull(actorFromDB);
    }

    @Test
    public void testUpdate() throws DBException
    {

        /*избавляемся от времени в дате*/
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String inputDate=sqlDate.toString();
        String outputDate=inputDate.substring(0,10);

        Actor actor = createActor("Name", "Surname",sqlDate.valueOf(outputDate));
        actorDAO.create(actor);

        actor.setFirst_name("Newname");
        actor.setLast_name("Newsurname");
        actor.setLast_update(sqlDate.valueOf(outputDate));

        actorDAO.update(actor);

        Actor actorFromDB = actorDAO.getByID(actor.getActor_id());

        assertNotNull(actorFromDB);
        assertEquals(actor.getFirst_name(), actorFromDB.getFirst_name());
        assertEquals(actor.getLast_name(), actorFromDB.getLast_name());
        assertEquals(actor.getActor_id(), actorFromDB.getActor_id());
        assertEquals(actor.getLast_update(),actorFromDB.getLast_update());

    }

    @Test
    public void testGetAll() throws DBException
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Actor actor1 = createActor("Name1", "Surname1", sqlDate);
        Actor actor2 = createActor("Name2", "Surname2", sqlDate);
        actorDAO.create(actor1);
        actorDAO.create(actor2);

        List<Actor> actors = actorDAO.getAll();

        assertEquals(actors.size(),2);
    }

}

