<%-- 
    Document   : viewChart
    Created on : 09/03/2010, 09:40:31 AM
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
        <link rel="stylesheet" type="text/css"
        href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='container'/>"/>
        <title><fmt:message key="title"/></title>
        <script type="text/javascript">

            //Using to show the loading panel
            YAHOO.namespace("example.container");
            var loadingImage = "<img src=\"${pageContext.request.contextPath}/themes/default/images/ajax-loader.gif\" ></img>";
            var loadingText = "<fmt:message key="loading"/>";
            var criteriaText = "<fmt:message key="criteria"/>";
            var speciesText = "<fmt:message key="species"/>";
            var speciesList = "<fmt:message key="species_list"/>";

            /*
             * Initialize a panel to show the loading image
             */
            function initLoadingPanel(){
                if (!YAHOO.example.container.wait) {
                   YAHOO.example.container.wait =
                        new YAHOO.widget.Panel("wait",
                    {
                        width:"240px",
                        fixedcenter:true,
                        close:false,
                        draggable:false,
                        zindex:999,
                        modal:true,
                        visible:false
                    }
                );
                    YAHOO.example.container.wait.setHeader(loadingText);
                    YAHOO.example.container.wait.setBody(loadingImage);
                    YAHOO.example.container.wait.render(document.getElementById('contenido'));
                }
            }

            /*
             * To show the chart delais
             */
            function viewChartDetail(){
                //Show loading
                YAHOO.example.container.wait.show();
                //Getting data from controller
                var xdata = "${xdata}";
                var ydata = "${ydata}";
                var xaxis = "${xaxis}";
                var yaxis = "${yaxis}";
                var xdatatoshow = "${xdatatoshow}";
                var ydatatoshow = "${ydatatoshow}";
                var isgeo = "${isgeo}";
                var layers = "",taxa="",indi="",lToShow="";

                if(xaxis=='1') // taxanomic
                    taxa = xdata;
                if(xaxis=='2') //geographic
                    layers = xdata;
                if(xaxis=='3') //indicators
                    indi = xdata;

                if(yaxis=='1') // taxanomic
                    taxa = ydata;
                if(yaxis=='2') //geographic
                    layers = ydata;
                if(yaxis=='3') //indicators
                    indi = ydata;

                if(isgeo=='x')
                    lToShow = xdatatoshow;
                if(isgeo=='y')
                    lToShow = ydatatoshow;
                
                //Showing the details
                viewSimpleDetail(layers,taxa,indi,lToShow);
            }

        </script>
	</head>
	<body onload="initLoadingPanel()" class=" yui-skin-sam">
        <!-- Content -->
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/jsp/header.jsp"/>

            <div id="content">
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
                            <div id="details">
                                <input type="button" class="simple_button" id="showOnMap0" value="<fmt:message key="see_detail"/>" onclick="viewChartDetail()" />
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p class="titles">
                                No data!
                            </p>
                        </c:otherwise>
                    </c:choose>
                    <!-- Panel to show the detailed result -->
                    <div id="detailedResults"></div>
                    <!-- Chart description -->
                    <!--<p class="texts"><fmt:message key="chart_description"/></p>-->
                </div>
            </div>

            <!--  Footer-->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </div> <!-- Content ending -->
	</body>
</html>
