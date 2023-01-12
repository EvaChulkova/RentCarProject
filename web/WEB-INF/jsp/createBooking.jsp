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

<div>
    <c:if test="${not empty sessionScope.user}">
        <div id="logout">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </c:if>
</div>

<h1>Create booking</h1>

<form action="${pageContext.request.contextPath}/create_booking" method="post">
    <label for="availableCarId"> Car:
        <select name="id" id="availableCarId">
            <c:forEach var="availableCars" items="${requestScope.availableCars}">
                <option value="${availableCars.id}">${availableCars.id}</option>
            </c:forEach>
        </select>
    </label><br><br>

    <label for="rentalStartId"> Start time of rent:
        <input type="datetime-local" name="rentalStart" id="rentalStartId">
    </label><br><br>

    <label for="rentalFinishId"> End time of rent:
        <input type="datetime-local" name="rentalFinish" id="rentalFinishId">
    </label><br><br>

    <label>
        <ul>
            <c:forEach var="availableCars" items="${requestScope.availableCars}">
                <b>Description of the selected car:</b><br>
                Car identification number: ${availableCars.id} <br>
                Brand: ${availableCars.brand} <br>
                Color: ${availableCars.color} <br>
                Seat amount: ${availableCars.seatAmount} <br>
                <b>Price per day: </b> ${availableCars.price} rubles <br><br>

                <img width="210" height="170" src="${pageContext.request.contextPath}/images${availableCars.image}" alt="No image"><br><br>
            </c:forEach>
        </ul>
    </label>

    <button type="submit">Create</button>
</form>

<form action="${pageContext.request.contextPath}/available_cars" method="get">
    <button type="submit">Back</button>
</form>

</body>
</html>
