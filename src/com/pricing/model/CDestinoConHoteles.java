package com.pricing.model;

import java.util.ArrayList;

public class CDestinoConHoteles 
{
	private String destino;
	private ArrayList<String[]> listaHotelesDestino;
	//=================
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public ArrayList<String[]> getListaHotelesDestino() {
		return listaHotelesDestino;
	}
	public void setListaHotelesDestino(ArrayList<String[]> listaHotelesDestino) {
		this.listaHotelesDestino = listaHotelesDestino;
	}
	//=================
	public CDestinoConHoteles() {
		// TODO Auto-generated constructor stub
		listaHotelesDestino=new ArrayList<String[]>();
	}
	public CDestinoConHoteles(String destino,
			ArrayList<String[]> listaHotelesDestino) {
		this.destino = destino;
		this.listaHotelesDestino = listaHotelesDestino;
	}
}
