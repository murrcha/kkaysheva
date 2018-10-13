<%@ page import="ru.job4j.service.ValidateService" %>
<%@ page import="ru.job4j.service.User" %>
<%--
  Created by IntelliJ IDEA.
  User: kkaysheva
  Date: 2018-10-13
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h2>Update user</h2>
<table>
    <form action="<%=request.getContextPath()%>/edit" method="post">
        <%User user = ValidateService.getInstance().findById(Integer.valueOf(request.getParameter("id")));%>
        <tr>
            <th>Id</th>
            <td><%=user.getId()%>
            </td>
            <input type="hidden" name="id" value="<%=user.getId()%>"/>
        </tr>
        <tr>
            <th>Login</th>
            <td><input required type="text" name="login" value="<%=user.getLogin()%>"/></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><input required type="text" name="name" value="<%=user.getName()%>"></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><input required type="text" name="email" value="<%=user.getEmail()%>"></td>
        </tr>
        <tr>
            <br/>
            <td colspan="2"><input type="submit" value="SAVE"></td>
        </tr>
    </form>
</table>
</body>
</html>
