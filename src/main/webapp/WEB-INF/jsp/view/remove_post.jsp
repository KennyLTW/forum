<%-- 
    Document   : remove_post
    Created on : 2020年4月25日, 下午10:47:02
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
        
        <h1>Remove Post</h1>
        Remove: </br>
        <a href="<c:url value="/admin/remove_post/lecture" />"> [ Lecture ] </a> </br>
        <a href="<c:url value="/admin/remove_post/lab" />"> [ Lab ] </a> </br>
        <a href="<c:url value="/admin/remove_post/other" />"> [ Other ] </a> </br>


        <table>
            <tr>
                <th>ID</th>
                <th>Topic</th>                   
                <th>Creater</th>
                <th>Create date</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${AllPost}" var="aPost">
                <tr>
                    <td>${aPost.postid}</td>
                    <td>${aPost.topic}</td>
                    <td>${aPost.creater}</td>
                    <td>${aPost.createddate}</td>
                    <td><a href="<c:url value="/admin/remove_post/${aPost.posttype}/${aPost.postid}" />">[ Delete ]</td>
                </c:forEach>
        </table>


    </body>
</html>
