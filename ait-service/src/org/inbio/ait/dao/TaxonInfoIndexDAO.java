/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao;

import java.util.List;
import org.inbio.ait.model.TaxonInfoIndex;

/**
 *
 * @author esmata
 */
public interface TaxonInfoIndexDAO {

    public List<TaxonInfoIndex> getallTaxonInfo();

    public List<TaxonInfoIndex> getTaxonsByQuery(String q);

    public Long countTaxonsByQuery(String q);

}
