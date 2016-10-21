package com.pricing.model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.ngi.zhighcharts.SimpleExtXYModel;
import org.ngi.zhighcharts.ZGauge;
import org.ngi.zhighcharts.ZHighCharts;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.SimplePieModel;
import org.zkoss.zul.Window;

import com.pricing.dao.CReporteReservaDAO;
public class CEstadistica_Reservas extends Window implements EventListener {
	protected Log logger = Log.lookup(this.getClass());

	// Stacked column
	private ZHighCharts chartComp28;
	private SimpleExtXYModel dataChartModel28 = new SimpleExtXYModel();
	
	// Stacked and grouped column
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
    private CReporteReservaDAO reporteReservaDao;
    private ArrayList<CEstadistica_Paquete> listaPaquetesMasVendidos;
    private ArrayList<CEstadistica_Paquete> masVendidoEnero;
    private ArrayList<CEstadistica_Paquete> masVendidoFebrero;
    private ArrayList<CEstadistica_Paquete> masVendidoMarzo;
    private ArrayList<CEstadistica_Paquete> masVendidoAbril;
    private ArrayList<CEstadistica_Paquete> masVendidoMayo;
    private ArrayList<CEstadistica_Paquete> masVendidoJunio;
    private ArrayList<CEstadistica_Paquete> masVendidoJulio;
    private ArrayList<CEstadistica_Paquete> masVendidoAgosto;
    private ArrayList<CEstadistica_Paquete> masVendidoSetiembre;
    private ArrayList<CEstadistica_Paquete> masVendidoOctubre;
    private ArrayList<CEstadistica_Paquete> masVendidoNoviembre;
    private ArrayList<CEstadistica_Paquete> masVendidoDiciembre;
    
    //===============getter and setter======
	
	public CReporteReservaDAO getReporteReservaDao() {
		return reporteReservaDao;
	}

	public void setReporteReservaDao(CReporteReservaDAO reporteReservaDao) {
		this.reporteReservaDao = reporteReservaDao;
	}

	public ArrayList<CEstadistica_Paquete> getListaPaquetesMasVendidos() {
		return listaPaquetesMasVendidos;
	}

	public void setListaPaquetesMasVendidos(
			ArrayList<CEstadistica_Paquete> listaPaquetesMasVendidos) {
		this.listaPaquetesMasVendidos = listaPaquetesMasVendidos;
	}
	
	
	public ArrayList<CEstadistica_Paquete> getMasVendidoEnero() {
		return masVendidoEnero;
	}

