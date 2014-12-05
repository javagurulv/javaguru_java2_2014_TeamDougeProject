<%@ page import="lv.javaguru.java2.domain.Dashboard" %>
<%@ page import="java.util.ArrayList" %>
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
</head>
<body>
    <div align="right">Welcome, <%= sessionLogin %>! <a href="/logout">Log Out</a></div><br>
    <h3>Dashboards:</h3>
    <%
        ArrayList<Dashboard> dashboardList;
        dashboardList = (ArrayList) request.getAttribute("model");

        for (int i = 0; i < dashboardList.size(); i++) {
            Dashboard dashboard = dashboardList.get(i);
            out.println("<a href=\"?id=" + dashboard.getId() + "\">" + dashboard.getName() + "</a><br>");
        }
    %>
</body>
</html>
