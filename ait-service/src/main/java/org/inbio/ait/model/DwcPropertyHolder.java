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

package org.inbio.ait.model;

/**
 *
 * @author esmata
 */
public class DwcPropertyHolder {

    //Constructor
    public DwcPropertyHolder() {
        this.tablename  = "unmapped";
        this.driverClassName = "unmapped";
        this.url = "unmapped";
        this.username = "unmapped";
        this.password = "unmapped";
        this.globaluniqueidentifier = "unmapped";
        this.datelastmodified = "unmapped";
        this.institutioncode = "unmapped";
        this.collectioncode = "unmapped";
        this.catalognumber = "unmapped";
        this.catalognumbernumeric = "unmapped";
        this.scientificname = "unmapped";
        this.basisofrecord = "unmapped";
        this.informationwithheld = "unmapped";
        this.highertaxon = "unmapped";
        this.kingdom = "unmapped";
        this.phylum = "unmapped";
        this.class1 = "unmapped";
        this.orders = "unmapped";
        this.family = "unmapped";
        this.genus = "unmapped";
        this.specificepithet = "unmapped";
        this.infraspecificepithet = "unmapped";
        this.infraspecificrank = "unmapped";
        this.authoryearofscientificname = "unmapped";
        this.nomenclaturalcode = "unmapped";
        this.identificationqualifier = "unmapped";
        this.identifiedby = "unmapped";
        this.dateidentified = "unmapped";
        this.typestatus = "unmapped";
        this.collectingmethod = "unmapped";
        this.validdistributionflag = "unmapped";
        this.collectornumber = "unmapped";
        this.fieldnumber = "unmapped";
        this.collector = "unmapped";
        this.earliestdatecollected = "unmapped";
        this.latestdatecollected = "unmapped";
        this.verbatimcollectingdate = "unmapped";
        this.dayofyear = "unmapped";
        this.fieldnotes = "unmapped";
        this.highergeography = "unmapped";
        this.continent = "unmapped";
        this.waterbody = "unmapped";
        this.islandgroup = "unmapped";
        this.island = "unmapped";
        this.country = "unmapped";
        this.stateprovince = "unmapped";
        this.county = "unmapped";
        this.locality = "unmapped";
        this.decimallongitude = "unmapped";
        this.verbatimlongitude = "unmapped";
        this.decimallatitude = "unmapped";
        this.verbatimlatitude = "unmapped";
        this.geodeticdatum = "unmapped";
        this.verbatimcoordinatesystem = "unmapped";
        this.georeferenceprotocol = "unmapped";
        this.coordinateuncertaintyinmeters = "unmapped";
        this.georeferenceremarks = "unmapped";
        this.footprintwkt = "unmapped";
        this.minimumelevationinmeters = "unmapped";
        this.maximumelevationinmeters = "unmapped";
        this.verbatimelevation = "unmapped";
        this.minimumdepthinmeters = "unmapped";
        this.maximumdepthinmeters = "unmapped";
        this.sex = "unmapped";
        this.lifestage = "unmapped";
        this.preparations = "unmapped";
        this.individualcount = "unmapped";
        this.genbanknum = "unmapped";
        this.othercatalognumbers = "unmapped";
        this.relatedcatalogitems = "unmapped";
        this.remarks = "unmapped";
        this.attributes = "unmapped";
        this.imageurl = "unmapped";
        this.relatedinformation = "unmapped";
        this.disposition = "unmapped";
        this.pointradiusspatialfit = "unmapped";
        this.footprintspatialfit = "unmapped";
        this.verbatimcoordinates = "unmapped";
        this.georeferencesources = "unmapped";
        this.georeferenceverificationstatus = "unmapped";
    }

