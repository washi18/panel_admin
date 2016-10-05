package com.pricing.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CPaquete 
{
	//====================
		private DecimalFormat df;
		private DecimalFormatSymbols simbolos;
	//====================
	private String cPaqueteCod;// varchar(10),			--codigo del paquete
	private String cTituloIdioma1;// varchar(200),			--titulo del paquete el el idioma 1
	private String cTituloIdioma2;// varchar(200),			--titulo del paquete el el idioma 2
	private String cTituloIdioma3;// varchar(200),			--titulo del paquete el el idioma 3
	private String cTituloIdioma4;// varchar(200),			--titulo del paquete el el idioma 4
	private String cTituloIdioma5;// varchar(200),			--titulo del paquete el el idioma 5
	private String cDescripcionIdioma1;// text,		        --Descripcion del paquete el el idioma 1
	private String cDescripcionIdioma2;// text,		        --Descripcion del paquete el el idioma 2
	private String cDescripcionIdioma3;// text,		        --Descripcion del paquete el el idioma 3
	private String cDescripcionIdioma4;// text,		        --Descripcion del paquete el el idioma 4
	private String cDescripcionIdioma5;// text,		        --Descripcion del paquete el el idioma 5
	private int nDias;// int,					--numero de dias
	private int nNoches;// int,					--numero de noches del paquete
	private Number nPrecioUno;// decimal(10,2),			--precio del paquete para una persona
	private Number nPrecioDos;// decimal(10,2),			--precio del paquete para dos personas
	private Number nPrecioTres;// decimal(10,2),			--precio del paquete para tres personas
	private Number nPrecioCuatro;// decimal(10,2),			--precio del paquete para cuatro personas
	private Number nPrecioCinco;// decimal(10,2),			--precio del paquete para cinco personas a mas
	private String cDisponibilidad;// varchar(100),                   --disponibilidad del que se requiere (CAMINO_INKA,MACHUPICCHU,NINGUNO)
	private boolean bEstado;// boolean,				--estado del paquete
	private int nDiaCaminoInka;//int,
	private String nPrecio1_text;
	private String nPrecio2_text;
	private String nPrecio3_text;
	private String nPrecio4_text;
	private String nPrecio5_text;
	private String Titulo;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private boolean manejo_camino_inca;
	private boolean manejo_propio;
	private boolean manejo_normal;
	private boolean manejo_propio_ConCaminoInka;
	private boolean editable;
	//==========================
	public String getcPaqueteCod() {
		return cPaqueteCod;
	}
	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}
	public String getcTituloIdioma1() {
		return cTituloIdioma1;
	}
	public void setcTituloIdioma1(String cTituloIdioma1) {
		this.cTituloIdioma1 = cTituloIdioma1;
	}
	public String getcTituloIdioma2() {
		return cTituloIdioma2;
	}
	public void setcTituloIdioma2(String cTituloIdioma2) {
		this.cTituloIdioma2 = cTituloIdioma2;
	}
	public String getcTituloIdioma3() {
		return cTituloIdioma3;
	}
	public void setcTituloIdioma3(String cTituloIdioma3) {
		this.cTituloIdioma3 = cTituloIdioma3;
	}
	public String getcTituloIdioma4() {
		return cTituloIdioma4;
	}
	public void setcTituloIdioma4(String cTituloIdioma4) {
		this.cTituloIdioma4 = cTituloIdioma4;
	}
	public String getcTituloIdioma5() {
		return cTituloIdioma5;
	}
	public void setcTituloIdioma5(String cTituloIdioma5) {
		this.cTituloIdioma5 = cTituloIdioma5;
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
	public int getnDias() {
		return nDias;
	}
	public void setnDias(int nDias) {
		this.nDias = nDias;
	}
	public int getnNoches() {
		return nNoches;
	}
	public void setnNoches(int nNoches) {
		this.nNoches = nNoches;
	}
	public Number getnPrecioUno() {
		return nPrecioUno;
	}
	public void setnPrecioUno(Number nPrecioUno) {
		this.nPrecioUno = nPrecioUno;
	}
	public Number getnPrecioDos() {
		return nPrecioDos;
	}
	public void setnPrecioDos(Number nPrecioDos) {
		this.nPrecioDos = nPrecioDos;
	}
	public Number getnPrecioTres() {
		return nPrecioTres;
	}
	public void setnPrecioTres(Number nPrecioTres) {
		this.nPrecioTres = nPrecioTres;
	}
	public Number getnPrecioCuatro() {
		return nPrecioCuatro;
	}
	public void setnPrecioCuatro(Number nPrecioCuatro) {
		this.nPrecioCuatro = nPrecioCuatro;
	}
	public Number getnPrecioCinco() {
		return nPrecioCinco;
	}
	public void setnPrecioCinco(Number nPrecioCinco) {
		this.nPrecioCinco = nPrecioCinco;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public String getcDisponibilidad() {
		return cDisponibilidad;
	}
	public void setcDisponibilidad(String cDisponibilidad) {
		this.cDisponibilidad = cDisponibilidad;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
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
	public boolean isManejo_camino_inca() {
		return manejo_camino_inca;
	}
	public void setManejo_camino_inca(boolean manejo_camino_inca) {
		this.manejo_camino_inca = manejo_camino_inca;
	}
	public boolean isManejo_propio() {
		return manejo_propio;
	}
	public void setManejo_propio(boolean manejo_propio) {
		this.manejo_propio = manejo_propio;
	}
	public boolean isManejo_normal() {
		return manejo_normal;
	}
	public void setManejo_normal(boolean manejo_normal) {
		this.manejo_normal = manejo_normal;
	}
	public int getnDiaCaminoInka() {
		return nDiaCaminoInka;
	}
	public void setnDiaCaminoInka(int nDiaCaminoInka) {
		this.nDiaCaminoInka = nDiaCaminoInka;
	}
	public boolean isManejo_propio_ConCaminoInka() {
		return manejo_propio_ConCaminoInka;
	}
	public void setManejo_propio_ConCaminoInka(boolean manejo_propio_ConCaminoInka) {
		this.manejo_propio_ConCaminoInka = manejo_propio_ConCaminoInka;
	}
	public String getnPrecio1_text() {
		return nPrecio1_text;
	}
	public void setnPrecio1_text(String nPrecio1_text) {
		this.nPrecio1_text = nPrecio1_text;
	}
	public String getnPrecio2_text() {
		return nPrecio2_text;
	}
	public void setnPrecio2_text(String nPrecio2_text) {
		this.nPrecio2_text = nPrecio2_text;
	}
	public String getnPrecio3_text() {
		return nPrecio3_text;
	}
	public void setnPrecio3_text(String nPrecio3_text) {
		this.nPrecio3_text = nPrecio3_text;
	}
	public String getnPrecio4_text() {
		return nPrecio4_text;
	}
	public void setnPrecio4_text(String nPrecio4_text) {
		this.nPrecio4_text = nPrecio4_text;
	}
	public String getnPrecio5_text() {
		return nPrecio5_text;
	}
	public void setnPrecio5_text(String nPrecio5_text) {
		this.nPrecio5_text = nPrecio5_text;
	}
	//=========================================
	public CPaquete() {
		// TODO Auto-generated constructor stub
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		Titulo="";
		manejo_camino_inca=false;
		manejo_propio=false;
		manejo_normal=false;
		nDiaCaminoInka=0;
		cTituloIdioma1="";
		cTituloIdioma2="";
		cTituloIdioma3="";
		nPrecio1_text=df.format(0);
		nPrecio2_text=df.format(0);
		nPrecio3_text=df.format(0);
		nPrecio4_text=df.format(0);
		nPrecio5_text=df.format(0);
		nPrecioUno=0;
		nPrecioDos=0;
		nPrecioTres=0;
		nPrecioCuatro=0;
		nPrecioCinco=0;
		manejo_propio_ConCaminoInka=false;
	}
	public CPaquete(String cPaqueteCod, String cTituloIdioma1,
			String cTituloIdioma2, String cTituloIdioma3,
			String cTituloIdioma4, String cTituloIdioma5,
			String cDescripcionIdioma1, String cDescripcionIdioma2,
			String cDescripcionIdioma3, String cDescripcionIdioma4,
			String cDescripcionIdioma5, int nDias, int nNoches,
			Number nPrecioUno, Number nPrecioDos, Number nPrecioTres,
			Number nPrecioCuatro, Number nPrecioCinco, String cDisponibilidad,
			boolean bEstado) {
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		this.cPaqueteCod = cPaqueteCod;
		this.cTituloIdioma1 = cTituloIdioma1;
		this.cTituloIdioma2 = cTituloIdioma2;
		this.cTituloIdioma3 = cTituloIdioma3;
		this.cTituloIdioma4 = cTituloIdioma4;
		this.cTituloIdioma5 = cTituloIdioma5;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.nDias = nDias;
		this.nNoches = nNoches;
		this.nPrecioUno = nPrecioUno;
		this.nPrecioDos = nPrecioDos;
		this.nPrecioTres = nPrecioTres;
		this.nPrecioCuatro = nPrecioCuatro;
		this.nPrecioCinco = nPrecioCinco;
		this.cDisponibilidad = cDisponibilidad;
		this.bEstado = bEstado;
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
	}
	
	
}
