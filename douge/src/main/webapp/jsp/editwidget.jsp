<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="lv.javaguru.java2.domain.Widget" %>
<%@ page import="lv.javaguru.java2.domain.WidgetType" %>
<%@ page import="lv.javaguru.java2.domain.MetricSet" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.Metric" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11-Dec-14
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //allow access only if session exists
    String sessionLogin = null;
    if (session.getAttribute("sessionLogin") != null) {
        sessionLogin = (String) session.getAttribute("sessionLogin");
    }
    else {
        response.sendRedirect("/login");
    }

    Integer dashboard_id = 0;
    Integer widget_id = 0;

    if (request.getParameter("dashboard_id") != null &&
            request.getParameter("widget_id") != null) {
        dashboard_id = Integer.valueOf(request.getParameter("dashboard_id"));
        widget_id = Integer.valueOf(request.getParameter("widget_id"));
    }

    Map<String, Object> objectHashMap = (HashMap) request.getAttribute("model");
    Widget currentWidget = (Widget) objectHashMap.get("currentWidget");
    WidgetType currentWidgetType = (WidgetType) objectHashMap.get("currentWidgetType");
    MetricSet currentMetricSet = (MetricSet) objectHashMap.get("currentMetricSet");
    List<WidgetType> widgetTypes = (List) objectHashMap.get("widgetTypes");
    List<Metric> primaryMetrics = (List) objectHashMap.get("primaryMetrics");
    List<Metric> groupByMetrics = (List) objectHashMap.get("groupByMetrics");
    List<Metric> limitMetrics = (List) objectHashMap.get("limitMetrics");
%>

<html>
<head>
    <title>Edit widget</title>
    <script>
        window.onunload = refreshParent;
        function refreshParent() {
            window.opener.location.reload();
        }
    </script>
</head>
<body>
    <h3>Edit Widget</h3>
    <form method="POST" action="editwidget">
        <table cellpadding="5">
            <tr>
                <td>Widget Name:</td>
                <td><input type="Text" name="widget_name" value="<%=currentWidget.getComments()%>"></td>
            </tr>
            <tr>
                <td>
                    Graph Type:
                </td>
                <td>
                    <select name="graph_type">
                        <%
                            //populate widget types dropdown list
                            for (int i = 0; i < widgetTypes.size(); i++) {
                                WidgetType widgetType = widgetTypes.get(i);
                                //if it's current widget type then make it as selected dropdown option
                                if (widgetType.getId() == currentWidgetType.getId()) {
                                    out.println("<option value=" + widgetType.getId() + " selected>" + widgetType.getName() + "</option>");
                                } else {
                                    out.println("<option value=" + widgetType.getId() + ">" + widgetType.getName() + "</option>");
                                }
                            }
                        %>
                    </select>
                </td>
            <tr>
                <td>
                    Primary metric:
                </td>
                <td>
                    <select name="primary_metric">
                        <%
                            //populate limit metric dropdown list
                            for (int i = 0; i < primaryMetrics.size(); i++) {
                                Metric metric = primaryMetrics.get(i);
                                //if it's current metric then make it as selected dropdown option
                                if (metric.getId() == currentMetricSet.getPrimary_id()) {
                                    out.println("<option value=" + metric.getId() + " selected>" + metric.getName() + "</option>");
                                } else {
                                    out.println("<option value=" + metric.getId() + ">" + metric.getName() + "</option>");
                                }
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Group by metric:
                </td>
                <td>
                    <select name="group_by_metric">
                        <%
                            //populate group by metric dropdown list
                            for (int i = 0; i < groupByMetrics.size(); i++) {
                                Metric metric = groupByMetrics.get(i);
                                //if it's current metric then make it as selected dropdown option
                                if (metric.getId() == currentMetricSet.getGroupby_id()) {
                                    out.println("<option value=" + metric.getId() + " selected>" + metric.getName() + "</option>");
                                } else {
                                    out.println("<option value=" + metric.getId() + ">" + metric.getName() + "</option>");
                                }
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Limit:
                </td>
                <td>
                    <select name="limit_metric">
                        <%
                            //populate limit metric dropdown list
                            for (int i = 0; i < limitMetrics.size(); i++) {
                                Metric metric = limitMetrics.get(i);
                                //if it's current metric then make it as selected dropdown option
                                if (metric.getId() == currentMetricSet.getLimit_id()) {
                                    out.println("<option value=" + metric.getId() + " selected>" + metric.getName() + "</option>");
                                } else {
                                    out.println("<option value=" + metric.getId() + ">" + metric.getName() + "</option>");
                                }
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
            <td></td>
                <td align="right">
                    <input type="hidden" value="<%= dashboard_id %>" name="dashboard_id">
                    <input type="hidden" value="<%= widget_id %>" name="widget_id">
                    <input type="SUBMIT" value="Submit" name="submit">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
































