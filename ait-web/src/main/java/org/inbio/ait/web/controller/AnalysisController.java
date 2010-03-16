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
import org.inbio.ait.web.filter.FilterMapWrapper;

/**
 *
 * @author esmata
 */
public class AnalysisController implements Controller{

    protected final Log logger = LogFactory.getLog(getClass());
    private FilterMapWrapper filtersMap;
    private String filtersKey;

    // Analysis manager class
    private AnalysisManager analysisManager;

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

        //Test String to generate java script code dynamically
        /*String jsCode = "function onChangeLayer(dropdown)"+"\n"+
            "{"+"\n"+
            "    var selectedIndex = dropdown.selectedIndex;"+"\n"+
            "    layerIndex  = selectedIndex;"+"\n"+
            "    layerName = layersList[selectedIndex][0];"+"\n"+
            "    document.getElementById(\'info\').innerHTML = \"\";"+"\n"+
            "    return true;"+"\n"+
            "};"; //<c:out value="${model.js}"/> ON JSP */

        logger.info("Initialazing geoespatial analysis page");

        myModel.put(filtersKey,filtersMap.getFilters());
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

}
