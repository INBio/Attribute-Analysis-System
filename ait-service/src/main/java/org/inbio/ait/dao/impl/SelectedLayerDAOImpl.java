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

package org.inbio.ait.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.dao.SelectedLayerDAO;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author esmata
 */
public class SelectedLayerDAOImpl extends SimpleJdbcDaoSupport implements SelectedLayerDAO{

    /**
     * Persist a single layer
     */
    @Override
    public boolean saveLayers(String layerName) {
        try {
            String query = "Insert into ait.selected_layer (name) values (?)";
            getSimpleJdbcTemplate().update(query,layerName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Deletes all elements from ait.selected_layer table
     */
    @Override
    public boolean deleteAllLayers() {
        try {
            String query = "Delete from ait.selected_layer";
            getSimpleJdbcTemplate().update(query);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Updates a single layer (just the base value)
     */
    @Override
    public boolean updateBase(String layerName) {
        try {
            String query = "Update ait.selected_layer set base = ? where name = ?";
            getSimpleJdbcTemplate().update(query,1,layerName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Gets the base layer (position 0)
     */
    @Override
    public List<String> getBaseLayers(){
        List<String> layers = new ArrayList<String>();
        try {
            String queryLayers = "Select * from ait.selected_layer where base != 0";
            layers = getSimpleJdbcTemplate().query(queryLayers,new Mapper());
        } catch (Exception e) {
            return layers;
        }
        return layers;
    }

    /**
     * Gets the complete list of selected layers
     */
    @Override
    public List<String> getLayersNames(){
        List<String> layers = new ArrayList<String>();
        try {
            String queryLayers = "Select * from ait.selected_layer";
            layers = getSimpleJdbcTemplate().query(queryLayers,new Mapper());
        } catch (Exception e) {
            return layers;
        }
        return layers;
    }

    private static class Mapper implements ParameterizedRowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String result = rs.getString("name");
            return result;
        }
    }

}
