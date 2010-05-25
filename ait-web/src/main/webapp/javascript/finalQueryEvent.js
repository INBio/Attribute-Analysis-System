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
                    callAnchor('#anchorTop');
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
                    callAnchor('#anchorTop');
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
        result += '<div id="p'+i+'" class="detailed_results">'+
        '<h3>'+layersShow[i]+'</h3>'+
        '<p>'+byPolygon[i].childNodes[0].nodeValue+' especies que cumplen algun indicador (FIXME)<p>';
        if(byPolygon[i].childNodes[0].nodeValue != '0'){
            result += '<input type="button" class="simple_button" id="viewDetail'+i+'" value="'+seeDetail+'" onclick="showDetails('+i+',\'p\',\''+arrayToString(treeShow)+'\')" />'+
        '<input type="button" class="simple_button" id="showOnMap'+i+'" value="'+seeOnMap+'" onclick="showPoints('+i+',\'p\')" />'+
        '<div id="p'+i+'detail"></div><div id="p'+i+'map"></div></div>';
        }
        else{
            result += '<div id="p'+i+'detail"></div><div id="p'+i+'map"></div></div>';
        }
    }
    for(var j = 0;j<byIndicator.length;j++){
        divIds.push('i'+j);
        result += '<div id="i'+j+'" class="detailed_results">'+
        '<h3>'+treeShow[j]+'</h3>'+
        '<p>'+byIndicator[j].childNodes[0].nodeValue+' especies que cumplen algun polígono (FIXME)</p>';
        if(byIndicator[j].childNodes[0].nodeValue != '0'){
            result += '<input type="button" class="simple_button" id="viewDetail'+i+'" value="'+seeDetail+'" onclick="showDetails('+j+',\'i\',\''+arrayToString(layersShow)+'\')" />'+
        '<input type="button" class="simple_button" id="showOnMap'+i+'" value="'+seeOnMap+'" onclick="showPoints('+j+',\'i\')" />'+
        '<div id="i'+j+'detail"></div><div id="i'+j+'map"></div></div>';
        }
        else{
            result += '<div id="i'+j+'detail"></div><div id="i'+j+'map"></div></div>';
        }
    }
    result+='<input type="submit" class="new_search_button" id="newSearch" value="'+newSearch+'"/><br><br><br>';
    return result;
}

/**
 * This function creates the general report, so, return an html string
 * based on the search criteria and the general count of matches
 */
function createReportHeader(criteria,total){
    var result = '<div id="reportHeader">'+
    '<h3>'+searchCriteria+'</h3>'+criteria+
    '<h3>'+total+' '+speciesMatches+'</h3>'+
    '<input type="button" class="simple_button" id="viewDetail0" value="'+seeDetail+'" onclick="showDetailsFromHiddenData(\'viewDetail0\')" />'+
    '<input type="button" class="simple_button" id="showOnMap0" value="'+seeOnMap+'" onclick="showPointFromHiddenData(\'showOnMap0\')" />'+
    '<input type="submit" class="new_search_button" id="newSearch" value="'+newSearch+'" /></div>'+
    '<div id="s0map"></div>';
    return result;
}

/**
 * To draw the specimen points into the map
 */
function showPointFromHiddenData(inputId){
    //Show loading
    YAHOO.example.container.wait.show();
    //Getting the query parameters
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    //Add div to show the map
    document.getElementById('s0map').innerHTML = '<div id="map"></div>';
    //Show the map
    map.render('map');
    //Drowing the points
    showSpecimenPoints(layers,taxa,indi);
    //Change button title
    changeInputText(inputId,hideMap);
    //Change button acction
    goToHidePointsFromHidden('s0map',inputId);
}
// Changes the input (button) action to hide points
function goToHidePointsFromHidden(divId,inputId){
   document.getElementById(inputId).setAttribute( "onclick",
   "hidePointFromHiddenData('"+divId+"','"+inputId+"');");
}

/**
 * To hide the map in simple detail
 */
