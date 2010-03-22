<%-- 
    Document   : statistical
    Created on : 03/03/2010, 04:30:23 PM
    Author     : esmata
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/analysisTags.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"
        href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='styleSheet'/>"/>
        <title><fmt:message key="title"/></title>

        <script language="javascript">

            /*              Global variables                */
            //Current selected layer
            var layerId; //(FID)
            var layerName; //(Name)
            //Available layers [[id,name],...]
            var layersList;

            //Passing parameters to controller class throw path property
            function doSubmit(){
                var form = document.getElementById('parameters');

                var xData = document.getElementById('xData');
                var yData = document.getElementById('yData');
                var xTitle = document.getElementById('xTitle');
                var yTitle = document.getElementById('yTitle');

                xData.value = "pruebaX";
                yData.value = "pruebaY";
                xTitle.value = "Titulo X";
                yTitle.value = "Titulo Y";

                form.submit();
            }
        </script>

        <!-- Internacionalization-->
        <script type="text/javascript">
            function internationalization(){
                layerText =  "<fmt:message key="layers"/>";
                loadingText = "<fmt:message key="loading"/>";
                selectDD = "<fmt:message key="drop_down_null_option"/>";
            };
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
                <form:hidden path="xtitle" id="xTitle"/>
                <form:hidden path="ytitle" id="yTitle"/>
            </form:form>

            <h2><fmt:message key="statistic_analysis"/></h2>

            <div id="querysPanel">
                <!-- GIS Panel -->
                <div id="queryPanel">
                    <p style="font-weight:bold;font-style:italic;margin:2px;text-align:center;">
                    <fmt:message key="geografical_criteria_title"/></p>
                    <div id="currentLayer"></div>  <!-- Layers drop down -->
                    <div id="info"></div> <!-- Polygons drop down -->
                    <span id="mapParameters" style="font-size:10px"></span>
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

