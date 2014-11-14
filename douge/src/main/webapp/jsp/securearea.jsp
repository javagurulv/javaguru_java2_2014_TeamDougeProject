<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 07-Nov-14
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Secure Area</title>
</head>
<body>
<%
    //allow access only if session exists
    String sessionLogin = null;

    if (session.getAttribute("sessionLogin") == null) {
        response.sendRedirect("/login");
    } else {
        sessionLogin = (String) session.getAttribute("sessionLogin");
    }
%>
Welcome, <%= sessionLogin %>! <a href="/logout">Log Out</a>

</body>
</html>
