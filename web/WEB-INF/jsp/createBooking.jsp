<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 23.12.2022
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Create booking</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.createBooking.createBooking" /></h1>

<form action="${pageContext.request.contextPath}/create_booking" method="post">
    <label for="availableCarId"><fmt:message key="page.createBooking.car" />:
        <select name="id" id="availableCarId">
            <c:forEach var="availableCars" items="${requestScope.availableCars}">
                <option value="${availableCars.id}">${availableCars.id}</option>
            </c:forEach>
        </select><br>
    </label>

    <label for="rentalStartId"> <fmt:message key="page.createBooking.rentalStartTime" />:
        <input type="datetime-local" name="rentalStart" id="rentalStartId">
    </label><br>

    <label for="rentalFinishId"> <fmt:message key="page.createBooking.rentalFinishTime" />:
        <input type="datetime-local" name="rentalFinish" id="rentalFinishId">
    </label><br>

    <label>
        <ul>
            <c:forEach var="availableCars" items="${requestScope.availableCars}">
                <h3>Description of the selected car:</h3>
                Car identification number: ${availableCars.id} <br>
                Brand: ${availableCars.brand} <br>
                Color: ${availableCars.color} <br>
                Seat amount: ${availableCars.seatAmount} <br>
                Price per day: ${availableCars.price} rubles <br>

                <img width="210" height="170" src="${pageContext.request.contextPath}/images${availableCars.image}" alt="No image"><br>
            </c:forEach>
        </ul>
    </label>

    <button type="submit"><fmt:message key="page.createBooking.button.send" /></button>
</form>

<form action="${pageContext.request.contextPath}/available_cars" method="get">
    <button type="submit"><fmt:message key="page.createBooking.button.back" /></button>
</form>

</body>
</html>
