package com.pricing.model;

import java.util.ArrayList;

public class CReportePagosMuestra extends CReportePagos{
	private boolean visiblepasajerospop=false;
	private String idPasajeropop;
	private ArrayList<CPasajero> listaPasajeros;
	//=======getter and setter======

	public ArrayList<CPasajero> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(ArrayList<CPasajero> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}
	
	public boolean isVisiblepasajerospop() {
		return visiblepasajerospop;
	}

	public void setVisiblepasajerospop(boolean visiblepasajerospop) {
		this.visiblepasajerospop = visiblepasajerospop;
	}

	public String getIdPasajeropop() {
		return idPasajeropop;
	}

	public void setIdPasajeropop(String idPasajeropop) {
		this.idPasajeropop = idPasajeropop;
	}

	//===============constructores=====
	public CReportePagosMuestra()
	{
		super();
		idPasajeropop="";
		visiblepasajerospop=false;
	}
	public CReportePagosMuestra(ArrayList<CPasajero> listaPasajeros) {
		super();
		this.listaPasajeros = listaPasajeros;
	}
}
