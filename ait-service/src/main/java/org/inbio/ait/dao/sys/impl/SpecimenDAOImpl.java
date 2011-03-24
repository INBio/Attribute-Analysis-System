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
import org.inbio.ait.dao.sys.SpecimenDAO;
import org.inbio.ait.model.AutocompleteNode;
import org.inbio.ait.model.Specimen;
import org.inbio.ait.model.SpecimenBase;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * Implementing SpecimenDAO methods
 * @author esmata
 */
public class SpecimenDAOImpl extends SimpleJdbcDaoSupport implements SpecimenDAO{

    @Override
    public List<Specimen> getSpecimenList() {
        List<Specimen> specimens = new ArrayList<Specimen>();
        try {
            //limit = num_registros_devueltos offset = inicio
            String query = "Select * from ait.darwin_core order by " +
                    "globaluniqueidentifier limit 10 offset 0;";
            specimens = getSimpleJdbcTemplate().query(query,
                    new SpecimenMapper());
        } catch (Exception e) {
            return specimens;
        }
        return specimens;
    }

    /**
     * Method to clean some identified data errors from darwin core data
     * Executes some cleaning scripts an retur true is everything was ok
     * @return
     */
    @Override
    public boolean cleanDwcData() throws Exception{
        try {
            String query1 = "update ait.darwin_core set decimallongitude = replace(decimallongitude, ',' , '.') where decimallongitude like '%,%';";
            String query2 = "update ait.darwin_core set decimallatitude = replace(decimallatitude, ',' , '.') where decimallatitude like '%,%';";
            String query3 = "update ait.darwin_core set decimallongitude = null where decimallongitude like '%W%';";
            String query4 = "update ait.darwin_core set decimallongitude = null where decimallongitude like '%E%';";
            String query5 = "update ait.darwin_core set decimallatitude = null where decimallatitude like '%N%';";
            String query6 = "update ait.darwin_core set decimallatitude = null where decimallatitude like '%S%';";
            String query7 = "update ait.darwin_core set decimallatitude = null where decimallatitude like '%\\'%';";
            String query8 = "update ait.darwin_core set decimallongitude = null where decimallongitude like '%\\'%';";
            String query9 = "update ait.darwin_core set decimallatitude = null where decimallatitude like '%º%';";
            String query10 = "update ait.darwin_core set decimallongitude = null where decimallongitude like '%º%';";
            String query11 = "update ait.darwin_core set decimallatitude = null where decimallatitude like '%°%';";
            String query12 = "update ait.darwin_core set decimallongitude = null where decimallongitude like '%°%';";
            String query13 = "update ait.darwin_core set decimallongitude = null,decimallatitude = null where decimallongitude is null and decimallatitude is not null or decimallongitude is not null and decimallatitude is null;";
            getSimpleJdbcTemplate().update(query1);
            getSimpleJdbcTemplate().update(query2);
            getSimpleJdbcTemplate().update(query3);
            getSimpleJdbcTemplate().update(query4);
            getSimpleJdbcTemplate().update(query5);
            getSimpleJdbcTemplate().update(query6);
            getSimpleJdbcTemplate().update(query7);
            getSimpleJdbcTemplate().update(query8);
            getSimpleJdbcTemplate().update(query9);
            getSimpleJdbcTemplate().update(query10);
            getSimpleJdbcTemplate().update(query11);
            getSimpleJdbcTemplate().update(query12);
            getSimpleJdbcTemplate().update(query13);            
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    /**
     * Get a list of specimens by their globaluniqueidentifiers
     * @param guiList
     * @return
     */
    @Override
    public List<Specimen> getSpecimenListByQuery(String q){
        List<Specimen> specimens = new ArrayList<Specimen>();
        try {
            //limit = num_registros_devueltos offset = inicio
            String query = q;
            specimens = getSimpleJdbcTemplate().query(query,
                    new SpecimenMapper());
        } catch (Exception e) {
            return specimens;
        }
        return specimens;
    }
    
    /**
     * Return all disctint elements for classes,phylums,kingdoms ...
     * @param partialName
     * @param range
     * @return
     */
    @Override
    @Deprecated
    public List<AutocompleteNode> getElementsByRange(String partialName, int range, String atributeName) {
        List<AutocompleteNode> nodes = new ArrayList<AutocompleteNode>();
        try {
            String query = "Select DISTINCT " + atributeName + " from ait.darwin_core as s " +
                    "where s." + atributeName + " like '%" + partialName + "%' limit 2 offset 0;";

            nodes = getSimpleJdbcTemplate().query(query,
                    new AutocompleteMapper(range, atributeName));
        } catch (Exception e) {
            return nodes;
        }
        return nodes;
    }

    /**
     * Deletes all information from ait.darwin_core table
     * @return
     */
    @Override
    public boolean deleteAllSpecimens() {
        try {
            String query = "Delete from ait.darwin_core";
            getSimpleJdbcTemplate().update(query);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Insert a single specimen into data base
     * @return
     */
    @Override
    public int InsertSpecimen(SpecimenBase sp) {
        int result = 0;
        try {
            String insertQuery =
                    "Insert into ait.darwin_core (globaluniqueidentifier," +
                    "datelastmodified,institutioncode,collectioncode," +
                    "catalognumber,scientificname,basisofrecord,kingdom,phylum,class," +
                    "orders,family,genus,specificepithet,decimallongitude," +
                    "decimallatitude) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            result = getSimpleJdbcTemplate().update(insertQuery, sp.getGlobaluniqueidentifier(),
                    sp.getDatelastmodified(), sp.getInstitutioncode(), sp.getCollectioncode(),
                    sp.getCatalognumber(), sp.getScientificname(), sp.getBasisofrecord(),
                    sp.getKingdom(), sp.getPhylum(), sp.getClass1(), sp.getOrders(), sp.getFamily(),
                    sp.getGenus(), sp.getSpecificepithet(), sp.getDecimallongitude(),
                    sp.getDecimallatitude());
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return result;
    }

    private static class SpecimenMapper implements ParameterizedRowMapper<Specimen> {

        @Override
        public Specimen mapRow(ResultSet rs, int rowNum) throws SQLException {
            Specimen sp = new Specimen();
            sp.setGlobaluniqueidentifier(rs.getString("globaluniqueidentifier"));
            sp.setDatelastmodified(rs.getDate("datelastmodified"));
            sp.setInstitutioncode(rs.getString("institutioncode"));
            sp.setCollectioncode(rs.getString("collectioncode"));
            sp.setCatalognumber(rs.getString("catalognumber"));
            sp.setCatalognumbernumeric(rs.getLong("catalognumbernumeric"));
            sp.setScientificname(rs.getString("scientificname"));
            sp.setBasisofrecord(rs.getString("basisofrecord"));
            sp.setInformationwithheld(rs.getString("informationwithheld"));
            sp.setKingdomid(rs.getLong("kingdomid"));
            sp.setPhylumId(rs.getLong("phylum_id"));
            sp.setClassId(rs.getLong("class_id"));
            sp.setOrdersId(rs.getLong("orders_id"));
            sp.setFamilyId(rs.getLong("family_id"));
            sp.setGenusId(rs.getLong("genus_id"));
            sp.setSpecificepithetId(rs.getLong("specificepithet_id"));
            sp.setInfraspecificepithetId(rs.getLong("infraspecificepithet_id"));
            sp.setHighertaxon(rs.getString("highertaxon"));
            sp.setKingdom(rs.getString("kingdom"));
            sp.setClass1(rs.getString("class"));
            sp.setOrders(rs.getString("orders"));
            sp.setFamily(rs.getString("family"));
            sp.setGenus(rs.getString("genus"));
            sp.setSpecificepithet(rs.getString("specificepithet"));
            sp.setInfraspecificepithet(rs.getString("infraspecificepithet"));
            sp.setInfraspecificrank(rs.getString("infraspecificrank"));
            sp.setAuthoryearofscientificname(rs.getString("authoryearofscientificname"));
            sp.setNomenclaturalcode(rs.getString("nomenclaturalcode"));
            sp.setIdentificationqualifier(rs.getString("identificationqualifier"));
            sp.setIdentifiedby(rs.getString("identifiedby"));
            sp.setDateidentified(rs.getDate("dateidentified"));
            sp.setTypestatus(rs.getString("typestatus"));
            sp.setCollectingmethod(rs.getString("collectingmethod"));
            sp.setValiddistributionflag(rs.getString("validdistributionflag"));
            sp.setCollectornumber(rs.getString("collectornumber"));
            sp.setFieldnumber(rs.getString("fieldnumber"));
            sp.setCollector(rs.getString("collector"));
            sp.setEarliestdatecollected(rs.getDate("earliestdatecollected"));
            sp.setLatestdatecollected(rs.getDate("latestdatecollected"));
            sp.setVerbatimcollectingdate(rs.getString("verbatimcollectingdate"));
            sp.setDayofyear(rs.getLong("dayofyear"));
            sp.setFieldnotes(rs.getString("fieldnotes"));
            sp.setHighergeography(rs.getString("highergeography"));
            sp.setContinent(rs.getString("continent"));
            sp.setWaterbody(rs.getString("waterbody"));
            sp.setIslandgroup(rs.getString("islandgroup"));
            sp.setIsland(rs.getString("island"));
            sp.setCountry(rs.getString("country"));
            sp.setStateprovince(rs.getString("stateprovince"));
            sp.setCounty(rs.getString("county"));
            sp.setLocality(rs.getString("locality"));
            sp.setDecimallongitude(rs.getString("decimallongitude"));
            sp.setVerbatimlongitude(rs.getString("verbatimlongitude"));
            sp.setDecimallatitude(rs.getString("decimallatitude"));
            sp.setVerbatimlatitude(rs.getString("verbatimlatitude"));
            sp.setGeodeticdatum(rs.getString("geodeticdatum"));
            sp.setVerbatimcoordinatesystem(rs.getString("verbatimcoordinatesystem"));
            sp.setGeoreferenceprotocol(rs.getString("georeferenceprotocol"));
            sp.setCoordinateuncertaintyinmeters(rs.getString("coordinateuncertaintyinmeters"));
            sp.setGeoreferenceremarks(rs.getString("georeferenceremarks"));
            sp.setFootprintwkt(rs.getString("footprintwkt"));
            sp.setMinimumelevationinmeters(rs.getDouble("minimumelevationinmeters"));
            sp.setMaximumelevationinmeters(rs.getDouble("maximumelevationinmeters"));
            sp.setVerbatimelevation(rs.getDouble("verbatimelevation"));
            sp.setMinimumdepthinmeters(rs.getDouble("minimumdepthinmeters"));
            sp.setMaximumdepthinmeters(rs.getDouble("maximumdepthinmeters"));
            sp.setSex(rs.getString("sex"));
            sp.setLifestage(rs.getString("lifestage"));
            sp.setPreparations(rs.getString("preparations"));
            sp.setIndividualcount(rs.getLong("individualcount"));
            sp.setGenbanknum(rs.getString("genbanknum"));
            sp.setOthercatalognumbers(rs.getString("othercatalognumbers"));
            sp.setRelatedcatalogitems(rs.getString("relatedcatalogitems"));
            sp.setRemarks(rs.getString("remarks"));
            sp.setAttributes(rs.getString("attributes"));
            sp.setImageurl(rs.getString("imageurl"));
            sp.setRelatedinformation(rs.getString("relatedinformation"));
            sp.setDisposition(rs.getString("disposition"));
            sp.setPointradiusspatialfit(rs.getLong("pointradiusspatialfit"));
            sp.setFootprintspatialfit(rs.getLong("footprintspatialfit"));
            sp.setVerbatimcoordinates(rs.getString("verbatimcoordinates"));
            sp.setGeoreferencesources(rs.getString("georeferencesources"));
            sp.setGeoreferenceverificationstatus(rs.getString("georeferenceverificationstatus"));
            
            return sp;
        }
    }

    private static class AutocompleteMapper implements ParameterizedRowMapper<AutocompleteNode> {

        //To determine witch range is been used
        private int range;
        //Name of table atribute
        private String atribute;

        //Constructor
        public AutocompleteMapper(int r,String a){
            this.range = r;
            this.atribute = a;
        }

        public AutocompleteNode mapRow(ResultSet rs, int rowNum) throws SQLException {

            AutocompleteNode acn = new AutocompleteNode();
            acn.setItemName(rs.getString(atribute));
            acn.setItemId(rs.getString(atribute));
            return acn;
        }
    }

}
