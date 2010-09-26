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
import org.inbio.ait.manager.SpeciesManager;
import org.inbio.ait.web.utils.Node;
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
        String limitPolygons = request.getParameter("limit");
		String errorMsj = "Error con los parámetros: "+
                paramLayer+" "+paramTaxon+" "+paramIndi+" "+limitPolygons;

		try {
            //Arrays that contains the parameters data
            String[] layerArray = paramLayer.split("\\|");
            String[] taxonArray = paramTaxon.split("\\|");
            String[] indiArray = paramIndi.split("\\|");
            String[] limitArray = limitPolygons.split("\\|");

            if(layerArray!=null && !layerArray[0].equals("")){ //If there is geographical criteria
                //Total of matches in the limit polygons
                Long totalLimitMatches = queryManager.countByCriteria
                        (limitArray, taxonArray, indiArray,TaxonInfoIndexColums.SPECIES.getName());

                //Total matches by polygon
                List<Node> matchesByPolygon = new ArrayList<Node>();
                for(int i=0;i<layerArray.length;i++){
                    String[] thePolygon = {layerArray[i]};
                    Long countAbs = queryManager.countByCriteria
                        (thePolygon, taxonArray, indiArray,TaxonInfoIndexColums.SPECIES.getName());
                    List<String> spList = speciesManager.speciesByCriteria(thePolygon, taxonArray, indiArray);
                    Long percentage = 0L;
                    if(totalLimitMatches>0){
                        percentage = (countAbs*100)/totalLimitMatches;
                    }
                    Node aux = new Node(); //Absulute count,percentage
                    aux.setValue1(countAbs);
                    aux.setValue2(percentage);
                    aux.setValue3(spList);
                    matchesByPolygon.add(aux);
                }
                return writeReponse(request,response,null,matchesByPolygon);
            }
            else{
                //Specimens that match with the search criteria
                List<String> species = speciesManager.speciesByCriteria
                        (layerArray, taxonArray, indiArray);
                return writeReponse(request,response,species,null);
            }

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

    /**
     * Return the XML with the results
     * @param request
     * @param response
     * @param species
     * @param matchesByPolygon
     * @return
     * @throws java.lang.Exception
     */
	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response,List<String> species,
            List<Node> matchesByPolygon) throws Exception {

		response.setCharacterEncoding("ISO-8859-1");
		response.setContentType("text/xml");
		ServletOutputStream out = response.getOutputStream();
        StringBuilder result = new StringBuilder();

        if(matchesByPolygon==null){ //If there is not geographical criteria
            result.append("<?xml version='1.0' encoding='ISO-8859-1'?><response><speciesList>");
            for(String s : species){
                result.append("<species>"+s+"</species>");
            }
            result.append("</speciesList><polygons></polygons></response>");
            out.println(result.toString());
        }
        else{ //If there is gegraphical criteria
            result.append("<?xml version='1.0' encoding='ISO-8859-1'?><response>");
            result.append("<speciesList></speciesList>");
            result.append("<polygons>");
            for(Node node : matchesByPolygon){

                result.append("<polygon>");
                result.append("<abs>"+node.getValue1()+"</abs>");
                result.append("<per>"+node.getValue2()+"</per>");
                for(String sp : node.getValue3()){
                    result.append("<sp>"+sp+"</sp>");
                }
                result.append("</polygon>");
            }
            result.append("</polygons></response>");
            out.println(result.toString());
        }
        
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
