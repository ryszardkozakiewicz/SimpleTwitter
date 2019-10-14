<%--
  Created by IntelliJ IDEA.
  User: Rico
  Date: 13.10.2019
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Messages</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background: #f5f5f5
        }

        .border-bottom {
            border-bottom: 1px solid #e5e5e5;
        }

        .box-shadow {
            box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05);
        }

        .blue {
            background-color: #00b5ec !important;
        }
    </style>
</head>
<body>
<main role="main" class="container">

    <%@include file="header.jsp" %>

    <div class="my-3 p-3 bg-white rounded box-shadow">
        <form action="addMessage" method="POST">
            <div class="form-group">
                <textarea class="form-control rounded-0" name="tweetMessage" rows="3"></textarea>
            </div>
            <button type="submit" class="btn btn-primary blue">Tweet</button>
        </form>
    </div>

    <div class="my-3 p-3 bg-white rounded box-shadow">
        <h6 class="border-bottom border-gray pb-2 mb-0">Recent updates</h6>
        <c:forEach items="${tweets}" var="tweet">
            <div class="media text-muted pt-3">
                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="" class="mr-2 rounded" width="32"
                     height="32">
                <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">

                    <strong class="d-block text-gray-dark">
                            ${tweet.author.login}

                    </strong>
                        ${tweet.message}
                    <br>
                    Published at: <fmt:formatDate value="${tweet.publishedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </p>
                <c:if test="${tweet.author.login.equals(sessionScope.login)}">
                    <a href="deleteTweet?tweetId=${tweet.id}">Delete</a>
                </c:if>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>
