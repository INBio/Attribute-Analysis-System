/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao;

import java.util.List;
import org.inbio.ait.model.AutocompleteNode;

/**
 *
 * @author esmata
 */
public interface IndicatorDAO {

    /**
     * Method to get a list of childrem for a especific node ID
     * @param nodeId
     * @return
     */
    public List<AutocompleteNode> getChildNodesByNodeId(int nodeId);

}
