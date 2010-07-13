<%-- 
    Document   : config
    Created on : 15/01/2010, 01:32:22 PM
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
            var loadingText = "<fmt:message key="loading"/>";
            var loadingImage = "<img src=\"${pageContext.request.contextPath}/themes/default/images/ajax-loader.gif\" ></img>";
            //Init the loading javascript
            function initLoading(){
                initLoadingPanel();
            }
            //Show loadinf panel
            function showLoading(){       
                YAHOO.example.container.wait.show();
            }
        </script>
    </head>
    <body onload="initLoading()" class=" yui-skin-sam">
        <!-- Content -->
        <form name = "config" method = "get">
            <div id="contenido">
                <!-- Header -->
                <jsp:include page="/WEB-INF/jsp/header.jsp"/>

                <div id="content">
                    <h2><fmt:message key="config_title"/></h2><br>
                    <a href="conndwc.htm" class="link"><fmt:message key="dwc_config"/></a><br>
                    <a href="connplic.htm" class="link"><fmt:message key="plic_config"/></a><br>
                    <a href="connindi.htm" class="link"><fmt:message key="attri_config"/></a><br>
                    <a href="conntindi.htm" class="link"><fmt:message key="tattri_config"/></a><br>
                    <a href="connlayer.htm" class="link"><fmt:message key="postgis_config"/></a><br>
                    <br>
                    <a href="copydwc.htm" class="link" onclick="showLoading()"><fmt:message key="import_dwc_data"/></a>
                </div>

                <!-- Footer -->
                <br><br>
                <div id="footer">
                    <fmt:message key="footer_text"/>
                </div>
            </div>
            <!-- Content ending -->
        </form>        
    </body>
</html>

