/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.dao.TaxonInfoIndexDAO;
import org.inbio.ait.model.TaxonInfoIndex;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author esmata
 */
public class TaxonInfoIndexDAOImpl extends SimpleJdbcDaoSupport implements TaxonInfoIndexDAO {

    @Override
    public List<TaxonInfoIndex> getallTaxonInfo(){
        List<TaxonInfoIndex> tInfo = new ArrayList<TaxonInfoIndex>();
        try{
            String query = "Select * from ait.taxon_info_index order by globaluniqueidentifier limit 10 offset 0;";
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tInfoMapper());
        }
        catch(Exception e){return tInfo;}

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
        try{
            String query = q;
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tInfoMapper());
        }
        catch(Exception e){return tInfo;}

        return tInfo;
    }

    /**
     * * Execute any count query of TaxonInfoIndex model object
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

}
