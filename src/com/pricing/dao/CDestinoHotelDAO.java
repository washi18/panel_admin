package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CDestinoHotel;

public class CDestinoHotelDAO extends CConexion
{
	private CDestinoHotel oDestinoHotel;
	private ArrayList<CDestinoHotel> listaDestinoHoteles;
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
}
