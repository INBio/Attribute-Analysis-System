<%-- 
    Document   : error
    Created on : Jun 25, 2010, 3:55:14 PM
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
        <!-- Content -->
        <div id="contenido">
            <h2><fmt:message key="error"/></h2><br>
            <fmt:message key="error_detail"/>${error} <br><br>
            <a class="simple_link" href="config.htm" style="height: 24px; width: 46px">
            <fmt:message key="back"/></a>
        </div>
        <!-- Content ending -->
        <div id="footer">
            <fmt:message key="footer_text"/>
        </div>
    </body>
</html>