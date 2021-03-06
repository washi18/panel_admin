package com.pricing.viewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CActividadDAO;
import com.pricing.dao.CDestinoDAO;
import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CPaqueteActividadDAO;
import com.pricing.dao.CPaqueteDAO;
import com.pricing.dao.CPaqueteDestinoDAO;
import com.pricing.dao.CPaqueteServicioDAO;
import com.pricing.dao.CServicioDAO;
import com.pricing.model.CActividad;
import com.pricing.model.CDestino;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CPaquete;
import com.pricing.model.CPaqueteActividad;
import com.pricing.model.CPaqueteDestino;
import com.pricing.model.CPaqueteServicio;
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
	private CPaquete oPaqueteUpdate;
	private CPaqueteDAO paqueteDao;
	private CPaqueteDestinoDAO paqueteDestinoDaoUpdate;
	private CPaqueteServicioDAO paqueteServicioDaoUpdate;
	private CPaqueteActividadDAO paqueteActividadDaoUpdate;
	private ArrayList<CPaquete> listaPaquetes;
	private boolean visibleGeneral=true;
	private boolean visibleDescripcion=false;
	private boolean select_manejo;
	private CDestinoDAO destinoDao;
	private CServicioDAO servicioDao;
	private CActividadDAO actividadDao;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CActividad> listaActividades;
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

	public ArrayList<CActividad> getListaActividades() {
		return listaActividades;
	}
	public void setListaActividades(ArrayList<CActividad> listaActividades) {
		this.listaActividades = listaActividades;
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
		oPaquete=new CPaquete();
		oPaqueteUpdate=new CPaquete();
		servicioDao=new CServicioDAO();
		destinoDao=new CDestinoDAO();
		actividadDao=new CActividadDAO();
		paqueteDao=new CPaqueteDAO();
		paqueteDestinoDaoUpdate=new CPaqueteDestinoDAO();
		paqueteServicioDaoUpdate=new CPaqueteServicioDAO();
		paqueteActividadDaoUpdate=new CPaqueteActividadDAO();
		listaPaquetes=new ArrayList<CPaquete>();
		listaDestinos=new ArrayList<CDestino>();
		listaServicios=new ArrayList<CServicio>();
		listaActividades=new ArrayList<CActividad>();
		/*****************************/
		Encryptar encrip= new Encryptar();
//		System.out.println("Aqui esta la contrase�a desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
		Execution exec = Executions.getCurrent();
		HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
	    String user=(String)ses.getAttribute("usuario");
	    String pas=(String)ses.getAttribute("clave");
	    if(user!=null && pas!=null)
	    	recuperarPaquetes();
	}
	public void recuperarPaquetes()
	{
		/**Obtencion de los paquetes existente desde la base de datos**/
		paqueteDao.asignarListaPaquetes(paqueteDao.recuperarPaquetesBD());
		setListaPaquetes(paqueteDao.getListaPaquetes());
		/**Obtencion de los servcios desde la base de datos**/
		servicioDao.asignarListaServicios(servicioDao.recuperarServiciosBD());
		setListaServicios(servicioDao.getListaServicios());
		/**Obtencion de los destinos desde la base de datos**/
		destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
		setListaDestinos(destinoDao.getListaDestinos());
		/**Obtencion de las actividades desde la base de datos**/
		actividadDao.asignarListaActividades(actividadDao.recuperarActividadesBD());
		setListaActividades(actividadDao.getListaActividades());
	}
	@Command
	public void insertarPaquete(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsertar(comp))
			return;
