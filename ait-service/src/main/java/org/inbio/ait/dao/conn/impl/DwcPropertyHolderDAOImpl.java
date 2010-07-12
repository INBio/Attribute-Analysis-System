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
import org.inbio.ait.dao.conn.DwcPropertyHolderDAO;
import org.inbio.ait.model.DwcPropertyHolder;

/**
 *
 * @author esmata
 */
public class DwcPropertyHolderDAOImpl implements DwcPropertyHolderDAO{

    /**
     * This method save the info from a DwcPropertyHolder java class
     * into the dwc.properties file
     */
    @Override
    public boolean saveToPropertiesFile(DwcPropertyHolder ph) {
        try {
            Properties prop = new Properties();

            prop.setProperty("dwc.tablename", ph.getTablename());
            prop.setProperty("dwc.driverClassName", ph.getDriverClassName());
            prop.setProperty("dwc.url", ph.getUrl());
            prop.setProperty("dwc.username", ph.getUsername());
            prop.setProperty("dwc.password", ph.getPassword());
            prop.setProperty("dwc.globaluniqueidentifier", ph.getGlobaluniqueidentifier());
            prop.setProperty("dwc.datelastmodified", ph.getDatelastmodified());
            prop.setProperty("dwc.institutioncode", ph.getInstitutioncode());
            prop.setProperty("dwc.collectioncode", ph.getCollectioncode());
            prop.setProperty("dwc.catalognumber", ph.getCatalognumber());
            prop.setProperty("dwc.catalognumbernumeric", ph.getCatalognumbernumeric());
            prop.setProperty("dwc.scientificname", ph.getScientificname());
            prop.setProperty("dwc.basisofrecord", ph.getBasisofrecord());
            prop.setProperty("dwc.informationwithheld", ph.getInformationwithheld());
            prop.setProperty("dwc.highertaxon", ph.getHighertaxon());
            prop.setProperty("dwc.kingdom", ph.getKingdom());
            prop.setProperty("dwc.phylum", ph.getPhylum());
            prop.setProperty("dwc.class1", ph.getClass1());
            prop.setProperty("dwc.orders", ph.getOrders());
            prop.setProperty("dwc.family", ph.getFamily());
            prop.setProperty("dwc.genus", ph.getGenus());
            prop.setProperty("dwc.specificepithet", ph.getSpecificepithet());
            prop.setProperty("dwc.infraspecificepithet", ph.getInfraspecificepithet());
            prop.setProperty("dwc.infraspecificrank", ph.getInfraspecificrank());
            prop.setProperty("dwc.authoryearofscientificname", ph.getAuthoryearofscientificname());
            prop.setProperty("dwc.nomenclaturalcode", ph.getNomenclaturalcode());
            prop.setProperty("dwc.identificationqualifier", ph.getIdentificationqualifier());
            prop.setProperty("dwc.identifiedby", ph.getIdentifiedby());
            prop.setProperty("dwc.dateidentified", ph.getDateidentified());
            prop.setProperty("dwc.typestatus", ph.getTypestatus());
            prop.setProperty("dwc.collectingmethod", ph.getCollectingmethod());
            prop.setProperty("dwc.validdistributionflag", ph.getValiddistributionflag());
            prop.setProperty("dwc.collectornumber", ph.getCollectornumber());
            prop.setProperty("dwc.fieldnumber", ph.getFieldnumber());
            prop.setProperty("dwc.collector", ph.getCollector());
            prop.setProperty("dwc.earliestdatecollected", ph.getEarliestdatecollected());
            prop.setProperty("dwc.latestdatecollected", ph.getLatestdatecollected());
            prop.setProperty("dwc.verbatimcollectingdate", ph.getVerbatimcollectingdate());
            prop.setProperty("dwc.dayofyear", ph.getDayofyear());
            prop.setProperty("dwc.fieldnotes", ph.getFieldnotes());
            prop.setProperty("dwc.highergeography", ph.getHighergeography());
            prop.setProperty("dwc.continent", ph.getContinent());
            prop.setProperty("dwc.waterbody", ph.getWaterbody());
            prop.setProperty("dwc.islandgroup", ph.getIslandgroup());
            prop.setProperty("dwc.island", ph.getIsland());
            prop.setProperty("dwc.country", ph.getCountry());
            prop.setProperty("dwc.stateprovince", ph.getStateprovince());
            prop.setProperty("dwc.county", ph.getCounty());
            prop.setProperty("dwc.locality", ph.getLocality());
            prop.setProperty("dwc.decimallongitude", ph.getDecimallongitude());
            prop.setProperty("dwc.verbatimlongitude", ph.getVerbatimlongitude());
            prop.setProperty("dwc.decimallatitude", ph.getDecimallatitude());
            prop.setProperty("dwc.verbatimlatitude", ph.getVerbatimlatitude());
            prop.setProperty("dwc.geodeticdatum", ph.getGeodeticdatum());
            prop.setProperty("dwc.verbatimcoordinatesystem", ph.getVerbatimcoordinatesystem());
            prop.setProperty("dwc.georeferenceprotocol", ph.getGeoreferenceprotocol());
            prop.setProperty("dwc.coordinateuncertaintyinmeters", ph.getCoordinateuncertaintyinmeters());
            prop.setProperty("dwc.georeferenceremarks", ph.getGeoreferenceremarks());
            prop.setProperty("dwc.footprintwkt", ph.getFootprintwkt());
            prop.setProperty("dwc.minimumelevationinmeters", ph.getMinimumelevationinmeters());
            prop.setProperty("dwc.maximumelevationinmeters", ph.getMaximumelevationinmeters());
            prop.setProperty("dwc.verbatimelevation", ph.getVerbatimelevation());
            prop.setProperty("dwc.minimumdepthinmeters", ph.getMinimumdepthinmeters());
            prop.setProperty("dwc.maximumdepthinmeters", ph.getMaximumdepthinmeters());
            prop.setProperty("dwc.sex", ph.getSex());
            prop.setProperty("dwc.lifestage", ph.getLifestage());
            prop.setProperty("dwc.preparations", ph.getPreparations());
            prop.setProperty("dwc.individualcount", ph.getIndividualcount());
            prop.setProperty("dwc.genbanknum", ph.getGenbanknum());
            prop.setProperty("dwc.othercatalognumbers", ph.getOthercatalognumbers());
            prop.setProperty("dwc.relatedcatalogitems", ph.getRelatedcatalogitems());
            prop.setProperty("dwc.remarks", ph.getRemarks());
            prop.setProperty("dwc.attributes", ph.getAttributes());
            prop.setProperty("dwc.imageurl", ph.getImageurl());
            prop.setProperty("dwc.relatedinformation", ph.getRelatedinformation());
            prop.setProperty("dwc.disposition", ph.getDisposition());
            prop.setProperty("dwc.pointradiusspatialfit", ph.getPointradiusspatialfit());
            prop.setProperty("dwc.footprintspatialfit", ph.getFootprintspatialfit());
            prop.setProperty("dwc.verbatimcoordinates", ph.getVerbatimcoordinates());
            prop.setProperty("dwc.georeferencesources", ph.getGeoreferencesources());
            prop.setProperty("dwc.georeferenceverificationstatus", ph.getGeoreferenceverificationstatus());

            //Update the file content
            FileOutputStream fos = new FileOutputStream("dwc.properties");
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
     * Returns a DwcPropertyHolder java Object with all the
     * information from de dwc.properties file
     */
    @Override
    public DwcPropertyHolder getDwcPropertyHolder() {
        DwcPropertyHolder result = new DwcPropertyHolder();
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("dwc.properties");
            //Load the file
            prop.load(fis);

            result.setTablename(prop.getProperty("dwc.tablename"));
            result.setDriverClassName(prop.getProperty("dwc.driverClassName"));
            result.setUrl(prop.getProperty("dwc.url"));
            result.setUsername(prop.getProperty("dwc.username"));
            result.setPassword(prop.getProperty("dwc.password"));
            result.setGlobaluniqueidentifier(prop.getProperty("dwc.globaluniqueidentifier"));
            result.setDatelastmodified(prop.getProperty("dwc.datelastmodified"));
            result.setInstitutioncode(prop.getProperty("dwc.institutioncode"));
            result.setCollectioncode(prop.getProperty("dwc.collectioncode"));
            result.setCatalognumber(prop.getProperty("dwc.catalognumber"));
            result.setCatalognumbernumeric(prop.getProperty("dwc.catalognumbernumeric"));
            result.setScientificname(prop.getProperty("dwc.scientificname"));
            result.setBasisofrecord(prop.getProperty("dwc.basisofrecord"));
            result.setInformationwithheld(prop.getProperty("dwc.informationwithheld"));
            result.setHighertaxon(prop.getProperty("dwc.highertaxon"));
            result.setKingdom(prop.getProperty("dwc.kingdom"));
            result.setPhylum(prop.getProperty("dwc.phylum"));
            result.setClass1(prop.getProperty("dwc.class1"));
            result.setOrders(prop.getProperty("dwc.orders"));
            result.setFamily(prop.getProperty("dwc.family"));
            result.setGenus(prop.getProperty("dwc.genus"));
            result.setSpecificepithet(prop.getProperty("dwc.specificepithet"));
            result.setInfraspecificepithet(prop.getProperty("dwc.infraspecificepithet"));
            result.setInfraspecificrank(prop.getProperty("dwc.infraspecificrank"));
            result.setAuthoryearofscientificname(prop.getProperty("dwc.authoryearofscientificname"));
            result.setNomenclaturalcode(prop.getProperty("dwc.nomenclaturalcode"));
            result.setIdentificationqualifier(prop.getProperty("dwc.identificationqualifier"));
            result.setIdentifiedby(prop.getProperty("dwc.identifiedby"));
            result.setDateidentified(prop.getProperty("dwc.dateidentified"));
            result.setTypestatus(prop.getProperty("dwc.typestatus"));
            result.setCollectingmethod(prop.getProperty("dwc.collectingmethod"));
            result.setValiddistributionflag(prop.getProperty("dwc.validdistributionflag"));
            result.setCollectornumber(prop.getProperty("dwc.collectornumber"));
            result.setFieldnumber(prop.getProperty("dwc.fieldnumber"));
            result.setCollector(prop.getProperty("dwc.collector"));
            result.setEarliestdatecollected(prop.getProperty("dwc.earliestdatecollected"));
            result.setLatestdatecollected(prop.getProperty("dwc.latestdatecollected"));
            result.setVerbatimcollectingdate(prop.getProperty("dwc.verbatimcollectingdate"));
            result.setDayofyear(prop.getProperty("dwc.dayofyear"));
            result.setFieldnotes(prop.getProperty("dwc.fieldnotes"));
            result.setHighergeography(prop.getProperty("dwc.highergeography"));
            result.setContinent(prop.getProperty("dwc.continent"));
            result.setWaterbody(prop.getProperty("dwc.waterbody"));
            result.setIslandgroup(prop.getProperty("dwc.islandgroup"));
            result.setIsland(prop.getProperty("dwc.island"));
            result.setCountry(prop.getProperty("dwc.country"));
            result.setStateprovince(prop.getProperty("dwc.stateprovince"));
            result.setCounty(prop.getProperty("dwc.county"));
            result.setLocality(prop.getProperty("dwc.locality"));
            result.setDecimallongitude(prop.getProperty("dwc.decimallongitude"));
            result.setVerbatimlongitude(prop.getProperty("dwc.verbatimlongitude"));
            result.setDecimallatitude(prop.getProperty("dwc.decimallatitude"));
            result.setVerbatimlatitude(prop.getProperty("dwc.verbatimlatitude"));
            result.setGeodeticdatum(prop.getProperty("dwc.geodeticdatum"));
            result.setVerbatimcoordinatesystem(prop.getProperty("dwc.verbatimcoordinatesystem"));
            result.setGeoreferenceprotocol(prop.getProperty("dwc.georeferenceprotocol"));
            result.setCoordinateuncertaintyinmeters(prop.getProperty("dwc.coordinateuncertaintyinmeters"));
            result.setGeoreferenceremarks(prop.getProperty("dwc.georeferenceremarks"));
            result.setFootprintwkt(prop.getProperty("dwc.footprintwkt"));
            result.setMinimumelevationinmeters(prop.getProperty("dwc.minimumelevationinmeters"));
            result.setMaximumelevationinmeters(prop.getProperty("dwc.maximumelevationinmeters"));
            result.setVerbatimelevation(prop.getProperty("dwc.verbatimelevation"));
            result.setMinimumdepthinmeters(prop.getProperty("dwc.minimumdepthinmeters"));
            result.setMaximumdepthinmeters(prop.getProperty("dwc.maximumdepthinmeters"));
            result.setSex(prop.getProperty("dwc.sex"));
            result.setLifestage(prop.getProperty("dwc.lifestage"));
            result.setPreparations(prop.getProperty("dwc.preparations"));
            result.setIndividualcount(prop.getProperty("dwc.individualcount"));
            result.setGenbanknum(prop.getProperty("dwc.genbanknum"));
            result.setOthercatalognumbers(prop.getProperty("dwc.othercatalognumbers"));
            result.setRelatedcatalogitems(prop.getProperty("dwc.relatedcatalogitems"));
            result.setRemarks(prop.getProperty("dwc.remarks"));
            result.setAttributes(prop.getProperty("dwc.attributes"));
            result.setImageurl(prop.getProperty("dwc.imageurl"));
            result.setRelatedinformation(prop.getProperty("dwc.relatedinformation"));
            result.setDisposition(prop.getProperty("dwc.disposition"));
            result.setPointradiusspatialfit(prop.getProperty("dwc.pointradiusspatialfit"));
            result.setFootprintspatialfit(prop.getProperty("dwc.footprintspatialfit"));
            result.setVerbatimcoordinates(prop.getProperty("dwc.verbatimcoordinates"));
            result.setGeoreferencesources(prop.getProperty("dwc.georeferencesources"));
            result.setGeoreferenceverificationstatus(prop.getProperty("dwc.georeferenceverificationstatus"));

            //Close the file
            fis.close();
        } catch (IOException e) {
            System.out.println("Error reading properties file" + "\n" + e);
        }
        return result;
    }

    /*public static void main(String[] args){
        DwcPropertyHolderDAOImpl p =  new DwcPropertyHolderDAOImpl();
        DwcPropertyHolder ph = new DwcPropertyHolder();
        ph.setGlobaluniqueidentifier("guim");
        ph.setDatelastmodified("dlmm");
        ph.setInstitutioncode("icm");
        ph.setCollectioncode("ccm");

        p.saveToPropertiesFile(ph);
        p.getDwcPropertyHolder();
    }*/
}
