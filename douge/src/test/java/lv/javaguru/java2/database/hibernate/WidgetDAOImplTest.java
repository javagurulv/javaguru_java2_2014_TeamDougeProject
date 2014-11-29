package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.WidgetDAO;
import lv.javaguru.java2.domain.Widget;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;

import static org.junit.Assert.*;



public class WidgetDAOImplTest extends SpringIntegrationTest {

    @Autowired
    @Qualifier("ORM_WidgetDAO")
    private WidgetDAO widgetDAO;


    @Test
    @Transactional
    public void createNewWidgetInstance() throws DBException {
        Widget widget = new Widget();
        widget.setComments("sdfsdfsd");
        widget.setDashboard_id(50L);
        widget.setMetric_set_id(8L);
        widget.setWidget_type_id(1);
        widget.setPosition(3L);

        assertNull(widget.getWidget_id());
        widgetDAO.create(widget);
        assertNotNull(widget.getWidget_id());
    }

}