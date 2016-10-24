package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CDestino;
import com.pricing.model.CHotel;

public class CDestinoDAO extends CConexion
{
	private CDestino oDestino;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHotelesDestino;
	//==============================
	public CDestino getoDestino() {
		return oDestino;
	}
	public void setoDestino(CDestino oDestino) {
		this.oDestino = oDestino;
	}
	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}
	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}
	public ArrayList<CHotel> getListaHotelesDestino() {
		return listaHotelesDestino;
	}
	public void setListaHotelesDestino(ArrayList<CHotel> listaHotelesDestino) {
		this.listaHotelesDestino = listaHotelesDestino;
	}
	//=====================================
	public CDestinoDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oDestino=new CDestino();
	}
	public CDestinoDAO(CDestino destino)
	{
		super();
		this.oDestino=destino;
	}
	//===================================
	public List recuperarListaDestinosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarDestinos");
	}
	public List recuperarListaTodosDestinosBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarTodosDestinos");
	}
	public List recuperarListaHotelesDestino(int codDestino)
	{
		Object[] values={codDestino};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarHotelesDestino", values);
	}
	public List recuperarDestinoBD(int codDestino)
	{
		Object[] values={codDestino};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarDestino", values);
	}
	public void asignarDestino(List lista)
	{
		Map row=(Map)lista.get(0);
		oDestino=new CDestino((int)row.get("ndestinocod"),
				(String)row.get("cdestino"),(boolean)row.get("bestado"),
				(int)row.get("ncodpostal"));
	}
	public void asignarListaHotelesPorDestino(List lista)
	{
		listaHotelesDestino=new ArrayList<CHotel>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaHotelesDestino.add(new CHotel((int)row.get("nhotelcod"),(String)row.get("chotel"), 
					(String)row.get("cdescripcionidioma1"),(String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"),(String)row.get("cdescripcionidioma4"),
					(String)row.get("cdescripcionidioma5"),(String)row.get("curl"),
					(int)row.get("categoriahotelcod"),(Number) row.get("npreciosimple"),
					(Number)row.get("npreciodoble"),(Number)row.get("npreciotriple"),
					(boolean)row.get("bestado"),(int)row.get("ndestinocod")));
		}
	}
	public void asignarListaDestinos(List lista)
	{
		listaDestinos=new ArrayList<CDestino>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaDestinos.add(new CDestino((int)row.get("ndestinocod"),
					(String)row.get("cdestino"),(boolean)row.get("bestado"),
					(int)row.get("ncodpostal")));
		}
	}
	public List insertarDestino(CDestino destino)
	{
		Object[] values={destino.getcDestino(),destino.getnCodPostal()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistrarDestino", values);
	}
	public List modificarDestino(CDestino destino)
	{
		Object[] values={destino.getnDestinoCod(),destino.getcDestino(),destino.isbEstado(),destino.getnCodPostal()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarDestino", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
	
}
