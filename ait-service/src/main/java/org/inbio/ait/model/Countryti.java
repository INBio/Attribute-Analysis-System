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

package org.inbio.ait.model;

/**
 *
 * @author esmata
 */
public class Countryti {

    /**
     * Constructor
     */
    public Countryti(){}

    /**
     * Atributes
     */
    private Long countryId;
    private Long indicatorId;
    private String taxonScientificName;

    /**
     * @return the countryId
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the indicatorId
     */
    public Long getIndicatorId() {
        return indicatorId;
    }

    /**
     * @param indicatorId the indicatorId to set
     */
    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    /**
     * @return the taxonScientificName
     */
    public String getTaxonScientificName() {
        return taxonScientificName;
    }

    /**
     * @param taxonScientificName the taxonScientificName to set
     */
    public void setTaxonScientificName(String taxonScientificName) {
        this.taxonScientificName = taxonScientificName;
    }

}
