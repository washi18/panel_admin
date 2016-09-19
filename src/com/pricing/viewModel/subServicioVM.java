package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import com.pricing.dao.CServicioDAO;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class subServicioVM 
{
	/**
	 * ATRIBUTOS
	 */
	private CSubServicio oSubServicio;
	private CServicioDAO servicioDao;
	private ArrayList<CSubServicio> listaSubServicios;
	private boolean visibleGeneral=true;
	private boolean visibleDescripcion=false;
	private boolean visibleEditarRespons=false;
	/**
	 * GETTER AND SETTER
	 */
	
	public CServicioDAO getServicioDao() {
		return servicioDao;
	}
	public boolean isVisibleEditarRespons() {
		return visibleEditarRespons;
	}
	public void setVisibleEditarRespons(boolean visibleEditarRespons) {
		this.visibleEditarRespons = visibleEditarRespons;
	}
	public boolean isVisibleGeneral() {
		return visibleGeneral;
	}
	public void setVisibleGeneral(boolean visibleGeneral) {
		this.visibleGeneral = visibleGeneral;
	}
	public boolean isVisibleDescripcion() {
		return visibleDescripcion;
	}
	public void setVisibleDescripcion(boolean visibleDescripcion) {
		this.visibleDescripcion = visibleDescripcion;
	}
	public void setServicioDao(CServicioDAO servicioDao) {
		this.servicioDao = servicioDao;
	}
	public CSubServicio getoSubServicio() {
		return oSubServicio;
	}
	public void setoSubServicio(CSubServicio oSubServicio) {
		this.oSubServicio = oSubServicio;
	}
	public ArrayList<CSubServicio> getListaSubServicios() {
		return listaSubServicios;
	}
	public void setListaSubServicios(ArrayList<CSubServicio> listaSubServicios) {
		this.listaSubServicios = listaSubServicios;
	}
	/**
	 * METODOS Y FUNCIONES DE LA CLASE
	 */
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		oSubServicio=new CSubServicio();
		servicioDao=new CServicioDAO();
		listaSubServicios=new ArrayList<CSubServicio>();
		/**Obtencion de las etiquetas de la base de datos**/
		servicioDao.asignarListaSubServicios(servicioDao.recuperarSubServiciosBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaSubServicios(servicioDao.getListaSubServicios());
	}
	@Command
	public void actualizarSubServicio(@BindingParam("subServicio")CSubServicio subServicio)
	{	
//		System.out.println("--> "+servicio);
		System.out.println("-->"+subServicio.getnSubServicioCod());
	    System.out.println("-->"+subServicio.getnServicioCod());
	    System.out.println("-->"+subServicio.getcSubServicioIndioma1());
	    System.out.println("-->"+subServicio.getcDescripcionIdioma1());
	    System.out.println("-->"+subServicio.getcLink());
	    System.out.println("-->"+subServicio.getcUrlImg());
	    System.out.println("-->"+subServicio.getnPrecioServicio());
		subServicio.setEditable(false);
		refrescaFilaTemplate(subServicio);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=servicioDao.isOperationCorrect(servicioDao.modificarSubServicio(subServicio));
//		initVM();
	}
	@Command
	@NotifyChange("visibleEditarRespons")
	 public void Editar(@BindingParam("subServicio") CSubServicio s ) 
	{
		visibleEditarRespons=true;
		oSubServicio.setEditable(false);
		refrescaFilaTemplate(oSubServicio);
		oSubServicio=s;
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
	public void cambioIdiomas(@BindingParam("idioma")String idIdioma,@BindingParam("subServicio")CSubServicio servicio)
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
	public void refrescaFilaTemplate(CSubServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
}
