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
	private int codCategoria;
	private String estado;
	private boolean visibleDestinospop=false;
	private boolean visibleHotelespop=false;
	private boolean visibleServiciospop=false;
	private boolean visibleSubServiciopop=false;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CSubServicio> listasubServicios;
	private Double PrecioTotal;
	private String colornoExisteListaDestinos;
	private String colornoExisteListaHoteles;
	private String colornoExisteListaServicios;
	private String colornoExisteListaSubServicios;
	private ArrayList<CDestinoConHoteles> listaDestinosconHoteles;
	private ArrayList<CServicioConSubServicios> listaServicioConSubServicios;
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
	
	public Number getPrecioPersona() {
		return precioPersona;
	}
	public void setPrecioPersona(Number precioPersona) {
		this.precioPersona = precioPersona;
	}
	
	public int getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	
	public boolean isVisibleDestinospop() {
		return visibleDestinospop;
	}
	public void setVisibleDestinospop(boolean visibleDestinospop) {
		this.visibleDestinospop = visibleDestinospop;
	}
	public boolean isVisibleHotelespop() {
		return visibleHotelespop;
	}
	public void setVisibleHotelespop(boolean visibleHotelespop) {
		this.visibleHotelespop = visibleHotelespop;
	}
	public boolean isVisibleServiciospop() {
		return visibleServiciospop;
	}
	public void setVisibleServiciospop(boolean visibleServiciospop) {
		this.visibleServiciospop = visibleServiciospop;
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
	
	public Double getPrecioTotal() {
		return PrecioTotal;
	}
	public void setPrecioTotal(Double precioTotal) {
		PrecioTotal = precioTotal;
	}
	
	public String getColornoExisteListaDestinos() {
		return colornoExisteListaDestinos;
	}
	public void setColornoExisteListaDestinos(String colornoExisteListaDestinos) {
		this.colornoExisteListaDestinos = colornoExisteListaDestinos;
	}
	public String getColornoExisteListaHoteles() {
		return colornoExisteListaHoteles;
	}
	public void setColornoExisteListaHoteles(String colornoExisteListaHoteles) {
		this.colornoExisteListaHoteles = colornoExisteListaHoteles;
	}
	public String getColornoExisteListaServicios() {
		return colornoExisteListaServicios;
	}
	public void setColornoExisteListaServicios(String colornoExisteListaServicios) {
		this.colornoExisteListaServicios = colornoExisteListaServicios;
	}
	
	public ArrayList<CDestinoConHoteles> getListaDestinosconHoteles() {
		return listaDestinosconHoteles;
	}
	public void setListaDestinosconHoteles(
			ArrayList<CDestinoConHoteles> listaDestinosconHoteles) {
		this.listaDestinosconHoteles = listaDestinosconHoteles;
	}
	
	public ArrayList<CServicioConSubServicios> getListaServicioConSubServicios() {
		return listaServicioConSubServicios;
	}
	public void setListaServicioConSubServicios(
			ArrayList<CServicioConSubServicios> listaServicioConSubServicios) {
		this.listaServicioConSubServicios = listaServicioConSubServicios;
	}
	
	public boolean isVisibleSubServiciopop() {
		return visibleSubServiciopop;
	}
	public void setVisibleSubServiciopop(boolean visibleSubServiciopop) {
		this.visibleSubServiciopop = visibleSubServiciopop;
	}
	public String getColornoExisteListaSubServicios() {
		return colornoExisteListaSubServicios;
	}
	public void setColornoExisteListaSubServicios(
			String colornoExisteListaSubServicios) {
		this.colornoExisteListaSubServicios = colornoExisteListaSubServicios;
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
		this.estado = "";
		this.visibleDestinospop=false;
		this.visibleHotelespop=false;
		this.visibleServiciospop=false;
		this.codCategoria=0;
		this.PrecioTotal=0.0;
	}
	public CReporteReserva(String codReserva, Date fechaInicio, Date fechaFin,
			Date fecha,String nombreContacto, String Email,
			String telefono, int nroPersonas,Number precioPersona,
			String nombrePaquete, String categoria,String estado,int codCategoria,Double precioTotal) {
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
		this.estado = estado;
		this.codCategoria=codCategoria;
		this.PrecioTotal=precioTotal;
		
	}
	//=================otros metodos======================
	
}
