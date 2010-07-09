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
import org.inbio.ait.dao.IndiPropertyHolderDAO;
import org.inbio.ait.model.IndiPropertyHolder;

/**
 *
 * @author esmata
 */
public class IndiPropertyHolderDAOImpl implements IndiPropertyHolderDAO{

    /**
     * This method save the info from a IndiPropertyHolder java object
     * into the indi.properties file
     */
    @Override
    public boolean saveToPropertiesFile(IndiPropertyHolder ph) {
        try {
            Properties prop = new Properties();

            prop.setProperty("indi.driverClassName", ph.getDriverClassName());
            prop.setProperty("indi.url", ph.getUrl());
            prop.setProperty("indi.username", ph.getUsername());
            prop.setProperty("indi.password", ph.getPassword());
            prop.setProperty("indi.tablename", ph.getTablename());
            prop.setProperty("indi.indicator_id", ph.getIndicator_id());
            prop.setProperty("indi.indicator_name", ph.getIndicator_name());
            prop.setProperty("indi.indicator_description", ph.getIndicator_description());
            prop.setProperty("indi.indicator_applies_to_part", ph.getIndicator_applies_to_part());
            prop.setProperty("indi.indicator_ancestor_id", ph.getIndicator_ancestor_id());
            prop.setProperty("indi.indicator_references", ph.getIndicator_references());

            //Update the file content
            FileOutputStream fos = new FileOutputStream("indi.properties");
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
     * Returns a IndiPropertyHolder java Object with all the
     * information from the indi.properties file
     */
    @Override
    public IndiPropertyHolder getIndiPropertyHolder() {
        IndiPropertyHolder result = new IndiPropertyHolder();
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("indi.properties");
            //Load the file
            prop.load(fis);

            result.setDriverClassName(prop.getProperty("indi.driverClassName"));
            result.setUrl(prop.getProperty("indi.url"));
            result.setUsername(prop.getProperty("indi.username"));
            result.setPassword(prop.getProperty("indi.password"));
            result.setTablename(prop.getProperty("indi.tablename"));
            result.setIndicator_id(prop.getProperty("indi.indicator_id"));
            result.setIndicator_name(prop.getProperty("indi.indicator_name"));
            result.setIndicator_description(prop.getProperty("indi.indicator_description"));
            result.setIndicator_applies_to_part(prop.getProperty("indi.indicator_applies_to_part"));
            result.setIndicator_ancestor_id(prop.getProperty("indi.indicator_ancestor_id"));
            result.setIndicator_references(prop.getProperty("indi.indicator_references"));

            //Close the file
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading properties file" + "\n" + e);
        }
        return result;
    }

}
