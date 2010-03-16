/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.web.controller;

import com.keypoint.PngEncoder;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.inbio.ait.web.utils.ChartParameters;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.web.servlet.mvc.SimpleFormController;


public class StatisticalController extends SimpleFormController {

    public StatisticalController() {
        setCommandClass(ChartParameters.class);
        setCommandName("parameters");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception{

        //Getting query parameters
		ChartParameters parameters = (ChartParameters) command;

        //Getting chart data
		/*HashMap datos = tpd.getSpecieOcurrenciesData(form.getIndicatorId(),
        form.getFieldTripId(), form.getTaxonId());*/

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
        parameters.getXdata(), // x axis label
        parameters.getYdata(),  // y axis label
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
}