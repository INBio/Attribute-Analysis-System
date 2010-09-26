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
function executeFinalQuery(selectedLayers,selectedTaxa,selectedIndicators,selectedLimit,
                           layersShow,taxonsShow,treeShow,layersLimitShow)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/finalQuery?layers="+selectedLayers+"&taxons="+selectedTaxa+"&indi="+
        selectedIndicators+"&limit="+selectedLimit;

    //Prepare callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //Get the XML type. 0 means one or two parameters. 1 means 3 parameters
            var type = xmlDoc.getElementsByTagName("type")[0].childNodes[0].nodeValue;

            switch(type){
                case '0': // 0 means one or this two categories (geographical and taxonomical)
                    //Get total count data
                    var total = xmlDoc.getElementsByTagName("total")[0].childNodes[0].nodeValue;
                    //Get total percentage data
                    var totalPer = xmlDoc.getElementsByTagName("totalp")[0].childNodes[0].nodeValue;
                    //Show general result and the search criteria
                    var criteria = "";
                    if(layersShow.length>0){
                        criteria += "<b>"+geographical+" </b>";
                        for(var i = 0;i<layersShow.length;i++){
                            //criteria += layersShow[i]+"   ";
                            criteria += "<a class=\"criteria\">"+layersShow[i]+"</a>";
                        }
                        criteria += "<br>";
                    }
                    if(taxonsShow.length>0){
                        criteria += "<b>"+taxonomic+" </b>";
                        for(var j = 0;j<taxonsShow.length;j++){
                            //criteria += taxonsShow[j]+"   ";
                            criteria += "<a class=\"criteria\">"+taxonsShow[j]+"</a>";
                        }
                        criteria += "<br>";
                    }
                    if(treeShow.length>0){
                        criteria += "<b>"+indicators+" </b>";
                        for(var k = 0;k<treeShow.length;k++){
                            //criteria += treeShow[k]+"   ";
                            criteria += "<a class=\"criteria\">"+treeShow[k]+"</a>";
                        }
                        criteria += "<br>";
                    }
                    var lAsText = '';
                    for(var lt = 0;lt<layersLimitShow.length;lt++){
                        if(lt==layersLimitShow.length-1){ //last element
                            lAsText+=layersLimitShow[lt]+'.';
                        }
                        else{
                            lAsText+=layersLimitShow[lt]+', ';
                        }
                    }
                    //Show the results
                    var resultHTML = createReportHeader(criteria, total,totalPer,lAsText);
                    document.getElementById('resultsPanel').innerHTML = resultHTML;
                    //Close de "Loading image"
                    YAHOO.example.container.wait.hide();
                    //Calling the anchor to positionate the page focus on the results
                    callAnchor('#anchorTop');
                    break;

                case '1': // 1 means 3 different criteria categories
                    //Get total count data (Absulute)
                    var total1 = xmlDoc.getElementsByTagName("total")[0].childNodes[0].nodeValue;
                    //Get total percentage data
                    var totalPer1 = xmlDoc.getElementsByTagName("totalp")[0].childNodes[0].nodeValue;
                    //Show general result and the search criteria
                    var criteria1 = "";
                    if(layersShow.length>0){
                        criteria1 += "<b>"+geographical+" </b>";
                        for(var o = 0;o<layersShow.length;o++){
                            //criteria1 += layersShow[o]+"   ";
                            criteria1 += "<a class=\"criteria\">"+layersShow[o]+"</a>";
                        }
                        criteria1 += "<br>";
                    }
                    if(taxonsShow.length>0){
                        criteria1 += "<b>"+taxonomic+" </b>";
                        for(var p = 0;p<taxonsShow.length;p++){
                            //criteria1 += taxonsShow[p]+"   ";
                            criteria1 += "<a class=\"criteria\">"+taxonsShow[p]+"</a>";
                        }
                        criteria1 += "<br>";
                    }
                    if(treeShow.length>0){
                        criteria1 += "<b>"+indicators+" </b>";
                        for(var q = 0;q<treeShow.length;q++){
                            //criteria1 += treeShow[q]+"   ";
                            criteria1 += "<a class=\"criteria\">"+treeShow[q]+"</a>";
                        }
                        criteria1 += "<br>";
                    }
                    //Variables that contains the results by polygon and by indicator
                    var byPolygon = xmlDoc.getElementsByTagName("bypolygon");
                    var byIndicator = xmlDoc.getElementsByTagName("byindicator");
                    var limitAsText = '';
                    for(var l = 0;l<layersLimitShow.length;l++){
                        if(l==layersLimitShow.length-1){ //last element
                            limitAsText+=layersLimitShow[l]+'.';
                        }
                        else{
                            limitAsText+=layersLimitShow[l]+', ';
                        }
                    }
                    //Show the results
                    var result = createAdvancedHeader(byPolygon,byIndicator,layersShow,
                    treeShow,total1,criteria1,totalPer1,limitAsText);
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
function createAdvancedHeader(byPolygon,byIndicator,layersShow,treeShow,total1,criteria1,totalPer1,limitAsText){
    var result = ''; //Criteria with results
    var others = '<p> '+criteriaWithoutResults+'</p>'; //Criteria without results
    //To manage later the current selected div and action buttons
    manageResultDivs('t0','showOnMapt0',0,'t');
    //Creating general result with all search criteria
    result += '<div id="t0" class="detailed_results">'+
    '<h3>'+searchCriteria+'</h3><div id="rHeader">'+criteria1+"</div>"+
    '<h3>'+total1+' '+speciesMatches+'.</h3>';
    //If is required to show pwrcentages
    if(limitAsText != ''){
        result += '<h4>'+percentText1+' '+totalPer1+'% '+percentText2+' '+limitAsText+'</h4>';
    }
    //If there are results, show to user the options to view details and maps
    if(total1 > 0){
        result += '<input type="button" class="simple_button" id="showOnMapt0" value="'+seeOnMap+'" onclick="showPoints(0,\'t\')" />'+
        '<div id="t0map"></div></div>';
    }    
    //Adding results by polygon
    result += '<p class="resultsTitle"> '+resultsGeo+'</p>';
    for(var i = 0;i<byPolygon.length;i++){
        //To manage the divs ids
        manageResultDivs('p'+i,'showOnMapp'+i,i,'p');
        //Get the node values
        var node = byPolygon[i];
        var absValue = node.getElementsByTagName("abs")[0].childNodes[0].nodeValue;
        var perValue = node.getElementsByTagName("per")[0].childNodes[0].nodeValue;
        //If the detail has results and needs to show percentages
        if(absValue != '0' && limitAsText != ''){
            result += '<div id="p'+i+'" class="detailed_results">'+
            '<h3>'+layersShow[i]+'</h3>'+
            '<p>'+absValue+' '+layerMatches+'. '+percentText1+' '+perValue+'% '+percentText2+' '+limitAsText+'</p>'+
            '<input type="button" class="simple_button" id="viewDetailp'+i+'" value="'+seeDetail+'" onclick="showDetails('+i+',\'p\',\''+arrayToString(treeShow)+'\')" />'+
            '<input type="button" class="simple_button" id="showOnMapp'+i+'" value="'+seeOnMap+'" onclick="showPoints('+i+',\'p\')" />'+
            '<input type="button" class="simple_button" id="exportPoint'+i+'" value="'+occurrences+'" onclick="exportPoints('+i+',\'p\')" />'+
            '<div id="p'+i+'detail"></div><div id="p'+i+'map"></div></div>';
        }
        //If the detail has results and doesn't need to show percentages
        if(absValue != '0' && limitAsText == ''){
            result += '<div id="p'+i+'" class="detailed_results">'+
            '<h3>'+layersShow[i]+'</h3>'+
            '<p>'+absValue+' '+layerMatches+'</p>'+
            '<input type="button" class="simple_button" id="viewDetailp'+i+'" value="'+seeDetail+'" onclick="showDetails('+i+',\'p\',\''+arrayToString(treeShow)+'\')" />'+
            '<input type="button" class="simple_button" id="showOnMapp'+i+'" value="'+seeOnMap+'" onclick="showPoints('+i+',\'p\')" />'+
            '<input type="button" class="simple_button" id="exportPoint'+i+'" value="'+occurrences+'" onclick="exportPoints('+i+',\'p\')" />'+
            '<div id="p'+i+'detail"></div><div id="p'+i+'map"></div></div>';
        }
        else{ //If the detail doesn't have results
            others += '<div id="p'+i+'" class="detailed_results">'+
            '<h3>'+layersShow[i]+'</h3>'+
            '<div id="p'+i+'detail"></div><div id="p'+i+'map"></div></div>';
        }
    }    
    //Adding results by indicators
    result += '<p class="resultsTitle"> '+resultsIndi+'</p>';
    for(var j = 0;j<byIndicator.length;j++){
        //To manage the divs ids
        manageResultDivs('i'+j,'showOnMapi'+j,j,'i')
        //Get the node values
        var nodeI = byIndicator[j];
        var absValueI = nodeI.getElementsByTagName("abs")[0].childNodes[0].nodeValue;
        var perValueI = nodeI.getElementsByTagName("per")[0].childNodes[0].nodeValue;
        //If the detail has results and needs to show percentages
        if(absValueI != '0' && limitAsText != ''){
            result += '<div id="i'+j+'" class="detailed_results">'+
            '<h3>'+treeShow[j]+'</h3>'+
            '<p>'+absValueI+' '+layerMatches+'. '+percentText1+' '+perValueI+'% '+percentText2+' '+limitAsText+'</p>'+
            '<input type="button" class="simple_button" id="viewDetaili'+j+'" value="'+seeDetail+'" onclick="showDetails('+j+',\'i\',\''+arrayToString(layersShow)+'\')" />'+
            '<input type="button" class="simple_button" id="showOnMapi'+j+'" value="'+seeOnMap+'" onclick="showPoints('+j+',\'i\')" />'+
            '<input type="button" class="simple_button" id="exportPointi'+j+'" value="'+occurrences+'" onclick="exportPoints('+j+',\'i\')" />'+
            '<div id="i'+j+'detail"></div><div id="i'+j+'map"></div></div>';
        }
        //If the detail has results and doesn't need to show percentages
        if(absValueI != '0' && limitAsText == ''){
            result += '<div id="i'+j+'" class="detailed_results">'+
            '<h3>'+treeShow[j]+'</h3>'+
            '<p>'+absValueI+' '+indicatorMatches+'</p>'+
            '<input type="button" class="simple_button" id="viewDetaili'+j+'" value="'+seeDetail+'" onclick="showDetails('+j+',\'i\',\''+arrayToString(layersShow)+'\')" />'+
            '<input type="button" class="simple_button" id="showOnMapi'+j+'" value="'+seeOnMap+'" onclick="showPoints('+j+',\'i\')" />'+
            '<input type="button" class="simple_button" id="exportPointi'+j+'" value="'+occurrences+'" onclick="exportPoints('+j+',\'i\')" />'+
            '<div id="i'+j+'detail"></div><div id="i'+j+'map"></div></div>';
        }
        else{ //If the detail doesn't have results
            others += '<div id="i'+j+'" class="detailed_results">'+
            '<h3>'+treeShow[j]+'</h3>'+
            '<div id="i'+j+'detail"></div><div id="i'+j+'map"></div></div>';
        }
    }
    result+='<input type="submit" class="new_search_button" id="newSearch" value="'+newSearch+'"/>'+others+'<br>';
    return result;
}

function manageResultDivs(divId,butonId,index,type){
    divIds.push(divId);
    buttonIds.push(butonId);
    ids.push(index);
    types.push(type);
}

/**
 * This function creates the general report, so, return an html string
 * based on the search criteria and the general count of matches
 */
function createReportHeader(criteria,total,totalPer,limitAsText){
    var result = '<div id="reportHeader">';
    if(total != '0' && limitAsText != ''){
        result += '<h3>'+searchCriteria+'</h3><div id="rHeader">'+criteria+"</div>"+
        '<h3>'+total+' '+speciesMatches+'</h3>'+
        '<h4>'+percentText1+' '+totalPer+'% '+percentText2S+' '+limitAsText+'</h4>'+
        '<input type="button" class="simple_button" id="viewDetail0" value="'+seeDetail+'" onclick="showDetailsFromHiddenData(\'viewDetail0\')" />'+
        '<input type="button" class="simple_button" id="showOnMap0" value="'+seeOnMap+'" onclick="showPointFromHiddenData(\'showOnMap0\')" />'+
        '<input type="button" class="simple_button" id="exportPoint0" value="'+occurrences+'" onclick="exportPointsFromHiddenData()" />'+
        '<input type="submit" class="new_search_button" id="newSearch" value="'+newSearch+'" /></div>'+
        '<div id="s0map"></div>';
        return result;
    }
    //If the detail has results and doesn't need to show percentages
    if(total != '0' && limitAsText == ''){
        result += '<h3>'+searchCriteria+'</h3><div id="rHeader">'+criteria+"</div>"+
        '<h3>'+total+' '+speciesMatches+'</h3>'+
        '<input type="button" class="simple_button" id="viewDetail0" value="'+seeDetail+'" onclick="showDetailsFromHiddenData(\'viewDetail0\')" />'+
        '<input type="button" class="simple_button" id="showOnMap0" value="'+seeOnMap+'" onclick="showPointFromHiddenData(\'showOnMap0\')" />'+
        '<input type="button" class="simple_button" id="exportPoint0" value="'+occurrences+'" onclick="exportPointsFromHiddenData()" />'+
        '<input type="submit" class="new_search_button" id="newSearch" value="'+newSearch+'" /></div>'+
        '<div id="s0map"></div>';
        return result;
    }
    else{ //If the detail doesn't have results
        result += '<h3>'+searchCriteria+'</h3><div id="rHeader">'+criteria+"</div>"+
        '<h3>'+total+' '+speciesMatches+'</h3>'+
        '<input type="submit" class="new_search_button" id="newSearch" value="'+newSearch+'" /></div>'+
        '<div id="s0map"></div>';
        return result;
    }
}

/**
 * To show the specimen points info on a detailed table
 */
function exportPointsFromHiddenData(){
    //Show loading
    YAHOO.example.container.wait.show();
    //Getting the query parameters
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    //Export points info
    exportSpecimenPoints(layers,taxa,indi);
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
    document.getElementById('map').appendChild(map.viewPortDiv); //Adding the new viewPort parent
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
   /*document.getElementById(inputId).setAttribute( "onclick",
   "hidePointFromHiddenData('"+divId+"','"+inputId+"');");*/
   document.getElementById(inputId).onclick = function(){
       hidePointFromHiddenData(divId,inputId);
   };
}

/**
 * To hide the map in simple detail
 */
function hidePointFromHiddenData(divId,inputId){
    //Hide the map
    document.getElementById(divId).innerHTML = "";
    //Change button title
    changeInputText(inputId,seeOnMap);
    //Change button  acction
    goToShowPointsFromHidden(inputId);
}
// Changes the input (button) action to show points
function goToShowPointsFromHidden(inputId){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "showPointFromHiddenData('"+inputId+"');");*/
   document.getElementById(inputId).onclick = function(){
       showPointFromHiddenData(inputId);
   };
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
    var limit = document.getElementById('hiddenLimitLayers').value;
    var limitToShow = document.getElementById('hiddenLimitToShow').value;
    //Showing the details
    viewSimpleDetail(layers,taxa,indi,lToShow,limit,limitToShow);
    //Change button title
    changeInputText(inputId,hideDetail);
    //Change button acction
    goToHideDetailFromHidden('detailedResults',inputId);
}
// Changes the input (button) action to hide details
function goToHideDetailFromHidden(divId,inputId){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "hideDetailFromHiddenData('"+divId+"','"+inputId+"');");*/
   document.getElementById(inputId).onclick = function(){
       hideDetailFromHiddenData(divId,inputId);
   };
}

