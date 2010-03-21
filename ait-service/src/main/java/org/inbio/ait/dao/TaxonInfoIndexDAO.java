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

package org.inbio.ait.dao;

import java.util.List;
import org.inbio.ait.model.TaxonInfoIndex;

/**
 *
 * @author esmata
 */
public interface TaxonInfoIndexDAO {

    /**
     * Getting a list that contains all information asociated with the diferent
     * taxons
     * @return
     */
    public List<TaxonInfoIndex> getallTaxonInfo();

    /**
     * Execute any query that returns a list of TaxonInfoIndex model object
     * @param q
     * @return
     */
    public List<TaxonInfoIndex> getTaxonsByQuery(String q);

    /**
     * Execute any count query of TaxonInfoIndex model object
     * @param q
     * @return
     */
    public Long countTaxonsByQuery(String q);

    public List<String> getGlobalUniqueIdentifiers(String q);

}
