package com.pricing.viewModel;

import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CDestino;
import com.pricing.model.CDestinoConHoteles;
import com.pricing.model.CHotel;
import com.pricing.model.CPasajero;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;
import com.pricing.model.CServicioConSubServicios;
import com.pricing.model.CSubServicio;

public class reporteReservasVM {
	//======atributos=====
	private ArrayList<CReporteReserva> listaReporteReserva;
	private CReporteReservaDAO reporteReservaDAO;
	private String FechaInicio;
	private String FechaFinal;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CHotel> listaHotelesTemp;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CSubServicio> listasubServicios;
	private ArrayList<CSubServicio> listaSubServiciosTemp;
	private ArrayList<CDestinoConHoteles> listaDestinosconHoteles;
	private ArrayList<CServicioConSubServicios> listaServicioconSubServicios;
	private CReporteReserva reporteReservaAnterior;
	
	//=======getter and setter=====
	
	public ArrayList<CReporteReserva> getListaReporteReserva() {
		return listaReporteReserva;
	}

	public void setListaReporteReserva(
			ArrayList<CReporteReserva> listaReporteReserva) {
		this.listaReporteReserva = listaReporteReserva;
	}

	public CReporteReservaDAO getReporteReservaDAO() {
		return reporteReservaDAO;
	}
	public void setReporteReservaDAO(CReporteReservaDAO reporteReservaDAO) {
		this.reporteReservaDAO = reporteReservaDAO;
	}
	public String getFechaInicio() {
		return FechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public String getFechaFinal() {
		return FechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		FechaFinal = fechaFinal;
	}
	

	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}

	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}

	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}

	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}

	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public ArrayList<CSubServicio> getListasubServicios() {
		return listasubServicios;
	}

	public void setListasubServicios(ArrayList<CSubServicio> listasubServicios) {
		this.listasubServicios = listasubServicios;
	}
	
	public CReporteReserva getReporteReservaAnterior() {
		return reporteReservaAnterior;
	}

	public void setReporteReservaAnterior(CReporteReserva reporteReservaAnterior) {
		this.reporteReservaAnterior = reporteReservaAnterior;
	}
	
	public ArrayList<CDestinoConHoteles> getListaDestinosconHoteles() {
		return listaDestinosconHoteles;
	}

	public void setListaDestinosconHoteles(
			ArrayList<CDestinoConHoteles> listaDestinosconHoteles) {
		this.listaDestinosconHoteles = listaDestinosconHoteles;
	}

	public ArrayList<CHotel> getListaHotelesTemp() {
		return listaHotelesTemp;
	}

	public void setListaHotelesTemp(ArrayList<CHotel> listaHotelesTemp) {
		this.listaHotelesTemp = listaHotelesTemp;
	}
	
	public ArrayList<CServicioConSubServicios> getListaServicioconSubServicios() {
		return listaServicioconSubServicios;
	}

	public void setListaServicioconSubServicios(
			ArrayList<CServicioConSubServicios> listaServicioconSubServicios) {
		this.listaServicioconSubServicios = listaServicioconSubServicios;
	}
	
	public ArrayList<CSubServicio> getListaSubServiciosTemp() {
		return listaSubServiciosTemp;
	}

	public void setListaSubServiciosTemp(
			ArrayList<CSubServicio> listaSubServiciosTemp) {
		this.listaSubServiciosTemp = listaSubServiciosTemp;
	}

	//======metodos=====
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		listaReporteReserva=new ArrayList<CReporteReserva>();
		FechaInicio="";
		FechaFinal="";
		reporteReservaDAO=new CReporteReservaDAO();
		reporteReservaAnterior=new CReporteReserva();
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
	}
	
	@Command
	@NotifyChange("listaDestinos")
	public void habilitarDestinosPOP(@BindingParam("creserva") CReporteReserva destinos)
	{
		reporteReservaDAO.asignarDestinosReserva(reporteReservaDAO.recuperarDestinosReservaBD(destinos.getCodReserva()));
		this.setListaDestinos(reporteReservaDAO.getListaDestinosReserva());
		destinos.setListaDestinos(this.getListaDestinos());
		if(!destinos.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			if(this.getListaDestinos().isEmpty()){
				destinos.setVisibleDestinospop(false);
				destinos.setColornoExisteListaDestinos("background: #DA0613;");
			}
			else{
				destinos.setVisibleDestinospop(true);
				destinos.setColornoExisteListaDestinos("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleDestinospop(false);
			reporteReservaAnterior=destinos;
		}
		else{
			destinos.setVisibleDestinospop(true);
		}
		BindUtils.postNotifyChange(null, null, destinos,"visibleDestinospop");
		BindUtils.postNotifyChange(null, null, destinos,"listaDestinos");
		BindUtils.postNotifyChange(null, null, destinos,"colornoExisteListaDestinos");
	}
	@Command
	@NotifyChange({"listaSubServicios","listaSubServiciosTemp","listaServiciosconSubServicios"})
	public void habilitarSubServiciosPOP(@BindingParam("creserva") CReporteReserva hoteles)
	{
		reporteReservaDAO.asignarSubServiciosReserva(reporteReservaDAO.recuperarSubServiciosReservaBD(hoteles.getCodReserva()));
		this.setListasubServicios(reporteReservaDAO.getListaSubServiciosReserva());
		int valorincremento;
		listaServicioconSubServicios=new ArrayList<CServicioConSubServicios>();
		for(int i=0; i<listasubServicios.size();i=i+valorincremento)
        {
        	String ServicioAnterior=listasubServicios.get(i).getcNombreServicio();
        	int contador=i;
        	valorincremento=0;
        	listaSubServiciosTemp=new ArrayList<CSubServicio>();
        	while(contador<listasubServicios.size() && listasubServicios.get(contador).getcNombreServicio().equals(ServicioAnterior))
        	{
        		listaSubServiciosTemp.add(new CSubServicio(listasubServicios.get(contador).getcSubServicioIndioma1(),listasubServicios.get(contador).getnPrecioServicio()));
        		valorincremento++;
        		contador++;
        		System.out.println("el valor de contador es:"+contador);
        	}
        	listaServicioconSubServicios.add(new CServicioConSubServicios(listasubServicios.get(i).getcNombreServicio().toString(),listaSubServiciosTemp));
        	System.out.println("termina esto?");
        }
		hoteles.setListaServicioConSubServicios(listaServicioconSubServicios);
		
		if(!hoteles.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			if(this.getListaServicioconSubServicios().isEmpty()){
				hoteles.setVisibleSubServiciopop(false);
				hoteles.setColornoExisteListaSubServicios("background: #DA0613;");
			}
			else{
				hoteles.setVisibleSubServiciopop(true);
				hoteles.setColornoExisteListaSubServicios("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleSubServiciopop(false);
			reporteReservaAnterior=hoteles;
		}
		else {
			hoteles.setVisibleHotelespop(true);
		}
		BindUtils.postNotifyChange(null, null, hoteles,"visibleSubServiciopop");
		BindUtils.postNotifyChange(null, null, hoteles,"colornoExisteListaSubServicios");
		BindUtils.postNotifyChange(null, null, hoteles,"listaServicioConSubServicios");
	}
	
	@Command
	@NotifyChange({"listaHoteles","listaHotelesTemp","listaDestinosconHoteles"})
	public void habilitarHotelesPOP(@BindingParam("creserva") CReporteReserva reserva)
	{
		reporteReservaDAO.asignarHotelesReserva(reporteReservaDAO.recuperarHotelesReservaBD(reserva.getCodReserva(),reserva.getCodCategoria()));
		this.setListaHoteles(reporteReservaDAO.getListaHotelesReserva());
		int valorincremento;
		listaDestinosconHoteles=new ArrayList<CDestinoConHoteles>();
		for(int i=0; i<listaHoteles.size();i=i+valorincremento)
        {
        	String DestinoAnterior=listaHoteles.get(i).getNombreDestino();
        	int contador=i;
        	valorincremento=0;
        	listaHotelesTemp=new ArrayList<CHotel>();
        	while(contador<listaHoteles.size() && listaHoteles.get(contador).getNombreDestino().equals(DestinoAnterior))
        	{
        		listaHotelesTemp.add(new CHotel(listaHoteles.get(contador).getcHotel(),listaHoteles.get(contador).getnPrecioSimple()));
        		valorincremento++;
        		contador++;
        		System.out.println("el valor de contador es:"+contador);
        	}
        	System.out.println("el valor de incremento es:"+valorincremento);
        	System.out.println("el valor de contador fuera del bucle es:"+contador);
        	System.out.println("el nombre de su destino es:"+listaHoteles.get(i).getNombreDestino());
        	System.out.println("el tamanio de hoteles es:"+ listaHotelesTemp.size());
        	listaDestinosconHoteles.add(new CDestinoConHoteles(listaHoteles.get(i).getNombreDestino().toString(),listaHotelesTemp));
        	System.out.println("termina esto?");
        }
		reserva.setListaDestinosconHoteles(listaDestinosconHoteles);
		
		if(!reserva.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			if(this.getListaDestinosconHoteles().isEmpty()){
				reserva.setVisibleHotelespop(false);
				reserva.setColornoExisteListaHoteles("background: #DA0613;");
			}
			else{
				reserva.setVisibleHotelespop(true);
				reserva.setColornoExisteListaHoteles("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleHotelespop(false);
			reporteReservaAnterior=reserva;
		}
		else {
			reserva.setVisibleHotelespop(true);
		}
		BindUtils.postNotifyChange(null, null, reserva,"visibleHotelespop");
		BindUtils.postNotifyChange(null, null, reserva,"colornoExisteListaHoteles");
		BindUtils.postNotifyChange(null, null, reserva,"listaDestinosconHoteles");
	}
	
	@Command
	@NotifyChange("listaServicios")
	public void habilitarServiciosPOP(@BindingParam("creserva") CReporteReserva servicio)
	{
		reporteReservaDAO.asignarServiciosReserva(reporteReservaDAO.recuperarServiciosReservaBD(servicio.getCodReserva()));
		this.setListaServicios(reporteReservaDAO.getListaServiciosReserva());
		servicio.setListaServicios(this.getListaServicios());
		if(!servicio.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			if(this.getListaServicios().isEmpty()){
				servicio.setVisibleServiciospop(false);
				servicio.setColornoExisteListaServicios("background: #DA0613;");
			}
			else{
				servicio.setVisibleServiciospop(true);
				servicio.setColornoExisteListaServicios("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleServiciospop(false);
			reporteReservaAnterior=servicio;
		}else{
			servicio.setVisibleServiciospop(true);
		}
		BindUtils.postNotifyChange(null, null, servicio,"visibleServiciospop");
		BindUtils.postNotifyChange(null, null, servicio,"listaServicios");
		BindUtils.postNotifyChange(null, null, servicio,"colornoExisteListaServicios");
	}
	
	@Command
	@NotifyChange({"FechaInicio","FechaFinal"})
	public void recuperarFechaDatebox(@BindingParam("fecha")String fecha,@BindingParam("id")String id)
	{
		if(id.equals("db_desde"))
			FechaInicio=fecha;
		else
			FechaFinal=fecha;
	}
	
	@Command
	@NotifyChange({"listaReporteReserva","reporteReserva"})
	public void Buscar_Reservas(@BindingParam("componente")Component componente)
	{
		if(FechaInicio.isEmpty() || FechaFinal.isEmpty())
		{
			Clients.showNotification("Las fechas DESDE-HASTA son obligatorias ", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
		else
		{
			//-------despedasando la fecha desde------
			String diaStart=FechaInicio.substring(0,2);
			String mesStart=cambiarFormatoMes(FechaInicio.substring(3,6));
			String anioStart=FechaInicio.substring(7,11);
			//-------despedasando la fecha hasta------
			String diaEnd=FechaFinal.substring(0,2);
			String mesEnd=cambiarFormatoMes(FechaFinal.substring(3,6));
			String anioEnd=FechaFinal.substring(7,11);
			/*************Fecha Inicio*******************/
			String fecha1=anioStart+"-"+mesStart+"-"+diaStart;
			String fecha2=anioEnd+"-"+mesEnd+"-"+diaEnd;
			/****Validando la fecha****/
			listaReporteReserva.clear();
			System.out.println("entro aqui 1");
			reporteReservaDAO.asignarListaReporteReservas(reporteReservaDAO.recuperarReporteReservasBD(fecha1,fecha2));
			this.setListaReporteReserva(reporteReservaDAO.getListaReporteReservas());
			
		}
	}
	public String cambiarFormatoMes(String mes)
	{
		String nuevo="";
		switch(mes)
		{
			case "ene":nuevo="01";break;
			case "feb":nuevo="02";break;
			case "mar":nuevo="03";break;
			case "abr":nuevo="04";break;
			case "may":nuevo="05";break;
			case "jun":nuevo="06";break;
			case "jul":nuevo="07";break;
			case "ago":nuevo="08";break;
			case "sep":nuevo="09";break;
			case "oct":nuevo="10";break;
			case "nov":nuevo="11";break;
			case "dic":nuevo="12";break;
		}
		return nuevo;
	}
}
