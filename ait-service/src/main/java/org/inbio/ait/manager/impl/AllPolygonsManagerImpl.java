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
import org.inbio.ait.dao.conn.LayerDataAccessDAO;
import org.inbio.ait.dao.conn.LayerPropertyHolderDAO;
import org.inbio.ait.manager.AllPolygonsManager;
import org.inbio.ait.model.LayerPropertyHolder;
import org.inbio.ait.model.Polygon;


/**
 * @author esmata
 */
public class AllPolygonsManagerImpl implements AllPolygonsManager{

    private LayerDataAccessDAO layerDataAccessDAO;
    private LayerPropertyHolderDAO layerPropertyHolderDAO;

    @Override
    public List<Polygon> getAllPolygons(String layer) {
        return this.layerDataAccessDAO.getAllPolygonsByLayer(layer, this.getLayerPropertyHolder());
    }

    /**
     * Returns a LayerPropertyHolder java Object with all the
     * information from de layer.properties file
     */
    @Override
    public LayerPropertyHolder getLayerPropertyHolder(){
        return this.layerPropertyHolderDAO.getLayerPropertyHolder();
    }

    /**
     * @return the layerDataAccessDAO
     */
    public LayerDataAccessDAO getLayerDataAccessDAO() {
        return layerDataAccessDAO;
    }

    /**
     * @param layerDataAccessDAO the layerDataAccessDAO to set
     */
    public void setLayerDataAccessDAO(LayerDataAccessDAO layerDataAccessDAO) {
        this.layerDataAccessDAO = layerDataAccessDAO;
    }

    /**
     * @return the layerPropertyHolderDAO
     */
    public LayerPropertyHolderDAO getLayerPropertyHolderDAO() {
        return layerPropertyHolderDAO;
    }

    /**
     * @param layerPropertyHolderDAO the layerPropertyHolderDAO to set
     */
    public void setLayerPropertyHolderDAO(LayerPropertyHolderDAO layerPropertyHolderDAO) {
        this.layerPropertyHolderDAO = layerPropertyHolderDAO;
    }

}
