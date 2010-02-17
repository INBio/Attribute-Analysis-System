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

            //Global variables
            var map;
            var polygonsList; //Available poligons [[id,name],...] Depends on layersList
            var currentPolygon = ""; //Current selected polygon
            var layersList; //Available layers [[id,name],...]
            var layerName; //Current layer (FID)
            var layerIndex; //Current layer index (numeric)
            var cantones;
            var provincias;
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
                layerName = 'IABIN_Indicadores:bd_meso_limite_paies';
                layerIndex = 0;

                //Setup base layer
                base = new OpenLayers.Layer.WMS(
                    "Mesoamerica", "http://216.75.53.105:80/geoserver/wms",
                    {
                        width: '540',
                        srs: 'EPSG:900913',
                        layers: 'IABIN_Indicadores:bd_meso_limite_paies',
                        height: '330',
                        styles: '',
                        format: format,
                        tilesOrigin : map.maxExtent.left + ',' + map.maxExtent.bottom
                    },
                    {isBaseLayer: true,singleTile: true, ratio: 1}
                );

                //Setup Provinces layer
                provincias = addLayerWMS( "Provincias",'IABIN_Indicadores:bd_cr_provincias');
                provincias.setVisibility(true);

                //Setup cantones layer
                cantones = addLayerWMS( "Cantones",'IABIN_Indicadores:bd_cr_cantones');
                cantones.setVisibility(true);

                map.addLayer(base);
                map.addLayer(provincias);
                map.addLayer(cantones);            

                //Variables to manage the events on the diferent layers
                layersList = new Array(new Array('IABIN_Indicadores:bd_meso_limite_paies','PAÍS'),
                new Array('IABIN_Indicadores:bd_cr_provincias','PROVINCIA'),
                new Array('IABIN_Indicadores:bd_cr_cantones','CANTÓN'));

                map.events.register('click', map, function (e) {
                    document.getElementById('info').innerHTML = "Cargando...";
                    var params = { REQUEST: "GetFeatureInfo",
                        EXCEPTIONS: "application/vnd.ogc.se_xml",
                        BBOX: map.getExtent().toBBOX(),
                        X: e.xy.x,
                        Y: e.xy.y,
                        INFO_FORMAT: 'text/html',
                        QUERY_LAYERS: map.layers[layerIndex].params.LAYERS,
                        FEATURE_COUNT: 50,
                        Styles: '',
                        Layers: layerName,
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
                var dropdown = "<a> Capas: </a>";
                dropdown += "<select name=ddLayer class=\"dDown\" onchange='onChangeLayer(this.form.ddLayer);'>";
                //Setting drop down options
                for(var i=0;i<layersList.length;i++){
                    dropdown+= "<option>"+layersList[i][1]+"</option>";
                }
                dropdown+= "</select>";
                document.getElementById('currentLayer').innerHTML = dropdown;

            } //init() ends

            //Function to create new Layers
            function addLayerWMS(name, layer)
            {
                return new OpenLayers.Layer.WMS( name, "http://216.75.53.105:80/geoserver/wms",
                {layers: layer,
                    transparent: "true",
                    height: '478',
                    width: '512'}, {isBaseLayer: false,singleTile: true, ratio: 1});
            };

            //Sets the HTML provided into the nodelist element
            function setHTML(response){
                //Obtain the selected polygon(s), value set on currentPolygon var
                parseHTML(response.responseText);
                //Set up drop down to select polygons
                var dd = "<a> Polígonos: </a>";
                dd += "<select name=ddPolygon class=\"dDown\" onchange='onChangePolygon(this.form.ddPolygon);'>";
                //Setting drop down options
                for(var i=0;i<polygonsList.length;i++){
                    dd+= "<option>"+polygonsList[i][1]+"</option>";
                }
                dd+= "</select>";
                //Show drop down
                document.getElementById('info').innerHTML = dd;
            };

            //To obtain an Array with all the selected polygons
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

            //Trim function
            function trim(string)
            {
                var str = string.replace(/^\s*|\s*$/g,"");
                return str;
            };

            function onChangePolygon(dropdown)
            {
                var selectedIndex = dropdown.selectedIndex;
                currentPolygon = polygonsList[selectedIndex][1];
                return true;
            };

            function onChangeLayer(dropdown)
            {
                var selectedIndex = dropdown.selectedIndex;
                layerIndex  = selectedIndex;
                layerName = layersList[selectedIndex][0];
                document.getElementById('info').innerHTML = "";
                return true;
            };


            
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
        <form name = "species" method = "get">
            <div id="contenido">
                <h2><fmt:message key="analysis_title"/></h2>

                <!-- Embedding the map -->                
                <div id="queryPanel">
                    <div id="centered">
                        <div id="currentLayer"></div>
                        <div id="info"></div>
                    </div>
                </div>

                <div id="queryPanel">
                    <div id="centered">
                        <a> <fmt:message key="taxonomy_level"/>: </a><br>
                        <select name="taxonType" id="taxonTypeId" class="dDown" tabindex="12" onchange="javascript:changeTaxonInput();" onKeyUp="javascript:changeTaxonInput();">
                          <c:forEach items="${model.taxonFilters}" var="taxonFilter">
                            <option value="<c:out value="${taxonFilter.id}"/>"<c:if test="${taxonFilter.id == taxonType}"> selected="selected"</c:if>>
                              <fmt:message key="${taxonFilter.displayName}"/>
                            </option>
                          </c:forEach>
                        </select>
                        <span id="newTaxonValue">
                            <a> <fmt:message key="taxon_name"/>: </a><br>
                            <input id="taxonId" type="text" name="taxonValue" value="<c:out value="${taxonValue}"/>" tabindex="13"/>
                            <div id="taxonContainer"></div>
                        </span>
                        <script type="text/javascript">
                          changeTaxonInput();
                        </script>
                    </div>
                </div>

                <div style="clear:both"></div>
                <div id="map"> </div>
                <div id="wrapper">
                    <div id="location">location</div>
                    <div id="scale"></div>
                </div>

            </div>
        </form>
        <!-- Content ending -->
    </body>
</html>

