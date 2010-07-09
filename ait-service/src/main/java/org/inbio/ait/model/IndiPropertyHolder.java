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
public class IndiPropertyHolder {

    //Constructor
    public IndiPropertyHolder(){
        this.driverClassName = "unmapped";
        this.url = "unmapped";
        this.username = "unmapped";
        this.password = "unmapped";
        this.tablename  = "unmapped";
        this.indicator_id = "unmapped";
        this.indicator_name = "unmapped";
        this.indicator_description = "unmapped";
        this.indicator_applies_to_part = "unmapped";
        this.indicator_ancestor_id = "unmapped";
        this.indicator_references = "unmapped";
    }

    //Attributes
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String tablename;
    private String indicator_id;
    private String indicator_name;
    private String indicator_description;
    private String indicator_applies_to_part;
    private String indicator_ancestor_id;
    private String indicator_references;

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
     * @return the indicator_name
     */
    public String getIndicator_name() {
        return indicator_name;
    }

    /**
     * @param indicator_name the indicator_name to set
     */
    public void setIndicator_name(String indicator_name) {
        this.indicator_name = indicator_name;
    }

    /**
     * @return the indicator_description
     */
    public String getIndicator_description() {
        return indicator_description;
    }

    /**
     * @param indicator_description the indicator_description to set
     */
    public void setIndicator_description(String indicator_description) {
        this.indicator_description = indicator_description;
    }

    /**
     * @return the indicator_applies_to_part
     */
    public String getIndicator_applies_to_part() {
        return indicator_applies_to_part;
    }

    /**
     * @param indicator_applies_to_part the indicator_applies_to_part to set
     */
    public void setIndicator_applies_to_part(String indicator_applies_to_part) {
        this.indicator_applies_to_part = indicator_applies_to_part;
    }

    /**
     * @return the indicator_ancestor_id
     */
    public String getIndicator_ancestor_id() {
        return indicator_ancestor_id;
    }

    /**
     * @param indicator_ancestor_id the indicator_ancestor_id to set
     */
    public void setIndicator_ancestor_id(String indicator_ancestor_id) {
        this.indicator_ancestor_id = indicator_ancestor_id;
    }

    /**
     * @return the indicator_references
     */
    public String getIndicator_references() {
        return indicator_references;
    }

    /**
     * @param indicator_references the indicator_references to set
     */
    public void setIndicator_references(String indicator_references) {
        this.indicator_references = indicator_references;
    }

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

}
