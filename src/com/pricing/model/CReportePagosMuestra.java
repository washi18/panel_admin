package com.pricing.model;

import java.util.ArrayList;

public class CReportePagosMuestra extends CReportePagos{
	private ArrayList<CPasajero> listaPasajeros;
	//=======getter and setter======

	public ArrayList<CPasajero> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(ArrayList<CPasajero> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}
	//===============constructores=====
	public CReportePagosMuestra()
	{
		super();
	}
	public CReportePagosMuestra(ArrayList<CPasajero> listaPasajeros) {
		super();
		this.listaPasajeros = listaPasajeros;
	}
}
