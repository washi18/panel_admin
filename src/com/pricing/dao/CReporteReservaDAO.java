package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CHotel;
import com.pricing.model.CReporteReserva;

public class CReporteReservaDAO extends CConexion{
	//====================atributos======================
	private ArrayList<CReporteReserva> listaReporteReservas;

	public ArrayList<CReporteReserva> getListaReporteReservas() {
		return listaReporteReservas;
	}
	//=======================getter and setter==============
	public void setListaReporteReservas(
			ArrayList<CReporteReserva> listaReporteReservas) {
		this.listaReporteReservas = listaReporteReservas;
	}
	//===================contructores====================
	public CReporteReservaDAO(ArrayList<CReporteReserva> listaReporteReservas) {
		super();
		this.listaReporteReservas = listaReporteReservas;
	}
	//=====================otros metodos=========================
	public List recuperarReporteReservasBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("");
	}
	
	public void asignarListaReporteReservas(List lista)
	{
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
		}
	}
}
