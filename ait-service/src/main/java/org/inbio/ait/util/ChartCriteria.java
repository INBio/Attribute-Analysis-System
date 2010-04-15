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

package org.inbio.ait.util;

/**
 *
 * @author esmata
 * See changeAxis() javascript method on statistical.jsp
 */
public enum ChartCriteria {
    
    TAXONOMICAL_CRITERIA("taxo",1),
    GEOGRAPHIC_CRITERIA("geo",2),
    INDICATORS_CRITERIA("indi",3);

    //Atributes
    private String criteria;
    private int id;

    //Constructor
    private ChartCriteria(String c,int id) {
        this.criteria = c;
        this.id = id;
    }

    /**
     * @return the criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }


}
