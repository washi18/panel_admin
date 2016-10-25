package com.pricing.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.ngi.zhighcharts.SimpleExtXYModel;
import org.ngi.zhighcharts.ZGauge;
import org.ngi.zhighcharts.ZHighCharts;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.SimplePieModel;
import org.zkoss.zul.Window;

import com.pricing.dao.CReporteReservaDAO;
import com.pricing.viewModel.CEstadisticaReservaVM;
public class CEstadistica_Reservas extends Window implements EventListener {
	protected Log logger = Log.lookup(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
	// Stacked column
	private ZHighCharts chartComp28;
	private SimpleExtXYModel dataChartModel28 = new SimpleExtXYModel();
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
    private long sumasMeses[]=new long[12];
	private String nombresPaquetes[]={" "," "," "," "," "," "," "," "," "," "," "," "};
    private CEstadistica_Reservas estadisticaReservas;
    private String anio="2016";
    
    public long[] getSumasMeses() {
		return sumasMeses;
	}

	public void setSumasMeses(long[] sumasMeses) {
		this.sumasMeses = sumasMeses;
	}

	public String[] getNombresPaquetes() {
		return nombresPaquetes;
	}

	public void setNombresPaquetes(String[] nombresPaquetes) {
		this.nombresPaquetes = nombresPaquetes;
	}
	
	public CEstadistica_Reservas getEstadisticaReservas() {
		return estadisticaReservas;
	}

	public void setEstadisticaReservas(CEstadistica_Reservas estadisticaReservas) {
		this.estadisticaReservas = estadisticaReservas;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}


	//=====meses del anio=====
    String eneroInicio;
	String eneroFinal;
	String febreroInicio;
	String febreroFinal;
	String marzoInicio;
	String marzoFinal;
	String abrilInicio;
	String abrilFinal;
	String mayoInicio;
	String mayoFinal;
	String junioInicio;
	String junioFinal;
	String julioInicio;
	String julioFinal;
	String agostoInicio;
	String agostoFinal;
	String setiembreInicio;
	String setiembreFinal;
	String octubreInicio;
	String octubreFinal;
	String noviembreInicio;
	String noviembreFinal;
	String diciembreInicio;
	String diciembreFinal;
	//=====en formato date==========
	Date EneroInicio=null;
	Date EneroFinal=null;
	Date FebreroInicio=null;
	Date FebreroFinal=null;
	Date MarzoInicio=null;
	Date MarzoFinal=null;
	Date AbrilInicio=null;
	Date AbrilFinal=null;
	Date MayoInicio=null;
	Date MayoFinal=null;
	Date JunioInicio=null;
	Date JunioFinal=null;
	Date JulioInicio=null;
	Date JulioFinal=null;
	Date AgostoInicio=null;
	Date AgostoFinal=null;
	Date SetiembreInicio=null;
	Date SetiembreFinal=null;
	Date OctubreInicio=null;
	Date OctubreFinal=null;
	Date NoviembreInicio=null;
	Date NoviembreFinal=null;
	Date DiciembreInicio=null;
	Date DiciembreFinal=null;
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
		reporteReservaDao.asignarPaquetesmasVendidos(reporteReservaDao.recuperarPaquetesMasVendidos(this.anio));
		this.setListaPaquetesMasVendidos(reporteReservaDao.getMasVendidosxMeses());
		System.out.println("el tamanio de la lista mas vendidos es:"+listaPaquetesMasVendidos.size());
		asignarAnioMeses(this.anio);
		//======meses del anio con inicio y fin=====
		String nombrePaqueteAnterior=listaPaquetesMasVendidos.get(0).getNombrePaquete();
		for(int i=0;i<listaPaquetesMasVendidos.size();i++)
		{
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(EneroInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(EneroFinal))
			{
				System.out.println("Estamos en enero");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[0]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[0]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[0]){
						sumasMeses[0]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[0]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(FebreroInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(FebreroFinal))
			{
				System.out.println("Estamos en febrero");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[1]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[1]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[1]){
						sumasMeses[1]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[1]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(MarzoInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(MarzoFinal))
			{
				System.out.println("Estamos en marzo");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[2]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[2]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[2]){
						sumasMeses[2]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[2]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(AbrilInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(AbrilFinal))
			{
				System.out.println("Estamos en abril");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[3]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[3]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[3]){
						sumasMeses[3]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[3]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(MayoInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(MayoFinal))
			{
				System.out.println("Estamos en mayo");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[4]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[4]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[4]){
						sumasMeses[4]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[4]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(JunioInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(JunioFinal))
			{
				System.out.println("Estamos en junio");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[5]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[5]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[5]){
						sumasMeses[5]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[5]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(JulioInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(JulioFinal))
			{
				System.out.println("Estamos en julio");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[6]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[6]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					System.out.println("vaklor de suma if: julio"+sumasMeses[6]);
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[6]){
						sumasMeses[6]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[6]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						System.out.println("vaklor de suma else: julio"+sumasMeses[6]);
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(AgostoInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(AgostoFinal))
			{
				System.out.println("Estamos en agosto");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[7]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[7]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					System.out.println("vaklor de suma: agosto"+sumasMeses[7]);
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[7]){
						sumasMeses[7]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[7]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(SetiembreInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(SetiembreFinal))
			{
				System.out.println("Estamos en setiembre");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[8]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[8]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[8]){
						sumasMeses[8]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[8]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(OctubreInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(OctubreFinal))
			{
				System.out.println("Estamos en octubre");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[9]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[9]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[9]){
						sumasMeses[9]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[9]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(NoviembreInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(NoviembreFinal))
			{
				System.out.println("Estamos en noviembre");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[10]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[10]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[10]){
						sumasMeses[10]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[10]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(DiciembreInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(DiciembreFinal))
			{
				System.out.println("Estamos en diciembre");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[11]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[11]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[11]){
						sumasMeses[11]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[11]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}
		}
	}
	public void asignarAnioMeses(String anioActual)
	{
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		eneroInicio=anioActual+"-01-01";
		eneroFinal=anioActual+"-01-31";
		febreroInicio=anioActual+"-02-01";
		febreroFinal=anioActual+"-02-31";
		marzoInicio=anioActual+"-03-01";
		marzoFinal=anioActual+"-03-31";
		abrilInicio=anioActual+"-04-01";
		abrilFinal=anioActual+"-04-31";
		mayoInicio=anioActual+"-05-01";
		mayoFinal=anioActual+"-05-31";
		junioInicio=anioActual+"-06-01";
		junioFinal=anioActual+"-06-31";
		julioInicio=anioActual+"-07-01";
		julioFinal=anioActual+"-07-31";
		agostoInicio=anioActual+"-08-01";
		agostoFinal=anioActual+"-08-31";
		setiembreInicio=anioActual+"-09-01";
		setiembreFinal=anioActual+"-09-31";
		octubreInicio=anioActual+"-10-01";
		octubreFinal=anioActual+"-10-31";
		noviembreInicio=anioActual+"-11-01";
		noviembreFinal=anioActual+"-11-31";
		diciembreInicio=anioActual+"-12-01";
		diciembreFinal=anioActual+"-12-31";
		
		try {
			 EneroInicio=formato.parse(eneroInicio);
			EneroFinal=formato.parse(eneroFinal);
			FebreroInicio=formato.parse(febreroInicio);
			FebreroFinal=formato.parse(febreroFinal);
			MarzoInicio=formato.parse(marzoInicio);
			MarzoFinal=formato.parse(marzoFinal);
			AbrilInicio=formato.parse(abrilInicio);
			AbrilFinal=formato.parse(abrilFinal);
		    MayoInicio=formato.parse(mayoInicio);
			MayoFinal=formato.parse(mayoFinal);
			JunioInicio=formato.parse(junioInicio);
			JunioFinal=formato.parse(junioFinal);
			JulioInicio=formato.parse(julioInicio);
			JulioFinal=formato.parse(julioFinal);
			AgostoInicio=formato.parse(agostoInicio);
			AgostoFinal=formato.parse(agostoFinal);
			SetiembreInicio=formato.parse(setiembreInicio);
			SetiembreFinal=formato.parse(setiembreFinal);
			OctubreInicio=formato.parse(octubreInicio);
			OctubreFinal=formato.parse(octubreFinal);
			NoviembreInicio=formato.parse(noviembreInicio);
			NoviembreFinal=formato.parse(noviembreFinal);
			DiciembreInicio=formato.parse(diciembreInicio);
			DiciembreFinal=formato.parse(diciembreFinal);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Command
	public void ejecutarGrafico() throws Exception
	{
		chartComp28 = (ZHighCharts) getFellow("chartComp28");
		chartComp28.setType("column");
		chartComp28.setTitle("Paquetes mas vendidos durante el año");
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
					"'Octubre', " +
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
					"'+obj.series.name+': '+ obj.y +'<br/>" +
					"'+'Total: '+ obj.point.stackTotal;" +
				"}");
		chartComp28.setPlotOptions("{" +
					"column: {" +
						"stacking: 'normal'" +
					"}" +
				"}");
		
		chartComp28.setModel(dataChartModel28);
		
		//==================enero==================
				Number enero [] = { sumasMeses[0],0,0,0,0,0,0,0,0,0,0,0};
				System.out.println("suma mese julio"+sumasMeses[0]);
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				for(int i = 0; i < enero.length; i++)
				dataChartModel28.addValue(nombresPaquetes[0], i, enero[i]);
				
				Map style = new HashMap();
				style.put("stack", "enero");
				chartComp28.setSeriesOptions("enero", style);
				//==================febrero==================
				Number febrero [] = { 0,sumasMeses[1],0,0,0,0,0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[1], 1, febrero[1]);
				
				style = new HashMap();
				style.put("stack", "febrero");
				chartComp28.setSeriesOptions("febrero", style);
				//==================febrero==================
				Number marzo [] = { 0,0,sumasMeses[2],0,0,0,0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[2], 2, marzo[2]);
				
				style = new HashMap();
				style.put("stack", "marzo");
				chartComp28.setSeriesOptions("marzo", style);
				//==================febrero==================
				Number abril [] = { 0,0,0,sumasMeses[3],0,0,0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[3], 3, abril[3]);
				
				style = new HashMap();
				style.put("stack", "abril");
				chartComp28.setSeriesOptions("abril", style);
				//==================febrero==================
				Number mayo [] = { 0,0,0,0,sumasMeses[4],0,0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[4], 4, mayo[4]);
				
				style = new HashMap();
				style.put("stack", "mayo");
				chartComp28.setSeriesOptions("mayo", style);
				//==================febrero==================
				Number junio [] = { 0,0,0,0,0,sumasMeses[5],0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[5], 5, junio[5]);
				
				style = new HashMap();
				style.put("stack", "junio");
				chartComp28.setSeriesOptions("junio", style);
				//==================febrero==================
				Number julio [] = { 0,0,0,0,0,0,sumasMeses[6],0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				System.out.println("suma mese julio"+sumasMeses[6]);
				dataChartModel28.addValue(nombresPaquetes[6], 6, julio[6]);
				
				style = new HashMap();
				style.put("stack", "julio");
				chartComp28.setSeriesOptions("julio", style);
				//==================febrero==================
				Number agosto [] = { 0,0,0,0,0,0,0,sumasMeses[7],0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[7], 7, agosto[7]);
				
				style = new HashMap();
				style.put("stack", "agosto");
				chartComp28.setSeriesOptions("agosto", style);
				//==================febrero==================
				Number setiembre [] = { 0,0,0,0,0,0,0,0,sumasMeses[8],0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[8], 8, setiembre[8]);
				
				style = new HashMap();
				style.put("stack", "setiembre");
				chartComp28.setSeriesOptions("setiembre", style);
				//==================febrero==================
				Number octubre [] = {0,0,0,0,0,0,0,0,0,sumasMeses[9],0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[9], 9, octubre[9]);
				
				style = new HashMap();
				style.put("stack", "octubre");
				chartComp28.setSeriesOptions("octubre", style);
				//==================febrero==================
				Number noviembre [] = { 0,0,0,0,0,0,0,0,0,0,sumasMeses[10],0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[10], 10, noviembre[10]);
				
				style = new HashMap();
				style.put("stack", "noviembre");
				chartComp28.setSeriesOptions("noviembre", style);
				//==================febrero==================
				Number diciembre [] = { 0,0,0,0,0,0,0,0,0,0,0,sumasMeses[11]};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[11], 11, diciembre[11]);
				
				 style = new HashMap();
				style.put("stack", "diciembre");
				chartComp28.setSeriesOptions("diciembre", style);
	}
	public void onCreate() throws Exception {
		//================================================================================
	    // Stacked and grouped column
	    //================================================================================	
		chartComp28 = (ZHighCharts) getFellow("chartComp28");
		chartComp28.setType("column");
		chartComp28.setTitle("Paquetes mas vendidos durante el año");
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
					"'Octubre', " +
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
					"'+obj.series.name+': '+ obj.y +'<br/>" +
					"'+'Total: '+ obj.point.stackTotal;" +
				"}");
		chartComp28.setPlotOptions("{" +
					"column: {" +
						"stacking: 'normal'" +
					"}" +
				"}");
		
		chartComp28.setModel(dataChartModel28);
		
		//==================enero==================
				Number enero [] = { sumasMeses[0],0,0,0,0,0,0,0,0,0,0,0};
				System.out.println("suma mese julio"+sumasMeses[0]);
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				for(int i = 0; i < enero.length; i++)
				dataChartModel28.addValue(nombresPaquetes[0], i, enero[i]);
				
				Map style = new HashMap();
				style.put("stack", "enero");
				chartComp28.setSeriesOptions("enero", style);
				//==================febrero==================
				Number febrero [] = { 0,sumasMeses[1],0,0,0,0,0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[1], 1, febrero[1]);
				
				style = new HashMap();
				style.put("stack", "febrero");
				chartComp28.setSeriesOptions("febrero", style);
				//==================febrero==================
				Number marzo [] = { 0,0,sumasMeses[2],0,0,0,0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[2], 2, marzo[2]);
				
				style = new HashMap();
				style.put("stack", "marzo");
				chartComp28.setSeriesOptions("marzo", style);
				//==================febrero==================
				Number abril [] = { 0,0,0,sumasMeses[3],0,0,0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[3], 3, abril[3]);
				
				style = new HashMap();
				style.put("stack", "abril");
				chartComp28.setSeriesOptions("abril", style);
				//==================febrero==================
				Number mayo [] = { 0,0,0,0,sumasMeses[4],0,0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[4], 4, mayo[4]);
				
				style = new HashMap();
				style.put("stack", "mayo");
				chartComp28.setSeriesOptions("mayo", style);
				//==================febrero==================
				Number junio [] = { 0,0,0,0,0,sumasMeses[5],0,0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[5], 5, junio[5]);
				
				style = new HashMap();
				style.put("stack", "junio");
				chartComp28.setSeriesOptions("junio", style);
				//==================febrero==================
				Number julio [] = { 0,0,0,0,0,0,sumasMeses[6],0,0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				System.out.println("suma mese julio"+sumasMeses[6]);
				dataChartModel28.addValue(nombresPaquetes[6], 6, julio[6]);
				
				style = new HashMap();
				style.put("stack", "julio");
				chartComp28.setSeriesOptions("julio", style);
				//==================febrero==================
				Number agosto [] = { 0,0,0,0,0,0,0,sumasMeses[7],0,0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[7], 7, agosto[7]);
				
				style = new HashMap();
				style.put("stack", "agosto");
				chartComp28.setSeriesOptions("agosto", style);
				//==================febrero==================
				Number setiembre [] = { 0,0,0,0,0,0,0,0,sumasMeses[8],0,0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[8], 8, setiembre[8]);
				
				style = new HashMap();
				style.put("stack", "setiembre");
				chartComp28.setSeriesOptions("setiembre", style);
				//==================febrero==================
				Number octubre [] = {0,0,0,0,0,0,0,0,0,sumasMeses[9],0,0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[9], 9, octubre[9]);
				
				style = new HashMap();
				style.put("stack", "octubre");
				chartComp28.setSeriesOptions("octubre", style);
				//==================febrero==================
				Number noviembre [] = { 0,0,0,0,0,0,0,0,0,0,sumasMeses[10],0};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[10], 10, noviembre[10]);
				
				style = new HashMap();
				style.put("stack", "noviembre");
				chartComp28.setSeriesOptions("noviembre", style);
				//==================febrero==================
				Number diciembre [] = { 0,0,0,0,0,0,0,0,0,0,0,sumasMeses[11]};
				/*masVendidoEnero.get(0).getNroVentas(), masVendidoFebrero.get(0).getNroVentas()*/
				dataChartModel28.addValue(nombresPaquetes[11], 11, diciembre[11]);
				
				 style = new HashMap();
				style.put("stack", "diciembre");
				chartComp28.setSeriesOptions("diciembre", style);
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
