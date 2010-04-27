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
function viewSimpleDetail(selectedLayers,selectedTaxa,selectedIndicators)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/getSpecies?layers="+selectedLayers+"&taxons="+selectedTaxa+"&indi="+selectedIndicators;

    //Prepare callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //Get the list of specimens
            var species = xmlDoc.getElementsByTagName("species");

            var dwcList = '';
            for(var i = 0;i<species.length;i++){
                var node = species[i];
                dwcList += (i+1)+') '+node.getElementsByTagName
                ("scientificname")[0].childNodes[0].nodeValue+'<br>';
            }

            // Show the result
            document.getElementById('detailedResults').innerHTML = '<p>'+speciesList+'</p>'+dwcList;

            /*YAHOO.example.container.detailedResults = new YAHOO.widget.Panel
            ("detailedResults", { width:"800px", visible:true, draggable:false, close:false} );
            YAHOO.example.container.detailedResults.setHeader(speciesList);
            YAHOO.example.container.detailedResults.setBody(dwcList);
            YAHOO.example.container.detailedResults.render();*/

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