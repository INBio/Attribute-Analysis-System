/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.model;

/**
 *
 * @author esmata
 */

public enum TaxonomicalRange {

	ROOT(0, "Raíz"),
	KINGDOM(1,"Reino"),
	PHYLUM(2, "Filo"),
	CLASS(3, "Clase"),
	ORDER(4,"Orden"),
	FAMILY(5,"Familia"),
	GENUS(6,"Género"),
	SPECIFICEPITHET(7,"Especie"),
    SCIENTIFICNAME(8,"NombreC");


	private int id;
	private String name;

	/**
	 * @param id
	 * @param name
	 */
	private TaxonomicalRange(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


}

