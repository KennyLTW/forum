<%-- 
    Document   : listUser
    Created on : 2020年4月14日, 下午08:21:01
    Author     : LTW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <c:choose>
            <c:when test="${fn:length(ForumAllUser) == 0}">
                <i>There are no tickets in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ForumAllUser}" var="user">
                <tr>
                    <th>Username</th><th>Password</th><th>Roles</th><th>Action</th>
                </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>

        </body>
    </html>
