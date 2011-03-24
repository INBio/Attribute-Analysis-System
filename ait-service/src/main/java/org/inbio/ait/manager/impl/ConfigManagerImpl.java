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

package org.inbio.ait.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.dao.conn.CountryDataAccessDAO;
import org.inbio.ait.dao.conn.CountryPropertyHolderDAO;
import org.inbio.ait.dao.conn.CountrytiDataAccessDAO;
import org.inbio.ait.dao.conn.CountrytiPropertyHolderDAO;
import org.inbio.ait.dao.conn.DwcDataAccessDAO;
import org.inbio.ait.dao.conn.DwcPropertyHolderDAO;
import org.inbio.ait.dao.conn.IndiDataAccessDAO;
import org.inbio.ait.dao.conn.IndiPropertyHolderDAO;
import org.inbio.ait.dao.conn.LayerDataAccessDAO;
import org.inbio.ait.dao.conn.LayerPropertyHolderDAO;
import org.inbio.ait.dao.conn.PlicDataAccessDAO;
import org.inbio.ait.dao.conn.PlicPropertyHolderDAO;
import org.inbio.ait.dao.sys.SelectedLayerDAO;
import org.inbio.ait.dao.conn.TindiDataAccessDAO;
import org.inbio.ait.dao.conn.TindiPropertyHolderDAO;
import org.inbio.ait.dao.sys.CountryDAO;
import org.inbio.ait.dao.sys.CountrytiDAO;
import org.inbio.ait.dao.sys.GeoserverPropertyHolderDAO;
import org.inbio.ait.dao.sys.IndicatorDAO;
import org.inbio.ait.dao.sys.SpecimenDAO;
import org.inbio.ait.dao.sys.TaxonIndexDAO;
import org.inbio.ait.dao.sys.TaxonIndicatorDAO;
import org.inbio.ait.dao.sys.TaxonInfoIndexDAO;
import org.inbio.ait.manager.ConfigManager;
import org.inbio.ait.model.Country;
import org.inbio.ait.model.CountryPropertyHolder;
import org.inbio.ait.model.Countryti;
import org.inbio.ait.model.CountrytiPropertyHolder;
import org.inbio.ait.model.DwcPropertyHolder;
import org.inbio.ait.model.GeoserverPropertyHolder;
import org.inbio.ait.model.IndiPropertyHolder;
import org.inbio.ait.model.Indicator;
import org.inbio.ait.model.LayerPropertyHolder;
import org.inbio.ait.model.PlicPropertyHolder;
import org.inbio.ait.model.PostgisLayers;
import org.inbio.ait.model.SpecimenBase;
import org.inbio.ait.model.TaxonIndicator;
import org.inbio.ait.model.TaxonomicalRange;
import org.inbio.ait.model.TindiPropertyHolder;

/**
 *
 * @author esmata
 */
public class ConfigManagerImpl implements ConfigManager{

    //Connection
    private DwcPropertyHolderDAO dwcPropertyHolderDAO;
    private DwcDataAccessDAO dwcDataAccessDAO;
    private PlicPropertyHolderDAO plicPropertyHolderDAO;
    private PlicDataAccessDAO plicDataAccessDAO;
    private LayerPropertyHolderDAO layerPropertyHolderDAO;
    private LayerDataAccessDAO layerDataAccessDAO;
    private SelectedLayerDAO selectedLayerDAO;
    private IndiPropertyHolderDAO indiPropertyHolderDAO;
    private IndiDataAccessDAO indiDataAccessDAO;
    private TindiPropertyHolderDAO tindiPropertyHolderDAO;
    private TindiDataAccessDAO tindiDataAccessDAO;
    private CountryPropertyHolderDAO countryPropertyHolderDAO;
    private CountryDataAccessDAO countryDataAccessDAO;
    private CountrytiPropertyHolderDAO countrytiPropertyHolderDAO;
    private CountrytiDataAccessDAO countrytiDataAccessDAO;
    private GeoserverPropertyHolderDAO geoserverPropertyHolderDAO;
    //Copy data
    private IndicatorDAO indicatorDAO;
    private SpecimenDAO specimenDAO;
    private TaxonIndicatorDAO taxonIndicatorDAO;
    private CountryDAO countryDAO;
    private CountrytiDAO countrytiDAO;
    //Indexation
    private TaxonIndexDAO taxonIndexDAO;
    private TaxonInfoIndexDAO taxonInfoIndexDAO;

