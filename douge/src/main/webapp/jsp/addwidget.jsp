<%@ page import="java.util.ArrayList" %>
<%@ page import="lv.javaguru.java2.domain.Metric" %>
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
%>

<%
    ArrayList<Metric> metricArrayList = (ArrayList) request.getAttribute("model");


%>

<html>
<head>
    <title>Add new widget</title>
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
                        <option value="0">Timeline</option>
                        <option value="1">Pie Chart</option>
                        <option value="2">Bar Chart</option>
                        <option value="3">Table</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Primary metric:
                </td>
                <td>
                    <select name="primary_metric">
                        <option value="0">Item Count</option>
                        <option value="1">Amount EUR</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Group by metric:
                </td>
                <td>
                    <select name="group_by_metric">
                        <option value="0">Staff</option>
                        <option value="1">Film Category</option>
                        <option value="2">Day</option>
                        <option value="3">Week</option>
                        <option value="4">Month</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Limit:
                </td>
                <td>
                    <select name="limit">
                        <option value="0">1</option>
                        <option value="1">2</option>
                        <option value="2">3</option>
                        <option value="3">4</option>
                        <option value="4">5</option>
                        <option value="5">6</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="right"><input type="SUBMIT" value="Submit" name="submit"></td>
            </tr>
        </table>
    </form>

</body>
</html>
