package lv.javaguru.java2.Controller.infoClasses;

import lv.javaguru.java2.Controller.Builders.ActorInfoBuilder;
import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Actor;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Juris on 05.11.2014.
 */
@Ignore
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

        int count = actorDAO.getAll().size();

        ActorInfoBuilder actorInfoBuilder = new ActorInfoBuilder();
        actorInfoBuilder.buildTableData();

        ArrayList<Map<String, String>> tableData = actorInfoBuilder.getTableData();

        assertEquals(tableData.size(),count);


    }

}
