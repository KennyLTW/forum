<%-- 
    Document   : forum
    Created on : 2020年4月14日, 上午12:49:39
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

            <h1>Welcome to OUHK Course Discussion Forum.</h1>

            There are 3 categories:</br>
            <a href="<c:url value="/forum/lecture"/>">[ lecture ]</a></br>
        <a href="<c:url value="/forum/lab"/>">[ lab ]</a></br>
        <a href="<c:url value="/forum/other"/>">[ other ]</a></br></br>

        <c:choose>
            <c:when test="${totalquestion} == 0">
                There are no poll
            </c:when>

            <c:otherwise>
                <c:forEach begin="0" end="${totalquestion -1}" var="index">
                    Question: ${AllPollResult[index][0]} </br>
                    Ans A: ${AllPollResult[index][1]} - ${AllPollResult[index][5]} </br> 
                    <fmt:parseNumber var="a" value="${AllPollResult[index][5]}" integerOnly="true"/>

                    Ans B: ${AllPollResult[index][2]} - ${AllPollResult[index][6]}</br>
                    <fmt:parseNumber var="b" value="${AllPollResult[index][6]}" integerOnly="true"/>

                    Ans C: ${AllPollResult[index][3]} - ${AllPollResult[index][7]}</br>
                    <fmt:parseNumber var="c" value="${AllPollResult[index][7]}" integerOnly="true"/>

                    Ans D: ${AllPollResult[index][4]} - ${AllPollResult[index][8]}</br>
                    <fmt:parseNumber var="d" value="${AllPollResult[index][8]}" integerOnly="true"/>
                    Total Poll Number: <c:out value="${  a + b + c + d   }" /> </br></br>


                </c:forEach>
            </c:otherwise>

        </c:choose>


        <security:authorize access="hasAnyRole('USER')">

            <c:if test="${fn:length(AllPoll) > 0}">
                There are some recently vote: </br>

                <c:forEach items="${AllPoll}" var="poll" >              

                    <form id="${poll.id}" method="POST" action="../forum/index/${poll.id}">

                        <label for="question" >Question: </label> ${poll.question} </br>
                        <input type="hidden" name="question" value="${poll.question}"/>

                        <input type="radio" id="${poll.ans_a}" name="user_result" value="${poll.ans_a}" />
                        <label for="${poll.ans_a}">${poll.ans_a}</label></br>

                        <input type="radio" id="${poll.ans_b}" name="user_result" value="${poll.ans_b}" />
                        <label for="${poll.ans_b}">${poll.ans_b}</label></br>

                        <input type="radio" id="${poll.ans_c}" name="user_result" value="${poll.ans_c}" />
                        <label for="${poll.ans_c}">${poll.ans_c}</label></br>

                        <input type="radio" id="${poll.ans_d}" name="user_result" value="${poll.ans_d}" />
                        <label for="${poll.ans_d}">${poll.ans_d}</label></br>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="username" 
                               value="<security:authentication property="principal.username" />"/>
                        <input type="submit" value="Submit">

                        </br></br>
                    </form>

                </c:forEach>
            </c:if>

            <c:if test="${fn:length(AllPoll) == 0}">
                You don't have any poll! </br></br>

            </c:if>

        </security:authorize>



    </body>
</html>
