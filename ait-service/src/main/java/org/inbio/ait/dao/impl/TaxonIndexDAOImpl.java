/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.inbio.ait.dao.TaxonIndexDAO;
import org.inbio.ait.model.TaxonIndex;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author esmata
 */
public class TaxonIndexDAOImpl extends SimpleJdbcDaoSupport implements TaxonIndexDAO{

    @Override
    public TaxonIndex getTaxonIndexByName(String name) {
        TaxonIndex tIndex = new TaxonIndex();
        try {
            String query = "Select * from ait.taxon_index where taxon_name = '"+ name +"';";
            tIndex = getSimpleJdbcTemplate().queryForObject(query,new tIndexMapper());
        } catch (Exception e) {
            return tIndex;
        }

        return tIndex;
    }

     private static class tIndexMapper implements ParameterizedRowMapper<TaxonIndex> {

        @Override
        public TaxonIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaxonIndex ti = new TaxonIndex();
            ti.setTaxon_id(rs.getLong("taxon_id"));
            ti.setTaxon_name(rs.getString("taxon_name"));
            ti.setTaxon_range(rs.getLong("taxon_range"));
            return ti;
        }

     }

}
