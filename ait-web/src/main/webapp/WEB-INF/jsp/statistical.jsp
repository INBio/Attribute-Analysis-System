<%-- 
    Document   : statistical
    Created on : 03/03/2010, 04:30:23 PM
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

        <script language="javascript">
            function doSubmit(){
                var form = document.getElementById('parameters');

                var xData = document.getElementById('xData');
                var yData = document.getElementById('yData');

                xData.value = "pruebaX";
                yData.value = "pruebaY";

                form.submit();
            }
        </script>
        
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>

        <!-- Content -->
        <div id="contenido">

            <!-- Chart parameters form -->
            <form:form method="POST" commandName="parameters" cssStyle="margin:0">
                <!-- Values to get data for building the chart -->
                <form:hidden path="xdata" id="xData"/>
                <form:hidden path="ydata" id="yData"/>
            </form:form>

            <h2><fmt:message key="statistic_analysis"/></h2>

            <div id="querysPanel">
                <!-- GIS Panel -->
                <div id="queryPanel">
                </div>

                <!-- Taxonomy Panel -->
                <div id="queryPanel">
                </div>

                <!-- Indicator Panel -->
                <div id="queryPanel">
                </div>

                <!-- Query Button -->
                <input type="submit" class="main_Button" id="makeQueryButton" value="<fmt:message key="consult"/>"
                       onclick="doSubmit()" />

            </div>

            <div id="mapPanel">
            </div>

            <div id="resultsPanel">
            </div>

        </div>
        <!-- Content ends -->
    </body>
</html>

