<%-- 
    Document   : openpost
    Created on : 2020年4月14日, 下午05:42:28
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
        <c:set var="logined_username">
            <security:authentication property="principal.username" />
        </c:set>

        <jsp:useBean id="createddate" class="java.util.Date" />
        <fmt:formatDate var="date" value="${createddate}" pattern="yyyy/MM/dd HH:MM:ss" />

        <form:form method="POST" enctype="multipart/form-data" modelAttribute="postForm">

            <form:hidden path="posttype" value="" />
            <form:hidden path="creater" value="${logined_username}" />
            <form:hidden path="createddate" value="" />

            <form:label path="topic">Topic: </form:label>
            <form:input path="topic" /></br></br>

            <form:label path="body">Message: </form:label></br>
            <form:textarea path="body" rows="5" cols="30" /><br /><br />

            <b>Attachments</b><br />
            <input type="file" name="attachments" multiple="multiple" /><br /><br />
            <input type="submit" value="Submit"/>
        </form:form>


    </body>
</body>
</html>
