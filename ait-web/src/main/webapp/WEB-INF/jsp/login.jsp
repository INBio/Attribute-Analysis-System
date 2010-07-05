<%-- 
    Document   : login
    Created on : May 14, 2010, 11:59:49 AM
    Author     : esmata
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tags.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"
        href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='styleSheet'/>"/>
        <title><fmt:message key="title"/></title>
    </head>
    <body>
        <!-- Content -->
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/jsp/header.jsp"/>

            <div id="content">
                <br><br>
                <form method="post" accept-charset="UTF-8" action="j_spring_security_check">

                    <c:if test="${not empty param.error}">
                        <font color="red">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</font>
                    </c:if>

                    <%--UserName --%>
                    <label>
                        <b><fmt:message key="login_username"/>:</b><br>
                        <input type="text" class="componentSize" id="userNameInput" name="j_username" />
                    </label>
                    <br>

                    <%--Password --%>
                    <label>
                        <b><fmt:message key="login_password"/>:</b><br>
                        <input type="password" class="componentSize" name="j_password"/>
                    </label>
                    <br><br>

                    <input type="submit" value="<fmt:message key="accept"/>" />
                    <input type="reset" name="<fmt:message key="reset"/>" />
                </form>
            </div>

            <!-- Footer -->
            <br><br>
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </div>
        <!-- Content ending -->
    </body>
</html>
