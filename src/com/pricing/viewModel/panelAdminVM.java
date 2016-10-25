package com.pricing.viewModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.http.HttpSession;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CUsuarioLoginDAO;
import com.pricing.extras.lectorPDF;
import com.pricing.model.CAcceso;
import com.pricing.model.CUsuarioLogin;
import com.pricing.util.Util;

public class panelAdminVM
{
	private boolean cargarAdmin;
	//==============TABBOX LAPTOP============
	private boolean visibleConfiguracion;
	//==============VARIABLES INTERFACES==============
	private boolean visibleEtiqueta;
	private boolean visiblePaquetes;
	private boolean visibleServicios;
	private boolean visibleSubServicios;
	private boolean visibleActividades;
	private boolean visibleImpuestos;
	private boolean visibleDisponibilidad;
	private boolean visibleHoteles;
	private boolean visibleDestinos;
	private boolean visibleReportReservas;
	private boolean visibleReportPagos;
	private boolean visibleEstadisticaPagos;
	private boolean visibleEstadisticaPaquetesmasVendidos;
	//===============VARIABLES SUBITEMS================
	private boolean openItemConfig;
	private boolean openItemUsuarios;
	private boolean openItemSliders;
	private boolean openItemPaquetes;
	
	//===============VARIABLES RESPONSIVE===========
	private String nombres="";
	private boolean visibleTabbox=false;
	private boolean visibleEditar=false;
	
	//===============SELECCION TABS==================
	private boolean seleccionDisponibilidad=false;
	private boolean seleccionEtiquetas=false;
	private boolean seleccionPaquetes=false;
	private boolean seleccionServicios=false;
	private boolean seleccionSubServicios=false;
	private boolean seleccionActividades;
	private boolean seleccionImpuestos=false;
	private boolean seleccionHoteles;
	private boolean seleccionDestinos;
	private boolean seleccionReportReservas;
	private boolean seleccionReportPagos;
	//======================================
	private CUsuarioLoginDAO usuarioDao;
	private CAcceso oAcceso;
	private CUsuarioLogin oUsuario;
	//=================GET Y SET SELECCION TABS=======
	public boolean isVisibleConfiguracion() {
		return visibleConfiguracion;
	}
	public boolean isVisibleEstadisticaPagos() {
		return visibleEstadisticaPagos;
	}
	public void setVisibleEstadisticaPagos(boolean visibleEstadisticaPagos) {
		this.visibleEstadisticaPagos = visibleEstadisticaPagos;
	}
	public boolean isVisibleEstadisticaPaquetesmasVendidos() {
		return visibleEstadisticaPaquetesmasVendidos;
	}
	public void setVisibleEstadisticaPaquetesmasVendidos(
			boolean visibleEstadisticaPaquetesmasVendidos) {
		this.visibleEstadisticaPaquetesmasVendidos = visibleEstadisticaPaquetesmasVendidos;
	}
	public void setVisibleConfiguracion(boolean visibleConfiguracion) {
		this.visibleConfiguracion = visibleConfiguracion;
	}
	public boolean isSeleccionDisponibilidad() {
		return seleccionDisponibilidad;
	}
	public void setSeleccionDisponibilidad(boolean seleccionDisponibilidad) {
		this.seleccionDisponibilidad = seleccionDisponibilidad;
	}

	public boolean isSeleccionEtiquetas() {
		return seleccionEtiquetas;
	}

	public void setSeleccionEtiquetas(boolean seleccionEtiquetas) {
		this.seleccionEtiquetas = seleccionEtiquetas;
	}

	public boolean isSeleccionPaquetes() {
		return seleccionPaquetes;
	}

	public void setSeleccionPaquetes(boolean seleccionPaquetes) {
		this.seleccionPaquetes = seleccionPaquetes;
	}

	public boolean isSeleccionServicios() {
		return seleccionServicios;
	}

