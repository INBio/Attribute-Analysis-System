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

package org.inbio.ait.manager.impl;

import org.inbio.ait.dao.SystemUserDAO;
import org.inbio.ait.model.SystemUser;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsManager;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 *
 * @author esmata
 */
public class SysUserManagerImpl implements UserDetailsManager{

    private SystemUserDAO systemUserDAO;

    @Override
    public void createUser(UserDetails arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateUser(UserDetails arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUser(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changePassword(String arg0, String arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean userExists(String username) {
        return systemUserDAO.findByUsername(username) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		SystemUser su = systemUserDAO.findByUsername(username);
		if(su==null)
			throw new UsernameNotFoundException("username does not exist in the database.");
		return su;
    }

    /**
     * @return the systemUserDAO
     */
    public SystemUserDAO getSystemUserDAO() {
        return systemUserDAO;
    }

    /**
     * @param systemUserDAO the systemUserDAO to set
     */
    public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
        this.systemUserDAO = systemUserDAO;
    }

}
