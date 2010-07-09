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

import java.util.List;
import org.inbio.ait.dao.DwcDataAccessDAO;
import org.inbio.ait.dao.DwcPropertyHolderDAO;
import org.inbio.ait.dao.IndiDataAccessDAO;
import org.inbio.ait.dao.IndiPropertyHolderDAO;
import org.inbio.ait.dao.LayerDataAccessDAO;
import org.inbio.ait.dao.LayerPropertyHolderDAO;
import org.inbio.ait.dao.PlicDataAccessDAO;
import org.inbio.ait.dao.PlicPropertyHolderDAO;
import org.inbio.ait.dao.SelectedLayerDAO;
import org.inbio.ait.dao.TindiDataAccessDAO;
import org.inbio.ait.dao.TindiPropertyHolderDAO;
import org.inbio.ait.manager.ConfigManager;
import org.inbio.ait.model.DwcPropertyHolder;
import org.inbio.ait.model.IndiPropertyHolder;
import org.inbio.ait.model.LayerPropertyHolder;
import org.inbio.ait.model.PlicPropertyHolder;
import org.inbio.ait.model.PostgisLayers;
import org.inbio.ait.model.TindiPropertyHolder;

/**
 *
 * @author esmata
 */
public class ConfigManagerImpl implements ConfigManager{

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

    /**
     * Returns a DwcPropertyHolder java Object with all the
     * information from de dwc.properties file
     */
    @Override
    public DwcPropertyHolder getDwcPropertyHolder(){
        return this.dwcPropertyHolderDAO.getDwcPropertyHolder();
    }

    /**
     * This method save the info from a DwcPropertyHolder java class
     * into the dwc.properties file
     */
    @Override
    public boolean saveToPropertiesFile(DwcPropertyHolder ph) {
        return this.dwcPropertyHolderDAO.saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of dwc registers
     * @return
     */
    @Override
    public int CountDwc(){
        return this.dwcDataAccessDAO.countAll(this.getDwcPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped dwc table
     * through jdbc conection
     */
    @Override
    public List<String> getDwcTableFields(){
        return this.dwcDataAccessDAO.getDwcTableFields(this.getDwcPropertyHolder());
    }

    /**
     * Returns a PlicPropertyHolder java Object with all the
     * information from de plic.properties file
     */
    @Override
    public PlicPropertyHolder getPlicPropertyHolder() {
        return this.plicPropertyHolderDAO.getPlicPropertyHolder();
    }

    /**
     * This method save the info from a PlicPropertyHolder java class
     * into the plic.properties file
     */
    @Override
    public boolean saveToPropertiesFilePlic(PlicPropertyHolder ph) {
        return this.plicPropertyHolderDAO.saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of plic registers
     * @return
     */
    @Override
    public int CountPlic(){
        return this.plicDataAccessDAO.countAll(this.getPlicPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped plic table
     * through jdbc conection
     */
    @Override
    public List<String> getPlicTableFields(){
        return this.plicDataAccessDAO.getPlicTableFields(this.getPlicPropertyHolder());
    }

    /**
     * Returns a IndiPropertyHolder java Object with all the
     * information from the indi.properties file
     */
    @Override
    public IndiPropertyHolder getIndiPropertyHolder() {
        return this.indiPropertyHolderDAO.getIndiPropertyHolder();
    }

    /**
     * This method save the info from a IndiPropertyHolder java object
     * into the indi.properties file
     */
    @Override
    public boolean saveToPropertiesFileIndi(IndiPropertyHolder ph) {
        return this.indiPropertyHolderDAO.saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of indicator registers
     * @return
     */
    @Override
    public int CountIndi(){
        return this.indiDataAccessDAO.countAll(this.getIndiPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped indicators table
     * through jdbc conection
     */
    @Override
    public List<String> getIndiTableFields(){
        return this.indiDataAccessDAO.getIndiTableFields(this.getIndiPropertyHolder());
    }

    /**
     * Returns a TindiPropertyHolder java Object with all the
     * information from the tindi.properties file
     */
    @Override
    public TindiPropertyHolder getTindiPropertyHolder() {
        return this.tindiPropertyHolderDAO.getTindiPropertyHolder();
    }

    /**
     * This method save the info from a TindiPropertyHolder java object
     * into the tindi.properties file
     */
    @Override
    public boolean saveToPropertiesFileTindi(TindiPropertyHolder ph) {
        return this.tindiPropertyHolderDAO.saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of taxon indicator registers
     * @return
     */
    @Override
    public int CountTindi(){
        return this.tindiDataAccessDAO.countAll(this.getTindiPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped taxon indicators table
     * through jdbc conection
     */
    @Override
    public List<String> getTindiTableFields(){
        return this.tindiDataAccessDAO.getTindiTableFields(this.getTindiPropertyHolder());
    }

    /**
     * Returns a LayerPropertyHolder java Object with all the
     * information from de layer.properties file
     */
    @Override
    public LayerPropertyHolder getLayerPropertyHolder(){
        return this.layerPropertyHolderDAO.getLayerPropertyHolder();
    }

    /**
     * This method save the info from a LayerPropertyHolder java class
     * into the layer.properties file
     */
    @Override
    public boolean saveToPropertiesFileLayer(LayerPropertyHolder ph) {
        return this.layerPropertyHolderDAO.saveToPropertiesFile(ph);
    }

    /**
     * Return a list of all the tables (layers) from the postgis
     * @return
     */
    @Override
    public List<String> getLayerTables(){
        return this.layerDataAccessDAO.getLayerTables(this.getLayerPropertyHolder());
    }

    /**
     * Count the total of tables existing in the postgis
     * @return
     */
    @Override
    public int countAllLayerTables(){
        return this.layerDataAccessDAO.countAllTables(this.getLayerPropertyHolder());
    }

    /**
     * Return a PostgisLayers Object that contains not just the list of
     * available layers but also the base layer
     * @return
     */
    @Override
    public PostgisLayers getLayersList() {
        PostgisLayers result = new PostgisLayers();
        List<String> layers = this.selectedLayerDAO.getLayersNames();
        List<String> base = this.selectedLayerDAO.getBaseLayers();
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
            this.selectedLayerDAO.deleteAllLayers();
            //Persist the new selected layers
            for(int i = 0;i<layers.length;i++){
                this.selectedLayerDAO.saveLayers(layers[i]);
            }
            //Indicates the base layer
            this.selectedLayerDAO.updateBase(base);
            return true;
        }
        catch(Exception e){return false;}
    }

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

}
