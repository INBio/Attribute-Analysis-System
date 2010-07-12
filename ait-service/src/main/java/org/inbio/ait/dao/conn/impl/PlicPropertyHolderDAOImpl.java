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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.inbio.ait.dao.conn.PlicPropertyHolderDAO;
import org.inbio.ait.model.PlicPropertyHolder;

/**
 *
 * @author esmata
 */
public class PlicPropertyHolderDAOImpl implements PlicPropertyHolderDAO{

    /**
     * This method save the info from a PlicPropertyHolder java class
     * into the plic.properties file
     */
    @Override
    public boolean saveToPropertiesFile(PlicPropertyHolder ph) {
        try {
            Properties prop = new Properties();

            prop.setProperty("plic.driverClassName", ph.getDriverClassName());
            prop.setProperty("plic.url", ph.getUrl());
            prop.setProperty("plic.username", ph.getUsername());
            prop.setProperty("plic.password", ph.getPassword());
            prop.setProperty("plic.tablename", ph.getTablename());
            prop.setProperty("plic.globaluniqueidentifier", ph.getGlobaluniqueidentifier());
            prop.setProperty("plic.scientificname", ph.getScientificname());
            prop.setProperty("plic.institutioncode", ph.getInstitutioncode());
            prop.setProperty("plic.datelastmodified", ph.getDatelastmodified());
            prop.setProperty("plic.taxonrecordid", ph.getTaxonrecordid());
            prop.setProperty("plic.language", ph.getLanguage());
            prop.setProperty("plic.creators", ph.getCreators());
            prop.setProperty("plic.distribution", ph.getDistribution());
            prop.setProperty("plic.abstract1", ph.getAbstract1());
            prop.setProperty("plic.kingdomtaxon", ph.getKingdomtaxon());
            prop.setProperty("plic.phylumtaxon", ph.getPhylumtaxon());
            prop.setProperty("plic.classtaxon", ph.getClasstaxon());
            prop.setProperty("plic.ordertaxon", ph.getOrdertaxon());
            prop.setProperty("plic.familytaxon", ph.getFamilytaxon());
            prop.setProperty("plic.genustaxon", ph.getGenustaxon());
            prop.setProperty("plic.synonyms", ph.getSynonyms());
            prop.setProperty("plic.authoryearofscientificname", ph.getAuthoryearofscientificname());
            prop.setProperty("plic.speciespublicationreference", ph.getSpeciespublicationreference());
            prop.setProperty("plic.commonnames", ph.getCommonnames());
            prop.setProperty("plic.typification", ph.getTypification());
            prop.setProperty("plic.contributors", ph.getContributors());
            prop.setProperty("plic.datecreated", ph.getDatecreated());
            prop.setProperty("plic.habit", ph.getHabit());
            prop.setProperty("plic.lifecycle", ph.getLifecycle());
            prop.setProperty("plic.reproduction", ph.getReproduction());
            prop.setProperty("plic.annualcycle", ph.getAnnualcycle());
            prop.setProperty("plic.scientificdescription", ph.getScientificdescription());
            prop.setProperty("plic.briefdescription", ph.getBriefdescription());
            prop.setProperty("plic.feeding", ph.getFeeding());
            prop.setProperty("plic.behavior", ph.getBehavior());
            prop.setProperty("plic.interactions", ph.getInteractions());
            prop.setProperty("plic.chromosomicnumbern", ph.getChromosomicnumbern());
            prop.setProperty("plic.moleculardata", ph.getMoleculardata());
            prop.setProperty("plic.populationbiology", ph.getPopulationbiology());
            prop.setProperty("plic.threatstatus", ph.getThreatstatus());
            prop.setProperty("plic.legislation", ph.getLegislation());
            prop.setProperty("plic.habitat", ph.getHabitat());
            prop.setProperty("plic.territory", ph.getTerritory());
            prop.setProperty("plic.endemicity", ph.getEndemicity());
            prop.setProperty("plic.theuses", ph.getTheuses());
            prop.setProperty("plic.themanagement", ph.getThemanagement());
            prop.setProperty("plic.folklore", ph.getFolklore());
            prop.setProperty("plic.thereferences", ph.getThereferences());
            prop.setProperty("plic.unstructureddocumentation", ph.getUnstructureddocumentation());
            prop.setProperty("plic.otherinformationsources", ph.getOtherinformationsources());
            prop.setProperty("plic.papers", ph.getPapers());
            prop.setProperty("plic.identificationkeys", ph.getIdentificationkeys());
            prop.setProperty("plic.migratorydata", ph.getMigratorydata());
            prop.setProperty("plic.ecologicalsignificance", ph.getEcologicalsignificance());
            prop.setProperty("plic.unstructurednaturalhistory", ph.getUnstructurednaturalhistory());
            prop.setProperty("plic.invasivenessdata", ph.getInvasivenessdata());
            prop.setProperty("plic.targetaudiences", ph.getTargetaudiences());
            prop.setProperty("plic.version", ph.getVersion());
            prop.setProperty("plic.urlimage1", ph.getUrlimage1());
            prop.setProperty("plic.captionimage1", ph.getCaptionimage1());
            prop.setProperty("plic.urlimage2", ph.getUrlimage2());
            prop.setProperty("plic.captionimage2", ph.getCaptionimage2());
            prop.setProperty("plic.urlimage3", ph.getUrlimage3());
            prop.setProperty("plic.captionimage3", ph.getCaptionimage3());

            //Update the file content
            FileOutputStream fos = new FileOutputStream("plic.properties");
            prop.store(fos,null);

            //Close the file
            fos.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing properties file" + "\n" + e);
            return false;
        }
    }

