/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.web.controller;

import com.keypoint.PngEncoder;
import java.awt.image.BufferedImage;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.JFreeChart;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author esmata
 */

public class ChartController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal
            (HttpServletRequest request,
            HttpServletResponse response) throws Exception {

		JFreeChart chart = (JFreeChart) request.getSession(true).getAttribute("chart");

		if (chart != null) {

			request.getSession(true).removeAttribute("chart");

			//Set the content type
			response.setContentType("image/png");

			//Send the image
			BufferedImage buf = chart.createBufferedImage(640, 400, null);
			PngEncoder encoder = new PngEncoder(buf, false, 0, 9);
			response.getOutputStream().write(encoder.pngEncode());
		}

		return null;
	}
}

