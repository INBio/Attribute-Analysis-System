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

package org.inbio.ait.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.inbio.ait.model.TaxonIndicator;
import org.inbio.ait.model.TindiPropertyHolder;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author esmata
 */
public class TaxonIndicatorMapper implements ParameterizedRowMapper<TaxonIndicator>{

    //Atributes
    private TindiPropertyHolder ph;

    //Constructor
    public TaxonIndicatorMapper(TindiPropertyHolder ph) {
        this.ph = ph;
    }

    @Override
    public TaxonIndicator mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaxonIndicator ti = new TaxonIndicator();
        //Mandatory data
        ti.setIndicator_id(rs.getLong(ph.getIndicator_id()));
        ti.setTaxon_scientific_name(rs.getString(ph.getTaxon_scientific_name()));
                //Aditional data
        if (!ph.getTaxon_indicator_certainty_level().equals("unmapped")) {
            ti.setTaxon_indicator_certainty_level
                    (rs.getString(ph.getTaxon_indicator_certainty_level()));
        }
        if (!ph.getTaxon_indicator_evaluation_criteria().equals("unmapped")) {
            ti.setTaxon_indicator_evaluation_criteria
                    (rs.getString(ph.getTaxon_indicator_evaluation_criteria()));
        }
        if (!ph.getTaxon_indicator_regionality().equals("unmapped")) {
            ti.setTaxon_indicator_regionality
                    (rs.getString(ph.getTaxon_indicator_regionality()));
        }
        if (!ph.getTaxon_indicator_temporality().equals("unmapped")) {
            ti.setTaxon_indicator_temporality
                    (rs.getString(ph.getTaxon_indicator_temporality()));
        }
        if (!ph.getTaxon_indicator_references().equals("unmapped")) {
            ti.setTaxon_indicator_references
                    (rs.getString(ph.getTaxon_indicator_references()));
        }
        if (!ph.getTaxon_indicator_notes().equals("unmapped")) {
            ti.setTaxon_indicator_notes(rs.getString(ph.getTaxon_indicator_notes()));
        }
        if (!ph.getTaxon_indicator_valuer_person().equals("unmapped")) {
            ti.setTaxon_indicator_valuer_person
                    (rs.getString(ph.getTaxon_indicator_valuer_person()));
        }
        if (!ph.getComponent_part().equals("unmapped")) {
            ti.setComponent_part(rs.getString(ph.getComponent_part()));
        }
        return ti;
    }

}
