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

/* Internacionalization variables */
var layerText;
var loadingText;
var selectDD;

/*
 * Create a drop down to specified the current layer
 */
function createDDLayers(){
    var dropdown = "<p style=\"margin:1px\"><a> "+layerText+": </a></p>";
    dropdown += "<select name=ddLayer class=\"componentSize\" onchange='onChangeLayer(this.form.ddLayer);'>";
    //Setting drop down options
    for(var i=0;i<layersList.length;i++){
        dropdown+= "<option>"+layersList[i][1]+"</option>";
    }
    dropdown+= "</select>";
    document.getElementById('currentLayer').innerHTML = dropdown;
}