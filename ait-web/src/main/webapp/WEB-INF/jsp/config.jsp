<%-- 
    Document   : config
    Created on : 15/01/2010, 01:32:22 PM
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
        <form name = "config" method = "get">
            <div id="contenido">
                <h2><fmt:message key="config_title"/></h2><br>
                <a href="conndwc.htm">1. <fmt:message key="dwc_config"/></a><br>
                <a href="hi.htm">2. Configurar acceso a los datos de especies (Plinian Core) FIXME</a><br>
                <a href="hi.htm">3. Configurar acceso a los datos de atributos taxonómicos FIXME</a><br>
                <a href="hi.htm">4. Configurar acceso a Postgis FIXME</a><br>
            </div>
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </form>
        <!-- Content ending -->
    </body>
</html>

