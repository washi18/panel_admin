package com.pricing.model;

import java.util.ArrayList;

public class CReportePagosMuestra extends CReportePagos{
	private boolean visiblepasajerospop=false;
	private Double montoTotal;
	private Double valorImpuesto;
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
	
	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	
	public Double getValorImpuesto() {
		return valorImpuesto;
	}

	public void setValorImpuesto(Double valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

	//===============constructores=====
	public CReportePagosMuestra()
	{
		super();
		visiblepasajerospop=false;
	}
	public CReportePagosMuestra(ArrayList<CPasajero> listaPasajeros) {
		super();
		this.listaPasajeros = listaPasajeros;
	}
}
