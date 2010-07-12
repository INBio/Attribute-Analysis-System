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

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.dao.conn.TindiDataAccessDAO;
import org.inbio.ait.model.TindiPropertyHolder;
import org.inbio.ait.util.AitDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author esmata
 */
public class TindiDataAccessDAOImpl implements TindiDataAccessDAO{

    private JdbcTemplate jdbcTemplate;

    /**
     * Method to get a list of all fields from the mapped taxon indicators table
     * through jdbc conection
     */
    @Override
    public List<String> getTindiTableFields(TindiPropertyHolder ph) {
        List<String> result = new ArrayList<String>();
        result.add("unmapped"); //Default value
        try {
            //Setting up the jdbcTemplate
            this.accessToDB(ph);
            //getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
            ResultSet cols = this.jdbcTemplate.getDataSource().getConnection().
                    getMetaData().getColumns(null, null, ph.getTablename(), null);
            while (cols.next()) {
                result.add(cols.getString("COLUMN_NAME"));
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int countAll(TindiPropertyHolder ph) {
        int result = -1;
        try {
            //Stting up the jdbcTemplate
            this.accessToDB(ph);
            return this.jdbcTemplate.queryForInt("Select count(*) from " + ph.getTablename());
        } catch (Exception e) {
            return result;
        }
    }

    //Getting the access to the data base
    private void accessToDB(TindiPropertyHolder ph) {
        //Getting the data source connection
        AitDataSource ds = new AitDataSource(ph.getDriverClassName(),
                ph.getUrl(), ph.getUsername(), ph.getPassword());
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

}
