package com.pricing.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class CReporteReserva {
	//==============atributos============
	private String CodReserva;
	private Date fechaInicio;
	private Date fechaFin;
	private Timestamp fecha;
	private String NombreContacto;
	private String Email;
	private String telefono;
	private int nroPersonas;
	private double precioPersona;
	private String estado;
	private String nombrePaquete;
	private String categoria;
	private String destinos;
	private String hoteles;
	private int nroDias;
	private int nroNoches;
	private String servicios;
	private String subServicios;
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
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
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
	public int getNroDias() {
		return nroDias;
	}
	public void setNroDias(int nroDias) {
		this.nroDias = nroDias;
	}
	public int getNroNoches() {
		return nroNoches;
	}
	public void setNroNoches(int nroNoches) {
		this.nroNoches = nroNoches;
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
	
	public double getPrecioPersona() {
		return precioPersona;
	}
	public void setPrecioPersona(double precioPersona) {
		this.precioPersona = precioPersona;
	}
	//=============metodos constructores===============
	public CReporteReserva(String codReserva, Date fechaInicio, Date fechaFin,
			Timestamp fecha, String nombreContacto, String email,
			String telefono, int nroPersonas,double precioPersona, String estado,
			String nombrePaquete, String categoria, String destinos,
			String hoteles, int nroDias, int nroNoches, String servicios,
			String subServicios) {
		super();
		CodReserva = codReserva;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fecha = fecha;
		NombreContacto = nombreContacto;
		Email = email;
		this.telefono = telefono;
		this.nroPersonas = nroPersonas;
		this.precioPersona=precioPersona;
		this.estado = estado;
		this.nombrePaquete = nombrePaquete;
		this.categoria = categoria;
		this.destinos = destinos;
		this.hoteles = hoteles;
		this.nroDias = nroDias;
		this.nroNoches = nroNoches;
		this.servicios = servicios;
		this.subServicios = subServicios;
		
	}
	//=================otros metodos======================
	
}
