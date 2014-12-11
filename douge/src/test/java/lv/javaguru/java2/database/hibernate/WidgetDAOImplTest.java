package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.database.WidgetDAO;
import lv.javaguru.java2.domain.Dashboard;
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

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    private DashboardDAO dashboardDAO;

    @Test
    @Transactional
    public void createNewWidgetInstance() throws DBException {

        Dashboard dashboard = dashboardDAO.getById(1L);

        Widget widget = new Widget();
        widget.setComments("Widget 1");
        widget.setDashboard(dashboard);
        widget.setMetric_set_id(8L);
        widget.setWidget_type_id(1);
        widget.setPosition(3L);

        assertNull(widget.getWidget_id());
        widgetDAO.create(widget);
        assertNotNull(widget.getWidget_id());
    }

}