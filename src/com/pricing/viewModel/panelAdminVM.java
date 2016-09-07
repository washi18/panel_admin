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
	@Wire
	Textbox txtNombreArchivo;
	@Wire
	Label lblMensaje;
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
	private boolean visibleMenu = false;
	private String windowMode = "embedded";
	private boolean visibleBoton= false;
	private String nombres="";
	private boolean visibleTabbox=false;
	
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
	
	public boolean isVisibleMenu() {
		return visibleMenu;
	}

	public void setVisibleMenu(boolean visibleMenu) {
		this.visibleMenu = visibleMenu;
	}

	public String getWindowMode() {
		return windowMode;
	}

	public void setWindowMode(String windowMode) {
		this.windowMode = windowMode;
	}

	public boolean isVisibleBoton() {
		return visibleBoton;
	}

	public void setVisibleBoton(boolean visibleBoton) {
		this.visibleBoton = visibleBoton;
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
		if(windowMode.equals("popup"))
		{
			if (cambios.equals("itemDisponibilidad")) {
				visibleDisponibilidad = true; visibleMenu=false;
			} else if (cambios.equals("itemEtiqueta")) {
				visibleEtiqueta = true; visibleMenu=false; 
			} else if (cambios.equals("itemPaquete")) {
				visiblePaquetes = true; visibleMenu=false;
			} else if (cambios.equals("itemServicio")) {
				visibleServicios = true; visibleMenu=false;
			} else if (cambios.equals("itemSubServicio")) {
				visibleSubServicios = true; visibleMenu=false;
			} else if (cambios.equals("itemImpuesto")) {
				visibleImpuestos= true; visibleMenu=false;
			}
		}else {
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
	}
	
	//=================CERRRAR TABS===============
	@Command
	@NotifyChange({ "visibleEtiqueta", "visiblePaquetes", "visibleServicios", "visibleSubServicios", "visibleImpuestos","visibleMenu","nombres","visibleDisponibilidad"})
	public void CerrarTab(@BindingParam("cerrarInterfaz") String cambios) {
		if(windowMode.equals("popup"))
		{
			if (cambios.equals("tabDisponibilidad")) {
				visibleDisponibilidad = false; visibleMenu=false;
			} else if (cambios.equals("tabEtiqueta")) {
				visibleEtiqueta = false; visibleMenu=false; 
			} else if (cambios.equals("tabPaquete")) {
				visiblePaquetes = false; visibleMenu=false;
			} else if (cambios.equals("tabServicio")) {
				visibleServicios = false; visibleMenu=false;
			} else if (cambios.equals("tabSubServicio")) {
				visibleSubServicios = false; visibleMenu=false;
			} else if (cambios.equals("tabImpuesto")) {
				visibleImpuestos= false; visibleMenu=false;
			}
		}else {
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
			  if(ancho<700){visibleBoton=true;}
			  else {visibleBoton=false; windowMode="embedded"; visibleMenu=true;}
	  }
	  
	  @Command
	  @NotifyChange({"visibleMenu","windowMode","openItemConfig","openItemUsuarios","openItemPaquetes"})
	  public void menuDesplegable()
	  {   
		  openItemConfig=openItemUsuarios=openItemPaquetes=false;
		  windowMode="popup"; 
		  visibleMenu=true;		  
	  }
	  
	  @Command
	  @NotifyChange("visibleMenu")
	  public void CerrarPopup()
	  {
		  visibleMenu=false;
	  }
	//==============CARGAR PDF====================
		@Command
		 public void uploadFile(@BindingParam("id") final String id) {
			 Fileupload.get(new EventListener<UploadEvent>(){
					public void onEvent(UploadEvent event) 
					{
						org.zkoss.util.media.Media media = event.getMedia();
						//Se procede a validar la extension del archivo
						if(media.getFormat().equals("pdf"))
						{
							String contentPdf=obtenerContenidoPDF(media);
							if(!validoContenidoPDF(contentPdf))
								return;
							String mes=obtenerMesDelPdf(contentPdf);
							String monthFileName=obtenerNombreArchivoDelMes(mes);
							System.out.println("mes recuperado->"+mes+"nombre file->"+monthFileName);
							//Ahora se procede a sobreescribir el archivo txt correspodiente al
							//mes de Disponibilidad con el contenido obtenido del pdf
							try {
								sobreescribirFileTxt(contentPdf,monthFileName);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//=================================
							asignarNombreFile(media.getName());
						} else {
							Messagebox.show("Not an PDF File: "+media, "Error", Messagebox.OK, Messagebox.ERROR);
								}
					}
			     });
		 }
		public boolean validoContenidoPDF(String contenidoFile)
		{
			boolean valido=true;
			String[]  contenido=contenidoFile.split("\n");
			for(int i=0;i<contenido.length;i++)
				System.out.println("==> ("+i+")"+contenido[i]+" tmanio--> "+contenido.length);
			/**validamos los textos de la DRC y CAMINO INCA**/
			String primero=contenido[0];
			String segundo=contenido[3];
			int u1=primero.length()-1;
			int u2=segundo.length()-1;
			if(primero.charAt(u1)<'A' || primero.charAt(u1)>'Z')
				primero=primero.substring(0, u1);
			if(segundo.charAt(u2)<'A' || segundo.charAt(u2)>'Z')
				segundo=segundo.substring(0, u2);
			/**************************************************/
			if(!primero.equals("DRC CUSCO") || !segundo.equals("CAMINO INCA"))
			{
				Clients.showNotification("El archivo no corresponde a la DRC CUSCO/CAMINO INCA",Clients.NOTIFICATION_TYPE_ERROR,null,"top_center",3000);
				valido=false;
			}
			return valido;
		}
		public void sobreescribirFileTxt(String contentPdf,String monthFileName) throws IOException
		{
	        File archivo = new File(Util.getPath()+monthFileName);
	        BufferedWriter bw;
	        if(archivo.exists()) {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(contentPdf);
	        } else {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(contentPdf);
	        }
	        bw.close();
		}
		public String obtenerNombreArchivoDelMes(String mes)
		{
			String nameFile="";
			//Esta condicional se hizo debido a que el nombre recuperado
			//de los meses de SEPTIEMBRE, NOVIEMBRE Y DICIEMBRE
			//tienen un espacio (" ") al final ejem. "DICIEMBRE " y no "DICIEMBRE"
			//imposibilitando la validacion en el switch
			int ultimo=mes.length()-1;
			if(mes.charAt(ultimo)<'A' || mes.charAt(ultimo)>'Z')
			{
				mes=mes.substring(0, ultimo);
				System.out.println("----> "+mes);
			}
			switch(mes)
			{
				case "ENERO":nameFile="enero.txt";break;
				case "MARZO":nameFile="marzo.txt";break;
				case "ABRIL":nameFile="abril.txt";break;
				case "MAYO":nameFile="mayo.txt";break;
				case "JUNIO":nameFile="junio.txt";break;
				case "JULIO":nameFile="julio.txt";break;
				case "AGOSTO":nameFile="agosto.txt";break;
				case "SEPTIEMBRE":nameFile="setiembre.txt";break;
				case "OCTUBRE":nameFile="octubre.txt";break;
				case "NOVIEMBRE":nameFile="noviembre.txt";break;
				case "DICIEMBRE":nameFile="diciembre.txt";break;
			}
			return nameFile;
		}
		public String obtenerContenidoPDF(Media media)
		{
			String contenido="";
			//primeramente subimos el archivo al proyecto
			boolean b=Util.uploadFileAux(media);
			//Luego se procede a leer el pdf guardado para obtener el nombre del mes
			lectorPDF lpdf=new lectorPDF();
			lpdf.setFilePath(media.getName());
			try {
				contenido=lpdf.ToText();
				//Una vez obtenido el contenido del pdf se procede a eliminar este
				File fileDelete=new File(Util.getPathAuxDisp()+media.getName());
				if(fileDelete.delete())
					System.out.println("se elimino el pdf auxiliar");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return contenido;
		}
		public String obtenerMesDelPdf(String contenidoPDF)
		{
			String[] datos=contenidoPDF.split("\n",4);
			String[] auxDatos=datos[1].split(" ");
			for(int i=0;i<auxDatos.length;i++)
			{
				System.out.println("====> "+auxDatos[i]);
			}
			//=================================
			lblMensaje.setValue("Se agrego el mes de: "+auxDatos[7]);
			return auxDatos[7];
		}
		public void asignarNombreFile(String nombreFile)
		{
			txtNombreArchivo.setValue(nombreFile);
		}
		@AfterCompose
		public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws WrongValueException, IOException
		{
			Selectors.wireComponents(view, this, false);
		}
}
