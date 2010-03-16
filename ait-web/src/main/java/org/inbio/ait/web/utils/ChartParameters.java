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

package org.inbio.ait.web.utils;

/**
 *
 * @author esmata
 */
public class ChartParameters {
    private String xdata;
    private String ydata;

    /**
     * @return the xdata
     */
    public String getXdata() {
        return xdata;
    }

    /**
     * @param xdata the xdata to set
     */
    public void setXdata(String xdata) {
        this.xdata = xdata;
    }

    /**
     * @return the ydata
     */
    public String getYdata() {
        return ydata;
    }

    /**
     * @param ydata the ydata to set
     */
    public void setYdata(String ydata) {
        this.ydata = ydata;
    }
}
