package com.pricing.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pricing.model.CReserva;

public class CReservaDAO extends CConexion 
{
	private CReserva oReserva;
	//=======================

	public CReserva getoReserva() {
		return oReserva;
	}

	public void setoReserva(CReserva oReserva) {
		this.oReserva = oReserva;
	}
	//==================
	public CReservaDAO() {
		// TODO Auto-generated constructor stub
		super();
		this.oReserva=new CReserva();
	}
	public CReservaDAO(CReserva reserva)
	{
		super();
		this.oReserva=reserva;
	}
	//====OTROS METODOS====
	public List registrarReserva(CReserva reserva)
	{
		Object[] values={(Date)reserva.getdFechaInicio(),(Date)reserva.getdFechaFin(),reserva.getcContacto(),
				reserva.getcEmail(),reserva.getcTelefono(),reserva.getnPrecioPaquetePersona(),
				reserva.getnNroPersonas(),reserva.getcInformacionAdicional()};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_RegistroReserva", values);
	}
	public String[] recuperarResultados(List lista)
	{
		System.out.println("tamaño de la lista--->"+lista.size());
		Map row=(Map)lista.get(0);
		String[] r={row.get("resultado").toString(),row.get("creservacod").toString()};
		return r;
	}
	public List modificarEstadoDePago(String codReserva,String estado)
	{
		Object[] values={codReserva,estado};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ModificarEstadoPagoReserva", values);
	}
	public boolean isCorrectOperation(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
