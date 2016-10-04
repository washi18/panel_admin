package com.pricing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.pricing.model.CReporteReserva;

public class CReporteReservaDAO extends CConexion{
	//====================atributos======================
	private ArrayList<CReporteReserva> listaReporteReservas;
	private CReporteReserva reporteReserva;

	
	//=======================getter and setter==============
	public ArrayList<CReporteReserva> getListaReporteReservas() {
		return listaReporteReservas;
	}
	public void setListaReporteReservas(
			ArrayList<CReporteReserva> listaReporteReservas) {
		this.listaReporteReservas = listaReporteReservas;
	}
	public CReporteReserva getReporteReserva() {
		return reporteReserva;
	}
	public void setReporteReserva(CReporteReserva reporteReserva) {
		this.reporteReserva = reporteReserva;
	}
	//===================contructores====================
	public CReporteReservaDAO() {
		super();
		this.reporteReserva=new CReporteReserva();
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
		this.listaReporteReservas = new ArrayList<CReporteReserva>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaReporteReservas.add(new CReporteReserva((String)row.get("creservacod"),(Date)row.get("dfechainicio"), 
					(Date)row.get("dfechafin"),(Date)row.get("dfecha"),
					(String)row.get("ccontacto"),(String)row.get("cemail"),
					(String)row.get("ctelefono"),(int)row.get("nnropersonas"),
					(Number)row.get("npreciopaquetepersona"),(String) row.get("ctituloidioma1"),
					(String)row.get("ccategoriaidioma1"),(String)row.get("cdestino"),
					(String)row.get("chotel"),(String)row.get("cservicioindioma1"),(String)row.get("csubservicioindioma1"),
					(String)row.get("cestado")));
		}
	}
}
