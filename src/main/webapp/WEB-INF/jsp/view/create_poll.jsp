<%-- 
    Document   : create_poll
    Created on : 2020年4月14日, 下午09:43:17
    Author     : LTW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create MC poll question</h1>

        <form:form method="POST" enctype="multipart/form-data" modelAttribute="poll_question">
            <form:label path="question">Question:  </form:label><br/>
            <form:input type="text" path="question"/><br/><br/>

            <form:label path="ans_a">Answer A:  </form:label><br/>
            <form:input type="text" path="ans_a"/><br/><br/>

            <form:label path="ans_b">Answer B:  </form:label><br/>
            <form:input type="text" path="ans_b"/><br/><br/>

            <form:label path="ans_c">Answer C:  </form:label><br/>
            <form:input type="text" path="ans_c"/><br/><br/>

            <form:label path="ans_d">Answer D:  </form:label><br/>
            <form:input type="text" path="ans_d"/><br/><br/>

            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
