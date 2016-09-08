package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.model.CEtiqueta;
import com.sun.mail.handlers.message_rfc822;

public class etiquetaVM 
{
	/**
	 * ATRIBUTOS
	 */
	private CEtiqueta oEtiqueta;
	private CEtiquetaDAO etiquetaDao;
	private ArrayList<CEtiqueta> listaEtiquetas;
	private boolean visibleEditar=false;
	private int anchoDispositivo;
	/**
	 * GETTER AND SETTER
	 */
	
	public CEtiqueta getoEtiqueta() {
		return oEtiqueta;
	}
	public int getAnchoDispositivo() {
		return anchoDispositivo;
	}
	public void setAnchoDispositivo(int anchoDispositivo) {
		this.anchoDispositivo = anchoDispositivo;
	}
	public boolean isVisibleEditar() {
		return visibleEditar;
	}
	public void setVisibleEditar(boolean visibleEditar) {
		this.visibleEditar = visibleEditar;
	}
	public void setoEtiqueta(CEtiqueta oEtiqueta) {
		this.oEtiqueta = oEtiqueta;
	}
	public ArrayList<CEtiqueta> getListaEtiquetas() {
		return listaEtiquetas;
	}
	public void setListaEtiquetas(ArrayList<CEtiqueta> listaEtiquetas) {
		this.listaEtiquetas = listaEtiquetas;
	}
	/**
	 * METODOS Y FUNCIONES DE LA CLASE
	 */
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		oEtiqueta=new CEtiqueta();
		etiquetaDao=new CEtiquetaDAO();
		listaEtiquetas=new ArrayList<CEtiqueta>();
		/**Obtencion de las etiquetas de la base de datos**/
		etiquetaDao.asignarListaEtiquetas(etiquetaDao.recuperarEtiquetasBD());
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		setListaEtiquetas(etiquetaDao.getListaEtiquetas());
	}
	@Command
	public void actualizarEtiqueta(@BindingParam("etiqueta")CEtiqueta etiqueta)
	{
		etiqueta.setEditable(false);
		refrescaFilaTemplate(etiqueta);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=etiquetaDao.isOperationCorrect(etiquetaDao.modificarEtiqueta(etiqueta));
//		initVM();
		System.out.println("-->"+etiqueta.getCodEtiqueta());
		
	}
	
	@Command
	public void anchoDisp(@BindingParam("ancho")int anchoD)
	{
		String numero=String.valueOf(anchoD);
		Messagebox.show(numero);
		anchoDispositivo=anchoD;
	}
	
	@Command
	@NotifyChange({"visibleEditar","oEtiqueta"})
	 public void Editar(@BindingParam("etiqueta") CEtiqueta e ) 
	{
		if(anchoDispositivo<400){visibleEditar=true;} 
		else{visibleEditar=false;}
		oEtiqueta.setEditable(false);
		refrescaFilaTemplate(oEtiqueta);
		oEtiqueta=e;
		//le damos estado editable
		e.setEditable(!e.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(e);
   }
	
	public void refrescaFilaTemplate(CEtiqueta e)
	{
		BindUtils.postNotifyChange(null, null, e, "editable");
	}
}
