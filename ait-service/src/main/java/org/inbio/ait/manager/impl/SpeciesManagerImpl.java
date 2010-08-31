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
import org.inbio.ait.dao.sys.TaxonIndexDAO;
import org.inbio.ait.dao.sys.TaxonInfoIndexDAO;
import org.inbio.ait.manager.QueryManager;
import org.inbio.ait.manager.SpeciesManager;
import org.inbio.ait.model.TaxonIndex;
import org.inbio.ait.util.SpeciesNode;
import org.inbio.ait.util.TaxonIndicatorRegionality;

/**
 *
 * @author esmata
 */
public class SpeciesManagerImpl implements SpeciesManager{

    //Dependency injections
    private TaxonInfoIndexDAO taxonInfoIndexDAO;
    private TaxonIndexDAO taxonIndexDAO;
    private QueryManager queryManager;

    /**
     * To get a list of diferent species from specimen repository
     * @param layerList
     * @param taxonList
     * @param indicList
     * @return
     */
    @Override
    public List<String> speciesByCriteria(String[] layerList, String[] taxonList, String[] indicList) {
        String query = queryByCriteria(layerList,taxonList,indicList);

        //get a list of scientific name ids
        List<String> tiiList = getTaxonInfoIndexDAO().
                getScientificNames(query.toString());

        //Create a list of disctint species
        List<String> result = new ArrayList<String>();
        for(int i = 0;i<tiiList.size();i++){
            TaxonIndex aux = getTaxonIndexDAO().getTaxonIndexById(tiiList.get(i));
            result.add(aux.getTaxon_name());
        }

        return result;
    }

    /**
     * To get a list of diferent species from specimen repository
     * @param layerList
     * @param taxonList
     * @param indicList
     * @return species nodes (name,id)
     */
    @Override
    public List<SpeciesNode> speciesNodesByCriteria(String[] layerList, String[] taxonList, String[] indicList) {
        String query = queryByCriteria(layerList,taxonList,indicList);

        //get a list of scientific name ids
        List<String> tiiList = getTaxonInfoIndexDAO().
                getScientificNames(query.toString());

        //Create a list of disctint species
        List<SpeciesNode> result = new ArrayList<SpeciesNode>();
        for(int i = 0;i<tiiList.size();i++){
            TaxonIndex aux = getTaxonIndexDAO().getTaxonIndexById(tiiList.get(i));
            SpeciesNode node = new SpeciesNode();
            node.setId(tiiList.get(i));
            node.setName(aux.getTaxon_name());
            result.add(node);
        }

        return result;
    }

    /**
     * Make the query string
     */
    private String queryByCriteria(String[] layerList, String[] taxonList, String[] indicList) {
        String colum = "scientific_name_id";
        //If taxonList is empty the system is going to include all the kingdoms by default
        if(taxonList[0].equals("")){
            taxonList = this.taxonIndexDAO.getFormatedKingdoms().toArray(taxonList);
        }
        //Query variable
        String query = new String();
        //Getting a list of detailed information for taxon-indicator data
        List<TaxonIndicatorRegionality> regList = queryManager.getRegionalityList(taxonList, indicList);
        //If doesn't exist the taxon-indicator relation (do it as usual)
        if(regList==null || regList.size()==0){
            query = queryManager.elementsByCriteriaSql(layerList, taxonList, indicList, colum);
        }
        else{ //If there is taxon-indicator relations
            query = queryManager.elementsByCriteriaRegSql(layerList, colum, regList);
        }
        //Return sql query
        return query;
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
     * @return the queryManager
     */
    public QueryManager getQueryManager() {
        return queryManager;
    }

    /**
     * @param queryManager the queryManager to set
     */
    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

}