	public void setSeleccionServicios(boolean seleccionServicios) {
		this.seleccionServicios = seleccionServicios;
	}

	public boolean isSeleccionSubServicios() {
		return seleccionSubServicios;
	}

	public void setSeleccionSubServicios(boolean seleccionSubServicios) {
		this.seleccionSubServicios = seleccionSubServicios;
	}

	public boolean isSeleccionActividades() {
		return seleccionActividades;
	}
	public void setSeleccionActividades(boolean seleccionActividades) {
		this.seleccionActividades = seleccionActividades;
	}
	public boolean isVisibleActividades() {
		return visibleActividades;
	}
	public void setVisibleActividades(boolean visibleActividades) {
		this.visibleActividades = visibleActividades;
	}
	public boolean isSeleccionImpuestos() {
		return seleccionImpuestos;
	}

	public void setSeleccionImpuestos(boolean seleccionImpuestos) {
		this.seleccionImpuestos = seleccionImpuestos;
	}
	//================RESPONSIVE======================
	
	public boolean isVisibleHoteles() {
		return visibleHoteles;
	}
	public void setVisibleHoteles(boolean visibleHoteles) {
		this.visibleHoteles = visibleHoteles;
	}
	public boolean isSeleccionHoteles() {
		return seleccionHoteles;
	}
	public void setSeleccionHoteles(boolean seleccionHoteles) {
		this.seleccionHoteles = seleccionHoteles;
	}
	public String getNombres() {
		return nombres;
	}

	public boolean isVisibleEditar() {
		return visibleEditar;
	}

