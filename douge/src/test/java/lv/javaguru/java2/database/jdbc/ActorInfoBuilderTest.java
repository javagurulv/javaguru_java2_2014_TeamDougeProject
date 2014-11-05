package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.Controller.Builders.ActorInfoBuilder;
import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Juris on 05.11.2014.
 */
public class ActorInfoBuilderTest {

    private Actor createActor(String first_name, String last_name){
        Actor actor=new Actor();
        actor.setFirst_name(first_name);
        actor.setLast_name(last_name);
        actor.setLast_update(new Date());
        return actor;
    }

    @Test
    public void testBuildFilmTable() throws DBException {
        new DatabaseCleaner().cleanDatabase();

        ActorDAO actorDAO = DAOFactory.getInstance().getActorDAO();

        Actor actor1 = createActor("Jim", "Cerry");
        actorDAO.create(actor1);

        Actor actor2 = createActor("Andrew", "Garfield");
        actorDAO.create(actor2);


        ActorInfoBuilder actorInfoBuilder = new ActorInfoBuilder();
        actorInfoBuilder.buildTableData();

        ArrayList<Map<String, String>> tableData = actorInfoBuilder.getTableData();

        assertEquals(tableData.size(),2);


    }

}
