package com.pricing.viewModel;

import java.io.File;
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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Messagebox;

import com.pricing.dao.CServicioDAO;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;
import com.pricing.util.ScannUtil;

public class subServicioVM 
{
	/**
	 * ATRIBUTOS
	 */
	private CSubServicio oSubServicioNew;
	private CSubServicio oSubServicioUpdate;
	private ArrayList<CServicio> listaServiciosNew;
	private CServicioDAO servicioDao;
	private ArrayList<CSubServicio> listaSubServicios;
	private boolean visibleGeneral=true;
	private boolean visibleDescripcion=false;
	private boolean visibleEditarRespons=false;
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	private String NombreServicio;
	/**
	 * GETTER AND SETTER
	 */
	
	public CServicioDAO getServicioDao() {
		return servicioDao;
	}
	public String getNombreServicio() {
		return NombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		NombreServicio = nombreServicio;
	}
	public ArrayList<CServicio> getListaServiciosNew() {
		return listaServiciosNew;
	}
	public void setListaServiciosNew(ArrayList<CServicio> listaServiciosNew) {
		this.listaServiciosNew = listaServiciosNew;
	}
	public CSubServicio getoSubServicioNew() {
		return oSubServicioNew;
	}
	public void setoSubServicioNew(CSubServicio oSubServicioNew) {
		this.oSubServicioNew = oSubServicioNew;
	}
	public CSubServicio getoSubServicioUpdate() {
		return oSubServicioUpdate;
	}
	public void setoSubServicioUpdate(CSubServicio oSubServicioUpdate) {
		this.oSubServicioUpdate = oSubServicioUpdate;
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
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		oSubServicioNew=new CSubServicio();
		listaServiciosNew=new ArrayList<CServicio>();
		servicioDao=new CServicioDAO();
		listaSubServicios=new ArrayList<CSubServicio>();
		/**Obtencion de las etiquetas de la base de datos**/
		servicioDao.asignarListaSubServicios(servicioDao.recuperarSubServiciosBD());
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosconSubServiciosBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaSubServicios(servicioDao.getListaSubServicios());
		setListaServiciosNew(servicioDao.getListaServicios());
	}
	
	@Command
	@NotifyChange({"oSubServicioNew"})
	public void InsertarSubServicio(@BindingParam("componente")Component componente)
	{
		oSubServicioNew.setcSubServicioIndioma4("cuatro");
		oSubServicioNew.setcSubServicioIndioma5("cinco");
		oSubServicioNew.setcDescripcionIdioma4("d4");
		oSubServicioNew.setcDescripcionIdioma5("d5");
		/**Empezamos realizando las validaciones respectivas**/
		if(oSubServicioNew.getcSubServicioIndioma1().equals("") ||  oSubServicioNew.getcSubServicioIndioma2().equals("") || oSubServicioNew.getcSubServicioIndioma3().equals(""))//Nombre del subServicio
		{
			Clients.showNotification("Es necesario poner el nombre del sub servicio en todos los idiomas", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oSubServicioNew.getcDescripcionIdioma1().equals("") ||  oSubServicioNew.getcDescripcionIdioma2().equals("") || oSubServicioNew.getcDescripcionIdioma3().equals(""))
		{
			Clients.showNotification("Es necesario poner la descripcion del sub servicio en todos los idiomas", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oSubServicioNew.getnServicioCod()==0)
		{
			Clients.showNotification("Debe seleccionar un servicio al cual pertenecera", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oSubServicioNew.getcLink().equals(""))
		{
			Clients.showNotification("Es necesario poner la direccion URL del sub servicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oSubServicioNew.getcUrlImg().equals(""))
		{
			Clients.showNotification("Es necesario insertar una imagen del sub servicio", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oSubServicioNew.getnPrecioServicio().doubleValue()==0.00 || oSubServicioNew.getnPrecioServicio().doubleValue()<0.00)
		{
			Clients.showNotification("El precio de un sub servicio no puede ser $ 0.00, mucho menos un valor negativo", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}
		/**Una vez validado los datos necesarios se procede a insertar el nuevo sub Servicio**/
		boolean correcto=servicioDao.isOperationCorrect(servicioDao.insertarSubServicio(oSubServicioNew));
		if(correcto)
		{ 
			oSubServicioNew=new CSubServicio();
			Clients.showNotification("El Nuevo Sub Servicio fue insertado correctamente", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
		}
		else
			Clients.showNotification("El Nuevo Sub Servicio fue insertado", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
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
	}
	@Command
	@NotifyChange("visibleEditarRespons")
	 public void Editar(@BindingParam("subServicio") CSubServicio s ) 
	{
		visibleEditarRespons=true;
		oSubServicioNew.setEditable(false);
		refrescaFilaTemplate(oSubServicioNew);
		oSubServicioNew=s;
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
	
	@Command
	@NotifyChange({"oSubServicioNew"})
	public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("componente")Component componente)
	{
		if(!isNumberDouble(precio))
		{
			oSubServicioNew.setnPrecioServicio_text(df.format(0.00));
			Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start", 2700);
		}
		else
		{
				oSubServicioNew.setnPrecioServicio(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
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
	@NotifyChange("oSubServicioNew")
	public void  asignacion_servicio(@BindingParam("servicio")int codServicio){
		oSubServicioNew.setnServicioCod(codServicio);
	}
	
	@Command
	@NotifyChange("oSubServicioNew")
	public void uploadImagen() {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) {
						org.zkoss.util.media.Media media = event.getMedia();
						if (media instanceof org.zkoss.image.Image) {
							org.zkoss.image.Image img = (org.zkoss.image.Image) media;
							//Con este metodo(uploadFile) de clase guardo la imagen en la ruta del servidor
				            boolean b=ScannUtil.uploadFile(img);
				            //================================
				            //Una vez creado el nuevo nombre de archivo de imagen se procede a cambiar el nombre
				            String urlImagen=ScannUtil.getPathImagensSubServicio()+img.getName();
				            File imgGuardado=new File(urlImagen);
				            if(imgGuardado.exists())
				            {	System.out.println("El archivo existe");
				            	if(imgGuardado.delete())System.out.println("Se elimino el archivo existente");
				            }
				            asignarUrlImagenSubServicio(urlImagen);
				            Clients.showNotification(img.getName()+" Se inserto",Clients.NOTIFICATION_TYPE_INFO,null,"top_center",3000);
						} else {
							Messagebox.show(media+"Error", "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
	}
	public void asignarUrlImagenSubServicio(String url)
	{
		System.out.println("==>:::"+url);
		oSubServicioNew.setcUrlImg(url);
		//pasajero.setcUrlMostrarDocumento("/DocumentosScanneados/"+url);
		BindUtils.postNotifyChange(null, null, oSubServicioNew,"cUrlImg");
		//BindUtils.postNotifyChange(null, null, pasajero,"cUrlMostrarDocumento");
	}
	public void refrescaFilaTemplate(CSubServicio s)
	{
		BindUtils.postNotifyChange(null, null, s, "editable");
	}
}
