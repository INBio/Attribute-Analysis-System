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
import org.inbio.ait.dao.TaxonIndexDAO;
import org.inbio.ait.dao.TaxonInfoIndexDAO;
import org.inbio.ait.manager.SpeciesManager;
import org.inbio.ait.model.TaxonIndex;
import org.inbio.ait.model.TaxonomicalRange;
import org.inbio.ait.util.SpeciesNode;

/**
 *
 * @author esmata
 */
public class SpeciesManagerImpl implements SpeciesManager{

    //Dependency injections
    private TaxonInfoIndexDAO taxonInfoIndexDAO;
    private TaxonIndexDAO taxonIndexDAO;

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

        //Get scientific name
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

        //Get scientific name
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
        //Build the query string base on parameters
        StringBuilder query = new StringBuilder();
        query.append("Select distinct scientific_name_id from ait.taxon_info_index where ");

        //If there is geografical criteria
        if (layerList.length > 0 && !layerList[0].equals("")) {
            query.append("(");
            for (int i = 0; i < layerList.length; i++) {
                String[] aux = layerList[i].split("~");
                String layer = aux[0];
                String polygon = aux[1];
                if (i == layerList.length - 1) { //last element
                    query.append("(layer_table = '" + layer + "' and polygom_id = " + polygon + ")");
                } else {
                    query.append("(layer_table = '" + layer + "' and polygom_id = " + polygon + ") or ");
                }
            }
            query.append(")");
        }

        //If there is taxonomy criteria
        if (taxonList.length > 0 && !taxonList[0].equals("")) {
            if (layerList.length > 0 && !layerList[0].equals("")) {
                query.append(" and (");
            } else {
                query.append("(");
            }
            for (int i = 0; i < taxonList.length; i++) {
                //Get the name and taxonomical level of the specified taxon
                TaxonIndex ti = getTaxonIndexDAO().getTaxonIndexByName(taxonList[i]);
                if (ti.getTaxon_id() != null) {
                    //To search in the specified taxonomyField
                    String levelColum;
                    switch (ti.getTaxon_range().intValue()) {
                        case 1:
                            levelColum = TaxonomicalRange.KINGDOM.getFieldName();
                            break;
                        case 2:
                            levelColum = TaxonomicalRange.PHYLUM.getFieldName();
                            break;
                        case 3:
                            levelColum = TaxonomicalRange.CLASS.getFieldName();
                            break;
                        case 4:
                            levelColum = TaxonomicalRange.ORDER.getFieldName();
                            break;
                        case 5:
                            levelColum = TaxonomicalRange.FAMILY.getFieldName();
                            break;
                        case 6:
                            levelColum = TaxonomicalRange.GENUS.getFieldName();
                            break;
                        case 7:
                            levelColum = TaxonomicalRange.SPECIFICEPITHET.getFieldName();
                            break;
                        default:
                            levelColum = TaxonomicalRange.SCIENTIFICNAME.getFieldName();
                            break;
                    }
                    if (i == taxonList.length - 1) { //last element
                        query.append("(" + levelColum + " = " + ti.getTaxon_id() + ")");
                    } else {
                        query.append("(" + levelColum + " = " + ti.getTaxon_id() + ") or ");
                    }
                } else { //If the taxon doesn't exist on data base
                    String levelColum = TaxonomicalRange.KINGDOM.getFieldName();
                    if (i == taxonList.length - 1) { //last element
                        query.append("(" + levelColum + " = " + -1 + ")");
                    } else {
                        query.append("(" + levelColum + " = " + -1 + ") or ");
                    }
                }

            }
            query.append(")");
        }

        //If there is indicators criteria
        if (indicList.length > 0 && !indicList[0].equals("")) {
            if ((taxonList.length > 0 && !taxonList[0].equals("")) || (layerList.length > 0 && !layerList[0].equals(""))) {
                query.append(" and (");
            } else {
                query.append("(");
            }
            for (int i = 0; i < indicList.length; i++) {
                if (i == indicList.length - 1) { //last element
                    query.append("(indicator_id = " + indicList[i] + ")");
                } else {
                    query.append("(indicator_id = " + indicList[i] + ") or ");
                }
            }
            query.append(")");
        }

        return query.toString();
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

}
