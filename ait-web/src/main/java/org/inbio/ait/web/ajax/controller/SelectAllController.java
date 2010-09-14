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
import org.inbio.ait.manager.AllPolygonsManager;
import org.inbio.ait.model.Polygon;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author esmata
 */
public class SelectAllController implements Controller{

    private AllPolygonsManager allPolygonsManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String layer = request.getParameter("layer");
		String errorMsj = "Error con los parámetros: "+layer;

		try {
            //Get the list of polygons
            List<Polygon> polygons = this.allPolygonsManager.getAllPolygons(layer);
            return writeReponse(request,response,polygons);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response, List<Polygon> pList) throws Exception {

		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/xml");
        // Binary output
		ServletOutputStream out = response.getOutputStream();

        if (pList != null) {
            StringBuilder result = new StringBuilder();
            result.append("<?xml version='1.0' encoding='ISO-8859-1'?><response>");
            for (Polygon p : pList) {
                result.append("<polygon><id>"+p.getId()+"</id>");
                result.append("<name>"+p.getName()+"</name></polygon>");
            }
            result.append("</response>");
            out.println(result.toString());
        }

		out.flush();
		out.close();

		return null;
	}

    /**
     * @return the allPolygonsManager
     */
    public AllPolygonsManager getAllPolygonsManager() {
        return allPolygonsManager;
    }

    /**
     * @param allPolygonsManager the allPolygonsManager to set
     */
    public void setAllPolygonsManager(AllPolygonsManager allPolygonsManager) {
        this.allPolygonsManager = allPolygonsManager;
    }

}
