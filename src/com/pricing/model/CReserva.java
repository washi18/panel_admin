package com.pricing.model;

import java.util.Date;

public class CReserva 
{
	private String cReservaCod;// VARCHAR(10),			--codigo de la reserva que se pretende pagar
	private Date dFechaInicio;// date,				--fecha de inicio del tour
	private Date dFechaFin;// date,					--fecha que culmina el tour
	private Date dFecha;// timestamp,				--fecha y hora de realizacion de la reserva
	private String cContacto;// varchar(100),		--Nombre de la persona que realiza la reserva
	private String cEmail;// varchar(100),				--email del pasajero que genera la reserva
	private String cTelefono;// varchar(50),				--telefono del pasajero que genera la reserva
	private Number nPrecioPaquetePersona;// decimal(10,2),            --precio del paquete de acuerdo a la cantidad de personas en la reserva
	private int nNroPersonas;// int,				--numero de personas que integran el tour
	private String cInformacionAdicional;// varchar(300),		--informacion adicional registrada por el cliente
	private String cEstado;// varchar(20),				--estado de una reserva: PENDIENTE DE PAGO,PAGO PARCIAL, PAGO TOTAL
	//============================
	public String getcReservaCod() {
		return cReservaCod;
	}
	public void setcReservaCod(String cReservaCod) {
		this.cReservaCod = cReservaCod;
	}
	public Date getdFecha() {
		return dFecha;
	}
	public void setdFecha(Date dFecha) {
		this.dFecha = dFecha;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public String getcTelefono() {
		return cTelefono;
	}
	public void setcTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}
	public Number getnPrecioPaquetePersona() {
		return nPrecioPaquetePersona;
	}
	public void setnPrecioPaquetePersona(Number nPrecioPaquetePersona) {
		this.nPrecioPaquetePersona = nPrecioPaquetePersona;
	}
	public int getnNroPersonas() {
		return nNroPersonas;
	}
	public void setnNroPersonas(int nNroPersonas) {
		this.nNroPersonas = nNroPersonas;
	}
	public String getcInformacionAdicional() {
		return cInformacionAdicional;
	}
	public void setcInformacionAdicional(String cInformacionAdicional) {
		this.cInformacionAdicional = cInformacionAdicional;
	}
	public String getcEstado() {
		return cEstado;
	}
	public void setcEstado(String cEstado) {
		this.cEstado = cEstado;
	}
	public String getcContacto() {
		return cContacto;
	}
	public void setcContacto(String cContacto) {
		this.cContacto = cContacto;
	}
	public Date getdFechaInicio() {
		return dFechaInicio;
	}
	public void setdFechaInicio(Date dFechaInicio) {
		this.dFechaInicio = dFechaInicio;
	}
	public Date getdFechaFin() {
		return dFechaFin;
	}
	public void setdFechaFin(Date dFechaFin) {
		this.dFechaFin = dFechaFin;
	}
	//=============================
	public CReserva() {
		// TODO Auto-generated constructor stub
		this.cTelefono ="";
		this.cInformacionAdicional ="";
		this.cContacto="";
		this.cEmail="";
	}
	public CReserva(String cReservaCod, Date dFechaInicio, Date dFechaFin,
			Date dFecha, String cContacto, String cEmail, String cTelefono,
			Number nPrecioPaquetePersona, int nNroPersonas,
			String cInformacionAdicional, String cEstado) {
		this.cReservaCod = cReservaCod;
		this.dFechaInicio = dFechaInicio;
		this.dFechaFin = dFechaFin;
		this.dFecha = dFecha;
		this.cContacto = cContacto;
		this.cEmail = cEmail;
		this.cTelefono = cTelefono;
		this.nPrecioPaquetePersona = nPrecioPaquetePersona;
		this.nNroPersonas = nNroPersonas;
		this.cInformacionAdicional = cInformacionAdicional;
		this.cEstado = cEstado;
	}
}
