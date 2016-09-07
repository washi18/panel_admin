package com.pricing.model;

public class CEtiqueta
{
	private int codEtiqueta;// int default nextval('seq_etiqueta'),
	private String cIdioma1;// text,
	private String cIdioma2;// text,
	private String cIdioma3;// text,
	private String cIdioma4;// text,
	private String cIdioma5;// text,
	private boolean editable;
	//GETTER AND SETTER
	public int getCodEtiqueta() {
		return codEtiqueta;
	}
	public void setCodEtiqueta(int codEtiqueta) {
		this.codEtiqueta = codEtiqueta;
	}
	public String getcIdioma1() {
		return cIdioma1;
	}
	public void setcIdioma1(String cIdioma1) {
		this.cIdioma1 = cIdioma1;
	}
	public String getcIdioma2() {
		return cIdioma2;
	}
	public void setcIdioma2(String cIdioma2) {
		this.cIdioma2 = cIdioma2;
	}
	public String getcIdioma3() {
		return cIdioma3;
	}
	public void setcIdioma3(String cIdioma3) {
		this.cIdioma3 = cIdioma3;
	}
	public String getcIdioma4() {
		return cIdioma4;
	}
	public void setcIdioma4(String cIdioma4) {
		this.cIdioma4 = cIdioma4;
	}
	public String getcIdioma5() {
		return cIdioma5;
	}
	public void setcIdioma5(String cIdioma5) {
		this.cIdioma5 = cIdioma5;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	//===METODOS===
	public CEtiqueta()
	{
		this.cIdioma1 = "";
		this.cIdioma2 = "";
		this.cIdioma3 = "";
		this.cIdioma4 = "";
		this.cIdioma5 = "";
	}
	public CEtiqueta(int codEtiqueta, String cIdioma1, String cIdioma2,
			String cIdioma3, String cIdioma4, String cIdioma5) {
		this.codEtiqueta = codEtiqueta;
		this.cIdioma1 = cIdioma1;
		this.cIdioma2 = cIdioma2;
		this.cIdioma3 = cIdioma3;
		this.cIdioma4 = cIdioma4;
		this.cIdioma5 = cIdioma5;
		this.editable=false;
	}
}
