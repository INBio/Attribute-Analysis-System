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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.ait.manager.ConfigManager;
import org.inbio.ait.model.PlicPropertyHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author esmata
 */
public class PlicController extends SimpleFormController{
    
    private ConfigManager configManager;

    /**
     * Setting command class and command name
     */
    public PlicController() {
        setCommandClass(PlicPropertyHolder.class);
        setCommandName("attributes");
    }

    /**
     * Seting the current PlicPropertyHolder Object to the form
     * @param request
     * @return
     * @throws java.lang.Exception
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        PlicPropertyHolder dph = (PlicPropertyHolder) super.formBackingObject(request);
        dph = configManager.getPlicPropertyHolder();
        return dph;
    }

    /**
     * Pass to the form the necessary data to be shown in the jsp
     * @param request
     * @return
     * @throws java.lang.Exception
     */
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();
        List tableColumns = this.configManager.getPlicTableFields();
        referenceData.put("cols",tableColumns);
        return referenceData;
    }

    /**
     * Gets the Plinian Core Maping object to be persisted in the
     * plic.properties file. This file eventually will to be used
     * in the tables indexing proccess
     * @throws java.lang.Exception
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception{

        //Getting query parameters
		PlicPropertyHolder attributes = (PlicPropertyHolder) command;

        //Persist the connection attributes
        boolean savePropertiesOk = this.configManager.saveToPropertiesFilePlic(attributes);

        if(savePropertiesOk == true){ //Everything is ok
            //Return the view with the required information
            ModelAndView mv = new ModelAndView(getSuccessView());
            return mv;
        }
        else{ //Error view
            ModelAndView mv = new ModelAndView("error");
            String error = ".";
            mv.addObject("error", error);
            return mv;
        }
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
