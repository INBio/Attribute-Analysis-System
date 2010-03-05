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

        <script type="text/javascript">
		    function doSubmit(taxon_id, fieldTrip, taxonName, site){
			var form = document.getElementById('objectInfo');

			var fieldTripId = document.getElementById('fieldTripId');
			var taxonId = document.getElementById('taxonId');
			var indicatorId = document.getElementById('indicatorId');
			var siteName = document.getElementById('siteName');
			var taxonScientificName = document.getElementById('taxonScientificName');

			fieldTripId.value = fieldTrip;
			taxonId.value = taxon_id;
			indicatorId.value = '<c:out value="${indicatorValue}"/>';

			taxonScientificName.value = taxonName;
			siteName.value = site;

			form.submit();
		    }
        </script>
        
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>
        <!-- Content -->
        <form name = "form1" method = "get">
            <div id="contenido">
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
                    <input type="button" class="main_Button" id="makeQueryButton" value="<fmt:message key="consult"/>" />

                </div>

                <div id="mapPanel">
                </div>

                <div id="resultsPanel">
                </div>

            </div>
        </form>
        <!-- Content ends -->
    </body>
</html>

