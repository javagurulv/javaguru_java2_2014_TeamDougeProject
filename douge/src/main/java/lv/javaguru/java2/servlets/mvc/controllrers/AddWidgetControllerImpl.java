package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.Metric;
import lv.javaguru.java2.domain.Widget;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11-Dec-14.
 */
@Component
public class AddWidgetControllerImpl implements AddWidgetController {

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    private DashboardDAO dashboardDAO;

    @Autowired
    @Qualifier("ORM_WidgetDAO")
    private WidgetDAO widgetDAO;

    @Autowired
    @Qualifier("ORM_MetricDAO")
    private MetricDAO metricDAO;

    @Override
    @Transactional
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        Dashboard currentDashboard = null;

        if (request.getParameter("dashboard_id") != null &&
                !request.getParameter("dashboard_id").trim().isEmpty()) {
            Long currentDashboardId = Long.parseLong(request.getParameter("dashboard_id"));
            try {
                currentDashboard = dashboardDAO.getById(currentDashboardId);
            } catch (DBException e) {
                e.printStackTrace();
            }

            for (Widget widget: currentDashboard.getWidgets()) {
                widget.getWidget_id();
            }
        }

        List<Metric> metrics = new ArrayList<Metric>();

        try {
            metrics = metricDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        return new MVCModel("/jsp/addwidget.jsp", metrics);
    }

}
