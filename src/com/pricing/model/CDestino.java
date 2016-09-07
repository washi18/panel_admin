package com.pricing.model;

public class CDestino 
{
	private int nDestinoCod; //int,				--codigo del destino
	private String cDestino;// varchar(100),				--descripcion del destino
	private boolean bEstado;// boolean,
	//======================
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	public String getcDestino() {
		return cDestino;
	}
	public void setcDestino(String cDestino) {
		this.cDestino = cDestino;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	//============================
	public CDestino() {
		// TODO Auto-generated constructor stub
	}
	public CDestino(int nDestinoCod, String cDestino, boolean bEstado) {
		this.nDestinoCod = nDestinoCod;
		this.cDestino = cDestino;
		this.bEstado = bEstado;
	}
	
}