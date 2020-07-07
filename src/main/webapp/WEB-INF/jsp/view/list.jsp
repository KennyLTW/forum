<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <c:choose>
            <c:when test="${fn:length(AllLecturePost) == 0}">
                <i>There are no tickets in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${AllLecturePost}" var="aPost">
                    Post Topic: ${aPost.topic} </br>
                    


                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>
