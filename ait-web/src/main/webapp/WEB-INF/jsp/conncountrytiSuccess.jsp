<%-- 
    Document   : conncountrySuccess
    Created on : March 15, 2011, 3:42:17 PM
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
                <h2 style="color:green"><fmt:message key="dwc_success"/></h2>
                <img src="${pageContext.request.contextPath}/themes/default/images/ok.png" ></img>
                <fmt:message key="conn_total"/>: ${total} <br><br>
                <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
                <a href="countrytiAttributes.htm" class="simple_link"><fmt:message key="continue"/></a>
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
