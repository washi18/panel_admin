package com.pricing.model;

import java.util.ArrayList;
import java.util.Date;

public class CReporteReserva {
	//==============atributos============
	private String CodReserva;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fecha;
	private String NombreContacto;
	private String Email;
	private String telefono;
	private int nroPersonas;
	private Number precioPersona;
	private String nombrePaquete;
	private String categoria;
	private String destinos;
	private String hoteles;
	private String servicios;
	private String subServicios;
	private String estado;
	//=======getter and setter===========
	public String getCodReserva() {
		return CodReserva;
	}
	public void setCodReserva(String codReserva) {
		CodReserva = codReserva;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreContacto() {
		return NombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		NombreContacto = nombreContacto;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getNroPersonas() {
		return nroPersonas;
	}
	public void setNroPersonas(int nroPersonas) {
		this.nroPersonas = nroPersonas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombrePaquete() {
		return nombrePaquete;
	}
	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDestinos() {
		return destinos;
	}
	public void setDestinos(String destinos) {
		this.destinos = destinos;
	}
	public String getHoteles() {
		return hoteles;
	}
	public void setHoteles(String hoteles) {
		this.hoteles = hoteles;
	}
	public String getServicios() {
		return servicios;
	}
	public void setServicios(String servicios) {
		this.servicios = servicios;
	}
	public String getSubServicios() {
		return subServicios;
	}
	public void setSubServicios(String subServicios) {
		this.subServicios = subServicios;
	}
	
	public Number getPrecioPersona() {
		return precioPersona;
	}
	public void setPrecioPersona(Number precioPersona) {
		this.precioPersona = precioPersona;
	}
	//=============metodos constructores===============
	public CReporteReserva()
	{
		this.CodReserva = "";
		this.NombreContacto = "";
		this.Email = "";
		this.telefono = "";
		this.nroPersonas = 0;
		this.precioPersona=0.0;
		this.nombrePaquete = "";
		this.categoria = "";
		this.destinos = "";
		this.hoteles = "";
		this.servicios = "";
		this.subServicios = "";
		this.estado = "";
	}
	public CReporteReserva(String codReserva, Date fechaInicio, Date fechaFin,
			Date fecha, String nombreContacto, String Email,
			String telefono, int nroPersonas,Number precioPersona,
			String nombrePaquete, String categoria, String destinos,
			String hoteles, String servicios,
			String subServicios,String estado) {
		super();
		this.CodReserva = codReserva;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fecha = fecha;
		this.NombreContacto = nombreContacto;
		this.Email = Email;
		this.telefono = telefono;
		this.nroPersonas = nroPersonas;
		this.precioPersona=precioPersona;
		this.nombrePaquete = nombrePaquete;
		this.categoria = categoria;
		this.destinos = destinos;
		this.hoteles = hoteles;
		this.servicios = servicios;
		this.subServicios = subServicios;
		this.estado = estado;
		
	}
	//=================otros metodos======================
	
}
