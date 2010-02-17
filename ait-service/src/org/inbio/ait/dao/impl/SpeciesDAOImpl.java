/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.dao.SpeciesDAO;
import org.inbio.ait.model.Species;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * Data access object implementation for the Species class
 * @author esmata
 */

public class SpeciesDAOImpl extends SimpleJdbcDaoSupport implements SpeciesDAO {

    public List<Species> getSpeciesList() {
        List<Species> species = new ArrayList<Species>();
        try{
            String query = "Select * from ait.species  order by globaluniqueidentifier limit 10 offset 0;";
            species = getSimpleJdbcTemplate().query(query,
                    new SpeciesMapper());
        }
        catch(Exception e){return species;}

        return species;
    }

    private static class SpeciesMapper implements ParameterizedRowMapper<Species> {

        public Species mapRow(ResultSet rs, int rowNum) throws SQLException {
            Species sp = new Species();
            sp.setGlobaluniqueidentifier(rs.getString("globaluniqueidentifier"));
            sp.setScientificname(rs.getString("scientificname"));
            sp.setInstitutioncode(rs.getString("institutioncode"));
            sp.setDatelastmodified(rs.getDate("datelastmodified"));
            sp.setTaxonrecordid(rs.getString("taxonrecordid"));
            sp.setLanguage(rs.getString("language"));
            sp.setCreators(rs.getString("creators"));
            sp.setDistribution(rs.getString("distribution"));
            sp.setAbstract1(rs.getString("abstract"));
            sp.setKingdomtaxon(rs.getString("kingdomtaxon"));
            sp.setPhylumtaxon(rs.getString("phylumtaxon"));
            sp.setClasstaxon(rs.getString("classtaxon"));
            sp.setOrdertaxon(rs.getString("ordertaxon"));
            sp.setFamilytaxon(rs.getString("familytaxon"));
            sp.setGenustaxon(rs.getString("genustaxon"));
            sp.setSynonyms(rs.getString("synonyms"));
            sp.setAuthoryearofscientificname(rs.getString("authoryearofscientificname"));
            sp.setSpeciespublicationreference(rs.getString("speciespublicationreference"));
            sp.setCommonnames(rs.getString("commonnames"));
            sp.setTypification(rs.getString("typification"));
            sp.setContributors(rs.getString("contributors"));
            sp.setDatecreated(rs.getDate("datecreated"));
            sp.setHabit(rs.getString("habit"));
            sp.setLifecycle(rs.getString("lifecycle"));
            sp.setReproduction(rs.getString("reproduction"));
            sp.setAnnualcycle(rs.getString("annualcycle"));
            sp.setScientificdescription(rs.getString("scientificdescription"));
            sp.setBriefdescription(rs.getString("briefdescription"));
            sp.setFeeding(rs.getString("feeding"));
            sp.setBehavior(rs.getString("behavior"));
            sp.setInteractions(rs.getString("interactions"));
            sp.setChromosomicnumbern(rs.getString("chromosomicnumbern"));
            sp.setMoleculardata(rs.getString("moleculardata"));
            sp.setPopulationbiology(rs.getString("populationbiology"));
            sp.setThreatstatus(rs.getString("threatstatus"));
            sp.setLegislation(rs.getString("legislation"));
            sp.setHabitat(rs.getString("habitat"));
            sp.setTerritory(rs.getString("territory"));
            sp.setEndemicity(rs.getString("endemicity"));
            sp.setTheuses(rs.getString("theuses"));
            sp.setThemanagement(rs.getString("themanagement"));
            sp.setFolklore(rs.getString("folklore"));
            sp.setThereferences(rs.getString("thereferences"));
            sp.setUnstructureddocumentation(rs.getString("unstructureddocumentation"));
            sp.setOtherinformationsources(rs.getString("otherinformationsources"));
            sp.setPapers(rs.getString("papers"));
            sp.setIdentificationkeys(rs.getString("identificationkeys"));
            sp.setMigratorydata(rs.getString("migratorydata"));
            sp.setEcologicalsignificance(rs.getString("ecologicalsignificance"));
            sp.setUnstructurednaturalhistory(rs.getString("unstructurednaturalhistory"));
            sp.setInvasivenessdata(rs.getString("invasivenessdata"));
            sp.setTargetaudiences(rs.getString("targetaudiences"));
            sp.setVersion(rs.getString("version"));
            sp.setUrlimage1(rs.getString("urlimage1"));
            sp.setCaptionimage1(rs.getString("captionimage1"));
            sp.setUrlimage2(rs.getString("urlimage2"));
            sp.setCaptionimage2(rs.getString("captionimage2"));
            sp.setUrlimage3(rs.getString("urlimage3"));
            sp.setCaptionimage3(rs.getString("captionimage3"));
            return sp;
        }
    }
}