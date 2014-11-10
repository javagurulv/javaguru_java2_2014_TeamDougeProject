package lv.javaguru.java2.database.jdbc;

/**
 * Created by Sergo on 16.10.2014.
 */

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import lv.javaguru.java2.database.UserTypeDAO;
import lv.javaguru.java2.database.WidgetDAO;
import lv.javaguru.java2.domain.Widget;
import lv.javaguru.java2.domain.Dashboard;
import org.junit.Before;
import org.junit.Test;

import lv.javaguru.java2.database.DBException;
public class WidgetDAOImplTest {
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

   private WidgetDAO widgetDAO = new WidgetDAOImpl();

   private Widget createWidget( Long dashboard_id, String comments)
   {
       Widget widget = new Widget();
       widget.setComments(comments);
       widget.setDashboard_id(dashboard_id);

       return widget;
   }

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    private void matchWidgets(Widget widget, Widget widgetFromDB)
    {
        assertEquals(widget.getComments(), widgetFromDB.getComments());
        assertEquals(widget.getDashboard_id(), widgetFromDB.getDashboard_id());
        assertEquals(widget.getWidget_id(), widgetFromDB.getWidget_id());
        assertEquals(widget.getMetric_set_id(), widgetFromDB.getMetric_set_id());
        assertEquals(widget.getPosition(), widgetFromDB.getPosition());
        assertEquals(widget.getWidget_type_id(), widgetFromDB.getWidget_type_id());
    }

    @Test
    public void testCreate() throws DBException
    {
        Widget widget = createWidget((long) 3,"test1");

        widgetDAO.create(widget);
        Widget widgetFromDB = widgetDAO.getByID(widget.getWidget_id());
        assertNotNull(widgetFromDB);
        matchWidgets(widget, widgetFromDB);
    }

    @Test
    public void testDelete() throws DBException
    {
        Widget widget = createWidget((long) 3,"test1");
        widgetDAO.create(widget);
        Widget widgetFromDB = widgetDAO.getByID(widget.getWidget_id());

        assertNotNull(widgetFromDB);

        widgetDAO.delete(widget.getWidget_id());

        widgetFromDB = widgetDAO.getByID(widget.getWidget_id());

        assertNull(widgetFromDB);
    }

    @Test
    public void testUpdate() throws DBException
    {
        Widget widget = createWidget((long) 5,"Test 1");
        widgetDAO.create(widget);

        widget.setDashboard_id((long) 5);
        widget.setComments("TEST 2");

        widgetDAO.update(widget);

        Widget widgetFromDB = widgetDAO.getByID(widget.getWidget_id());

        assertNotNull(widgetFromDB);
        matchWidgets(widget, widgetFromDB);

    }

    @Test
    public void testGetAll() throws DBException
    {
        Widget widget1 = createWidget((long) 5,"Test 1");
        Widget widget2 = createWidget((long) 10,"Test 10");

        widgetDAO.create(widget1);
        widgetDAO.create(widget2);

        List<Widget>  widgets = widgetDAO.getAll();

        assertEquals(widgets.size(),2);
    }

    @Test
    public void testGetAllForDashboard() throws DBException
    {
        Widget widget1 = createWidget((long) 5,"Test 1");
        Widget widget2 = createWidget((long) 10,"Test 10");
        Widget widget3 = createWidget((long) 10,"Test 10");
        Widget widget4 = createWidget((long) 10,"Test 10");
        widgetDAO.create(widget1);
        widgetDAO.create(widget2);
        widgetDAO.create(widget3);
        widgetDAO.create(widget4);

        Dashboard dashboard = new Dashboard();
        dashboard.setId((long) 10);

        List<Widget> widgets = widgetDAO.getAllForDashboard(dashboard);
        assertEquals(widgets.size(),3);
    }



}
