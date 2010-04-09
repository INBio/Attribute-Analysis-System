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
            //Get total count data
            var totalCount = xmlDoc.getElementsByTagName("data")[0].childNodes[0].nodeValue;
            //Get the list of polygons
            var polygonsList = xmlDoc.getElementsByTagName("polygon");           

            //Show general result and the search criteria
            var criteria = "<b>Geográficos: </b>";
            for(var i = 0;i<layersShow.length;i++){
                criteria += layersShow[i]+"   ";
            }
            criteria += "<br><b>Taxonómicos: </b>";
            for(var j = 0;j<taxonsShow.length;j++){
                criteria += taxonsShow[j]+"   ";
            }
            criteria += "<br><b>Indicadores: </b>";
            for(var k = 0;k<treeShow.length;k++){
                criteria += treeShow[k]+"   ";
            }
            var resultHTML = "<p style=\"font-weight:bold;font-size:14px;\">Resultados generales</p>"+
            "<table class=\"contacts\" cellspacing=\"0\">"+
            "<tr><th class=\"contactDept\" >Criterios de búsqueda</th>"+
            "<th  class=\"contactDept\" >Total de coincidencias</th></tr><tr>"+
            "<td class=\"contactJusti\" width=\"70%\">"+criteria+"</td>"+
            "<td class=\"contact\" width=\"30%\">"+totalCount+"</td>"+
            "</tr></table>";

            //Show the result in terms of polygons
            if(polygonsList.length>1){
                resultHTML += "<p style=\"font-weight:bold;font-size:14px;\">Resultados por polígono</p>"+
                    "<table class=\"contacts\" cellspacing=\"0\">"+
                    "<tr><th class=\"contactDept\" >Criterio</th>"+
                    "<th  class=\"contactDept\" >Coincidencias</th></tr>";
                for(var l = 0;l<polygonsList.length;l++){
                    var aux = polygonsList[l].getElementsByTagName("data")[0].childNodes[0].nodeValue;
                    resultHTML += "<tr><td class=\"contact\" width=\"70%\">"+layersShow[l]+"</td>"+
                    "<td class=\"contact\" width=\"30%\">"+aux+"</td></tr>";
                }
                resultHTML += "</table>";
            }

            document.getElementById('resultsPanel').innerHTML = resultHTML;
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