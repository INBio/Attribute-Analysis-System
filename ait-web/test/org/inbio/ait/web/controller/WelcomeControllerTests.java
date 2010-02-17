/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.web.controller;

/**
 *
 * @author esmata
 */
import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;

public class WelcomeControllerTests extends TestCase {

    public void testHandleRequestView() throws Exception{

        WelcomeController controller = new WelcomeController();

        ModelAndView modelAndView = controller.handleRequest(null, null);

        assertEquals("welcome", modelAndView.getViewName());

        assertNotNull(modelAndView.getModel());

        String nowValue = (String) modelAndView.getModel().get("now");

        assertNotNull(nowValue);

    }

}
