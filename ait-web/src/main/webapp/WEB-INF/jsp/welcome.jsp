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
            <!--<h2><fmt:message key="welcome"/></h2>--><br><br><br><br>

            <div align="center"> <table width="50%" border="0" align="center" cellpadding="0" cellspacing="1" class="tabla-opciones">
                    <!--<tr>
                        <td colspan="2" class="celda01-opciones"><span class="texto-principal"><fmt:message key="greeting"/></span></td>
                    </tr>-->
                    <tr>
                        <td width="22%" class="celda01-opciones"><img src="themes/default/images/ico-geoespacial.png" width="61" height="66"></td>
                        <td width="78%" class="celda02-opciones"><a class="link-celda02" href="analysis.htm" style="height: 24px; width: 46px;"><fmt:message key="analysis_title"/></a><br/></td>
                    </tr>
                    <tr>
                        <td class="celda01-opciones"><img src="themes/default/images/ico-estadisticas.png" width="61" height="66"></td>
                        <td class="celda02-opciones"><a class="link-celda02" href="statisticalParameters.htm" style="height: 24px; width: 46px"><fmt:message key="statistic_analysis"/></a></td>
                    </tr>
                    <tr>
                        <td class="celda01-opciones"><img src="themes/default/images/ico-config.png" width="61" height="66"></td>
                        <td class="celda02-opciones"><a class="link-celda02" href="config.htm" style="height: 24px; width: 46px"><fmt:message key="config_title"/></a></td>
                    </tr>
                </table>
            </div>
            
        </div>
        <!-- Content ending -->
        <div id="footer">
            <fmt:message key="footer_text"/>
        </div>
    </body>
</html>