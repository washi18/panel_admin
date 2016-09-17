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
	private boolean visibleEspaniol=false;
	private boolean visiblePortugues=false;
	private boolean visibleIngles=false;
	private boolean visibleIdioma4=false;
	private boolean visibleIdioma5=false;
	private boolean visiblecmbIdiomas=false;
	private boolean visiblebtnActualizar=false;
	
	private boolean visibleIdiomaEspaniol=true;
	private boolean visibleIdiomaIngles=false;
	private boolean visibleIdiomaPortugues=false;
	private boolean visibleIdiomaCuatro=false;
	private boolean visibleIdiomaCinco=false;
	
	private boolean visibleEditarRespons=false;
	/**
	 * GETTER AND SETTER
	 */
	
	public CServicio getoServicio() {
		return oServicio;
	}
	public boolean isVisibleEditarRespons() {
		return visibleEditarRespons;
	}
	public void setVisibleEditarRespons(boolean visibleEditarRespons) {
		this.visibleEditarRespons = visibleEditarRespons;
	}
	public boolean isVisibleIdiomaEspaniol() {
		return visibleIdiomaEspaniol;
	}
	public void setVisibleIdiomaEspaniol(boolean visibleIdiomaEspaniol) {
		this.visibleIdiomaEspaniol = visibleIdiomaEspaniol;
	}
	public boolean isVisibleIdiomaIngles() {
		return visibleIdiomaIngles;
	}
	public void setVisibleIdiomaIngles(boolean visibleIdiomaIngles) {
		this.visibleIdiomaIngles = visibleIdiomaIngles;
	}
	public boolean isVisibleIdiomaPortugues() {
		return visibleIdiomaPortugues;
	}
	public void setVisibleIdiomaPortugues(boolean visibleIdiomaPortugues) {
		this.visibleIdiomaPortugues = visibleIdiomaPortugues;
	}
	public boolean isVisibleIdiomaCuatro() {
		return visibleIdiomaCuatro;
	}
	public void setVisibleIdiomaCuatro(boolean visibleIdiomaCuatro) {
		this.visibleIdiomaCuatro = visibleIdiomaCuatro;
	}
	public boolean isVisibleIdiomaCinco() {
		return visibleIdiomaCinco;
	}
	public void setVisibleIdiomaCinco(boolean visibleIdiomaCinco) {
		this.visibleIdiomaCinco = visibleIdiomaCinco;
	}
	public boolean isVisibleEspaniol() {
		return visibleEspaniol;
	}
	public void setVisibleEspaniol(boolean visibleEspaniol) {
		this.visibleEspaniol = visibleEspaniol;
	}
	public boolean isVisiblePortugues() {
		return visiblePortugues;
	}
	public void setVisiblePortugues(boolean visiblePortugues) {
		this.visiblePortugues = visiblePortugues;
	}
	public boolean isVisibleIngles() {
		return visibleIngles;
	}
	public void setVisibleIngles(boolean visibleIngles) {
		this.visibleIngles = visibleIngles;
	}
	public boolean isVisibleIdioma4() {
		return visibleIdioma4;
	}
	public void setVisibleIdioma4(boolean visibleIdioma4) {
		this.visibleIdioma4 = visibleIdioma4;
	}
	public boolean isVisibleIdioma5() {
		return visibleIdioma5;
	}
	public void setVisibleIdioma5(boolean visibleIdioma5) {
		this.visibleIdioma5 = visibleIdioma5;
	}
	public boolean isVisiblecmbIdiomas() {
		return visiblecmbIdiomas;
	}
	public void setVisiblecmbIdiomas(boolean visiblecmbIdiomas) {
		this.visiblecmbIdiomas = visiblecmbIdiomas;
	}
	public boolean isVisiblebtnActualizar() {
		return visiblebtnActualizar;
	}
	public void setVisiblebtnActualizar(boolean visiblebtnActualizar) {
		this.visiblebtnActualizar = visiblebtnActualizar;
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
	@NotifyChange({"visibleEspaniol","visibleIngles","visiblePortugues","visiblebtnActualizar","visibleIdioma4","visibleIdioma5","visiblecmbIdiomas"})
	public void actualizarServicio(@BindingParam("servicio")CServicio servicio)
	{	
		visiblecmbIdiomas=visiblebtnActualizar=visibleEspaniol=visibleIngles=visiblePortugues=visibleIdioma4=visibleIdioma5=false;
		System.out.println("--> "+servicio);
		servicio.setEditable(false);
		refrescaFilaTemplate(servicio);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=servicioDao.isOperationCorrect(servicioDao.modificarServicio(servicio));
//		initVM();
//		System.out.println("-->"+etiqueta.getCodEtiqueta());
		
	}
	@Command
	@NotifyChange({"oServicio","visiblecmbIdiomas","visiblebtnActualizar","visibleEditarRespons"})
	 public void Editar(@BindingParam("servicio") CServicio s ) 
	{
		visiblecmbIdiomas=visiblebtnActualizar=visibleEditarRespons=true;
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
	@NotifyChange({"visibleEspaniol","visibleIngles","visiblePortugues","visibleIdioma4","visibleIdioma5"})
	public void EditarIdiomas(@BindingParam("idioma")String idIdioma){
		if(idIdioma.equals("cmbEspañol")){visibleEspaniol=true; visibleIngles=visiblePortugues=visibleIdioma4=visibleIdioma5=false;}
		else if(idIdioma.equals("cmbIngles")){visibleIngles=true; visibleEspaniol=visiblePortugues=visibleIdioma4=visibleIdioma5=false;}
		else if(idIdioma.equals("cmbPortugues")){visiblePortugues=true; visibleIngles=visibleEspaniol=visibleIdioma4=visibleIdioma5=false;}
		else if(idIdioma.equals("cmbIdioma4")){visibleIdioma4=true; visibleIngles=visiblePortugues=visibleEspaniol=visibleIdioma5=false;}
		else if(idIdioma.equals("cmbIdioma5")){visibleIdioma5=true; visibleIngles=visiblePortugues=visibleIdioma4=visibleEspaniol=false;}
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
