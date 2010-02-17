/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao;

import java.util.List;
import org.inbio.ait.model.Species;

/**
 * Data access object interface for the Species class
 * @author esmata
 */

public interface SpeciesDAO {

    public List<Species> getSpeciesList();

}
