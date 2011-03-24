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
import org.inbio.ait.model.CountryPropertyHolder;
import org.inbio.ait.model.Country;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author esmata
 */
public class CountryMapper implements ParameterizedRowMapper<Country>{
    //Atributes
    private CountryPropertyHolder ph;

    //Constructor
    public CountryMapper(CountryPropertyHolder ph) {
        this.ph = ph;
    }

    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country c = new Country();
        //Mandatory data
        c.setCountryId(rs.getLong(ph.getCountryId()));
        c.setCountryName(rs.getString(ph.getCountryName()));
        return c;
    }
}
