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
            <h1>Admin page - manage post</h1>
            <a href="<c:url value="/admin/create_poll"/>">Create a new forum poll</a>
        </br>
        <a href="<c:url value="/admin/remove_post"/>">Delete forum reply or thread</a>



    </body>
</html>
