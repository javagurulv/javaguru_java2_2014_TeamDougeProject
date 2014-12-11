<%@ page import="lv.javaguru.java2.domain.Dashboard" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.Widget" %>
<%@ page import="lv.javaguru.java2.servlets.mvc.models.MVCDashboardModel" %>
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
    <link rel="stylesheet" type="text/css" href="/jsp/includes/style.css">
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

            //out.println("<br>Current Dashboard ID: " + currentDashboardId);

        %>
    </div>

    <div id="section">
        <%
            if (dashboardModel.getCurrentDashboard() != null) {

                //for (int i = 0; i < dashboardList.size(); i++) {
                Dashboard currentDashboard = dashboardModel.getCurrentDashboard();
                List<Widget> widgets = currentDashboard.getWidgets();

                for (int j = 0; j < widgets.size(); j++) {
                    Widget widget = widgets.get(j);
                    out.println("<div id=\"widget\"><a href=\"?widget_id=" + widget.getWidget_id() + "\">" + widget.getComments() + "</a></div>");
                    if (j % 2 == 1)
                        out.println("<br style=\"clear:both\" />");
                }
                //}
            }
        %>

        <div id="widget"><a href="/addwidget">Add new widget</a></div>

    </div>

    <div id="footer">
        Copyright © Team Douge Project
    </div>

</body>
</html>
