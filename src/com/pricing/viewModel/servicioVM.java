package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CServicio;

public class servicioVM {
	/**
	 * ATRIBUTOS
	 */
	private CServicio oServicio;
	private CServicioDAO servicioDao;
	private ArrayList<CServicio> listaServicios;
	/**
	 * GETTER AND SETTER
	 */
	
	public CServicio getoServicio() {
		return oServicio;
	}
	public void setoServicio(CServicio oServicio) {
		this.oServicio = oServicio;
	}
	public CServicioDAO getServicioDao() {
		return servicioDao;
	}
	public void setServicioDao(CServicioDAO servicioDao) {
		this.servicioDao = servicioDao;
	}
	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}
	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	/**
	 * METODOS Y FUNCIONES DE LA CLASE
	 */
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		oServicio=new CServicio();
		servicioDao=new CServicioDAO();
		listaServicios=new ArrayList<CServicio>();
		/**Obtencion de las etiquetas de la base de datos**/
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaServicios(servicioDao.getListaServicios());
	}
	@Command
	public void actualizarServicio(@BindingParam("servicio")CServicio servicio)
	{	
		System.out.println("--> "+servicio);
		servicio.setEditable(false);
		refrescaFilaTemplate(servicio);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=servicioDao.isOperationCorrect(servicioDao.modificarServicio(servicio));
//		initVM();
//		System.out.println("-->"+etiqueta.getCodEtiqueta());
		
	}
	@Command
	public void Editar(@BindingParam("servicio") CServicio s ) 
	{
		s.setEditable(false);
		oServicio.setEditable(false);
		refrescaFilaTemplate(oServicio);
		oServicio=s;
		//le damos estado editable
		s.setEditable(!s.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(s);
   }
	@Command
	 public void Activar(@BindingParam("servicio") CServicio s ) 
	{
		
	}
	@Command
	 public void Desactivar(@BindingParam("servicio") CServicio s ) 
	{
		
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idIdioma,@BindingParam("servicio")CServicio servicio)
	{
		if(idIdioma.equals("Espanol"))
		{
				servicio.setVisibleEspanol(true);
				servicio.setVisibleIngles(false);
				servicio.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Ingles"))
		{
				servicio.setVisibleEspanol(false);
				servicio.setVisibleIngles(true);
				servicio.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Portugues"))
		{
				servicio.setVisibleEspanol(false);
				servicio.setVisibleIngles(false);
				servicio.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, servicio, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, servicio, "visibleIngles");
		BindUtils.postNotifyChange(null, null, servicio, "visiblePortugues");
	}
	public void refrescaFilaTemplate(CServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
}
