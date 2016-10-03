package com.pricing.viewModel;

import java.util.ArrayList;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

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
	@NotifyChange({"oDestino"})
	public void selectDestinos(@BindingParam("destino")CDestino destino)
	{
		if(destino.isSeleccionado())
			destino.setSeleccionado(false);
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
