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
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CServicio;
import com.pricing.util.ScannUtil;

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
		servicioDao.asignarListaServicios(servicioDao.recuperarTodosServiciosBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaServicios(servicioDao.getListaServicios());
	}
	@Command
	@NotifyChange({"oServicioNuevo"})
	public void insertarServicio(@BindingParam("componente")Component comp)
	{
		oServicioNuevo.setcServicioIndioma1(oServicioNuevo.getcServicioIndioma1().toUpperCase());
		oServicioNuevo.setcServicioIndioma2(oServicioNuevo.getcServicioIndioma2().toUpperCase());
		oServicioNuevo.setcServicioIndioma3(oServicioNuevo.getcServicioIndioma3().toUpperCase());
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
		if(!oServicioNuevo.isEscogioRestriccion())//no escogio ninguna restriccion
		{
			valido=false;
			Clients.showNotification("Debe de escoger alguna restriccion",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}else if(oServicioNuevo.getcServicioIndioma1().equals("")||
				oServicioNuevo.getcServicioIndioma2().equals("")||
				oServicioNuevo.getcServicioIndioma3().equals("")){
			valido=false;
			Clients.showNotification("Debe de existir un nombre de servicio en todos los idiomas",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
		}else if(oServicioNuevo.getcRestriccionNum()==1 || oServicioNuevo.getcRestriccionYesNo()==1)
		{
			if(oServicioNuevo.getcUrlImg().equals(""))
			{
				valido=false;
				Clients.showNotification("El Servicio debe tener una imagen",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			}else if(oServicioNuevo.getcDescripcionIdioma1().equals("")||
					oServicioNuevo.getcDescripcionIdioma2().equals("")||
					oServicioNuevo.getcDescripcionIdioma3().equals(""))
			{
				valido=false;
				Clients.showNotification("Debe de existir la descripcion del servicio en todos los idiomas",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			}else if(oServicioNuevo.getcDescripcionIdioma1().equals("Tiene Sub Servicios")||
					oServicioNuevo.getcDescripcionIdioma2().equals("Tiene Sub Servicios")||
					oServicioNuevo.getcDescripcionIdioma3().equals("Tiene Sub Servicios")){
				valido=false;
				Clients.showNotification("La descripcion debe tener un contenido acorde al servicio ofrecido en todos los idiomas",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			}else if(oServicioNuevo.getnPrecioServicio().doubleValue()==0)
			{
				valido=false;
				Clients.showNotification("El precio del servicio no puede ser $ 0.00",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",2700);
			}
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
		oServicioNuevo.setEscogioRestriccion(true);
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
			oServicioNuevo.setcUrlImg("");
			BindUtils.postNotifyChange(null, null, oServicioNuevo,"cUrlImg");
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
	@Command
	public void uploadImagen(@BindingParam("componente")final Component comp) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.image.Image) {
							org.zkoss.image.Image img = (org.zkoss.image.Image) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFile(img);
				            //================================
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            String urlImagen=ScannUtil.getPathImagensSubServicios()+img.getName();
				            asignarUrlImagenServicio(img.getName());
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",2700);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenServicio(String url)
	{
		System.out.println("==>:::"+url);
		oServicioNuevo.setcUrlImg("/img/servicios/"+url);
		BindUtils.postNotifyChange(null, null, oServicioNuevo,"cUrlImg");
	}
	public void refrescaFilaTemplate(CServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
}
