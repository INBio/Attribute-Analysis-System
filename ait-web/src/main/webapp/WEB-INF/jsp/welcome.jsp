<%-- 
    Document   : hello
    Created on : 11/01/2010, 11:13:15 AM
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
            <h2><fmt:message key="welcome"/></h2>
            <!--<p><c:out value="${now}"/></p>-->
            <p><fmt:message key="greeting"/></p>
            <div id="menu_links">
                <a class="link" href="analysis.htm" style="height: 24px; width: 46px;">
                    <fmt:message key="analysis_title"/></a><br/>
                <a class="link" href="statistical.htm" style="height: 24px; width: 46px">
                    <fmt:message key="statistic_analysis"/></a><br>
                <a class="link" href="config.htm" style="height: 24px; width: 46px">
                    <fmt:message key="config_title"/></a>
            </div>
        </div>
        <!-- Content ending -->
    </body>
</html>