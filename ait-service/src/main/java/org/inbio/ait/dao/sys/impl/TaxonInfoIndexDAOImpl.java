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
     * Execute any query that returns a list of scientific name ids
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
     * Execute any query that returns a list of scientific name NAMES
     */
    @Override
    public List<String> getScientificNamesAsText(String q) {
        List<String> tInfo = new ArrayList<String>();
        try {
            String query = q;
            tInfo = getSimpleJdbcTemplate().query(query,
                    new tSNAsTextMapper());
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

    /**
     * Indexation for the taxon_info_index table that contains all the indexed
     * information prepared specifically for quering (Step1)
     * @return if the proccess was successfully completed or not
     */
    @Override
    public boolean taxonInfoIndex(String layer) throws Exception{
        try {
            String sqlUpdate = "insert into ait.taxon_info_index (globaluniqueidentifier,  kingdom_id,  phylum_id,  class_id,  order_id,  family_id,  genus_id,  specific_epithet_id, "+
            "scientific_name_id,  indicator_id,  polygom_id,  layer_table,  country) "+
            "select dc.globaluniqueidentifier,null as kingdom_id,null as phylum_id,null as class_id,null as order_id,null as family_id,null as genus_id, "+
            "null as specific_epithet_id, sn.taxon_id as scientific_name_id, ti.indicator_id as indicator_id, "+
            "(Select gid from "+layer+" where ST_Contains(the_geom, 'POINT(' || dc.decimallongitude || ' ' ||  dc.decimallatitude || ')') limit 1) as polygom_id, "+
            "'"+layer+"' as layer_table, "+
            "(Select country_id from ait.country where country_name =  "+
            "(Select iso_code from ait.country_iso_3166 where country_id =  "+
            "(Select gid from paises where _ST_Contains(the_geom, 'POINT(' || dc.decimallongitude || ' ' ||  dc.decimallatitude || ')') limit 1))) as country "+
            "from ait.darwin_core dc, ait.taxon_index sn, ait.taxon_indicator ti where sn.taxon_name = dc.scientificname and "+
            "ti.taxon_scientific_name = dc.scientificname; ";
            int affected = getSimpleJdbcTemplate().update(sqlUpdate);
            System.out.println("Affected rows - taxon_info_index -"+affected);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    /**
     * Setting the complete indexed taxonomy on ait.taxon_info_index (Step2)
     * @return if the proccess was successfully completed or not
     */
    @Override
    public boolean completeIndexedTaxa() throws Exception{
        try {
            String sqlUpdatek = "update ait.taxon_info_index A set kingdom_id = (select C.taxon_id from 	ait.darwin_core B, ait.taxon_index C where "+
                                "A.globaluniqueidentifier = B.globaluniqueidentifier and B.kingdom = C.taxon_name and C.taxon_range = 1);"; //Kingdom
            String sqlUpdatep = "update ait.taxon_info_index A set phylum_id = (select C.taxon_id from 	ait.darwin_core B, ait.taxon_index C where "+
                                "A.globaluniqueidentifier = B.globaluniqueidentifier and B.phylum = C.taxon_name and C.taxon_range = 2);"; //phylum
            String sqlUpdatec = "update ait.taxon_info_index A set class_id = (select C.taxon_id from ait.darwin_core B, ait.taxon_index C where "+
                                "A.globaluniqueidentifier = B.globaluniqueidentifier and B.class = C.taxon_name and C.taxon_range = 3);"; //class
            String sqlUpdateo = "update ait.taxon_info_index A set order_id = (select C.taxon_id from ait.darwin_core B, ait.taxon_index C where "+
                                "A.globaluniqueidentifier = B.globaluniqueidentifier and B.orders = C.taxon_name and C.taxon_range = 4);"; //order
            String sqlUpdatef = "update ait.taxon_info_index A set family_id = (select C.taxon_id from ait.darwin_core B, ait.taxon_index C where "+
                                "A.globaluniqueidentifier = B.globaluniqueidentifier and B.family = C.taxon_name and C.taxon_range = 5);"; //family
            String sqlUpdateg = "update ait.taxon_info_index A set genus_id = (select C.taxon_id from ait.darwin_core B, ait.taxon_index C where "+
                                "A.globaluniqueidentifier = B.globaluniqueidentifier and B.genus = C.taxon_name and C.taxon_range = 6);"; //genus
            String sqlUpdatese = "update ait.taxon_info_index A set specific_epithet_id = (select C.taxon_id from ait.darwin_core B, ait.taxon_index C where "+
                                 "A.globaluniqueidentifier = B.globaluniqueidentifier and B.specificepithet = C.taxon_name and C.taxon_range = 7);"; //specificepithet
            getSimpleJdbcTemplate().update(sqlUpdatek);
            getSimpleJdbcTemplate().update(sqlUpdatep);
            getSimpleJdbcTemplate().update(sqlUpdatec);
            getSimpleJdbcTemplate().update(sqlUpdateo);
            getSimpleJdbcTemplate().update(sqlUpdatef);
            getSimpleJdbcTemplate().update(sqlUpdateg);
            getSimpleJdbcTemplate().update(sqlUpdatese);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    /**
     * Creates columns index to optimize the queries
     * @return
     */
    @Override
    public boolean createColumnIndex() throws Exception{
        try {
            String index1 = "CREATE INDEX ci_index  ON ait.taxon_info_index USING btree (class_id);";
            String index2 = "CREATE INDEX co_index ON ait.taxon_info_index USING btree (country);";
            String index3 = "CREATE INDEX fi_index ON ait.taxon_info_index USING btree (family_id);";
            String index4 = "CREATE INDEX gi_index ON ait.taxon_info_index USING btree (genus_id);";
            String index5 = "CREATE INDEX gui_index ON ait.taxon_info_index USING btree (globaluniqueidentifier);";
            String index6 = "CREATE INDEX ii_index ON ait.taxon_info_index USING btree (indicator_id);";
            String index7 = "CREATE INDEX ki_index ON ait.taxon_info_index USING btree (kingdom_id);";
            String index8 = "CREATE INDEX lti_index ON ait.taxon_info_index USING btree (layer_table);";
            String index9 = "CREATE INDEX oi_index ON ait.taxon_info_index USING btree (order_id);";
            String index10 = "CREATE INDEX pi_index ON ait.taxon_info_index USING btree (phylum_id);";
            String index11 = "CREATE INDEX poi_index ON ait.taxon_info_index USING btree (polygom_id);";
            String index12 = "CREATE INDEX ri_index ON ait.taxon_info_index USING btree (row_id);";
            String index13 = "CREATE INDEX sci_index ON ait.taxon_info_index USING btree (scientific_name_id);";
            String index14 = "CREATE INDEX sei_index ON ait.taxon_info_index USING btree (specific_epithet_id);";
            getSimpleJdbcTemplate().update(index1);
            getSimpleJdbcTemplate().update(index2);
            getSimpleJdbcTemplate().update(index3);
            getSimpleJdbcTemplate().update(index4);
            getSimpleJdbcTemplate().update(index5);
            getSimpleJdbcTemplate().update(index6);
            getSimpleJdbcTemplate().update(index7);
            getSimpleJdbcTemplate().update(index8);
            getSimpleJdbcTemplate().update(index9);
            getSimpleJdbcTemplate().update(index10);
            getSimpleJdbcTemplate().update(index11);
            getSimpleJdbcTemplate().update(index12);
            getSimpleJdbcTemplate().update(index13);
            getSimpleJdbcTemplate().update(index14);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    /**
     * Deletes all info from ait.taxon_info_index table
     * @throws java.lang.Exception
     */
    @Override
    public boolean deleteAllTaxonInfoIndex()  throws Exception{
        try {
            String query = "Delete from ait.taxon_info_index";
            getSimpleJdbcTemplate().update(query);
        } catch (Exception e) {
            throw e;
        }
        return true;
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

    private static class tSNAsTextMapper implements ParameterizedRowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String result = rs.getString("scientific_name");
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
