<%-- 
    Document   : species
    Created on : 15/01/2010, 11:24:22 AM
    Author     : esmata
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tags.jsp" %>
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
        
        <title><fmt:message key="title"/></title>

        <script src="http://216.75.53.105:80/geoserver/openlayers/OpenLayers.js" type="text/javascript"></script>       
        <script type="text/JavaScript" src="http://openlayers.org/api/OpenLayers.js"></script>   
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
            //Base Layer
            var base;
            //Indicators tree
            var tree;
            var currentIconMode;
            //Getting a reference to the root node of indicators tree
            var rootNode;
            //Current selected indicators tree node
            var selectedNodeId;
            var selectedNodeName;
            var isLeaf;

            /*              Internacionalization variables                */
            var layerText;
            var consultText;
            var loadingText;

            //Pink tile avoidance
            OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
            //Make OL compute scale according to WMS spec
            OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;

            function init(){

                internationalization(); //Load messages content

                format = 'image/png';

                var bounds = new OpenLayers.Bounds(
                    -102.184, 7.204,
                    -77.157, 22.472
                );
                var options = {
                    controls: [],
                    maxExtent: bounds,
                    maxResolution: 0.09776171875,
                    projection: "EPSG:900913",
                    units: 'm'
                };
                map = new OpenLayers.Map('map', options);

                //Seting the default current layer (Layers drop down)
                layerId = 'IABIN_Indicadores:bd_meso_limite_paies';
                layerIndex = 0;
                layerName = 'Paises - Mesoamérica';

                //Setup base layer
                base = new OpenLayers.Layer.WMS(
                    "Mesoamerica", "http://216.75.53.105:80/geoserver/wms",
                    {
                        width: '540',
                        height: '330',
                        transparent: "true",
                        layers: 'IABIN_Indicadores:bd_meso_limite_paies'
                    },
                    {isBaseLayer: true,singleTile: true, ratio: 1}
                );

                //Setup CR Provinces layer
                provincias = addLayerWMS( 'Provincias-CR','IABIN_Indicadores:bd_cr_provincias');
                provincias.setVisibility(true);

                //Setup PMA ASP layer
                aspPMA = addLayerWMS( 'ASP-PMA','IABIN_Indicadores:bd_pan_areas_protegidas');
                aspPMA.setVisibility(true);

                map.addLayer(base);
                map.addLayer(provincias);
                map.addLayer(aspPMA);

                //Variables to manage the events on the diferent layers
                layersList = new Array(new Array('IABIN_Indicadores:bd_meso_limite_paies','Paises - Mesoamérica'),
                new Array('IABIN_Indicadores:bd_cr_provincias','Provincias - CR'),
                new Array('IABIN_Indicadores:bd_pan_areas_protegidas','Área Silvestre Protegida - PMA'));

                map.events.register('click', map, function (e) {
                    document.getElementById('info').innerHTML = loadingText;
                    polygonsList = null;
                    var params = { REQUEST: "GetFeatureInfo",
                        EXCEPTIONS: "application/vnd.ogc.se_xml",
                        BBOX: map.getExtent().toBBOX(),
                        X: e.xy.x,
                        Y: e.xy.y,
                        INFO_FORMAT: 'text/html',
                        QUERY_LAYERS: map.layers[layerIndex].params.LAYERS,
                        FEATURE_COUNT: 50,
                        Styles: '',
                        Layers: layerId,
                        srs: 'EPSG:900913',
                        WIDTH: map.size.w,
                        HEIGHT: map.size.h,
                        format: 'image/png' };
                    event = e;
                    OpenLayers.loadURL("http://216.75.53.105:80/geoserver/wms", params, this, setHTML, setHTML);
                    OpenLayers.Event.stop(e);
                });

                //Build up all controls
                map.addControl(new OpenLayers.Control.PanZoomBar({
                    position: new OpenLayers.Pixel(2, 15)
                }));
                map.addControl(new OpenLayers.Control.Navigation());
                map.addControl(new OpenLayers.Control.Scale($('scale')));
                map.addControl(new OpenLayers.Control.MousePosition({element: $('location')}));
                map.addControl(new OpenLayers.Control.LayerSwitcher());
                map.zoomToExtent(bounds);

                //Create a drop down to specified the current layer
                createDDLayers();

                //Init indicators tree
                initIndicators();

            } //init() ends

            /*
             * Create a drop down to specified the current layer
             */
            function createDDLayers(){
                var dropdown = "<p style=\"margin:1px\"><a> "+layerText+": </a></p>";
                dropdown += "<select name=ddLayer class=\"componentSize\" onchange='onChangeLayer(this.form.ddLayer);'>";
                //Setting drop down options
                for(var i=0;i<layersList.length;i++){
                    dropdown+= "<option>"+layersList[i][1]+"</option>";
                }
                dropdown+= "</select>";
                document.getElementById('currentLayer').innerHTML = dropdown;
            }

            /*
             * Initialazing the indicators tree
             */
            function initIndicators(){
                //Creating the tree
                tree = new YAHOO.widget.TreeView("treeDiv");
                //Turn dynamic loading on for entire tree
                tree.setDynamicLoad(loadNodeData, currentIconMode);
                //Getting a reference to the root element
                rootNode = tree.getRoot();
                //Add the root element
                var tempNode = new YAHOO.widget.TextNode('Indicadores Taxonómicos', rootNode, false);
                tempNode.data = 0;
                //Render the tree
                tree.draw();
                //Suscribing the event to get the selected node
                tree.subscribe('clickEvent',function(oArgs) {
                    selectedNodeId = oArgs.node.data;
                    selectedNodeName = oArgs.node.label;
                    if(oArgs.node.isLeaf){
                        isLeaf = "true";
                    }
                    else{
                        isLeaf = "false";
                    }
                });
            }

            /*
             * Function to create new Layers
             */
            function addLayerWMS(name, layer)
            {
                return new OpenLayers.Layer.WMS( name, "http://216.75.53.105:80/geoserver/wms",
                {layers: layer,
                    transparent: "true",
                    height: '478',
                    width: '512'}, {isBaseLayer: false,singleTile: true, ratio: 1});
            };

            /*
             * Sets the HTML provided into the nodelist element
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
                    alert('Debe seleccionar solo un polígono de la capa');
                    return;
                }
                //Add the polygon to the geografical criteria list
                currentPolygonId = polygonsList[0][0];
                currentPolygonName = polygonsList[0][1];
                addLayerParam(currentPolygonId,layerId,currentPolygonName,layerName);
                //Clean the Loading status
                document.getElementById('info').innerHTML = "";
            };

            /*
             * To obtain an Array with all the selected polygons
             */
            function parseHTML(html){
                var rows = html.split("<tr>");
                //Validate that the response have at least one polygon
                if(rows.length<3){ //On 3 position is the first polygon
                    alert('No se ha seleccionado un polígono válido');
                    document.getElementById('info').innerHTML = "";
                    return;
                }
                var prepolygon = rows[2];
                var prepolygonIni = prepolygon.replace("<td>","|_|");
                var prepolygonFin = prepolygonIni.replace("</tr>","|_|");
                var polygonArray = prepolygonFin.split("|_|");
                var polygon = polygonArray[1];
                //Delete separators (new line ...)
                var withOutSeparators = deleteSeparators(polygon);
                //Homogenize the delimeter of the atributes
                var homogenized = withOutSeparators.replace(/<\/td>/gi,"");
                //Get the final info
                var atributes = homogenized.split("<td>");
                var id = atributes[0];
                var info = "";
                for(var i = 1;i<atributes.length;i++){
                    info += trim(atributes[i])+" ";
                }
                var polygons = new Array();
                polygons.push(new Array(id,info));
                polygonsList = polygons;
            };

            /*
             * Trim function
             */
            function trim(string)
            {
                var str = string.replace(/^\s*|\s*$/g,"");
                return str;
            };

            /**
             * Delete all separators caracters
             */
            function deleteSeparators(string){
                var str = string.replace(/(\r\n|\r|\n|\s|\u0085|\u000C|\u2028|\u2029)/g,"");
                return str;
            };

            /*
             * When the value of layers drop down is changed
             */
            function onChangeLayer(dropdown)
            {
                var selectedIndex = dropdown.selectedIndex;
                layerIndex  = selectedIndex;
                layerId = layersList[selectedIndex][0];
                layerName = layersList[selectedIndex][1];
                document.getElementById('info').innerHTML = "";
                currentPolygonId = null;
                currentPolygonName = null;
                polygonsList = null;
                return true;
            };

            /*
             * Add a new geographic filter
             */
            function addLayerParam(polygon,capa,pname,cname) {
                //Validate null parameters
                if(capa==null||polygon==null){
                    alert('Se debe seleccionar una capa y un polígono');
                    return;
                }
                //Get just the plain data of layer and polygon
                var newCapa = capa.split(":")[1];
                var newPolygon = polygon.split(".")[1];
                //Validate repeated layer/polygon
                var aux_exist = document.getElementById(newCapa+"~"+newPolygon);
                if(aux_exist!=null){
                    alert('La capa y polígono seleccionados ya fue agregada anteriormente');
                    document.getElementById('info').innerHTML = "";
                    currentPolygonId = null;
                    currentPolygonName = null;
                    polygonsList = null;
                    return;
                }
                //Add the parameter to the list
                var layerslist = document.getElementById('mapParameters');
                var newdiv = document.createElement('div');
                newdiv.setAttribute("id",newCapa+"~"+newPolygon);
                newdiv.innerHTML =
                    "<a href=\"javascript:\" onclick=\"removeLayerParamElement(\'"+newCapa+"~"+newPolygon+"\')\">"+pname+"</a>";
                layerslist.appendChild(newdiv);
                //Restore the mechanism for layer selection
                document.getElementById('info').innerHTML = "";
                currentPolygonId = null;
                currentPolygonName = null;
                polygonsList = null;
            };

            /*
             * Deletes an element by it's id
             */
            function removeLayerParamElement(divNum) {
              var d = document.getElementById('mapParameters');
              var olddiv = document.getElementById(divNum);
              d.removeChild(olddiv);
            };

            /*
             * Add a new taxonomic filter
             */
            function addTaxonParam() {
                //Get the text field value
                var txTaxon = document.getElementById('taxonId');
                var text = txTaxon.value;
                //Validate null values
                if(text==null||text==''){
                    alert('Debe especificar el nombre del taxón que desea agregar');
                    txTaxon.value = null;
                    return
                }
                //Validate repeated values
                var aux_exist = document.getElementById(text);
                if(aux_exist!=null){
                    alert('El taxón seleccionado ya fue agregado anteriormente');
                    txTaxon.value = null;
                    return;
                }
                //Add the search criteria
                var taxonlist = document.getElementById('taxParameters');
                var newdiv = document.createElement('div');
                newdiv.setAttribute("id",text);
                newdiv.innerHTML =
                    "<a href=\"javascript:\" onclick=\"removeTaxonParamElement(\'"+text+"\')\">"+text+"</a>";
                taxonlist.appendChild(newdiv);
                txTaxon.value = null;
            };

            /*
             * Deletes an element by it's id
             */
            function removeTaxonParamElement(divNum) {
              var d = document.getElementById('taxParameters');
              var olddiv = document.getElementById(divNum);
              d.removeChild(olddiv);
            };

            /*
             * Add new indicators filter
             */
            function addIndicatorParam(){
                //Validate that a node was selected
                if(selectedNodeId==null||selectedNodeName==null){
                    alert('Primero debe seleccionar un indicador taxonómico');
                    return;
                }
                //Validate if it is a leaf
                if(isLeaf=="false"){
                    alert('El indicador debe ser una hoja del árbol');
                    return;
                }
                //Validate that the selected indicator was not repeated
                var aux_exist = document.getElementById(selectedNodeId);
                if(aux_exist!=null){
                    alert('El indicador ya fue agregado anteriormente');
                    return;
                }
                //Add the criteria to the list
                var indicatorslist = document.getElementById('treeParameters');
                var newdiv = document.createElement('div');
                newdiv.setAttribute("id",selectedNodeId);
                newdiv.innerHTML =
                    "<a href=\"javascript:\" onclick=\"removeTreeParamElement(\'"+selectedNodeId+"\')\">"+selectedNodeName+"</a>";
                indicatorslist.appendChild(newdiv);
            };

            /*
             * Deletes an element by it's id
             */
            function removeTreeParamElement(divNum) {
              var d = document.getElementById('treeParameters');
              var olddiv = document.getElementById(divNum);
              d.removeChild(olddiv);
            };

            /*
             * Set the initial value to the geografic variables
             */
            function clearGeograficVars(){
                currentPolygonId = null;
                currentPolygonName = null;
                layerId = 'IABIN_Indicadores:bd_meso_limite_paies';
                layerIndex = 0;
                layerName = 'Paises - Mesoamérica';
                polygonsList = null;
            };

            /*
             * This function calls another function that is on charge to make the
             * final query and show the result to the user
             */
            function makeQuery(){
                var layerslist = document.getElementById('mapParameters');
                var taxonlist = document.getElementById('taxParameters');
                var treelist = document.getElementById('treeParameters');
                var layersShow = new Array();
                var taxonsShow = new Array();
                var treeShow = new Array();
                //Validate that exist at least one search criteria
                if(layerslist.childNodes.length==0&&taxonlist.childNodes.length==0&&treelist.childNodes.length==0){
                    alert('Por favor seleccione algún criterio de búsqueda');
                    return;
                }
                //Loop over geographical criteria
                var selectedLayers = "";
                for (var i =0; i <layerslist.childNodes.length; i++){
                    selectedLayers += layerslist.childNodes[i].id+"|";
                    layersShow.push(layerslist.childNodes[i].textContent);
                }
                //Loop over taxonomic criteria
                var selectedTaxa = "";
                for (var j =0; j <taxonlist.childNodes.length; j++){
                    selectedTaxa += taxonlist.childNodes[j].textContent+"|";
                    taxonsShow.push(taxonlist.childNodes[j].textContent);
                }
                //Loop over indicators criteria
                var selectedIndicators = "";
                for (var k =0; k <treelist.childNodes.length; k++){
                    selectedIndicators += treelist.childNodes[k].id+"|";
                    treeShow.push(treelist.childNodes[k].textContent);
                }
                //Call the function that returns the result (xml) asincrinically
                executeFinalQuery(selectedLayers,selectedTaxa,selectedIndicators,
                layersShow,taxonsShow,treeShow);
            };
            
        </script>

        <!-- Internacionalization-->
        <script type="text/javascript">
            function internationalization(){
                layerText =  "<fmt:message key="layers"/>";
                loadingText = "<fmt:message key="loading"/>";
            };
        </script>

    </head>
    <body onload="init()">

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
        <form id="myform" name = "species" method = "get">
            <div id="contenido">
                <h2><fmt:message key="analysis_title"/></h2>

                <div id="querysPanel">
                    <!-- GIS Panel -->
                    <div id="queryPanel">
                        <p style="font-weight:bold;font-style:italic;margin:2px;text-align:center;">
                            <fmt:message key="geografical_criteria_title"/></p>
                        <div id="currentLayer"></div>
                        <div id="info"></div>
                        <span id="mapParameters" style="font-size:10px"></span>
                    </div>

                    <!-- Taxonomy Panel -->
                    <div id="queryPanel">
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
                        <input type="button" class="my_Button" id="addToListButtonTax" value="Agregar criterio" onclick="addTaxonParam()" />
                        <span id="taxParameters" style="font-size:10px"></span>
                        <script type="text/javascript">
                            changeTaxonInput();
                        </script>
                    </div>

                    <!-- Indicator Panel -->
                    <div id="queryPanel">
                        <p style="font-weight:bold;font-style:italic;margin:2px;text-align:center;">
                            <fmt:message key="indicators_criteria_title"/></p>
                        <div id="treeDiv"></div>
                        <input type="button" class="my_Button" id="addToListButtonIndi" value="Agregar criterio" onclick="addIndicatorParam()" />
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

                <div id="resultsPanel"></div>

            </div>
        </form>
        <!-- Content ending -->
    </body>
</html>

