package com.pricing.model;

public class CReservaPaqueteServicio 
{
	private long codReservaPServicio;//bigint,
	private String codPaqueteServicio;// varchar(10),
	private String cReservaCod;// varchar(10),
	private double nroPrestacionServicio;// int,
	private Number precioPrestacionServicio;// decimal(10,2),
	//===========================
	public String getCodPaqueteServicio() {
		return codPaqueteServicio;
	}
	public void setCodPaqueteServicio(String codPaqueteServicio) {
		this.codPaqueteServicio = codPaqueteServicio;
	}
	public String getcReservaCod() {
		return cReservaCod;
	}
	public void setcReservaCod(String cReservaCod) {
		this.cReservaCod = cReservaCod;
	}
	public Number getPrecioPrestacionServicio() {
		return precioPrestacionServicio;
	}
	public void setPrecioPrestacionServicio(Number precioPrestacionServicio) {
		this.precioPrestacionServicio = precioPrestacionServicio;
	}
	public long getCodReservaPServicio() {
		return codReservaPServicio;
	}
	public void setCodReservaPServicio(long codReservaPServicio) {
		this.codReservaPServicio = codReservaPServicio;
	}
	public double getNroPrestacionServicio() {
		return nroPrestacionServicio;
	}
	public void setNroPrestacionServicio(double nroPrestacionServicio) {
		this.nroPrestacionServicio = nroPrestacionServicio;
	}
	//===================================
	public CReservaPaqueteServicio() {
		// TODO Auto-generated constructor stub
	}
	public CReservaPaqueteServicio(long codReservaPServicio,
			String codPaqueteServicio, String cReservaCod,
			double nroPrestacionServicio, Number precioPrestacionServicio) {
		this.codReservaPServicio = codReservaPServicio;
		this.codPaqueteServicio = codPaqueteServicio;
		this.cReservaCod = cReservaCod;
		this.nroPrestacionServicio = nroPrestacionServicio;
		this.precioPrestacionServicio = precioPrestacionServicio;
	}
	
}
