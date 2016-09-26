package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CDestinoHotel;
import com.pricing.model.CHotel;

public class CDestinoHotelDAO extends CConexion
{
	private CDestinoHotel oDestinoHotel;
	private ArrayList<CDestinoHotel> listaDestinoHoteles;
	private ArrayList<CHotel> listaHoteles;
	//================================0
	public CDestinoHotel getoDestinoHotel() {
		return oDestinoHotel;
	}
	public void setoDestinoHotel(CDestinoHotel oDestinoHotel) {
		this.oDestinoHotel = oDestinoHotel;
	}
	public ArrayList<CDestinoHotel> getListaDestinoHoteles() {
		return listaDestinoHoteles;
	}
	public void setListaDestinoHoteles(ArrayList<CDestinoHotel> listaDestinoHoteles) {
		this.listaDestinoHoteles = listaDestinoHoteles;
	}
	
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}
	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}
	//================================
	public CDestinoHotelDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oDestinoHotel=new CDestinoHotel();
	}
	public CDestinoHotelDAO(CDestinoHotel destinoH)
	{
		super();
		this.oDestinoHotel=destinoH;
	}
	//=================================
	public List recuperarDestinoHotelesBD(int codDestino)
	{
		Object[] value={codDestino};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarDestinoHoteles", value);
	}
	
	public List recuperarListaHotelesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosHoteles");
	}
	public void asignarListaDestinoHoteles(List lista)
	{
		listaDestinoHoteles=new ArrayList<CDestinoHotel>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinoHoteles.add(new CDestinoHotel((String)row.get("destinohotelcod"),
					(int)row.get("ndestinocod"),(int)row.get("nhotelcod")));
		}
	}
	public void asignarListaHoteles(List lista)
	{
		listaHoteles=new ArrayList<CHotel>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaHoteles.add(new CHotel((int)row.get("nhotelcod"),(String)row.get("chotel"), 
					(String)row.get("cdescripcionidioma1"),(String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
					(String)row.get("cdescripcionidioma5"),(String)row.get("curl"),
					(int)row.get("categoriahotelcod"),(Number) row.get("npreciosimple"),
					(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),
					(boolean)row.get("bestado")));
		}
	}
	
}
