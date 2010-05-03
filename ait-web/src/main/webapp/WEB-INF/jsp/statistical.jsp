<%-- 
    Document   : statistical
    Created on : 03/03/2010, 04:30:23 PM
    Author     : esmata
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/analysisTags.jsp" %>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='styleSheet'/>"/>
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='autocomplete'/>"/>
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='tree'/>"/>
        <link rel="stylesheet" type="text/css"
              href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='container'/>"/>

        <title><fmt:message key="title"/></title>

        <script src="http://216.75.53.105:80/geoserver/openlayers/OpenLayers.js" type="text/javascript"></script>
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>
        <script src='http://dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=6.1'></script>
        <script defer="defer" type="text/javascript">

            //Use a proxy for GeoServer requesting
            OpenLayers.ProxyHost = "cgi-bin/proxy.cgi/?url=";

            /*              Global variables                */
            var map;
            //Available poligons [[id,name],...] Depends on layersList
            var polygonsList;
            //Current selected polygon
            var currentPolygonId; //(FID)
            var currentPolygonName; //(Name)
            //Available layers [[id,name],...]
            var layersList;
            //Current selected layer
            var layerId; //(FID)
            var layerName; //(Name)
            //Current layer index (numeric)
            var layerIndex;
            //Indicators tree
            var tree;
            var currentIconMode;
            //Getting a reference to the root node of indicators tree
            var rootNode;
            //Current selected indicators tree node
            var selectedNodeId;
            var selectedNodeName;
            var isLeaf;
            //Layer to show specimens points
            var vectorLayer;
            //To create a new atribute for each specimen point
            var attributes;
            //Internationalization variable
            var chartTitle;

            //Pink tile avoidance
            OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
            //Make OL compute scale according to WMS spec
            OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;
            //Using to show the loading panel
            YAHOO.namespace("example.container");

            /*
             * Initialize the map, the indicators tree and sets the
             * internationalization to the javascript code
             */
            function init(){
                //Hide parameters panels
                hide('queryPanel1');
                hide('queryPanel2');
                hide('queryPanel3');
                //The vector layer to show the specimen points
                vectorLayer = new OpenLayers.Layer.Vector('Specimens');
                //Load messages content
                internationalization();
                //initialize map functionality
                initMap();
                //Create a drop down to specified the current layer
                createDDLayers();
                //Init indicators tree
                initIndicators();
                //Init the loading javascript
                initLoadingPanel();
            }

            //Passing parameters to controller class throw path property
            function doSubmit(mapParams,taxonParams,treeParams){

                //Getting the form reference
                var form = document.getElementById('parameters');

                //Getting a reference for chart data
                var xData = document.getElementById('xData');
                var yData = document.getElementById('yData');
                var xTitle = document.getElementById('xTitle');
                var yTitle = document.getElementById('yTitle');
                var xDataToShow = document.getElementById('xDataToShow');
                var yDataToShow = document.getElementById('yDataToShow');
                var isGeo = document.getElementById('isGeo');
                var ChartTitle = document.getElementById('ChartTitle');
                var aux = prompt(chartTitle,'');
                ChartTitle.value = aux;

                //Variables to store the search criteria
                var selectedLayers = "",layersToShow = "";
                var selectedTaxa = "", taxaToShow = "";
                var selectedIndicators = "", indiToShow = "";

                //Loop over geographical criteria
                for (var i =0; i <mapParams.childNodes.length; i++){
                    selectedLayers += mapParams.childNodes[i].id+"|";
                    if(document.all){
                        layersToShow += mapParams.childNodes[i].innerText+"|";
                    }
                    else{
                        layersToShow += mapParams.childNodes[i].textContent+"|";
                    }
                }
                //Loop over taxonomic criteria
                for (var j =0; j <taxonParams.childNodes.length; j++){
                    if(document.all){
                        selectedTaxa += taxonParams.childNodes[j].innerText+"|";
                        taxaToShow += taxonParams.childNodes[j].innerText+"|";
                    }
                    else{
                        selectedTaxa += taxonParams.childNodes[j].textContent+"|";
                        taxaToShow += taxonParams.childNodes[j].textContent+"|";
                    }
                }
                //Loop over indicators criteria
                for (var k =0; k <treeParams.childNodes.length; k++){
                    selectedIndicators += treeParams.childNodes[k].id+"|";
                    if(document.all){
                        indiToShow += treeParams.childNodes[k].innerText+"|";
                    }
                    else{
                        indiToShow += treeParams.childNodes[k].textContent+"|";
                    }
                }

                //Setting the data passed throw submition
                var x = document.getElementById('xAxis').selectedIndex;
                var y = document.getElementById('yAxis').selectedIndex;
                switch(x){
                    case 1: //taxonomical
                        xData.value = selectedTaxa;
                        xDataToShow.value = taxaToShow;
                        xTitle.value = "<fmt:message key="taxonomical_criteria_title"/>";
                        break;
                    case 2: //geographical
                        xData.value = selectedLayers;
                        xDataToShow.value = layersToShow;
                        xTitle.value = "<fmt:message key="geografical_criteria_title"/>";
                        isGeo.value = "x";
                        break;
                    case 3: //indicators
                        xData.value = selectedIndicators;
                        xDataToShow.value = indiToShow;
                        xTitle.value = "<fmt:message key="indicators_criteria_title"/>";
                        break;
                }
                switch(y){
                    case 1: //taxonomical
                        yData.value = selectedTaxa;
                        yDataToShow.value = taxaToShow;
                        yTitle.value = "<fmt:message key="species_number"/>";
                        break;
                    case 2: //geographical
                        yData.value = selectedLayers;
                        yDataToShow.value = layersToShow;
                        yTitle.value = "<fmt:message key="species_number"/>";
                        isGeo.value = "y";
                        break;
                    case 3: //indicators
                        yData.value = selectedIndicators;
                        yDataToShow.value = indiToShow;
                        yTitle.value = "<fmt:message key="species_number"/>";
                        break;
                }                

                //Submiting the form
                form.submit();
            }

            /*
             * Validates if everything is correct with the chart parameters
             */
            function validateParameters(){
                //Show loading image
                YAHOO.example.container.wait.show();
                //Getting the parameter lists
                var mapParams = document.getElementById('mapParameters');
                var taxonParams = document.getElementById('taxParameters');
                var treeParams = document.getElementById('treeParameters');

                //Getting the chart type,x axis, y axis selected values
                var indexType = document.getElementById('chartType').selectedIndex;
                var indexX = document.getElementById('xAxis').selectedIndex;
                var indexY = document.getElementById('yAxis').selectedIndex;

                //Validate if the user has selected the chart type
                if(indexType==0){
                    alert("<fmt:message key="select_chart_type"/>");
                    YAHOO.example.container.wait.hide();
                    return;
                }

                //Validate if the user already select x and y axis
                if(indexX==0||indexY==0){
                    alert("<fmt:message key="indicate_axis"/>");
                    YAHOO.example.container.wait.hide();
                    return;
                }

                //Validate if the user select the same option for x and y axis
                if(indexX==indexY){
                    alert("<fmt:message key="same_axis"/>");
                    YAHOO.example.container.wait.hide();
                    return;
                }

                //If everything is ok
                doSubmit(mapParams,taxonParams,treeParams);
            }

            /*
             * Show or hide the panels dependding on x and y axis
             * 1 means taxonomical criteria
             * 2 means geographic criteria
             * 3 means indicators criteria
             * See ChartCriteria.java class
             */
            function changeAxis(){
                var x = document.getElementById('xAxis').selectedIndex;
                var y = document.getElementById('yAxis').selectedIndex;
                hide('queryPanel1');
                hide('queryPanel2');
                hide('queryPanel3');
                switch(x){
                    case 1: //taxonomical
                        show('queryPanel2');
                        break;
                    case 2: //geographical
                        show('queryPanel1');
                        break;
                    case 3: //indicators
                        show('queryPanel3');
                        break;
                }
                switch(y){
                    case 1: //taxonomical
                        show('queryPanel2');
                        break;
                    case 2: //geographical
                        show('queryPanel1');
                        break;
                    case 3: //indicators
                        show('queryPanel3');
                        break;
                }
            }

            //Set visible true
            function show(element){
                document.getElementById(element).style.display="";
            }
            //Set visible false
            function hide(element){
                document.getElementById(element).style.display="none";
            }

            /*
             * Sets the HTML provided into the nodelist element from
             * the maps response
             */
            function setHTML(response){
                //Validate if is posible to select geoespatial criteria
                var x = document.getElementById('xAxis').selectedIndex;
                var y = document.getElementById('yAxis').selectedIndex;
                if(x!=2&&y!=2){ //2 means geoespatial criteria
                    document.getElementById('info').innerHTML = "";
                    return;
                }

                //Obtain the selected polygon(s), value set on currentPolygonId var
                parseHTML(response.responseText);
                //Verify if the list is null
                if(polygonsList==null){
                    return;
                }
                //Verify if the polygon is unique
                if(polygonsList.length!=1){
                    alert(selectOnePolygonE);
                    return;
                }
                //Add the polygon to the geografical criteria list
                currentPolygonId = polygonsList[0][0];
                currentPolygonName = polygonsList[0][1];
                addLayerParam(currentPolygonId,layerId,currentPolygonName,layerName);
                //Clean the Loading status
                document.getElementById('info').innerHTML = "";
            }

        </script>

        <!-- Internacionalization-->
        <script type="text/javascript">
            function internationalization(){
                layerText =  "<fmt:message key="layers"/>";
                loadingText = "<fmt:message key="loading"/>";
                selectCriteriaE = "<fmt:message key="select_criteria_error"/>";
                selectOnePolygonE = "<fmt:message key="select_one_polygon"/>";
                invalidPolygonE = "<fmt:message key="not_valid_polygon"/>";
                selectLayerPolyE = "<fmt:message key="select_layer_and_poly"/>";
                alreadyAddedE = "<fmt:message key="already_criteria"/>";
                specifyTaxonE = "<fmt:message key="taxon_name_error"/>";
                selectIndicatorFirstE = "<fmt:message key="first_select_indicator"/>";
                treeLeafE = "<fmt:message key="indicator_leaf"/>";
                loadingImage = "<img src=\"${pageContext.request.contextPath}/themes/default/images/ajax-loader.gif\" ></img>";
                chartTitle = "<fmt:message key="chart_title"/>";
            };
        </script>

    </head>
    <body onload="init()"  class=" yui-skin-sam">

        <!-- TaxonFilter Auto Complete-->
        <script type="text/javascript">
            var taxonAutoCompleteUrls = new Array(${ fn:length(taxonFilters)});
            <c:forEach items="${taxonFilters}" var="taxonFilter" varStatus="filterStatus" begin="0">
              <c:if test="${not empty taxonFilter.autoCompleteUrl}">
                  taxonAutoCompleteUrls[${taxonFilter.id}] = "${pageContext.request.contextPath}/${taxonFilter.autoCompleteUrl}";
              </c:if>
            </c:forEach>
        </script>

        <!-- Header -->
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>

        <!-- Content -->
        <form:form method="POST" commandName="parameters" cssStyle="margin:0">

            <!-- Values to get data for building the chart -->
            <form:hidden path="xdata" id="xData"/>
            <form:hidden path="ydata" id="yData"/>
            <form:hidden path="xtitle" id="xTitle"/>
            <form:hidden path="ytitle" id="yTitle"/>
            <form:hidden path="xdatatoshow" id="xDataToShow"/>
            <form:hidden path="ydatatoshow" id="yDataToShow"/>
            <form:hidden path="isgeo" id="isGeo"/>
            <form:hidden path="title" id="ChartTitle"/>

            <div id="contenido">

                <h2><fmt:message key="statistic_analysis"/></h2>

                <div id="querysPanel">

                    <!-- Chart type Panel -->
                    <div id="settings" class="queryPanel">
                        <div id="chartS1" class="chartSetting" >
                            <p style="margin:1px"><a> <fmt:message key="chart_type"/>: </a></p>
                            <form:select id="chartType" path="type" cssClass="componentSize">
                                <!-- See ChartType.java enum to verify de options values -->
                                <form:option value="select"><fmt:message key="drop_down_null_option"/></form:option>
                                <form:option value="bar"><fmt:message key="barChart"/></form:option>
                                <form:option value="line"><fmt:message key="lineChart"/></form:option>
                            </form:select>
                        </div>
                        <div id="chartS2" class="chartSetting">
                            <p style="margin:1px"><a> <fmt:message key="x_axis"/>: </a></p>
                            <form:select id="xAxis" path="xaxis" cssClass="componentSize" onchange="changeAxis()">
                                <!-- See ChartCriteria.java enum to verify the options values -->
                                <form:option value="0"><fmt:message key="drop_down_null_option"/></form:option>
                                <form:option value="1"><fmt:message key="taxonomical_criteria_title"/></form:option>
                                <form:option value="2"><fmt:message key="geografical_criteria_title"/></form:option>
                                <form:option value="3"><fmt:message key="indicators_criteria_title"/></form:option>
                            </form:select>
                        </div>
                        <div id="chartS3" class="chartSetting">
                            <p style="margin:1px"><a> <fmt:message key="y_axis"/>: </a></p>
                            <form:select id="yAxis" path="yaxis" cssClass="componentSize" onchange="changeAxis()">
                                <!-- See ChartCriteria.java enum to verify the options values -->
                                <form:option value="0"><fmt:message key="drop_down_null_option"/></form:option>
                                <form:option value="1"><fmt:message key="taxonomical_criteria_title"/></form:option>
                                <form:option value="2"><fmt:message key="geografical_criteria_title"/></form:option>
                                <form:option value="3"><fmt:message key="indicators_criteria_title"/></form:option>
                            </form:select>
                        </div>
                    </div>

                    <!-- GIS Panel -->
                    <div id="queryPanel1" class="queryPanel">
                        <p style="font-weight:bold;font-style:italic;margin:2px;text-align:center;">
                        <fmt:message key="geografical_criteria_title"/></p>
                        <div id="currentLayer"></div>
                        <div id="info"></div>
                        <span id="mapParameters" style="font-size:10px"></span>
                    </div>

                    <!-- Taxonomy Panel -->
                    <div id="queryPanel2" class="queryPanel">
                        <p style="font-weight:bold;font-style:italic;margin:2px;text-align:center;">
                        <fmt:message key="taxonomical_criteria_title"/></p>
                        <p style="margin:1px"><a> <fmt:message key="taxonomy_level"/>: </a></p>
                        <select name="taxonType" id="taxonTypeId" class="componentSize" tabindex="12" onchange="javascript:changeTaxonInput();" onKeyUp="javascript:changeTaxonInput();">
                            <c:forEach items="${taxonFilters}" var="taxonFilter">
                                <option value="<c:out value="${taxonFilter.id}"/>"<c:if test="${taxonFilter.id == taxonType}"> selected="selected"</c:if>>
                                    <fmt:message key="${taxonFilter.displayName}"/>
                                </option>
                            </c:forEach>
                        </select>
                        <p style="margin:1px"><a> <fmt:message key="taxon_name"/>: </a></p>
                        <span id="newTaxonValue">
                            <input type="text" id="taxonId" tabindex="13" name="taxonValue" value="<c:out value="${taxonValue}"/>"/>
                            <div id="taxonContainer"></div>
                        </span>
                        <input type="button" class="my_Button" id="addToListButtonTax" value="Agregar criterio" onclick="addTaxonParam()" />
                        <span id="taxParameters" style="font-size:10px"></span>
                        <script type="text/javascript">
                            changeTaxonInput();
                        </script>
                    </div>

                    <!-- Indicator Panel -->
                    <div id="queryPanel3" class="queryPanel">
                        <p style="font-weight:bold;font-style:italic;margin:2px;text-align:center;">
                        <fmt:message key="indicators_criteria_title"/></p>
                        <div id="treeDiv"></div>
                        <input type="button" class="my_Button" id="addToListButtonIndi" value="Agregar criterio" onclick="addIndicatorParam()" />
                        <span id="treeParameters" style="font-size:10px"></span>
                    </div>

                    <!-- Query Button -->
                    <input type="button" class="main_Button" id="makeQueryButton" value="<fmt:message key="generate_chart"/>"
                           onclick="validateParameters()" />
                </div>

                <!-- Map Panel -->
                <div id="mapPanel">
                    <div id="map"> </div>
                    <div id="wrapper">
                        <div id="location">location</div>
                        <div id="scale"></div>
                    </div>
                </div>

                <!-- Results Panel -->
                <div id="resultsPanel"></div>

            </div>
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </form:form>
        <!-- Content ends -->
    </body>
</html>

