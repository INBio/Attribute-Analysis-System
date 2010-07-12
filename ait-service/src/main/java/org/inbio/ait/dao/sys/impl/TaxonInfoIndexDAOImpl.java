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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.dao.sys.TaxonInfoIndexDAO;
import org.inbio.ait.model.TaxonInfoIndex;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * Implementing TaxonInfoIndexDAO methods
 * @author esmata
 */
public class TaxonInfoIndexDAOImpl extends SimpleJdbcDaoSupport implements TaxonInfoIndexDAO {

    @Override
    public List<TaxonInfoIndex> getallTaxonInfo() {
        List<TaxonInfoIndex> tInfo = new ArrayList<TaxonInfoIndex>();
        try {
            String query = "Select * from ait.taxon_info_index order by globaluniqueidentifier limit 10 offset 0;";
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tInfoMapper());
        } catch (Exception e) {
            return tInfo;
        }
        return tInfo;
    }

    /**
     * Execute any query that returns a list of TaxonInfoIndex model object
     * @param q
     * @return
     */
    @Override
    public List<TaxonInfoIndex> getTaxonsByQuery(String q) {
        List<TaxonInfoIndex> tInfo = new ArrayList<TaxonInfoIndex>();
        try {
            String query = q;
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tInfoMapper());
        } catch (Exception e) {
            return tInfo;
        }
        return tInfo;
    }

    /**
     * Execute any query that returns a list of TaxonInfoIndex model object
     * just with the globalUniqueIdentifier
     * @param q
     * @return
     */
    @Override
    public List<String> getGlobalUniqueIdentifiers(String q) {
        List<String> tInfo = new ArrayList<String>();
        try {
            String query = q;
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tGUIMapper());
        } catch (Exception e) {
            return tInfo;
        }
        return tInfo;
    }

    /**
     * Execute any query that returns a list of TaxonInfoIndex model object
     * just with the scientific name
     * @param q
     * @return
     */
    @Override
    public List<String> getScientificNames(String q) {
        List<String> tInfo = new ArrayList<String>();
        try {
            String query = q;
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tSNMapper());
        } catch (Exception e) {
            return tInfo;
        }
        return tInfo;
    }

    /**
     * Execute any count query of TaxonInfoIndex model object
     * @param q
     * @return
     */
    @Override
    public Long countTaxonsByQuery(String q) {
        Long result = 0L;
        try {
            result = getSimpleJdbcTemplate().queryForLong(q);
        } catch (Exception e) {
            return result;
        }
        return result;
    }
    
    private static class tInfoMapper implements ParameterizedRowMapper<TaxonInfoIndex> {

        @Override
        public TaxonInfoIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaxonInfoIndex ti = new TaxonInfoIndex();
            ti.setGlobaluniqueidentifier(rs.getString("globaluniqueidentifier"));
            ti.setClass_id(rs.getLong("class_id"));
            ti.setFamily_id(rs.getLong("family_id"));
            ti.setGenus_id(rs.getLong("genus_id"));
            ti.setIndicator_id(rs.getLong("indicator_id"));
            ti.setKingdom_id(rs.getLong("kingdom_id"));
            ti.setLayer_id(rs.getLong("layer_id"));
            ti.setOrder_id(rs.getLong("order_id"));
            ti.setPhylum_id(rs.getLong("phylum_id"));
            ti.setPolygom_id(rs.getLong("polygom_id"));
            ti.setScientific_name_id(rs.getLong("scientific_name_id"));
            ti.setSpecific_epithet_id(rs.getLong("specific_epithet_id"));
            return ti;
        }
    }

    private static class tGUIMapper implements ParameterizedRowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String result = rs.getString("globaluniqueidentifier");
            return result;
        }
    }

    private static class tSNMapper implements ParameterizedRowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long result = rs.getLong("scientific_name_id");
            if(result==null)
                return null;
            else
                return result.toString();
        }
    }

}
