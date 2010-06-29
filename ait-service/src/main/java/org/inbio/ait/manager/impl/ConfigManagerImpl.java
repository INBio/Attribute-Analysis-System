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

package org.inbio.ait.manager.impl;

import java.util.List;
import org.inbio.ait.dao.DwcDataAccessDAO;
import org.inbio.ait.dao.DwcPropertyHolderDAO;
import org.inbio.ait.manager.ConfigManager;
import org.inbio.ait.model.DwcPropertyHolder;

/**
 *
 * @author esmata
 */
public class ConfigManagerImpl implements ConfigManager{

    private DwcPropertyHolderDAO dwcPropertyHolderDAO;
    private DwcDataAccessDAO dwcDataAccessDAO;

    /**
     * Returns a DwcPropertyHolder java Object with all the
     * information from de dwc.properties file
     */
    @Override
    public DwcPropertyHolder getDwcPropertyHolder(){
        return this.dwcPropertyHolderDAO.getDwcPropertyHolder();
    }

    /**
     * This method save the info from a DwcPropertyHolder java class
     * into the dwc.properties file
     */
    @Override
    public boolean saveToPropertiesFile(DwcPropertyHolder ph) {
        return this.dwcPropertyHolderDAO.saveToPropertiesFile(ph);
    }

    /**
     * Return the total count of dwc registers
     * @return
     */
    @Override
    public int CountDwc(){
        return this.dwcDataAccessDAO.countAll(this.getDwcPropertyHolder());
    }

    /**
     * Method to get a list of all columns from the mapped dwc table
     * trhow jdbc conection
     */
    @Override
    public List<String> getDwcTableFields(){
        return this.dwcDataAccessDAO.getDwcTableFields(this.getDwcPropertyHolder());
    }

    /**
     * @return the dwcPropertyHolderDAO
     */
    public DwcPropertyHolderDAO getDwcPropertyHolderDAO() {
        return dwcPropertyHolderDAO;
    }

    /**
     * @param dwcPropertyHolderDAO the dwcPropertyHolderDAO to set
     */
    public void setDwcPropertyHolderDAO(DwcPropertyHolderDAO dwcPropertyHolderDAO) {
        this.dwcPropertyHolderDAO = dwcPropertyHolderDAO;
    }

    /**
     * @return the dwcDataAccessDAO
     */
    public DwcDataAccessDAO getDwcDataAccessDAO() {
        return dwcDataAccessDAO;
    }

    /**
     * @param dwcDataAccessDAO the dwcDataAccessDAO to set
     */
    public void setDwcDataAccessDAO(DwcDataAccessDAO dwcDataAccessDAO) {
        this.dwcDataAccessDAO = dwcDataAccessDAO;
    }

}