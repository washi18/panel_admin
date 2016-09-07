package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.model.CEtiqueta;

public class etiquetaVM 
{
	/**
	 * ATRIBUTOS
	 */
	private CEtiqueta oEtiqueta;
	private CEtiquetaDAO etiquetaDao;
	private ArrayList<CEtiqueta> listaEtiquetas;
	/**
	 * GETTER AND SETTER
	 */
	public CEtiqueta getoEtiqueta() {
		return oEtiqueta;
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
	 public void Editar(@BindingParam("etiqueta") CEtiqueta e ) 
	{
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
