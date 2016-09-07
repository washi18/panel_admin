package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CHotel;

public class CHotelDAO extends CConexion
{
	private CHotel oHotel;
	private ArrayList<CHotel> listaHoteles;
	//============================
	public CHotel getoHotel() {
		return oHotel;
	}
	public void setoHotel(CHotel oHotel) {
		this.oHotel = oHotel;
	}
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}
	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}
	//=================================
	public CHotelDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oHotel=new CHotel();
	}
	public CHotelDAO(CHotel hotel)
	{
		super();
		this.oHotel=hotel;
	}
	//==================================
	public List recuperarHotelBD(int codHotel)
	{
		Object[] value={codHotel};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarHotel",value);
	}
	public void asignarHotel(List lista)
	{
		Map row=(Map) lista.get(0);
		oHotel=new CHotel((int)row.get("nhotelcod"),(String)row.get("chotel"), 
				(String)row.get("cdescripcionidioma1"),(String)row.get("cdescripcionidioma2"),
				(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
				(String)row.get("cdescripcionidioma5"),(String)row.get("curl"),
				(int)row.get("categoriahotelcod"),(Number) row.get("npreciosimple"),
				(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),
				(boolean)row.get("bestado")); 
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
