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

import org.inbio.ait.dao.sys.CountryDAO;
import org.inbio.ait.model.Country;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 * Implementing IndicatorDAO methods
 * @author esmata
 */
public class CountryDAOImpl extends SimpleJdbcDaoSupport implements CountryDAO{

    /**
     * Deletes all information from ait.country table
     * @return
     */
    @Override
    public boolean deleteAllCountries() {
        try {
            String query = "Delete from ait.country";
            getSimpleJdbcTemplate().update(query);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Insert a single country into data base
     * @return
     */
    @Override
    public int InsertCountry(Country country) {
        int result = 0;
        try {
            String insert =
                    "Insert into ait.country (country_id,country_name) " +
                    "values (?,?)";
            result = getSimpleJdbcTemplate().update(insert, country.getCountryId(),
                    country.getCountryName());
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

}
