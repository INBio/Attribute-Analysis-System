/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.manager;

import java.util.List;
import org.inbio.ait.model.AutocompleteNode;


/**
 * @author jgutierrez
 *
 */
public interface TaxonomyManager {


	/**
	 * Todos los manager que quieran usar autocomplete deben implementar este método
	 *
	 * @param value autocomplete paramater value
	 * @return the map to be returned will have the Key as an integer and the name of the
	 *  option as an String
	 */
	public List<AutocompleteNode> getElementsByTaxonomicalRange(String value, int range);
}

