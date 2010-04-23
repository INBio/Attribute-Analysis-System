<%-- 
    Document   : species
    Created on : 15/01/2010, 11:24:22 AM
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
            //Base layer
            var virtualEarthLayer  = new OpenLayers.Layer.VirtualEarth('Virtual Earth');

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

                // Instantiate a Panel from markup
                /*YAHOO.example.container.queryPanel1 = new YAHOO.widget.Panel("queryPanel1", { visible:true, draggable:true, close:false } );
                YAHOO.example.container.queryPanel1.render();

                YAHOO.example.container.queryPanel2 = new YAHOO.widget.Panel("queryPanel2", { visible:true, draggable:true, close:false } );
                YAHOO.example.container.queryPanel2.render();

                YAHOO.example.container.queryPanel3 = new YAHOO.widget.Panel("queryPanel3", { visible:true, draggable:true, close:false } );
                YAHOO.example.container.queryPanel3.render();
                             http://developer.yahoo.com/yui/examples/container/panel.html*/
            };
            
            /*
             * This function calls another function that is on charge to make the
             * final query and show the result to the user
             */
            function makeQuery(){                
                //Show loading
                YAHOO.example.container.wait.show();
                //Getting the parameter lists
                var layerslist = document.getElementById('mapParameters');
                var taxonlist = document.getElementById('taxParameters');
                var treelist = document.getElementById('treeParameters');
                //Arrays with parameters data (to show on the results table)
                var layersShow = new Array();
                var taxonsShow = new Array();
                var treeShow = new Array();
                //Validate that exist at least one search criteria
                if(layerslist.childNodes.length==0&&taxonlist.childNodes.length==0&&treelist.childNodes.length==0){
                    alert(selectCriteriaE);
                    document.getElementById('resultsPanel').innerHTML = "";
                    YAHOO.example.container.wait.hide();
                    return;
                }
                //Loop over geographical criteria
                var selectedLayers = "";
                for (var i =0; i <layerslist.childNodes.length; i++){
                    selectedLayers += layerslist.childNodes[i].id+"|";
                    if(document.all){
                        layersShow.push(layerslist.childNodes[i].innerText);
                    }
                    else{
                        layersShow.push(layerslist.childNodes[i].textContent);
                    }
                }
                //Loop over taxonomic criteria
                var selectedTaxa = "";
                for (var j =0; j <taxonlist.childNodes.length; j++){
                    if(document.all){
                        selectedTaxa += taxonlist.childNodes[j].innerText+"|";
                        taxonsShow.push(taxonlist.childNodes[j].innerText);
                    }
                    else{
                        selectedTaxa += taxonlist.childNodes[j].textContent+"|";
                        taxonsShow.push(taxonlist.childNodes[j].textContent);
                    }
                }
                //Loop over indicators criteria
                var selectedIndicators = "";
                for (var k =0; k <treelist.childNodes.length; k++){
                    selectedIndicators += treelist.childNodes[k].id+"|";
                    if(document.all){
                        treeShow.push(treelist.childNodes[k].innerText);
                    }
                    else{
                        treeShow.push(treelist.childNodes[k].textContent);
                    }
                }
                //Setting to hidden fields the query values. Those info are going to
                //be used to show specimens point into the map (not in use yet)
                document.getElementById('hiddenLayers').value = selectedLayers;
                document.getElementById('hiddenTaxa').value = selectedTaxa;
                document.getElementById('hiddenIndicators').value = selectedIndicators;

                //Clean criteria lists
                document.getElementById('mapParameters').innerHTML = "";
                document.getElementById('taxParameters').innerHTML = "";
                document.getElementById('treeParameters').innerHTML = "";
                tree.collapseAll();
                
                //Call the function that returns the result (xml) asincronically
                executeFinalQuery(selectedLayers,selectedTaxa,selectedIndicators,
                layersShow,taxonsShow,treeShow);

                //Clean the specimen points layer
                replaceVectorLayer();
                //Draw the spevectorLayer.refresh();cimen points into the map
                showSpecimenPoints(selectedLayers,selectedTaxa,selectedIndicators);
            };

            /*
             * This function adds a new point to the specimens Layer
             */
            function addPoint(x, y, attribute) {
                var feature = new OpenLayers.Feature.Vector(
                new OpenLayers.Geometry.Point(x, y), attribute);
                vectorLayer.addFeatures(feature);
            };

            /*
             * Creates a new atributes array for each speciemns point
             */
            function createAttrib(scientificName) {
                attrib = {
                    sScientificName: scientificName
                }
                return attrib;
            };

            /*
             * Deletes the current specimen points
             */
            function replaceVectorLayer(){
                vectorLayer.destroy();
                vectorLayer = new OpenLayers.Layer.Vector('Specimens');
                vectorLayer.setVisibility(true);
                map.addLayer(vectorLayer);
            }

            /*
             * Sets the HTML provided into the nodelist element from
             * the maps response
             */
            function setHTML(response){
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
            };
        </script>

    </head>
    <body onload="init()" class=" yui-skin-sam">

        <!-- TaxonFilter Auto Complete-->
        <script type="text/javascript">
            var taxonAutoCompleteUrls = new Array(${ fn:length(model.taxonFilters)});
            <c:forEach items="${model.taxonFilters}" var="taxonFilter" varStatus="filterStatus" begin="0">
              <c:if test="${not empty taxonFilter.autoCompleteUrl}">
                taxonAutoCompleteUrls[${taxonFilter.id}] = "${pageContext.request.contextPath}/${taxonFilter.autoCompleteUrl}";
              </c:if>
            </c:forEach>
        </script>

        <!-- Header -->
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>
        <!-- Content -->
        <form id="myform" name = "species" method = "get" style="margin:0px">
            <div id="contenido">
                <h2><fmt:message key="analysis_title"/></h2>

                <div id="querysPanel">
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
                            <c:forEach items="${model.taxonFilters}" var="taxonFilter">
                                <option value="<c:out value="${taxonFilter.id}"/>"<c:if test="${taxonFilter.id == taxonType}"> selected="selected"</c:if>>
                                    <fmt:message key="${taxonFilter.displayName}"/>
                                </option>
                            </c:forEach>
                        </select>
                        <p style="margin:1px"><a> <fmt:message key="taxon_name"/>: </a></p>
                        <span id="newTaxonValue">
                            <input id="taxonId" tabindex="13" class="componentSize" type="text" name="taxonValue" value="<c:out value="${taxonValue}"/>"/>
                            <div id="taxonContainer"></div>
                        </span>
                        <input type="button" class="my_Button" id="addToListButtonTax" value="<fmt:message key="add_criteria"/>" onclick="addTaxonParam()" />
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
                        <input type="button" class="my_Button" id="addToListButtonIndi" value="<fmt:message key="add_criteria"/>" onclick="addIndicatorParam()" />
                         <span id="treeParameters" style="font-size:10px"></span>
                    </div>

                    <!-- Query Button -->
                    <input type="button" class="main_Button" id="makeQueryButton" value="<fmt:message key="consult"/>"
                    onclick="makeQuery()" />

                </div>

                <div id="mapPanel">
                    <!-- Map Panel -->
                    <div id="map"> </div>
                    <div id="wrapper">
                        <div id="location">location</div>
                        <div id="scale"></div>
                    </div>
                </div>

                <div id="resultsPanel" style="padding-bottom:15px"></div>

                <!-- To show specimens points into the map -->
                <input type="hidden" id="hiddenLayers" value="">
                <input type="hidden" id="hiddenTaxa" value="">
                <input type="hidden" id="hiddenIndicators" value="">

            </div>
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </form>
        <!-- Content ending -->
    </body>
</html>

