/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao;

import java.util.List;
import org.inbio.ait.model.AutocompleteNode;
import org.inbio.ait.model.Specimen;

/**
 * Data access object interface for the Specimen class
 * @author esmata
 */
public interface SpecimenDAO {

    public List<Specimen> getSpecimenList();

    public List<AutocompleteNode> getElementsByRange(String partialName,int range,String atributeName);

}
