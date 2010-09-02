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

package org.inbio.ait.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.ait.manager.impl.SysUserManagerImpl;
import org.inbio.ait.model.SystemUser;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author esmata
 */
public class ChangePassController extends SimpleFormController{

    private SysUserManagerImpl sysUserManagerImpl;

    /**
     * Setting command class and command name
     */
    public ChangePassController() {
        setCommandClass(SystemUser.class);
        setCommandName("user");
    }

    /**
     * Pass to the form the necessary data to be shown in the jsp
     * @param request
     * @return
     * @throws java.lang.Exception
     */
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        //Pass data to the view
        Map referenceData = new HashMap();
        referenceData.put("usuario", "admin");
        return referenceData;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception{

        //Getting query parameters
		SystemUser user = (SystemUser) command;
        SystemUser userTopersist = (SystemUser)this.sysUserManagerImpl.loadUserByUsername("admin");
        Md5PasswordEncoder enc = new Md5PasswordEncoder();
		String passEnc = enc.encodePassword(user.getPassword(), null);
        userTopersist.setPassword(passEnc);
        this.sysUserManagerImpl.updateUser(userTopersist);

		//Return success view
        ModelAndView mv = new ModelAndView(getSuccessView());
        return mv;
    }

    /**
     * @return the sysUserManagerImpl
     */
    public SysUserManagerImpl getSysUserManagerImpl() {
        return sysUserManagerImpl;
    }

    /**
     * @param sysUserManagerImpl the sysUserManagerImpl to set
     */
    public void setSysUserManagerImpl(SysUserManagerImpl sysUserManagerImpl) {
        this.sysUserManagerImpl = sysUserManagerImpl;
    }

}