    /**
     * Returns a PlicPropertyHolder java Object with all the
     * information from the plic.properties file
     */
    @Override
    public PlicPropertyHolder getPlicPropertyHolder() {
        PlicPropertyHolder result = new PlicPropertyHolder();
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("plic.properties");
            //Load the file
            prop.load(fis);

            result.setDriverClassName(prop.getProperty("plic.driverClassName"));
            result.setUrl(prop.getProperty("plic.url"));
            result.setUsername(prop.getProperty("plic.username"));
            result.setPassword(prop.getProperty("plic.password"));
            result.setTablename(prop.getProperty("plic.tablename"));
            result.setGlobaluniqueidentifier(prop.getProperty("plic.globaluniqueidentifier"));
            result.setScientificname(prop.getProperty("plic.scientificname"));
            result.setInstitutioncode(prop.getProperty("plic.institutioncode"));
            result.setDatelastmodified(prop.getProperty("plic.datelastmodified"));
            result.setTaxonrecordid(prop.getProperty("plic.taxonrecordid"));
            result.setLanguage(prop.getProperty("plic.language"));
            result.setCreators(prop.getProperty("plic.creators"));
            result.setDistribution(prop.getProperty("plic.distribution"));
            result.setAbstract1(prop.getProperty("plic.abstract1"));
            result.setKingdomtaxon(prop.getProperty("plic.kingdomtaxon"));
            result.setPhylumtaxon(prop.getProperty("plic.phylumtaxon"));
            result.setClasstaxon(prop.getProperty("plic.classtaxon"));
            result.setOrdertaxon(prop.getProperty("plic.ordertaxon"));
            result.setFamilytaxon(prop.getProperty("plic.familytaxon"));
            result.setGenustaxon(prop.getProperty("plic.genustaxon"));
            result.setSynonyms(prop.getProperty("plic.synonyms"));
            result.setAuthoryearofscientificname(prop.getProperty("plic.authoryearofscientificname"));
            result.setSpeciespublicationreference(prop.getProperty("plic.speciespublicationreference"));
            result.setCommonnames(prop.getProperty("plic.commonnames"));
            result.setTypification(prop.getProperty("plic.typification"));
            result.setContributors(prop.getProperty("plic.contributors"));
            result.setDatecreated(prop.getProperty("plic.datecreated"));
            result.setHabit(prop.getProperty("plic.habit"));
            result.setLifecycle(prop.getProperty("plic.lifecycle"));
            result.setReproduction(prop.getProperty("plic.reproduction"));
            result.setAnnualcycle(prop.getProperty("plic.annualcycle"));
            result.setScientificdescription(prop.getProperty("plic.scientificdescription"));
            result.setBriefdescription(prop.getProperty("plic.briefdescription"));
            result.setFeeding(prop.getProperty("plic.feeding"));
            result.setBehavior(prop.getProperty("plic.behavior"));
            result.setInteractions(prop.getProperty("plic.interactions"));
            result.setChromosomicnumbern(prop.getProperty("plic.chromosomicnumbern"));
            result.setMoleculardata(prop.getProperty("plic.moleculardata"));
            result.setPopulationbiology(prop.getProperty("plic.populationbiology"));
            result.setThreatstatus(prop.getProperty("plic.threatstatus"));
            result.setLegislation(prop.getProperty("plic.legislation"));
            result.setHabitat(prop.getProperty("plic.habitat"));
            result.setTerritory(prop.getProperty("plic.territory"));
            result.setEndemicity(prop.getProperty("plic.endemicity"));
            result.setTheuses(prop.getProperty("plic.theuses"));
            result.setThemanagement(prop.getProperty("plic.themanagement"));
            result.setFolklore(prop.getProperty("plic.folklore"));
            result.setThereferences(prop.getProperty("plic.thereferences"));
            result.setUnstructureddocumentation(prop.getProperty("plic.unstructureddocumentation"));
            result.setOtherinformationsources(prop.getProperty("plic.otherinformationsources"));
            result.setPapers(prop.getProperty("plic.papers"));
            result.setIdentificationkeys(prop.getProperty("plic.identificationkeys"));
            result.setMigratorydata(prop.getProperty("plic.migratorydata"));
            result.setEcologicalsignificance(prop.getProperty("plic.ecologicalsignificance"));
            result.setUnstructurednaturalhistory(prop.getProperty("plic.unstructurednaturalhistory"));
            result.setInvasivenessdata(prop.getProperty("plic.invasivenessdata"));
            result.setTargetaudiences(prop.getProperty("plic.targetaudiences"));
            result.setVersion(prop.getProperty("plic.version"));
            result.setUrlimage1(prop.getProperty("plic.urlimage1"));
            result.setCaptionimage1(prop.getProperty("plic.captionimage1"));
            result.setUrlimage2(prop.getProperty("plic.urlimage2"));
            result.setCaptionimage2(prop.getProperty("plic.captionimage2"));
            result.setUrlimage3(prop.getProperty("plic.urlimage3"));
            result.setCaptionimage3(prop.getProperty("plic.captionimage3"));

            //Close the file
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading properties file" + "\n" + e);
        }
        return result;
    }

}
