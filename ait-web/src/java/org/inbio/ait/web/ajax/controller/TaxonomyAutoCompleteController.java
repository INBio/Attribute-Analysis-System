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

import org.inbio.ait.manager.TaxonomyManager;
import org.inbio.ait.model.AutocompleteNode;
import org.inbio.ait.model.TaxonomicalRange;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author esmata
 *
 */
public class TaxonomyAutoCompleteController extends MultiActionController{

	private TaxonomyManager taxonomyManager;
	/* For the yahoo auto complete javascript this value always is = "query"*/
	private String queryParam = "query";

	/**
     * Method to get the 10 first kingdoms that match with the autocomplete query
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
	public ModelAndView kingdom(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = request.getParameter(queryParam);
		String errorMsj = "No se puede obtener la lista para: " + param + ".";
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
			acnList = taxonomyManager.getElementsByTaxonomicalRange(param,TaxonomicalRange.KINGDOM.getId());
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	}

    /**
     * Method to get the 10 first phylums that match with the autocomplete query
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
	public ModelAndView phylum(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = request.getParameter(queryParam);
		String errorMsj = "No se puede obtener la lista para: " + param + ".";
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
			acnList = taxonomyManager.getElementsByTaxonomicalRange(param,TaxonomicalRange.PHYLUM.getId());
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	}

    /**
     * Method to get the 10 first classes that match with the autocomplete query
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
	public ModelAndView class1(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = request.getParameter(queryParam);
		String errorMsj = "No se puede obtener la lista para: " + param + ".";
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
			acnList = taxonomyManager.getElementsByTaxonomicalRange(param,TaxonomicalRange.CLASS.getId());
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	}
    
    /**
     * Method to get the 10 first orders that match with the autocomplete query
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = request.getParameter(queryParam);
		String errorMsj = "No se puede obtener la lista para: " + param + ".";
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
			acnList = taxonomyManager.getElementsByTaxonomicalRange(param,TaxonomicalRange.ORDER.getId());
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	}
    
    /**
     * Method to get the 10 first families that match with the autocomplete query
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
	public ModelAndView family(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = request.getParameter(queryParam);
		String errorMsj = "No se puede obtener la lista para: " + param + ".";
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
			acnList = taxonomyManager.getElementsByTaxonomicalRange(param,TaxonomicalRange.FAMILY.getId());
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	}
    
    /**
     * Method to get the 10 first genus that match with the autocomplete query
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
	public ModelAndView genus(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = request.getParameter(queryParam);
		String errorMsj = "No se puede obtener la lista para: " + param + ".";
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
			acnList = taxonomyManager.getElementsByTaxonomicalRange(param,TaxonomicalRange.GENUS.getId());
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	}
    
    /**
     * Method to get the 10 first specificEpithet that match with the autocomplete query
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
	public ModelAndView specificEpithet(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = request.getParameter(queryParam);
		String errorMsj = "No se puede obtener la lista para: " + param + ".";
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
			acnList = taxonomyManager.getElementsByTaxonomicalRange(param,TaxonomicalRange.SPECIFICEPITHET.getId());
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	}
    
    /**
     * Method to get the 10 first scientificnames that match with the autocomplete query
     * @param request
     * @param response
     * @return
     * @throws java.lang.Exception
     */
	public ModelAndView scientificname(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = request.getParameter(queryParam);
		String errorMsj = "No se puede obtener la lista para: " + param + ".";
		List<AutocompleteNode> acnList = new ArrayList<AutocompleteNode>();

		try {
			acnList = taxonomyManager.getElementsByTaxonomicalRange(param,TaxonomicalRange.SCIENTIFICNAME.getId());
			return writeReponse(request,response, acnList);

		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	}

	/**
	 * Writes the response in the output!.
	 * @param request
	 * @param response
	 * @param acnList
	 * @return
	 * @throws Exception
	 */
	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response, List<AutocompleteNode> acnList) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
        // Binary output
		ServletOutputStream out = response.getOutputStream(); 

        if (acnList != null) {

            for (AutocompleteNode sp : acnList) {
                out.println(sp.getItemName() + "\t" + sp.getItemId());
            }
        }

		out.flush();
		out.close();

		return null;
	}

	/**
	 * @return the taxonomyManager
	 */
	public TaxonomyManager getTaxonomyManager() {
		return taxonomyManager;
	}

	/**
	 * @param taxonomyManager the taxonomyManager to set
	 */
	public void setTaxonomyManager(TaxonomyManager taxonomyManager) {
		this.taxonomyManager = taxonomyManager;
	}

}