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

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.ait.manager.PointsManager;
import org.inbio.ait.model.Specimen;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author esmata
 */
public class ExportCsvController implements Controller{

    private PointsManager pointsManager;

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
            List<Specimen> specimens = pointsManager.specimensByCriteria
                    (layerArray, taxonArray, indiArray);

			return writeReponse(request,response,specimens);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response,
            List<Specimen> specimens) throws Exception {
        
		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();

        //String documento = "";
        StringBuilder documento = new StringBuilder();
        documento.append("<table class=\"contacts\" cellspacing=\"0\"><tr><th class=\"species\">Nombre científico</th><th class=\"species\">Longitud</th><th class=\"species\">Latitud</th><th class=\"species\"># de catálogo</th><th class=\"species\">Institución</th>");

        for(Specimen s : specimens){
            documento.append("<tr>");
            documento.append("<td class=\"contact\">"+s.getScientificname()+"</td>");
            documento.append("<td class=\"contact\">"+s.getDecimallongitude()+"</td>");
            documento.append("<td class=\"contact\">"+s.getDecimallatitude()+"</td>");
            documento.append("<td class=\"contact\">"+s.getCatalognumber()+"</td>");
            documento.append("<td class=\"contact\">"+s.getInstitutioncode()+"</td>");
            documento.append("<tr>");
        }
        documento.append("</table>");

        out.println(documento.toString());
		out.flush();
		out.close();

		return null;
	}

    /**
     * @return the pointsManager
     */
    public PointsManager getPointsManager() {
        return pointsManager;
    }

    /**
     * @param pointsManager the pointsManager to set
     */
    public void setPointsManager(PointsManager pointsManager) {
        this.pointsManager = pointsManager;
    }

}