    //Atributes
    private String tablename;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String globaluniqueidentifier;
    private String datelastmodified;
    private String institutioncode;
    private String collectioncode;
    private String catalognumber;
    private String catalognumbernumeric;
    private String scientificname;
    private String basisofrecord;
    private String informationwithheld;
    private String highertaxon;
    private String kingdom;
    private String phylum;
    private String class1;
    private String orders;
    private String family;
    private String genus;
    private String specificepithet;
    private String infraspecificepithet;
    private String infraspecificrank;
    private String authoryearofscientificname;
    private String nomenclaturalcode;
    private String identificationqualifier;
    private String identifiedby;
    private String dateidentified;
    private String typestatus;
    private String collectingmethod;
    private String validdistributionflag;
    private String collectornumber;
    private String fieldnumber;
    private String collector;
    private String earliestdatecollected;
    private String latestdatecollected;
    private String verbatimcollectingdate;
    private String dayofyear;
    private String fieldnotes;
    private String highergeography;
    private String continent;
    private String waterbody;
    private String islandgroup;
    private String island;
    private String country;
    private String stateprovince;
    private String county;
    private String locality;
    private String decimallongitude;
    private String verbatimlongitude;
    private String decimallatitude;
    private String verbatimlatitude;
    private String geodeticdatum;
    private String verbatimcoordinatesystem;
    private String georeferenceprotocol;
    private String coordinateuncertaintyinmeters;
    private String georeferenceremarks;
    private String footprintwkt;
    private String minimumelevationinmeters;
    private String maximumelevationinmeters;
    private String verbatimelevation;
    private String minimumdepthinmeters;
    private String maximumdepthinmeters;
    private String sex;
    private String lifestage;
    private String preparations;
    private String individualcount;
    private String genbanknum;
    private String othercatalognumbers;
    private String relatedcatalogitems;
    private String remarks;
    private String attributes;
    private String imageurl;
    private String relatedinformation;
    private String disposition;
    private String pointradiusspatialfit;
    private String footprintspatialfit;
    private String verbatimcoordinates;
    private String georeferencesources;
    private String georeferenceverificationstatus;

    /**
     * @return the globaluniqueidentifier
     */
    public String getGlobaluniqueidentifier() {
        return globaluniqueidentifier;
    }

    /**
     * @param globaluniqueidentifier the globaluniqueidentifier to set
     */
    public void setGlobaluniqueidentifier(String globaluniqueidentifier) {
        this.globaluniqueidentifier = globaluniqueidentifier;
    }

    /**
     * @return the datelastmodified
     */
    public String getDatelastmodified() {
        return datelastmodified;
    }

    /**
     * @param datelastmodified the datelastmodified to set
     */
    public void setDatelastmodified(String datelastmodified) {
        this.datelastmodified = datelastmodified;
    }

    /**
     * @return the institutioncode
     */
    public String getInstitutioncode() {
        return institutioncode;
    }

    /**
     * @param institutioncode the institutioncode to set
     */
    public void setInstitutioncode(String institutioncode) {
        this.institutioncode = institutioncode;
    }

    /**
     * @return the collectioncode
     */
    public String getCollectioncode() {
        return collectioncode;
    }

    /**
     * @param collectioncode the collectioncode to set
     */
    public void setCollectioncode(String collectioncode) {
        this.collectioncode = collectioncode;
    }

    /**
     * @return the catalognumber
     */
    public String getCatalognumber() {
        return catalognumber;
    }

    /**
     * @param catalognumber the catalognumber to set
     */
    public void setCatalognumber(String catalognumber) {
        this.catalognumber = catalognumber;
    }

    /**
     * @return the catalognumbernumeric
     */
    public String getCatalognumbernumeric() {
        return catalognumbernumeric;
    }

    /**
     * @param catalognumbernumeric the catalognumbernumeric to set
     */
    public void setCatalognumbernumeric(String catalognumbernumeric) {
        this.catalognumbernumeric = catalognumbernumeric;
    }

    /**
     * @return the scientificname
     */
    public String getScientificname() {
        return scientificname;
    }

    /**
     * @param scientificname the scientificname to set
     */
    public void setScientificname(String scientificname) {
        this.scientificname = scientificname;
    }

    /**
     * @return the basisofrecord
     */
    public String getBasisofrecord() {
        return basisofrecord;
    }

    /**
     * @param basisofrecord the basisofrecord to set
     */
    public void setBasisofrecord(String basisofrecord) {
        this.basisofrecord = basisofrecord;
    }

    /**
     * @return the informationwithheld
     */
    public String getInformationwithheld() {
        return informationwithheld;
    }

    /**
     * @param informationwithheld the informationwithheld to set
     */
    public void setInformationwithheld(String informationwithheld) {
        this.informationwithheld = informationwithheld;
    }

    /**
     * @return the highertaxon
     */
    public String getHighertaxon() {
        return highertaxon;
    }