	public void setMasVendidoEnero(ArrayList<CEstadistica_Paquete> masVendidoEnero) {
		this.masVendidoEnero = masVendidoEnero;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoFebrero() {
		return masVendidoFebrero;
	}

	public void setMasVendidoFebrero(
			ArrayList<CEstadistica_Paquete> masVendidoFebrero) {
		this.masVendidoFebrero = masVendidoFebrero;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoMarzo() {
		return masVendidoMarzo;
	}

	public void setMasVendidoMarzo(ArrayList<CEstadistica_Paquete> masVendidoMarzo) {
		this.masVendidoMarzo = masVendidoMarzo;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoAbril() {
		return masVendidoAbril;
	}

	public void setMasVendidoAbril(ArrayList<CEstadistica_Paquete> masVendidoAbril) {
		this.masVendidoAbril = masVendidoAbril;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoMayo() {
		return masVendidoMayo;
	}

	public void setMasVendidoMayo(ArrayList<CEstadistica_Paquete> masVendidoMayo) {
		this.masVendidoMayo = masVendidoMayo;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoJunio() {
		return masVendidoJunio;
	}

	public void setMasVendidoJunio(ArrayList<CEstadistica_Paquete> masVendidoJunio) {
		this.masVendidoJunio = masVendidoJunio;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoJulio() {
		return masVendidoJulio;
	}

	public void setMasVendidoJulio(ArrayList<CEstadistica_Paquete> masVendidoJulio) {
		this.masVendidoJulio = masVendidoJulio;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoAgosto() {
		return masVendidoAgosto;
	}

	public void setMasVendidoAgosto(ArrayList<CEstadistica_Paquete> masVendidoAgosto) {
		this.masVendidoAgosto = masVendidoAgosto;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoSetiembre() {
		return masVendidoSetiembre;
	}

	public void setMasVendidoSetiembre(
			ArrayList<CEstadistica_Paquete> masVendidoSetiembre) {
		this.masVendidoSetiembre = masVendidoSetiembre;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoOctubre() {
		return masVendidoOctubre;
	}

	public void setMasVendidoOctubre(
			ArrayList<CEstadistica_Paquete> masVendidoOctubre) {
		this.masVendidoOctubre = masVendidoOctubre;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoNoviembre() {
		return masVendidoNoviembre;
	}

	public void setMasVendidoNoviembre(
			ArrayList<CEstadistica_Paquete> masVendidoNoviembre) {
		this.masVendidoNoviembre = masVendidoNoviembre;
	}

	public ArrayList<CEstadistica_Paquete> getMasVendidoDiciembre() {
		return masVendidoDiciembre;
	}

	public void setMasVendidoDiciembre(
			ArrayList<CEstadistica_Paquete> masVendidoDiciembre) {
		this.masVendidoDiciembre = masVendidoDiciembre;
	}

	public CEstadistica_Reservas()
	{
	    reporteReservaDao=new CReporteReservaDAO();
	    masVendidoEnero=new ArrayList<CEstadistica_Paquete>();
		Calendar fechaActual=Calendar.getInstance();
		int anio=fechaActual.get(Calendar.YEAR);
		String anioActual=String.valueOf(anio).toString();
		System.out.println("el anio es es:"+anioActual);
		reporteReservaDao.asignarPaquetesmasVendidos(reporteReservaDao.recuperarPaquetesMasVendidos(anioActual));
		this.setListaPaquetesMasVendidos(reporteReservaDao.getMasVendidosxMeses());
		int factorIncremento=0;
		for(int i=0;i<listaPaquetesMasVendidos.size();i=i+factorIncremento)
		{
			String nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
			int contador=i;
			int total=0;
			while(contador<listaPaquetesMasVendidos.size() && listaPaquetesMasVendidos.get(contador).getNombrePaquete().equals(nombrePaqueteAnterior))
			{
				 total+=listaPaquetesMasVendidos.get(contador).getNroVentas();
				 contador++;
				 factorIncremento++;
			}
			masVendidoEnero.add(new CEstadistica_Paquete(listaPaquetesMasVendidos.get(i).getNombrePaquete(), total));
		}
	}
	
	public void onCreate() throws Exception {

		//================================================================================
	    // Stacked and grouped column
	    //================================================================================

		chartComp28 = (ZHighCharts) getFellow("chartComp28");
		chartComp28.setType("column");
		chartComp28.setTitle("Paquetes mas vendidos durante el anio");
		chartComp28.setxAxisOptions("{" +
				"categories: [" +
					"'Enero'," +
					"'Febrero', " +
					"'Marzo', " +
					"'Abril', " +
					"'Mayo', " +
					"'Junio', " +
					"'Julio', " +
					"'Agosto', " +
					"'Setiembre', " +
					"'Noviembre', " +
					"'Diciembre'," +
				"]" +
			"}");
		chartComp28.setyAxisOptions("{" +
					"min: 0," +
					"allowDecimals: false," +
				"}");
		chartComp28.setYAxisTitle("Cantidad de paquetes");
		chartComp28.setTooltipFormatter("function formatTooltip(obj){ " +
					"return '<b>'+ obj.x +'</b><br/>" +
					"'+obj.series.name +': '+ obj.y +'<br/>" +
					"'+'Total: '+ obj.point.stackTotal;" +
				"}");
		chartComp28.setPlotOptions("{" +
					"column: {" +
						"stacking: 'normal'" +
					"}" +
				"}");
		
		chartComp28.setModel(dataChartModel28);
		
		Number Johndata28 [] = { masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()};
		for(int i = 0; i < Johndata28.length; i++)
			dataChartModel28.addValue("John", i, Johndata28[i]);
		
		Map style = new HashMap();
		style.put("stack", "male");
		chartComp28.setSeriesOptions("", style);
	}

	@Override
	public void onEvent(Event arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * internal method to convert date&time from string to epoch milliseconds
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	private long getDateTime(String date) throws Exception {
		return sdf.parse(date).getTime();
	}
}
