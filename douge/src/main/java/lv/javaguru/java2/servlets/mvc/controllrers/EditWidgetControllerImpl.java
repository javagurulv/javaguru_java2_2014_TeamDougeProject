package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class EditWidgetControllerImpl{

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    private DashboardDAO dashboardDAO;

    @Autowired
    @Qualifier("ORM_WidgetDAO")
    private WidgetDAO widgetDAO;

    @Autowired
    @Qualifier("ORM_WidgetTypeDAO")
    private WidgetTypeDAO widgetTypeDAO;

    @Autowired
    @Qualifier("ORM_MetricSetDAO")
    private MetricSetDAO metricSetDAO;

    @Autowired
    @Qualifier("ORM_MetricDAO")
    private MetricDAO metricDAO;

    @RequestMapping(value = "editwidget", method = {RequestMethod.GET,RequestMethod.POST})
    @Transactional
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        model.setViewName("editwidget");

        Integer errorCode = 0;

        if (request.getParameter("submit") != null) {
            if (!request.getParameter("widget_name").trim().isEmpty()) {
                Widget widget = getWidgetFromRequest(request);

                //get metric data from POST request and update it
                MetricSet metricSet = createMetricSetFromRequest(request, widget);
                updateMetricSetInDatabase(metricSet);

                //get widget data from POST request and update it
                widget = createWidgetFromRequest(request, widget);
                updateWidgetInDatabase(widget);
            } else {
                errorCode = 1; //Widget name is empty
            }
        }

        // get all info to build current widget: widget, widget type, metric set
        Widget currentWidget = null;
        WidgetType currentWidgetType = null;
        MetricSet currentMetricSet = null;
        if (request.getParameter("widget_id") != null &&
                !request.getParameter("widget_id").trim().isEmpty()) {
            Long currendWidgetId = Long.parseLong(request.getParameter("widget_id"));
            try {
                currentWidget = widgetDAO.getByID(currendWidgetId);
                currentWidgetType = widgetTypeDAO.getById(currentWidget.getWidget_type_id());
                currentMetricSet = metricSetDAO.getById(currentWidget.getMetric_set_id());
            } catch (DBException e) {
                e.printStackTrace();
            }
        }

        // get all widget types
        List<WidgetType> widgetTypes = new ArrayList<WidgetType>();
        try {
            widgetTypes = widgetTypeDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        // get all metric names by types
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

        // put all gathered data to hash map
        Map<String, Object> objectHashMap = new HashMap<String, Object>();
        objectHashMap.put("currentWidget", currentWidget);
        objectHashMap.put("currentWidgetType", currentWidgetType);
        objectHashMap.put("currentMetricSet", currentMetricSet);
        objectHashMap.put("widgetTypes", widgetTypes);
        objectHashMap.put("primaryMetrics", primaryMetrics);
        objectHashMap.put("groupByMetrics", groupByMetrics);
        objectHashMap.put("limitMetrics", limitMetrics);
        objectHashMap.put("errorCode", errorCode);

        model.addObject("model", objectHashMap);
        return model;
    }

    protected Widget getWidgetFromRequest(HttpServletRequest request) {
        Long widgetId = Long.parseLong(request.getParameter("widget_id"));
        Widget widget = null;
        try {
            widget = widgetDAO.getByID(widgetId);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return widget;
    }

    private void updateMetricSetInDatabase(MetricSet metricSet) {
        try {
            metricSetDAO.update(metricSet);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private void updateWidgetInDatabase(Widget widget) {
        try {
            widgetDAO.update(widget);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    protected MetricSet createMetricSetFromRequest(HttpServletRequest request, Widget widget) {
        Long primary_id = Long.valueOf(request.getParameter("primary_metric"));
        Long groupby_id = Long.valueOf(request.getParameter("group_by_metric"));
        Long limit_id = Long.valueOf(request.getParameter("limit_metric"));
        MetricSet metricSet = null;
        try {
            metricSet = metricSetDAO.getById(widget.getMetric_set_id());
            metricSet.setPrimary_id(primary_id);
            metricSet.setGroupby_id(groupby_id);
            metricSet.setLimit_id(limit_id);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return metricSet;
    }

    protected Widget createWidgetFromRequest(HttpServletRequest request, Widget widget) {

        String comments = request.getParameter("widget_name");
        //Long dashboard_id = Long.valueOf(request.getParameter("dashboard_id"));
        Long widget_type_id = Long.valueOf(request.getParameter("graph_type"));

        /*
        Dashboard dashboard = null;
        try {
            dashboard = dashboardDAO.getById(dashboard_id);
        } catch (DBException e) {
            e.printStackTrace();
        }
        */

        widget.setComments(comments);
        //widget.setDashboard(dashboard);
        //widget.setMetric_set_id(metric_set_id);
        widget.setWidget_type_id(widget_type_id);
        //widget.setPosition(1L);
        return widget;
    }































}
