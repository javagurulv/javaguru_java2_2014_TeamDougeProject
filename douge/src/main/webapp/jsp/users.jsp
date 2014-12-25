<%@ page import="lv.javaguru.java2.servlets.mvc.models.MVCModel" %>
<%@ page import="org.springframework.web.servlet.ModelAndView" %>
<%--
  Created by IntelliJ IDEA.
  User: Juris
  Date: 10.12.2014
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<head>
    <title></title>
</head>
<body>
<%

    String str = (String) request.getAttribute("model");
    String sessionLogin = (String) session.getAttribute("sessionLogin");
    Long userType = (Long) session.getAttribute("userType");

    if (session.getAttribute("sessionLogin") == null || userType != 0) {
            response.sendRedirect("/index");
    }

%>
<div align="right">Welcome, <%= sessionLogin %>! &nbsp <a href="/home">Home</a> &nbsp <a href="/logout">Log Out</a></div><br>

<script type="text/javascript">
    google.load("visualization", "1", {packages:["table"]});
    google.setOnLoadCallback(drawTable);



    function drawTable() {
        var data = new google.visualization.DataTable("<%out.print(str);%>");

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: false});

        function clickEventHandler(e){
            var selection = table.getSelection();
            var item = selection[0];
            document.getElementById("user_id").value = data.getFormattedValue(item.row,0);
            document.getElementById("userlogin").value = data.getFormattedValue(item.row,1);
            document.getElementById("userpassword").value = data.getFormattedValue(item.row,3);
            document.getElementById("comments").value = data.getFormattedValue(item.row,4);
            document.getElementById("user_type").value = data.getFormattedValue(item.row,2);
        }

        google.visualization.events.addListener(table, 'select', clickEventHandler);
    }

    function userActionEvent(){
       return confirm("Are you sure?");
    }


</script>
<div id="table_div"></div>

    <form method="POST" action="users" id="user_action">
        <input type="hidden" name="user_id" id="user_id">
        <br>

        <Table>
            <tr>
                <td>
                    Login:
                </td>
                <td>
                    <input type="Text" id="userlogin" name="userlogin">
                </td>


                <td rowspan="3">
                    <input type="radio" name="useraction" value="Add"> Add<br>
                    <input type="radio" name="useraction" value="Edit"> Edit<br>
                    <input type="radio" name="useraction" value="Delete" checked>Delete<br>
                </td>

            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="text" id="userpassword" name="userpassword"></td>
            </tr>
            <tr>
                <td>Comments</td>
                <td><input type="text" id="comments" name="comments"></td>
            </tr>
            <tr>
                <td>User Type</td>
                <td>
                    <select name="user_type" id="user_type">
                        <option value="0">Admin</option>
                        <option value="1">User</option>
                    </select></td>
                </td>
            </tr>
            <tr>
                <td>Execute</td>
                <td colspan="3"><input type="submit" value="Let's go bastards!!!!" onclick="return userActionEvent()"> </td>
            </tr>
        </Table>
    </form>

</body>
</html>
