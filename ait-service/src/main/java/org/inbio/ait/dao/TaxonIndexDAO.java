/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao;

import org.inbio.ait.model.TaxonIndex;

/**
 *
 * @author esmata
 */
public interface TaxonIndexDAO {

    public TaxonIndex getTaxonIndexByName(String name);

}
