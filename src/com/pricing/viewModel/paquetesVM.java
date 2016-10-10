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
	private CDestinoDAO destinoDao;
	private CServicioDAO servicioDao;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CServicio> listaServicios;
	private int nroDestinosSelect;
	private int ordenDesSelect;
	private boolean conDestino;
	private boolean sinDestino;
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
	public boolean isSelect_manejo() {
		return select_manejo;
	}

	public void setSelect_manejo(boolean select_manejo) {
		this.select_manejo = select_manejo;
	}

	public boolean isConDestino() {
		return conDestino;
	}

	public void setConDestino(boolean conDestino) {
		this.conDestino = conDestino;
	}

	public boolean isSinDestino() {
		return sinDestino;
	}

	public void setSinDestino(boolean sinDestino) {
		this.sinDestino = sinDestino;
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
		nroDestinosSelect=0;
		ordenDesSelect=0;
		select_manejo=false;
		conDestino=false;
		sinDestino=true;
		/**Inicializando los objetos**/
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
			"listaPaquetes","listaServicios","conDestino","sinDestino"})
	public void insertarPaquete(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
//		System.out.println("hay "+nroDestinosSelect+" destinos");
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
			if(conDestino)
			{
				if(oPaquete.isManejoPropio_conCaminoInka())
				{
					//Calculamos en que dia se efectuará el camino inka
					int iter=1;
					int nroNoches=0;
					boolean seEncontroCaminoInka=false;
					while(iter<=nroDestinosSelect)
					{
						for(CDestino dest:listaDestinos)
						{
							if(dest.isSeleccionado() && dest.getnOrdenItinerario()==iter)
							{
								if(dest.getnCodPostal()==84 && dest.isConCaminoInka())
								{
									nroNoches+=dest.getnNoches();
									seEncontroCaminoInka=true;
								}
								else
									nroNoches+=dest.getnNoches();
								break;
							}
						}
						if(seEncontroCaminoInka)
							break;
						iter++;
					}
					oPaquete.setnDiaCaminoInka(nroNoches+1);
				}else
					oPaquete.setnDiaCaminoInka(0);
			}else
				oPaquete.setnDiaCaminoInka(0);
			/**Se procede a insertar el paquete**/
			String codPaquete=paqueteDao.recuperarCodigoPaquete(paqueteDao.insertarPaquete(oPaquete));
			for(CDestino destino:listaDestinos)
			{
				if(destino.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteDestino(codPaquete, destino.getnDestinoCod(),destino.getnNoches(),destino.getnOrdenItinerario(),destino.isConCaminoInka()));
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
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteDestino(codPaquete, destino.getnDestinoCod(),destino.getnNoches(),destino.getnOrdenItinerario(),destino.isConCaminoInka()));
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
		oPaquete=new CPaquete();
		/**Inicializando los estados nuevamente**/
		conDestino=false;
		sinDestino=true;
		nroDestinosSelect=0;
		ordenDesSelect=0;
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
			if(oPaquete.getcTituloIdioma1().equals(""))
			{
				valido=false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else if(oPaquete.getnDias()==0)
			{
				valido=false;
				Clients.showNotification("El paquete debe de tener un numero de dias", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else if(oPaquete.getnPrecioUno().doubleValue()==0 ||
					oPaquete.getnPrecioDos().doubleValue()==0 ||
					oPaquete.getnPrecioTres().doubleValue()==0 ||
					oPaquete.getnPrecioCuatro().doubleValue()==0 ||
					oPaquete.getnPrecioCinco().doubleValue()==0)
			{
				valido=false;
				Clients.showNotification("Ningun precio del paquete puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}
		}else
		{
			if(oPaquete.getcTituloIdioma1().equals(""))
			{
				valido=false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else if(oPaquete.getnPrecioUno().doubleValue()==0 ||
					oPaquete.getnPrecioDos().doubleValue()==0 ||
					oPaquete.getnPrecioTres().doubleValue()==0 ||
					oPaquete.getnPrecioCuatro().doubleValue()==0 ||
					oPaquete.getnPrecioCinco().doubleValue()==0)
			{
				valido=false;
				Clients.showNotification("Ningun precio del paquete puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else if(oPaquete.getnNoches()==0){
				valido=false;
				Clients.showNotification("El paquete debe tener un numero de dias y noches", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else
			{
				if(conDestino)
				{
					for(CDestino destino:listaDestinos)
					{
						if(destino.isSeleccionado() && destino.getnNoches()==0)
						{
							valido=false;
							Clients.showNotification("Debe especificar Cuantas noches se quedará en "+destino.getcDestino(), Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
							break;
						}
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
	public void manejo_propio_CaminoInka(@BindingParam("opcion")String opcion,@BindingParam("destino")CDestino destino)
	{
		if(opcion.equals("con_camino_inka"))
		{
			oPaquete.setManejoPropio_conCaminoInka(true);
			oPaquete.setnNoches(oPaquete.getnNoches()+4);
			oPaquete.setnDias(oPaquete.getnNoches()+1);
			destino.setConCaminoInka(true);
			destino.setSinCaminoInka(false);
			for(CDestino dest:listaDestinos)
			{
				if(!dest.equals(destino) && dest.isSeleccionado() && dest.getnCodPostal()==84)
				{
					dest.setConCaminoInka(false);
					dest.setSinCaminoInka(true);
					dest.setPuedeCaminoInka(false);
					BindUtils.postNotifyChange(null, null, dest, "conCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "sinCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
			BindUtils.postNotifyChange(null, null, destino, "conCaminoInka");
			BindUtils.postNotifyChange(null, null, destino, "sinCaminoInka");
		}
		else
		{
			oPaquete.setManejoPropio_conCaminoInka(false);
			oPaquete.setnNoches(oPaquete.getnNoches()-4);
			if(oPaquete.getnNoches()==0)
				oPaquete.setnDias(0);
			else
				oPaquete.setnDias(oPaquete.getnNoches()+1);
			oPaquete.setnDiaCaminoInka(0);
			for(CDestino dest:listaDestinos)
			{
				if(dest.isSeleccionado() && dest.getnCodPostal()==84)
				{
					dest.setConCaminoInka(false);
					dest.setSinCaminoInka(true);
					dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "conCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "sinCaminoInka");
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
		}
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("descuento")String descuento,@BindingParam("componente")Component comp)
	{
		if(descuento.equals("precio1"))
		{
			if(!isNumberDouble(precio))
			{
				oPaquete.setnPrecio1_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				oPaquete.setnPrecioUno(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}else if(descuento.equals("precio2"))
		{
			if(!isNumberDouble(precio))
			{
				oPaquete.setnPrecio2_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				oPaquete.setnPrecioDos(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}else if(descuento.equals("precio3"))
		{
			if(!isNumberDouble(precio))
			{
				oPaquete.setnPrecio3_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				oPaquete.setnPrecioTres(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}else if(descuento.equals("precio4"))
		{
			if(!isNumberDouble(precio))
			{
				oPaquete.setnPrecio4_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				oPaquete.setnPrecioCuatro(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}else if(descuento.equals("precio5"))
		{
			if(!isNumberDouble(precio))
			{
				oPaquete.setnPrecio5_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				oPaquete.setnPrecioCinco(Double.parseDouble(df.format(Double.parseDouble(precio))));
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
			if(oPaquete.isManejoPropio_conCaminoInka())
			{
				oPaquete.setnNoches(oPaquete.getnNoches()-destino.getnNoches());
				if(oPaquete.getnNoches()!=0)
					oPaquete.setnDias(oPaquete.getnNoches());
				else
					oPaquete.setnDias(0);
			}else
			{
				oPaquete.setnNoches(oPaquete.getnNoches()-destino.getnNoches());
				if(oPaquete.getnNoches()!=0)
					oPaquete.setnDias(oPaquete.getnNoches()+1);
				else
					oPaquete.setnDias(0);
			}
			if(oPaquete.isManejoPropio_conCaminoInka() && destino.getnCodPostal()==84 &&
					destino.isConCaminoInka())
			{
				oPaquete.setnNoches(oPaquete.getnNoches()-4);
				if(oPaquete.getnNoches()!=0)
					oPaquete.setnDias(oPaquete.getnNoches()+1);
				else
					oPaquete.setnDias(0);
				destino.setConCaminoInka(false);
				destino.setSinCaminoInka(true);
				oPaquete.setManejoPropio_conCaminoInka(false);
			}
			for(CDestino dest:listaDestinos)
			{
				if(dest.isSeleccionado() && dest.getnCodPostal()==84)
				{
					dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
			oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
			destino.setnNoches(0);
			ordenDesSelect=destino.getnOrdenItinerario();//recupero el orden des seleccionado
			destino.setnOrdenItinerario(0);
			destino.setPuedeCaminoInka(false);
		}
		else
		{
			destino.setSeleccionado(true);
			if(oPaquete.isManejo_propio() && !oPaquete.isManejoPropio_conCaminoInka())
				destino.asignaPuedeCaminoInka(destino);
			if(ordenDesSelect!=0)
			{
				destino.setnOrdenItinerario(ordenDesSelect);
				ordenDesSelect=0;
			}else
				destino.setnOrdenItinerario(++nroDestinosSelect);
		}
		BindUtils.postNotifyChange(null, null, destino, "seleccionado");
		BindUtils.postNotifyChange(null, null, destino, "puedeCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nOrdenItinerario");
		BindUtils.postNotifyChange(null, null, destino, "conCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "sinCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nNoches");
	}
	@Command
	@NotifyChange("oPaquete")
	public void determinarNroNochesDestino()
	{
		oPaquete.setnNoches(0);
		for(CDestino destino:listaDestinos)
		{
			if(destino.isSeleccionado())
				oPaquete.setnNoches(oPaquete.getnNoches()+destino.getnNoches());
		}
		if(oPaquete.getnNoches()!=0)
			oPaquete.setnDias(oPaquete.getnNoches()+1);
		else
			oPaquete.setnDias(0);
		if(oPaquete.isManejo_propio() && oPaquete.isManejoPropio_conCaminoInka())
		{
			oPaquete.setnNoches(oPaquete.getnNoches()+4);
			oPaquete.setnDias(oPaquete.getnNoches()+1);
		}
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
			if(nroDestinosSelect>0)
				for(CDestino dest:listaDestinos)
				{
					if(oPaquete.isManejoPropio_conCaminoInka())
					{
						if(dest.isConCaminoInka() && dest.isSeleccionado() && dest.getnCodPostal()==84)
							dest.setPuedeCaminoInka(true);
						else
							dest.setPuedeCaminoInka(false);
					}
					else if(dest.isSeleccionado() && dest.getnCodPostal()==84)
						dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
		}
		else if(opcion.equals("MANEJO_NORMAL"))
		{
			oPaquete.setManejo_camino_inca(false);
			oPaquete.setManejo_propio(false);
			oPaquete.setManejo_normal(true);
			if(nroDestinosSelect>0)
				for(CDestino dest:listaDestinos)
				{
					if(dest.isSeleccionado() && dest.getnCodPostal()==84)
					{
						dest.setPuedeCaminoInka(false);
						BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
					}
				}
		}
		inicializarOpcionesPaquete();
	}
	public void inicializarOpcionesPaquete()
	{
		oPaquete.setnDias(0);
		oPaquete.setnNoches(0);
		if(conDestino)
		{
			if(oPaquete.isManejo_propio())
			{
				for(CDestino destino:listaDestinos)
				{
					if(destino.isSeleccionado())
						if(destino.getnNoches()!=0)
							oPaquete.setnNoches(oPaquete.getnNoches()+destino.getnNoches());
				}
				if(oPaquete.getnNoches()!=0)
					oPaquete.setnDias(oPaquete.getnNoches()+1);
				if(oPaquete.isManejoPropio_conCaminoInka())
				{
					oPaquete.setnNoches(oPaquete.getnNoches()+4);
					oPaquete.setnDias(oPaquete.getnNoches()+1);
				}
			}else if(oPaquete.isManejo_normal())
			{
				for(CDestino destino:listaDestinos)
				{
					if(destino.isSeleccionado())
						if(destino.getnNoches()!=0)
							oPaquete.setnNoches(oPaquete.getnNoches()+destino.getnNoches());
				}
				if(oPaquete.getnNoches()!=0)
					oPaquete.setnDias(oPaquete.getnNoches()+1);
			}
		}
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	@NotifyChange({"oPaquete","sinDestino","conDestino"})
	public void select_paquete_conDestino(@BindingParam("opcion")String opcion)
	{
		oPaquete.setnNoches(0);
		oPaquete.setnDias(0);
		if(opcion.equals("sin_destino"))
		{
			sinDestino=true;conDestino=false;
		}else
		{
			sinDestino=false;conDestino=true;
			for(CDestino destino:listaDestinos)
			{
				if(destino.isSeleccionado())
					if(destino.getnNoches()!=0)
						oPaquete.setnNoches(oPaquete.getnNoches()+destino.getnNoches());
			}
			if(oPaquete.getnNoches()!=0)
				oPaquete.setnDias(oPaquete.getnNoches()+1);
			if(oPaquete.isManejo_propio() && oPaquete.isManejoPropio_conCaminoInka())
			{
				oPaquete.setnNoches(oPaquete.getnNoches()+4);
				oPaquete.setnDias(oPaquete.getnNoches()+1);
			}

		}
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void diasCaminoInka(@BindingParam("dias")int dias)
	{
		oPaquete.setnDias(dias);
		oPaquete.setnNoches(dias-1);
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void diasSinDestino(@BindingParam("dias")int dias)
	{
		oPaquete.setnDias(dias);
		oPaquete.setnNoches(dias-1);
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1()+" "+oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
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
