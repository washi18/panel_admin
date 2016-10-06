package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CDestino;

public class CDestinoDAO extends CConexion
{
	private CDestino oDestino;
	private ArrayList<CDestino> listaDestinos;
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
	//Antes de registrar hay que generar el codigo del destino
	public List generarCodDestino()
	{
		return getEjecutorSQL().ejecutarProcedimiento("");
	}
	public int NuevoCodDestino(List lista)
	{
		int cod=-1;
		Map row=(Map)lista.get(0);
		boolean seGeneroCorrectamente=row.get("resultado").toString().equals("correcto");
		if(seGeneroCorrectamente)
			cod=(int)row.get("codigo");
		return cod;
	}
//	public List registrarDestinoBD(CDestino destino)
//	{
//		Object value={destino.};
//	}
}
