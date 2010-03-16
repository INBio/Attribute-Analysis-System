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

     // Data Access object to species model class 
    private SpeciesDAO speciesDAO;
    private SpecimenDAO specimenDAO;

    // Dependency injections 
    public void setSpeciesDAO(SpeciesDAO speciesDAO) {
        this.speciesDAO = speciesDAO;
    }
    public void setSpecimenDAO(SpecimenDAO specimenDAO) {
        this.specimenDAO = specimenDAO;
    }

    @Override
    public List<Species> getAllSpecies() {
        return speciesDAO.getSpeciesList();
    }

    @Override
    public List<Specimen> getAllSpecimens() {
        return specimenDAO.getSpecimenList();
    }
}
