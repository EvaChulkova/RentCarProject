<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 17.12.2022
  Time: 08:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:if test="${sessionScope.user.role.name() == 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/add_new_car" method="get">
            <button type="submit">Add new car</button>
        </form>
        <form action="${pageContext.request.contextPath}/bookings" method="get">
            <button type="submit">See all bookings</button>
        </form>
    </c:if>
    <c:if test="${sessionScope.user.role.name() == 'CLIENT'}">
        <form action="${pageContext.request.contextPath}/add_client_info" method="get">
            <button type="submit">Add info about me</button>
        </form>
        <form action="${pageContext.request.contextPath}/client_bookings" method="get">
            <button type="submit">See all my bookings</button>
        </form>
        <form action="/create_booking" method="get">
            <button type="submit">Create booking</button>
        </form>
    </c:if>

</div>
