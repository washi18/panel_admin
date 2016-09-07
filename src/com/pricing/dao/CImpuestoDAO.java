package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CEtiqueta;
import com.pricing.model.CImpuesto;

public class CImpuestoDAO extends CConexion
{
	private CImpuesto oImpuesto;
	//=================

	public CImpuesto getoImpuesto() {
		return oImpuesto;
	}

	public void setoImpuesto(CImpuesto oImpuesto) {
		this.oImpuesto = oImpuesto;
	}
	//================
	public CImpuestoDAO() {
		// TODO Auto-generated constructor stub
		super();
		oImpuesto=new CImpuesto();
	}
	public CImpuestoDAO(CImpuesto oImpuesto)
	{
		super();
		this.oImpuesto = oImpuesto;
	}
	//==================
	public CImpuesto recuperarImpuestosBD()
	{
		List lista=getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarImpuesto");
		Map row=(Map)lista.get(0);
		oImpuesto.setCodImpuesto((int)row.get("codimpuesto"));
		oImpuesto.setImpuestoPaypal((String)row.get("impuestopaypal"));
		oImpuesto.setImpuestoVisa((String)row.get("impuestoVisa"));
		oImpuesto.setPorcentajeCobro((String)row.get("porcentajecobro"));
		return oImpuesto;
	}
	public List modificarImpuesto(CImpuesto impuesto)
	{
		Object[] values={impuesto.getCodImpuesto(),impuesto.getImpuestoPaypal(),
				impuesto.getImpuestoVisa(),impuesto.getPorcentajeCobro()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarImpuesto", values);
	}
	public boolean isOperationCorrect(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