function hidePointFromHiddenData(divId,inputId){
    //Hide the map
    document.getElementById(divId).innerHTML = '';
    //Change button title
    changeInputText(inputId,seeOnMap);
    //Change button  acction
    goToShowPointsFromHidden(inputId);
}
// Changes the input (button) action to show points
function goToShowPointsFromHidden(inputId){
   document.getElementById(inputId).setAttribute( "onclick",
   "showPointFromHiddenData('"+inputId+"');");
}

/**
 * To get a detailed report of the query when there is one or two parameters
 * Return a List of specimens that match with the criteria
 */
function showDetailsFromHiddenData(inputId){
    //Show loading
    YAHOO.example.container.wait.show();
    //Getting the query parameters
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    var lToShow = document.getElementById('hidLayersToShow').value;
    //Showing the details
    viewSimpleDetail(layers,taxa,indi,lToShow);
    //Change button title
    changeInputText(inputId,hideDetail);
    //Change button acction
    goToHideDetailFromHidden('detailedResults',inputId);
}
// Changes the input (button) action to hide points
function goToHideDetailFromHidden(divId,inputId){
   document.getElementById(inputId).setAttribute( "onclick",
   "hideDetailFromHiddenData('"+divId+"','"+inputId+"');");
}

/**
 * To hide the map in simple detail
 */
function hideDetailFromHiddenData(divId,inputId){
    //Hide the detailed table
    document.getElementById(divId).innerHTML = '';
    //Change button title
    changeInputText(inputId,seeDetail);
    //Change button  acction
    goToShowDetailFromHidden(inputId);
}
// Changes the input (button) action to show points
function goToShowDetailFromHidden(inputId){
   document.getElementById(inputId).setAttribute( "onclick",
   "showDetailsFromHiddenData('"+inputId+"');");
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
        indicateCurrent('p'+id,'map');
        //Show map
        map.render('map');
        //Drowing the points
        showSpecimenPoints(layersArray[id]+'|',taxa,indi);        
    }
    else{
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var indiArray = indi.split('|');
        //Indicate de current selected
        indicateCurrent('i'+id,'map');
        //Show map
        map.render('map');
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
    replaceVectorLayer();
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    if(type=='p'){
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var layersArray = layers.split('|');
        //Indicate de current selected
        indicateCurrent('p'+id,'detail');
        //Drowing the points
        detailedTable(layersArray[id]+'|',taxa,indi,toShow,type,id);
    }
    else{
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var indiArray = indi.split('|');
        //Indicate de current selected
        indicateCurrent('i'+id,'detail');
        //Drowing the points
        detailedTable(layers,taxa,indiArray[id]+'|',toShow,type,id);
    }
}

/**
 * Changes the input (button) title
 * Generic method
 */
function changeInputText(inputId,text){
    document.getElementById(inputId).value = text;
}

/**
 * Indicates de current selected <div> from resultsPanel
 * type could be map or detail
 */
function indicateCurrent(id,type){
    if(type=='map'){ //Display on map
        for(var i = 0;i<divIds.length;i++){
            if(id==divIds[i]){ //Indicate
                document.getElementById(divIds[i]).className = 'current_Selected';
                document.getElementById(divIds[i]+'map').innerHTML = '<div id="map"></div>';
            }
            else{ //No indicate
                document.getElementById(divIds[i]).className = 'detailed_results';
                document.getElementById(divIds[i]+'map').innerHTML = "";
            }
        }
    }
    else{ //Show detail
        for(var j = 0;j<divIds.length;j++){
            if(id==divIds[j]){ //Indicate
                document.getElementById(divIds[j]).className = 'current_Selected';
            }
            else{ //No indicate
                document.getElementById(divIds[j]).className = 'detailed_results';
            }
        }
    }
}
function getDivParameter(id,rest){
    var result = new Array();
    result.push(id+rest)
    return result;
}

//Array to string
function arrayToString(array){
    var result = '';
    for(var i = 0 ;i<array.length ;i++){
        result += array[i]+"|";
    }
    return result;
}