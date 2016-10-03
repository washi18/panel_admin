package com.pricing.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
	public CReporteReservaDAO() {
		super();
		this.listaReporteReservas = new ArrayList<CReporteReserva>();
	}
	public CReporteReservaDAO(ArrayList<CReporteReserva> listaReporteReservas) {
		super();
		this.listaReporteReservas = listaReporteReservas;
	}
	//=====================otros metodos=========================
	public List recuperarReporteReservasBD(String FechaIni,String FechaFin,String EstadoPago)
	{
		String[] values={FechaIni,FechaFin,EstadoPago};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_buscarreservas",values);
	}
	
	public void asignarListaReporteReservas(List lista)
	{
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaReporteReservas.add(new CReporteReserva((String)row.get("CodReserva"),(Date)row.get("inicio"), 
					(Date)row.get("fin"),(Timestamp)row.get("fecha"),
					(String)row.get("contacto"),(String)row.get("email"),
					(String)row.get("telefono"),(int)row.get("nropersonas"),
					(Number)row.get("preciopersona"),(String) row.get("nombrePaquete"),
					(String)row.get("categoria"),(String)row.get("destinos"),
					(String)row.get("hoteles"),(String)row.get("servicios"),(String)row.get("subservicios"),
					(int)row.get("dias"),(int)row.get("noches"),
					(String)row.get("estado")));
		}
	}
}
