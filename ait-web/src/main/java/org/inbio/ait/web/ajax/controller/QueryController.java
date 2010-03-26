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

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.ait.manager.QueryManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author esmata
 */
public class QueryController implements Controller{

    private QueryManager queryManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String paramLayer = request.getParameter("layers");
        String paramTaxon = request.getParameter("taxons");
        String paramIndi = request.getParameter("indi");
		String errorMsj = "Error con los parámetros: "+paramLayer+" "+paramTaxon+" "+paramIndi;

		try {

            //Arrays that contains the parameters data
            String[] layerArray = paramLayer.split("\\|");
            String[] taxonArray = paramTaxon.split("\\|");
            String[] indiArray = paramIndi.split("\\|");

            //Total of matches
            Long totalMatches = queryManager.countByCriteria
                    (layerArray, taxonArray, indiArray);

            //List that contains the matches by polygon
            List<Long> matchesByPolygon = new ArrayList<Long>();
            for(int i=0;i<layerArray.length;i++){
                String[] thePolygon = {layerArray[i]};
                Long aux = queryManager.countByCriteria(thePolygon,taxonArray,indiArray);
                matchesByPolygon.add(aux);
            }

			return writeReponse(request,response,totalMatches,matchesByPolygon);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response, Long totalMatch,
            List<Long> matchesByPolygon) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		ServletOutputStream out = response.getOutputStream();

        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><response><total>");
        result.append("<data>"+totalMatch+"</data></total><polygons>");

        for(Long n : matchesByPolygon){
            result.append("<polygon><data>"+n+"</data></polygon>");
        }
        result.append("</polygons></response>");

        out.println(result.toString());
		out.flush();
		out.close();

		return null;
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
