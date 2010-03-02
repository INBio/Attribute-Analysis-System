/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author esmata
 */
public class QueryManagerImpl implements QueryManager{

    private TaxonInfoIndexDAO taxonInfoIndexDAO;
    private TaxonIndexDAO taxonIndexDAO;

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
    public Long countByCriteria(String[] layerList, String[] taxonList, String[] indicList) {
        //Build the query string base on parameters
        String query = "Select count(distinct globaluniqueidentifier) from ait.taxon_info_index where ";

        //If there is geografical criteria
        if(layerList.length>0 && !layerList[0].equals("")){
            query += "(";
            for(int i = 0;i<layerList.length;i++){
                String layer = layerList[i].split("~")[0];
                String polygon = layerList[i].split("~")[1];
                if(i==layerList.length-1){ //last element
                    query += "(layer_table = '"+layer.split("\\:")[1]+"' and polygom_id = "+polygon.split("\\.")[1]+")";
                }
                else{
                    query += "(layer_table = '"+layer.split("\\:")[1]+"' and polygom_id = "+polygon.split("\\.")[1]+") or ";
                }
            }
            query += ")";
        }

        //If there is taxonomy criteria
        if(taxonList.length>0 && !taxonList[0].equals("")){
            if(layerList.length>0 && !layerList[0].equals("")){
                query += " and (";
            }
            else{
                query += "(";
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
                    query += "("+levelColum+" = "+ti.getTaxon_id()+")";
                }
                else{
                    query += "("+levelColum+" = "+ti.getTaxon_id()+") or ";
                }
            }
            query += ")";
        }

        //If there is indicators criteria
        if(indicList.length>0 && !indicList[0].equals("")){
            if(taxonList.length>0 && !taxonList[0].equals("")){
                query += " and (";
            }
            else{
                query += "(";
            }
            for(int i = 0;i<indicList.length;i++){
                if(i==indicList.length-1){ //last element
                    query += "(indicator_id = "+indicList[i]+")";
                }
                else{
                    query += "(indicator_id = "+indicList[i]+") or ";
                }
            }
            query += ")";
        }

        System.out.println(query);

        //Execute query
        return taxonInfoIndexDAO.countTaxonsByQuery(query);
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
