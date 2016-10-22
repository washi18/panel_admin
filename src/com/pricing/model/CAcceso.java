package com.pricing.model;

public class CAcceso 
{
	private int nAccesoCod;// int,
	private int nPerfilCod;// int,
	private boolean accesoUpdateDisdo;// boolean,
	private boolean accesoEtiqueta;// boolean,
	private boolean accesoImpuesto;// boolean,
	private boolean accesoCodVisa;// boolean,
	private boolean accesoRegUsuarios;// boolean,
	private boolean accesoCrearNuevoUser;// boolean,
	private boolean accesoCategorias;// boolean,
	private boolean accesoPaquetes;// boolean,
	private boolean accesoServicios;// boolean,
	private boolean accesoSubServicios;// boolean,
	private boolean accesoHoteles;// boolean,
	private boolean accesoDestinos;// boolean,
	private boolean accesoReporReservas;// boolean,
	private boolean accesoReporPagos;// boolean,
	/******************/
	public int getnAccesoCod() {
		return nAccesoCod;
	}
	public void setnAccesoCod(int nAccesoCod) {
		this.nAccesoCod = nAccesoCod;
	}
	public int getnPerfilCod() {
		return nPerfilCod;
	}
	public void setnPerfilCod(int nPerfilCod) {
		this.nPerfilCod = nPerfilCod;
	}
	public boolean isAccesoUpdateDisdo() {
		return accesoUpdateDisdo;
	}
	public void setAccesoUpdateDisdo(boolean accesoUpdateDisdo) {
		this.accesoUpdateDisdo = accesoUpdateDisdo;
	}
	public boolean isAccesoEtiqueta() {
		return accesoEtiqueta;
	}
	public void setAccesoEtiqueta(boolean accesoEtiqueta) {
		this.accesoEtiqueta = accesoEtiqueta;
	}
	public boolean isAccesoImpuesto() {
		return accesoImpuesto;
	}
	public void setAccesoImpuesto(boolean accesoImpuesto) {
		this.accesoImpuesto = accesoImpuesto;
	}
	public boolean isAccesoCodVisa() {
		return accesoCodVisa;
	}
	public void setAccesoCodVisa(boolean accesoCodVisa) {
		this.accesoCodVisa = accesoCodVisa;
	}
	public boolean isAccesoRegUsuarios() {
		return accesoRegUsuarios;
	}
	public void setAccesoRegUsuarios(boolean accesoRegUsuarios) {
		this.accesoRegUsuarios = accesoRegUsuarios;
	}
	public boolean isAccesoCrearNuevoUser() {
		return accesoCrearNuevoUser;
	}
	public void setAccesoCrearNuevoUser(boolean accesoCrearNuevoUser) {
		this.accesoCrearNuevoUser = accesoCrearNuevoUser;
	}
	public boolean isAccesoCategorias() {
		return accesoCategorias;
	}
	public void setAccesoCategorias(boolean accesoCategorias) {
		this.accesoCategorias = accesoCategorias;
	}
	public boolean isAccesoPaquetes() {
		return accesoPaquetes;
	}
	public void setAccesoPaquetes(boolean accesoPaquetes) {
		this.accesoPaquetes = accesoPaquetes;
	}
	public boolean isAccesoServicios() {
		return accesoServicios;
	}
	public void setAccesoServicios(boolean accesoServicios) {
		this.accesoServicios = accesoServicios;
	}
	public boolean isAccesoSubServicios() {
		return accesoSubServicios;
	}
	public void setAccesoSubServicios(boolean accesoSubServicios) {
		this.accesoSubServicios = accesoSubServicios;
	}
	public boolean isAccesoHoteles() {
		return accesoHoteles;
	}
	public void setAccesoHoteles(boolean accesoHoteles) {
		this.accesoHoteles = accesoHoteles;
	}
	public boolean isAccesoDestinos() {
		return accesoDestinos;
	}
	public void setAccesoDestinos(boolean accesoDestinos) {
		this.accesoDestinos = accesoDestinos;
	}
	public boolean isAccesoReporReservas() {
		return accesoReporReservas;
	}
	public void setAccesoReporReservas(boolean accesoReporReservas) {
		this.accesoReporReservas = accesoReporReservas;
	}
	public boolean isAccesoReporPagos() {
		return accesoReporPagos;
	}
	public void setAccesoReporPagos(boolean accesoReporPagos) {
		this.accesoReporPagos = accesoReporPagos;
	}
	/**********************/
	public CAcceso() {
		// TODO Auto-generated constructor stub
	}
	public CAcceso(int nAccesoCod, int nPerfilCod, boolean accesoUpdateDisdo,
			boolean accesoEtiqueta, boolean accesoImpuesto,
			boolean accesoCodVisa, boolean accesoRegUsuarios,
			boolean accesoCrearNuevoUser, boolean accesoCategorias,
			boolean accesoPaquetes, boolean accesoServicios,
			boolean accesoSubServicios, boolean accesoHoteles,
			boolean accesoDestinos, boolean accesoReporReservas,
			boolean accesoReporPagos) {
		this.nAccesoCod = nAccesoCod;
		this.nPerfilCod = nPerfilCod;
		this.accesoUpdateDisdo = accesoUpdateDisdo;
		this.accesoEtiqueta = accesoEtiqueta;
		this.accesoImpuesto = accesoImpuesto;
		this.accesoCodVisa = accesoCodVisa;
		this.accesoRegUsuarios = accesoRegUsuarios;
		this.accesoCrearNuevoUser = accesoCrearNuevoUser;
		this.accesoCategorias = accesoCategorias;
		this.accesoPaquetes = accesoPaquetes;
		this.accesoServicios = accesoServicios;
		this.accesoSubServicios = accesoSubServicios;
		this.accesoHoteles = accesoHoteles;
		this.accesoDestinos = accesoDestinos;
		this.accesoReporReservas = accesoReporReservas;
		this.accesoReporPagos = accesoReporPagos;
	}
	
}
