package com.pricing.viewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Listbox;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CServicio;

public class servicioVM {
	//====================
		private DecimalFormat df;
		private DecimalFormatSymbols simbolos;
	//====================
	/**
	 * ATRIBUTOS
	 */
	private CServicio oServicioNuevo;
	private CServicio oServicioUpdate;
	private CServicioDAO servicioDao;
	private ArrayList<CServicio> listaServicios;
	/**
	 * GETTER AND SETTER
	 */
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
	public CServicio getoServicioNuevo() {
		return oServicioNuevo;
	}
	public void setoServicioNuevo(CServicio oServicioNuevo) {
		this.oServicioNuevo = oServicioNuevo;
	}
	public CServicio getoServicioUpdate() {
		return oServicioUpdate;
	}
	public void setoServicioUpdate(CServicio oServicioUpdate) {
		this.oServicioUpdate = oServicioUpdate;
	}
	/**
	 * METODOS Y FUNCIONES DE LA CLASE
	 */
	@Init
	public void initVM()
	{
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		/**Inicializando los objetos**/
		oServicioNuevo=new CServicio();
		oServicioUpdate=new CServicio();
		servicioDao=new CServicioDAO();
		listaServicios=new ArrayList<CServicio>();
		/**Obtencion de las etiquetas de la base de datos**/
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaServicios(servicioDao.getListaServicios());
	}
	@Command
	public void insertarServicio(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
		boolean correcto=servicioDao.isOperationCorrect(servicioDao.insertarServicio(oServicioNuevo));
		if(correcto)
		{
			oServicioNuevo=new CServicio();
			Clients.showNotification("El servicio se inserto correctamente",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		}else{
			Clients.showNotification("El servicio no se inserto",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",2700);
		}
	}
	public boolean validoParaInsertar(Component comp)
	{
		boolean valido=true;
		if(oServicioNuevo.getcServicioIndioma1().equals("")||
				oServicioNuevo.getcServicioIndioma2().equals("")||
				oServicioNuevo.getcServicioIndioma3().equals(""))
		{
			valido=false;
			Clients.showNotification("Debe de existir un nombre de servicio en todos los idiomas",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}else if(oServicioNuevo.getcDescripcionIdioma1().equals("")||
				oServicioNuevo.getcDescripcionIdioma2().equals("")||
				oServicioNuevo.getcDescripcionIdioma3().equals(""))
		{
			valido=false;
			Clients.showNotification("Debe de existir la descripcion del servicio en todos los idiomas",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}
		return valido;
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
		oServicioUpdate.setEditable(false);
		refrescaFilaTemplate(oServicioUpdate);
		oServicioUpdate=s;
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
	@NotifyChange({"oServicioNuevo"})
	public void selectRestricciones(@BindingParam("restriccion")String rest)
	{
		if(rest.equals("sub_servicios"))
		{
			oServicioNuevo.setbEstado(false);
			oServicioNuevo.setcRestriccionNum(0);
			oServicioNuevo.setcRestriccionYesNo(0);
			oServicioNuevo.setnPrecioServicio(0);
			oServicioNuevo.setnPrecioServicio_text(df.format(0));
			oServicioNuevo.setcDescripcionIdioma1("Tiene Sub Servicios");
			oServicioNuevo.setcDescripcionIdioma2("Tiene Sub Servicios");
			oServicioNuevo.setcDescripcionIdioma3("Tiene Sub Servicios");
			oServicioNuevo.setDisabledConSubServicio(true);
			oServicioNuevo.setColor_disabled(oServicioNuevo.COLOR_DISABLED);
		}else if(rest.equals("si_no")){
			oServicioNuevo.setbEstado(true);
			oServicioNuevo.setcRestriccionNum(0);
			oServicioNuevo.setcRestriccionYesNo(1);
			oServicioNuevo.setDisabledConSubServicio(false);
			oServicioNuevo.setColor_disabled(oServicioNuevo.COLOR_NO_DISABLED);
		}else if(rest.equals("numerica")){
			oServicioNuevo.setbEstado(true);
			oServicioNuevo.setcRestriccionNum(1);
			oServicioNuevo.setcRestriccionYesNo(0);
			oServicioNuevo.setDisabledConSubServicio(false);
			oServicioNuevo.setColor_disabled(oServicioNuevo.COLOR_NO_DISABLED);
		}
	}
	@Command
	@NotifyChange({"oServicioNuevo"})
	public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("componente")Component comp)
	{
		if(!isNumberDouble(precio))
		{
			oServicioNuevo.setnPrecioServicio_text(df.format(0));
			Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		else
		{
			oServicioNuevo.setnPrecioServicio(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
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
	public boolean isNumberDouble(String cad)
	{
		try
		 {
		   Double.parseDouble(cad);
		   return true;
		 }
		 catch(NumberFormatException nfe)
		 {
		   return false;
		 }
	}
	public void refrescaFilaTemplate(CServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
}
