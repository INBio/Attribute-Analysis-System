<%-- 
    Document   : about
    Created on : 03/02/2010, 03:10:48 PM
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
        <!-- Header -->
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>
        <form name = "form1" method = "get">
            <div id="contenido">
                <h2><fmt:message key="about_title"/></h2>
                <p><fmt:message key="about_content"/></p>
            </div>
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </form>
    </body>
</html>
