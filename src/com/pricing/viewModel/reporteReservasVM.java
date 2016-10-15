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
import com.pricing.model.CHotel;
import com.pricing.model.CPasajero;
import com.pricing.model.CReportePagosMuestra;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class reporteReservasVM {
	//======atributos=====
	private ArrayList<CReporteReserva> listaReporteReserva;
	private CReporteReservaDAO reporteReservaDAO;
	private String FechaInicio;
	private String FechaFinal;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CSubServicio> listasubServicios;
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
	public void habilitarDestinosPOP(@BindingParam("creserva") CReporteReserva servicio)
	{
		if(!servicio.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			reporteReservaDAO.asignarDestinosReserva(reporteReservaDAO.recuperarDestinosReservaBD(servicio.getCodReserva()));
			this.setListaDestinos(reporteReservaDAO.getListaDestinosReserva());
			servicio.setListaDestinos(this.getListaDestinos());
			if(this.getListaDestinos().isEmpty()){
				servicio.setVisibleDestinospop(false);
				servicio.setColornoExisteListaDestinos("background: #DA0613;");
			}
			else{
				servicio.setVisibleDestinospop(true);
				servicio.setColornoExisteListaDestinos("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleDestinospop(false);
			reporteReservaAnterior=servicio;
		}
		else{
			servicio.setVisibleDestinospop(true);
		}
		BindUtils.postNotifyChange(null, null, servicio,"visibleDestinospop");
		BindUtils.postNotifyChange(null, null, servicio,"listaDestinos");
		BindUtils.postNotifyChange(null, null, servicio,"colornoExisteLista");
	}
	@Command
	@NotifyChange("listaHoteles")
	public void habilitarHotelesPOP(@BindingParam("creserva") CReporteReserva servicio)
	{
		if(!servicio.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			reporteReservaDAO.asignarHotelesReserva(reporteReservaDAO.recuperarHotelesReservaBD(servicio.getCodReserva(),servicio.getCodCategoria()));
			this.setListaHoteles(reporteReservaDAO.getListaHotelesReserva());
			servicio.setListaHoteles(this.getListaHoteles());
			if(this.getListaHoteles().isEmpty()){
				servicio.setVisibleHotelespop(false);
				servicio.setColornoExisteListaHoteles("background: #DA0613;");
			}
			else{
				servicio.setVisibleHotelespop(true);
				servicio.setColornoExisteListaHoteles("background: #3BA420;");
			}
			reporteReservaAnterior.setVisibleHotelespop(false);
			reporteReservaAnterior=servicio;
		}
		else {
			servicio.setVisibleHotelespop(true);
		}
		BindUtils.postNotifyChange(null, null, servicio,"visibleHotelespop");
		BindUtils.postNotifyChange(null, null, servicio,"listaHoteles");
		BindUtils.postNotifyChange(null, null, servicio,"colornoExisteListaHoteles");
	}
	@Command
	@NotifyChange("listaServicios")
	public void habilitarServiciosPOP(@BindingParam("creserva") CReporteReserva servicio)
	{
		if(!servicio.getCodReserva().equals(reporteReservaAnterior.getCodReserva()))
		{
			reporteReservaDAO.asignarServiciosReserva(reporteReservaDAO.recuperarServiciosReservaBD(servicio.getCodReserva()));
			this.setListaServicios(reporteReservaDAO.getListaServiciosReserva());
			servicio.setListaServicios(this.getListaServicios());
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
