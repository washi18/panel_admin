package com.pricing.model;

public class CSubServicio 
{
	private int nSubServicioCod;// int,
	private int nServicioCod;// int,
	private String cSubServicioIndioma1;// varchar(200),                   --nombre del servicio en el idioma 1
	private String cSubServicioIndioma2;// varchar(200),                   --nombre del servicio en el idioma 2
	private String cSubServicioIndioma3;// varchar(200),                   --nombre del servicio en el idioma 3
	private String cSubServicioIndioma4;// varchar(200),                   --nombre del servicio en el idioma 4
	private String cSubServicioIndioma5;// varchar(200),                   --nombre del servicio en el idioma 5
	private String cDescripcionIdioma1;// text,                         --descripcion de lo que ofrece el servicio en el idioma 1
	private String cDescripcionIdioma2;// text,                         --descripcion de lo que ofrece el servicio en el idioma 2
	private String cDescripcionIdioma3;// text,                         --descripcion de lo que ofrece el servicio en el idioma 3
	private String cDescripcionIdioma4;// text,                         --descripcion de lo que ofrece el servicio en el idioma 4
	private String cDescripcionIdioma5;// text,                         --descripcion de lo que ofrece el servicio en el idioma 5
	private String cUrlImg;// varchar(200),
	private String cLink;// text,
	private Number nPrecioServicio;// decimal(10,2),
	private boolean bEstado;// boolean,
	private String tituloSubServicio;
	private String cDescripcion;
	private boolean visibleEspanol;
	private boolean visibleIngles;
	private boolean visiblePortugues;
	private boolean editable;
	//============================
	
	public int getnSubServicioCod() {
		return nSubServicioCod;
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
	public void setnSubServicioCod(int nSubServicioCod) {
		this.nSubServicioCod = nSubServicioCod;
	}
	public int getnServicioCod() {
		return nServicioCod;
	}
	public void setnServicioCod(int nServicioCod) {
		this.nServicioCod = nServicioCod;
	}
	public String getcSubServicioIndioma1() {
		return cSubServicioIndioma1;
	}
	public void setcSubServicioIndioma1(String cSubServicioIndioma1) {
		this.cSubServicioIndioma1 = cSubServicioIndioma1;
	}
	public String getcSubServicioIndioma2() {
		return cSubServicioIndioma2;
	}
	public void setcSubServicioIndioma2(String cSubServicioIndioma2) {
		this.cSubServicioIndioma2 = cSubServicioIndioma2;
	}
	public String getcSubServicioIndioma3() {
		return cSubServicioIndioma3;
	}
	public void setcSubServicioIndioma3(String cSubServicioIndioma3) {
		this.cSubServicioIndioma3 = cSubServicioIndioma3;
	}
	public String getcSubServicioIndioma4() {
		return cSubServicioIndioma4;
	}
	public void setcSubServicioIndioma4(String cSubServicioIndioma4) {
		this.cSubServicioIndioma4 = cSubServicioIndioma4;
	}
	public String getcSubServicioIndioma5() {
		return cSubServicioIndioma5;
	}
	public void setcSubServicioIndioma5(String cSubServicioIndioma5) {
		this.cSubServicioIndioma5 = cSubServicioIndioma5;
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
	public String getcLink() {
		return cLink;
	}
	public void setcLink(String cLink) {
		this.cLink = cLink;
	}
	public Number getnPrecioServicio() {
		return nPrecioServicio;
	}
	public void setnPrecioServicio(Number nPrecioServicio) {
		this.nPrecioServicio = nPrecioServicio;
	}
	public String getTituloSubServicio() {
		return tituloSubServicio;
	}
	public void setTituloSubServicio(String tituloSubServicio) {
		this.tituloSubServicio = tituloSubServicio;
	}
	public String getcDescripcion() {
		return cDescripcion;
	}
	public void setcDescripcion(String cDescripcion) {
		this.cDescripcion = cDescripcion;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	//============================
	public CSubServicio() {
		// TODO Auto-generated constructor stub
		cDescripcion="";
		tituloSubServicio="";
	}
	public CSubServicio(int nSubServicioCod, int nServicioCod,
			String cSubServicioIndioma1, String cSubServicioIndioma2,
			String cSubServicioIndioma3, String cSubServicioIndioma4,
			String cSubServicioIndioma5, String cDescripcionIdioma1,
			String cDescripcionIdioma2, String cDescripcionIdioma3,
			String cDescripcionIdioma4, String cDescripcionIdioma5,
			String cUrlImg, String cLink, Number nPrecioServicio,
			boolean bEstado) {
		this.nSubServicioCod = nSubServicioCod;
		this.nServicioCod = nServicioCod;
		this.cSubServicioIndioma1 = cSubServicioIndioma1;
		this.cSubServicioIndioma2 = cSubServicioIndioma2;
		this.cSubServicioIndioma3 = cSubServicioIndioma3;
		this.cSubServicioIndioma4 = cSubServicioIndioma4;
		this.cSubServicioIndioma5 = cSubServicioIndioma5;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cUrlImg = cUrlImg;
		this.cLink = cLink;
		this.nPrecioServicio = nPrecioServicio;
		this.bEstado = bEstado;
		this.editable=false;
		this.visibleEspanol=true;
		this.visibleIngles=false;
		this.visiblePortugues=false;
	}
	
}
