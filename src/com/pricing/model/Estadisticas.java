package com.pricing.model;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.pricing.dao.CReportePagosDAO;
public class Estadisticas extends Window implements EventListener{
	//=====atributos===========
	private CReportePagosDAO reportePagoDAO;
	private ArrayList<CPago> listaPagos;
	protected Log logger = Log.lookup(this.getClass());
    //Pie chart
    private ZHighCharts chartComp2;
    private SimplePieModel pieModel = new SimplePieModel();
    // date format used to capture date time
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
    private DecimalFormat df=new DecimalFormat("0.00");
    //============getter and setter===============
    public CReportePagosDAO getReportePagoDAO() {
		return reportePagoDAO;
	}
	public void setReportePagoDAO(CReportePagosDAO reportePagoDAO) {
		this.reportePagoDAO = reportePagoDAO;
	}
	public ArrayList<CPago> getListaPagos() {
		return listaPagos;
	}
	public void setListaPagos(ArrayList<CPago> listaPagos) {
		this.listaPagos = listaPagos;
	}
	public Estadisticas()
	{
		reportePagoDAO=new CReportePagosDAO();
		listaPagos=new ArrayList<CPago>();
	}
	//======================metodos======
    public void onCreate() throws Exception {
    	reportePagoDAO.asignarListaPagos(reportePagoDAO.recuperarTodoPagosPaypalBD());
    	reportePagoDAO.asignarListaPagos(reportePagoDAO.recuperarTodoPagosVisaBD());
    	System.out.println("el tamnio de lista anterior es:"+reportePagoDAO.getListaPagos().size());
    	this.setListaPagos(reportePagoDAO.getListaPagos());
    	System.out.println("el tamanio de lista es:"+listaPagos.size());
    	long total=listaPagos.get(0).getCantidadPagos()+listaPagos.get(1).getCantidadPagos();
    	double porcentajeVisa=(listaPagos.get(0).getCantidadPagos()*100)/total;
    	double porcentajePaypal=(listaPagos.get(1).getCantidadPagos()*100)/total;
        //================================================================================
        // Pie chart
        //================================================================================
        chartComp2 = (ZHighCharts) getFellow("chartComp2");
        chartComp2.setTitle("Formas de pago");
        chartComp2.setSubTitle("------------porcentaje de pago---------------");
        chartComp2.setType("pie");
        chartComp2.setTooltipFormatter("function formatTooltip(obj){" +
                    "return obj.key + '<br />Forma Pago: <b>'+obj.y+'%</b>'" +
                "}");
        chartComp2.setPlotOptions("{" +
                "pie:{" +
                    "allowPointSelect: true," +
                    "cursor: 'pointer'," +
                    "dataLabels: {" +
                        "enabled: true, " +
                        "color: '#000000'," +
                        "connectorColor: '#000000'," +
                        "formatter: function() {"+
                            "return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';"+
                        "}"+
                    "}" +
                "}" +
            "}");                          
        chartComp2.setModel(pieModel);
        //Adding some data to the model
        pieModel.setValue(listaPagos.get(1).getModoPago(),porcentajeVisa); 
        pieModel.setValue(listaPagos.get(0).getModoPago(),porcentajePaypal);
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
