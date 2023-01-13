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
<%--<%@ include file="header.jsp" %>--%>
<div>
    <c:if test="${not empty sessionScope.user}">
        <div id="logout">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </c:if>
</div>

<h1>Information about booking</h1>
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
            <br>NOTE:<br>
            <span><b>Canceled at the request of the client</b></span>
        </div>
    </c:if>

    <c:if test="${requestScope.booking.status == 'COMPLETED'}">
        <div style="color: blue">
            <br>NOTE:<br>
            <span><b>Booking completed</b></span>
        </div>
    </c:if>

</ul>


<c:if test="${requestScope.booking.status == 'IN_PROGRESS'}">
    <form action="${pageContext.request.contextPath}/send_cancel_message" method="get">
        <button type="submit" name="bookingId" value="${booking.id}"> Submit an order cancellation request </button>
    </form>
</c:if>

<c:if test="${requestScope.booking.status == 'APPROVED'}">
    <form action="${pageContext.request.contextPath}/send_cancel_message" method="get">
        <button type="submit" name="bookingId" value="${booking.id}"> Submit an order cancellation request </button>
    </form>
</c:if>

<c:if test="${requestScope.booking.status == 'PAYED'}">
    <form action="${pageContext.request.contextPath}/send_cancel_message" method="get">
        <button type="submit" name="bookingId" value="${booking.id}"> Submit an order cancellation request </button>
    </form>
</c:if>

<c:if test="${requestScope.booking.status == 'APPROVED'}">
    <form action="${pageContext.request.contextPath}/pay_booking" method="get">
        <button type="submit" name="bookingId" value="${booking.id}"> Pay booking </button>
    </form>
</c:if>

<form action="${pageContext.request.contextPath}/available_cars" method="get">
    <button type="submit">Go to main page</button>
</form>

<form action="${pageContext.request.contextPath}/client_bookings" method="get">
    <button type="submit">Back</button>
</form>

</body>
</html>
