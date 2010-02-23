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
            var currentPolygomId; //(FID)
            var currentPolygomName; //(Name)
            //Available layers [[id,name],...]
            var layersList;
            //Current selected layer
            var layerId; //(FID)
            var layerName; //(Name)
            //Current layer index (numeric)
            var layerIndex;
            //Base Layer
            var base;

            //Pink tile avoidance
            OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
            //Make OL compute scale according to WMS spec
            OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;

            function init(){

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
                provincias = addLayerWMS( "Provincias-CR",'IABIN_Indicadores:bd_cr_provincias');
                provincias.setVisibility(true);

                //Setup PMA ASP layer
                aspPMA = addLayerWMS( "ASP-PMA",'IABIN_Indicadores:bd_pan_areas_protegidas');
                aspPMA.setVisibility(true);

                map.addLayer(base);
                map.addLayer(provincias);
                map.addLayer(aspPMA);

                //Variables to manage the events on the diferent layers
                layersList = new Array(new Array('IABIN_Indicadores:bd_meso_limite_paies','Paises - Mesoamérica'),
                new Array('IABIN_Indicadores:bd_cr_provincias','Provincias - CR'),
                new Array('IABIN_Indicadores:bd_pan_areas_protegidas','Área Silvestre Protegida - PMA'));

                map.events.register('click', map, function (e) {
                    document.getElementById('info').innerHTML = "Cargando...";
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

                // Build up all controls
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

            } //init() ends

            /*
             *Create a drop down to specified the current layer
             */
            function createDDLayers(){
                var dropdown = "<p style=\"margin:1px\"><a> Capas: </a></p>";
                dropdown += "<select name=ddLayer class=\"componentSize\" onchange='onChangeLayer(this.form.ddLayer);'>";
                //Setting drop down options
                for(var i=0;i<layersList.length;i++){
                    dropdown+= "<option>"+layersList[i][1]+"</option>";
                }
                dropdown+= "</select>";
                document.getElementById('currentLayer').innerHTML = dropdown;
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
                //Obtain the selected polygon(s), value set on currentPolygomId var
                parseHTML(response.responseText);
                //Set up drop down to select polygons
                var dd = "<p style=\"margin:1px\"><a> Polígonos: </a></p>";
                dd += "<select name=ddPolygon class=\"componentSize\" onchange='onChangePolygon(this.form.ddPolygon);'>";
                //Setting drop down options
                for(var i=0;i<polygonsList.length;i++){
                    dd+= "<option>"+polygonsList[i][1]+"</option>";
                }
                dd+= "</select>";
                //Show drop down
                document.getElementById('info').innerHTML = dd;
            };

            /*
             * To obtain an Array with all the selected polygons
             */
            function parseHTML(html){
                var rows = html.split("<tr>");
                var polygons = new Array();
                polygons.push(new Array(""," -- Seleccionar -- ")); //Non selected option
                //Loop into the rows to obtain polygons' ids
                //In the second position the polygons appears
                for(var i = 2;i<rows.length;i++){ 
                    var row = rows[i];
                    var node = new Array(trim(row.split("<td>")[1]),
                    trim(row.split("<td>")[2])); //id,name
                    polygons.push(node);
                }
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

            /*
             * When the value of polygoms drop down is changed
             */
            function onChangePolygon(dropdown)
            {
                var selectedIndex = dropdown.selectedIndex;
                currentPolygomId = polygonsList[selectedIndex][0];
                currentPolygomName = polygonsList[selectedIndex][1];
                return true;
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
                currentPolygomId = null;
                currentPolygomName = null;
                polygonsList = null;
                return true;
            };

            /*
             * Agrega un nuevo filtro geografico
             */
            function addLayerParam(polygom,capa,pname,cname) {
                //Validar que ninguno de los parametros sea nulo
                if(capa==null||polygom==null){
                    alert('Se debe seleccionar una capa y un polígono');
                    return;
                }
                //Validar que la capa/polígono seleccionados no sean repetidos
                var aux_exist = document.getElementById(capa+"~"+polygom);
                if(aux_exist!=null){
                    alert('La capa y polígono seleccionados ya fue agregada anteriormente');
                    document.getElementById('info').innerHTML = "";
                    clearGeograficVars();
                    createDDLayers();
                    return;
                }
                //Agregar el parametro a la lista
                var layerslist = document.getElementById('mapParameters');
                var newdiv = document.createElement('div');
                newdiv.setAttribute("id",capa+"~"+polygom);
                newdiv.innerHTML =
                    "<a href=\"javascript:\" onclick=\"removeLayerParamElement(\'"+capa+"~"+polygom+"\')\">"+pname+"</a>";
                layerslist.appendChild(newdiv);
                //Restablecer el estado del mecanismo de seleccion de capas
                document.getElementById('info').innerHTML = "";
                clearGeograficVars();
                createDDLayers();
            }

            /*
             * Elimina un elemento dado su id
             */
            function removeLayerParamElement(divNum) {
              var d = document.getElementById('mapParameters');
              var olddiv = document.getElementById(divNum);
              d.removeChild(olddiv);
            }

            /*
             * Agrega un nuevo filtro taxonomico
             */
            function addTaxonParam() {
                //Obtener el valor del campo de texto
                var txTaxon = document.getElementById('taxonId');
                var text = txTaxon.value;
                //Validar que no sea nulo
                if(text==null||text==''){
                    alert('Debe especificar el nombre del taxón que desea agregar');
                    txTaxon.value = null;
                    return
                }
                //Validar que no sea repetido
                var aux_exist = document.getElementById(text);
                if(aux_exist!=null){
                    alert('El taxón seleccionado ya fue agregado anteriormente');
                    txTaxon.value = null;
                    return;
                }
                //Agregar el parametro de busqueda
                var taxonlist = document.getElementById('taxParameters');
                var newdiv = document.createElement('div');
                newdiv.setAttribute("id",text);
                newdiv.innerHTML =
                    "<a href=\"javascript:\" onclick=\"removeTaxonParamElement(\'"+text+"\')\">"+text+"</a>";
                taxonlist.appendChild(newdiv);
                txTaxon.value = null;
            }

            /*
             * Elimina un elemento dado su id
             */
            function removeTaxonParamElement(divNum) {
              var d = document.getElementById('taxParameters');
              var olddiv = document.getElementById(divNum);
              d.removeChild(olddiv);
            }

            /*
             * Setea en su valor inicial las variables geograficas
             */
            function clearGeograficVars(){
                currentPolygomId = null;
                currentPolygomName = null;
                layerId = 'IABIN_Indicadores:bd_meso_limite_paies';
                layerIndex = 0;
                layerName = 'Paises - Mesoamérica';
                polygonsList = null;
            }

            /*
             * To make the final query
             */
            function makeQuery(){
                var layerslist = document.getElementById('mapParameters');
                if(layerslist.childNodes.length==0){
                    alert('No hay filtros de GIS');
                    return;
                }
            }
            
        </script>

    </head>
    <body onload="init()">

        <%-- TaxonFilter Auto Completes--%>
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
                        <div id="currentLayer"></div>
                        <div id="info"></div>
                        <input type="button" class="my_Button" id="addToListButton"
                        value="Agregar filtro"
                        onclick="addLayerParam(currentPolygomId,layerId,currentPolygomName,layerName)" />
                        <span id="mapParameters"></span>
                    </div>

                    <!-- Taxonomy Panel -->
                    <div id="queryPanel">
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
                        <input type="button" class="my_Button" id="addToListButtonTax" value="Agregar filtro" onclick="addTaxonParam()" />
                        <span id="taxParameters"></span>
                        <script type="text/javascript">
                            changeTaxonInput();
                        </script>
                    </div>

                    <!-- Indicator Button -->
                    <div id="queryPanel">

                    </div>

                    <!-- Query Button -->
                    <input type="button" class="main_Button" id="makeQueryButton" value="Hacer consulta"
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

                <div id="resultsPanel">

                </div>

            </div>
        </form>
        <!-- Content ending -->
    </body>
</html>

