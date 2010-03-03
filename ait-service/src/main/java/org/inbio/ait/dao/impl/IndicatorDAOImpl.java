/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.inbio.ait.dao.IndicatorDAO;
import org.inbio.ait.model.AutocompleteNode;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author esmata
 */
public class IndicatorDAOImpl extends SimpleJdbcDaoSupport implements IndicatorDAO{

    public List<AutocompleteNode> getChildNodesByNodeId(int nodeId) {
        List<AutocompleteNode> nodes = new ArrayList<AutocompleteNode>();
        try{
            String query = "Select indicator_id,indicator_name from ait.indicator where indicator_ancestor_id = "+nodeId+";";

            nodes = getSimpleJdbcTemplate().query(query,
                    new AutocompleteMapper());
        }
        catch(Exception e){return nodes;}
        return nodes;
    }

    private static class AutocompleteMapper implements ParameterizedRowMapper<AutocompleteNode> {

        public AutocompleteNode mapRow(ResultSet rs, int rowNum) throws SQLException {

            AutocompleteNode acn = new AutocompleteNode();
            acn.setItemName(rs.getString("indicator_name"));
            acn.setItemId(rs.getString("indicator_id"));
            return acn;
        }
    }

}