/**
 * To hide details in simple detail
 */
function hideDetailFromHiddenData(divId,inputId){
    //Hide the detailed table
    document.getElementById(divId).innerHTML = "";
    //Change button title
    changeInputText(inputId,seeDetail);
    //Change button  acction
    goToShowDetailFromHidden(inputId);
}
// Changes the input (button) action to show detail
function goToShowDetailFromHidden(inputId){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "showDetailsFromHiddenData('"+inputId+"');");*/
   document.getElementById(inputId).onclick = function(){
       showDetailsFromHiddenData(inputId);
   };
}

/**
 * To export the specimen points on csv format
 * id = polygon id or indicator id
 * type p = polygon or i = indicator
 */
function exportPoints(id,type){
    var layers = document.getElementById('hiddenLayers').value;
    var taxa = document.getElementById('hiddenTaxa').value;
    var indi = document.getElementById('hiddenIndicators').value;
    if(type=='t'){ //All search criteria
        //Show loading
        YAHOO.example.container.wait.show();
        //Indicate de current selected
        indicateCurrent('t'+id,'detail');
        //Export points info
        exportSpecimenPoints(layers,taxa,indi);
    }
    else if(type=='p'){ //By polygon
        //Show loading
        YAHOO.example.container.wait.show();
        //Getting the query parameters
        var layersArray = layers.split('|');
        //Indicate de current selected
        indicateCurrent('p'+id,'detail');
        //Export points info
        exportSpecimenPoints(layersArray[id]+'|',taxa,indi);
    }
        else{ //By indicator
            //Show loading
            YAHOO.example.container.wait.show();
            //Getting the query parameters
            var indiArray = indi.split('|');
            //Indicate de current selected
            indicateCurrent('i'+id,'detail');
            //Export points info
            exportSpecimenPoints(layers,taxa,indiArray[id]+'|');
        }
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
    if(type=='t'){ //All search criteria
        //Show loading
        YAHOO.example.container.wait.show();
        //Indicate de current selected
        indicateCurrent('t'+id,'map');
        //Show map
        map.render('map');
        //Drowing the points
        showSpecimenPoints(layers,taxa,indi);
        //Change button title
        changeInputText('showOnMapt'+id,hideMap); //button,text
        //Change button acction
        goToHideMap(type+id+'map','showOnMapt'+id,id,type); //div,button
    }
    else if(type=='p'){ //By polygon
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
        //Change button title
        changeInputText('showOnMapp'+id,hideMap); //button,text
        //Change button acction
        goToHideMap(type+id+'map','showOnMapp'+id,id,type); //div,button
    }
        else{ //By indicator
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
            //Change button title
            changeInputText('showOnMapi'+id,hideMap); //button,text
            //Change button acction
            goToHideMap(type+id+'map','showOnMapi'+id,id,type); //div,button,id,type
        }
}
// Changes the input (button) action to hide map
function goToHideMap(divId,inputId,id,type){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "hidePoints('"+divId+"','"+inputId+"','"+id+"','"+type+"');");*/
   document.getElementById(inputId).onclick = function(){
       hidePoints(divId,inputId,id,type);
   };
}

