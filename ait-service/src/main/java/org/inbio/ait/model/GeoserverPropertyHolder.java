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

package org.inbio.ait.model;

/**
 *
 * @author esmata
 */
public class GeoserverPropertyHolder {

    //Constructor
    public GeoserverPropertyHolder(){
    }

    //Attributes
    private String serverIpAddress;
    private String layersWorkSpace;

    /**
     * @return the serverIpAddress
     */
    public String getServerIpAddress() {
        return serverIpAddress;
    }

    /**
     * @param serverIpAddress the serverIpAddress to set
     */
    public void setServerIpAddress(String serverIpAddress) {
        this.serverIpAddress = serverIpAddress;
    }

    /**
     * @return the layersWorkSpace
     */
    public String getLayersWorkSpace() {
        return layersWorkSpace;
    }

    /**
     * @param layersWorkSpace the layersWorkSpace to set
     */
    public void setLayersWorkSpace(String layersWorkSpace) {
        this.layersWorkSpace = layersWorkSpace;
    }

}
