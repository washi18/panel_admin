package com.pricing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pricing.model.CDestino;
import com.pricing.model.CEstadistica_Paquete;
import com.pricing.model.CHotel;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class CReporteReservaDAO extends CConexion{
	//====================atributos======================
	private ArrayList<CReporteReserva> listaReporteReservas;
	private ArrayList<CDestino> listaDestinosReserva;
	private ArrayList<CHotel> listaHotelesReserva;
	private ArrayList<CServicio> listaServiciosReserva;
	private ArrayList<CSubServicio> listaSubServiciosReserva;
	private CReporteReserva reporteReserva;
	private ArrayList<CEstadistica_Paquete> masVendidosxMeses;

	
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
	
	public ArrayList<CDestino> getListaDestinosReserva() {
		return listaDestinosReserva;
	}
	public void setListaDestinosReserva(ArrayList<CDestino> listaDestinosReserva) {
		this.listaDestinosReserva = listaDestinosReserva;
	}
	public ArrayList<CHotel> getListaHotelesReserva() {
		return listaHotelesReserva;
	}
	public void setListaHotelesReserva(ArrayList<CHotel> listaHotelesReserva) {
		this.listaHotelesReserva = listaHotelesReserva;
	}
	public ArrayList<CServicio> getListaServiciosReserva() {
		return listaServiciosReserva;
	}
	public void setListaServiciosReserva(ArrayList<CServicio> listaServiciosReserva) {
		this.listaServiciosReserva = listaServiciosReserva;
	}
	
	public ArrayList<CEstadistica_Paquete> getMasVendidosxMeses() {
		return masVendidosxMeses;
	}
	public void setMasVendidosxMeses(
			ArrayList<CEstadistica_Paquete> masVendidosxMeses) {
		this.masVendidosxMeses = masVendidosxMeses;
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
	
	public ArrayList<CSubServicio> getListaSubServiciosReserva() {
		return listaSubServiciosReserva;
	}
	public void setListaSubServiciosReserva(
			ArrayList<CSubServicio> listaSubServiciosReserva) {
		this.listaSubServiciosReserva = listaSubServiciosReserva;
	}
	//=====================otros metodos=========================
	public List recuperarReporteReservasBD(String FechaIni,String FechaFin)
	{
		String[] values={FechaIni,FechaFin};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_buscarreservas",values);
	}
	
	public List recuperarDestinosReservaBD(String codReserva)
	{
		String[] values={codReserva};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_buscardestinosreserva",values);
	}
	
	public List recuperarHotelesReservaBD(String codReserva,int codCategoriaHotel)
	{
		Object[] values={codReserva,codCategoriaHotel};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_buscarhotelesreserva",values);
	}
	
	public List recuperarServiciosReservaBD(String codReserva)
	{
		String[] values={codReserva};
		return getEjecutorSQL().ejecutarProcedimiento("pricing_sp_buscarserviciosreserva",values);
	}
	
	public List recuperarPaquetesMasVendidos(String fecha)
	{
		String[] values={fecha};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarPaquetesMasVendidos",values);
	}
	
	public List recuperarSubServiciosReservaBD(String codReserva)
	{
		String[] values={codReserva};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_buscarsubserviciosreserva",values);
	}
	public void asignarListaReporteReservas(List lista)
	{
		System.out.println("entro aqui DAO");
		this.listaReporteReservas = new ArrayList<CReporteReserva>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			Number a=(Number)row.get("npreciopaquetepersona");
			double preciopersona=a.doubleValue();
			int n=(int)row.get("nnropersonas");
			double total=n*preciopersona;
			listaReporteReservas.add(new CReporteReserva((String)row.get("creservacod"),(Date)row.get("dfechainicio"), 
					(Date)row.get("dfechafin"),(Date)row.get("dfecha"),
					(String)row.get("ccontacto"),(String)row.get("cemail"),
					(String)row.get("ctelefono"),(int)row.get("nnropersonas"),
					(Number)row.get("npreciopaquetepersona"),(String) row.get("ctituloidioma1"),
					(String)row.get("ccategoriaidioma1"),
					(String)row.get("cestado"),(int)row.get("categoriahotelcod"),total));
		}
		System.out.println("termino aqui DAO");
	}
	public void asignarDestinosReserva(List lista)
	{
		System.out.println("entro aqui DAO 2");
		listaDestinosReserva=new ArrayList<CDestino>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinosReserva.add(new CDestino((String)row.get("cdestino"),(int)row.get("ndestinocod"),(int)row.get("ncodpostal")));
		}
		System.out.println("termino aqui DAO 2");
	}
	public void asignarServiciosReserva(List lista)
	{
		listaServiciosReserva=new ArrayList<CServicio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaServiciosReserva.add(new CServicio((String)row.get("cservicioindioma1"),(Number)row.get("nprecioservicio")));
		}
	}
	public void asignarHotelesReserva(List lista)
	{
		listaHotelesReserva=new ArrayList<CHotel>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaHotelesReserva.add(new CHotel((String)row.get("chotel"),(Number)row.get("npreciosimple"),(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),(String)row.get("cdestino")));
		}
	}
	public void asignarSubServiciosReserva(List lista)
	{
		listaSubServiciosReserva=new ArrayList<CSubServicio>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaSubServiciosReserva.add(new CSubServicio((String)row.get("csubservicioindioma1"),(Number)row.get("nprecioservicio"),(String)row.get("cservicioindioma1")));
		}
	}
	
	public void asignarPaquetesmasVendidos(List lista)
	{
		System.out.println("entra aqui 1");
		masVendidosxMeses=new ArrayList<CEstadistica_Paquete>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			masVendidosxMeses.add(new CEstadistica_Paquete((String)row.get("ctituloidioma1"),(long)row.get("nrovendidos"),(Date)row.get("fecha")));
		}
		System.out.println("entra aqui 2");
	}
}
