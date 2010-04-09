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

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.ait.web.filter.FilterMapWrapper;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.inbio.ait.web.utils.ChartParameters;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.web.servlet.mvc.SimpleFormController;


public class StatisticalController extends SimpleFormController {

    private FilterMapWrapper filtersMap;
    private String filtersKey;

    /**
     * Setting command class and command name
     */
    public StatisticalController() {
        setCommandClass(ChartParameters.class);
        setCommandName("parameters");
    }

    /**
     * Pass to the form the necessary data to be shown in the jsp
     * @param request
     * @return
     * @throws java.lang.Exception
     */
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();
        referenceData.put(filtersKey,filtersMap.getFilters());
        return referenceData;
    }

    /**
     * Gets the chart parameters, build the chart based on those parameters.
     * Then, the chart is passed as atribute trhow the session to another
     * view and finally the chart is shown to the user.
     * @param request
     * @param response
     * @param command
     * @param errors
     * @return
     * @throws java.lang.Exception
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception{

        //Getting query parameters
		ChartParameters parameters = (ChartParameters) command;

        System.out.println(parameters.getXtitle());
        System.out.println(parameters.getYtitle());
        System.out.println(parameters.getXdata());
        System.out.println(parameters.getYdata());
        System.out.println(parameters.getType());
        System.out.println(parameters.getXaxis());
        System.out.println(parameters.getYaxis());

        //Getting chart data from data base

        //Create the data set
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "Peligro de extinción", "Familia X");
        dataset.addValue(5.0, "Peligro de extinción", "Familia Y");
        dataset.addValue(3.0, "Peligro de extinción", "Familia Z");
        
        dataset.addValue(2.0, "Extinta", "Familia X");
        dataset.addValue(3.0, "Extinta", "Familia Y");
        dataset.addValue(2.0, "Extinta", "Familia Z");

        dataset.addValue(2.0, "Vulnerable", "Familia X");
        dataset.addValue(7.0, "Vulnerable", "Familia Y");
        dataset.addValue(8.0, "Vulnerable", "Familia Z");

        //Create the chart
        JFreeChart chart = ChartFactory.createBarChart3D(
        "Gráfico de prueba", //title
        parameters.getXtitle(), // x axis label
        parameters.getYtitle(),  // y axis label
        dataset, //dataset
        PlotOrientation.VERTICAL,
        true,  // include legend
        true,  // tooltips
        false); // urls

		//Customize the chart
		chart.setBackgroundPaint(Color.WHITE);
		/*XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesShapesVisible(0, true);
		XYPlot plot = chart.getXYPlot();
		plot.setRenderer(renderer);
		//Configuring the fonts
		plot.getDomainAxis().setLabelFont
                (new Font(Font.SANS_SERIF, Font.BOLD, 12));
		plot.getRangeAxis().setLabelFont
                (new Font(Font.SANS_SERIF, Font.BOLD, 12));
		plot.getDomainAxis().setLabel("Fecha del registro");
		plot.getRangeAxis().setLabel("Cantidad de Huellas");*/

		//Store te chart in the session.
		request.getSession(true).setAttribute("chart", chart);

		//Show the resultant chart
        boolean chartDisplay = true;
        ModelAndView mv = new ModelAndView(getSuccessView());
        mv.addObject("chartDisplay",chartDisplay);
        return mv;
    }

    /**
     * @return the filtersMap
     */
    public FilterMapWrapper getFiltersMap() {
        return filtersMap;
    }

    /**
     * @param filtersMap the filtersMap to set
     */
    public void setFiltersMap(FilterMapWrapper filtersMap) {
        this.filtersMap = filtersMap;
    }

    /**
     * @return the filtersKey
     */
    public String getFiltersKey() {
        return filtersKey;
    }

    /**
     * @param filtersKey the filtersKey to set
     */
    public void setFiltersKey(String filtersKey) {
        this.filtersKey = filtersKey;
    }
}
