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

package org.inbio.ait.web.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.inbio.ait.manager.AnalysisManager;
import org.inbio.ait.manager.ConfigManager;
import org.inbio.ait.model.PostgisLayers;
import org.inbio.ait.web.filter.FilterMapWrapper;

/**
 *
 * @author esmata
 */
public class AnalysisController implements Controller{

    protected final Log logger = LogFactory.getLog(getClass());
    private FilterMapWrapper filtersMap;
    private String filtersKey;

    // Manager classes
    private AnalysisManager analysisManager;
    private ConfigManager configManager;

    /**
     * Method that handle the http request
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {

        //Map to storage all madel data needed
        Map<String, Object> myModel = new HashMap<String, Object>();

        //Gets the list of selected layers
        PostgisLayers pl = this.configManager.getLayersList();
        String[] layers = pl.getLayers();

        logger.info("Initialazing geoespatial analysis page");

        myModel.put(filtersKey,filtersMap.getFilters());
        myModel.put("geoserver", "IABIN_Indicadores:");
        myModel.put("layers", layers);

        return new ModelAndView("analysis", "model", myModel);
    }

    // Dependency injection
    public void setAnalysisManager(AnalysisManager analysisManager){
        this.analysisManager = analysisManager;
    }

    /**
     * @return the filtersKey
     */
    public String getFiltersKey() {
        return filtersKey;
    }

    /**
     * @param filtersKey the filtersKey to set
     */
    public void setFiltersKey(String filtersKey) {
        this.filtersKey = filtersKey;
    }

    /**
     * @return the filtersMap
     */
    public FilterMapWrapper getFiltersMap() {
        return filtersMap;
    }

    /**
     * @param filtersMap the filtersMap to set
     */
    public void setFiltersMap(FilterMapWrapper filtersMap) {
        this.filtersMap = filtersMap;
    }

    /**
     * @return the configManager
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * @param configManager the configManager to set
     */
    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

}
