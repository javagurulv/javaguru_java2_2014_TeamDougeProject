package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.Metric;
import lv.javaguru.java2.domain.MetricSet;
import lv.javaguru.java2.domain.Widget;
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

    @Autowired
    @Qualifier("ORM_MetricSetDAO")
    private MetricSetDAO metricSetDAO;

    @Override
    @Transactional
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("submit") != null) {
            if (!request.getParameter("widget_name").trim().isEmpty()) {
                MetricSet metricSet = createMetricSetFromRequest(request);
                storeMetricSetToDatabase(metricSet);

                Widget widget = createWidgetFromRequest(request, metricSet.getId());
                storeWidgetToDatabase(widget);
            }
        }

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

        Map<String, List<Metric>> metricMap = new HashMap<String, List<Metric>>();
        metricMap.put("Primary", primaryMetrics);
        metricMap.put("GroupBy", groupByMetrics);
        metricMap.put("Limit", limitMetrics);

        return new MVCModel("/jsp/addwidget.jsp", metricMap);
    }

    private void storeMetricSetToDatabase(MetricSet metricSet) {
        try {
            metricSetDAO.create(metricSet);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private void storeWidgetToDatabase(Widget widget) {
        try {
            widgetDAO.create(widget);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    protected MetricSet createMetricSetFromRequest(HttpServletRequest request) {
        MetricSet metricSet = new MetricSet();
        Integer primary_id = Integer.valueOf(request.getParameter("primary_metric"));
        Integer groupby_id = Integer.valueOf(request.getParameter("group_by_metric"));
        Integer limit_id = Integer.valueOf(request.getParameter("limit_metric"));

        metricSet.setPrimary_id(primary_id);
        metricSet.setGroupby_id(groupby_id);
        metricSet.setLimit_id(limit_id);
        return metricSet;
    }

    protected Widget createWidgetFromRequest(HttpServletRequest request, Integer metric_set_id) {

        Widget widget = new Widget();
        String comments = request.getParameter("widget_name");
        Long dashboard_id = Long.valueOf(request.getParameter("dashboard_id"));
        Long widget_type_id = Long.valueOf(request.getParameter("graph_type"));

        Dashboard dashboard = null;
        try {
            dashboard = dashboardDAO.getById(dashboard_id);
        } catch (DBException e) {
            e.printStackTrace();
        }

        widget.setComments(comments);
        widget.setDashboard(dashboard);
        widget.setMetric_set_id(metric_set_id);
        widget.setWidget_type_id(widget_type_id);
        widget.setPosition(1L);
        return widget;
    }
}











