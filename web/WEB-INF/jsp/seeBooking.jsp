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
    <title>My booking</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.seeBooking.seeInfo" /></h1>
<ul>
    <b>Booking identification number:</b>  ${requestScope.booking.id}<br><br>

    <b>Car details:</b> <br>
    Car identification number: ${requestScope.booking.carId} <br>
    Brand: ${requestScope.carById.brand} <br>
    Color: ${requestScope.carById.color} <br>
    Seat amount: ${requestScope.carById.seatAmount} <br>
    <b>Price per day: </b> ${requestScope.carById.price} rubles <br>

    <img width="210" height="170" src="${pageContext.request.contextPath}/images${requestScope.carById.image}" alt="No image"><br><br>

    <b>Rental period:</b> <br>
    From: ${requestScope.booking.rentalStart} - To: ${requestScope.booking.rentalFinish} <br><br>

    <b>Status:</b>  <br>
    ${requestScope.booking.status}, ${requestScope.booking.comment}

    <c:if test="${requestScope.booking.status == 'CANCELLED'}">
        <div style="color: red">
            <span><b><fmt:message key="page.checkBooking.cancelled" /></b></span>
        </div>
    </c:if>

</ul>



<c:if test="${requestScope.booking.status != 'CANCELLED'}">
    <form action="${pageContext.request.contextPath}/send_cancel_message" method="get">
        <button type="submit" name="bookingId" value="${booking.id}"> <fmt:message key="page.seeBooking.sendCancelMessage" /> </button>
    </form>
</c:if>




<form action="${pageContext.request.contextPath}/client_bookings" method="get">
    <button type="submit"><fmt:message key="page.seeBooking.button.back" /></button>
</form>

</body>
</html>
