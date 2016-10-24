package com.pricing.model;

import java.util.ArrayList;

public class CDestinoConHoteles 
{
	private CDestino oDestino;
	private ArrayList<CHotel> listaHoteles;
	//=================
	public CDestino getoDestino() {
		return oDestino;
	}

	public void setoDestino(CDestino oDestino) {
		this.oDestino = oDestino;
	}

	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}

	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}
	//=================
	public CDestinoConHoteles() {
		// TODO Auto-generated constructor stub
		this.oDestino=new CDestino();
		this.listaHoteles=new ArrayList<CHotel>();;
	}
	/*
	public CDestinoConHoteles(String destino,
			ArrayList<String[]> listaHotelesDestino) {
		this.destino = destino;
		this.listaHotelesDestino = listaHotelesDestino;
	}
	*/
	public CDestinoConHoteles(CDestino destino, ArrayList<CHotel> listaHoteles)
	{
		this.oDestino = destino;
		this.listaHoteles=listaHoteles;
	}
}
