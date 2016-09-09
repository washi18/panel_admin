package com.pricing.viewModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.pricing.extras.lectorPDF;
import com.pricing.util.Util;

public class panelAdminVM
{
	//==============VARIABLES INTERFACES==============
	private boolean visibleEtiqueta=false;
	private boolean visiblePaquetes=false;
	private boolean visibleServicios=false;
	private boolean visibleSubServicios=false;
	private boolean visibleImpuestos=false;
	private boolean visibleDisponibilidad=false;
	
	//===============VARIABLES SUBITEMS================
	private boolean openItemConfig;
	private boolean openItemUsuarios;
	private boolean openItemSliders;
	private boolean openItemPaquetes;
	
	//===============VARIABLES RESPONSIVE===========
	private String nombres="";
	private boolean visibleTabbox=false;
	private boolean visibleEditar=false;
	private int anchoDispositivo;
	
	//===============SELECCION TABS==================
	private boolean seleccionDisponibilidad=false;
	private boolean seleccionEtiquetas=false;
	private boolean seleccionPaquetes=false;
	private boolean seleccionServicios=false;
	private boolean seleccionSubServicios=false;
	private boolean seleccionImpuestos=false;
	
	//=================GET Y SET SELECCION TABS=======
	
	public boolean isSeleccionDisponibilidad() {
		return seleccionDisponibilidad;
	}

	public boolean isVisibleEditar() {
		return visibleEditar;
	}

	public void setVisibleEditar(boolean visibleEditar) {
		this.visibleEditar = visibleEditar;
	}

	public int getAnchoDispositivo() {
		return anchoDispositivo;
	}

	public void setAnchoDispositivo(int anchoDispositivo) {
		this.anchoDispositivo = anchoDispositivo;
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

	public boolean isSeleccionImpuestos() {
		return seleccionImpuestos;
	}

	public void setSeleccionImpuestos(boolean seleccionImpuestos) {
		this.seleccionImpuestos = seleccionImpuestos;
	}
	//================RESPONSIVE======================
	public String getNombres() {
		return nombres;
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

	@Init
	public void Inicializar() {
		seleccionDisponibilidad=seleccionEtiquetas=seleccionImpuestos=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
		visibleDisponibilidad=visibleEtiqueta = visiblePaquetes = visibleServicios = visibleSubServicios = visibleImpuestos =false;
	}

	//================CAMBIO DE VISIBILIDAD========
	@Command
	@NotifyChange({ "visibleEtiqueta", "visiblePaquetes", "visibleServicios", "visibleSubServicios", "visibleImpuestos", "visibleMenu", "visibleDisponibilidad",
		"seleccionDisponibilidad","seleccionEtiquetas", "seleccionPaquetes", "seleccionServicios", "seleccionSubServicios", "seleccionImpuestos" })
	public void Cambio(@BindingParam("cambioInterfaz") String cambios) {
			if (cambios.equals("itemDisponibilidad")) {
				visibleDisponibilidad= seleccionDisponibilidad= true;
			} else if (cambios.equals("itemEtiqueta")) {
				visibleEtiqueta =seleccionEtiquetas= true; 
			} else if (cambios.equals("itemPaquete")) {
				visiblePaquetes=seleccionPaquetes = true;
			} else if (cambios.equals("itemServicio")) {
				visibleServicios=seleccionServicios = true;
			} else if (cambios.equals("itemSubServicio")) {
				visibleSubServicios =seleccionSubServicios= true;
			} else if (cambios.equals("itemImpuesto")) {
				visibleImpuestos=seleccionImpuestos= true;
			}
			
			if (cambios.equals("itemDisponibilidad")) {
				visibleDisponibilidad = seleccionDisponibilidad=true; 
				seleccionEtiquetas=seleccionImpuestos=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			} else if (cambios.equals("itemEtiqueta")) {
				visibleEtiqueta = seleccionEtiquetas=true;
				seleccionDisponibilidad=seleccionImpuestos=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			} else if (cambios.equals("itemPaquete")) {
				visiblePaquetes = seleccionPaquetes=true;
				seleccionEtiquetas=seleccionImpuestos=seleccionDisponibilidad=seleccionServicios=seleccionSubServicios=false;
			} else if (cambios.equals("itemServicio")) {
				visibleServicios = seleccionServicios=true;
				seleccionEtiquetas=seleccionImpuestos=seleccionPaquetes=seleccionDisponibilidad=seleccionSubServicios=false;
			} else if (cambios.equals("itemSubServicio")) {
				visibleSubServicios = seleccionSubServicios=true;
				seleccionEtiquetas=seleccionImpuestos=seleccionPaquetes=seleccionServicios=seleccionDisponibilidad=false;
			} else if (cambios.equals("itemImpuesto")) {
				visibleImpuestos= seleccionImpuestos=true;
				seleccionEtiquetas=seleccionDisponibilidad=seleccionPaquetes=seleccionServicios=seleccionSubServicios=false;
			}
	}
	
	//=================CERRRAR TABS===============
	@Command
	@NotifyChange({ "visibleEtiqueta", "visiblePaquetes", "visibleServicios", "visibleSubServicios", "visibleImpuestos","visibleMenu","nombres","visibleDisponibilidad"})
	public void CerrarTab(@BindingParam("cerrarInterfaz") String cambios) {

			if (cambios.equals("tabDisponibilidad")) {
				visibleDisponibilidad = false;
			} else if (cambios.equals("tabEtiqueta")) {
				visibleEtiqueta = false;
			} else if (cambios.equals("tabPaquete")) {
				visiblePaquetes = false;
			} else if (cambios.equals("tabServicio")) {
				visibleServicios = false;
			} else if (cambios.equals("tabSubServicio")) {
				visibleSubServicios = false;
			} else if (cambios.equals("tabImpuesto")) {
				visibleImpuestos= false;
			}
			
			if (cambios.equals("tabDisponibilidad")) {
				visibleDisponibilidad = false;
			} else if (cambios.equals("tabEtiqueta")) {
				visibleEtiqueta = false;
			} else if (cambios.equals("tabPaquete")) {
				visiblePaquetes = false;
			} else if (cambios.equals("tabServicio")) {
				visibleServicios = false;
			} else if (cambios.equals("tabSubServicio")) {
				visibleSubServicios = false;
			} else if (cambios.equals("tabImpuesto")) {
				visibleImpuestos= false;
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
	  @NotifyChange({"visibleBoton","visibleMenu","windowMode"})
	  public void muestra(@BindingParam("width")int ancho){
		      anchoDispositivo=ancho;
			  
	  }
	  
	  @Command
	  @NotifyChange({"windowMode","openItemConfig","openItemUsuarios","openItemPaquetes"})
	  public void menuDesplegable()
	  {   
		  openItemConfig=openItemUsuarios=openItemPaquetes=false;	  
	  }
	  @Command
	  @NotifyChange("visibleEditar")
	  public void Editar()
	  {
		  String ancho=String.valueOf(anchoDispositivo);
		  Messagebox.show(ancho);
		  if(anchoDispositivo<400)
		  {
			  visibleEditar=true;
		  }
		  else
		  {
			  visibleEditar=false;
		  }
	  }
}
