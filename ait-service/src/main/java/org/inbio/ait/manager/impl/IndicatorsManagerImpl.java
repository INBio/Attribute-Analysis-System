/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.manager.impl;

import java.util.List;
import org.inbio.ait.dao.IndicatorDAO;
import org.inbio.ait.manager.IndicatorsManager;
import org.inbio.ait.model.AutocompleteNode;

/**
 *
 * @author esmata
 */
public class IndicatorsManagerImpl implements IndicatorsManager{

    private IndicatorDAO indicatorDAO;

    @Override
    public List<AutocompleteNode> getChildNodesByNodeId(int nodeId) {
        return indicatorDAO.getChildNodesByNodeId(nodeId);
    }

    /**
     * @return the indicatorDAO
     */
    public IndicatorDAO getIndicatorDAO() {
        return indicatorDAO;
    }

    /**
     * @param indicatorDAO the indicatorDAO to set
     */
    public void setIndicatorDAO(IndicatorDAO indicatorDAO) {
        this.indicatorDAO = indicatorDAO;
    }

}
