package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.annotation.Init;

import com.pricing.dao.CDestinoHotelDAO;
import com.pricing.dao.CHotelDAO;
import com.pricing.model.CHotel;

public class hotelesVM 
{
	/**=============**/
	/**==ATRIBUTOS==**/
	/**=============**/
	private CHotel oHotel;
	private ArrayList<CHotel> listaHoteles;
	private CHotelDAO hotelDao;
	/**=====================**/
	/**==GETTER AND SETTER==**/
	/**=====================**/
	public CHotel getoHotel() {
		return oHotel;
	}
	public void setoHotel(CHotel oHotel) {
		this.oHotel = oHotel;
	}
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}
	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}
	public CHotelDAO getHotelDao() {
		return hotelDao;
	}
	public void setHotelDao(CHotelDAO hotelDao) {
		this.hotelDao = hotelDao;
	}
	/**===========**/
	/**==METODOS==**/
	/**===========**/
	
	/**
	 * Inicializa el view model de hoteles
	 */
	@Init
	public void inicializarVM()
	{
		oHotel=new CHotel();
		hotelDao=new CHotelDAO();
	}
	
}
