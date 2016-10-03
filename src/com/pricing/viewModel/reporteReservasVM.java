package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CReporteReservaDAO;
import com.pricing.dao.CReservaDAO;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CReserva;

public class reporteReservasVM {
	//======atributos=====
	private ArrayList<CReporteReserva> listaReporteReservas;
	private CReporteReservaDAO reporteReservaDAO;
	private boolean estadoPagoPendiente;
	private boolean estadoPagoParcial;
	private boolean estadoPagoTotal;
	private String FechaInicio;
	private String FechaFinal;
	
	//=======getter and setter=====
	
	public boolean isEstadoPagoPendiente() {
		return estadoPagoPendiente;
	}
	public ArrayList<CReporteReserva> getListaReporteReservas() {
		return listaReporteReservas;
	}
	public void setListaReporteReservas(
			ArrayList<CReporteReserva> listaReporteReservas) {
		this.listaReporteReservas = listaReporteReservas;
	}
	public CReporteReservaDAO getReporteReservaDAO() {
		return reporteReservaDAO;
	}
	public void setReporteReservaDAO(CReporteReservaDAO reporteReservaDAO) {
		this.reporteReservaDAO = reporteReservaDAO;
	}
	public void setEstadoPagoPendiente(boolean estadoPagoPendiente) {
		this.estadoPagoPendiente = estadoPagoPendiente;
	}
	public boolean isEstadoPagoParcial() {
		return estadoPagoParcial;
	}
	public void setEstadoPagoParcial(boolean estadoPagoParcial) {
		this.estadoPagoParcial = estadoPagoParcial;
	}
	public boolean isEstadoPagoTotal() {
		return estadoPagoTotal;
	}
	public void setEstadoPagoTotal(boolean estadoPagoTotal) {
		this.estadoPagoTotal = estadoPagoTotal;
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
	//======metodos=====
	@Init
	public void initVM()
	{
		estadoPagoParcial=false;
		estadoPagoPendiente=false;
		estadoPagoTotal=false;
		/**Inicializando los objetos**/
		listaReporteReservas=new ArrayList<CReporteReserva>();
		reporteReservaDAO=new CReporteReservaDAO();
		FechaInicio="";
		FechaFinal="";
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
	}
	
	@Command
	public void recuperarFechaDatebox(@BindingParam("fecha")String fecha,@BindingParam("id")String id)
	{
		if(id.equals("db_desde"))
			FechaInicio=fecha;
		else
			FechaFinal=fecha;
	}
	@Command
	@NotifyChange("listaReservas")
	public void Buscar_Reservas(@BindingParam("componente")Component componente)
	{
		System.out.println("La fecha inicio es: "+FechaInicio);
		System.out.println("La fecha final es: "+FechaFinal);
		System.out.println("La fecha inicio es: "+estadoPagoParcial);
		System.out.println("La fecha final es: "+estadoPagoPendiente);
		System.out.println("La fecha inicio es: "+estadoPagoTotal);
		if(FechaInicio.isEmpty() || FechaFinal.isEmpty())
		{
			Clients.showNotification("Las fechas DESDE-HASTA son obligatorias ", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
		else if(estadoPagoParcial==true || estadoPagoPendiente==true || estadoPagoTotal==true)
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
			String NombrePago="";
			if(estadoPagoPendiente)
			{
				estadoPagoPendiente=false;
				NombrePago="PENDIENTE DE PAGO";
			}else if(estadoPagoParcial){
				estadoPagoParcial=false;
				NombrePago="PAGO PARCIAL";
			}else if(estadoPagoTotal){
				estadoPagoTotal=false;
				NombrePago="PAGO TOTAL";
			}
			listaReporteReservas.clear();
			reporteReservaDAO.asignarListaReporteReservas(reporteReservaDAO.recuperarReporteReservasBD(fecha1,fecha2,NombrePago));
			setListaReporteReservas(reporteReservaDAO.getListaReporteReservas());
			FechaInicio="";
			FechaFinal="";
		}else{
			Clients.showNotification("Eliga un ESTADO DE PAGO", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
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