    /**
     * @param highertaxon the highertaxon to set
     */
    public void setHighertaxon(String highertaxon) {
        this.highertaxon = highertaxon;
    }

    /**
     * @return the kingdom
     */
    public String getKingdom() {
        return kingdom;
    }

    /**
     * @param kingdom the kingdom to set
     */
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    /**
     * @return the phylum
     */
    public String getPhylum() {
        return phylum;
    }

    /**
     * @param phylum the phylum to set
     */
    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    /**
     * @return the class1
     */
    public String getClass1() {
        return class1;
    }

    /**
     * @param class1 the class1 to set
     */
    public void setClass1(String class1) {
        this.class1 = class1;
    }

    /**
     * @return the orders
     */
    public String getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * @return the family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * @return the genus
     */
    public String getGenus() {
        return genus;
    }

    /**
     * @param genus the genus to set
     */
    public void setGenus(String genus) {
        this.genus = genus;
    }

    /**
     * @return the specificepithet
     */
    public String getSpecificepithet() {
        return specificepithet;
    }

    /**
     * @param specificepithet the specificepithet to set
     */
    public void setSpecificepithet(String specificepithet) {
        this.specificepithet = specificepithet;
    }

    /**
     * @return the infraspecificepithet
     */
    public String getInfraspecificepithet() {
        return infraspecificepithet;
    }

    /**
     * @param infraspecificepithet the infraspecificepithet to set
     */
    public void setInfraspecificepithet(String infraspecificepithet) {
        this.infraspecificepithet = infraspecificepithet;
    }

    /**
     * @return the infraspecificrank
     */
    public String getInfraspecificrank() {
        return infraspecificrank;
    }

    /**
     * @param infraspecificrank the infraspecificrank to set
     */
    public void setInfraspecificrank(String infraspecificrank) {
        this.infraspecificrank = infraspecificrank;
    }

    /**
     * @return the authoryearofscientificname
     */
    public String getAuthoryearofscientificname() {
        return authoryearofscientificname;
    }

    /**
     * @param authoryearofscientificname the authoryearofscientificname to set
     */
    public void setAuthoryearofscientificname(String authoryearofscientificname) {
        this.authoryearofscientificname = authoryearofscientificname;
    }

    /**
     * @return the nomenclaturalcode
     */
    public String getNomenclaturalcode() {
        return nomenclaturalcode;
    }

    /**
     * @param nomenclaturalcode the nomenclaturalcode to set
     */
    public void setNomenclaturalcode(String nomenclaturalcode) {
        this.nomenclaturalcode = nomenclaturalcode;
    }

    /**
     * @return the identificationqualifier
     */
    public String getIdentificationqualifier() {
        return identificationqualifier;
    }

    /**
     * @param identificationqualifier the identificationqualifier to set
     */
    public void setIdentificationqualifier(String identificationqualifier) {
        this.identificationqualifier = identificationqualifier;
    }

    /**
     * @return the identifiedby
     */
    public String getIdentifiedby() {
        return identifiedby;
    }

    /**
     * @param identifiedby the identifiedby to set
     */
    public void setIdentifiedby(String identifiedby) {
        this.identifiedby = identifiedby;
    }

    /**
     * @return the dateidentified
     */
    public String getDateidentified() {
        return dateidentified;
    }

    /**
     * @param dateidentified the dateidentified to set
     */
    public void setDateidentified(String dateidentified) {
        this.dateidentified = dateidentified;
    }

    /**
     * @return the typestatus
     */
    public String getTypestatus() {
        return typestatus;
    }

    /**
     * @param typestatus the typestatus to set
     */
    public void setTypestatus(String typestatus) {
        this.typestatus = typestatus;
    }

    /**
     * @return the collectingmethod
     */
    public String getCollectingmethod() {
        return collectingmethod;
    }

    /**
     * @param collectingmethod the collectingmethod to set
     */
    public void setCollectingmethod(String collectingmethod) {
        this.collectingmethod = collectingmethod;
    }

    /**
     * @return the validdistributionflag
     */
    public String getValiddistributionflag() {
        return validdistributionflag;
    }

    /**
     * @param validdistributionflag the validdistributionflag to set
     */
    public void setValiddistributionflag(String validdistributionflag) {
        this.validdistributionflag = validdistributionflag;
    }

    /**
     * @return the collectornumber
     */
    public String getCollectornumber() {
        return collectornumber;
    }

