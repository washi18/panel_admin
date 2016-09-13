package com.pricing.model;

import java.util.ArrayList;

public class CServicio 
{
	private int nServicioCod;// int,
	private String cServicioIndioma1;// varchar(200),                   --nombre del servicio en el idioma 1
	private String cServicioIndioma2;// varchar(200),                   --nombre del servicio en el idioma 2
	private String cServicioIndioma3;// varchar(200),                   --nombre del servicio en el idioma 3
	private String cServicioIndioma4;// varchar(200),                   --nombre del servicio en el idioma 4
	private String cServicioIndioma5;// varchar(200),                   --nombre del servicio en el idioma 5
	private String cDescripcionIdioma1;// text,                         --descripcion de lo que ofrece el servicio en el idioma 1
	private String cDescripcionIdioma2;// text,                         --descripcion de lo que ofrece el servicio en el idioma 2
	private String cDescripcionIdioma3;// text,               --descripcion de lo que ofrece el servicio en el idioma 3
	private String cDescripcionIdioma4;// text,               --descripcion de lo que ofrece el servicio en el idioma 4
	private String cDescripcionIdioma5;// text,               --descripcion de lo que ofrece el servicio en el idioma 5
	private int cRestriccionYesNo;// int,			--Restriccion yes/no (0=no se toma restriccion,1=si se toma restriccion)
	private int cRestriccionNum;// int,			--Restriccion numerica (0=no se toma restriccion, n>=1 se toma restriccion y restriccion proporcional a n*nroPasajeros)
	private int cIncremento;// int,		--De cuanto en cuanto se puede llegar a la restriccion numerica
	private String cUrlImg;// varchar(200),
	private Number nPrecioServicio;// decimal(10,2),
	private boolean bEstado;// boolean,
	private ArrayList<String[]> listaOpcionServicios;
	private String DescripcionVisible;//La descripcion que sera visible al usuario
	private String urlImageVisible;//Imagen que sera visible al usuario
	private String selectOpcion;
	private String opcionValue;
	private boolean mostrarDescripcion;
	private float cantidadServicio;
	private String precioTotalServicio;
	private String link;
	private String Servicio;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private boolean editable;
	//=============================
	public int getnServicioCod() {
		return nServicioCod;
	}
	public void setnServicioCod(int nServicioCod) {
		this.nServicioCod = nServicioCod;
	}
	public String getcServicioIndioma1() {
		return cServicioIndioma1;
	}
	public void setcServicioIndioma1(String cServicioIndioma1) {
		this.cServicioIndioma1 = cServicioIndioma1;
	}
	public String getcServicioIndioma2() {
		return cServicioIndioma2;
	}
	public void setcServicioIndioma2(String cServicioIndioma2) {
		this.cServicioIndioma2 = cServicioIndioma2;
	}
	public String getcServicioIndioma3() {
		return cServicioIndioma3;
	}
	public void setcServicioIndioma3(String cServicioIndioma3) {
		this.cServicioIndioma3 = cServicioIndioma3;
	}
	public String getcServicioIndioma4() {
		return cServicioIndioma4;
	}
	public void setcServicioIndioma4(String cServicioIndioma4) {
		this.cServicioIndioma4 = cServicioIndioma4;
	}
	public String getcServicioIndioma5() {
		return cServicioIndioma5;
	}
	public void setcServicioIndioma5(String cServicioIndioma5) {
		this.cServicioIndioma5 = cServicioIndioma5;
	}
	public String getcDescripcionIdioma1() {
		return cDescripcionIdioma1;
	}
	public void setcDescripcionIdioma1(String cDescripcionIdioma1) {
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
	}
	public String getcDescripcionIdioma2() {
		return cDescripcionIdioma2;
	}
	public void setcDescripcionIdioma2(String cDescripcionIdioma2) {
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
	}
	public String getcDescripcionIdioma3() {
		return cDescripcionIdioma3;
	}
	public void setcDescripcionIdioma3(String cDescripcionIdioma3) {
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
	}
	public String getcDescripcionIdioma4() {
		return cDescripcionIdioma4;
	}
	public void setcDescripcionIdioma4(String cDescripcionIdioma4) {
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
	}
	public String getcDescripcionIdioma5() {
		return cDescripcionIdioma5;
	}
	public void setcDescripcionIdioma5(String cDescripcionIdioma5) {
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
	}
	public int getcRestriccionYesNo() {
		return cRestriccionYesNo;
	}
	public void setcRestriccionYesNo(int cRestriccionYesNo) {
		this.cRestriccionYesNo = cRestriccionYesNo;
	}
	public int getcRestriccionNum() {
		return cRestriccionNum;
	}
	public void setcRestriccionNum(int cRestriccionNum) {
		this.cRestriccionNum = cRestriccionNum;
	}
	public int getcIncremento() {
		return cIncremento;
	}
	public void setcIncremento(int cIncremento) {
		this.cIncremento = cIncremento;
	}
	public Number getnPrecioServicio() {
		return nPrecioServicio;
	}
	public void setnPrecioServicio(Number nPrecioServicio) {
		this.nPrecioServicio = nPrecioServicio;
	}
	public String getcUrlImg() {
		return cUrlImg;
	}
	public void setcUrlImg(String cUrlImg) {
		this.cUrlImg = cUrlImg;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public ArrayList<String[]> getListaOpcionServicios() {
		return listaOpcionServicios;
	}
	public void setListaOpcionServicios(ArrayList<String[]> listaOpcionServicios) {
		this.listaOpcionServicios = listaOpcionServicios;
	}
	public String getDescripcionVisible() {
		return DescripcionVisible;
	}
	public void setDescripcionVisible(String descripcionVisible) {
		DescripcionVisible = descripcionVisible;
	}
	public String getUrlImageVisible() {
		return urlImageVisible;
	}
	public void setUrlImageVisible(String urlImageVisible) {
		this.urlImageVisible = urlImageVisible;
	}
	public boolean isMostrarDescripcion() {
		return mostrarDescripcion;
	}
	public void setMostrarDescripcion(boolean mostrarDescripcion) {
		this.mostrarDescripcion = mostrarDescripcion;
	}
	public String getSelectOpcion() {
		return selectOpcion;
	}
	public void setSelectOpcion(String selectOpcion) {
		this.selectOpcion = selectOpcion;
	}
	public float getCantidadServicio() {
		return cantidadServicio;
	}
	public void setCantidadServicio(float cantidadServicio) {
		this.cantidadServicio = cantidadServicio;
	}
	public String getOpcionValue() {
		return opcionValue;
	}
	public void setOpcionValue(String opcionValue) {
		this.opcionValue = opcionValue;
	}
	public String getPrecioTotalServicio() {
		return precioTotalServicio;
	}
	public void setPrecioTotalServicio(String precioTotalServicio) {
		this.precioTotalServicio = precioTotalServicio;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getServicio() {
		return Servicio;
	}
	public void setServicio(String servicio) {
		Servicio = servicio;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isVisibleEspanol() {
		return visibleEspanol;
	}
	public void setVisibleEspanol(boolean visibleEspanol) {
		this.visibleEspanol = visibleEspanol;
	}
	public boolean isVisibleIngles() {
		return visibleIngles;
	}
	public void setVisibleIngles(boolean visibleIngles) {
		this.visibleIngles = visibleIngles;
	}
	public boolean isVisiblePortugues() {
		return visiblePortugues;
	}
	public void setVisiblePortugues(boolean visiblePortugues) {
		this.visiblePortugues = visiblePortugues;
	}
	//========================================
	public CServicio() {
		// TODO Auto-generated constructor stub
		this.listaOpcionServicios=new ArrayList<String[]>();
		Servicio="";
	}
	public CServicio(int nServicioCod, String cServicioIndioma1,
			String cServicioIndioma2, String cServicioIndioma3,
			String cServicioIndioma4, String cServicioIndioma5,
			String cDescripcionIdioma1, String cDescripcionIdioma2,
			String cDescripcionIdioma3, String cDescripcionIdioma4,
			String cDescripcionIdioma5, int cRestriccionYesNo,
			int cRestriccionNum, int cIncremento, String cUrlImg,
			Number nPrecioServicio, boolean bEstado) {
		this.nServicioCod = nServicioCod;
		this.cServicioIndioma1 = cServicioIndioma1;
		this.cServicioIndioma2 = cServicioIndioma2;
		this.cServicioIndioma3 = cServicioIndioma3;
		this.cServicioIndioma4 = cServicioIndioma4;
		this.cServicioIndioma5 = cServicioIndioma5;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cRestriccionYesNo = cRestriccionYesNo;
		this.cRestriccionNum = cRestriccionNum;
		this.cIncremento = cIncremento;
		this.cUrlImg = cUrlImg;
		this.nPrecioServicio = nPrecioServicio;
		this.bEstado = bEstado;
		this.listaOpcionServicios=new ArrayList<String[]>();
		this.urlImageVisible="";
		this.DescripcionVisible="";
		this.mostrarDescripcion=false;
		this.selectOpcion="";
		this.opcionValue="0";
		this.cantidadServicio=0;
		this.precioTotalServicio="0.00";
		this.link="";
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
	}
}
