<%-- 
    Document   : conndwcSucces
    Created on : Jun 25, 2010, 9:06:03 AM
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
            <h2 style="color:green"><fmt:message key="dwc_success"/></h2><br>
            <fmt:message key="conn_total"/>: ${total} <br><br>
            <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
            <a href="dwcAttributes.htm" class="simple_link"><fmt:message key="continue"/></a>
        </div>
        <!-- Content ending -->
        <div id="footer">
            <fmt:message key="footer_text"/>
        </div>
    </body>
</html>