    /**
     * Returns a DwcPropertyHolder java Object with all the
     * information from de dwc.properties file
     */
    @Override
    public DwcPropertyHolder getDwcPropertyHolder(){
        return this.getDwcPropertyHolderDAO().getDwcPropertyHolder();
    }

    /**
     * This method save the info from a DwcPropertyHolder java class
     * into the dwc.properties file
     */
    @Override
    public boolean saveToPropertiesFile(DwcPropertyHolder ph) {
        return this.getDwcPropertyHolderDAO().saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of dwc registers
     * @return
     */
    @Override
    public int CountDwc(){
        return this.getDwcDataAccessDAO().countAll(this.getDwcPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped dwc table
     * through jdbc conection
     */
    @Override
    public List<String> getDwcTableFields(){
        return this.getDwcDataAccessDAO().getDwcTableFields(this.getDwcPropertyHolder());
    }

    /**
     * Returns a PlicPropertyHolder java Object with all the
     * information from de plic.properties file
     */
    @Override
    public PlicPropertyHolder getPlicPropertyHolder() {
        return this.getPlicPropertyHolderDAO().getPlicPropertyHolder();
    }

    /**
     * This method save the info from a PlicPropertyHolder java class
     * into the plic.properties file
     */
    @Override
    public boolean saveToPropertiesFilePlic(PlicPropertyHolder ph) {
        return this.getPlicPropertyHolderDAO().saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of plic registers
     * @return
     */
    @Override
    public int CountPlic(){
        return this.getPlicDataAccessDAO().countAll(this.getPlicPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped plic table
     * through jdbc conection
     */
    @Override
    public List<String> getPlicTableFields(){
        return this.getPlicDataAccessDAO().getPlicTableFields(this.getPlicPropertyHolder());
    }

    /**
     * Returns a IndiPropertyHolder java Object with all the
     * information from the indi.properties file
     */
    @Override
    public IndiPropertyHolder getIndiPropertyHolder() {
        return this.getIndiPropertyHolderDAO().getIndiPropertyHolder();
    }

    /**
     * This method save the info from a IndiPropertyHolder java object
     * into the indi.properties file
     */
    @Override
    public boolean saveToPropertiesFileIndi(IndiPropertyHolder ph) {
        return this.getIndiPropertyHolderDAO().saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of indicator registers
     * @return
     */
    @Override
    public int CountIndi(){
        return this.getIndiDataAccessDAO().countAll(this.getIndiPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped indicators table
     * through jdbc conection
     */
    @Override
    public List<String> getIndiTableFields(){
        return this.getIndiDataAccessDAO().getIndiTableFields(this.getIndiPropertyHolder());
    }

    /**
     * Returns a TindiPropertyHolder java Object with all the
     * information from the tindi.properties file
     */
    @Override
    public TindiPropertyHolder getTindiPropertyHolder() {
        return this.getTindiPropertyHolderDAO().getTindiPropertyHolder();
    }

    /**
     * This method save the info from a TindiPropertyHolder java object
     * into the tindi.properties file
     */
    @Override
    public boolean saveToPropertiesFileTindi(TindiPropertyHolder ph) {
        return this.getTindiPropertyHolderDAO().saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of taxon indicator registers
     * @return
     */
    @Override
    public int CountTindi(){
        return this.getTindiDataAccessDAO().countAll(this.getTindiPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped taxon indicators table
     * through jdbc conection
     */
    @Override
    public List<String> getTindiTableFields(){
        return this.getTindiDataAccessDAO().getTindiTableFields(this.getTindiPropertyHolder());
    }

    /**
     * Returns a CountrytiPropertyHolder java Object with all the
     * information from the countryti.properties file
     */
    @Override
    public CountrytiPropertyHolder getCountrytiPropertyHolder() {
        return this.getCountrytiPropertyHolderDAO().getCountrytiPropertyHolder();
    }

    /**
     * This method save the info from a CountrytiPropertyHolder java object
     * into the countryti.properties file
     */
    @Override
    public boolean saveToPropertiesFileCountryti(CountrytiPropertyHolder ph) {
        return this.getCountrytiPropertyHolderDAO().saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of countryti registers
     * @return
     */
    @Override
    public int CountCountryti(){
        return this.getCountrytiDataAccessDAO().countAll(this.getCountrytiPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped countryti table
     * through jdbc conection
     */
    @Override
    public List<String> getCountrytiTableFields(){
        return this.getCountrytiDataAccessDAO().getCountrytiTableFields(this.getCountrytiPropertyHolder());
    }

    /**
     * Returns a CountryPropertyHolder java Object with all the
     * information from the country.properties file
     */
    @Override
    public CountryPropertyHolder getCountryPropertyHolder() {
        return this.getCountryPropertyHolderDAO().getCountryPropertyHolder();
    }

    /**
     * This method save the info from a CountryPropertyHolder java object
     * into the country.properties file
     */
    @Override
    public boolean saveToPropertiesFileCountry(CountryPropertyHolder ph) {
        return this.getCountryPropertyHolderDAO().saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of country registers
     * @return
     */
    @Override
    public int CountCountry(){
        return this.getCountryDataAccessDAO().countAll(this.getCountryPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped country table
     * through jdbc conection
     */
    @Override
    public List<String> getCountryTableFields(){
        return this.getCountryDataAccessDAO().getCountryTableFields(this.getCountryPropertyHolder());
    }

    @Override
    public GeoserverPropertyHolder getGeoPropertyHolder() {
        return this.getGeoserverPropertyHolderDAO().getPropertyHolder();
    }

    @Override
    public boolean saveToPropertiesFileGeo(GeoserverPropertyHolder ph) {
        return this.getGeoserverPropertyHolderDAO().saveToPropertiesFile(ph);
    }

    /**
     * Returns a LayerPropertyHolder java Object with all the
     * information from de layer.properties file
     */
    @Override
    public LayerPropertyHolder getLayerPropertyHolder(){
        return this.getLayerPropertyHolderDAO().getLayerPropertyHolder();
    }

    /**
     * This method save the info from a LayerPropertyHolder java class
     * into the layer.properties file
     */
    @Override
    public boolean saveToPropertiesFileLayer(LayerPropertyHolder ph) {
        return this.getLayerPropertyHolderDAO().saveToPropertiesFile(ph);
    }

    /**
     * Return a list of all the tables (layers) from the postgis
     * @return
     */
    @Override
    public List<String> getLayerTables(){
        return this.getLayerDataAccessDAO().getLayerTables(this.getLayerPropertyHolder());
    }

    /**
     * Count the total of tables existing in the postgis
     * @return
     */
    @Override
    public int countAllLayerTables(){
        return this.getLayerDataAccessDAO().countAllTables(this.getLayerPropertyHolder());
    }

    /**
     * Return a PostgisLayers Object that contains not just the list of
     * available layers but also the base layer
     * @return
     */
    @Override
    public PostgisLayers getLayersList() {
        PostgisLayers result = new PostgisLayers();
        List<String> layers = this.getSelectedLayerDAO().getLayersNames();
        List<String> base = this.getSelectedLayerDAO().getBaseLayers();
        String[] aux = new String[layers.size()];
        for(int i = 0;i<layers.size();i++){
            aux[i] = layers.get(i);
        }
        result.setLayers(aux);
        if(base.size()>0){
            result.setBase(base.get(0));
        }
        return result;
    }

    /**
     * Persist a list of selected layers, including the base property
     */
    @Override
    public boolean saveLayersList(PostgisLayers pl){
        String[] layers = pl.getLayers();
        String base = pl.getBase();
        try{
            //Delete the selected layers from db
            this.getSelectedLayerDAO().deleteAllLayers();
            //Persist the new selected layers
            for(int i = 0;i<layers.length;i++){
                this.getSelectedLayerDAO().saveLayers(layers[i]);
            }
            //Indicates the base layer
            this.getSelectedLayerDAO().updateBase(base);
            return true;
        }
        catch(Exception e){return false;}
    }

    /**
     * Method to migrate data from external dwc table to system dwc table
     * @return
     *  -2 error conecting to the external dwc db
     *  -1 error in data migration
     *  <1 number of afected rows (# of insertions)
     */
    @Override
    public int migrateDwc(){
        DwcPropertyHolder ph = this.getDwcPropertyHolder();
        //Check if the conecction was established already        
        int check = this.getDwcDataAccessDAO().countAll(ph);        
        if(check == -1){
            return -2; //error
        }
        else{
            //Do migration
            return this.migrateSpecimensData(ph,check);
        }
    }

    /**
     * This method gets the specimens information from external data base and copy
     * this information into the darwin_core system table
     * @param ph Connection data
     * @param totalDwc total of registers
     * @return number of afected rows (copied in this case)
     */
    private int migrateSpecimensData(DwcPropertyHolder ph,int totalDwc){
        //Copy data from external dwc table to system dwc table
        List<SpecimenBase> spList = new ArrayList<SpecimenBase>();
        try {
            //Delete existing data
            boolean delete = this.getSpecimenDAO().deleteAllSpecimens();
            if (!delete) {
                return -1; //error deleting existing data
            }
            //Paginating the migration proccess
            int afectedRows = 0;
            for (int i = 0; i < totalDwc; i+=500) {
                //Get data from external db
                spList = this.getDwcDataAccessDAO().getAllSpecimenBase(ph, 500, i);
                //Insert external data into system db
                for (SpecimenBase sp : spList) {
                    int a = this.getSpecimenDAO().InsertSpecimen(sp);
                    afectedRows += a;
                }
            }//Ends pagination
            return afectedRows;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * Method to migrate data from external indicators table to system tables
     * @return
     *  -2 error conecting to the external dwc db
     *  -1 error in data migration
     *  <1 number of afected rows (# of insertions)
     */
    @Override
    public int migrateIndicators(){
        IndiPropertyHolder ph = this.getIndiPropertyHolder();
        //Check if the conecction was established already
        int check = this.getIndiDataAccessDAO().countAll(ph);
        if(check == -1){
            return -2; //error
        }
        else{
            //Do migration
            return this.migrateIndicatorsData(ph,check);
        }
    }

    /**
     * This method gets the indicators information from external data base and copy
     * this information into the indicator system table
     * @param ph Connection data
     * @param totalIndi total of indicators registers
     * @return number of afected rows (copied in this case)
     */
    private int migrateIndicatorsData(IndiPropertyHolder ph,int totalIndi){
        //Copy data from external indicators table to system indicators table
        List<Indicator> iList = new ArrayList<Indicator>();
        try {
            //Delete existing data
            boolean delete = this.getIndicatorDAO().deleteAllIndicators();
            if (!delete) {
                return -1; //error deleting existing data
            }
            //Paginating the migration proccess
            int afectedRows = 0;
            for (int i = 0; i < totalIndi; i+=500) {
                //Get data from external db
                iList = this.getIndiDataAccessDAO().getAllIndicators(ph, 500, i);
                //Insert external data into system db
                for (Indicator indi : iList) {
                    int a = this.getIndicatorDAO().InsertIndicator(indi);
                    afectedRows += a;
                }                
            }//Ends pagination
            return afectedRows;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * Method to migrate data from external country table to system tables
     * @return
     *  -2 error conecting to the external dwc db
     *  -1 error in data migration
     *  <1 number of afected rows (# of insertions)
     */
    @Override
    public int migrateCountries(){
        CountryPropertyHolder ph = this.getCountryPropertyHolder();
        //Check if the conecction was established already
        int check = this.getCountryDataAccessDAO().countAll(ph);
        if(check == -1){
            return -2; //error
        }
        else{
            //Do migration
            return this.migrateCountriesData(ph,check);
        }
    }

    /**
     * This method gets the countries information from external data base and copy
     * this information into the country system table
     * @param ph Connection data
     * @param totalCountries total of countries registers
     * @return number of afected rows (copied in this case)
     */
    private int migrateCountriesData(CountryPropertyHolder ph,int totalCountries){
        //Copy data from external countries table to system countiey table
        List<Country> cList = new ArrayList<Country>();
        try {
            //Delete existing data
            boolean delete = this.getCountryDAO().deleteAllCountries();
            if (!delete) {
                return -1; //error deleting existing data
            }
            //Paginating the migration proccess
            int afectedRows = 0;
            for (int i = 0; i < totalCountries; i+=500) {
                //Get data from external db
                cList = this.getCountryDataAccessDAO().getAllCountries(ph, 500, i);
                //Insert external data into system db
                for (Country cou : cList) {
                    int a = this.getCountryDAO().InsertCountry(cou);
                    afectedRows += a;
                }
            }//Ends pagination
            return afectedRows;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

        /**
     * Method to migrate data from external taxon_indicator_country table to system tables
     * @return
     *  -2 error conecting to the external dwc db
     *  -1 error in data migration
     *  <1 number of afected rows (# of insertions)
     */
    @Override
    public int migrateCountriesti(){
        CountrytiPropertyHolder ph = this.getCountrytiPropertyHolder();
        //Check if the conecction was established already
        int check = this.getCountrytiDataAccessDAO().countAll(ph);
        if(check == -1){
            return -2; //error
        }
        else{
            //Do migration
            return this.migrateCountriestiData(ph,check);
        }
    }

    /**
     * This method gets the taxon_indicator_country information from external data base and copy
     * this information into the taxon_indicator_country system table
     * @param ph Connection data
     * @param totalCountriesti total of taxon_indicator_country registers
     * @return number of afected rows (copied in this case)
     */
    private int migrateCountriestiData(CountrytiPropertyHolder ph,int totalCountriesti){
        //Copy data from external countries table to system countiey table
        List<Countryti> cList = new ArrayList<Countryti>();
        try {
            //Delete existing data
            boolean delete = this.getCountrytiDAO().deleteAllCountriesti();
            if (!delete) {
                return -1; //error deleting existing data
            }
            //Paginating the migration proccess
            int afectedRows = 0;
            for (int i = 0; i < totalCountriesti; i+=500) {
                //Get data from external db
                cList = this.getCountrytiDataAccessDAO().getAllCountriesti(ph, 500, i);
                //Insert external data into system db
                for (Countryti couti : cList) {
                    int a = this.getCountrytiDAO().InsertCountryti(couti);
                    afectedRows += a;
                }
            }//Ends pagination
            return afectedRows;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * Method to migrate data from external taxon indicators table to system tables
     * @return
     *  -2 error conecting to the external dwc db
     *  -1 error in data migration
     *  <1 number of afected rows (# of insertions)
     */
    @Override
    public int migrateTaxonIndicators(){
        TindiPropertyHolder ph = this.getTindiPropertyHolder();
        //Check if the conecction was established already
        int check = this.getTindiDataAccessDAO().countAll(ph);
        if(check == -1){
            return -2; //error
        }
        else{
            //Do migration
            return this.migrateTaxonIndicatorsData(ph,check);
        }
    }

    /**
     * This method gets the taxon indicators data from external data base and copy
     * this information into the taxon indicator system table
     * @param ph Connection data
     * @param totalTindi total of taxon indicator registers
     * @return number of afected rows (copied rows)
     */
    private int migrateTaxonIndicatorsData(TindiPropertyHolder ph,int totalTindi){
        //Copy data from external taxon indicators table to system table
        List<TaxonIndicator> tiList = new ArrayList<TaxonIndicator>();
        try {
            //Delete existing data
            boolean delete = this.getTaxonIndicatorDAO().deleteAllTaxonIndi();
            if (!delete) {
                return -1; //error deleting existing data
            }
            //Paginating the migration proccess
            int afectedRows = 0;
            for (int i = 0; i < totalTindi; i+=500) {
                //Get data from external db
                tiList = this.getTindiDataAccessDAO().getAllTaxonIndicators(ph, 500, i);
                //Insert external data into system db
                for (TaxonIndicator ti : tiList) {
                    int a = this.getTaxonIndicatorDAO().InsertTaxonIndicator(ti);
                    afectedRows += a;
                }
            }//Ends pagination
            return afectedRows;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * Indexation for the taxon_index table that contains all the names from
     * the taxonomical hierarchy
     * @return boolean indicating if the proccess was successfull or failed
     */
    @Override
    public boolean taxonIndexProccess() {
        try {
            //Delete existing data
            this.getTaxonIndexDAO().deleteAllTaxonIndex();
            //Execute indexing proccess
            this.getTaxonIndexDAO().taxonIndexByRange
                    (1, TaxonomicalRange.KINGDOM.getDwcFieldName());
            this.getTaxonIndexDAO().taxonIndexByRange
                    (2, TaxonomicalRange.PHYLUM.getDwcFieldName());
            this.getTaxonIndexDAO().taxonIndexByRange
                    (3, TaxonomicalRange.CLASS.getDwcFieldName());
            this.getTaxonIndexDAO().taxonIndexByRange
                    (4, TaxonomicalRange.ORDER.getDwcFieldName());
            this.getTaxonIndexDAO().taxonIndexByRange
                    (5, TaxonomicalRange.FAMILY.getDwcFieldName());
            this.getTaxonIndexDAO().taxonIndexByRange
                    (6, TaxonomicalRange.GENUS.getDwcFieldName());
            this.getTaxonIndexDAO().taxonIndexByRange
                    (7, TaxonomicalRange.SPECIFICEPITHET.getDwcFieldName());
            this.getTaxonIndexDAO().taxonIndexByRange
                    (8, TaxonomicalRange.SCIENTIFICNAME.getDwcFieldName());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Indexation for the taxon_info_index table that contains all the indexed
     * information prepared specifically for quering
     * @return if the proccess was successfully completed or not
     */
    @Override
    public boolean taxonInfoIndexProccess() {
        //Do indexation
        try {
            //Clean dwc data before index that data
            this.getSpecimenDAO().cleanDwcData();
            //Delete existing data
            this.getTaxonInfoIndexDAO().deleteAllTaxonInfoIndex();
            //Get a list of selected layers
            List<String> layers = this.getSelectedLayerDAO().getLayersNames();
            //Initial indexing process (Step1)
            for(String l : layers){
                this.getTaxonInfoIndexDAO().taxonInfoIndex(l);
            }
            //Setting the complete indexed taxonomy on ait.taxon_info_index (Step2)
            this.getTaxonInfoIndexDAO().completeIndexedTaxa();
            //Finally creates column index
            this.getTaxonIndexDAO().createColumnIndex();
            this.getTaxonInfoIndexDAO().createColumnIndex();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /* ----------------------------------------------------
    ----------------- Getters y Setters -------------------
    -----------------------------------------------------*/

    /**
     * @return the dwcPropertyHolderDAO
     */
    public DwcPropertyHolderDAO getDwcPropertyHolderDAO() {
        return dwcPropertyHolderDAO;
    }

    /**
     * @param dwcPropertyHolderDAO the dwcPropertyHolderDAO to set
     */
    public void setDwcPropertyHolderDAO(DwcPropertyHolderDAO dwcPropertyHolderDAO) {
        this.dwcPropertyHolderDAO = dwcPropertyHolderDAO;
    }

    /**
     * @return the dwcDataAccessDAO
     */
    public DwcDataAccessDAO getDwcDataAccessDAO() {
        return dwcDataAccessDAO;
    }

    /**
     * @param dwcDataAccessDAO the dwcDataAccessDAO to set
     */
    public void setDwcDataAccessDAO(DwcDataAccessDAO dwcDataAccessDAO) {
        this.dwcDataAccessDAO = dwcDataAccessDAO;
    }

    /**
     * @return the plicPropertyHolderDAO
     */
    public PlicPropertyHolderDAO getPlicPropertyHolderDAO() {
        return plicPropertyHolderDAO;
    }

    /**
     * @param plicPropertyHolderDAO the plicPropertyHolderDAO to set
     */
    public void setPlicPropertyHolderDAO(PlicPropertyHolderDAO plicPropertyHolderDAO) {
        this.plicPropertyHolderDAO = plicPropertyHolderDAO;
    }

    /**
     * @return the plicDataAccessDAO
     */
    public PlicDataAccessDAO getPlicDataAccessDAO() {
        return plicDataAccessDAO;
    }

    /**
     * @param plicDataAccessDAO the plicDataAccessDAO to set
     */
    public void setPlicDataAccessDAO(PlicDataAccessDAO plicDataAccessDAO) {
        this.plicDataAccessDAO = plicDataAccessDAO;
    }

    /**
     * @return the layerPropertyHolderDAO
     */
    public LayerPropertyHolderDAO getLayerPropertyHolderDAO() {
        return layerPropertyHolderDAO;
    }

    /**
     * @param layerPropertyHolderDAO the layerPropertyHolderDAO to set
     */
    public void setLayerPropertyHolderDAO(LayerPropertyHolderDAO layerPropertyHolderDAO) {
        this.layerPropertyHolderDAO = layerPropertyHolderDAO;
    }

    /**
     * @return the layerDataAccessDAO
     */
    public LayerDataAccessDAO getLayerDataAccessDAO() {
        return layerDataAccessDAO;
    }

    /**
     * @param layerDataAccessDAO the layerDataAccessDAO to set
     */
    public void setLayerDataAccessDAO(LayerDataAccessDAO layerDataAccessDAO) {
        this.layerDataAccessDAO = layerDataAccessDAO;
    }

    /**
     * @return the selectedLayerDAO
     */
    public SelectedLayerDAO getSelectedLayerDAO() {
        return selectedLayerDAO;
    }

    /**
     * @param selectedLayerDAO the selectedLayerDAO to set
     */
    public void setSelectedLayerDAO(SelectedLayerDAO selectedLayerDAO) {
        this.selectedLayerDAO = selectedLayerDAO;
    }

    /**
     * @return the indiPropertyHolderDAO
     */
    public IndiPropertyHolderDAO getIndiPropertyHolderDAO() {
        return indiPropertyHolderDAO;
    }

    /**
     * @param indiPropertyHolderDAO the indiPropertyHolderDAO to set
     */
    public void setIndiPropertyHolderDAO(IndiPropertyHolderDAO indiPropertyHolderDAO) {
        this.indiPropertyHolderDAO = indiPropertyHolderDAO;
    }

    /**
     * @return the indiDataAccessDAO
     */
    public IndiDataAccessDAO getIndiDataAccessDAO() {
        return indiDataAccessDAO;
    }

    /**
     * @param indiDataAccessDAO the indiDataAccessDAO to set
     */
    public void setIndiDataAccessDAO(IndiDataAccessDAO indiDataAccessDAO) {
        this.indiDataAccessDAO = indiDataAccessDAO;
    }

    /**
     * @return the tindiPropertyHolderDAO
     */
    public TindiPropertyHolderDAO getTindiPropertyHolderDAO() {
        return tindiPropertyHolderDAO;
    }

    /**
     * @param tindiPropertyHolderDAO the tindiPropertyHolderDAO to set
     */
    public void setTindiPropertyHolderDAO(TindiPropertyHolderDAO tindiPropertyHolderDAO) {
        this.tindiPropertyHolderDAO = tindiPropertyHolderDAO;
    }

    /**
     * @return the tindiDataAccessDAO
     */
    public TindiDataAccessDAO getTindiDataAccessDAO() {
        return tindiDataAccessDAO;
    }

    /**
     * @param tindiDataAccessDAO the tindiDataAccessDAO to set
     */
    public void setTindiDataAccessDAO(TindiDataAccessDAO tindiDataAccessDAO) {
        this.tindiDataAccessDAO = tindiDataAccessDAO;
    }

    /**
     * @return the indicatorDAO
     */
    public IndicatorDAO getIndicatorDAO() {
        return indicatorDAO;
    }

    /**
     * @param indicatorDAO the indicatorDAO to set
     */
    public void setIndicatorDAO(IndicatorDAO indicatorDAO) {
        this.indicatorDAO = indicatorDAO;
    }

    /**
     * @return the specimenDAO
     */
    public SpecimenDAO getSpecimenDAO() {
        return specimenDAO;
    }

    /**
     * @param specimenDAO the specimenDAO to set
     */
    public void setSpecimenDAO(SpecimenDAO specimenDAO) {
        this.specimenDAO = specimenDAO;
    }

    /**
     * @return the taxonIndicatorDAO
     */
    public TaxonIndicatorDAO getTaxonIndicatorDAO() {
        return taxonIndicatorDAO;
    }

    /**
     * @param taxonIndicatorDAO the taxonIndicatorDAO to set
     */
    public void setTaxonIndicatorDAO(TaxonIndicatorDAO taxonIndicatorDAO) {
        this.taxonIndicatorDAO = taxonIndicatorDAO;
    }

    /**
     * @return the taxonIndexDAO
     */
    public TaxonIndexDAO getTaxonIndexDAO() {
        return taxonIndexDAO;
    }

    /**
     * @param taxonIndexDAO the taxonIndexDAO to set
     */
    public void setTaxonIndexDAO(TaxonIndexDAO taxonIndexDAO) {
        this.taxonIndexDAO = taxonIndexDAO;
    }

    /**
     * @return the taxonInfoIndexDAO
     */
    public TaxonInfoIndexDAO getTaxonInfoIndexDAO() {
        return taxonInfoIndexDAO;
    }

    /**
     * @param taxonInfoIndexDAO the taxonInfoIndexDAO to set
     */
    public void setTaxonInfoIndexDAO(TaxonInfoIndexDAO taxonInfoIndexDAO) {
        this.taxonInfoIndexDAO = taxonInfoIndexDAO;
    }

    /**
     * @return the geoserverPropertyHolderDAO
     */
    public GeoserverPropertyHolderDAO getGeoserverPropertyHolderDAO() {
        return geoserverPropertyHolderDAO;
    }

    /**
     * @param geoserverPropertyHolderDAO the geoserverPropertyHolderDAO to set
     */
    public void setGeoserverPropertyHolderDAO(GeoserverPropertyHolderDAO geoserverPropertyHolderDAO) {
        this.geoserverPropertyHolderDAO = geoserverPropertyHolderDAO;
    }

    /**
     * @return the countryPropertyHolderDAO
     */
    public CountryPropertyHolderDAO getCountryPropertyHolderDAO() {
        return countryPropertyHolderDAO;
    }

    /**
     * @param countryPropertyHolderDAO the countryPropertyHolderDAO to set
     */
    public void setCountryPropertyHolderDAO(CountryPropertyHolderDAO countryPropertyHolderDAO) {
        this.countryPropertyHolderDAO = countryPropertyHolderDAO;
    }

    /**
     * @return the countryDataAccessDAO
     */
    public CountryDataAccessDAO getCountryDataAccessDAO() {
        return countryDataAccessDAO;
    }

    /**
     * @param countryDataAccessDAO the countryDataAccessDAO to set
     */
    public void setCountryDataAccessDAO(CountryDataAccessDAO countryDataAccessDAO) {
        this.countryDataAccessDAO = countryDataAccessDAO;
    }

    /**
     * @return the countrytiPropertyHolderDAO
     */
    public CountrytiPropertyHolderDAO getCountrytiPropertyHolderDAO() {
        return countrytiPropertyHolderDAO;
    }

    /**
     * @param countrytiPropertyHolderDAO the countrytiPropertyHolderDAO to set
     */
    public void setCountrytiPropertyHolderDAO(CountrytiPropertyHolderDAO countrytiPropertyHolderDAO) {
        this.countrytiPropertyHolderDAO = countrytiPropertyHolderDAO;
    }

    /**
     * @return the countrytiDataAccessDAO
     */
    public CountrytiDataAccessDAO getCountrytiDataAccessDAO() {
        return countrytiDataAccessDAO;
    }

    /**
     * @param countrytiDataAccessDAO the countrytiDataAccessDAO to set
     */
    public void setCountrytiDataAccessDAO(CountrytiDataAccessDAO countrytiDataAccessDAO) {
        this.countrytiDataAccessDAO = countrytiDataAccessDAO;
    }

    /**
     * @return the countryDAO
     */
    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    /**
     * @param countryDAO the countryDAO to set
     */
    public void setCountryDAO(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    /**
     * @return the countrytiDAO
     */
    public CountrytiDAO getCountrytiDAO() {
        return countrytiDAO;
    }

    /**
     * @param countrytiDAO the countrytiDAO to set
     */
    public void setCountrytiDAO(CountrytiDAO countrytiDAO) {
        this.countrytiDAO = countrytiDAO;
    }
}
