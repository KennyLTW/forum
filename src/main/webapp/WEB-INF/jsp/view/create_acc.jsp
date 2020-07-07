<%-- 
    Document   : create_acc
    Created on : 2020年4月14日, 上午12:56:49
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
        <h1>Create account</h1>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="newuser">
            <form:label path="username">Username: </form:label><br/>
            <form:input type="text" path="username"/><br/><br/>
            
            <form:label path="password">Password: </form:label><br/>
            <form:input type="password" path="password"/><br/><br/>
            
            <form:hidden path="role" value="ROLE_USER"></form:hidden>
            <br/><br/>
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
