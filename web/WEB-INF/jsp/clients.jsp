
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
<h1><fmt:message key="page.clients.allClients" />Clients</h1>

<ul>
    <c:forEach var="client" items="${requestScope.clients}">
        <li>
            ${client.id} - ${client.userId} - ${client.age} - ${client.licenceNo} - ${client.validity}
        </li>
    </c:forEach>
</ul>
</body>
</html>
