/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.manager.impl;

import java.util.List;
import org.inbio.ait.dao.TaxonInfoIndexDAO;
import org.inbio.ait.manager.QueryManager;
import org.inbio.ait.model.TaxonInfoIndex;

/**
 *
 * @author esmata
 */
public class QueryManagerImpl implements QueryManager{

    private TaxonInfoIndexDAO taxonInfoIndexDAO;

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
        if(layerList.length>0){
            query += "(";
            for(int i = 0;i<layerList.length;i++){
                if(i==layerList.length-1){ //last element
                    String layer = layerList[i].split("~")[0];
                    String polygon = layerList[i].split("~")[1];
                    query += "(layer_table = '"+layer.split("\\:")[1]+"' and polygom_id = "+polygon.split("\\.")[1]+")";
                }
                else{
                    String layer = layerList[i].split("~")[0];
                    String polygon = layerList[i].split("~")[1];
                    query += "(layer_table = '"+layer.split("\\:")[1]+"' and polygom_id = "+polygon.split("\\.")[1]+") or ";
                }
            }
            query += ")";
        }

        System.out.println(query);

        return 0L;

         /*Select  count(distinct globaluniqueidentifier) from ait.taxon_info_index
         where (capa = c1 and polygon = p1 or capa = c2 and polygon = p2) and
         (reino = r or filo = f) and
         (indicador = i or indicador = i2)*/


        //String query = "Select count(globaluniqueidentifier) from ait.taxon_info_index";
        //Execute query
        //return taxonInfoIndexDAO.countTaxonsByQuery(query);
    }

}
