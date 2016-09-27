package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CDestinoHotelDAO;
import com.pricing.model.CDestino;
import com.pricing.model.CDestinoHotel;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;

public class DestinosVM {
	/*====atributos=====*/
	private CDestinoDAO destinoDao;
	private CDestino oDestinoNuevo;
	private CDestino oDestinoUpdate;
	private ArrayList<CDestino> listaDestinos;
	/*=====getter and setter====*/
	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}
	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}
	public CDestinoDAO getDestinoDao() {
		return destinoDao;
	}
	public void setDestinoDao(CDestinoDAO destinoDao) {
		this.destinoDao = destinoDao;
	}
	public CDestino getoDestinoNuevo() {
		return oDestinoNuevo;
	}
	public void setoDestinoNuevo(CDestino oDestinoNuevo) {
		this.oDestinoNuevo = oDestinoNuevo;
	}
	public CDestino getoDestinoUpdate() {
		return oDestinoUpdate;
	}
	public void setoDestinoUpdate(CDestino oDestinoUpdate) {
		this.oDestinoUpdate = oDestinoUpdate;
	}
	/*======metodos=====*/
	@Init
	public void initVM()
	{
		destinoDao=new CDestinoDAO();
		oDestinoNuevo=new CDestino();
		oDestinoUpdate=new CDestino();
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
	}
	@Command
	@NotifyChange({"listaDestinos"})
	public void insertarDestino(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
		boolean correcto=destinoDao.isOperationCorrect(destinoDao.insertarDestino(oDestinoNuevo));
		if(correcto)
		{
			listaDestinos.clear();
			destinoDao.asignarListaDestinos(destinoDao.recuperarListaTodosDestinosBD());
			setListaDestinos(destinoDao.getListaDestinos());
			Clients.showNotification("El Destino se inserto correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start", 2700);
		}
		else
			Clients.showNotification("El Destino no se inserto", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
	}
	public boolean validoParaInsertar(Component comp)
	{
		oDestinoNuevo.setcDestino(oDestinoNuevo.getcDestino().toUpperCase());
		boolean valido=true;
		if(oDestinoNuevo.getcDestino().equals(""))
		{
			valido=false;
			Clients.showNotification("El Destino siempre debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		return valido;
	}
	@Command
	public void actualizarDestino(@BindingParam("destino")CDestino destino,@BindingParam("componente")Component comp)
	{
		if(!validoParaActualizar(destino,comp))
			return;
		boolean correcto=destinoDao.isOperationCorrect(destinoDao.modificarDestino(destino));
		if(correcto)
		{
			Clients.showNotification("El Destino se actualizo correctamente", Clients.NOTIFICATION_TYPE_INFO, comp,"before_start", 2700);
		}
		else
			Clients.showNotification("El Destino no se actualizo", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		destino.setEditable(false);
		refrescaFilaTemplate(destino);
	}
	public boolean validoParaActualizar(CDestino destino,Component comp)
	{
		destino.setcDestino(destino.getcDestino().toUpperCase());
		boolean valido=true;
		if(destino.getcDestino().equals(""))
		{
			valido=false;
			Clients.showNotification("El Destino siempre debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		return valido;
	}
	@Command
	public void Editar(@BindingParam("destino") CDestino d ) 
	{
		d.setEditable(false);
		oDestinoUpdate.setEditable(false);
		refrescaFilaTemplate(oDestinoUpdate);
		oDestinoUpdate=d;
		//le damos estado editable
		d.setEditable(!d.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(d);
   }
	@Command
	public void Activar_Desactivar_destino(@BindingParam("destino")CDestino d,@BindingParam("texto")String texto)
	{
		if(texto.equals("activar"))
		{
			d.setColor_btn_activo(d.COLOR_ACTIVO);
			d.setColor_btn_desactivo(d.COLOR_TRANSPARENT);
			d.setEstado_activo(true);
			d.setEstado_desactivo(false);
			d.setbEstado(true);
		}else{
			d.setColor_btn_activo(d.COLOR_TRANSPARENT);
			d.setColor_btn_desactivo(d.COLOR_DESACTIVO);
			d.setEstado_activo(false);
			d.setEstado_desactivo(true);
			d.setbEstado(false);
		}
		BindUtils.postNotifyChange(null, null, d,"estado_activo");
		BindUtils.postNotifyChange(null, null, d,"estado_desactivo");
		BindUtils.postNotifyChange(null, null, d,"color_btn_activo");
		BindUtils.postNotifyChange(null, null, d,"color_btn_desactivo");
	}
	public void refrescaFilaTemplate(CDestino d)
	{
		BindUtils.postNotifyChange(null, null, d, "editable");
	}
}
