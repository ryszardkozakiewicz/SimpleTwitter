<%--
  Created by IntelliJ IDEA.
  User: Rico
  Date: 12.10.2019
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>hello</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background: #f5f5f5
        }

        .text-white-50 {
            color: rgba(255, 255, 255, .5);
        }

        .bg-blue {
            background-color: #00b5ec;
        }

        .box-shadow {
            box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05);
        }

        .tab {
            padding-left: 50px;
        }
    </style>
</head>
<body>

<div class="d-flex align-items-center p-3 my-3 text-white-50 bg-blue rounded box-shadow">
    <img class="mr-3" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" width="48" height="48">
    <div class="lh-100">
        <h6 class="mb-0 text-white lh-100">${fn:toUpperCase(login)}</h6>
    </div>
    <div class="lh-100">
        <h6 class="mb-0 lh-100 tab">
            <a class="text-white" href="messages">Messages</a>
        </h6>
    </div>
    <div class="lh-100">
        <h6 class="mb-0 lh-100 tab">
            <a class="text-white" href="users">Users</a>
        </h6>
    </div>
    <div class="lh-100 ml-auto">
        <h6 class="mb-0 lh-100">
            <a class="text-white" href="logout">Log out</a>
        </h6>
    </div>
</div>

</body>
</html>
