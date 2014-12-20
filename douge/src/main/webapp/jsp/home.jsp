<%@ page import="lv.javaguru.java2.domain.Dashboard" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.Widget" %>
<%@ page import="lv.javaguru.java2.servlets.mvc.models.MVCDashboardModel" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05-Dec-14
  Time: 14:10
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
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="/style/style.css">

    <script>
        function addDashboard() {
            window.open("/adddashboard", "_blank", "toolbar=no, scrollbars=no, resizable=no, top=400, left=400, width=300, height=300");
        }
        function addWidget(dashboard_id) {
            window.open("/addwidget?dashboard_id=" + dashboard_id, "_blank", "toolbar=no, scrollbars=no, resizable=no, top=400, left=400, width=300, height=300");
        }
        function editWidget(widget_id) {
            window.open("/editwidget?widget_id=" + widget_id, "_blank", "toolbar=no, scrollbars=no, resizable=no, top=400, left=400, width=300, height=300");
        }
    </script>

</head>
<body>
    <div align="right">Welcome, <%= sessionLogin %>! <a href="/logout">Log Out</a></div><br>
    <div id="header">
        <h2>Team Douge Project</h2>
    </div>
    <div id="nav">
        <h3>Dashboards</h3>

        <%

            MVCDashboardModel dashboardModel = (MVCDashboardModel) request.getAttribute("model");
            ArrayList<Dashboard> dashboardList = (ArrayList) dashboardModel.getData();

            for (int i = 0; i < dashboardList.size(); i++) {
                Dashboard dashboard = dashboardList.get(i);
                out.println("<a href=\"?dashboard_id=" + dashboard.getId() + "\">" + dashboard.getName() + "</a><br>");
            }
        %>
        <br>
        <a href=# onclick="addDashboard()">Create dashboard</a>
    </div>

    <div id="section">
        <%
            if (dashboardModel.getCurrentDashboard() != null) {

                Dashboard currentDashboard = dashboardModel.getCurrentDashboard();
                List<Widget> widgets = currentDashboard.getWidgets();
                Map<Long,String> widgetTypes = new HashMap<Long, String>();
                widgetTypes.put(1L,"includes/charts/pie_chart.jsp");
                widgetTypes.put(2L,"includes/charts/bar_chart.jsp");
                widgetTypes.put(3L,"includes/charts/timeline_chart.jsp");
                widgetTypes.put(4L,"includes/charts/table.jsp");


                for (int j = 0; j < widgets.size(); j++) {
                    Widget widget = widgets.get(j);
                    ;
                    out.println("<div id=\"widget\"><a href=# onclick=\"editWidget(" + widget.getWidget_id() + ")\">" + widget.getComments() + "</a>");
                   // out.println(widget.getJsonWidgetDAta());
        %>
                    <jsp:include page="<%=widgetTypes.get(widget.getWidget_type_id())  %>">
                        <jsp:param name="divId" value="<%=widget.getWidget_id() %>" />
                        <jsp:param name="dataSet" value="<%=widget.getJsonWidgetDAta() %>" />
                    </jsp:include>


            <%      out.println("</div>");
                    if (j % 2 == 1)
                        out.println("<br style=\"clear:both\" />");
                }

                out.println("<div id=\"widget\"><a href=# onclick=\"addWidget(" + currentDashboard.getId() + ")\">Add new widget</a></div>");
            }
        %>

    </div>

    <div id="footer">
        Copyright © Team Douge Project
    </div>

</body>
</html>
