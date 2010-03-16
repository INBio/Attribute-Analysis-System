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
import org.inbio.ait.manager.IndicatorsManager;
import org.inbio.ait.model.AutocompleteNode;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author esmata
 */
public class IndicatorsTreeController implements Controller{

    private IndicatorsManager indicatorsManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = request.getParameter("query");
		String errorMsj = "No se pudo obtener la lista de hijos para el nodo: "+param;
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
            int paramInt = Integer.parseInt(param);
			acnList = indicatorsManager.getChildNodesByNodeId(paramInt);
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response, List<AutocompleteNode> acnList) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
        // Binary output
		ServletOutputStream out = response.getOutputStream();

        if (acnList != null) {

            String result = "<response>";
            for (AutocompleteNode sp : acnList) {
                result += "<node>";
                result += "<id>"+sp.getItemId()+"</id>";
                result += "<name>"+sp.getItemName()+"</name>";
                result += "</node>";
            }
            result += "</response>";
            out.println(result);
        }

		out.flush();
		out.close();

		return null;
	}

    /**
     * @return the indicatorsManager
     */
    public IndicatorsManager getIndicatorsManager() {
        return indicatorsManager;
    }

    /**
     * @param indicatorsManager the indicatorsManager to set
     */
    public void setIndicatorsManager(IndicatorsManager indicatorsManager) {
        this.indicatorsManager = indicatorsManager;
    }
}
