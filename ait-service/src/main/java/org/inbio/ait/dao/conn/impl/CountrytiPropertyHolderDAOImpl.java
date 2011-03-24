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
import org.inbio.ait.dao.conn.CountrytiPropertyHolderDAO;
import org.inbio.ait.model.CountrytiPropertyHolder;

/**
 *
 * @author esmata
 */
public class CountrytiPropertyHolderDAOImpl implements CountrytiPropertyHolderDAO{

    /**
     * This method save the info from a CountryPropertyHolder java object
     * into the country.properties file
     */
    @Override
    public boolean saveToPropertiesFile(CountrytiPropertyHolder ph) {
        try {
            Properties prop = new Properties();

            prop.setProperty("countryti.driverClassName", ph.getDriverClassName());
            prop.setProperty("countryti.url", ph.getUrl());
            prop.setProperty("countryti.username", ph.getUsername());
            prop.setProperty("countryti.password", ph.getPassword());
            prop.setProperty("countryti.tablename", ph.getTablename());
            prop.setProperty("countryti.country_id", ph.getCountryId());
            prop.setProperty("countryti.indicator_id", ph.getIndicatorId());
            prop.setProperty("countryti.taxon_scientific_name", ph.getTaxonScientificName());

            //Update the file content
            FileOutputStream fos = new FileOutputStream("countryti.properties");
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
    public CountrytiPropertyHolder getCountrytiPropertyHolder() {
        CountrytiPropertyHolder result = new CountrytiPropertyHolder();
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("countryti.properties");
            //Load the file
            prop.load(fis);

            result.setDriverClassName(prop.getProperty("countryti.driverClassName"));
            result.setUrl(prop.getProperty("countryti.url"));
            result.setUsername(prop.getProperty("countryti.username"));
            result.setPassword(prop.getProperty("countryti.password"));
            result.setTablename(prop.getProperty("countryti.tablename"));
            result.setCountryId(prop.getProperty("countryti.country_id"));
            result.setIndicatorId(prop.getProperty("countryti.indicator_id"));
            result.setTaxonScientificName(prop.getProperty("countryti.taxon_scientific_name"));

            //Close the file
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading properties file" + "\n" + e);
        }
        return result;
    }

}
