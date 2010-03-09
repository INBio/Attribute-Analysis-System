<%-- 
    Document   : viewChart
    Created on : 09/03/2010, 09:40:31 AM
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
            <div align="center">
                <!-- Chart title -->
                <!--<p class="titles" ><fmt:message key="chart_title"/></p>-->
                <br>
                <a class="link_back" href="statisticalParameters.htm" style="height: 24px; width: 46px">
                    <fmt:message key="back"/></a><br>
                <br>
                <!-- Chart image -->
                <c:choose>
                    <c:when test="${chartDisplay == true}">
                        <img src="chart.htm" ></img>
                    </c:when>
                    <c:otherwise>
                        <p class="titles">
                            No data!
                        </p>
                    </c:otherwise>
                </c:choose>
                <!-- Chart description -->
                <!--<p class="texts"><fmt:message key="chart_description"/></p>-->
            </div>
        </div>
	</body>
</html>
