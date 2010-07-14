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
import org.inbio.ait.model.IndiPropertyHolder;
import org.inbio.ait.model.Indicator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author esmata
 */
public class IndiMapper implements ParameterizedRowMapper<Indicator>{
    //Atributes
    private IndiPropertyHolder ph;

    //Constructor
    public IndiMapper(IndiPropertyHolder ph) {
        this.ph = ph;
    }

    @Override
    public Indicator mapRow(ResultSet rs, int rowNum) throws SQLException {
        Indicator i = new Indicator();
        //Mandatory data
        i.setIndicator_id(rs.getLong(ph.getIndicator_id()));
        i.setIndicator_name(rs.getString(ph.getIndicator_name()));
        //Aditional data
        if (!ph.getIndicator_description().equals("unmapped")) {
            i.setIndicator_description(rs.getString(ph.getIndicator_description()));
        }
        if (!ph.getIndicator_applies_to_part().equals("unmapped")) {
            i.setIndicator_applies_to_part(rs.getLong(ph.getIndicator_applies_to_part()));
        }
        if (!ph.getIndicator_ancestor_id().equals("unmapped")) {
            i.setIndicator_ancestor_id(rs.getLong(ph.getIndicator_ancestor_id()));
        }
        if (!ph.getIndicator_references().equals("unmapped")) {
            i.setIndicator_references(rs.getString(ph.getIndicator_references()));
        }
        return i;
    }
}
