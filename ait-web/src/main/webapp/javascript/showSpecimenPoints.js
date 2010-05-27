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
            //Destroy map control for specimens pop ups
            replaceMapControl();
            //Clear map popups
            clearPopups();
            //Clear vector layer
            replaceVectorLayer();
            //Root element -> response
            var xmlDoc = oResponse.responseXML.documentElement;
            //Get the list of specimens
            var specimenList = xmlDoc.getElementsByTagName("specimen");
            //Add all the specimen point
            for(var i = 0;i<specimenList.length;i++){
                var node = specimenList[i];
                var catalog = node.getElementsByTagName("catalog")[0].childNodes[0].nodeValue;
                var latitude = node.getElementsByTagName("latitude")[0].childNodes[0].nodeValue;
                var longitude = node.getElementsByTagName("longitude")[0].childNodes[0].nodeValue;
                var scientificname = node.getElementsByTagName("scientificname")[0].childNodes[0].nodeValue;
                attributes = createAttrib(scientificname,latitude,longitude,catalog);
                addPoint(longitude,latitude,attributes);
            }

            //Set the new control for specimens pop ups
            selectControl = new OpenLayers.Control.SelectFeature(vectorLayer,
            {onSelect: onFeatureSelect, onUnselect: onFeatureUnselect});
            map.addControl(selectControl);
            selectControl.activate();

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
function createAttrib(scientificName,latitude,longitude,catalog) {
    attrib = {
        ScientificName: scientificName,
        Latitude: latitude,
        Longitude: longitude,
        Catalog: catalog
    }
    return attrib;
}

/*
 * Deletes the current specimen points
 */
function replaceVectorLayer(){
    //Specimen points Layer
    vectorLayer.destroy();
    vectorLayer = new OpenLayers.Layer.Vector('Specimens');
    vectorLayer.setVisibility(true);
    map.addLayer(vectorLayer);
}

/*
 * Deletes all the current popups on the map
 */
function clearPopups(){
    for (var i=0; i<map.popups.length; i++) {
        map.removePopup(map.popups[i]);
    }
}

/*
 * Destroy map control for specimens pop ups
 */
function replaceMapControl(){
    if(selectControl != null){
        selectControl.destroy();
        selectControl = null;
    }
}

//Event on specimen Popup Close
function onPopupClose(evt) {
    selectControl.unselect(selectedFeature);
}

// Event onFeatureSelect (When especific specimen point was selected)
function onFeatureSelect(feature) {
    selectedFeature = feature;
    popup = new OpenLayers.Popup.FramedCloud("point",
    feature.geometry.getBounds().getCenterLonLat(),
    null,
    "<div style=\"font-size:.8em\">"+
    "<br><b>"+scientificName+": </b>"+feature.attributes.ScientificName+
    "<br><b>"+catalog+": </b>"+feature.attributes.Catalog+
    "<br><b>"+latitude+": </b>"+feature.attributes.Latitude+
    "<br><b>"+longitute+": </b>"+feature.attributes.Longitude+"</div>",
    null, true, onPopupClose);
    feature.popup = popup;
    map.addPopup(popup);
}

//Event onFeatureUnselect
function onFeatureUnselect(feature) {
    map.removePopup(feature.popup);
    feature.popup.destroy();
    feature.popup = null;
}