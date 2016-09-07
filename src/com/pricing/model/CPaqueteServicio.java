package com.pricing.model;

public class CPaqueteServicio 
{
	private String codPaqueteServicio;// varchar(10),
	private String cPaqueteCod;// varchar(10),
	private int nServicioCod;// int,
	//==========================
	public String getCodPaqueteServicio() {
		return codPaqueteServicio;
	}
	public void setCodPaqueteServicio(String codPaqueteServicio) {
		this.codPaqueteServicio = codPaqueteServicio;
	}
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public int getnServicioCod() {
		return nServicioCod;
	}
	public void setnServicioCod(int nServicioCod) {
		this.nServicioCod = nServicioCod;
	}
	//========================================
	public CPaqueteServicio() {
		// TODO Auto-generated constructor stub
	}
	public CPaqueteServicio(String codPaqueteServicio, String cPaqueteCod,
			int nServicioCod) {
		this.codPaqueteServicio = codPaqueteServicio;
		this.cPaqueteCod = cPaqueteCod;
		this.nServicioCod = nServicioCod;
	}
	
}