/**
 * To hide the map in multiple detailed results
 */
function hidePoints(divId,inputId,id,type){
    //Hide the map
    document.getElementById(divId).innerHTML = "";
    //Change button title
    changeInputText(inputId,seeOnMap);
    //Change button  acction
    goToShowMap(inputId,id,type);
}
// Changes the input (button) action to show map
function goToShowMap(inputId,id,type){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "showPoints('"+id+"','"+type+"');");*/
   document.getElementById(inputId).onclick = function(){
       showPoints(id,type);
   };
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
        indicateCurrent('p'+id,'detail');
        //Drowing the points
        detailedTable(layersArray[id]+'|',taxa,indi,toShow,type,id);
        //Change button title
        changeInputText('viewDetailp'+id,hideDetail); //button,text
        //Change button acction
        goToHideDetail(type+id+'detail','viewDetailp'+id,id,type,toShow); //div,button
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
        //Change button title
        changeInputText('viewDetaili'+id,hideDetail); //button,text
        //Change button acction
        goToHideDetail(type+id+'detail','viewDetaili'+id,id,type,toShow); //div,button
    }
}
// Changes the input (button) action to hide detail
function goToHideDetail(divId,inputId,id,type,toShow){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "hideDetails('"+divId+"','"+inputId+"','"+id+"','"+type+"','"+toShow+"');");*/
   document.getElementById(inputId).onclick = function(){
       hideDetails(divId,inputId,id,type,toShow);
   };
}

/**
 * To hide a detail in multiple detailed results
 */
function hideDetails(divId,inputId,id,type,toShow){
    //Hide the detail
    document.getElementById(divId).innerHTML = "";
    //Change button title
    changeInputText(inputId,seeDetail);
    //Change button  acction
    goToShowDetail(inputId,id,type,toShow);
}
// Changes the input (button) action to show detail
function goToShowDetail(inputId,id,type,toShow){
   /*document.getElementById(inputId).setAttribute( "onclick",
   "showDetails('"+id+"','"+type+"','"+toShow+"');");*/
   document.getElementById(inputId).onclick = function(){
       showDetails(id,type,toShow);
   };
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
 * id = div identificator
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
                if(document.getElementById(buttonIds[i]) != null){
                    //Change button title
                    changeInputText(buttonIds[i],seeOnMap);
                    //Change button  acction
                    goToShowMap(buttonIds[i],ids[i],types[i]);
                }
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