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

package org.inbio.ait.dao.conn;

import org.inbio.ait.model.TindiPropertyHolder;

/**
 *
 * @author esmata
 */
public interface TindiPropertyHolderDAO {

    /**
     * This method save the info from a TindiPropertyHolder java object
     * into the tindi.properties file
     */
    public boolean saveToPropertiesFile(TindiPropertyHolder ph);

    /**
     * Returns a TindiPropertyHolder java Object with all the
     * information from the tindi.properties file
     */
    public TindiPropertyHolder getTindiPropertyHolder();

}
