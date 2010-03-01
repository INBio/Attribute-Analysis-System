/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.web.ajax.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.ait.manager.QueryManager;
import org.inbio.ait.model.TaxonInfoIndex;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author esmata
 */
public class QueryController implements Controller{

    private QueryManager queryManager;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String paramLayer = request.getParameter("layers");
        String paramTaxon = request.getParameter("taxons");
        String paramIndi = request.getParameter("indi");
		String errorMsj = "Error con los parámetros: "+paramLayer+" "+paramTaxon+" "+paramIndi;

		try {

            Long totalMatch = queryManager.countByCriteria
                    (paramLayer.split("\\|"), paramTaxon.split("\\|"), paramIndi.split("\\|"));

			return writeReponse(request,response,totalMatch);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
    }

	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response, Long totalMatch) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		ServletOutputStream out = response.getOutputStream();

        String result = "<response>";
        result += "<total>";
        result += "<data>"+totalMatch+"</data>";
        result += "</total>";
        result += "</response>";
        out.println(result);
        
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
