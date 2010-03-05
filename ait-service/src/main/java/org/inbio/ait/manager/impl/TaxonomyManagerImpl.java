/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.manager.impl;

import java.util.List;
import org.inbio.ait.dao.SpecimenDAO;
import org.inbio.ait.manager.TaxonomyManager;
import org.inbio.ait.model.AutocompleteNode;
import org.inbio.ait.model.TaxonomicalRange;

/**
 *
 * @author esmata
 */
public class TaxonomyManagerImpl implements TaxonomyManager {

	// DAO's
    private SpecimenDAO specimenDAO;

    /**
     * To get the elements for autocomplete by taxonomical range
     * @param value
     * @param range
     * @return
     */
    @Override
	public List<AutocompleteNode> getElementsByTaxonomicalRange(String value,int range) {
        if(range == TaxonomicalRange.KINGDOM.getId()){
            return specimenDAO.getElementsByRange(value,range,"kingdom");
        }
        else if(range == TaxonomicalRange.PHYLUM.getId()){
            return specimenDAO.getElementsByRange(value,range,"phylum");
        }
        else if(range == TaxonomicalRange.CLASS.getId()){
            return specimenDAO.getElementsByRange(value,range,"class");
        }
        else if(range == TaxonomicalRange.ORDER.getId()){
            return specimenDAO.getElementsByRange(value,range,"orders");
        }
        else if(range == TaxonomicalRange.FAMILY.getId()){
            return specimenDAO.getElementsByRange(value,range,"family");
        }
        else if(range == TaxonomicalRange.GENUS.getId()){
            return specimenDAO.getElementsByRange(value,range,"genus");
        }
        else if(range == TaxonomicalRange.SPECIFICEPITHET.getId()){
            return specimenDAO.getElementsByRange(value,range,"specificepithet");
        }
        else if(range == TaxonomicalRange.SCIENTIFICNAME.getId()){
            return specimenDAO.getElementsByRange(value,range,"scientificname");
        }
        else return null;
	}

    /**
     * @return the specimenDAO
     */
    public // DAO's
    SpecimenDAO getSpecimenDAO() {
        return specimenDAO;
    }

    /**
     * @param specimenDAO the specimenDAO to set
     */
    public void setSpecimenDAO(SpecimenDAO specimenDAO) {
        this.specimenDAO = specimenDAO;
    }

}

