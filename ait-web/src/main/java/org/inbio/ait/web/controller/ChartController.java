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

