<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Date: 10/20/2016
  Time: 7:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Models.User" scope="session"/>

<header>
    <h1>UoN Sales</h1>
    <nav>
        <a href="index.jsp">Home</a> |
        <%--
        <c:choose>
            <c:when test="${user.isStaff()}">
                <a href="viewReported.jsp">View reported issues</a> |
            </c:when>
            <c:otherwise>
                <a href="viewCurrent.jsp">View current</a> |
            </c:otherwise>
        </c:choose>
        --%>
    </nav>
</header>
