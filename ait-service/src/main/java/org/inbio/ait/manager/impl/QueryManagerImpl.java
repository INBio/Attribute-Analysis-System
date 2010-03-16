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
import org.inbio.ait.dao.TaxonIndexDAO;
import org.inbio.ait.dao.TaxonInfoIndexDAO;
import org.inbio.ait.manager.QueryManager;
import org.inbio.ait.model.TaxonIndex;
import org.inbio.ait.model.TaxonInfoIndex;
import org.inbio.ait.model.TaxonomicalRange;

/**
 * @author esmata
 */
public class QueryManagerImpl implements QueryManager{

    //Dependency injections
    private TaxonInfoIndexDAO taxonInfoIndexDAO;
    private TaxonIndexDAO taxonIndexDAO;

    @Override
    public List<TaxonInfoIndex> getallTaxonInfo() {
        return taxonInfoIndexDAO.getallTaxonInfo();
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
     * Count all dwc registers from taxonInfoIndex table that match
     * with the specified query criteria
     * @return
     */
    @Override
    public Long countByCriteria(String[] layerList, String[] taxonList, String[] indicList) {
        //Build the query string base on parameters
        StringBuilder query = new StringBuilder();
        query.append("Select count(distinct globaluniqueidentifier) from ait.taxon_info_index where ");

        //If there is geografical criteria
        if(layerList.length>0 && !layerList[0].equals("")){
            query.append("(");
            for(int i = 0;i<layerList.length;i++){
                String[] aux = layerList[i].split("~");
                String layer = aux[0];
                String polygon = aux[1];
                if(i==layerList.length-1){ //last element
                    query.append("(layer_table = '"+layer+"' and polygom_id = "+polygon+")");
                }
                else{
                    query.append("(layer_table = '"+layer+"' and polygom_id = "+polygon+") or ");
                }
            }
            query.append(")");
        }

        //If there is taxonomy criteria
        if(taxonList.length>0 && !taxonList[0].equals("")){
            if(layerList.length>0 && !layerList[0].equals("")){
                query.append(" and (");
            }
            else{
                query.append("(");
            }
            for(int i = 0;i<taxonList.length;i++){
                //Get the name and taxonomical level of the specified taxon
                TaxonIndex ti = taxonIndexDAO.getTaxonIndexByName(taxonList[i]);
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
                if(i==taxonList.length-1){ //last element
                    query.append("("+levelColum+" = "+ti.getTaxon_id()+")");
                }
                else{
                    query.append("("+levelColum+" = "+ti.getTaxon_id()+") or ");
                }
            }
            query.append(")");
        }

        //If there is indicators criteria
        if(indicList.length>0 && !indicList[0].equals("")){
            if((taxonList.length>0 && !taxonList[0].equals(""))||(layerList.length>0 && !layerList[0].equals(""))){
                query.append(" and (");
            }
            else{
                query.append("(");
            }
            for(int i = 0;i<indicList.length;i++){
                if(i==indicList.length-1){ //last element
                    query.append("(indicator_id = "+indicList[i]+")");
                }
                else{
                    query.append("(indicator_id = "+indicList[i]+") or ");
                }
            }
            query.append(")");
        }

        //System.out.println(query.toString());

        //Execute query
        return taxonInfoIndexDAO.countTaxonsByQuery(query.toString());
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