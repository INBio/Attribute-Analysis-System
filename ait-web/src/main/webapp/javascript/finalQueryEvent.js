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

/**
 * Execute final query from especific parameters
 */
function executeFinalQuery(selectedLayers,selectedTaxa,selectedIndicators,
                           layersShow,taxonsShow,treeShow)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/finalQuery?layers="+selectedLayers+"&taxons="+selectedTaxa+"&indi="+selectedIndicators;

    //Prepare callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //Get the XML type. 0 means one or two parameters. 1 means 3 parameters
            var type = xmlDoc.getElementsByTagName("type")[0].childNodes[0].nodeValue;

            switch(type){
                case '0': // 0 means one or these tow categories (geographical and taxonomical)
                    //Get total count data
                    var total = xmlDoc.getElementsByTagName("total")[0].childNodes[0].nodeValue;
                    //Show general result and the search criteria
                    var criteria = "";
                    if(layersShow.length>0){
                        criteria += "<b>"+geographical+" </b>";
                        for(var i = 0;i<layersShow.length;i++){
                            criteria += layersShow[i]+"   ";
                        }
                        criteria += "<br>";
                    }
                    if(taxonsShow.length>0){
                        criteria += "<b>"+taxonomic+" </b>";
                        for(var j = 0;j<taxonsShow.length;j++){
                            criteria += taxonsShow[j]+"   ";
                        }
                        criteria += "<br>";
                    }
                    if(treeShow.length>0){
                        criteria += "<b>"+indicators+" </b>";
                        for(var k = 0;k<treeShow.length;k++){
                            criteria += treeShow[k]+"   ";
                        }
                        criteria += "<br>";
                    }                    
                    //Show the results
                    var resultHTML = createReportHeader(criteria, total);
                    document.getElementById('resultsPanel').innerHTML = resultHTML;
                    //Close de "Loading image"
                    YAHOO.example.container.wait.hide();
                    //Calling the anchor to positionate the page focus on the results
                    callAnchor('#anchorResult');
                    break;

                case '1': // 1 means 3 different criteria categories
                    //Getting the xml dom
                    document.getElementById('resultsPanel').innerHTML = xmlDoc.toString();
                    //Variables that are going to contain the results
                    var byPolygon = xmlDoc.getElementsByTagName("bypolygon");
                    var byIndicator = xmlDoc.getElementsByTagName("byindicator");
                    //Show the results
                    var result = createAdvancedHeader(byPolygon,byIndicator,layersShow,treeShow);
                    document.getElementById('resultsPanel').innerHTML = result;
                    //Close de "Loading image"
                    YAHOO.example.container.wait.hide();
                    //Calling the anchor to positionate the page focus on the results
                    callAnchor('#anchorResult');
                    break;
            }
            
        },

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }
    };
    
    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}

/**
 * This function creates the general report, so, return an html string
 * to show on results panel
 */
function createAdvancedHeader(byPolygon,byIndicator,layersShow,treeShow){
    var result = '';
    for(var i = 0;i<byPolygon.length;i++){
        divIds.push('p'+i);
        result += '<div id="p'+i+'">'+
        '<h3>'+layersShow[i]+'</h3>'+
        '<p>'+byPolygon[i].childNodes[0].nodeValue+' que cumplen algun indicador (FIXME)<p>'+
        '<input type="button" class="simple_button" id="viewDetail'+i+'" value="'+seeDetail+'" onclick="showDetails('+i+',\'p\',\''+arrayToString(treeShow)+'\')" />'+
        '<input type="button" class="simple_button" id="showOnMap'+i+'" value="'+seeOnMap+'" onclick="showPoints('+i+',\'p\')" /></div>';
    }
    for(var j = 0;j<byIndicator.length;j++){
        divIds.push('i'+j);
        result += '<div id="i'+j+'">'+
        '<h3>'+treeShow[j]+'</h3>'+
        '<p>'+byIndicator[j].childNodes[0].nodeValue+' que cumplen algun polígono (FIXME)</p>'+
        '<input type="button" class="simple_button" id="viewDetail'+i+'" value="'+seeDetail+'" onclick="showDetails('+j+',\'i\',\''+arrayToString(layersShow)+'\')" />'+
        '<input type="button" class="simple_button" id="showOnMap'+i+'" value="'+seeOnMap+'" onclick="showPoints('+j+',\'i\')" /></div>';
    }
    result+='<br><input type="button" class="new_search_button" id="newSearch" value="'+newSearch+'" onclick="cleanPage()" /><br><br><br>';
    return result;
}