//		System.out.println("hay "+nroDestinosSelect+" destinos");
		if(oPaquete.isSinDescuento())
		{
			oPaquete.setnPrecioDos(oPaquete.getnPrecioUno().doubleValue());
			oPaquete.setnPrecioTres(oPaquete.getnPrecioUno().doubleValue());
			oPaquete.setnPrecioCuatro(oPaquete.getnPrecioUno().doubleValue());
			oPaquete.setnPrecioCinco(oPaquete.getnPrecioUno().doubleValue());
		}
		if(oPaquete.isManejo_camino_inca())
		{
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
			for(CActividad act:listaActividades)
			{
				if(act.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(codPaquete, act.getnActividadCod()));
				}
			}
		}else if(oPaquete.isManejo_propio())
		{
			oPaquete.setcDisponibilidad("MANEJO_PROPIO");
			if(oPaquete.isConDestino())
			{
				if(oPaquete.isManejoPropio_conCaminoInka())
				{
					//Calculamos en que dia se efectuar� el camino inka
					int iter=1;
					int nroNoches=0;
					boolean seEncontroCaminoInka=false;
					while(iter<=oPaquete.getNroDestinosSelect())
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
			for(CActividad act:listaActividades)
			{
				if(act.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(codPaquete, act.getnActividadCod()));
				}
			}
			boolean r=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(codPaquete));
		}else if(oPaquete.isManejo_normal())
		{
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
			for(CActividad act:listaActividades)
			{
				if(act.isSeleccionado())
				{
					boolean b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(codPaquete, act.getnActividadCod()));
				}
			}
			boolean r=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(codPaquete));
		}
		Clients.showNotification("El paquete se inserto correctamente", Clients.NOTIFICATION_TYPE_INFO, comp, "before_start", 2700);
		/**Inicializando los objetos**/
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
		/**Obtencion de las actividades desde la base de datos**/
		actividadDao.asignarListaActividades(actividadDao.recuperarActividadesBD());
		setListaActividades(actividadDao.getListaActividades());
		refrescarSistema();
	}
	public void refrescarSistema()
	{
		BindUtils.postNotifyChange(null, null,this,"oPaquete");
		BindUtils.postNotifyChange(null, null, this,"listaDestinos");
		BindUtils.postNotifyChange(null, null, this,"listaServicios");
		BindUtils.postNotifyChange(null, null, this,"listaPaquetes");
		BindUtils.postNotifyChange(null, null, this,"listaActividades");
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
			}else{
				if(oPaquete.isConDescuento())
				{
					if(oPaquete.getnPrecioUno().doubleValue()==0 ||
							oPaquete.getnPrecioDos().doubleValue()==0 ||
							oPaquete.getnPrecioTres().doubleValue()==0 ||
							oPaquete.getnPrecioCuatro().doubleValue()==0 ||
							oPaquete.getnPrecioCinco().doubleValue()==0)
					{
						valido=false;
						Clients.showNotification("Ningun precio del paquete puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				}else if(oPaquete.getnPrecioUno().doubleValue()==0){
					valido=false;
					Clients.showNotification("El precio del paquete no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
		}else
		{
			if(oPaquete.getcTituloIdioma1().equals(""))
			{
				valido=false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else{
				if(oPaquete.isConDescuento())
				{
					if(oPaquete.getnPrecioUno().doubleValue()==0 ||
							oPaquete.getnPrecioDos().doubleValue()==0 ||
							oPaquete.getnPrecioTres().doubleValue()==0 ||
							oPaquete.getnPrecioCuatro().doubleValue()==0 ||
							oPaquete.getnPrecioCinco().doubleValue()==0)
					{
						valido=false;
						Clients.showNotification("Ningun precio del paquete puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				}else if(oPaquete.getnPrecioUno().doubleValue()==0){
					valido=false;
					Clients.showNotification("El precio del paquete no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
			if(valido)
			{
				if(oPaquete.getnNoches()==0){
					valido=false;
					Clients.showNotification("El paquete debe tener un numero de dias y noches", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}else
				{
					if(oPaquete.isConDestino())
					{
						for(CDestino destino:listaDestinos)
						{
							if(destino.isSeleccionado() && destino.getnNoches()==0)
							{
								valido=false;
								Clients.showNotification("Debe especificar Cuantas noches se quedar� en "+destino.getcDestino(), Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
								break;
							}
						}
					}
				}
			}
		}
		return valido;
	}
	@Command
	public void actualizarPaquete(@BindingParam("componente")Component comp,@BindingParam("paquete")CPaquete paquete)
	{
		if(!validoParaActualizar(paquete,comp))
			return;
		if(paquete.isSinDescuento())
		{
			paquete.setnPrecioDos(paquete.getnPrecioUno().doubleValue());
			paquete.setnPrecioTres(paquete.getnPrecioUno().doubleValue());
			paquete.setnPrecioCuatro(paquete.getnPrecioUno().doubleValue());
			paquete.setnPrecioCinco(paquete.getnPrecioUno().doubleValue());
		}
		if(paquete.isManejo_camino_inca())
		{
			paquete.setcDisponibilidad("CAMINO_INKA");
			paquete.setnDiaCaminoInka(0);
			/**Se procede a actualizar el paquete**/
			boolean b=paqueteDao.isOperationCorrect(paqueteDao.modificarPaquete(paquete));
			for(CServicio servicio:paquete.getListaServicios())
			{
				boolean estaRegistrado=false;
				for(CPaqueteServicio ps:paquete.getListaPaqueteServicios())
				{
					if(ps.getnServicioCod()==servicio.getnServicioCod())
					{
						estaRegistrado=true;
						if(!servicio.isSeleccionado())
						{
							/**ELIMINAR**/
							paquete.setConServicio(false);
							b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteServicio(ps.getCodPaqueteServicio()));
						}
						break;
					}
				}
				if(servicio.isSeleccionado() && !estaRegistrado)
				{
					paquete.setConServicio(true);
					b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteServicio(paquete.getcPaqueteCod(), servicio.getnServicioCod()));
				}
			}
			for(CActividad actividad:paquete.getListaActividades())
			{
				boolean estaRegistrado=false;
				for(CPaqueteActividad pa:paquete.getListaPaqueteActividades())
				{
					if(pa.getnActividadCod()==actividad.getnActividadCod())
					{
						estaRegistrado=true;
						if(!actividad.isSeleccionado())
						{
							/**ELIMINAR**/
							paquete.setConActividad(false);
							b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteActividad(pa.getnPaqueteActividad()));
						}
						break;
					}
				}
				if(actividad.isSeleccionado() && !estaRegistrado)
				{
					paquete.setConActividad(true);
					b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(paquete.getcPaqueteCod(), actividad.getnActividadCod()));
				}
			}
			if(paquete.isSinDestino())
			{
				for(CPaqueteDestino pd:paquete.getListaPaqueteDestinos())
					b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
			}
		}else if(paquete.isManejo_propio())
		{
			paquete.setcDisponibilidad("MANEJO_PROPIO");
			if(paquete.isConDestino())
			{
				if(paquete.isManejoPropio_conCaminoInka())
				{
					//Calculamos en que dia se efectuar� el camino inka
					int iter=1;
					int nroNoches=0;
					boolean seEncontroCaminoInka=false;
					while(iter<=paquete.getNroDestinosSelect())
					{
						for(CDestino dest:paquete.getListaDestinos())
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
					paquete.setnDiaCaminoInka(nroNoches+1);
				}else
					paquete.setnDiaCaminoInka(0);
			}else
				paquete.setnDiaCaminoInka(0);
			/**Se procede a actualizar el paquete**/
			boolean b=paqueteDao.isOperationCorrect(paqueteDao.modificarPaquete(paquete));
			if(paquete.isConDestino())
			{
				for(CDestino destino:paquete.getListaDestinos())
				{
					boolean estaRegistrado=false;
					for(CPaqueteDestino pd:paquete.getListaPaqueteDestinos())
					{
						if(pd.getnDestinoCod()==destino.getnDestinoCod())
						{
							estaRegistrado=true;
							if(!destino.isSeleccionado())
							{
								/**ELIMINAR**/
								b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
							}else
								b=paqueteDao.isOperationCorrect(paqueteDao.modificarPaqueteDestino(pd.getCodPaqueteDestino(),destino.getnNoches(), destino.getnOrdenItinerario(),destino.isConCaminoInka()));
							break;
						}
					}
					if(destino.isSeleccionado() && !estaRegistrado)
					{
						b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteDestino(paquete.getcPaqueteCod(), destino.getnDestinoCod(),destino.getnNoches(),destino.getnOrdenItinerario(),destino.isConCaminoInka()));
					}
				}
			}
			if(paquete.isSinDestino())
			{
				for(CPaqueteDestino pd:paquete.getListaPaqueteDestinos())
					b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
			}
			for(CServicio servicio:paquete.getListaServicios())
			{
				boolean estaRegistrado=false;
				for(CPaqueteServicio ps:paquete.getListaPaqueteServicios())
				{
					if(ps.getnServicioCod()==servicio.getnServicioCod())
					{
						estaRegistrado=true;
						if(!servicio.isSeleccionado())
						{
							/**ELIMINAR**/
							paquete.setConServicio(false);
							b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteServicio(ps.getCodPaqueteServicio()));
						}
						break;
					}
				}
				if(servicio.isSeleccionado() && !estaRegistrado)
				{
					paquete.setConServicio(true);
					b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteServicio(paquete.getcPaqueteCod(), servicio.getnServicioCod()));
				}
			}
			for(CActividad actividad:paquete.getListaActividades())
			{
				boolean estaRegistrado=false;
				for(CPaqueteActividad pa:paquete.getListaPaqueteActividades())
				{
					if(pa.getnActividadCod()==actividad.getnActividadCod())
					{
						estaRegistrado=true;
						if(!actividad.isSeleccionado())
						{
							/**ELIMINAR**/
							paquete.setConActividad(false);
							b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteActividad(pa.getnPaqueteActividad()));
						}
						break;
					}
				}
				if(actividad.isSeleccionado() && !estaRegistrado)
				{
					paquete.setConActividad(true);
					b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(paquete.getcPaqueteCod(), actividad.getnActividadCod()));
				}
			}
			boolean r=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(paquete.getcPaqueteCod()));
		}else if(paquete.isManejo_normal())
		{
			paquete.setcDisponibilidad("MANEJO_NORMAL");
			paquete.setnDiaCaminoInka(0);
			/**Se procede a actualizar el paquete**/
			boolean b=paqueteDao.isOperationCorrect(paqueteDao.modificarPaquete(paquete));
			if(paquete.isConDestino())
			{
				for(CDestino destino:paquete.getListaDestinos())
				{
					boolean estaRegistrado=false;
					for(CPaqueteDestino pd:paquete.getListaPaqueteDestinos())
					{
						if(pd.getnDestinoCod()==destino.getnDestinoCod())
						{
							estaRegistrado=true;
							if(!destino.isSeleccionado())
							{
								/**ELIMINAR**/
								b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
							}else
								b=paqueteDao.isOperationCorrect(paqueteDao.modificarPaqueteDestino(pd.getCodPaqueteDestino(),destino.getnNoches(), destino.getnOrdenItinerario(),destino.isConCaminoInka()));
							break;
						}
					}
					if(destino.isSeleccionado() && !estaRegistrado)
					{
						b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteDestino(paquete.getcPaqueteCod(), destino.getnDestinoCod(),destino.getnNoches(),destino.getnOrdenItinerario(),destino.isConCaminoInka()));
					}
				}
			}
			if(paquete.isSinDestino())
			{
				for(CPaqueteDestino pd:paquete.getListaPaqueteDestinos())
					b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteDestino(pd.getCodPaqueteDestino()));
			}
			for(CServicio servicio:paquete.getListaServicios())
			{
				boolean estaRegistrado=false;
				for(CPaqueteServicio ps:paquete.getListaPaqueteServicios())
				{
					if(ps.getnServicioCod()==servicio.getnServicioCod())
					{
						estaRegistrado=true;
						if(!servicio.isSeleccionado())
						{
							/**ELIMINAR**/
							paquete.setConServicio(false);
							b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteServicio(ps.getCodPaqueteServicio()));
						}
						break;
					}
				}
				if(servicio.isSeleccionado() && !estaRegistrado)
				{
					paquete.setConServicio(true);
					b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteServicio(paquete.getcPaqueteCod(), servicio.getnServicioCod()));
				}
			}
			for(CActividad actividad:paquete.getListaActividades())
			{
				boolean estaRegistrado=false;
				for(CPaqueteActividad pa:paquete.getListaPaqueteActividades())
				{
					if(pa.getnActividadCod()==actividad.getnActividadCod())
					{
						estaRegistrado=true;
						if(!actividad.isSeleccionado())
						{
							/**ELIMINAR**/
							paquete.setConActividad(false);
							b=paqueteDao.isOperationCorrect(paqueteDao.eliminarPaqueteActividad(pa.getnPaqueteActividad()));
						}
						break;
					}
				}
				if(actividad.isSeleccionado() && !estaRegistrado)
				{
					paquete.setConActividad(true);
					b=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteActividad(paquete.getcPaqueteCod(), actividad.getnActividadCod()));
				}
			}
			boolean r=paqueteDao.isOperationCorrect(paqueteDao.insertarPaqueteCatHotel(paquete.getcPaqueteCod()));
		}
		/*RECUPERAMOS LOS NUEVOS DESTINOS EN EL PAQUETE*/
		paqueteDestinoDaoUpdate.asignarListaPaqueteDestinos(paqueteDestinoDaoUpdate.recuperarPaqueteDestinos(paquete.getcPaqueteCod()));
		paquete.setListaPaqueteDestinos(paqueteDestinoDaoUpdate.getListaPaqueteDestinos());
		/*RECUPERAMOS LOS NUEVOS SERVICIOS EN EL PAQUETE*/
		paqueteServicioDaoUpdate.asignarListaPaqueteServicios(paqueteServicioDaoUpdate.recuperarPaqueteServiciosBD(paquete.getcPaqueteCod()));
		paquete.setListaPaqueteServicios(paqueteServicioDaoUpdate.getListaPaqueteServicios());
		/*RECUPERAMOS LAS NUEVAS ACTIVIDADES EN EL PAQUETE*/
		paqueteActividadDaoUpdate.asignarListaPaqueteActividaes(paqueteActividadDaoUpdate.recuperarPaqueteActividades(paquete.getcPaqueteCod()));
		paquete.setListaPaqueteActividades(paqueteActividadDaoUpdate.getListaPaqueteActividades());
		if(paquete.getListaActividades().size()>0)
			paquete.setConActividad(true);
		else
			paquete.setConActividad(false);
		if(paquete.getListaPaqueteServicios().size()>0)
			paquete.setConServicio(true);
		else
			paquete.setConServicio(false);
		Clients.showNotification("El paquete se actualiz� correctamente", Clients.NOTIFICATION_TYPE_INFO, comp, "before_start", 2700);
		paquete.setEditable(false);
		refrescaFilaTemplate(paquete);
		/**Actualizar datos de la etiqueta en la BD**/
