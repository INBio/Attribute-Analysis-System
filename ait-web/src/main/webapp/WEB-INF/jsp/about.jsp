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
        <form name = "form1" method = "get">
            <div id="contenido">
                <!-- Header -->
                <jsp:include page="/WEB-INF/jsp/header.jsp"/>

                <div id="content">
                    <h2><fmt:message key="about_title"/></h2>
                    <p><fmt:message key="about_content"/></p>
                    <h3><fmt:message key="about_f_title"/></h3>
                    <ul>
                    <li><fmt:message key="about_sstn"/> <a href="http://ara.inbio.ac.cr/SSTN-IABIN/welcome.htm">http://ara.inbio.ac.cr/SSTN-IABIN/welcome.htm</a>
                    <li><fmt:message key="about_ccad"/> <a href="http://www.ccad.ws/documentos/mapas.html">http://www.ccad.ws/documentos/mapas.html</a>
                    <fmt:message key="about_paises"/> <a href="http://www.diva-gis.org/Data">http://www.diva-gis.org/Data</a>
                    <li><fmt:message key="about_inbio"/>
                    <li><fmt:message key="about_uicn"/> <a href="http://www.iucn.org/">http://www.iucn.org/</a>
                    </ul>
                </div>
                
                <!-- Footer -->
                <div id="footer">
                    <fmt:message key="footer_text"/>
                </div>
            </div>
        </form>
    </body>
</html>
