package com.pricing.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;

public class ScannUtil {
	
	public static final String separator = System.getProperty("file.separator");//Get de system separator
	
	public static boolean uploadFile(Media media) {
		System.out.println("aqui estoy="+separator);
		System.out.println("Esta es la ruta=>"+getPathImagensSubServicio());
		//return saveFile(media, "E:/SoftwareDevelopment/workspace/GPS/WebContent/image/unidades/");
		return saveFile(media,getPathImagensSubServicio());
	}
	
	//Gets the path of the current web application
	public static String getPath(){
		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)+"DocumentosScanneados"+separator;
	}
	
	public static String getPathImagensSubServicios(){
		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)+"img"+separator+"servicios"+separator;
	}
	
	public static String getPathImagensSubServicio(){
		return "D:\\ProyectosGitHub\\";
	}	
	//save file
	public static boolean saveFile(Media media, String path){
		boolean uploaded = false;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			InputStream ins = media.getStreamData();
			in = new BufferedInputStream(ins);

			String fileName = media.getName();
			File arc = new File(path + fileName);
			OutputStream aout = new FileOutputStream(arc);
			out = new BufferedOutputStream(aout);

			byte buffer[] = new byte[1024];
			int ch = in.read(buffer);
			while(ch != -1){
				out.write(buffer, 0, ch);
				ch = in.read(buffer);
			}
			uploaded = true;
			if(uploaded)System.out.println("Se llego a cargar la imagen scanneada");
		}catch (IOException ie) {
			throw new RuntimeException(ie);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
				try {
					if(out != null)
						out.close();
					if(in != null)
						in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
		return uploaded;
	}
}