//		initVM();
//		System.out.println("-->"+etiqueta.getCodEtiqueta());
		
	}
	public boolean validoParaActualizar(CPaquete paquete,Component comp)
	{
		boolean valido=true;
		if(paquete.isManejo_camino_inca())
		{
			if(paquete.getcTituloIdioma1().equals(""))
			{
				valido=false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else if(paquete.getnDias()==0)
			{
				valido=false;
				Clients.showNotification("El paquete debe de tener un numero de dias", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else{
				if(paquete.isConDescuento())
				{
					if(paquete.getnPrecioUno().doubleValue()==0 ||
							paquete.getnPrecioDos().doubleValue()==0 ||
							paquete.getnPrecioTres().doubleValue()==0 ||
							paquete.getnPrecioCuatro().doubleValue()==0 ||
							paquete.getnPrecioCinco().doubleValue()==0)
					{
						valido=false;
						Clients.showNotification("Ningun precio del paquete puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				}else if(paquete.getnPrecioUno().doubleValue()==0){
					valido=false;
					Clients.showNotification("El precio del paquete no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
		}else
		{
			if(paquete.getcTituloIdioma1().equals(""))
			{
				valido=false;
				Clients.showNotification("El paquete debe de tener un nombre", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
			}else{
				if(paquete.isConDescuento())
				{
					if(paquete.getnPrecioUno().doubleValue()==0 ||
							paquete.getnPrecioDos().doubleValue()==0 ||
							paquete.getnPrecioTres().doubleValue()==0 ||
							paquete.getnPrecioCuatro().doubleValue()==0 ||
							paquete.getnPrecioCinco().doubleValue()==0)
					{
						valido=false;
						Clients.showNotification("Ningun precio del paquete puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
					}
				}else if(paquete.getnPrecioUno().doubleValue()==0){
					valido=false;
					Clients.showNotification("El precio del paquete no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}
			}
			if(valido)
			{
				if(paquete.getnNoches()==0){
					valido=false;
					Clients.showNotification("El paquete debe tener un numero de dias y noches", Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
				}else
				{
					if(paquete.isConDestino())
					{
						for(CDestino destino:paquete.getListaDestinos())
						{
							if(destino.isSeleccionado() && destino.getnNoches()==0)
							{
								valido=false;
								Clients.showNotification("Debe especificar Cuantas noches se quedar� en "+destino.getcDestino(), Clients.NOTIFICATION_TYPE_ERROR, comp, "before_start", 2700);
								break;
							}
						}
					}
				}
			}
		}
		return valido;
	}
	@Command
	 public void Editar(@BindingParam("paquete") CPaquete p) 
	{
		oPaqueteUpdate.setEditable(false);
		refrescaFilaTemplate(oPaqueteUpdate);
		oPaqueteUpdate=p;
		//le damos estado editable
		p.setEditable(!p.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(p);
   }
	@Command
	 public void Activar_Desactivar_paquete(@BindingParam("paquete")CPaquete p,@BindingParam("texto")String texto)
	{
		if(texto.equals("activar"))
		{
			p.setColor_btn_activo(p.COLOR_ACTIVO);
			p.setColor_btn_desactivo(p.COLOR_TRANSPARENT);
			p.setbEstado(true);
		}else{
			p.setColor_btn_activo(p.COLOR_TRANSPARENT);
			p.setColor_btn_desactivo(p.COLOR_DESACTIVO);
			p.setbEstado(false);
		}
		BindUtils.postNotifyChange(null, null, p,"color_btn_activo");
		BindUtils.postNotifyChange(null, null, p,"color_btn_desactivo");
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
		oPaquete.setDias_noches(oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	public void manejo_propio_CaminoInka_update(@BindingParam("opcion")String opcion,@BindingParam("dest")CDestino destino,@BindingParam("paquete")CPaquete paquete)
	{
		if(opcion.equals("con_camino_inka"))
		{
			paquete.setManejoPropio_conCaminoInka(true);
			paquete.setnNoches(paquete.getnNoches()+4);
			paquete.setnDias(paquete.getnNoches()+1);
			destino.setConCaminoInka(true);
			destino.setSinCaminoInka(false);
			for(CDestino dest:paquete.getListaDestinos())
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
			paquete.setManejoPropio_conCaminoInka(false);
			paquete.setnNoches(paquete.getnNoches()-4);
			if(paquete.getnNoches()==0)
				paquete.setnDias(0);
			else
				paquete.setnDias(paquete.getnNoches()+1);
			paquete.setnDiaCaminoInka(0);
			for(CDestino dest:paquete.getListaDestinos())
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
		paquete.setDias_noches(paquete.getnDias()+" DIAS Y "+paquete.getnNoches()+" NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
		BindUtils.postNotifyChange(null, null, paquete, "manejoPropio_conCaminoInka");
		BindUtils.postNotifyChange(null, null, paquete, "conCaminoInka");
		BindUtils.postNotifyChange(null, null, paquete, "sinCaminoInka");
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
	public void changePrecios_update(@BindingParam("precio")String precio,@BindingParam("descuento")String descuento,@BindingParam("componente")Component comp,@BindingParam("paquete")CPaquete paquete)
	{
		if(descuento.equals("precio1"))
		{
			if(!isNumberDouble(precio))
			{
				paquete.setnPrecio1_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				paquete.setnPrecioUno(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}else if(descuento.equals("precio2"))
		{
			if(!isNumberDouble(precio))
			{
				paquete.setnPrecio2_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				paquete.setnPrecioDos(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}else if(descuento.equals("precio3"))
		{
			if(!isNumberDouble(precio))
			{
				paquete.setnPrecio3_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				paquete.setnPrecioTres(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}else if(descuento.equals("precio4"))
		{
			if(!isNumberDouble(precio))
			{
				paquete.setnPrecio4_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				paquete.setnPrecioCuatro(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}else if(descuento.equals("precio5"))
		{
			if(!isNumberDouble(precio))
			{
				paquete.setnPrecio5_text(df.format(0));
				Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start", 2700);
			}
			else
				paquete.setnPrecioCinco(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio1_text");
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio2_text");
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio3_text");
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio4_text");
		BindUtils.postNotifyChange(null, null, paquete, "nPrecio5_text");
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
	@NotifyChange({"oPaquete"})
	public void selectDestinos(@BindingParam("destino")CDestino destino)
	{
		if(destino.isSeleccionado())
		{
			destino.setSeleccionado(false);
			if(oPaquete.isManejoPropio_conCaminoInka())
			{
				oPaquete.setnNoches(oPaquete.getnNoches()-destino.getnNoches());
				if(oPaquete.getnNoches()!=0)
					oPaquete.setnDias(oPaquete.getnNoches()+1);
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
				if(dest.isSeleccionado() && dest.getnCodPostal()==84 && oPaquete.isManejo_propio())
				{
					dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
			oPaquete.setDias_noches(oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
			destino.setnNoches(0);
			//Recupero el orden de itinerario des seleccionado
			oPaquete.setOrdenDesSelect(destino.getnOrdenItinerario());
			//Disminuyo el numero de destinos seleccionados
			oPaquete.setNroDestinosSelect(oPaquete.getNroDestinosSelect()-1);
			//Actualizo los itinerarios
			while(oPaquete.getOrdenDesSelect()<=oPaquete.getNroDestinosSelect())
			{
				for(CDestino dest:listaDestinos)
				{
					if((oPaquete.getOrdenDesSelect()+1)==dest.getnOrdenItinerario())
					{
						dest.setnOrdenItinerario(oPaquete.getOrdenDesSelect());
						BindUtils.postNotifyChange(null, null, dest, "nOrdenItinerario");
						break;
					}
				}
				oPaquete.setOrdenDesSelect(oPaquete.getOrdenDesSelect()+1);
			}
			oPaquete.setOrdenDesSelect(0);
			destino.setnOrdenItinerario(0);
			destino.setPuedeCaminoInka(false);
		}
		else
		{
			destino.setSeleccionado(true);
			if(oPaquete.isManejo_propio() && !oPaquete.isManejoPropio_conCaminoInka())
				destino.asignaPuedeCaminoInka(destino);
			oPaquete.setNroDestinosSelect(oPaquete.getNroDestinosSelect()+1);
			destino.setnOrdenItinerario(oPaquete.getNroDestinosSelect());
		}
		BindUtils.postNotifyChange(null, null, destino, "seleccionado");
		BindUtils.postNotifyChange(null, null, destino, "puedeCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nOrdenItinerario");
		BindUtils.postNotifyChange(null, null, destino, "conCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "sinCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nNoches");
	}
	@Command
	public void selectDestinos_update(@BindingParam("dest")CDestino destino,@BindingParam("paquete")CPaquete paquete)
	{
		if(destino.isSeleccionado())
		{
			destino.setSeleccionado(false);
			if(paquete.isManejoPropio_conCaminoInka())
			{
				paquete.setnNoches(paquete.getnNoches()-destino.getnNoches());
				if(paquete.getnNoches()!=0)
					paquete.setnDias(paquete.getnNoches()+1);
				else
					paquete.setnDias(0);
			}else
			{
				paquete.setnNoches(paquete.getnNoches()-destino.getnNoches());
				if(paquete.getnNoches()!=0)
					paquete.setnDias(paquete.getnNoches()+1);
				else
					paquete.setnDias(0);
			}
			if(paquete.isManejoPropio_conCaminoInka() && destino.getnCodPostal()==84 &&
					destino.isConCaminoInka())
			{
				paquete.setnNoches(paquete.getnNoches()-4);
				if(paquete.getnNoches()!=0)
					paquete.setnDias(paquete.getnNoches()+1);
				else
					paquete.setnDias(0);
				destino.setConCaminoInka(false);
				destino.setSinCaminoInka(true);
				paquete.setManejoPropio_conCaminoInka(false);
			}
			for(CDestino dest:paquete.getListaDestinos())
			{
				if(dest.isSeleccionado() && dest.getnCodPostal()==84 && paquete.isManejo_propio())
				{
					dest.setPuedeCaminoInka(true);
					BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
				}
			}
			paquete.setDias_noches(paquete.getnDias()+" DIAS Y "+paquete.getnNoches()+" NOCHES");
			destino.setnNoches(0);
			//Recupero el orden de itinerario des seleccionado
			paquete.setOrdenDesSelect(destino.getnOrdenItinerario());
			//Disminuyo el numero de destinos seleccionados
			paquete.setNroDestinosSelect(paquete.getNroDestinosSelect()-1);
			//Actualizo los itinerarios
//			System.out.println("<---------------------->");
			while(paquete.getOrdenDesSelect()<=paquete.getNroDestinosSelect())
			{
				for(CDestino dest:paquete.getListaDestinos())
				{
					if((paquete.getOrdenDesSelect()+1)==dest.getnOrdenItinerario())
					{
						dest.setnOrdenItinerario(paquete.getOrdenDesSelect());
//						System.out.println("Orden: "+dest.getnOrdenItinerario());
						BindUtils.postNotifyChange(null, null, dest, "nOrdenItinerario");
						break;
					}
				}
				paquete.setOrdenDesSelect(paquete.getOrdenDesSelect()+1);
			}
//			System.out.println("<---------------------->");
			paquete.setOrdenDesSelect(0);
			destino.setnOrdenItinerario(0);
			destino.setPuedeCaminoInka(false);
		}
		else
		{
			destino.setSeleccionado(true);
			if(paquete.isManejo_propio() && !paquete.isManejoPropio_conCaminoInka())
				destino.asignaPuedeCaminoInka(destino);
			paquete.setNroDestinosSelect(paquete.getNroDestinosSelect()+1);
			destino.setnOrdenItinerario(paquete.getNroDestinosSelect());
		}
		BindUtils.postNotifyChange(null, null, destino, "seleccionado");
		BindUtils.postNotifyChange(null, null, destino, "puedeCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nOrdenItinerario");
		BindUtils.postNotifyChange(null, null, destino, "conCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "sinCaminoInka");
		BindUtils.postNotifyChange(null, null, destino, "nNoches");
		BindUtils.postNotifyChange(null, null, paquete, "manejoPropio_conCaminoInka");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
//		for(CDestino dest:paquete.getListaDestinos())
//			if(dest.isSeleccionado())
//				System.out.println("Iti: "+dest.getnOrdenItinerario());
//		System.out.println("===========================");
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
		oPaquete.setDias_noches(oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	public void determinarNroNochesDestino_update(@BindingParam("paquete")CPaquete paquete)
	{
		paquete.setnNoches(0);
		for(CDestino destino:paquete.getListaDestinos())
		{
			if(destino.isSeleccionado())
				paquete.setnNoches(paquete.getnNoches()+destino.getnNoches());
		}
		if(paquete.getnNoches()!=0)
			paquete.setnDias(paquete.getnNoches()+1);
		else
			paquete.setnDias(0);
		if(paquete.isManejo_propio() && paquete.isManejoPropio_conCaminoInka())
		{
			paquete.setnNoches(paquete.getnNoches()+4);
			paquete.setnDias(paquete.getnNoches()+1);
		}
		paquete.setDias_noches(paquete.getnDias()+" DIAS Y "+paquete.getnNoches()+" NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
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
	public void selectServicios_update(@BindingParam("serv")CServicio servicio)
	{
		if(servicio.isSeleccionado())
			servicio.setSeleccionado(false);
		else
			servicio.setSeleccionado(true);
	}
	@Command
	public void selectActividades(@BindingParam("actividad")CActividad actividad)
	{
		if(actividad.isSeleccionado())
			actividad.setSeleccionado(false);
		else
			actividad.setSeleccionado(true);
	}
	@Command 
	public void selectActividades_update(@BindingParam("act")CActividad actividad)
	{
		if(actividad.isSeleccionado())
			actividad.setSeleccionado(false);
		else
			actividad.setSeleccionado(true);
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
			oPaquete.setConDestino(false);
			oPaquete.setSinDestino(true);
		}
		else if(opcion.equals("MANEJO_PROPIO"))
		{
			oPaquete.setManejo_camino_inca(false);
			oPaquete.setManejo_propio(true);
			oPaquete.setManejo_normal(false);
			if(oPaquete.getNroDestinosSelect()>0)
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
			if(oPaquete.getNroDestinosSelect()>0)
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
	@Command
	public void select_manejo_paquete_update(@BindingParam("opcion")String opcion,@BindingParam("paquete")CPaquete paquete)
	{
		if(opcion.equals("CAMINO_INKA"))
		{
			paquete.setManejo_camino_inca(true);
			paquete.setManejo_propio(false);
			paquete.setManejo_normal(false);
			paquete.setConDestino(false);
			paquete.setSinDestino(true);
		}
		else if(opcion.equals("MANEJO_PROPIO"))
		{
			paquete.setManejo_camino_inca(false);
			paquete.setManejo_propio(true);
			paquete.setManejo_normal(false);
			if(paquete.getNroDestinosSelect()>0)
				for(CDestino dest:paquete.getListaDestinos())
				{
					if(paquete.isManejoPropio_conCaminoInka())
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
			paquete.setManejo_camino_inca(false);
			paquete.setManejo_propio(false);
			paquete.setManejo_normal(true);
			if(paquete.getNroDestinosSelect()>0)
				for(CDestino dest:paquete.getListaDestinos())
				{
					if(dest.isSeleccionado() && dest.getnCodPostal()==84)
					{
						dest.setPuedeCaminoInka(false);
						BindUtils.postNotifyChange(null, null, dest, "puedeCaminoInka");
					}
				}
		}
		BindUtils.postNotifyChange(null, null, paquete, "manejo_camino_inca");
		BindUtils.postNotifyChange(null, null, paquete, "manejo_propio");
		BindUtils.postNotifyChange(null, null, paquete, "manejo_normal");
		BindUtils.postNotifyChange(null, null, paquete, "conDestino");
		BindUtils.postNotifyChange(null, null, paquete, "sinDestino");
		inicializarOpcionesPaquete_update(paquete);
	}
	public void inicializarOpcionesPaquete()
	{
		oPaquete.setnDias(0);
		oPaquete.setnNoches(0);
		if(oPaquete.isConDestino())
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
		oPaquete.setDias_noches(oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	public void inicializarOpcionesPaquete_update(CPaquete paquete)
	{
		paquete.setnDias(0);
		paquete.setnNoches(0);
		if(paquete.isConDestino())
		{
			if(paquete.isManejo_propio())
			{
				for(CDestino destino:paquete.getListaDestinos())
				{
					if(destino.isSeleccionado())
						if(destino.getnNoches()!=0)
							paquete.setnNoches(paquete.getnNoches()+destino.getnNoches());
				}
				if(paquete.getnNoches()!=0)
					paquete.setnDias(paquete.getnNoches()+1);
				if(paquete.isManejoPropio_conCaminoInka())
				{
					paquete.setnNoches(paquete.getnNoches()+4);
					paquete.setnDias(paquete.getnNoches()+1);
				}
			}else if(paquete.isManejo_normal())
			{
				for(CDestino destino:paquete.getListaDestinos())
				{
					if(destino.isSeleccionado())
						if(destino.getnNoches()!=0)
							paquete.setnNoches(paquete.getnNoches()+destino.getnNoches());
				}
				if(paquete.getnNoches()!=0)
					paquete.setnDias(paquete.getnNoches()+1);
			}
		}
		paquete.setDias_noches(paquete.getnDias()+" DIAS Y "+paquete.getnNoches()+" NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
		BindUtils.postNotifyChange(null, null, paquete, "nDias");
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void select_paquete_conDestino(@BindingParam("opcion")String opcion)
	{
		oPaquete.setnNoches(0);
		oPaquete.setnDias(0);
		if(opcion.equals("sin_destino"))
		{
			oPaquete.setConDestino(false);
			oPaquete.setSinDestino(true);
		}else
		{
			oPaquete.setConDestino(true);
			oPaquete.setSinDestino(false);
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
		oPaquete.setDias_noches(oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	public void select_paquete_conDestino_update(@BindingParam("opcion")String opcion,@BindingParam("paquete")CPaquete paquete)
	{
		paquete.setnNoches(0);
		paquete.setnDias(0);
		if(opcion.equals("sin_destino"))
		{
			paquete.setConDestino(false);
			paquete.setSinDestino(true);
			paquete.setNroDestinosSelect(0);
			paquete.setOrdenDesSelect(0);
			destinoDao.asignarListaDestinos(destinoDao.recuperarListaDestinosBD());
			paquete.setListaDestinos(destinoDao.getListaDestinos());
		}else
		{
			paquete.setConDestino(true);
			paquete.setSinDestino(false);
			for(CDestino destino:paquete.getListaDestinos())
			{
				if(destino.isSeleccionado())
					if(destino.getnNoches()!=0)
						paquete.setnNoches(paquete.getnNoches()+destino.getnNoches());
			}
			if(paquete.getnNoches()!=0)
				paquete.setnDias(paquete.getnNoches()+1);
			if(paquete.isManejo_propio() && paquete.isManejoPropio_conCaminoInka())
			{
				paquete.setnNoches(paquete.getnNoches()+4);
				paquete.setnDias(paquete.getnNoches()+1);
			}

		}
		paquete.setDias_noches(paquete.getnDias()+" DIAS Y "+paquete.getnNoches()+" NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
		BindUtils.postNotifyChange(null, null, paquete, "conDestino");
		BindUtils.postNotifyChange(null, null, paquete, "sinDestino");
		BindUtils.postNotifyChange(null, null, paquete, "nDias");
		BindUtils.postNotifyChange(null, null, paquete, "listaDestinos");
		BindUtils.postNotifyChange(null, null, paquete, "ordenDesSelect");
		BindUtils.postNotifyChange(null, null, paquete, "nroDestinosSelect");
	}
	@Command
	public void select_paquete_conDescuento(@BindingParam("opcion")String opcion)
	{
		if(opcion.equals("sin_descuento"))
		{
			oPaquete.setConDescuento(false);
			oPaquete.setSinDescuento(true);
		}
		else
		{
			oPaquete.setConDescuento(true);
			oPaquete.setSinDescuento(false);
		}
	}
	@Command
	public void select_paquete_conDescuento_update(@BindingParam("opcion")String opcion,@BindingParam("paquete")CPaquete paquete)
	{
		if(opcion.equals("sin_descuento"))
		{
			paquete.setConDescuento(false);
			paquete.setSinDescuento(true);
		}
		else
		{
			paquete.setConDescuento(true);
			paquete.setSinDescuento(false);
		}
		BindUtils.postNotifyChange(null, null, paquete, "conDescuento");
		BindUtils.postNotifyChange(null, null, paquete, "sinDescuento");
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void diasCaminoInka(@BindingParam("dias")int dias)
	{
		oPaquete.setnDias(dias);
		oPaquete.setnNoches(dias-1);
		oPaquete.setDias_noches(oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	public void diasCaminoInka_update(@BindingParam("dias")int dias,@BindingParam("paquete")CPaquete paquete)
	{
		paquete.setnDias(dias);
		paquete.setnNoches(dias-1);
		paquete.setDias_noches(paquete.getnDias()+" DIAS Y "+paquete.getnNoches()+" NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void diasSinDestino(@BindingParam("dias")int dias)
	{
		oPaquete.setnDias(dias);
		oPaquete.setnNoches(dias-1);
		oPaquete.setDias_noches(oPaquete.getnDias()+" DIAS Y "+oPaquete.getnNoches()+" NOCHES");
	}
	@Command
	public void diasSinDestino_update(@BindingParam("dias")int dias,@BindingParam("paquete")CPaquete paquete)
	{
		paquete.setnDias(dias);
		paquete.setnNoches(dias-1);
		paquete.setDias_noches(paquete.getnDias()+" DIAS Y "+paquete.getnNoches()+" NOCHES");
		BindUtils.postNotifyChange(null, null, paquete, "dias_noches");
	}
	@Command
	@NotifyChange({"oPaquete"})
	public void asignarNamePaquete()
	{
		oPaquete.setcTituloIdioma1(oPaquete.getcTituloIdioma1().toUpperCase());
		oPaquete.setTitulo(oPaquete.getcTituloIdioma1());
	}
	@Command
	public void asignarNamePaquete_update(@BindingParam("paquete")CPaquete paquete)
	{
		paquete.setcTituloIdioma1(paquete.getcTituloIdioma1().toUpperCase());
		paquete.setTitulo(paquete.getcTituloIdioma1());
		BindUtils.postNotifyChange(null, null, paquete, "titulo");
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
		BindUtils.postNotifyChange(null, null, p, "listaServicios");
		BindUtils.postNotifyChange(null, null, p, "listaDestinos");
		BindUtils.postNotifyChange(null, null, p, "listaActividades");
		BindUtils.postNotifyChange(null, null, p, "conServicio");
		BindUtils.postNotifyChange(null, null, p, "conActividad");
	}
}
