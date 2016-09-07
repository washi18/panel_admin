package com.pricing.model;

public class CDestinoHotel
{
	private String destinoHotelCod;// varchar(10),                             --codigo de la relacion entre el hotel y destino (TDestinoHotel)
	private int nDestinoCod;// int,                                 --codigo del destino alque pertenecen los hoteles
	private int nHotelCod;// int,
	//========================
	public String getDestinoHotelCod() {
		return destinoHotelCod;
	}
	public void setDestinoHotelCod(String destinoHotelCod) {
		this.destinoHotelCod = destinoHotelCod;
	}
	public int getnDestinoCod() {
		return nDestinoCod;
	}
	public void setnDestinoCod(int nDestinoCod) {
		this.nDestinoCod = nDestinoCod;
	}
	public int getnHotelCod() {
		return nHotelCod;
	}
	public void setnHotelCod(int nHotelCod) {
		this.nHotelCod = nHotelCod;
	}
	//========================
	public CDestinoHotel() {
		// TODO Auto-generated constructor stub
	}
	public CDestinoHotel(String destinoHotelCod, int nDestinoCod, int nHotelCod) {
		this.destinoHotelCod = destinoHotelCod;
		this.nDestinoCod = nDestinoCod;
		this.nHotelCod = nHotelCod;
	}
	
}
