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
package org.inbio.ait.model;

/**
 *
 * @author esmata
 */
public class TindiPropertyHolder {

    //Constructor
    public TindiPropertyHolder(){
        this.driverClassName = "unmapped";
        this.url = "unmapped";
        this.username = "unmapped";
        this.password = "unmapped";
        this.tablename  = "unmapped";
        this.taxon_indicator_id  = "unmapped";
        this.taxon_indicator_certainty_level  = "unmapped";
        this.taxon_indicator_evaluation_criteria  = "unmapped";
        this.taxon_indicator_regionality  = "unmapped";
        this.taxon_indicator_temporality  = "unmapped";
        this.taxon_indicator_references  = "unmapped";
        this.taxon_indicator_notes  = "unmapped";
        this.taxon_indicator_valuer_person  = "unmapped";
        this.taxon_scientific_name  = "unmapped";
        this.indicator_id  = "unmapped";
        this.component_part  = "unmapped";
    }

    //Attributes
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String tablename;
    private String taxon_indicator_id;
    private String taxon_indicator_certainty_level;
    private String taxon_indicator_evaluation_criteria;
    private String taxon_indicator_regionality;
    private String taxon_indicator_temporality;
    private String taxon_indicator_references;
    private String taxon_indicator_notes;
    private String taxon_indicator_valuer_person;
    private String taxon_scientific_name;
    private String indicator_id;
    private String component_part;

    /**
     * @return the driverClassName
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * @param driverClassName the driverClassName to set
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tablename
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * @param tablename the tablename to set
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * @return the taxon_indicator_id
     */
    public String getTaxon_indicator_id() {
        return taxon_indicator_id;
    }

    /**
     * @param taxon_indicator_id the taxon_indicator_id to set
     */
    public void setTaxon_indicator_id(String taxon_indicator_id) {
        this.taxon_indicator_id = taxon_indicator_id;
    }

    /**
     * @return the taxon_indicator_certainty_level
     */
    public String getTaxon_indicator_certainty_level() {
        return taxon_indicator_certainty_level;
    }

    /**
     * @param taxon_indicator_certainty_level the taxon_indicator_certainty_level to set
     */
    public void setTaxon_indicator_certainty_level(String taxon_indicator_certainty_level) {
        this.taxon_indicator_certainty_level = taxon_indicator_certainty_level;
    }

    /**
     * @return the taxon_indicator_evaluation_criteria
     */
    public String getTaxon_indicator_evaluation_criteria() {
        return taxon_indicator_evaluation_criteria;
    }

    /**
     * @param taxon_indicator_evaluation_criteria the taxon_indicator_evaluation_criteria to set
     */
    public void setTaxon_indicator_evaluation_criteria(String taxon_indicator_evaluation_criteria) {
        this.taxon_indicator_evaluation_criteria = taxon_indicator_evaluation_criteria;
    }

    /**
     * @return the taxon_indicator_regionality
     */
    public String getTaxon_indicator_regionality() {
        return taxon_indicator_regionality;
    }

    /**
     * @param taxon_indicator_regionality the taxon_indicator_regionality to set
     */
    public void setTaxon_indicator_regionality(String taxon_indicator_regionality) {
        this.taxon_indicator_regionality = taxon_indicator_regionality;
    }

    /**
     * @return the taxon_indicator_temporality
     */
    public String getTaxon_indicator_temporality() {
        return taxon_indicator_temporality;
    }

    /**
     * @param taxon_indicator_temporality the taxon_indicator_temporality to set
     */
    public void setTaxon_indicator_temporality(String taxon_indicator_temporality) {
        this.taxon_indicator_temporality = taxon_indicator_temporality;
    }

    /**
     * @return the taxon_indicator_references
     */
    public String getTaxon_indicator_references() {
        return taxon_indicator_references;
    }

    /**
     * @param taxon_indicator_references the taxon_indicator_references to set
     */
    public void setTaxon_indicator_references(String taxon_indicator_references) {
        this.taxon_indicator_references = taxon_indicator_references;
    }

    /**
     * @return the taxon_indicator_notes
     */
    public String getTaxon_indicator_notes() {
        return taxon_indicator_notes;
    }

    /**
     * @param taxon_indicator_notes the taxon_indicator_notes to set
     */
    public void setTaxon_indicator_notes(String taxon_indicator_notes) {
        this.taxon_indicator_notes = taxon_indicator_notes;
    }

    /**
     * @return the taxon_indicator_valuer_person
     */
    public String getTaxon_indicator_valuer_person() {
        return taxon_indicator_valuer_person;
    }

    /**
     * @param taxon_indicator_valuer_person the taxon_indicator_valuer_person to set
     */
    public void setTaxon_indicator_valuer_person(String taxon_indicator_valuer_person) {
        this.taxon_indicator_valuer_person = taxon_indicator_valuer_person;
    }

    /**
     * @return the taxon_scientific_name
     */
    public String getTaxon_scientific_name() {
        return taxon_scientific_name;
    }

    /**
     * @param taxon_scientific_name the taxon_scientific_name to set
     */
    public void setTaxon_scientific_name(String taxon_scientific_name) {
        this.taxon_scientific_name = taxon_scientific_name;
    }

    /**
     * @return the indicator_id
     */
    public String getIndicator_id() {
        return indicator_id;
    }

    /**
     * @param indicator_id the indicator_id to set
     */
    public void setIndicator_id(String indicator_id) {
        this.indicator_id = indicator_id;
    }

    /**
     * @return the component_part
     */
    public String getComponent_part() {
        return component_part;
    }

    /**
     * @param component_part the component_part to set
     */
    public void setComponent_part(String component_part) {
        this.component_part = component_part;
    }

}
