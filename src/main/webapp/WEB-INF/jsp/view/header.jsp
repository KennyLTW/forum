<%-- 
    Document   : header
    Created on : 2020年4月14日, 下午05:53:44
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

        <security:authorize access="isAnonymous()">
            <c:set var="userID" scope="session" value="guest"></c:set>
            Hi, guest!
            <c:set var="identify" scope="session" value="guest"> </c:set>
            <a href="<c:url value="/login"/>">[ Login ]</a>
            <a href="<c:url value="/create_acc"/>">[ Create account ]</a>
        </security:authorize>

        <security:authorize access="hasAnyRole('USER', 'ADMIN')">
            Hi, <security:authentication property="principal.username" />
            <c:set var="userID" scope="session">
                <security:authentication property="principal.username" />
            </c:set>
            <c:set var="identify" scope="session" value="notGuest"></c:set>

            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>

        <security:authorize access="hasAnyRole('ADMIN')">
            <a href="<c:url value="/admin/manage_ac"/>">Manage user account</a>
            <a href="<c:url value="/admin/manage_post"/>">Manage forum post</a>
        </security:authorize>
        <a href="<c:url value="/forum/index"/>"> Back to index </a>
    </body>
</html>
