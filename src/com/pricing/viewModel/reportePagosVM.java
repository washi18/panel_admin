package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class reportePagosVM {
	
	//===============atributos======
	private boolean estadoPagosAutorizados;
	private boolean estadoPagosDenegados;
	private String fechaInicio;
	private String fechaFinal;
	//===============getter and setter=======
	public boolean isEstadoPagosAutorizados() {
		return estadoPagosAutorizados;
	}
	public void setEstadoPagosAutorizados(boolean estadoPagosAutorizados) {
		this.estadoPagosAutorizados = estadoPagosAutorizados;
	}
	public boolean isEstadoPagosDenegados() {
		return estadoPagosDenegados;
	}
	public void setEstadoPagosDenegados(boolean estadoPagosDenegados) {
		this.estadoPagosDenegados = estadoPagosDenegados;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	//=====================constructores======
	
	//====================metodos============
	@Command
	public void recuperarFechaDatebox(@BindingParam("fecha")String fecha,@BindingParam("id")String id)
	{
		if(id.equals("db_desde"))
			fechaInicio=fecha;
		else
			fechaFinal=fecha;
	}
	@Command
	@NotifyChange({"estadoPagosAutorizados","estadoPagosDenegados"})
	public void seleccion_radio(@BindingParam("radio")String idRadio)
	{
		if(idRadio.equals("rdPagoAutorizado"))
		{
			estadoPagosAutorizados=true;
			estadoPagosDenegados=false;
		}else if(idRadio.equals("rdPagoDenegado"))
		{
			estadoPagosDenegados=true;
			estadoPagosAutorizados=false;
		}
	}
	public String cambiarFormatoMes(String mes)
	{
		String nuevo="";
		switch(mes)
		{
			case "ene":nuevo="01";break;
			case "feb":nuevo="02";break;
			case "mar":nuevo="03";break;
			case "abr":nuevo="04";break;
			case "may":nuevo="05";break;
			case "jun":nuevo="06";break;
			case "jul":nuevo="07";break;
			case "ago":nuevo="08";break;
			case "sep":nuevo="09";break;
			case "oct":nuevo="10";break;
			case "nov":nuevo="11";break;
			case "dic":nuevo="12";break;
		}
		return nuevo;
	}
	
}
