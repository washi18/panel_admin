package com.pricing.model;

public class CPaqueteDestino
{
	private int codPaqueteDestino;// int,
	private String cPaqueteCod;// varchar(10),
	private int nDestinoCod;// int,
	//=====================
	public int getCodPaqueteDestino() {
		return codPaqueteDestino;
	}
	public void setCodPaqueteDestino(int codPaqueteDestino) {
		this.codPaqueteDestino = codPaqueteDestino;
	}
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	//=============================
	public CPaqueteDestino() {
		// TODO Auto-generated constructor stub
	}
	public CPaqueteDestino(int codPaqueteDestino, String cPaqueteCod,
			int nDestinoCod) {
		this.codPaqueteDestino = codPaqueteDestino;
		this.cPaqueteCod = cPaqueteCod;
		this.nDestinoCod = nDestinoCod;
	}
	
}
