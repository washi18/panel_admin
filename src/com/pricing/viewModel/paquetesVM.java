package com.pricing.viewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CPaqueteDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.model.CDestino;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CPaquete;
import com.pricing.model.CServicio;

public class paquetesVM 
{
	//====================
		private DecimalFormat df;
		private DecimalFormatSymbols simbolos;
	//====================
	/**
	 * ATRIBUTOS
	 */
	private CPaquete oPaquete;
	private CPaqueteDAO paqueteDao;
	private ArrayList<CPaquete> listaPaquetes;
	private boolean visibleGeneral=true;
	private boolean visibleDescripcion=false;
	private boolean select_manejo;
	private CDestino oDestino;
	private CDestinoDAO destinoDao;
	private CServicioDAO servicioDao;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CServicio> listaServicios;
	/**
	 * GETTER AND SETTER
	 */
	public CPaquete getoPaquete() {
		return oPaquete;
	}
	
	public void setoPaquete(CPaquete oPaquete) {
		this.oPaquete = oPaquete;
	}
	public CPaqueteDAO getPaqueteDao() {
		return paqueteDao;
	}
	public void setPaqueteDao(CPaqueteDAO paqueteDao) {
		this.paqueteDao = paqueteDao;
	}
	public ArrayList<CPaquete> getListaPaquetes() {
		return listaPaquetes;
	}
	public void setListaPaquetes(ArrayList<CPaquete> listaPaquetes) {
		this.listaPaquetes = listaPaquetes;
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
	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}

	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}

	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	public CDestinoDAO getDestinoDao() {
		return destinoDao;
	}

	public void setDestinoDao(CDestinoDAO destinoDao) {
		this.destinoDao = destinoDao;
	}

	public CServicioDAO getServicioDao() {
		return servicioDao;
	}

	public void setServicioDao(CServicioDAO servicioDao) {
		this.servicioDao = servicioDao;
	}

	public CDestino getoDestino() {
		return oDestino;
	}

	public void setoDestino(CDestino oDestino) {
		this.oDestino = oDestino;
	}

	public boolean isSelect_manejo() {
		return select_manejo;
	}

	public void setSelect_manejo(boolean select_manejo) {
		this.select_manejo = select_manejo;
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
		select_manejo=false;
		/**Inicializando los objetos**/
		oDestino=new CDestino();
		oPaquete=new CPaquete();
		servicioDao=new CServicioDAO();
		destinoDao=new CDestinoDAO();
		paqueteDao=new CPaqueteDAO();
		listaPaquetes=new ArrayList<CPaquete>();
		listaDestinos=new ArrayList<CDestino>();
		listaServicios=new ArrayList<CServicio>();
		/**Obtencion de los paquetes existente desde la base de datos**/
		paqueteDao.asignarListaPaquetes(paqueteDao.recuperarPaquetesBD());
		setListaPaquetes(paqueteDao.getListaPaquetes());
		/**Obtencion de los servcios desde la base de datos**/
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
		/**Obtencion de los destinos desde la base de datos**/
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
	}
	@Command
	@NotifyChange({"oDestino","oPaquete","listaDestinos",
			"listaPaquetes","listaServicios"})
	public void insertarPaquete(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
		oPaquete.setnPrecioDos(oPaquete.getnPrecioUno());
		oPaquete.setnPrecioTres(oPaquete.getnPrecioUno());
		oPaquete.setnPrecioCuatro(oPaquete.getnPrecioUno());
		oPaquete.setnPrecioCinco(oPaquete.getnPrecioUno());
		if(oPaquete.isManejo_camino_inca())
		{
			oPaquete.setcTituloIdioma1(oPaquete.getTitulo());
			oPaquete.setcTituloIdioma2(oPaquete.getTitulo());
			oPaquete.setcTituloIdioma3(oPaquete.getTitulo());
			oPaquete.setcTituloIdioma4(oPaquete.getTitulo());
			oPaquete.setcTituloIdioma5(oPaquete.getTitulo());
			oPaquete.setcDisponibilidad("CAMINO_INKA");
			oPaquete.setnDiaCaminoInka(0);
			/**Se procede a insertar el paquete**/
			String codPaquete=paqueteDao.recuperarCodigoPaquete(paqueteDao.insertarPaquete(oPaquete));
			for(CServicio servicio:listaServicios)
			{
				if(servicio.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteServicio(codPaquete, servicio.getnServicioCod()));
				}
			}
		}else if(oPaquete.isManejo_propio())
		{
			oPaquete.setcTituloIdioma1(oPaquete.getTitulo());
			oPaquete.setcTituloIdioma4("");
			oPaquete.setcTituloIdioma5("");
			oPaquete.setcDisponibilidad("MANEJO_PROPIO");
			/**Se procede a insertar el paquete**/
			String codPaquete=paqueteDao.recuperarCodigoPaquete(paqueteDao.insertarPaquete(oPaquete));
			for(CDestino destino:listaDestinos)
			{
				if(destino.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteDestino(codPaquete, destino.getnDestinoCod(),destino.getnNoches()));
				}
			}
			for(CServicio servicio:listaServicios)
			{
				if(servicio.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteServicio(codPaquete, servicio.getnServicioCod()));
				}
			}
			boolean r=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(codPaquete));
		}else if(oPaquete.isManejo_normal())
		{
			oPaquete.setcTituloIdioma1(oPaquete.getTitulo());
			oPaquete.setcTituloIdioma4("");
			oPaquete.setcTituloIdioma5("");
			oPaquete.setcDisponibilidad("MANEJO_NORMAL");
			oPaquete.setnDiaCaminoInka(0);
			/**Se procede a insertar el paquete**/
			String codPaquete=paqueteDao.recuperarCodigoPaquete(paqueteDao.insertarPaquete(oPaquete));
			for(CDestino destino:listaDestinos)
			{
				if(destino.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteDestino(codPaquete, destino.getnDestinoCod(),destino.getnNoches()));
				}
			}
			for(CServicio servicio:listaServicios)
			{
				if(servicio.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteServicio(codPaquete, servicio.getnServicioCod()));
				}
			}
			boolean r=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(codPaquete));
		}
		Clients.showNotification("El paquete se inserto correctamente", Clients.NOTIFICATION_TYPE_INFO, comp, "before_start", 2700);
		/**Inicializando los objetos**/
		oDestino=new CDestino();
		oPaquete=new CPaquete();
		/**Obtencion de los paquetes existente desde la base de datos**/
		paqueteDao.asignarListaPaquetes(paqueteDao.recuperarPaquetesBD());
		setListaPaquetes(paqueteDao.getListaPaquetes());
		/**Obtencion de los servcios desde la base de datos**/
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
		/**Obtencion de los destinos desde la base de datos**/
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
	}
	public boolean validoParaInsertar(Component comp)
	{
		boolean valido=true;
		if(oPaquete.isManejo_camino_inca())
		{
			if(oPaquete.getnPrecioUno().doubleValue()==0)
			{
				valido=false;
				Clients.showNotification("El precio del paquete no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else
			{
				boolean flag=false;
				for(CServicio servicio:listaServicios)
				{
					if(servicio.isSeleccionado())
					{
						flag=true;
						break;
					}
				}
				if(!flag)
				{
					valido=false;
					Clients.showNotification("Debe de escoger por lo menos un servicio", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
		}else
		{
			if(oPaquete.getcTituloIdioma1().equals("") ||
					oPaquete.getcTituloIdioma2().equals("")||
					oPaquete.getcTituloIdioma3().equals(""))
			{
				valido=false;
				Clients.showNotification("El nuevo paquete debe tener un nombre en todos los idomas", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else if(oPaquete.getnPrecioUno().doubleValue()==0)
			{
				valido=false;
				Clients.showNotification("El paquete debe tener un precio significativo, no $ 0.00.", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else if(oPaquete.getnNoches()==0){
				valido=false;
				Clients.showNotification("El paquete debe tener un numero de dias y noches", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else
			{
				boolean flag=false;
				for(CServicio servicio:listaServicios)
				{
					if(servicio.isSeleccionado())
					{
						flag=true;
						break;
					}
				}
				if(!flag)
				{
					valido=false;
					Clients.showNotification("Debe de escoger por lo menos un servicio", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
				if(valido)
				{
					flag=false;
					for(CDestino destino:listaDestinos)
					{
						if(destino.isSeleccionado())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						valido=false;
						Clients.showNotification("Debe de escoger por lo menos un destino", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				}
			}
			if(valido)
			{
				if(oPaquete.isManejo_propio())
				{
					if(oPaquete.isManejo_propio_ConCaminoInka())
						if(oPaquete.getnDiaCaminoInka()==0)
						{
							valido=false;
							Clients.showNotification("Tiene que especificar en que dia del tour se va a realizar el camino inka", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
						}
				}
			}
		}
		return valido;
	}
	@Command
	public void actualizarPaquete(@BindingParam("paquete")CPaquete paquete)
	{
		paquete.setEditable(false);
		refrescaFilaTemplate(paquete);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=paqueteDao.isOperationCorrect(paqueteDao.modificarPaquete(paquete));
//		initVM();
//		System.out.println("-->"+etiqueta.getCodEtiqueta());
		
	}
	@Command
	 public void Editar(@BindingParam("paquete") CPaquete p) 
	{
		oPaquete.setEditable(false);
		refrescaFilaTemplate(oPaquete);
		oPaquete=p;
		//le damos estado editable
		p.setEditable(!p.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(p);
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
	@NotifyChange({"oPaquete"})
	public void manejo_propio_CaminoInka(@BindingParam("opcion")String opcion)
	{
		if(opcion.equals("con_camino_inka"))
			oPaquete.setManejo_propio_ConCaminoInka(true);
		else
		{
			oPaquete.setnDiaCaminoInka(0);
			oPaquete.setManejo_propio_ConCaminoInka(false);
		}
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("componente")Component comp)
	{
		if(!isNumberDouble(precio))
		{
			oPaquete.setnPrecio_text(df.format(0));
			Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		else
		{
			oPaquete.setnPrecioUno(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
	}
	@Command
	public void changePrecios_update(@BindingParam("precio")String precio,@BindingParam("componente")Component comp,@BindingParam("servicio")CServicio servicio)
	{
		if(!isNumberDouble(precio))
		{
			servicio.setnPrecioServicio_text(df.format(servicio.getnPrecioServicio().doubleValue()));
			Clients.showNotification("Ingrese valores numericos para poder modificar el precio",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
		}
		else
		{
			servicio.setnPrecioServicio(Double.parseDouble(df.format(Double.parseDouble(precio))));
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
	@NotifyChange({"oDestino","oPaquete"})
	public void selectDestinos(@BindingParam("destino")CDestino destino)
	{
		if(destino.isSeleccionado())
		{
			destino.setSeleccionado(false);
			oPaquete.setnNoches(oPaquete.getnNoches()-destino.getnNoches());
			if(oPaquete.getnNoches()!=0)
				oPaquete.setnDias(oPaquete.getnNoches()+1);
			else
				oPaquete.setnDias(0);
			oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
			destino.setnNoches(0);
		}
		else
		{
			destino.setSeleccionado(true);
			oDestino=destino;
		}
		BindUtils.postNotifyChange(null, null, destino, "seleccionado");
	}
	@Command
	@NotifyChange("oPaquete")
	public void determinarNroNochesDestino()
	{
		oPaquete.setnNoches(oPaquete.getnNoches()+oDestino.getnNoches());
		oPaquete.setnDias(oPaquete.getnNoches()+1);
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	public void selectServicios(@BindingParam("servicio")CServicio servicio)
	{
		if(servicio.isSeleccionado())
			servicio.setSeleccionado(false);
		else
			servicio.setSeleccionado(true);
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
	@Command
	@NotifyChange({"select_manejo","oPaquete"})
	public void select_manejo_paquete(@BindingParam("opcion")String opcion)
	{
		select_manejo=true;
		if(opcion.equals("CAMINO_INKA"))
		{
			oPaquete.setManejo_camino_inca(true);
			oPaquete.setManejo_propio(false);
			oPaquete.setManejo_normal(false);
		}
		else if(opcion.equals("MANEJO_PROPIO"))
		{
			oPaquete.setManejo_camino_inca(false);
			oPaquete.setManejo_propio(true);
			oPaquete.setManejo_normal(false);
		}
		else if(opcion.equals("MANEJO_NORMAL"))
		{
			oPaquete.setManejo_camino_inca(false);
			oPaquete.setManejo_propio(false);
			oPaquete.setManejo_normal(true);
		}
		inicializarOpcionesPaquete();
	}
	public void inicializarOpcionesPaquete()
	{
		if(oPaquete.isManejo_camino_inca())
		{
			oPaquete.setnDias(4);
			oPaquete.setnNoches(3);
			oPaquete.setTitulo("CAMINO INKA CLASICO 4 DIAS Y 3 NOCHES");
		}else if(oPaquete.isManejo_propio())
		{
			oPaquete.setnNoches(0);
			oPaquete.setnDias(0);
			for(CDestino destino:listaDestinos)
			{
				if(destino.isSeleccionado())
					if(destino.getnNoches()!=0)
						oPaquete.setnNoches(oPaquete.getnNoches()+destino.getnNoches());
			}
			if(oPaquete.getnNoches()!=0)
				oPaquete.setnDias(oPaquete.getnNoches()+1);
			oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
		}else if(oPaquete.isManejo_normal())
		{
			
		}
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void asignarNamePaquete()
	{
		oPaquete.setcTituloIdioma1(oPaquete.getcTituloIdioma1().toUpperCase());
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idIdioma,@BindingParam("paquete")CPaquete paquete)
	{
		if(idIdioma.equals("Espanol"))
		{
			paquete.setVisibleEspanol(true);
			paquete.setVisibleIngles(false);
			paquete.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Ingles"))
		{
			paquete.setVisibleEspanol(false);
			paquete.setVisibleIngles(true);
			paquete.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Portugues"))
		{
			paquete.setVisibleEspanol(false);
			paquete.setVisibleIngles(false);
			paquete.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, paquete, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, paquete, "visibleIngles");
		BindUtils.postNotifyChange(null, null, paquete, "visiblePortugues");
	}
	public void refrescaFilaTemplate(CPaquete p)
	{
		BindUtils.postNotifyChange(null, null, p, "editable");
	}
}
