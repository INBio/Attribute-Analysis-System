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

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.dao.LayerDataAccessDAO;
import org.inbio.ait.model.LayerPropertyHolder;
import org.inbio.ait.util.AitDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author esmata
 */
public class LayerDataAccessDAOImpl implements LayerDataAccessDAO{

    private JdbcTemplate jdbcTemplate;

    /**
     * Method to get a list of all the tables existing on the data base
     */
    @Override
    public List<String> getLayerTables(LayerPropertyHolder ph) {
        List<String> result = new ArrayList<String>();
        result.add("unmapped"); //Default value
        try {
            //Stting up the jdbcTemplate
            this.accessToDB(ph);
            //getTables(String catalog,String schemaPattern,String tableNamePattern,String[] types)
            ResultSet tables = this.jdbcTemplate.getDataSource().getConnection().
                    getMetaData().getTables(null, null, null,new String[] {"TABLE"});
            while (tables.next()) {
                result.add(tables.getString("TABLE_NAME"));
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Coutn all tables from data base
     */
    @Override
    public int countAllTables(LayerPropertyHolder ph) {
        int result = 0;
        try {
            //Stting up the jdbcTemplate
            this.accessToDB(ph);
            //getTables(String catalog,String schemaPattern,String tableNamePattern,String[] types)
            ResultSet tables = this.jdbcTemplate.getDataSource().getConnection().
                    getMetaData().getTables(null, null, null,new String[] {"TABLE"});
            while (tables.next()) {
                result++;
            }
            return result;
        } catch (Exception e) {
            return -1;
        }
    }

    //Getting the access to the data base
    private void accessToDB(LayerPropertyHolder ph){
        //Getting the data source connection
        AitDataSource ds = new AitDataSource(ph.getDriverClassName(),
                ph.getUrl(), ph.getUsername(), ph.getPassword());
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

}
