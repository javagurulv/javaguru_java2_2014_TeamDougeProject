package lv.javaguru.java2.database.jdbc.deprecated_tests;

import lv.javaguru.java2.Controller.infoClasses.ActorFullInfo;
import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Actor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Juris on 05.11.2014.
 */
@Ignore
public class ActorFullInfoTest {

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private Actor createActor(String first_name, String last_name){
        Actor actor=new Actor();
        actor.setFirst_name(first_name);
        actor.setLast_name(last_name);
        actor.setLast_update(new Date());
        return actor;
    }

    @Before
    public void cleanDB() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testFullInfoCreation() throws DBException {

        ActorDAO actorDAO = DAOFactory.getInstance().getActorDAO();

        Actor actor = createActor("Patrik", "Harris");
        actorDAO.create(actor);
        ActorFullInfo actorFullInfo = new ActorFullInfo(actor);
        Map<String,String> info = actorFullInfo.getActorInfo();
        assertEquals(info.get("First Name"), actor.getFirst_name());
        assertEquals(info.get("Last Name"), actor.getLast_name());
    }

}