	public void setVisibleEditar(boolean visibleEditar) {
		this.visibleEditar = visibleEditar;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public boolean isVisibleTabbox() {
		return visibleTabbox;
	}

	public void setVisibleTabbox(boolean visibleTabbox) {
		this.visibleTabbox = visibleTabbox;
	}
	//================CAMBIOS ITEMS==============
	public boolean isOpenItemConfig() {
		return openItemConfig;
	}

	public void setOpenItemConfig(boolean openItemConfig) {
		this.openItemConfig = openItemConfig;
	}

	public boolean isOpenItemUsuarios() {
		return openItemUsuarios;
	}

	public void setOpenItemUsuarios(boolean openItemUsuarios) {
		this.openItemUsuarios = openItemUsuarios;
	}

	public boolean isOpenItemSliders() {
		return openItemSliders;
	}

	public void setOpenItemSliders(boolean openItemSliders) {
		this.openItemSliders = openItemSliders;
	}

	public boolean isOpenItemPaquetes() {
		return openItemPaquetes;
	}

	public void setOpenItemPaquetes(boolean openItemPaquetes) {
		this.openItemPaquetes = openItemPaquetes;
	}
	//===============CAMBIO INTERFACES==============
	public boolean isVisibleDisponibilidad() {
		return visibleDisponibilidad;
	}

	public void setVisibleDisponibilidad(boolean visibleDisponibilidad) {
		this.visibleDisponibilidad = visibleDisponibilidad;
	}
	
	public boolean isVisibleEtiqueta() {
		return visibleEtiqueta;
	}

	public void setVisibleEtiqueta(boolean visibleEtiqueta) {
		this.visibleEtiqueta = visibleEtiqueta;
	}

	public boolean isVisiblePaquetes() {
		return visiblePaquetes;
	}

	public void setVisiblePaquetes(boolean visiblePaquetes) {
		this.visiblePaquetes = visiblePaquetes;
	}

	public boolean isVisibleServicios() {
		return visibleServicios;
	}

	public void setVisibleServicios(boolean visibleServicios) {
		this.visibleServicios = visibleServicios;
	}

	public boolean isVisibleSubServicios() {
		return visibleSubServicios;
	}

	public void setVisibleSubServicios(boolean visibleSubServicios) {
		this.visibleSubServicios = visibleSubServicios;
	}

	public boolean isVisibleImpuestos() {
		return visibleImpuestos;
	}

	public void setVisibleImpuestos(boolean visibleImpuestos) {
		this.visibleImpuestos = visibleImpuestos;
	}
	
	public boolean isVisibleDestinos() {
		return visibleDestinos;
	}
	public void setVisibleDestinos(boolean visibleDestinos) {
		this.visibleDestinos = visibleDestinos;
	}
	public boolean isSeleccionDestinos() {
		return seleccionDestinos;
	}
	public void setSeleccionDestinos(boolean seleccionDestinos) {
		this.seleccionDestinos = seleccionDestinos;
	}
	
	public boolean isVisibleReportReservas() {
		return visibleReportReservas;
	}
	public void setVisibleReportReservas(boolean visibleReportReservas) {
		this.visibleReportReservas = visibleReportReservas;
	}
	public boolean isVisibleReportPagos() {
		return visibleReportPagos;
	}
	public void setVisibleReportPagos(boolean visibleReportPagos) {
		this.visibleReportPagos = visibleReportPagos;
	}
	public boolean isSeleccionReportReservas() {
		return seleccionReportReservas;
	}
	public void setSeleccionReportReservas(boolean seleccionReportReservas) {
		this.seleccionReportReservas = seleccionReportReservas;
	}
	public boolean isSeleccionReportPagos() {
		return seleccionReportPagos;
	}
	public void setSeleccionReportPagos(boolean seleccionReportPagos) {
		this.seleccionReportPagos = seleccionReportPagos;
	}
	public CAcceso getoAcceso() {
		return oAcceso;
	}
	public void setoAcceso(CAcceso oAcceso) {
		this.oAcceso = oAcceso;
	}
	public CUsuarioLogin getoUsuario() {
		return oUsuario;
	}
	public void setoUsuario(CUsuarioLogin oUsuario) {
		this.oUsuario = oUsuario;
	}
	public boolean isCargarAdmin() {
		return cargarAdmin;
	}
	public void setCargarAdmin(boolean cargarAdmin) {
		this.cargarAdmin = cargarAdmin;
	}
	@Init
	public void Inicializar() {
		cargarAdmin=false;
		try
		{
			Encryptar encrip= new Encryptar();
//			System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
			Execution exec = Executions.getCurrent();
			HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
		    String user=(String)ses.getAttribute("usuario");
		    String pas=(String)ses.getAttribute("clave");
		    int perfil=(int)ses.getAttribute("perfil");
		    /******************************************/
		    iniciarPanelAdministrador(user,pas,perfil);
		}
		catch(Exception e)
		{
			irALogin();
		}
	}
	public void irALogin()
	{
		Executions.getCurrent().sendRedirect("/login.zul");
	}
	public void iniciarPanelAdministrador(String usuario,String password,int codPerfil)
	{
		cargarAdmin=true;
		usuarioDao=new CUsuarioLoginDAO();
		oAcceso=new CAcceso();
		oUsuario=new CUsuarioLogin();
		usuarioDao.asignarAccesosUsuario(usuarioDao.recuperarAccesosUsuario(codPerfil));
		setoAcceso(usuarioDao.getoAcceso());
		
		usuarioDao.asignarUsuario(usuarioDao.recuperarUsuario(usuario, password));
		setoUsuario(usuarioDao.getoUsuario());
		/********************************/
		seleccionDisponibilidad=seleccionEtiquetas=seleccionImpuestos=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
		seleccionActividades=seleccionHoteles=seleccionDestinos=false;
		visibleDisponibilidad=visibleEtiqueta = visiblePaquetes = visibleServicios = visibleSubServicios = visibleImpuestos =false;
		visibleActividades=visibleHoteles=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
		visibleConfiguracion=visibleDestinos=visibleReportReservas=visibleReportPagos=false;
	}
	//================CAMBIO DE VISIBILIDAD========
	@Command
	@NotifyChange({ "visibleEtiqueta", "visiblePaquetes","visibleDestinos","visibleServicios", "visibleSubServicios",
		"visibleActividades","visibleImpuestos", "visibleMenu", "visibleDisponibilidad","visibleReportReservas","visibleReportPagos",
		"seleccionDisponibilidad","seleccionEtiquetas", "seleccionPaquetes", "seleccionServicios", "seleccionSubServicios", "seleccionImpuestos",
		"visibleConfiguracion","visibleHoteles","seleccionHoteles","seleccionDestinos","seleccionReportReservas",
		"seleccionActividades","seleccionReportPagos","visibleEstadisticaPaquetesmasVendidos","visibleEstadisticaPagos"})
	public void Cambio(@BindingParam("cambioInterfaz") String cambios) {
		visibleConfiguracion=true;	
		if (cambios.equals("itemDisponibilidad") || cambios.equals("btnDisponibilidad") || cambios.equals("tabDisponibilidad") ) {
				visibleDisponibilidad=true;
				visibleActividades=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				visibleEtiqueta=visiblePaquetes=visibleServicios=visibleSubServicios=visibleReportReservas=visibleHoteles=false;
				visibleSubServicios=visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				seleccionDisponibilidad=true;
				seleccionActividades=false;
				seleccionEtiquetas=seleccionImpuestos=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			} else if (cambios.equals("itemEtiqueta") || cambios.equals("btnEtiquetas") || cambios.equals("tabEtiqueta") ) {
				visibleEtiqueta = true;
				visibleDisponibilidad=visiblePaquetes=visibleServicios=visibleSubServicios=visibleReportReservas=visibleHoteles=false;
				visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleActividades=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				seleccionActividades=false;
				seleccionEtiquetas=true;
				seleccionDisponibilidad=seleccionImpuestos=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			} else if (cambios.equals("itemPaquete") || cambios.equals("btnPaquetes") || cambios.equals("tabPaquete")) {
				visiblePaquetes=true;
				visibleDisponibilidad=visibleEtiqueta=visibleServicios=visibleReportReservas=visibleHoteles=false;
				visibleSubServicios=visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleActividades=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				seleccionActividades=false;
				seleccionPaquetes=true;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionImpuestos=seleccionServicios=seleccionSubServicios=false;
			} else if (cambios.equals("itemServicio") || cambios.equals("btnServicios") || cambios.equals("tabServicio")) {
				visibleServicios=true;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleReportReservas=visibleHoteles=false;
				visibleSubServicios=visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleActividades=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				seleccionActividades=false;
				seleccionServicios=true;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionImpuestos=seleccionPaquetes=seleccionSubServicios=false;
			} else if (cambios.equals("itemSubServicio") || cambios.equals("btnSubServicios") || cambios.equals("tabSubServicio")) {
				visibleSubServicios =true;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=visibleHoteles=false;
				visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleActividades=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				seleccionActividades=false;
				seleccionSubServicios=true;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionImpuestos=seleccionPaquetes=seleccionServicios=false;
			} else if(cambios.equals("itemActividad")){
				visibleEstadisticaPaquetesmasVendidos=false;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=true;
				seleccionActividades=true;
				visibleSubServicios=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				seleccionReportPagos=false;
				seleccionHoteles=seleccionImpuestos=seleccionDestinos=false;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			
			}else if (cambios.equals("itemImpuesto") || cambios.equals("btnImpuestos") || cambios.equals("tabImpuesto")) {
				visibleImpuestos=true;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=visibleHoteles=false;
				visibleSubServicios=visibleDestinos=visibleReportPagos=false;
				visibleActividades=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				seleccionActividades=false;
				seleccionImpuestos=true;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			}else if (cambios.equals("itemHoteles") || cambios.equals("btnHoteles") || cambios.equals("tabHotel")) {
				visibleHoteles=true;
				visibleImpuestos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=visibleReportPagos=false;
				visibleSubServicios=visibleDestinos=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				visibleActividades=false;
				seleccionActividades=false;
				seleccionHoteles=true;
				seleccionImpuestos=false;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			}else if (cambios.equals("itemDestinos") || cambios.equals("btnDestinos") || cambios.equals("tabDestino")) {
				visibleDestinos=true;
				visibleHoteles=visibleImpuestos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleSubServicios=visibleReportPagos=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				visibleActividades=false;
				seleccionActividades=false;
				seleccionDestinos=true;
				seleccionHoteles=seleccionImpuestos=false;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			}else if (cambios.equals("itemReporteReservas") || cambios.equals("btnReporteReservas") || cambios.equals("tabReporteReserva")) {
				visibleReportReservas=true;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleSubServicios=false;
				visibleActividades=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				seleccionActividades=false;
				seleccionReportReservas=true;
				seleccionHoteles=seleccionImpuestos=seleccionDestinos=false;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			}
			else if (cambios.equals("itemReportePagos")) {
				visibleReportPagos=true;
				visibleHoteles=visibleImpuestos=visibleDestinos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=visibleEstadisticaPagos=visibleEstadisticaPaquetesmasVendidos=false;
				seleccionActividades=false;
				visibleSubServicios=false;
				seleccionReportPagos=true;
				seleccionHoteles=seleccionImpuestos=seleccionDestinos=false;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			}else if (cambios.equals("itemEstadisticaPagos")) {
				visibleEstadisticaPagos=true;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPaquetesmasVendidos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				seleccionActividades=false;
				visibleSubServicios=false;
				seleccionReportPagos=false;
				seleccionHoteles=seleccionImpuestos=seleccionDestinos=false;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			}else if (cambios.equals("itemEstadisticaPaquetesmasVendidos")) {
				visibleEstadisticaPaquetesmasVendidos=true;
				visibleHoteles=visibleImpuestos=visibleDestinos=visibleReportPagos=visibleEstadisticaPagos=false;
				visibleDisponibilidad=visibleEtiqueta=visiblePaquetes=visibleServicios=visibleReportReservas=false;
				visibleActividades=false;
				seleccionActividades=false;
				visibleSubServicios=false;
				seleccionReportPagos=false;
				seleccionHoteles=seleccionImpuestos=seleccionDestinos=false;
				seleccionDisponibilidad=seleccionEtiquetas=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			}
			
	}
	//================CAMBIO DE APERTURA==========
	@Command
	@NotifyChange({ "openItemConfig","openItemUsuarios","openItemPaquetes" })
	public void CambioApertura(@BindingParam("cambioApertura") String cambios) {
		if (cambios.equals("itemConfiguracion")) {
			if(openItemConfig){ openItemConfig=false;}
			else {openItemConfig = true; openItemUsuarios = openItemPaquetes = false;}
		} else if (cambios.equals("itemUsuarios")) {
			if(openItemUsuarios){ openItemUsuarios=false;}
			else {openItemUsuarios = true; openItemSliders= openItemConfig = openItemPaquetes = false;}
		} else if (cambios.equals("itemPaquetes")) {
			if(openItemPaquetes){ openItemPaquetes=false;}
			else {openItemPaquetes = true; openItemConfig = openItemUsuarios = openItemSliders = false;}
		}				
	}
	//==============CAMBIO RESPONSIVE=============
	  @Command
	  @NotifyChange({"visibleBoton","visibleMenu","windowMode","visibleEditar"})
	  public void muestra(@BindingParam("width")int ancho){
		  if(ancho<700){visibleEditar=true;}
	  }
	  @Command
	  @NotifyChange({"windowMode","openItemConfig","openItemUsuarios","openItemPaquetes"})
	  public void menuDesplegable()
	  {   
		  openItemConfig=openItemUsuarios=openItemPaquetes=false;	  
	  }
	  
	  @Command
	  @NotifyChange("visibleMenu")
	  public void CerrarPopup()
	  {
	  }
}
