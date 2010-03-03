/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.manager;

import java.util.List;
import org.inbio.ait.model.Species;
import org.inbio.ait.model.Specimen;

/**
 *
 * @author esmata
 */
public interface AnalysisManager {

    public List<Species> getAllSpecies();

    public List<Specimen> getAllSpecimens();

}
