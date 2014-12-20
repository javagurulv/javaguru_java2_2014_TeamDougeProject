<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05-Dec-14
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("model") != null) {
        Integer errorType = (Integer) request.getAttribute("model");

        if (errorType != 1) { //Redirect immediately
            response.sendRedirect("/home");
        }
    }
%>
<html>
<head>
    <title>Logout</title>
    <meta http-equiv="refresh" content="3;url=/login" />
</head>
<body>
You have successfully logged out. You will be redirected in 3 seconds...
</body>
</html>
