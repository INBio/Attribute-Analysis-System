/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.manager.impl;

import java.util.List;
import org.inbio.ait.dao.SpeciesDAO;
import org.inbio.ait.dao.SpecimenDAO;
import org.inbio.ait.manager.AnalysisManager;
import org.inbio.ait.model.Species;
import org.inbio.ait.model.Specimen;

/**
 *
 * @author esmata
 */
public class AnalysisManagerImpl implements AnalysisManager{

     // Data Access object to species model class -----------------------
    private SpeciesDAO speciesDAO;
    private SpecimenDAO specimenDAO;
    //-------------------------------------------------------------------

    // Dependency injections ********************************************
    public void setSpeciesDAO(SpeciesDAO speciesDAO) {
        this.speciesDAO = speciesDAO;
    }
    public void setSpecimenDAO(SpecimenDAO specimenDAO) {
        this.specimenDAO = specimenDAO;
    }
    //*******************************************************************

    @Override
    public List<Species> getAllSpecies() {
        return speciesDAO.getSpeciesList();
    }

    @Override
    public List<Specimen> getAllSpecimens() {
        return specimenDAO.getSpecimenList();
    }
}
