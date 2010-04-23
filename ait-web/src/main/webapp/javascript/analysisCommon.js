/* AIT - Analysis of taxonomic indicators
 *
 * Copyright (C) 2010  INBio (Instituto Nacional de Biodiversidad)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/* Internacionalization variables in common */
var layerText;
var loadingText;
var selectCriteriaE; //E mens error message
var selectOnePolygonE;
var invalidPolygonE;
var selectLayerPolyE;
var alreadyAddedE;
var specifyTaxonE;
var selectIndicatorFirstE;
var treeLeafE;
var loadingImage; //To store the image URL

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
 * Initialazing the gis functionality
 */
function initMap(){
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
    layerIndex = 1; //Porque la 0 es la de Virtual Earth
    layerName = 'Paises - Mesoamérica';

    //Setup Meso layer
    base = addLayerWMS( 'Mesoamerica','IABIN_Indicadores:bd_meso_limite_paies');
    base.setVisibility(true);

    //Setup CR Provinces layer
    provincias = addLayerWMS( 'Provincias-CR','IABIN_Indicadores:bd_cr_provincias');
    provincias.setVisibility(true);

    //Setup PMA ASP layer
    aspPMA = addLayerWMS( 'ASP-PMA','IABIN_Indicadores:bd_pan_areas_protegidas');
    aspPMA.setVisibility(true);

    vectorLayer.setVisibility(true);

    map.addLayer(virtualEarthLayer);
    map.addLayer(base);
    map.addLayer(provincias);
    map.addLayer(aspPMA);
    map.addLayer(vectorLayer);

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
}

/*
 * To obtain an Array with all the selected polygons
 */
function parseHTML(html){
    var rows = html.split("<tr>");
    //Validate that the response have at least one polygon
    if(rows.length<3){ //On 3 position is the first polygon
        alert(invalidPolygonE);
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
}

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
 * Trim function
 */
function trim(string)
{
    var str = string.replace(/^\s*|\s*$/g,"");
    return str;
}

/**
 * Delete all separators caracters
 */
function deleteSeparators(string){
    var str = string.replace(/(\r\n|\r|\n|\s|\u0085|\u000C|\u2028|\u2029)/g,"");
    return str;
}

/*
 * When the value of layers drop down is changed
 */
function onChangeLayer(dropdown)
{
    var selectedIndex = dropdown.selectedIndex;
    layerIndex  = selectedIndex+1; //+1 is because of virtual earth layer
    layerId = layersList[selectedIndex][0];
    layerName = layersList[selectedIndex][1];
    document.getElementById('info').innerHTML = "";
    currentPolygonId = null;
    currentPolygonName = null;
    polygonsList = null;
    return true;
}

/*
 * Add a new geographic filter
 */
function addLayerParam(polygon,capa,pname,cname) {
    //Validate null parameters
    if(capa==null||polygon==null){
        alert(selectLayerPolyE);
        return;
    }
    //Get just the plain data of layer and polygon
    var newCapa = capa.split(":")[1];
    var newPolygon = polygon.split(".")[1];
    //Validate repeated layer/polygon
    var aux_exist = document.getElementById(newCapa+"~"+newPolygon);
    if(aux_exist!=null){
        alert(alreadyAddedE);
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
}

/*
 * Deletes an element by it's id
 */
function removeLayerParamElement(divNum) {
  var d = document.getElementById('mapParameters');
  var olddiv = document.getElementById(divNum);
  d.removeChild(olddiv);
}

/*
 * Add a new taxonomic filter
 */
function addTaxonParam() {
    //Get the text field value
    var txTaxon = document.getElementById('taxonId');
    var text = txTaxon.value;
    //Validate null values
    if(text==null||text==''){
        alert(specifyTaxonE);
        txTaxon.value = '';
        return
    }
    //Validate repeated values
    var aux_exist = document.getElementById(text);
    if(aux_exist!=null){
        alert(alreadyAddedE);
        txTaxon.value = '';
        return;
    }
    //Add the search criteria
    var taxonlist = document.getElementById('taxParameters');
    var newdiv = document.createElement('div');
    newdiv.setAttribute("id",text);
    newdiv.innerHTML =
        "<a href=\"javascript:\" onclick=\"removeTaxonParamElement(\'"+text+"\')\">"+text+"</a>";
    taxonlist.appendChild(newdiv);
    txTaxon.value = '';
}

/*
 * Deletes an element by it's id
 */
function removeTaxonParamElement(divNum) {
  var d = document.getElementById('taxParameters');
  var olddiv = document.getElementById(divNum);
  d.removeChild(olddiv);
}

/*
 * Add new indicators filter
 */
function addIndicatorParam(){
    //Validate that a node was selected
    if(selectedNodeId==null||selectedNodeName==null){
        alert(selectIndicatorFirstE);
        return;
    }
    //Validate if it is a leaf
    if(isLeaf=="false"){
        alert(treeLeafE);
        return;
    }
    //Validate that the selected indicator was not repeated
    var aux_exist = document.getElementById(selectedNodeId);
    if(aux_exist!=null){
        alert(alreadyAddedE);
        return;
    }
    //Add the criteria to the list
    var indicatorslist = document.getElementById('treeParameters');
    var newdiv = document.createElement('div');
    newdiv.setAttribute("id",selectedNodeId);
    newdiv.innerHTML =
        "<a href=\"javascript:\" onclick=\"removeTreeParamElement(\'"+selectedNodeId+"\')\">"+selectedNodeName+"</a>";
    indicatorslist.appendChild(newdiv);
}

/*
 * Deletes an element by it's id
 */
function removeTreeParamElement(divNum) {
  var d = document.getElementById('treeParameters');
  var olddiv = document.getElementById(divNum);
  d.removeChild(olddiv);
}

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
}
