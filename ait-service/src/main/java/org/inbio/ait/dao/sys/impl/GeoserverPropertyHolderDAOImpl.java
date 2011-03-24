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

package org.inbio.ait.dao.sys.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.inbio.ait.dao.sys.GeoserverPropertyHolderDAO;
import org.inbio.ait.model.GeoserverPropertyHolder;

/**
 *
 * @author esmata
 */
public class GeoserverPropertyHolderDAOImpl implements GeoserverPropertyHolderDAO{

    /**
     * This method save the info from a GeoserverPropertyHolder java object
     * into the geoserver.properties file
     */
    @Override
    public boolean saveToPropertiesFile(GeoserverPropertyHolder ph) {
        try {
            Properties prop = new Properties();

            prop.setProperty("geoserver.serverIpAddress", ph.getServerIpAddress());
            prop.setProperty("geoserver.layersWorkSpace", ph.getLayersWorkSpace());

            //Update the file content
            FileOutputStream fos = new FileOutputStream("geoserver.properties");
            prop.store(fos,null);

            //Close the file
            fos.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing properties file" + "\n" + e);
            return false;
        }
    }

    /**
     * Returns a GeoserverPropertyHolder java Object with all the
     * information from the geoserver.properties file
     */
    @Override
    public GeoserverPropertyHolder getPropertyHolder() {
        GeoserverPropertyHolder result = new GeoserverPropertyHolder();
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("geoserver.properties");
            //Load the file
            prop.load(fis);

            result.setServerIpAddress(prop.getProperty("geoserver.serverIpAddress"));
            result.setLayersWorkSpace(prop.getProperty("geoserver.layersWorkSpace"));

            //Close the file
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading properties file" + "\n" + e);
        }
        return result;
    }

}
