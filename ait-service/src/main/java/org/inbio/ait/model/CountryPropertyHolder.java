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
public class CountryPropertyHolder {

    //Constructor
    public CountryPropertyHolder(){
        this.driverClassName = "unmapped";
        this.url = "unmapped";
        this.username = "unmapped";
        this.password = "unmapped";
        this.tablename  = "unmapped";
        this.countryId = "unmapped";
        this.countryName = "unmapped";
    }

    //Attributes
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String tablename;
    private String countryId;
    private String countryName;

    /**
     * @return the countryId
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
