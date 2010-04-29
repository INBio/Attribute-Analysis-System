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
function viewSimpleDetail(selectedLayers,selectedTaxa,selectedIndicators,lToShow)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/getSpecies?layers="+selectedLayers+"&taxons="+selectedTaxa+"&indi="+selectedIndicators;

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

            if(polygons.length==0){ //If there are not polygons
                var dwcList = '';
                for(var i = 0;i<species.length;i++){
                    var node = species[i];
                    dwcList += '- '+node.childNodes[0].nodeValue+'<br>';
                }
                // Show the result
                document.getElementById('detailedResults').innerHTML = '<p style="text-align:center;"><b>'+
                    speciesList+'</b></p>'+dwcList;
                document.getElementById("detailedResults").className = "detailedResults";
            }
            else{
                //Show the result in terms of polygons
                var resultHTML = '<table class="contacts" cellspacing="0">'+
                    '<tr><th class="contactDept" >'+criteriaText+'</th>'+
                    '<th  class="contactDept" >'+speciesText+'</th></tr>';
                for(var l = 0;l<polygons.length;l++){
                    var spList = polygons[l].getElementsByTagName("sp");
                    var aux = '';
                    for(var j = 0;j<spList.length;j++){
                        aux += '- '+spList[j].childNodes[0].nodeValue+'<br>';
                    }
                    resultHTML += '<tr><td class="contact" width="50%">'+layersToShow[l]+'</td>'+
                    '<td class="contact" width="50%">'+aux+'</td></tr>';
                }
                resultHTML += '</table>';
                // Show the result
                document.getElementById('detailedResults').innerHTML = '<p style="text-align:center;"><b>'+
                    speciesList+'</b></p>'+resultHTML;
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