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
        <link rel="stylesheet" type="text/css"
        href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='menu'/>"/>
        
        <title><fmt:message key="title"/></title>

        <link rel="stylesheet" type="text/css" href="http://openlayers.org/theme/default/style.css"/>
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>

        <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAGtIHQJm1-pS3ci26k9D7hRRbo2pJpNQIEpt8-nIdM7Qcnrb6GBRgf7oLano6tXPoM6kwdMXfL49wvQ" type="text/javascript"></script>
        <!--<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAGtIHQJm1-pS3ci26k9D7hRRURa6X8semXTOqalZdyJcp_MFd9RS_0fj31egxhzrJ1gql_bQ3Rcc7Qw" type="text/javascript"></script>-->

        <script defer="defer" type="text/javascript">

            //Use a proxy for GeoServer requesting
            OpenLayers.ProxyHost = "cgi-bin/proxy.cgi/?url=";

            /*              Global variables                */
            var map;
            //Available poligons [[id,name],...] Depends on layersList
            var polygonsList;
            //Current selected polygon
            var currentPolygonId,currentPolygonLimitId; //(FID)
            var currentPolygonName,currentPolygonLimitName; //(Name)
            //Available layers [[id,name],...]
            var layersList = new Array();
            //Current selected layer
            var layerId,layerLimitId; //(FID)
            var layerName,layerLimitName; //(Name)
            //Current layer index (numeric)
            var layerIndex,layerLimitIndex;
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
            //Control to manage pop ups on the map
            var selectControl;
            //Current selected especimen point into the map
            var selectedFeature;
            //To create a new atribute for each specimen point
            var attributes;
            //Array that contains the id's of each <div> from multiple div results
            var divIds = new Array(),buttonIds = new Array(),
            ids = new Array(),types = new Array();
            //Var to know if the selected polygon is a limit polygon (% functionality)
            var isLimitPolygon = false;
            //Geoserver ip address
            var geoIpAddress = '${model.geoip}';

            //Internacionalization of the report texts
            var searchResults,geographical,taxonomic,indicators,speciesMatches,
            seeOnMap,seeDetail,searchCriteria,speciesList,newSearch,criteriaText,
            speciesText,hideMap,hideDetail,catalog,latitude,longitute,scientificName,
            layerMatches,indicatorMatches,resultDetails,criteriaWithoutResults,occurrences,
            resultsGeo,resultsIndi,institution,percentText1,percentText2,percentText2S;

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
                //Load messages content
                internationalization();

                //Sets the layersList values
                <c:forEach var="var" items="${model.layers}" begin="0">
                layersList.push(new Array('${model.geows}${var}','${var}')); //(id,name)
                </c:forEach>
                //Sets the layerId,layerIndex and layerName values
                if(layersList.length > 0){
                    layerId = layersList[0][0];
                    layerLimitId = layersList[0][0];
                    layerName = layersList[0][1];
                    layerLimitName = layersList[0][1];
                }
                layerIndex = 1; //Used as id on map layer list, not in layersList
                layerLimitIndex = 1;
                
                //initialize map functionality
                initMap('map');
                //Create a drop down to specified the current layer
                createDDLayers();
                //Init indicators tree
                initIndicators();
                //Init the loading javascript                
                initLoadingPanel();
                //Init the help panel
                initHelpPanel();
            };
            
            /*
             * This function calls another function that is on charge to make the
             * final query and show the result to the user
             */
            function makeQuery(){             
                //Show loading
                YAHOO.example.container.wait.show();
                //Clear vector layer
                replaceVectorLayer();
                //Getting the parameter lists
                var layerslist = document.getElementById('mapParameters');
                var layersLimitlist = document.getElementById('mapLimitParameters');
                var taxonlist = document.getElementById('taxParameters');
                var treelist = document.getElementById('treeParameters');
                //Arrays with parameters data (to show on the results table)
                var layersShow = new Array(),taxonsShow = new Array(),treeShow = new Array(),
                layersLimitShow = new Array();
                //String with parameters data (to store in hidden field)
                var layersAsText = '',indiAsText = '',layersLimitAsText = '';
                //Validate that exist at least one search criteria
                if(layerslist.childNodes.length==0&&taxonlist.childNodes.length==0&&treelist.childNodes.length==0){
                    alert(selectCriteriaE);
                    simpleCleannig();
                    YAHOO.example.container.wait.hide();
                    return;
                }
                //Loop over geographical criteria (Selected polygons)
                var selectedLayers = "";
                for (var i =0; i <layerslist.childNodes.length; i++){
                    selectedLayers += layerslist.childNodes[i].id+"|";
                    if(document.all){
                        layersShow.push(layerslist.childNodes[i].innerText);
                        layersAsText += layerslist.childNodes[i].innerText+'|';
                    }
                    else{
                        layersShow.push(layerslist.childNodes[i].textContent);
                        layersAsText += layerslist.childNodes[i].textContent+'|';
                    }
                }
                //Loop over geographical criteria (Limit polygons)
                var selectedLimit = "";
                for (var i =0; i <layersLimitlist.childNodes.length; i++){
                    selectedLimit += layersLimitlist.childNodes[i].id+"|";
                    if(document.all){
                        layersLimitShow.push(layersLimitlist.childNodes[i].innerText);
                        layersLimitAsText += layersLimitlist.childNodes[i].innerText+'|';
                    }
                    else{
                        layersLimitShow.push(layersLimitlist.childNodes[i].textContent);
                        layersLimitAsText += layersLimitlist.childNodes[i].textContent+'|';
                    }
                }
                //Loop over taxonomic criteria
                var selectedTaxa = "";
                for (var j =0; j <taxonlist.childNodes.length; j++){
                    if(document.all){
                        selectedTaxa += taxonlist.childNodes[j].id+"|";
                        taxonsShow.push(taxonlist.childNodes[j].innerText);
                    }
                    else{
                        selectedTaxa += taxonlist.childNodes[j].id+"|";
                        taxonsShow.push(taxonlist.childNodes[j].textContent);
                    }
                }
                //Loop over indicators criteria
                var selectedIndicators = "";
                for (var k =0; k <treelist.childNodes.length; k++){
                    selectedIndicators += treelist.childNodes[k].id+"|";
                    if(document.all){
                        treeShow.push(treelist.childNodes[k].innerText);
                        indiAsText += treelist.childNodes[k].innerText+'|';
                    }
                    else{
                        treeShow.push(treelist.childNodes[k].textContent);
                        indiAsText += treelist.childNodes[k].textContent+'|';
                    }
                }
                //Setting to hidden fields the query values.
                setHiddenValues(selectedLayers,selectedTaxa,selectedIndicators,
                layersAsText,indiAsText,layersLimitAsText,selectedLimit);

                //Clean entry criteria lists
                cleanAfterRequest();

                //To unregister the function to introduce map info to the query criteria
                map.events.unregister('click', map, addMapListener);
                
                //Call the function that returns the result (xml) asincronically
                executeFinalQuery(selectedLayers,selectedTaxa,selectedIndicators,selectedLimit,
                layersShow,taxonsShow,treeShow,layersLimitShow);
            };

            function simpleCleannig(){
                document.getElementById('resultsPanel').innerHTML = "";
                document.getElementById('detailedResults').innerHTML = "";
                document.getElementById("detailedResults").className = "";
            }

            /*
             * Clean the page after request a new report
             */
            function cleanAfterRequest(){
                document.getElementById('entryCriteria').innerHTML = "";
                divIds = new Array();
                buttonIds = new Array();
                ids = new Array();
                types = new Array();
            }

            /*
             * Setting to hidden fields the query values. Those info are going to
             * be used to show specimens point into the map (not in use yet)
             */
            function setHiddenValues(selectedLayers,selectedTaxa,selectedIndicators,layersAsText,indiAsText,
            layersLimitAsText,selectedLimit){
                document.getElementById('hiddenLayers').value = selectedLayers;                
                document.getElementById('hiddenTaxa').value = selectedTaxa;
                document.getElementById('hiddenIndicators').value = selectedIndicators;
                document.getElementById('hiddenLimitLayers').value = selectedLimit;

                document.getElementById('hidLayersToShow').value = layersAsText;
                document.getElementById('hiddenIndiToShow').value = indiAsText;
                document.getElementById('hiddenLimitToShow').value = layersLimitAsText;
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
                // If adding limit polygons
                if(isLimitPolygon){
                    //Add the polygon to the geografical criteria list
                    currentPolygonLimitId = polygonsList[0][0];
                    currentPolygonLimitName = polygonsList[0][1];
                    addLayerLimitParam(currentPolygonLimitId,layerLimitId,currentPolygonLimitName);
                    //Clean the Loading status
                    document.getElementById('infoLimit').innerHTML = "";
                }
                else{ //If adding normal polygons
                    //Add the polygon to the geografical criteria list
                    currentPolygonId = polygonsList[0][0];
                    currentPolygonName = polygonsList[0][1];
                    addLayerParam(currentPolygonId,layerId,currentPolygonName);
                    //Clean the Loading status
                    document.getElementById('info').innerHTML = "";
                }
            }

            //Go to anchor
            function callAnchor(anchor){
            document.location.href = anchor;
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
                searchResults = "<fmt:message key="search_results"/>";
                geographical = "<fmt:message key="geographical"/>";
                taxonomic = "<fmt:message key="taxonomic"/>";
                indicators = "<fmt:message key="indicators"/>";
                speciesMatches = "<fmt:message key="species_matches"/>";
                seeOnMap = "<fmt:message key="see_on_map"/>";
                seeDetail = "<fmt:message key="see_detail"/>";
                searchCriteria = "<fmt:message key="search_criteria"/>";
                speciesList = "<fmt:message key="species_list"/>";
                newSearch = "<fmt:message key="new_search"/>";
                criteriaText = "<fmt:message key="criteria"/>";
                speciesText = "<fmt:message key="species"/>";
                hideMap = "<fmt:message key="hide_map"/>";
                hideDetail = "<fmt:message key="hide_detail"/>";
                catalog = "<fmt:message key="catalog_number"/>";
                latitude = "<fmt:message key="latitude"/>";
                longitute = "<fmt:message key="longitude"/>";
                scientificName = "<fmt:message key="scientificname"/>";
                layerMatches = "<fmt:message key="layer_matches"/>";
                indicatorMatches = "<fmt:message key="indicator_matches"/>";
                resultDetails = "<fmt:message key="result_details"/>";
                criteriaWithoutResults = "<fmt:message key="criteria_without_results"/>";
                occurrences = "<fmt:message key="occurrences"/>";
                resultsGeo = "<fmt:message key="results_geo"/>";
                resultsIndi = "<fmt:message key="results_indi"/>";
                institution = "<fmt:message key="institution"/>";
                treeBase = "<fmt:message key="indicators_criteria_title"/>";
                addAll = "<fmt:message key="add_all"/>";
                invalidChar = "<fmt:message key="invalid_char"/>";
                limitLayers = "<fmt:message key="limit_layers"/>";
                percentText1 = "<fmt:message key="percentage_one"/>";
                percentText2 = "<fmt:message key="percentage_two"/>";
                percentText2S = "<fmt:message key="percentage_simple"/>";
                changeSelector= "<fmt:message key="change_selector"/>";
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

        <a name="anchorTop"></a>
        <!-- Content -->
        <form id="myform" name = "species" method = "get" style="margin:0px">
            <div id="contenido">
                <!-- Header -->
                <jsp:include page="/WEB-INF/jsp/header.jsp"/>

                <div id="content">
                    <h2><fmt:message key="analysis_title"/></h2>
                    <div id="help-box" ></div>
                    
                    <div id="entryCriteria"> <!-- Entry criteria div -->
                        <div id="querysPanel">

                            <!-- Taxonomy Panel -->
                            <div id="queryPanel2" class="queryPanel">
                                <p class="criteria_title">
                                    <fmt:message key="taxonomical_criteria_title"/>
                                    <a class="link_help" onclick="showPanel('<fmt:message key="help_tax_title" />','<fmt:message key="help_tax_desc" />')"></a>
                                </p>
                                <p style="margin:2px"><a> <fmt:message key="taxonomy_level"/>: </a></p>
                                <select name="taxonType" id="taxonTypeId" class="componentSize" tabindex="12" onchange="javascript:changeTaxonInput();" onKeyUp="javascript:changeTaxonInput();">
                                    <c:forEach items="${model.taxonFilters}" var="taxonFilter">
                                        <option value="<c:out value="${taxonFilter.id}"/>"<c:if test="${taxonFilter.id == taxonType}"> selected="selected"</c:if>>
                                            <fmt:message key="${taxonFilter.displayName}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <p style="margin:2px"><a> <fmt:message key="taxon_name"/>: </a></p>
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
                                <p class="criteria_title">
                                    <fmt:message key="indicators_criteria_title"/>
                                    <a class="link_help" onclick="showPanel('<fmt:message key="help_indi_title" />','<fmt:message key="help_indi_desc" />')"></a>
                                </p>
                                <div id="treeDiv"></div>
                                <input type="button" class="my_Button" id="addToListButtonIndi" value="<fmt:message key="add_criteria"/>" onclick="addIndicatorParam()" />
                                 <span id="treeParameters" style="font-size:10px"></span>
                            </div>                        

                            <!-- GIS Panel -->
                            <div id="queryPanel1" class="queryPanel">
                                <p class="criteria_title">
                                    <fmt:message key="geografical_criteria_title"/>
                                    <a class="link_help" onclick="showPanel('<fmt:message key="help_geo_title" />','<fmt:message key="help_geo_desc" />')"></a>
                                </p>
                                <!-- Selected polygons -->
                                <div id="currentLayer"></div>
                                <div id="info"></div>
                                <span id="mapParameters" style="font-size:10px"></span>
                                <!-- Selected limit polygons -->
                                <div id="currentLimitLayer" style="border-top:dashed 2px gray;padding-top:6px;margin-top:6px"></div>
                                <div id="infoLimit"></div>
                                <span id="mapLimitParameters" style="font-size:10px"></span>
                            </div>

                            <!-- Query Button -->
                            <input type="button" class="main_Button" id="makeQueryButton" value="<fmt:message key="consult"/>"
                            onclick="makeQuery()" />

                        </div>

                        <div id="mapPanel">
                            <!-- Map Panel -->
                            <div id="map"> </div>
                            <div id="wrapper">
                                <div id="location"></div>
                                <div id="scale"></div>
                            </div>
                        </div>

                    </div> <!-- End entry criteria div -->

                    <!-- Panel to show the result header (abstract) -->
                    <div id="resultsPanel"></div>

                    <!-- Panel to show the detailed result -->
                    <div id="detailedResults"></div>

                    <!-- To show specimens points into the map -->
                    <input type="hidden" id="hiddenLayers" value="">
                    <input type="hidden" id="hiddenLimitLayers" value="">
                    <input type="hidden" id="hiddenTaxa" value="">
                    <input type="hidden" id="hiddenIndicators" value="">

                    <input type="hidden" id="hidLayersToShow" value="">
                    <input type="hidden" id="hiddenIndiToShow" value="">
                    <input type="hidden" id="hiddenLimitToShow" value="">
                </div>

                <!-- Footer -->
                <br>
                <div id="footer">
                    <fmt:message key="footer_text"/>
                </div>
            </div><!-- Content ending -->
        </form>

    </body>
</html>

