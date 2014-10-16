package lv.javaguru.java2.database.jdbc;

/**
 * Created by Sergo on 16.10.2014.
 */
import static org.junit.Assert.*;

import java.util.List;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.WidgetType;
import lv.javaguru.java2.database.WidgetTypeDAO;
import lv.javaguru.java2.domain.Widget;
import lv.javaguru.java2.domain.Dashboard;
import org.junit.Before;
import org.junit.Test;


public class WidgetTypeDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private WidgetTypeDAO widgetTypeDAO = new WidgetTypeDAOImpl();

    private WidgetType createWidgetType( String name, String comments)
    {
        WidgetType widgetType = new WidgetType();
        widgetType.setComments(comments);
        widgetType.setName(name);

        return widgetType;
    }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException
    {
        WidgetType widgetType = createWidgetType("name", "comments");
        widgetTypeDAO.create(widgetType);

        WidgetType widgetTypeFromDB = widgetTypeDAO.getById(widgetType.getId());

        assertNotNull(widgetTypeFromDB);
        assertEquals(widgetType.getId(), widgetTypeFromDB.getId());
        assertEquals(widgetType.getComments(), widgetTypeFromDB.getComments());
        assertEquals(widgetType.getName(), widgetTypeFromDB.getName());
    }

    @Test
    public void testUpdate() throws DBException
    {
        WidgetType widgetType = createWidgetType("name", "comments");
        widgetTypeDAO.create(widgetType);

        widgetType.setName("name 1");
        widgetType.setComments("comment 1");

        widgetTypeDAO.update(widgetType);

        WidgetType widgetTypeFromDB = widgetTypeDAO.getById(widgetType.getId());

        assertNotNull(widgetTypeFromDB);
        assertEquals(widgetType.getId(), widgetTypeFromDB.getId());
        assertEquals(widgetType.getComments(), widgetTypeFromDB.getComments());
        assertEquals(widgetType.getName(), widgetTypeFromDB.getName());
    }

    @Test
    public void testDelete() throws DBException
    {

        WidgetType widgetType = createWidgetType("name", "comments");
        widgetTypeDAO.create(widgetType);

        widgetTypeDAO.delete(widgetType.getId());

        WidgetType widgetTypeFromDB = widgetTypeDAO.getById(widgetType.getId());

        assertNull(widgetTypeFromDB);

    }

    @Test
    public  void testGetAll() throws DBException
    {
        WidgetType widgetType1 = createWidgetType("name", "comments");
        widgetTypeDAO.create(widgetType1);

        WidgetType widgetType2 = createWidgetType("name1", "comments1");
        widgetTypeDAO.create(widgetType2);
        List<WidgetType> widgetTypes = widgetTypeDAO.getAll();

        assertEquals(widgetTypes.size(),2);
    }


}
