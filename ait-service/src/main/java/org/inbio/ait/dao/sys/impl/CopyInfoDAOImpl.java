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
import org.inbio.ait.model.DwcPropertyHolder;
import org.inbio.ait.model.IndiPropertyHolder;
import org.inbio.ait.model.Indicator;
import org.inbio.ait.model.SpecimenBase;
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
