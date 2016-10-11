package com.pricing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pricing.model.CReportePagos;
import com.pricing.model.CReporteReserva;

public class CReportePagosDAO  extends CConexion{
	//==============atributos===================
	private ArrayList<CReportePagos> listaReportePagos;
	private CReportePagos reportePagos;
	//=================getter and setter=============
	public ArrayList<CReportePagos> getListaReportePagos() {
		return listaReportePagos;
	}
	public void setListaReportePagos(ArrayList<CReportePagos> listaReportePagos) {
		this.listaReportePagos = listaReportePagos;
	}
	public CReportePagos getReportePagos() {
		return reportePagos;
	}
	public void setReportePagos(CReportePagos reportePagos) {
		this.reportePagos = reportePagos;
	}
	//=====================constructores==========
	public CReportePagosDAO()
	{
		super();
	}
	//====================metodos====================
	public List recuperarPagosVisaBD(String fechaInicio,String fechaFinal,String estado)
	{
		Object[] values={fechaInicio,fechaFinal,estado};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarPagosVisaEntreFechasBD",values);
	}
	public List recuperarPagosPaypalBD(String fechaInicio,String fechaFinal,String estado)
	{
		Object[] values={fechaInicio,fechaFinal,estado};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarPagosPaypalEntreFechasBD",values);
	}
	public void asignarVisaListaReportePagos(List lista)
	{
		this.listaReportePagos = new ArrayList<CReportePagos>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaReportePagos.add(new CReportePagos((String)row.get("creservacod"),(Date)row.get("dfechainicio"), 
					(Date)row.get("dfechafin"),(Date)row.get("dfecha"),
					(String)row.get("ctituloidioma1"),(int)row.get("nnropersonas"),
					(Number)row.get("nimporte"),(Number)row.get("nporcentaje"),(String)row.get("formapago"),
					(String)row.get("estado"),(Date)row.get("fechayhora_initx"),(String)row.get("codtransaccion"),
					(String)row.get("nom_th"),(String)row.get("capellidos"),(String)row.get("cnombres"),(char)row.get("csexo"),
					(int)row.get("nedad"),(String)row.get("cabrevtipodoc"),(String)row.get("cnombreesp"),(String)row.get("nro_tarjeta")));
		}
	}
}
