<%--
  Created by IntelliJ IDEA.
  User: kkaysheva
  Date: 2018-10-13
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <th>Role</th>
        <th>Created</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td>
            <c:choose>
                <c:when test="${user.role == 1}">ADMIN</c:when>
                <c:when test="${user.role == 2}">USER</c:when>
            </c:choose>
        <td><c:out value="${user.createDate}"/></td>
        <c:if test="${pageContext.session.getAttribute('role') == 1 || pageContext.session.getAttribute('login') == user.login}">
            <td>
                <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="EDIT"/>
                </form>
            </td>
        </c:if>
    </tr>
    </c:forEach>
</table>
<br/>
<c:if test="${pageContext.session.getAttribute('role') == 1}">
    <form action="${pageContext.servletContext.contextPath}/create" method="get">
        <input type="submit" value="CREATE NEW USER"/>
    </form>
</c:if>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="submit" value="EXIT"/>
</form>
</body>
</html>
