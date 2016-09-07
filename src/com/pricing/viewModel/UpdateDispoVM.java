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

public class UpdateDispoVM 
{
	@Wire
	Textbox txtNombreArchivo;
	@Wire
	Label lblMensaje;
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
