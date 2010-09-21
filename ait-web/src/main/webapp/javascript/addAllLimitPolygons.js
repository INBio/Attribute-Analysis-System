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
 * Method called when the user needs to select all the polygons from a specifc limit layer
 * For example: Add all provinces of Costa Rica ...
 */
function addAllLimitPolygons(layer)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/selectAll?layer=" + layer;

    //Prepare our callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;           
            //polygons
            var rootChildNodes = xmlDoc.getElementsByTagName("polygon");
            //Loop over polygons
            for(var i=0; i< rootChildNodes.length; i++){
                 var basicElement = rootChildNodes[i];  //node
                 var id =  basicElement.getElementsByTagName("id")[0].childNodes[0].nodeValue; //id
                 var name =  basicElement.getElementsByTagName("name")[0].childNodes[0].nodeValue; //name
                 //Add the polygon to the selected criteria div
                 addLayerLimitParam('capa.'+id,'schema:'+layer,name);
            }
            //Close loadinf panel
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