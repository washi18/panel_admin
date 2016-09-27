package com.pricing.viewModel;

import java.util.ArrayList;
import org.zkoss.bind.annotation.Init;
import com.pricing.dao.CReservaDAO;
import com.pricing.model.CReserva;

public class ReservasVM {
	//====atributos=====
	private ArrayList<CReserva> listaReservas;
	private CReservaDAO reservaDao;
	//=====getter and setterr=====

	public ArrayList<CReserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<CReserva> listaReservas) {
		this.listaReservas = listaReservas;
	}
	
	public CReservaDAO getReservaDao() {
		return reservaDao;
	}

	public void setReservaDao(CReservaDAO reservaDao) {
		this.reservaDao = reservaDao;
	}

	//=====metodos=========
	@Init
	public void initVM()
	{
		/*******************************/
		/*******************************/
		/**Inicializando los objetos**/
		listaReservas=new ArrayList<CReserva>();
		reservaDao=new CReservaDAO();
		/**Obtencion de las etiquetas de la base de datos**/
		reservaDao.asignarListaReservas(reservaDao.recuperarTodasReservasBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaReservas(reservaDao.getListaReservas());
	}
}
