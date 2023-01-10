<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 25.12.2022
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Check booking</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.checkBooking.checkNewBooking" /></h1>
<ul>
    <b>Booking identification number: </b> ${requestScope.booking.id} \---\ <b>Client ID: </b> ${requestScope.booking.userId} \---\ <b>Car ID: </b> ${requestScope.booking.carId} <br><br>

    <b>Rental period: </b> <br>
    From: ${requestScope.booking.rentalStart}  To: ${requestScope.booking.rentalFinish} <br><br>

    <b>Status: </b>
    ${requestScope.booking.status}, ${requestScope.booking.comment} <br><br>

    <b>Client details: </b><br>
        Client identification number: ${requestScope.userById.id} <br>
        Name: ${requestScope.userById.firstName} ${requestScope.userById.lastName} <br><br>

    <b>Car details: </b><br>
        Car identification number: ${requestScope.carById.id} <br>
        Brand: ${requestScope.carById.brand} <br>
        Color: ${requestScope.carById.color} <br>
        Price per day: ${requestScope.carById.price} rubles

</ul>

<form action="${pageContext.request.contextPath}/check_booking" method="get">
    <button type="submit" name="bookingId" value="${booking.id}"> <fmt:message key="page.checkBooking.button.checkBooking" /></button>
</form>
<form action="${pageContext.request.contextPath}/bookings" method="get">
    <button type="submit"><fmt:message key="page.checkBooking.button.back" /></button>
</form>
</body>
</html>
