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
import org.inbio.ait.dao.TindiPropertyHolderDAO;
import org.inbio.ait.model.TindiPropertyHolder;

/**
 *
 * @author esmata
 */
public class TindiPropertyHolderDAOImpl implements TindiPropertyHolderDAO{

    /**
     * This method save the info from a TindiPropertyHolder java object
     * into the tindi.properties file
     */
    @Override
    public boolean saveToPropertiesFile(TindiPropertyHolder ph) {
        try {
            Properties prop = new Properties();

            prop.setProperty("tindi.driverClassName", ph.getDriverClassName());
            prop.setProperty("tindi.url", ph.getUrl());
            prop.setProperty("tindi.username", ph.getUsername());
            prop.setProperty("tindi.password", ph.getPassword());
            prop.setProperty("tindi.tablename", ph.getTablename());
            prop.setProperty("tindi.taxon_indicator_id", 
                    ph.getTaxon_indicator_id());
            prop.setProperty("tindi.taxon_indicator_certainty_level", 
                    ph.getTaxon_indicator_certainty_level());
            prop.setProperty("tindi.taxon_indicator_evaluation_criteria", 
                    ph.getTaxon_indicator_evaluation_criteria());
            prop.setProperty("tindi.taxon_indicator_regionality", 
                    ph.getTaxon_indicator_regionality());
            prop.setProperty("tindi.taxon_indicator_temporality", 
                    ph.getTaxon_indicator_temporality());
            prop.setProperty("tindi.taxon_indicator_references", 
                    ph.getTaxon_indicator_references());
            prop.setProperty("tindi.taxon_indicator_notes", 
                    ph.getTaxon_indicator_notes());
            prop.setProperty("tindi.taxon_indicator_valuer_person", 
                    ph.getTaxon_indicator_valuer_person());
            prop.setProperty("tindi.taxon_scientific_name", 
                    ph.getTaxon_scientific_name());
            prop.setProperty("tindi.indicator_id", ph.getIndicator_id());
            prop.setProperty("tindi.component_part", ph.getComponent_part());

            //Update the file content
            FileOutputStream fos = new FileOutputStream("tindi.properties");
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
     * Returns a TindiPropertyHolder java Object with all the
     * information from the tindi.properties file
     */
    @Override
    public TindiPropertyHolder getTindiPropertyHolder() {
        TindiPropertyHolder result = new TindiPropertyHolder();
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("tindi.properties");
            //Load the file
            prop.load(fis);

            result.setDriverClassName(prop.getProperty("tindi.driverClassName"));
            result.setUrl(prop.getProperty("tindi.url"));
            result.setUsername(prop.getProperty("tindi.username"));
            result.setPassword(prop.getProperty("tindi.password"));
            result.setTablename(prop.getProperty("tindi.tablename"));

            result.setTaxon_indicator_id(prop.getProperty
                    ("tindi.taxon_indicator_id"));
            result.setTaxon_indicator_certainty_level(prop.getProperty
                    ("tindi.taxon_indicator_certainty_level"));
            result.setTaxon_indicator_evaluation_criteria(prop.getProperty
                    ("tindi.taxon_indicator_evaluation_criteria"));
            result.setTaxon_indicator_regionality
                    (prop.getProperty("tindi.taxon_indicator_regionality"));
            result.setTaxon_indicator_temporality
                    (prop.getProperty("tindi.taxon_indicator_temporality"));
            result.setTaxon_indicator_references
                    (prop.getProperty("tindi.taxon_indicator_references"));
            result.setTaxon_indicator_notes
                    (prop.getProperty("tindi.taxon_indicator_notes"));
            result.setTaxon_indicator_valuer_person
                    (prop.getProperty("tindi.taxon_indicator_valuer_person"));
            result.setTaxon_scientific_name
                    (prop.getProperty("tindi.taxon_scientific_name"));
            result.setIndicator_id(prop.getProperty("tindi.indicator_id"));
            result.setComponent_part(prop.getProperty("tindi.component_part"));

            //Close the file
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading properties file" + "\n" + e);
        }
        return result;
    }

}
