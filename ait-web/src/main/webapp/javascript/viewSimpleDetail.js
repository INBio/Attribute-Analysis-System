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
function viewSimpleDetail(selectedLayers,selectedTaxa,selectedIndicators,lToShow,selectedLimit,limitToshow)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/getSpecies?layers="+selectedLayers+
        "&taxons="+selectedTaxa+"&indi="+selectedIndicators+"&limit="+selectedLimit;

    //Prepare callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //Get the list of species (not by polygon)
            var species = xmlDoc.getElementsByTagName("species");
            //Get the list of polygons
            var polygons = xmlDoc.getElementsByTagName("polygon");
            //Layers to show
            var layersToShow = lToShow.split("|");
            //Layers limit to show
            var lLimitToShow = limitToshow.split("|");
            //String use to show the percentage values
            var lAsText = '';
            for(var lt = 0;lt<lLimitToShow.length;lt++){
                if(lLimitToShow[lt] != ''){
                    if(lt==lLimitToShow.length-1){ //last element
                        lAsText+=lLimitToShow[lt]+'.';
                    }
                    else{
                        lAsText+=lLimitToShow[lt]+', ';
                    }
                }
            }
            //If there are not polygons
            if(polygons.length==0){ 
                var dwcList = '';
                for(var i = 0;i<species.length;i++){
                    var node = species[i];
                    dwcList += '<a class="criteria">'+node.childNodes[0].nodeValue+'</a><br>';
                }
                // Show the result
                document.getElementById('detailedResults').innerHTML = '<p style="text-align:center;"></p>'+dwcList+'<br><br>';
                document.getElementById("detailedResults").className = "detailedResults";
            }
            //Show the result in terms of polygons
            else{                
                var resultHTML = '<table class="contacts" cellspacing="0">'+
                    '<tr><th class="contactDept" >'+criteriaText+'</th>'+
                    '<th  class="contactDept" >'+speciesText+'</th></tr>';
                for(var l = 0;l<polygons.length;l++){
                    var spList = polygons[l].getElementsByTagName("sp");
                    var absValue = polygons[l].getElementsByTagName("abs")[0].childNodes[0].nodeValue;
                    var perValue = polygons[l].getElementsByTagName("per")[0].childNodes[0].nodeValue;
                    var auxList = ''; //Contains the list of scientific names by polygon
                    for(var j = 0;j<spList.length;j++){
                        auxList += '<a class="criteria">'+spList[j].childNodes[0].nodeValue+'</a><br>';
                    }
                    //If show percentages is needed
                    var criteriaValue = '<h2 style="margin:0;text-align:center">'+layersToShow[l]+'</h2>';
                    if(lAsText != '' && limitToshow[0] != ''){
                        criteriaValue += '<h3 style="margin:0;text-align:justify">'+absValue+' '+speciesMatches+'</h3>'+
                        '<h4 style="margin:0;text-align:justify">'+percentText1+' '+perValue+'% '+percentText2S+' '+lAsText+'</h4>';
                        resultHTML += '<tr><td class="contact" width="60%">'+criteriaValue+'</td>'+
                        '<td class="contact" width="40%">'+auxList+'</td></tr>';
                    }
                    //Not percentages to show
                    else{
                        criteriaValue += '<h3 style="margin:0;text-align:justify">'+absValue+' '+speciesMatches+'</h3>';
                        resultHTML += '<tr><td class="contact" width="60%">'+criteriaValue+'</td>'+
                        '<td class="contact" width="40%">'+auxList+'</td></tr>';
                    }
                }
                resultHTML += '</table>';
                // Show the result
                document.getElementById('detailedResults').innerHTML = '<p style="text-align:center;"></p>'+resultHTML+'<br><br>';
                document.getElementById("detailedResults").className = "detailedResults";
            }

            YAHOO.example.container.wait.hide();
        }, 

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }
    };

    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}