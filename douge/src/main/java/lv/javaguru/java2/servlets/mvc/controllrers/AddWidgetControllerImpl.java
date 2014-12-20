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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 11-Dec-14.
 */
@Controller
public class AddWidgetControllerImpl{

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

    @RequestMapping(value = "addwidget", method = {RequestMethod.GET,RequestMethod.POST})
    @Transactional
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        model.setViewName("addwidget");

        Integer errorCode = 0;

        if (request.getParameter("submit") != null) {
            if (!request.getParameter("widget_name").trim().isEmpty()) {
                MetricSet metricSet = createMetricSetFromRequest(request);
                storeMetricSetToDatabase(metricSet);

                Widget widget = createWidgetFromRequest(request, metricSet.getId());
                storeWidgetToDatabase(widget);
            } else {
                errorCode = 1; //Widget name is empty
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

        model.addObject("model",metricMap);

        return model;
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
        Long primary_id = Long.valueOf(request.getParameter("primary_metric"));
        Long groupby_id = Long.valueOf(request.getParameter("group_by_metric"));
        Long limit_id = Long.valueOf(request.getParameter("limit_metric"));

        metricSet.setPrimary_id(primary_id);
        metricSet.setGroupby_id(groupby_id);
        metricSet.setLimit_id(limit_id);
        return metricSet;
    }

    protected Widget createWidgetFromRequest(HttpServletRequest request, Long metric_set_id) {

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











