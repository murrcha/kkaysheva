<%@ page import="ru.job4j.service.ValidateService" %>
<%@ page import="ru.job4j.service.User" %>
<%--
  Created by IntelliJ IDEA.
  User: kkaysheva
  Date: 2018-10-13
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h2>Users list</h2>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
        <th>Email</th>
        <th>Created</th>
        <th>Action</th>
    </tr>
    <%for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getCreateDate()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/edit.jsp" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="EDIT"/>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<br/>
<form action="<%=request.getContextPath()%>/create.jsp" method="get">
    <input type="submit" value="CREATE NEW USER"/>
</form>
</body>
</html>
