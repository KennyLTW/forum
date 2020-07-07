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
        <h1>Update user information</h1>

        <form:form method="POST" enctype="multipart/form-data" modelAttribute="pre_update_user">
            <form:label path="username">Username: </form:label><br/>
            <form:input type="text" path="username" value="${pre_update_user.username}" readonly="true"/><br/><br/>

            <form:label path="password">Password: </form:label><br/>
            <form:input type="password" path="password"/><br/><br/>

            <form:hidden path="role" value="ROLE_USER"></form:hidden>
                <br/><br/>
                <input type="submit" value="Submit"/>
        </form:form>

    </body>

</body>
</html>
