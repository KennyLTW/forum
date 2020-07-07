<%-- 
    Document   : viewPost
    Created on : 2020年4月25日, 下午08:56:30
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

            <h1>Post ID: ${post.postid} - ${post.topic}</h1>
        created on: ${post.createddate}
        <h2>#1: ${post.creater}: </h2> 
        <pre>           ${post.body}</pre>

        <c:choose>
            <c:when test="${fn:length(post.attachments) == 0}">
                There are no attachments</br>
            </c:when>
            <c:otherwise>
                Attachments: </br>
                <a href="<c:url value="/login"/>"> --- Login to download attachment --- </a></br>
                <security:authorize access="hasAnyRole('USER', 'ADMIN')">

                    <c:forEach items="${post.attachments}" var="attachment" varStatus="status">
                        <a href="<c:url value="/forum/${post.posttype}/${post.postid}/attachment/${attachment.filename}" />">
                            <c:out value="${attachment.filename}" /></a></br>
                    </c:forEach><br /><br />
                </security:authorize>
            </c:otherwise>
        </c:choose>

        <security:authorize access="hasAnyRole('ADMIN')">
            <c:forEach items="${AllComment}" var="aComment" varStatus="loop">

                #${loop.index +2}: ${aComment.creater}:      <a href="<c:url value="/admin/deleteComment/${aComment.commentid}" />"> [ Delete Comment ] </a>   </br>
                <pre>           ${aComment.comment}</pre>
            </c:forEach>
        </security:authorize>

        <security:authorize access="hasAnyRole('USER')">
            <c:forEach items="${AllComment}" var="aComment" varStatus="loop">

                #${loop.index +2}: ${aComment.creater}:      </br>
                <pre>           ${aComment.comment}</pre>
            </c:forEach>
        </security:authorize>

        <security:authorize access="isAnonymous()">
            <a href="<c:url value="/login"/>"> --- Login to reply --- </a>
        </security:authorize>

        <security:authorize access="hasAnyRole('USER', 'ADMIN')">
            <c:set var="logined_username">
                <security:authentication property="principal.username" />
            </c:set>

            </br> Total number of comment: ${fn:length(AllComment)}</br>
            <form:form method="POST" enctype="multipart/form-data" modelAttribute="reply">

                <form:label path="comment">Comment: </form:label><br/>
                <form:textarea path="comment" rows="5" cols="30" /><br /><br />

                <form:hidden path="creater" value="${logined_username}"></form:hidden>
                <form:hidden path="postid" value="${post.postid}"></form:hidden>
                <form:hidden path="createddate" value=""></form:hidden>
                    <input type="submit" value="Submit"/>

            </form:form>
        </security:authorize>


    </body>
</html>
