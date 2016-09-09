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
	/**
	 * GETTER AND SETTER
	 */
	
	public CServicioDAO getServicioDao() {
		return servicioDao;
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
		subServicio.setEditable(false);
		refrescaFilaTemplate(subServicio);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=servicioDao.isOperationCorrect(servicioDao.modificarSubServicio(subServicio));
//		initVM();
//		System.out.println("-->"+etiqueta.getCodEtiqueta());
		
	}
	@Command
	 public void Editar(@BindingParam("subServicio") CSubServicio s ) 
	{
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
	@NotifyChange({ "visibleGeneral", "visibleDescripcion" })
	public void pasosInsertar(@BindingParam("Opcion") String idOpcion) {
		if (idOpcion.equals("btnGeneral")) {
			visibleGeneral = true;
			visibleDescripcion = false;
		} else {
			visibleGeneral = false;
			visibleDescripcion = true;
		}
	}
	public void refrescaFilaTemplate(CSubServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
}