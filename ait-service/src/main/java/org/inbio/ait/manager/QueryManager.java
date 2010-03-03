/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.manager;

import java.util.List;
import org.inbio.ait.model.TaxonInfoIndex;

/**
 *
 * @author esmata
 */
public interface QueryManager {

    public List<TaxonInfoIndex> getallTaxonInfo();

    /**
     * Count all dwc registers from taxonInfoIndex table that match
     * with the specified query criteria
     * @return
     */
    public Long countByCriteria
            (String[] layerList,String[] taxonList,String[] indicList);

}
