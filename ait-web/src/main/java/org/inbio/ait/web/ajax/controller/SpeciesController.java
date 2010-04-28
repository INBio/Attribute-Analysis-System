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

package org.inbio.ait.web.ajax.controller;

import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.ait.manager.QueryManager;
import org.inbio.ait.manager.SpeciesManager;
import org.inbio.ait.web.utils.TaxonInfoIndexColums;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author esmata
 */
public class SpeciesController implements Controller{

    private SpeciesManager speciesManager;
    private QueryManager queryManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String paramLayer = request.getParameter("layers");
        String paramTaxon = request.getParameter("taxons");
        String paramIndi = request.getParameter("indi");
		String errorMsj = "Error con los parámetros: "+
                paramLayer+" "+paramTaxon+" "+paramIndi;

		try {

            //Arrays that contains the parameters data
            String[] layerArray = paramLayer.split("\\|");
            String[] taxonArray = paramTaxon.split("\\|");
            String[] indiArray = paramIndi.split("\\|");

            //Specimens that match with the search criteria
            List<String> species = speciesManager.speciesByCriteria
                    (layerArray, taxonArray, indiArray);

            Long specimenes = queryManager.countByCriteria(layerArray, taxonArray,
                    indiArray,TaxonInfoIndexColums.SPECIMENS.getName());

			return writeReponse(request,response,species,specimenes);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response,
            List<String> species,Long specimenes) throws Exception {

		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/xml");
		ServletOutputStream out = response.getOutputStream();

        StringBuilder result = new StringBuilder();
        result.append("<?xml version='1.0' encoding='ISO-8859-1'?><response>");
        result.append("<specimens>"+specimenes+"</specimens><speciesList>");
        for(String s : species){
            result.append("<species><scientificname>"+s+"</scientificname></species>");
        }
        result.append("</speciesList></response>");

        out.println(result.toString());
		out.flush();
		out.close();

		return null;
	}

    /**
     * @return the speciesManager
     */
    public SpeciesManager getSpeciesManager() {
        return speciesManager;
    }

    /**
     * @param speciesManager the speciesManager to set
     */
    public void setSpeciesManager(SpeciesManager speciesManager) {
        this.speciesManager = speciesManager;
    }

    /**
     * @return the queryManager
     */
    public QueryManager getQueryManager() {
        return queryManager;
    }

    /**
     * @param queryManager the queryManager to set
     */
    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

}
