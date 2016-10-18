package com.pricing.model;

import java.util.ArrayList;

public class CDestinoConHoteles 
{
	private String destino;
	private ArrayList<String[]> listaHotelesDestino;
	private ArrayList<String> listaDestinoHoteles;
	private ArrayList<CHotel> listaDestinosHoteles;
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
	
	public ArrayList<String> getListaDestinoHoteles() {
		return listaDestinoHoteles;
	}
	public void setListaDestinoHoteles(ArrayList<String> listaDestinoHoteles) {
		this.listaDestinoHoteles = listaDestinoHoteles;
	}
	
	public ArrayList<CHotel> getListaDestinosHoteles() {
		return listaDestinosHoteles;
	}
	public void setListaDestinosHoteles(ArrayList<CHotel> listaDestinosHoteles) {
		this.listaDestinosHoteles = listaDestinosHoteles;
	}
	//=================
	public CDestinoConHoteles() {
		// TODO Auto-generated constructor stub
		listaHotelesDestino=new ArrayList<String[]>();
	}
	/*
	public CDestinoConHoteles(String destino,
			ArrayList<String[]> listaHotelesDestino) {
		this.destino = destino;
		this.listaHotelesDestino = listaHotelesDestino;
	}
	*/
	
	public CDestinoConHoteles(String destino, ArrayList<CHotel> listaDestinoHoteles)
	{
		this.destino = destino;
		this.listaDestinosHoteles=listaDestinoHoteles;
	}
}
