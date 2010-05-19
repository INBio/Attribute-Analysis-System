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
 * To obtain a detailed table (matrix) between species and (indicators or polygons)
 * toShow is an Array
 */
function detailedTable(selectedLayers,selectedTaxa,selectedIndicators,toShow,type,id)  {

    //Prepare URL for XHR request:
    var sUrl = "/ait-web/ajax/getTable?l="+selectedLayers+"&tx="+selectedTaxa+"&i="+selectedIndicators+"&t="+type;

    //Prepare callback object
    var callback = {

        //If XHR call is successful
        success: function(oResponse) {
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //Get the list of species (The names)
            var species = xmlDoc.getElementsByTagName("species");
            //Get the matrix rows
            var rows = xmlDoc.getElementsByTagName("row");
            var toShowArray = toShow.split('|');
            //HTML result
            var result = '<table class="contacts" cellspacing="0"><tr><th class="contactDept"> ---- </th>';
            for(var k = 0 ;k<toShowArray.length;k++){ //Headings row
                if(toShowArray[k] != ""){
                    result += '<th class="contactDept"> '+toShowArray[k]+' </th>';
                }
            }
            result += '</tr>';
            for(var i = 0;i<rows.length;i++){ //Loop over rows
                result += '<tr><th class="contactDept"> '+species[i].childNodes[0].nodeValue+' </th>';
                var columns = rows[i].getElementsByTagName("column");
                for(var j = 0;j<columns.length;j++){
                    result += '<td class="contact"> '+columns[j].childNodes[0].nodeValue+' </td>';
                }
                result += '</tr>';
            }
            result += '</table>';

            document.getElementById(type+id).innerHTML += result;
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