<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 17.12.2022
  Time: 08:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.cars.allCars" /></h1>
<ul>
    <c:forEach var = "car" items="${requestScope.cars}">
        <li>
            <img width="210" height="170" src="${pageContext.request.contextPath}/images${car.image}" alt="No image"><br>
            <b>Car ID: </b> ${car.id} <br>
            <b>Brand: </b> ${car.brand} \---\ <b>Color: </b> ${car.color} \---\ <b>Seat amount: </b> ${car.seatAmount} <br>
            <b>Price per day: </b> ${car.price} rubles <br>
            <b>Status: </b> ${car.status} <br>
        </li><br>
    </c:forEach>
</ul>
<%@ include file="footer.jsp" %>
</body>
</html>
