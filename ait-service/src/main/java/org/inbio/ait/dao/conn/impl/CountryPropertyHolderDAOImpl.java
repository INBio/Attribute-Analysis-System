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

package org.inbio.ait.dao.conn.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.inbio.ait.dao.conn.CountryPropertyHolderDAO;
import org.inbio.ait.model.CountryPropertyHolder;

/**
 *
 * @author esmata
 */
public class CountryPropertyHolderDAOImpl implements CountryPropertyHolderDAO{

    /**
     * This method save the info from a CountryPropertyHolder java object
     * into the country.properties file
     */
    @Override
    public boolean saveToPropertiesFile(CountryPropertyHolder ph) {
        try {
            Properties prop = new Properties();

            prop.setProperty("country.driverClassName", ph.getDriverClassName());
            prop.setProperty("country.url", ph.getUrl());
            prop.setProperty("country.username", ph.getUsername());
            prop.setProperty("country.password", ph.getPassword());
            prop.setProperty("country.tablename", ph.getTablename());
            prop.setProperty("country.country_id", ph.getCountryId());
            prop.setProperty("country.country_name", ph.getCountryName());

            //Update the file content
            FileOutputStream fos = new FileOutputStream("country.properties");
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
     * Returns a CountryPropertyHolder java Object with all the
     * information from the country.properties file
     */
    @Override
    public CountryPropertyHolder getCountryPropertyHolder() {
        CountryPropertyHolder result = new CountryPropertyHolder();
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("country.properties");
            //Load the file
            prop.load(fis);

            result.setDriverClassName(prop.getProperty("country.driverClassName"));
            result.setUrl(prop.getProperty("country.url"));
            result.setUsername(prop.getProperty("country.username"));
            result.setPassword(prop.getProperty("country.password"));
            result.setTablename(prop.getProperty("country.tablename"));
            result.setCountryId(prop.getProperty("country.country_id"));
            result.setCountryName(prop.getProperty("country.country_name"));

            //Close the file
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading properties file" + "\n" + e);
        }
        return result;
    }

}
