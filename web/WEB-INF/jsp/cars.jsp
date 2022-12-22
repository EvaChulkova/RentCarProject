<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 17.12.2022
  Time: 08:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Cars</h1>
<ul>
    <c:forEach var = "car" items="${requestScope.cars}">
        <li>
                <%--<img width="150" height="100" src="${pageContext.request.contextPath}/images/${car.image}" alt="No image"><br>--%>
            ${car.id} - ${car.brand} - ${car.color} - ${car.seatAmount} - ${car.price} - ${car.status} - ${car.image}
        </li>
    </c:forEach>
</ul>

</body>
</html>
