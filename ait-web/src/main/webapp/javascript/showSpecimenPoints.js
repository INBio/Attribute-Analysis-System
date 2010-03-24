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
function showSpecimenPoints(selectedLayers,selectedTaxa,selectedIndicators)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/getPoints?layers="+selectedLayers+"&taxons="+selectedTaxa+"&indi="+selectedIndicators;

    //Prepare callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //Root element -> response
            var response = oResponse.responseXML.documentElement;
            //Child node <specimens></specimens>
            var specimensNode = response.childNodes[0];
            //Get the list of specimens
            var specimenList = specimensNode.childNodes;

            for(var i = 0;i<specimenList.length;i++){
                var node = specimenList[i];
                attributes = createAttrib(node.childNodes[0].textContent);
                addPoint(node.childNodes[1].textContent, node.childNodes[2].textContent,attributes);
            }
        }, 

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }

        //Timeout -- if more than 7 seconds go by, we'll abort
        //timeout: 7000
    };

    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}