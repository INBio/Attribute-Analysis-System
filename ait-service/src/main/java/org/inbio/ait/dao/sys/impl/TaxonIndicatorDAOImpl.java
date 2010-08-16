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

package org.inbio.ait.dao.sys.impl;

import org.inbio.ait.dao.sys.TaxonIndicatorDAO;
import org.inbio.ait.model.TaxonIndicator;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author esmata
 */
public class TaxonIndicatorDAOImpl  extends SimpleJdbcDaoSupport implements TaxonIndicatorDAO{

    /**
     * Deletes all information from ait.taxon_indicator table
     * @return
     */
    @Override
    public boolean deleteAllTaxonIndi() {
        try {
            String query = "Delete from ait.taxon_indicator";
            getSimpleJdbcTemplate().update(query);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Insert a single taxon indicator into data base
     * @return
     */
    @Override
    public int InsertTaxonIndicator(TaxonIndicator ti) {
        int result = 0;
        try {
            String insertQuery =
                    "Insert into ait.taxon_indicator (" +
                    "taxon_indicator_certainty_level,taxon_indicator_evaluation_criteria," +
                    "taxon_indicator_regionality,taxon_indicator_temporality," +
                    "taxon_indicator_references,taxon_indicator_notes," +
                    "taxon_indicator_valuer_person,taxon_scientific_name," +
                    "indicator_id,component_part) " +
                    "values (?,?,?,?,?,?,?,?,?,?)";
            result = getSimpleJdbcTemplate().update(insertQuery,
                    ti.getTaxon_indicator_certainty_level(),ti.getTaxon_indicator_evaluation_criteria(),
                    ti.getTaxon_indicator_regionality(),ti.getTaxon_indicator_temporality(),
                    ti.getTaxon_indicator_references(),ti.getTaxon_indicator_notes(),
                    ti.getTaxon_indicator_valuer_person(),ti.getTaxon_scientific_name(),
                    ti.getIndicator_id(),ti.getComponent_part());
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

}
