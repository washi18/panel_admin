package com.pricing.model;

public class CPago {
	//===========atributos========
	private String modoPago;
	private Number monto;
	private long cantidadPagos;
	//===========getter and setter=====
	public String getModoPago() {
		return modoPago;
	}
	public void setModoPago(String modoPago) {
		this.modoPago = modoPago;
	}
	public Number getMonto() {
		return monto;
	}
	public void setMonto(Number monto) {
		this.monto = monto;
	}
	
	public long getCantidadPagos() {
		return cantidadPagos;
	}
	public void setCantidadPagos(long cantidadPagos) {
		this.cantidadPagos = cantidadPagos;
	}
	//=================constructores======
	public CPago(String modoPago, long nroPagos) {
		super();
		this.modoPago = modoPago;
		this.cantidadPagos=nroPagos;
	}
	
}
