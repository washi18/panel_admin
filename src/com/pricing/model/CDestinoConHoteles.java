package com.pricing.model;

import java.util.ArrayList;

public class CDestinoConHoteles 
{
	private CDestino oDestino;
	private ArrayList<CHotel> listaDestinosHoteles;
	//=================
	
	public ArrayList<CHotel> getListaDestinosHoteles() {
		return listaDestinosHoteles;
	}
	
	public CDestino getoDestino() {
		return oDestino;
	}

	public void setoDestino(CDestino oDestino) {
		this.oDestino = oDestino;
	}

	public void setListaDestinosHoteles(ArrayList<CHotel> listaDestinosHoteles) {
		this.listaDestinosHoteles = listaDestinosHoteles;
	}
	//=================
	public CDestinoConHoteles() {
		// TODO Auto-generated constructor stub
		this.oDestino=null;
		this.listaDestinosHoteles=null;
	}
	/*
	public CDestinoConHoteles(String destino,
			ArrayList<String[]> listaHotelesDestino) {
		this.destino = destino;
		this.listaHotelesDestino = listaHotelesDestino;
	}
	*/
	
	public CDestinoConHoteles(CDestino destino, ArrayList<CHotel> listaDestinoHoteles)
	{
		this.oDestino = destino;
		this.listaDestinosHoteles=listaDestinoHoteles;
	}
}
