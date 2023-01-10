
<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 17.12.2022
  Time: 08:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Clients</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.clients.allClients" /></h1>

<ul>
    <c:forEach var="client" items="${requestScope.clients}">
        <li>
            <b>Client ID: </b> ${client.id} - <b>User ID: </b> ${client.userId} <br>
            <b>Driving licence No: </b> ${client.licenceNo} <br>
            <b>Driving licence validity: </b> ${client.validity} <br><br>
        </li>
    </c:forEach>
</ul>

<form action="${pageContext.request.contextPath}/cars" method="get">
    <button type="submit"><fmt:message key="page.bookings.button.back" /></button>
</form>
</body>
</html>
