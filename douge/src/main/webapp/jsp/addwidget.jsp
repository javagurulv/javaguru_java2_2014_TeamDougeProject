<%@ page import="lv.javaguru.java2.domain.Metric" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11-Dec-14
  Time: 18:35
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

    if (request.getParameter("dashboard_id") != null) {
        dashboard_id = Integer.valueOf(request.getParameter("dashboard_id"));
    }

    //get model
    Map<String, List<Metric>> metricMap  = (HashMap) request.getAttribute("model");
%>

<html>
<head>
    <title>Add new widget</title>
    <script>
        window.onunload = refreshParent;
        function refreshParent() {
            window.opener.location.reload();
        }
    </script>
</head>
<body>
    <h2>Add Widget</h2>
    <form method="POST" action="addwidget">
        <table cellpadding="5">
            <tr>
                <td>Widget Name:</td>
                <td><input type="Text" name="widget_name"></td>
            </tr>
            <tr>
                <td>
                    Graph Type:
                </td>
                <td>
                    <select name="graph_type">

                        <option value="1">Pie Chart</option>
                        <option value="2">Bar Chart</option>
                       <!-- <option value="3">Timeline</option> -->
                        <option value="4">Table</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Primary metric:
                </td>
                <td>
                    <select name="primary_metric">
                        <%
                            //populate primary metric dropdown list
                            for (int i = 0; i < metricMap.get("Primary").size(); i++) {
                                Metric metric = metricMap.get("Primary").get(i);
                                out.println("<option value=\"" + metric.getId() + "\">" + metric.getName() + "</option>");
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
                            for (int i = 0; i < metricMap.get("GroupBy").size(); i++) {
                                Metric metric = metricMap.get("GroupBy").get(i);
                                out.println("<option value=\"" + metric.getId() + "\">" + metric.getName() + "</option>");
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
                            for (int i = 0; i < metricMap.get("Limit").size(); i++) {
                                Metric metric = metricMap.get("Limit").get(i);
                                out.println("<option value=\"" + metric.getId() + "\">" + metric.getName() + "</option>");
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="right">
                    <input type="hidden" value="<%= dashboard_id %>" name="dashboard_id">
                    <input type="SUBMIT" value="Submit" name="submit">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
