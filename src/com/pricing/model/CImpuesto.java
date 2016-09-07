package com.pricing.model;

public class CImpuesto 
{
	private int codImpuesto;// int,
	private String impuestoPaypal;// varchar(5),
	private String impuestoVisa;// varchar(5),
	private String porcentajeCobro;
	private boolean editable;
	//================
	public int getCodImpuesto() {
		return codImpuesto;
	}
	public void setCodImpuesto(int codImpuesto) {
		this.codImpuesto = codImpuesto;
	}
	public String getImpuestoPaypal() {
		return impuestoPaypal;
	}
	public void setImpuestoPaypal(String impuestoPaypal) {
		this.impuestoPaypal = impuestoPaypal;
	}
	public String getImpuestoVisa() {
		return impuestoVisa;
	}
	public void setImpuestoVisa(String impuestoVisa) {
		this.impuestoVisa = impuestoVisa;
	}
	public String getPorcentajeCobro() {
		return porcentajeCobro;
	}
	public void setPorcentajeCobro(String porcentajeCobro) {
		this.porcentajeCobro = porcentajeCobro;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	//===========================
	public CImpuesto() {
		// TODO Auto-generated constructor stub
	}
	public CImpuesto(int codImpuesto, String impuestoPaypal,
			String impuestoVisa, String porcentajeCobro, int modoCobro) {
		this.codImpuesto = codImpuesto;
		this.impuestoPaypal = impuestoPaypal;
		this.impuestoVisa = impuestoVisa;
		this.porcentajeCobro = porcentajeCobro;
		this.editable=false;
	}
}
