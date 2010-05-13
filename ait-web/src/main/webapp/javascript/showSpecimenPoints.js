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
            //Clear vector layer
            replaceVectorLayer();
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //Get the list of specimens
            var specimenList = xmlDoc.getElementsByTagName("specimen");

            for(var i = 0;i<specimenList.length;i++){
                var node = specimenList[i];
                attributes = createAttrib(node.getElementsByTagName("scientificname")[0].childNodes[0].nodeValue);
                addPoint(node.getElementsByTagName("longitude")[0].childNodes[0].nodeValue,
                node.getElementsByTagName("latitude")[0].childNodes[0].nodeValue,attributes);
            }
            YAHOO.example.container.wait.hide();
            callAnchor('#anchorTop');
        }, 

        //If XHR call is not successful
        failure: function(oResponse) {
            YAHOO.log("Failed to process XHR transaction.", "info", "example");
        }
    };

    //Make our XHR call using Connection Manager's
    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
}

/*
 * This function adds a new point to the specimens Layer
 */
function addPoint(x, y, attribute) {
    var feature = new OpenLayers.Feature.Vector(
    new OpenLayers.Geometry.Point(x, y), attribute);
    vectorLayer.addFeatures(feature);
}

/*
 * Creates a new atributes array for each speciemns point
 */
function createAttrib(scientificName) {
    attrib = {
        sScientificName: scientificName
    }
    return attrib;
}

/*
 * Deletes the current specimen points
 */
function replaceVectorLayer(){
    vectorLayer.destroy();
    vectorLayer = new OpenLayers.Layer.Vector('Specimens');
    vectorLayer.setVisibility(true);
    map.addLayer(vectorLayer);
}