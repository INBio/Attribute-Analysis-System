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

package org.inbio.ait.dao.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.inbio.ait.dao.LayerPropertyHolderDAO;
import org.inbio.ait.model.LayerPropertyHolder;

/**
 *
 * @author esmata
 */
public class LayerPropertyHolderDAOImpl implements LayerPropertyHolderDAO{

    /**
     * This method save the info from a LayerPropertyHolder java class
     * into the layer.properties file
     */
    @Override
    public boolean saveToPropertiesFile(LayerPropertyHolder ph) {
        try {
            Properties prop = new Properties();

            prop.setProperty("layer.driverClassName", ph.getDriverClassName());
            prop.setProperty("layer.url", ph.getUrl());
            prop.setProperty("layer.username", ph.getUsername());
            prop.setProperty("layer.password", ph.getPassword());

            //Update the file content
            FileOutputStream fos = new FileOutputStream("layer.properties");
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
     * Returns a LayerPropertyHolder java Object with all the
     * information from the layer.properties file
     */
    @Override
    public LayerPropertyHolder getLayerPropertyHolder() {
        LayerPropertyHolder result = new LayerPropertyHolder();
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("layer.properties");
            //Load the file
            prop.load(fis);

            result.setDriverClassName(prop.getProperty("layer.driverClassName"));
            result.setUrl(prop.getProperty("layer.url"));
            result.setUsername(prop.getProperty("layer.username"));
            result.setPassword(prop.getProperty("layer.password"));

            //Close the file
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading properties file" + "\n" + e);
        }
        return result;
    }

}
