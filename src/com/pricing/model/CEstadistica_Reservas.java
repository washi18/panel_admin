package com.pricing.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.ngi.zhighcharts.SimpleExtXYModel;
import org.ngi.zhighcharts.ZHighCharts;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.SimplePieModel;
import org.zkoss.zul.Window;
public class CEstadistica_Reservas extends Window implements EventListener {
protected Log logger = Log.lookup(this.getClass());
	
	//Stacked area
	private ZHighCharts chartComp13;
	private SimpleExtXYModel dataChartModel13 = new SimpleExtXYModel();
		

	// date format used to capture date time
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
	
	public void onCreate() throws Exception {

		//================================================================================
	    // Stacked area
	    //================================================================================

		
		chartComp13 = (ZHighCharts) getFellow("chartComp13");
		chartComp13.setType("area");
		chartComp13.setTitle("Cantidad de reservas en los ultimos tiempos");
		chartComp13.setSubTitle("Source: WebPage");
		chartComp13.setxAxisOptions("{" +
					"categories: [" +
						"'2016', " +
						"'2017', " +
						"'2018', " +
						"'2019', " +
						"'2020', " +
						"'2021', " +
						"'2022'" +
					"]," +
					"tickmarkPlacement: 'on'," +
					"title: {" +
						"enabled: false" +
					"}" +
				"}");
		chartComp13.setyAxisOptions("{" +
					"labels: {" +
						"formatter: function() {" +
							"return this.value / 100;" +
						"}" +
					"}" +
				"}");
		chartComp13.setTooltipFormatter("function formatTooltip(obj){" +
					"return ''+obj.x +': '+ Highcharts.numberFormat(obj.y, 0, ',') +' reservas-pagos';" +
				"}");
		chartComp13.setYAxisTitle("Reservas-Pagos");
		chartComp13.setLegend("{" +
					"layout: 'vertical'," +
					"align: 'left'," +
					"verticalAlign: 'top'," +
					"x: 100, " +
					"y: 70," +
					"floating: true," +
					"backgroundColor: '#3BA420'," +
					"borderWidth: 1" +
				"}");
		chartComp13.setPlotOptions("{" +
					"area: {" +
						"stacking: 'normal'," +
						"lineColor: '#3BA420', " +
						"lineWidth: 1," +
						"marker: {" +
							"lineWidth: 1, " +
							"lineColor: '#3BA420'" +
						"}" +
					"}" +
				"}");
				
		chartComp13.setModel(dataChartModel13);
		
		//Adding some data to the model

		dataChartModel13.addValue("Reservas", 0, 502);
		dataChartModel13.addValue("Reservas", 1, 635);
		dataChartModel13.addValue("Reservas", 2, 809);
		dataChartModel13.addValue("Reservas", 3, 947);
		dataChartModel13.addValue("Reservas", 4, 1402);
		dataChartModel13.addValue("Reservas", 5, 3634);
		dataChartModel13.addValue("Reservas", 6, 5268);
		
		dataChartModel13.addValue("Pagos", 0, 106);
		dataChartModel13.addValue("Pagos", 1, 107);
		dataChartModel13.addValue("Pagos", 2, 111);
		dataChartModel13.addValue("Pagos", 3, 133);
		dataChartModel13.addValue("Pagos", 4, 224);
		dataChartModel13.addValue("Pagos", 5, 767);
		dataChartModel13.addValue("Pagos", 6, 1766);
					
	}
	
	@Override
	public void onEvent(Event arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * internal method to convert date&amp;time from string to epoch milliseconds
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	private long getDateTime(String date) throws Exception {
		return sdf.parse(date).getTime();
	}
}
