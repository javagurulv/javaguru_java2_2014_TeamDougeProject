<%--
  Created by IntelliJ IDEA.
  User: Radchuk
  Date: 11/14/2014
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%=(String)request.getAttribute("model")%>
<br>
<jsp:include page="templates/adduser_form.html" ></jsp:include>
</body>
</html>
