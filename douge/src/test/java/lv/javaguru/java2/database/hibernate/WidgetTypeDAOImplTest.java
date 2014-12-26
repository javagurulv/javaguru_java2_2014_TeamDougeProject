package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.WidgetTypeDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.WidgetType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@WebAppConfiguration
public class WidgetTypeDAOImplTest extends SpringIntegrationTest{

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    private WidgetType createWidgetType( String name, String comments, Long compat)
    {
        WidgetType widgetType = new WidgetType();
        widgetType.setComments(comments);
        widgetType.setName(name);
        widgetType.setCompatibility_sign(compat );

        return widgetType;
    }

    @Autowired @Qualifier("ORM_WidgetTypeDAO")
    private WidgetTypeDAO widgetTypeDAO;

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void createNewWidgetType() throws DBException{
        /*WidgetType widgetType = new WidgetType();
        widgetType.setComments("comments");
        widgetType.setName("name");
        widgetType.setCompatibility_sign(128L);

        assertNull(widgetType.getId());
        widgetTypeDAO.create(widgetType);
        assertNotNull(widgetType.getId());*/
    }

    @Test
    @Transactional
    public void getWidgetTypeById() throws DBException{
       /* WidgetType widgetType1 = createWidgetType("name1","comments1",128L);
        widgetTypeDAO.create(widgetType1);

        WidgetType widgetType2 = createWidgetType("name2","comments2", 64L);
        widgetTypeDAO.create(widgetType2);

        WidgetType widgetTypeFromDB = widgetTypeDAO.getById(widgetType1.getId());
        assertEquals(widgetType1.getName(),widgetTypeFromDB.getName());
        assertEquals(widgetType1.getComments(),widgetTypeFromDB.getComments());*/
    }

    @Test
    @Transactional
    public void getAllWidgetTypes() throws DBException{
       /* WidgetType widgetType1 = createWidgetType("name1","comments1");
        widgetTypeDAO.create(widgetType1);

        WidgetType widgetType2 = createWidgetType("name2","comments2");
        widgetTypeDAO.create(widgetType2);

        List<WidgetType> widgetTypes = widgetTypeDAO.getAll();
        assertEquals(widgetTypes.size(),2);*/
    }

    @Test
    @Transactional
    public void deleteWidgetType() throws DBException{
       /* WidgetType widgetType = createWidgetType("name", "comments", 128L);
        widgetTypeDAO.create(widgetType);

        widgetTypeDAO.delete(widgetType.getId());

        WidgetType widgetTypeFromDB = widgetTypeDAO.getById(widgetType.getId());

        assertNull(widgetTypeFromDB);*/
    }

    @Test @Transactional
    public void updateWidgetType() throws DBException
    {
       /* WidgetType widgetType = createWidgetType("name", "comments",128L);
        widgetTypeDAO.create(widgetType);

        widgetType.setName("name1");
        widgetType.setComments("comment1");

        widgetTypeDAO.update(widgetType);

        WidgetType widgetTypeFromDB = widgetTypeDAO.getById(widgetType.getId());

        assertNotNull(widgetTypeFromDB);
        assertEquals(widgetType.getId(), widgetTypeFromDB.getId());
        assertEquals(widgetType.getComments(), widgetTypeFromDB.getComments());
        assertEquals(widgetType.getName(), widgetTypeFromDB.getName());*/
    }




}