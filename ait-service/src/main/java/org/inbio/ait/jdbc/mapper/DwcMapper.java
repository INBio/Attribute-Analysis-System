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

package org.inbio.ait.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.inbio.ait.model.DwcPropertyHolder;
import org.inbio.ait.model.SpecimenBase;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Mapper to iterate over external resulset and them insert those data into system db
 * @author esmata
 */
public class DwcMapper implements ParameterizedRowMapper<SpecimenBase> {

    //Atributes
    private DwcPropertyHolder ph;

    //Constructor
    public DwcMapper(DwcPropertyHolder ph) {
        this.ph = ph;
    }

    @Override
    public SpecimenBase mapRow(ResultSet rs, int rowNum) throws SQLException {
        SpecimenBase sp = new SpecimenBase();
        //Mandatory data
        sp.setGlobaluniqueidentifier(rs.getString(ph.getGlobaluniqueidentifier()));
        sp.setDatelastmodified(rs.getDate(ph.getDatelastmodified()));
        sp.setInstitutioncode(rs.getString(ph.getInstitutioncode()));
        sp.setCollectioncode(rs.getString(ph.getCollectioncode()));
        sp.setCatalognumber(rs.getString(ph.getCatalognumber()));
        sp.setScientificname(rs.getString(ph.getScientificname()));
        sp.setBasisofrecord(rs.getString(ph.getBasisofrecord()));
        //Aditional data
        if (!ph.getKingdom().equals("unmapped")) {
            sp.setKingdom(rs.getString(ph.getKingdom()));
        }
        if (!ph.getPhylum().equals("unmapped")) {
            sp.setClass1(rs.getString(ph.getPhylum()));
        }
        if (!ph.getClass1().equals("unmapped")) {
            sp.setClass1(rs.getString(ph.getClass1()));
        }
        if (!ph.getOrders().equals("unmapped")) {
            sp.setOrders(rs.getString(ph.getOrders()));
        }
        if (!ph.getFamily().equals("unmapped")) {
            sp.setFamily(rs.getString(ph.getFamily()));
        }
        if (!ph.getGenus().equals("unmapped")) {
            sp.setGenus(rs.getString(ph.getGenus()));
        }
        if (!ph.getSpecificepithet().equals("unmapped")) {
            sp.setSpecificepithet(rs.getString(ph.getSpecificepithet()));
        }
        if (!ph.getDecimallongitude().equals("unmapped")) {
            sp.setDecimallongitude(rs.getString(ph.getDecimallongitude()));
        }
        if (!ph.getDecimallatitude().equals("unmapped")) {
            sp.setDecimallatitude(rs.getString(ph.getDecimallatitude()));
        }
        return sp;
    }
}
