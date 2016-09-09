package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CEtiqueta;
import com.pricing.model.CPaquete;

public class CPaqueteDAO extends CConexion 
{
	private CPaquete oPaquete;
	private ArrayList<CPaquete> listaPaquetes;
	//======================
	public CPaquete getoPaquete() {
		return oPaquete;
	}
	public void setoPaquete(CPaquete oPaquete) {
		this.oPaquete = oPaquete;
	}
	public ArrayList<CPaquete> getListaPaquetes() {
		return listaPaquetes;
	}
	public void setListaPaquetes(ArrayList<CPaquete> listaPaquetes) {
		this.listaPaquetes = listaPaquetes;
	}
	//=========================
	public CPaqueteDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oPaquete=new CPaquete();
	}
	public CPaqueteDAO(CPaquete paquete)
	{
		super();
		this.oPaquete=paquete;
	}
	//===========================
	public List recuperarPaqueteBD(String codigoPaquete)
	{
		Object[] value={codigoPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaquete", value);
	}
	public List recuperarPaquetesBD()
	{
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarPaquetes");
	}
	public void asignarPaquete(List lista)
	{
		Map row=(Map)lista.get(0);
		oPaquete=new CPaquete((String)row.get("cpaquetecod"),(String)row.get("ctituloidioma1"),
				(String)row.get("ctituloidioma2"),(String)row.get("ctituloidioma3"),
				(String)row.get("ctituloidioma4"),(String)row.get("ctituloidioma5"),
				(String)row.get("cdescripcionidioma1"), (String)row.get("cdescripcionidioma2"),
				(String)row.get("cdescripcionidioma3"), (String)row.get("cdescripcionidioma4"), 
				(String)row.get("cdescripcionidioma5"),
				(int)row.get("ndias"),(int)row.get("nnoches"), 
				(Number)row.get("npreciouno"),(Number)row.get("npreciodos"),
				(Number)row.get("npreciotres"),(Number)row.get("npreciocuatro"), 
				(Number)row.get("npreciocinco"),(String)row.get("cdisponibilidad"), 
				(boolean)row.get("bestado"));
	}
	public void asignarListaPaquetes(List lista)
	{
		listaPaquetes=new ArrayList<CPaquete>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaPaquetes.add(new CPaquete((String)row.get("cpaquetecod"),(String)row.get("ctituloidioma1"),
					(String)row.get("ctituloidioma2"),(String)row.get("ctituloidioma3"),
					(String)row.get("ctituloidioma4"),(String)row.get("ctituloidioma5"),
					(String)row.get("cdescripcionidioma1"), (String)row.get("cdescripcionidioma2"),
					(String)row.get("cdescripcionidioma3"), (String)row.get("cdescripcionidioma4"), 
					(String)row.get("cdescripcionidioma5"),
					(int)row.get("ndias"),(int)row.get("nnoches"), 
					(Number)row.get("npreciouno"),(Number)row.get("npreciodos"),
					(Number)row.get("npreciotres"),(Number)row.get("npreciocuatro"), 
					(Number)row.get("npreciocinco"),(String)row.get("cdisponibilidad"), 
					(boolean)row.get("bestado")));
		}
	}
	public List modificarPaquete(CPaquete paquete)
	{
		Object[] values={paquete.getcPaqueteCod(),paquete.getcTituloIdioma1(),
				paquete.getcTituloIdioma2(),paquete.getcTituloIdioma3(),
				paquete.getcTituloIdioma4(),paquete.getcTituloIdioma5(),
				paquete.getcDescripcionIdioma1(),paquete.getcDescripcionIdioma2(),
				paquete.getcDescripcionIdioma3(),paquete.getcDescripcionIdioma4(),
				paquete.getcDescripcionIdioma5(),paquete.getnDias(),paquete.getnNoches(),
				paquete.getnPrecioUno().doubleValue(),paquete.getnPrecioDos().doubleValue(),
				paquete.getnPrecioTres().doubleValue(),paquete.getnPrecioCuatro().doubleValue(),
				paquete.getnPrecioCinco().doubleValue(),paquete.getcDisponibilidad()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarPaquetes", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}