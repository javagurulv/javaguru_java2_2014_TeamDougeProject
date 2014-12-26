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

    private WidgetType createWidgetType( String name, String comments)
    {
        WidgetType widgetType = new WidgetType();
        widgetType.setComments(comments);
        widgetType.setName(name);
       // widgetType.setCompatibility_sign(compat );

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
        WidgetType widgetType = new WidgetType("name","comment",2L);
        widgetType.setComments("comments");
        widgetType.setName("name");


        assertNull(widgetType.getId());
        widgetTypeDAO.create(widgetType);
        assertNotNull(widgetType.getId());
    }

    @Test
    @Transactional
    public void getWidgetTypeById() throws DBException{
        WidgetType widgetType1 = new WidgetType("name1","comments1",128L);
        widgetTypeDAO.create(widgetType1);

        WidgetType widgetType2 = new WidgetType("name2","comments2", 64L);
        widgetTypeDAO.create(widgetType2);

        WidgetType widgetTypeFromDB = widgetTypeDAO.getById(widgetType1.getId());
        assertEquals(widgetType1.getName(),widgetTypeFromDB.getName());
        assertEquals(widgetType1.getComments(),widgetTypeFromDB.getComments());
    }

    @Test
    @Transactional
    public void getAllWidgetTypes() throws DBException{
        WidgetType widgetType1 = new WidgetType("name1","comments1",128L);
        widgetTypeDAO.create(widgetType1);

        WidgetType widgetType2 = new WidgetType("name2","comments2", 64L);
        widgetTypeDAO.create(widgetType2);

        List<WidgetType> widgetTypes = widgetTypeDAO.getAll();
        assertEquals(widgetTypes.size(),2);
    }

    @Test
    @Transactional
    public void deleteWidgetType() throws DBException{
        WidgetType widgetType = new WidgetType("name1","comments1",128L);
        widgetTypeDAO.create(widgetType);


        widgetTypeDAO.delete(widgetType.getId());

        WidgetType widgetTypeFromDB = widgetTypeDAO.getById(widgetType.getId());

        assertNull(widgetTypeFromDB);
    }






}