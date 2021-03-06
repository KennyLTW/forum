<%-- 
    Document   : lab
    Created on : 2020年4月14日, 下午05:16:46
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
            <h1>Lab</h1>

        <security:authorize access="hasAnyRole('USER', 'ADMIN')">
            <a href="<c:url value="/forum/lab/openpost"/>">[ create a new post ]</a></br>
        </security:authorize>

        <c:choose>
            <c:when test="${fn:length(AllLabPost) == 0}">
                <i>There are no post in the Lab.</i>
            </c:when>
            <c:otherwise>
                </br> Total number of post: ${fn:length(AllLabPost)}</br>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Topic</th>                   
                        <th>Creater</th>
                        <th>Create date</th>
                    </tr>

                    <c:forEach items="${AllLabPost}" var="aPost">
                        <tr>
                            <td>${aPost.postid}</td>
                            <td>
                                <a href="<c:url value="/forum/lab/${aPost.postid}" />"> ${aPost.topic}
                            </td> 
                            <td>${aPost.creater} </td>
                            <td>${aPost.createddate}</td>
                        </tr>
                    </c:forEach>

                </table>
            </c:otherwise>
        </c:choose>

    </body>
</html>
