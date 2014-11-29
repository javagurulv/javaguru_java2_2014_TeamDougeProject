package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;
import lv.javaguru.java2.domain.DomainWidgetContent;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Juris on 21.11.2014.
 */
public class ActorDAOImplTest {

    private ActorDAO actorDAO = DAOFactory.getInstance().getActorDAO();

    @Test
    public void testGetAll() throws DBException
    {
        int count = actorDAO.getActorsAmount();

        List<DomainWidgetContent> actors = actorDAO.getAll();
        assertEquals(actors.size(),count);
    }

    @Test
    public void testGetAllFromRange() throws DBException
    {
        List<DomainWidgetContent> actors = actorDAO.getAllFromRange(50,10);
        assertEquals(actors.size(),10);
    }

    @Test
    public void testGetById() throws DBException {
        Actor actor = actorDAO.getByID((short) 55);
        assertNotNull(actor);
    }

    @Test
    public void testInitInfoMap() throws DBException, NoSuchFieldException, IllegalAccessException {
        Actor actor = actorDAO.getByID((short) 55);
        System.out.println(actor.getInfoMap().toString());
        assertEquals(actor.getInfoMap().keySet().size(),4);

    }

}
