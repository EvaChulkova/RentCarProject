<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 24.12.2022
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All my bookings</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>All my bookings</h1>
<ul>
    <c:forEach var="client_booking" items="${requestScope.client_bookings}">
        <li>
            <a href="${pageContext.request.contextPath}/see_info_about_booking?bookingId=${client_booking.id}">
                    ${client_booking.carId} - ${client_booking.rentalStart} - ${client_booking.rentalFinish} - ${client_booking.status} - ${client_booking.comment}
            </a>

        </li>
    </c:forEach>
</ul>

<form action="${pageContext.request.contextPath}/available_cars" method="get">
    <button type="submit">Back</button>
</form>

</body>
</html>