    /**
     * @param collectornumber the collectornumber to set
     */
    public void setCollectornumber(String collectornumber) {
        this.collectornumber = collectornumber;
    }

    /**
     * @return the fieldnumber
     */
    public String getFieldnumber() {
        return fieldnumber;
    }

    /**
     * @param fieldnumber the fieldnumber to set
     */
    public void setFieldnumber(String fieldnumber) {
        this.fieldnumber = fieldnumber;
    }

    /**
     * @return the collector
     */
    public String getCollector() {
        return collector;
    }

    /**
     * @param collector the collector to set
     */
    public void setCollector(String collector) {
        this.collector = collector;
    }

    /**
     * @return the earliestdatecollected
     */
    public String getEarliestdatecollected() {
        return earliestdatecollected;
    }

    /**
     * @param earliestdatecollected the earliestdatecollected to set
     */
    public void setEarliestdatecollected(String earliestdatecollected) {
        this.earliestdatecollected = earliestdatecollected;
    }

    /**
     * @return the latestdatecollected
     */
    public String getLatestdatecollected() {
        return latestdatecollected;
    }

    /**
     * @param latestdatecollected the latestdatecollected to set
     */
    public void setLatestdatecollected(String latestdatecollected) {
        this.latestdatecollected = latestdatecollected;
    }

    /**
     * @return the verbatimcollectingdate
     */
    public String getVerbatimcollectingdate() {
        return verbatimcollectingdate;
    }

    /**
     * @param verbatimcollectingdate the verbatimcollectingdate to set
     */
    public void setVerbatimcollectingdate(String verbatimcollectingdate) {
        this.verbatimcollectingdate = verbatimcollectingdate;
    }

    /**
     * @return the dayofyear
     */
    public String getDayofyear() {
        return dayofyear;
    }

    /**
     * @param dayofyear the dayofyear to set
     */
    public void setDayofyear(String dayofyear) {
        this.dayofyear = dayofyear;
    }

    /**
     * @return the fieldnotes
     */
    public String getFieldnotes() {
        return fieldnotes;
    }

    /**
     * @param fieldnotes the fieldnotes to set
     */
    public void setFieldnotes(String fieldnotes) {
        this.fieldnotes = fieldnotes;
    }

    /**
     * @return the highergeography
     */
    public String getHighergeography() {
        return highergeography;
    }

    /**
     * @param highergeography the highergeography to set
     */
    public void setHighergeography(String highergeography) {
        this.highergeography = highergeography;
    }

    /**
     * @return the continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @param continent the continent to set
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * @return the waterbody
     */
    public String getWaterbody() {
        return waterbody;
    }

    /**
     * @param waterbody the waterbody to set
     */
    public void setWaterbody(String waterbody) {
        this.waterbody = waterbody;
    }

    /**
     * @return the islandgroup
     */
    public String getIslandgroup() {
        return islandgroup;
    }

    /**
     * @param islandgroup the islandgroup to set
     */
    public void setIslandgroup(String islandgroup) {
        this.islandgroup = islandgroup;
    }

    /**
     * @return the island
     */
    public String getIsland() {
        return island;
    }

