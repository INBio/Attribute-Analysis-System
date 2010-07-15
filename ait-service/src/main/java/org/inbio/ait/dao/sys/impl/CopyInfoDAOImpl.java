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

import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.jdbc.mapper.DwcMapper;
import org.inbio.ait.dao.sys.CopyInfoDAO;
import org.inbio.ait.jdbc.mapper.IndiMapper;
import org.inbio.ait.jdbc.mapper.TaxonIndicatorMapper;
import org.inbio.ait.model.DwcPropertyHolder;
import org.inbio.ait.model.IndiPropertyHolder;
import org.inbio.ait.model.Indicator;
import org.inbio.ait.model.SpecimenBase;
import org.inbio.ait.model.TaxonIndicator;
import org.inbio.ait.model.TindiPropertyHolder;
import org.inbio.ait.util.AitDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author esmata
 */
public class CopyInfoDAOImpl extends SimpleJdbcDaoSupport implements CopyInfoDAO{

    private JdbcTemplate jdbcFrom; //Conecction to external db

    /**
     * This method gets the specimens information from external data base and copy
     * this information into the darwin_core system table
     * @param ph Connection data
     * @param totalDwc total of registers
     * @return number of afected rows (copied in this case)
     */
    @Override
    public int migrateSpecimensData(DwcPropertyHolder ph,int totalDwc){
        //Stablish the connection to the external data base
        this.accessToDB(ph.getDriverClassName(), ph.getUrl(), ph.getUsername(), ph.getPassword());
        
        //Copy data from external dwc table to system dwc table
        List<SpecimenBase> spList = new ArrayList<SpecimenBase>();
        try {
            //Delete existing data
            if (!deleteAllGeneric("darwin_core")) {
                return -1; //error deleting existing data
            }
            //Paginating the migration proccess
            int afectedRows = 0;
            for (int i = 0; i < totalDwc; i+=500) {
                //Get data from external db
                String getQuery = "Select * from " + ph.getTablename() + " order by " +
                        ph.getGlobaluniqueidentifier() + " limit 500 offset " + i; //limit cuantos offset comienzo
                spList = this.jdbcFrom.query(getQuery, new DwcMapper(ph));
                //Insert external data into system db                
                for (SpecimenBase sp : spList) {
                    String insertQuery =
                            "Insert into ait.darwin_core (globaluniqueidentifier," +
                            "datelastmodified,institutioncode,collectioncode," +
                            "catalognumber,scientificname,basisofrecord,kingdom,phylum,class," +
                            "orders,family,genus,specificepithet,decimallongitude," +
                            "decimallatitude) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    int a = getSimpleJdbcTemplate().update(insertQuery, sp.getGlobaluniqueidentifier(),
                            sp.getDatelastmodified(), sp.getInstitutioncode(), sp.getCollectioncode(),
                            sp.getCatalognumber(), sp.getScientificname(), sp.getBasisofrecord(),
                            sp.getKingdom(), sp.getPhylum(), sp.getClass1(), sp.getOrders(), sp.getFamily(),
                            sp.getGenus(), sp.getSpecificepithet(), sp.getDecimallongitude(),
                            sp.getDecimallatitude());
                    afectedRows += a;
                }
                jdbcFrom.getDataSource().getConnection().close();                
            }//Ends pagination
            return afectedRows;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * This method gets the indicators information from external data base and copy
     * this information into the indicator system table
     * @param ph Connection data
     * @param totalIndi total of indicators registers
     * @return number of afected rows (copied in this case)
     */
    @Override
    public int migrateIndicatorsData(IndiPropertyHolder ph,int totalIndi){
        //Stablish the connection to the external data base
        this.accessToDB(ph.getDriverClassName(), ph.getUrl(), ph.getUsername(), ph.getPassword());

        //Copy data from external indicators table to system indicators table
        List<Indicator> iList = new ArrayList<Indicator>();
        try {
            //Delete existing data
            if (!deleteAllGeneric("indicator")) {
                return -1; //error deleting existing data
            }
            //Paginating the migration proccess
            int afectedRows = 0;
            for (int i = 0; i < totalIndi; i+=500) {
                //Get data from external db
                String getQuery = "Select * from " + ph.getTablename() + " order by " +
                        ph.getIndicator_id() + " limit 500 offset " + i; //limit cuantos offset comienzo
                iList = this.jdbcFrom.query(getQuery, new IndiMapper(ph));
                //Insert external data into system db
                for (Indicator indi : iList) {
                    String insertQuery =
                            "Insert into ait.indicator (indicator_id,indicator_name," +
                            "indicator_description,indicator_applies_to_part," +
                            "indicator_ancestor_id,indicator_references) " +
                            "values (?,?,?,?,?,?)";
                    int a = getSimpleJdbcTemplate().update(insertQuery, indi.getIndicator_id(),
                            indi.getIndicator_name(), indi.getIndicator_description(),
                            indi.getIndicator_applies_to_part(),indi.getIndicator_ancestor_id(),
                            indi.getIndicator_references());
                    afectedRows += a;
                }
                jdbcFrom.getDataSource().getConnection().close();
            }//Ends pagination
            return afectedRows;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * This method gets the taxon indicators data from external data base and copy
     * this information into the taxon indicator system table
     * @param ph Connection data
     * @param totalTindi total of taxon indicator registers
     * @return number of afected rows (copied rows)
     */
    @Override
    public int migrateTaxonIndicatorsData(TindiPropertyHolder ph,int totalTindi){
        //Stablish the connection to the external data base
        this.accessToDB(ph.getDriverClassName(), ph.getUrl(), ph.getUsername(), ph.getPassword());

        //Copy data from external taxon indicators table to system table
        List<TaxonIndicator> tiList = new ArrayList<TaxonIndicator>();
        try {
            //Delete existing data
            if (!deleteAllGeneric("taxon_indicator")) {
                return -1; //error deleting existing data
            }
            //Paginating the migration proccess
            int afectedRows = 0;
            for (int i = 0; i < totalTindi; i+=500) {
                //Get data from external db
                String getQuery = "Select * from " + ph.getTablename() + " order by " +
                        ph.getTaxon_indicator_id() + " limit 500 offset " + i; //limit cuantos offset comienzo
                tiList = this.jdbcFrom.query(getQuery, new TaxonIndicatorMapper(ph));
                //Insert external data into system db
                for (TaxonIndicator ti : tiList) {
                    String insertQuery =
                            "Insert into ait.taxon_indicator (taxon_indicator_id," +
                            "taxon_indicator_certainty_level,taxon_indicator_evaluation_criteria," +
                            "taxon_indicator_regionality,taxon_indicator_temporality," +
                            "taxon_indicator_references,taxon_indicator_notes," +
                            "taxon_indicator_valuer_person,taxon_scientific_name," +
                            "indicator_id,component_part) " +
                            "values (?,?,?,?,?,?,?,?,?,?,?)";
                    int a = getSimpleJdbcTemplate().update(insertQuery, ti.getTaxon_indicator_id(),
                            ti.getTaxon_indicator_certainty_level(),ti.getTaxon_indicator_evaluation_criteria(),
                            ti.getTaxon_indicator_regionality(),ti.getTaxon_indicator_temporality(),
                            ti.getTaxon_indicator_references(),ti.getTaxon_indicator_notes(),
                            ti.getTaxon_indicator_valuer_person(),ti.getTaxon_scientific_name(),
                            ti.getIndicator_id(),ti.getComponent_part());
                    afectedRows += a;
                }
                jdbcFrom.getDataSource().getConnection().close();
            }//Ends pagination
            return afectedRows;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * Deletes all table info
     * @param table name
     * @return
     */
    public boolean deleteAllGeneric(String table) {
        try {
            String query = "Delete from ait."+table;
            getSimpleJdbcTemplate().update(query);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //Getting the access to the external data base (whatever it be)
    private void accessToDB(String className,String url,String user,String pass){
        //Getting the data source connection
        AitDataSource ds = new AitDataSource(className,url,user,pass);
        this.jdbcFrom = new JdbcTemplate(ds);
    }

}
