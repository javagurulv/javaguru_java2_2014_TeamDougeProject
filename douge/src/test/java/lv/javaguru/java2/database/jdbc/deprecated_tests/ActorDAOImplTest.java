package lv.javaguru.java2.database.jdbc.deprecated_tests;

import static org.junit.Assert.*;

import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.ActorDAOImpl;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Actor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 * Created by Juris on 17.10.2014.
 */
@Ignore
public class ActorDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private ActorDAO actorDAO = new ActorDAOImpl();

    private Actor createActor(String first_name, String last_name) {
        Actor actor = new Actor();
        actor.setFirst_name(first_name);
        actor.setLast_name(last_name);
        actor.setLast_update(new Date());

        return actor;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {

        Actor actor = createActor("Name", "Surname");

        actorDAO.create(actor);
        Actor actorFromDB = actorDAO.getByID(actor.getActor_id());
        assertNotNull(actorFromDB);
        assertEquals(actor.getFirst_name(), actorFromDB.getFirst_name());
        assertEquals(actor.getLast_name(), actorFromDB.getLast_name());
        assertEquals(actor.getActor_id(), actorFromDB.getActor_id());
        assertionEqualsDateCustom(actor.getLast_update(), actorFromDB.getLast_update());
    }

    @Test
    public void testDelete() throws DBException
    {
        Actor actor = createActor("Name", "Surname");
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

        Actor actor = createActor("Name", "Surname");
        actorDAO.create(actor);

        actor.setFirst_name("Newname");
        actor.setLast_name("Newsurname");
        actor.setLast_update(new Date());

        actorDAO.update(actor);

        Actor actorFromDB = actorDAO.getByID(actor.getActor_id());

        assertNotNull(actorFromDB);
        assertEquals(actor.getFirst_name(), actorFromDB.getFirst_name());
        assertEquals(actor.getLast_name(), actorFromDB.getLast_name());
        assertEquals(actor.getActor_id(), actorFromDB.getActor_id());
        assertionEqualsDateCustom(actor.getLast_update(), actor.getLast_update());


    }

    @Test
    public void testGetAll() throws DBException
    {

        int count = actorDAO.getAll().size();
        Actor actor1 = createActor("Name1", "Surname1");
        Actor actor2 = createActor("Name2", "Surname2");
        actorDAO.create(actor1);
        actorDAO.create(actor2);

        List<Actor> actors = actorDAO.getAll();

        assertEquals(actors.size(),count + 2);
    }
    private void assertionEqualsDateCustom(Date date1, Date date2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = simpleDateFormat.format(date1);
        String strDate2 = simpleDateFormat.format(date2);
        assertEquals(strDate1, strDate2);
    }
}

