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

            //Used to internationalize javascript code
            var confirmText =  "<fmt:message key="confirm_text"/>";

            //Using to show the loading panel
            YAHOO.namespace("example.container");
            var loadingText = "<fmt:message key="loading"/>";
            var loadingImage = "<img src=\"${pageContext.request.contextPath}/themes/default/images/ajax-loader.gif\" ></img>";
            //Init the loading javascript
            function initLoading(){
                initLoadingPanel();
            }
            //Show loadinf panel
            function verifyAction(url){
                var answer = confirm(confirmText)
                if (answer){
                    document.location = url;
                    YAHOO.example.container.wait.show();
                }
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
                    <h2><fmt:message key="config_title"/></h2>

                    <p class="link_tools"><fmt:message key="general_config"/></p>
                    <a href="changepass.htm" class="link"><fmt:message key="change_pass"/></a><br>
                    <a href="geoserver.htm" class="link"><fmt:message key="map_server_link"/></a><br>
                    <a href="connlayer.htm" class="link"><fmt:message key="postgis_config"/></a><br>

                    <p class="link_tools"><fmt:message key="data_access"/></p>
                    <a href="conndwc.htm" class="link"><fmt:message key="dwc_config"/></a><br>
                    <!--<a href="connplic.htm" class="link"><fmt:message key="plic_config"/></a><br>-->
                    <a href="connindi.htm" class="link"><fmt:message key="attri_config"/></a><br>
                    <a href="conntindi.htm" class="link"><fmt:message key="tattri_config"/></a><br>
                    <a href="conncountry.htm" class="link"><fmt:message key="country_config"/></a><br>
                    <a href="conncountryti.htm" class="link"><fmt:message key="tac_config"/></a><br>

                    <p class="link_tools"><fmt:message key="data_import"/></p>
                    <a href="javascript:verifyAction('copydwc.htm')" class="link"><fmt:message key="import_dwc_data"/></a><br>
                    <a href="javascript:verifyAction('copyindi.htm')" class="link"><fmt:message key="import_indi_data"/></a><br>
                    <a href="javascript:verifyAction('copytaxonindi.htm')" class="link"><fmt:message key="import_indit_data"/></a><br>
                    <a href="javascript:verifyAction('copycountry.htm')" class="link"><fmt:message key="import_country"/></a><br>
                    <a href="javascript:verifyAction('copycountryti.htm')" class="link"><fmt:message key="import_tac"/></a><br>

                    <p class="link_tools"><fmt:message key="data_index"/></p>
                    <a href="javascript:verifyAction('indextnames.htm')" class="link"><fmt:message key="index_tnames"/></a><br>
                    <a href="javascript:verifyAction('indexinfo.htm')" class="link"><fmt:message key="index_info"/></a><br>

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

