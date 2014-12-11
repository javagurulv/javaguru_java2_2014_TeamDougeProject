package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.Metric;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 11-Dec-14.
 */
@Component
public class AddWidgetControllerImpl implements AddWidgetController {

    private Map<String, List<Metric>> metricMap;

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
        /*
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
        */
        List<Metric> primaryMetrics = new ArrayList<Metric>();
        List<Metric> groupByMetrics = new ArrayList<Metric>();
        List<Metric> limitMetrics = new ArrayList<Metric>();

        try {
            primaryMetrics = metricDAO.getAllByType("Primary");
            groupByMetrics = metricDAO.getAllByType("GroupBy");
            limitMetrics = metricDAO.getAllByType("Limit");
        } catch (DBException e) {
            e.printStackTrace();
        }

        metricMap = new HashMap<String, List<Metric>>();
        metricMap.put("Primary", primaryMetrics);
        metricMap.put("GroupBy", groupByMetrics);
        metricMap.put("Limit", limitMetrics);

        return new MVCModel("/jsp/addwidget.jsp", metricMap);
    }

}