/**
 * This function creates the general report, so, return an html string
 * based on the search criteria and the general count of matches
 */
function createReportHeader(criteria,total){
    var result = '<div id="reportHeader">'+
    '<h2>'+searchResults+'</h2>'+
    '<h3>'+searchCriteria+'</h3>'+criteria+
    '<h3>'+total+' '+speciesMatches+'</h3></div>'+
    '<input type="button" class="simple_button" id="viewDetail0" value="'+seeDetail+'" onclick="showDetailsFromHiddenData()" />'+
    '<input type="button" class="simple_button" id="showOnMap0" value="'+seeOnMap+'" onclick="showPointFromHiddenData()" />'+
    '<input type="button" class="new_search_button" id="newSearch" value="'+newSearch+'" onclick="cleanPage()" />';
    return result;
}

/**
 * To draw the specimen points into the map
 */
function showPointFromHiddenData(){
    //Show loading
    YAHOO.example.container.wait.show();
    //Getting the query parameters
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    //Drowing the points
    showSpecimenPoints(layers,taxa,indi);
}

/**
 * To draw the specimen points into the map
 * id = polygon id or indicator id
 * type p = polygon or i = indicator
 */
function showPoints(id,type){
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    if(type=='p'){
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var layersArray = layers.split('|');
        //Indicate de current selected
        indicateCurrent('p'+id);
        //Drowing the points
        showSpecimenPoints(layersArray[id]+'|',taxa,indi);
    }
    else{
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var indiArray = indi.split('|');
        //Indicate de current selected
        indicateCurrent('i'+id);
        //Drowing the points
        showSpecimenPoints(layers,taxa,indiArray[id]+'|');
    }
}

/**
 * To get a detailed report of the query when there is one or two parameters
 * Return a List of specimens that match with the criteria
 * id = polygon id or indicator id
 * type p = polygon or i = indicator
 * toShow polygons or indicators to show in the final matrix
 */
function showDetails(id,type,toShow){
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    if(type=='p'){
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var layersArray = layers.split('|');
        //Indicate de current selected
        indicateCurrent('p'+id);
        //Drowing the points
        detailedTable(layersArray[id]+'|',taxa,indi,toShow,type,id);
    }
    else{
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var indiArray = indi.split('|');
        //Indicate de current selected
        indicateCurrent('i'+id);
        //Drowing the points
        detailedTable(layers,taxa,indiArray[id]+'|',toShow,type,id);
    }
}

/**
 * To get a detailed report of the query when there is one or two parameters
 * Return a List of specimens that match with the criteria
 */
function showDetailsFromHiddenData(){
    //Show loading
    YAHOO.example.container.wait.show();
    //Getting the query parameters
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    var lToShow = document.getElementById('hidLayersToShow').value;
    //Showing the details
    viewSimpleDetail(layers,taxa,indi,lToShow);
}

/**
 * Get ready for a new query
 */
function cleanPage(){
    document.getElementById('detailedResults').innerHTML = "";
    document.getElementById("detailedResults").className = "";
    document.getElementById('resultsPanel').innerHTML = "";
    replaceVectorLayer();
    divIds = new Array();
    callAnchor('#anchorTop');
}

/**
 * Indicates de current selected <div> from resultsPanel
 */
function indicateCurrent(id){
    for(var i = 0;i<divIds.length;i++){
        if(id==divIds[i]){ //Indicar
            document.getElementById(divIds[i]).className = "current_Selected";
        }
        else{ //No indicar
            document.getElementById(divIds[i]).className = "";
        }
    }
}

//Array to string
function arrayToString(array){
    var result = '';
    for(var i = 0 ;i<array.length ;i++){
        result += array[i]+"|";
    }
    return result;
}