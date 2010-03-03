/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.model;

/**
 *
 * @author esmata
 */
public class TaxonIndex {

    //Costructor
    public TaxonIndex(){}

    //Atributes
    private Long taxon_id;
    private String taxon_name;
    private Long taxon_range;

    /**
     * @return the taxon_id
     */
    public Long getTaxon_id() {
        return taxon_id;
    }

    /**
     * @param taxon_id the taxon_id to set
     */
    public void setTaxon_id(Long taxon_id) {
        this.taxon_id = taxon_id;
    }

    /**
     * @return the taxon_name
     */
    public String getTaxon_name() {
        return taxon_name;
    }

    /**
     * @param taxon_name the taxon_name to set
     */
    public void setTaxon_name(String taxon_name) {
        this.taxon_name = taxon_name;
    }

    /**
     * @return the taxon_range
     */
    public Long getTaxon_range() {
        return taxon_range;
    }

    /**
     * @param taxon_range the taxon_range to set
     */
    public void setTaxon_range(Long taxon_range) {
        this.taxon_range = taxon_range;
    }

}
