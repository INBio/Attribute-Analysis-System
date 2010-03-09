/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.ait.web.controller;

import com.keypoint.PngEncoder;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.inbio.ait.web.utils.ChartParameters;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
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
        dataset.addValue(1.0, "Fila 1", "Columna 1");
        dataset.addValue(5.0, "Fila 1", "Columna 2");
        dataset.addValue(3.0, "Fila 1", "Columna 3");
        
        dataset.addValue(2.0, "Fila 2", "Columna 1");
        dataset.addValue(3.0, "Fila 2", "Columna 2");
        dataset.addValue(2.0, "Fila 2", "Columna 3");

        //Create the chart
        JFreeChart chart = ChartFactory.createBarChart(
        "Mi gráfico", //title
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

		//Show the resultant chart
        return writeReponse(request, response,chart);
    }

	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response,JFreeChart chart) throws Exception {

		response.setContentType("image/png");
        //response.setHeader("Content-Disposition","attachment;filename=\"chart.png\"");
        
        // Binary output
		ServletOutputStream out = response.getOutputStream();

        //Send the image
        BufferedImage buf = chart.createBufferedImage(640, 400, null);
        PngEncoder encoder = new PngEncoder(buf, false, 0, 9);
        out.write(encoder.pngEncode());

        //Close de outputStream
		out.flush();
		out.close();

		return null;
	}

	/*private void setLabelsConfiguration(XYLineAndShapeRenderer renderer, DateFormat dfm) {

		StandardXYItemLabelGenerator gen = null;

		// Agrega las Etiquetas a los puntos de ocurrencia.
		gen = new StandardXYItemLabelGenerator("{1}", dfm, dfm);
		renderer.setSeriesItemLabelGenerator(0, gen);
		renderer.setSeriesItemLabelsVisible(0, true);
	}*/
}
