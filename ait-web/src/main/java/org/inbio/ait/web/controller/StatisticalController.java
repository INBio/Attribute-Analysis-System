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
import org.inbio.ait.manager.QueryManager;
import org.inbio.ait.web.filter.FilterMapWrapper;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.inbio.ait.web.utils.ChartParameters;
import org.inbio.ait.web.utils.ChartType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.web.servlet.mvc.SimpleFormController;


public class StatisticalController extends SimpleFormController {

    private FilterMapWrapper filtersMap;
    private String filtersKey;
    private QueryManager queryManager;

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

        //Arrays that contains the parameters data
        String[] xArray,yArray,xToShow,yToShow;
        //Validate if the user don't specify the x and y criteria
        if(parameters.getXdata().equals("")||parameters.getYdata().equals("")){
            xArray = new String[0];
            yArray = new String[0];
            xToShow = new String[0];
            yToShow = new String[0];
        }
        else{
            xArray = parameters.getXdata().split("\\|");
            yArray = parameters.getYdata().split("\\|");
            xToShow = parameters.getXdatatoshow().split("\\|");
            yToShow = parameters.getYdatatoshow().split("\\|");
        }

        //Create the data set (value,row,colum) = (value,y,x)
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //Getting x and y axis int values
        int x = 0, y = 0;
        try{
            x = Integer.parseInt(parameters.getXaxis());
            y = Integer.parseInt(parameters.getYaxis());
        }
        catch(Exception e){}

        //Getting chart data from data base
        for(int i = 0;i < xArray.length;i++){
            for(int j = 0;j < yArray.length;j++){
                Long result = getQueryManager().countByCriteria(xArray[i],yArray[j],x,y);
                dataset.addValue(result,yToShow[j], xToShow[i]); //(value,y,x)
            }
        }

        //Create the chart
        JFreeChart chart;
        if(parameters.getType().equals(ChartType.BAR_CHART.getType())){
            chart = ChartFactory.createBarChart3D(
            parameters.getTitle(), //title
            parameters.getXtitle(), // x axis label
            parameters.getYtitle(),  // y axis label
            dataset, //dataset
            PlotOrientation.VERTICAL,
            true,  // include legend
            true,  // tooltips
            false); // urls
        }
        else{
            chart = ChartFactory.createLineChart3D(
            parameters.getTitle(), //title
            parameters.getXtitle(), // x axis label
            parameters.getYtitle(),  // y axis label
            dataset, //dataset
            PlotOrientation.VERTICAL,
            true,  // include legend
            true,  // tooltips
            false); // urls
        }

		//Customize the chart
		chart.setBackgroundPaint(Color.WHITE);

		//Store te chart in the session.
		request.getSession(true).setAttribute("chart", chart);

		//Show the resultant chart
        boolean chartDisplay = true;
        ModelAndView mv = new ModelAndView(getSuccessView());
        //Chart
        mv.addObject("chartDisplay",chartDisplay);
        //ChartParameters atributes
        mv.addObject("xdata",parameters.getXdata());
        mv.addObject("ydata",parameters.getYdata());
        mv.addObject("xaxis",parameters.getXaxis());
        mv.addObject("yaxis",parameters.getYaxis());
        mv.addObject("xdatatoshow",parameters.getXdatatoshow());
        mv.addObject("ydatatoshow",parameters.getYdatatoshow());
        mv.addObject("isgeo",parameters.getIsgeo());
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

    /**
     * @return the queryManager
     */
    public QueryManager getQueryManager() {
        return queryManager;
    }

    /**
     * @param queryManager the queryManager to set
     */
    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }
}
