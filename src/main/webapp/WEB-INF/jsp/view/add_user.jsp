<%-- 
    Document   : add_user
    Created on : 2020年4月15日, 上午01:53:00
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
        <h1>Add new user</h1>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="newuser_admin">
            <form:label path="username">Username: </form:label><br/>
            <form:input type="text" path="username"/><br/><br/>

            <form:label path="password">Password: </form:label><br/>
            <form:input type="password" path="password"/><br/><br/>

            <form:label path="role">Role: </form:label><br/>
            <form:radiobutton path="role" value="ROLE_USER" label="ROLE_USER" />
            <form:radiobutton path="role" value="ROLE_ADMIN" label="ROLE_ADMIN" />
            <br/><br/>
            <input type="submit" value="Submit"/>
        </form:form>

    </body>
</html>
