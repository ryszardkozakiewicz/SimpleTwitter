<%--
  Created by IntelliJ IDEA.
  User: Rico
  Date: 12.10.2019
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Test</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<h2>
    <br>
    <strong class="d-block text-gray-dark">${requestScope.user.name}</strong>
    <br>
    <strong class="d-block text-gray-dark">${user.email}</strong>
    <br>
    <strong class="d-block text-gray-dark">${user}</strong>

    <fmt:formatDate value="${requestScope.now}" pattern="yyyy-MM-dd HH:mm:ss"/>
    <br>
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>


    <h3>
        <stron>${me}</stron>
        <br>
        <br>
        <c:forEach items="${users}" var="user">

            <c:if test='${!fn:startsWith(user.name, "M" )}'>
                <div>
                    Users: ${user}

                </div>
            </c:if>
            <br>
        </c:forEach>


    </h3>

    <p>
        Me from undefined scope = ${me} <br>
        Me from reques scope = ${requestScope.me} <br>
        Me from session scope without get = ${sessionScope.me}<br>
        Me from seesion scope with get method = ${sessionScope.get("me")} <br>
    </p>


</h2>
</body>
</html>





