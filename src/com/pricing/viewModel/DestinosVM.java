package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import com.pricing.dao.CDestinoHotelDAO;
import com.pricing.model.CDestinoHotel;
import com.pricing.model.CHotel;

public class DestinosVM {
	/*====atributos=====*/
	private ArrayList<CDestinoHotel> listaDestinoHoteles;
	private ArrayList<CHotel> listaHoteles;
	private CDestinoHotelDAO destinoDao;
	private String nombreDestino;
	
	/*=====getter and setter====*/
	
	public CDestinoHotelDAO getDestinoDao() {
		return destinoDao;
	}

	public void setDestinoDao(CDestinoHotelDAO destinoDao) {
		this.destinoDao = destinoDao;
	}

	public ArrayList<CDestinoHotel> getListaDestinoHoteles() {
		return listaDestinoHoteles;
	}

	public void setListaDestinoHoteles(ArrayList<CDestinoHotel> listaDestinoHoteles) {
		this.listaDestinoHoteles = listaDestinoHoteles;
	}
    
	public String getNombreDestino() {
		return nombreDestino;
	}

	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}
	
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}

	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}

	/*======metodos=====*/
	@Init
	public void initVM()
	{
		destinoDao=new CDestinoHotelDAO();
		listaDestinoHoteles=new ArrayList<CDestinoHotel>();
		listaHoteles=new ArrayList<CHotel>();
		/**Obtencion de las etiquetas de la base de datos**/
		destinoDao.asignarListaDestinoHoteles(destinoDao.recuperarDestinoHotelesBD(2));
		destinoDao.asignarListaHoteles(destinoDao.recuperarListaHotelesBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaDestinoHoteles(destinoDao.getListaDestinoHoteles());
		setListaHoteles(destinoDao.getListaHoteles());
	}
	
	@Command
	public void asignar_destino(@BindingParam("destino")String destino)
	{
		
	}
}
