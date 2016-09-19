package com.pricing.model;

public class CHotel 
{
	private int nHotelCod;// int,					--codigo del hotel
	private String cHotel;// varchar(200),				--nombre del hotel
	private String cDescripcionIdioma1;// text,			--descripcion del hotel en el idioma 1
	private String cDescripcionIdioma2;// text,			--descripcion del hotel en el idioma 2
	private String cDescripcionIdioma3;// text,			--descripcion del hotel en el idioma 3
	private String cDescripcionIdioma4;// text,			--descripcion del hotel en el idioma 4
	private String cDescripcionIdioma5;// text,			--descripcion del hotel en el idioma 5
	private String cUrl;// varchar(200),				--url de la pagina web del hotel o contenido en nuestra pagina
	private int categoriaHotelCod;// int,				--codigo de la categoria al que pertenece el hotel
	private Number nPrecioSimple;// decimal(10,2),			--precio del hotel con habitacion simple
	private Number nPrecioDoble;// decimal(10,2),			--precio del hotel con habitacion doble
	private Number nPrecioTriple;// decimal(10,2),			--precio del hotel con habitacion triple
	private boolean bEstado;// boolean,				--estado del hotel (si trabaja o no con nuestros paquetes)
	private boolean editable;
	//================================
	public int getnHotelCod() {
		return nHotelCod;
	}
	public void setnHotelCod(int nHotelCod) {
		this.nHotelCod = nHotelCod;
	}
	public String getcHotel() {
		return cHotel;
	}
	public void setcHotel(String cHotel) {
		this.cHotel = cHotel;
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
	public String getcUrl() {
		return cUrl;
	}
	public void setcUrl(String cUrl) {
		this.cUrl = cUrl;
	}
	public int getCategoriaHotelCod() {
		return categoriaHotelCod;
	}
	public void setCategoriaHotelCod(int categoriaHotelCod) {
		this.categoriaHotelCod = categoriaHotelCod;
	}
	public Number getnPrecioSimple() {
		return nPrecioSimple;
	}
	public void setnPrecioSimple(Number nPrecioSimple) {
		this.nPrecioSimple = nPrecioSimple;
	}
	public Number getnPrecioDoble() {
		return nPrecioDoble;
	}
	public void setnPrecioDoble(Number nPrecioDoble) {
		this.nPrecioDoble = nPrecioDoble;
	}
	public Number getnPrecioTriple() {
		return nPrecioTriple;
	}
	public void setnPrecioTriple(Number nPrecioTriple) {
		this.nPrecioTriple = nPrecioTriple;
	}
	public boolean isbEstado() {
		return bEstado;
	}
	public void setbEstado(boolean bEstado) {
		this.bEstado = bEstado;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	//======================================
	public CHotel() {
		// TODO Auto-generated constructor stub
		this.cHotel ="";
		this.cDescripcionIdioma1 ="";
		this.cDescripcionIdioma2 ="";
		this.cDescripcionIdioma3 ="";
		this.cDescripcionIdioma4 ="";
		this.cDescripcionIdioma5 ="";
		this.cUrl ="";
		this.categoriaHotelCod =0;
		this.nPrecioSimple =0;
		this.nPrecioDoble =0;
		this.nPrecioTriple =0;
	}
	public CHotel(int nHotelCod, String cHotel, String cDescripcionIdioma1,
			String cDescripcionIdioma2, String cDescripcionIdioma3,
			String cDescripcionIdioma4, String cDescripcionIdioma5,
			String cUrl, int categoriaHotelCod, Number nPrecioSimple,
			Number nPrecioDoble, Number nPrecioTriple, boolean bEstado) {
		this.nHotelCod = nHotelCod;
		this.cHotel = cHotel;
		this.cDescripcionIdioma1 = cDescripcionIdioma1;
		this.cDescripcionIdioma2 = cDescripcionIdioma2;
		this.cDescripcionIdioma3 = cDescripcionIdioma3;
		this.cDescripcionIdioma4 = cDescripcionIdioma4;
		this.cDescripcionIdioma5 = cDescripcionIdioma5;
		this.cUrl = cUrl;
		this.categoriaHotelCod = categoriaHotelCod;
		this.nPrecioSimple = nPrecioSimple;
		this.nPrecioDoble = nPrecioDoble;
		this.nPrecioTriple = nPrecioTriple;
		this.bEstado = bEstado;
		this.editable=false;
	}
	
}
