package com.pricing.viewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import com.pricing.dao.CReservaDAO;
import com.pricing.model.CReserva;

public class ReservasVM {
	//====atributos=====
	private ArrayList<CReserva> listaReservas;
	private CReservaDAO reservaDao;
	private boolean estadoPagoPendiente;
	private boolean estadoPagoParcial;
	private boolean estadoPagoTotal;
	private String FechaInicio;
	private String FechaFinal;
	private SimpleDateFormat formatoTexto;
	private Date fechaStart;
	private Date fechaFin;
	//=====getter and setterr=====

	public ArrayList<CReserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<CReserva> listaReservas) {
		this.listaReservas = listaReservas;
	}
	
	public CReservaDAO getReservaDao() {
		return reservaDao;
	}

	public void setReservaDao(CReservaDAO reservaDao) {
		this.reservaDao = reservaDao;
	}

	public boolean isEstadoPagoPendiente() {
		return estadoPagoPendiente;
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

	public SimpleDateFormat getFormatoTexto() {
		return formatoTexto;
	}

	public void setFormatoTexto(SimpleDateFormat formatoTexto) {
		this.formatoTexto = formatoTexto;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public Date getFechaStart() {
		return fechaStart;
	}

	public void setFechaStart(Date fechaStart) {
		this.fechaStart = fechaStart;
	}

	//=====metodos=========
	@Init
	public void initVM()
	{
		/*******************************/
		/*******************************/
		/**Inicializando los objetos**/
		listaReservas=new ArrayList<CReserva>();
		reservaDao=new CReservaDAO();
		formatoTexto=new SimpleDateFormat("yyy-MM-dd");
		fechaStart=null;
		fechaFin=null;
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
	}
	
	@Command
	@NotifyChange({"FechaInicio","FechaFinal","ëstadoPagoPendiente","estadoPagoParcial","estadoPagoTotal"})
	public void Buscar_Reservas() throws ParseException
	{
		fechaStart=formatoTexto.parse(FechaInicio);
		fechaFin=formatoTexto.parse(FechaFinal);
		String NombrePago="";
		if(estadoPagoPendiente)
		{
			NombrePago="PENDIENTE DE PAGO";
		}else if(estadoPagoParcial){
			NombrePago="PAGO PARCIAL";
		}else if(estadoPagoTotal){
			NombrePago="PAGO TOTAL";
		}
		reservaDao.asignarListaReservas(reservaDao.recuperarTodasReservasBD(fechaStart,fechaFin,NombrePago));
		setListaReservas(reservaDao.getListaReservas());
	}
}
