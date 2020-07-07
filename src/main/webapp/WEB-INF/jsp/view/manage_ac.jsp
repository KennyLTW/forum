<%-- 
    Document   : manage_ac
    Created on : 2020年4月14日, 下午06:52:14
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
        <jsp:include page="header.jsp"></jsp:include>
            <h1>Admin page - manage user</h1>

            <a href="<c:url value="/admin/add_user"/>"> [ Add user ] </a>

        <c:set var="logined_username">
            <security:authentication property="principal.username" />
        </c:set>

        <c:choose>
            <c:when test="${fn:length(ForumAllUser) == 0}">
                <i>There are no tickets in the system.</i>
            </c:when>
            <c:otherwise>
                <table
                    <tr>
                    <th>Username</th><th>Password</th><th>Roles</th><th>Action</th>
                </tr>
                <c:forEach items="${ForumAllUser}" var="user">
                    <tr>
                        <td>${user.username}</td><td>${user.password}</td>
                        <td>
                            <c:choose>
                                <c:when test = "${user.role == 'ROLE_ADMIN'}">
                                    Admin
                                </c:when>
                                <c:otherwise>
                                    Normal user
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test = "${user.role == 'ROLE_ADMIN'  && 
                                                  user.username !=  logined_username}">
                                        permissions fail
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value="/admin/update_user/${user.username}"/>"> [ Update ] </a></br>
                                    <c:if test = "${user.username != logined_username}" >
                                        <a href="<c:url value="/admin/remove_user/${user.username}"/>"> [ Remove ] </a>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>

                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

</body>
</html>