    /**
     * @param island the island to set
     */
    public void setIsland(String island) {
        this.island = island;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the stateprovince
     */
    public String getStateprovince() {
        return stateprovince;
    }

    /**
     * @param stateprovince the stateprovince to set
     */
    public void setStateprovince(String stateprovince) {
        this.stateprovince = stateprovince;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     * @param locality the locality to set
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * @return the decimallongitude
     */
    public String getDecimallongitude() {
        return decimallongitude;
    }

    /**
     * @param decimallongitude the decimallongitude to set
     */
    public void setDecimallongitude(String decimallongitude) {
        this.decimallongitude = decimallongitude;
    }

    /**
     * @return the verbatimlongitude
     */
    public String getVerbatimlongitude() {
        return verbatimlongitude;
    }

    /**
     * @param verbatimlongitude the verbatimlongitude to set
     */
    public void setVerbatimlongitude(String verbatimlongitude) {
        this.verbatimlongitude = verbatimlongitude;
    }

    /**
     * @return the decimallatitude
     */
    public String getDecimallatitude() {
        return decimallatitude;
    }

    /**
     * @param decimallatitude the decimallatitude to set
     */
    public void setDecimallatitude(String decimallatitude) {
        this.decimallatitude = decimallatitude;
    }

    /**
     * @return the verbatimlatitude
     */
    public String getVerbatimlatitude() {
        return verbatimlatitude;
    }

    /**
     * @param verbatimlatitude the verbatimlatitude to set
     */
    public void setVerbatimlatitude(String verbatimlatitude) {
        this.verbatimlatitude = verbatimlatitude;
    }

    /**
     * @return the geodeticdatum
     */
    public String getGeodeticdatum() {
        return geodeticdatum;
    }

    /**
     * @param geodeticdatum the geodeticdatum to set
     */
    public void setGeodeticdatum(String geodeticdatum) {
        this.geodeticdatum = geodeticdatum;
    }

    /**
     * @return the verbatimcoordinatesystem
     */
    public String getVerbatimcoordinatesystem() {
        return verbatimcoordinatesystem;
    }

    /**
     * @param verbatimcoordinatesystem the verbatimcoordinatesystem to set
     */
    public void setVerbatimcoordinatesystem(String verbatimcoordinatesystem) {
        this.verbatimcoordinatesystem = verbatimcoordinatesystem;
    }

    /**
     * @return the georeferenceprotocol
     */
    public String getGeoreferenceprotocol() {
        return georeferenceprotocol;
    }

    /**
     * @param georeferenceprotocol the georeferenceprotocol to set
     */
    public void setGeoreferenceprotocol(String georeferenceprotocol) {
        this.georeferenceprotocol = georeferenceprotocol;
    }

    /**
     * @return the coordinateuncertaintyinmeters
     */
    public String getCoordinateuncertaintyinmeters() {
        return coordinateuncertaintyinmeters;
    }

    /**
     * @param coordinateuncertaintyinmeters the coordinateuncertaintyinmeters to set
     */
    public void setCoordinateuncertaintyinmeters(String coordinateuncertaintyinmeters) {
        this.coordinateuncertaintyinmeters = coordinateuncertaintyinmeters;
    }

    /**
     * @return the georeferenceremarks
     */
    public String getGeoreferenceremarks() {
        return georeferenceremarks;
    }

    /**
     * @param georeferenceremarks the georeferenceremarks to set
     */
    public void setGeoreferenceremarks(String georeferenceremarks) {
        this.georeferenceremarks = georeferenceremarks;
    }

    /**
     * @return the footprintwkt
     */
    public String getFootprintwkt() {
        return footprintwkt;
    }

    /**
     * @param footprintwkt the footprintwkt to set
     */
    public void setFootprintwkt(String footprintwkt) {
        this.footprintwkt = footprintwkt;
    }

    /**
     * @return the minimumelevationinmeters
     */
    public String getMinimumelevationinmeters() {
        return minimumelevationinmeters;
    }

    /**
     * @param minimumelevationinmeters the minimumelevationinmeters to set
     */
    public void setMinimumelevationinmeters(String minimumelevationinmeters) {
        this.minimumelevationinmeters = minimumelevationinmeters;
    }

    /**
     * @return the maximumelevationinmeters
     */
    public String getMaximumelevationinmeters() {
        return maximumelevationinmeters;
    }

    /**
     * @param maximumelevationinmeters the maximumelevationinmeters to set
     */
    public void setMaximumelevationinmeters(String maximumelevationinmeters) {
        this.maximumelevationinmeters = maximumelevationinmeters;
    }

    /**
     * @return the verbatimelevation
     */
    public String getVerbatimelevation() {
        return verbatimelevation;
    }

    /**
     * @param verbatimelevation the verbatimelevation to set
     */
    public void setVerbatimelevation(String verbatimelevation) {
        this.verbatimelevation = verbatimelevation;
    }

    /**
     * @return the minimumdepthinmeters
     */
    public String getMinimumdepthinmeters() {
        return minimumdepthinmeters;
    }

    /**
     * @param minimumdepthinmeters the minimumdepthinmeters to set
     */
    public void setMinimumdepthinmeters(String minimumdepthinmeters) {
        this.minimumdepthinmeters = minimumdepthinmeters;
    }

    /**
     * @return the maximumdepthinmeters
     */
    public String getMaximumdepthinmeters() {
        return maximumdepthinmeters;
    }

    /**
     * @param maximumdepthinmeters the maximumdepthinmeters to set
     */
    public void setMaximumdepthinmeters(String maximumdepthinmeters) {
        this.maximumdepthinmeters = maximumdepthinmeters;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the lifestage
     */
    public String getLifestage() {
        return lifestage;
    }

    /**
     * @param lifestage the lifestage to set
     */
    public void setLifestage(String lifestage) {
        this.lifestage = lifestage;
    }

    /**
     * @return the preparations
     */
    public String getPreparations() {
        return preparations;
    }

    /**
     * @param preparations the preparations to set
     */
    public void setPreparations(String preparations) {
        this.preparations = preparations;
    }

    /**
     * @return the individualcount
     */
    public String getIndividualcount() {
        return individualcount;
    }

    /**
     * @param individualcount the individualcount to set
     */
    public void setIndividualcount(String individualcount) {
        this.individualcount = individualcount;
    }

    /**
     * @return the genbanknum
     */
    public String getGenbanknum() {
        return genbanknum;
    }

    /**
     * @param genbanknum the genbanknum to set
     */
    public void setGenbanknum(String genbanknum) {
        this.genbanknum = genbanknum;
    }

    /**
     * @return the othercatalognumbers
     */
    public String getOthercatalognumbers() {
        return othercatalognumbers;
    }

    /**
     * @param othercatalognumbers the othercatalognumbers to set
     */
    public void setOthercatalognumbers(String othercatalognumbers) {
        this.othercatalognumbers = othercatalognumbers;
    }

    /**
     * @return the relatedcatalogitems
     */
    public String getRelatedcatalogitems() {
        return relatedcatalogitems;
    }

    /**
     * @param relatedcatalogitems the relatedcatalogitems to set
     */
    public void setRelatedcatalogitems(String relatedcatalogitems) {
        this.relatedcatalogitems = relatedcatalogitems;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the attributes
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the imageurl
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     * @param imageurl the imageurl to set
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    /**
     * @return the relatedinformation
     */
    public String getRelatedinformation() {
        return relatedinformation;
    }

    /**
     * @param relatedinformation the relatedinformation to set
     */
    public void setRelatedinformation(String relatedinformation) {
        this.relatedinformation = relatedinformation;
    }

    /**
     * @return the disposition
     */
    public String getDisposition() {
        return disposition;
    }

    /**
     * @param disposition the disposition to set
     */
    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    /**
     * @return the pointradiusspatialfit
     */
    public String getPointradiusspatialfit() {
        return pointradiusspatialfit;
    }

    /**
     * @param pointradiusspatialfit the pointradiusspatialfit to set
     */
    public void setPointradiusspatialfit(String pointradiusspatialfit) {
        this.pointradiusspatialfit = pointradiusspatialfit;
    }

    /**
     * @return the footprintspatialfit
     */
    public String getFootprintspatialfit() {
        return footprintspatialfit;
    }

    /**
     * @param footprintspatialfit the footprintspatialfit to set
     */
    public void setFootprintspatialfit(String footprintspatialfit) {
        this.footprintspatialfit = footprintspatialfit;
    }

    /**
     * @return the verbatimcoordinates
     */
    public String getVerbatimcoordinates() {
        return verbatimcoordinates;
    }

    /**
     * @param verbatimcoordinates the verbatimcoordinates to set
     */
    public void setVerbatimcoordinates(String verbatimcoordinates) {
        this.verbatimcoordinates = verbatimcoordinates;
    }

    /**
     * @return the georeferencesources
     */
    public String getGeoreferencesources() {
        return georeferencesources;
    }

    /**
     * @param georeferencesources the georeferencesources to set
     */
    public void setGeoreferencesources(String georeferencesources) {
        this.georeferencesources = georeferencesources;
    }

    /**
     * @return the georeferenceverificationstatus
     */
    public String getGeoreferenceverificationstatus() {
        return georeferenceverificationstatus;
    }

    /**
     * @param georeferenceverificationstatus the georeferenceverificationstatus to set
     */
    public void setGeoreferenceverificationstatus(String georeferenceverificationstatus) {
        this.georeferenceverificationstatus = georeferenceverificationstatus;
    }

    /**
     * @return the driverClassName
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * @param driverClassName the driverClassName to set
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tablename
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * @param tablename the tablename to set
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

}
