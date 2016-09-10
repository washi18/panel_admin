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
	private boolean visibleEspaniol=false;
	private boolean visiblePortugues=false;
	private boolean visibleIngles=false;
	private boolean visibleIdioma4=false;
	private boolean visibleIdioma5=false;
	private boolean visiblecmbIdiomas=false;
	private boolean visiblebtnActualizar=false;
	/**
	 * GETTER AND SETTER
	 */
	
	public CEtiqueta getoEtiqueta() {
		return oEtiqueta;
	}
	
	public boolean isVisiblebtnActualizar() {
		return visiblebtnActualizar;
	}

	public void setVisiblebtnActualizar(boolean visiblebtnActualizar) {
		this.visiblebtnActualizar = visiblebtnActualizar;
	}

	public boolean isVisiblecmbIdiomas() {
		return visiblecmbIdiomas;
	}

	public void setVisiblecmbIdiomas(boolean visiblecmbIdiomas) {
		this.visiblecmbIdiomas = visiblecmbIdiomas;
	}

	public boolean isVisibleEspaniol() {
		return visibleEspaniol;
	}

	public void setVisibleEspaniol(boolean visibleEspaniol) {
		this.visibleEspaniol = visibleEspaniol;
	}

	public boolean isVisiblePortugues() {
		return visiblePortugues;
	}
	public void setVisiblePortugues(boolean visiblePortugues) {
		this.visiblePortugues = visiblePortugues;
	}
	public boolean isVisibleIngles() {
		return visibleIngles;
	}
	public void setVisibleIngles(boolean visibleIngles) {
		this.visibleIngles = visibleIngles;
	}
	public boolean isVisibleIdioma4() {
		return visibleIdioma4;
	}
	public void setVisibleIdioma4(boolean visibleIdioma4) {
		this.visibleIdioma4 = visibleIdioma4;
	}
	public boolean isVisibleIdioma5() {
		return visibleIdioma5;
	}
	public void setVisibleIdioma5(boolean visibleIdioma5) {
		this.visibleIdioma5 = visibleIdioma5;
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
	
	public CEtiquetaDAO getEtiquetaDao() {
		return etiquetaDao;
	}

	public void setEtiquetaDao(CEtiquetaDAO etiquetaDao) {
		this.etiquetaDao = etiquetaDao;
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
	@NotifyChange({"visibleEspaniol","visibleIngles","visiblePortugues","visiblebtnActualizar","visibleIdioma4","visibleIdioma5","visiblecmbIdiomas"})
	public void actualizarEtiqueta(@BindingParam("etiqueta")CEtiqueta etiqueta)
	{
		visiblecmbIdiomas=visiblebtnActualizar=visibleEspaniol=visibleIngles=visiblePortugues=visibleIdioma4=visibleIdioma5=false;
		etiqueta.setEditable(false);
		refrescaFilaTemplate(etiqueta);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=etiquetaDao.isOperationCorrect(etiquetaDao.modificarEtiqueta(etiqueta));
//		initVM();
		System.out.println("-->"+etiqueta.getCodEtiqueta());
		etiqueta.setColor(etiqueta.COLOR_NO_SELECT);
	}
	
	@Command
	@NotifyChange({"oEtiqueta","visiblecmbIdiomas","visiblebtnActualizar"})
	 public void Editar(@BindingParam("etiqueta") CEtiqueta e ) 
	{
		visiblecmbIdiomas=visiblebtnActualizar=true;
		oEtiqueta.setEditable(false);
		refrescaFilaTemplate(oEtiqueta);
		oEtiqueta=e;
		//le damos estado editable
		e.setEditable(!e.isEditable());	
		e.setColor(e.COLOR_SELECT);
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(e);
   }
	
	@Command
	@NotifyChange({"visibleEspaniol","visibleIngles","visiblePortugues","visibleIdioma4","visibleIdioma5"})
	public void EditarIdiomas(@BindingParam("idioma")String idIdioma){
		if(idIdioma.equals("cmbEspa�ol")){visibleEspaniol=true; visibleIngles=visiblePortugues=visibleIdioma4=visibleIdioma5=false;}
		else if(idIdioma.equals("cmbIngles")){visibleIngles=true; visibleEspaniol=visiblePortugues=visibleIdioma4=visibleIdioma5=false;}
		else if(idIdioma.equals("cmbPortugues")){visiblePortugues=true; visibleIngles=visibleEspaniol=visibleIdioma4=visibleIdioma5=false;}
		else if(idIdioma.equals("cmbIdioma4")){visibleIdioma4=true; visibleIngles=visiblePortugues=visibleEspaniol=visibleIdioma5=false;}
		else if(idIdioma.equals("cmbIdioma5")){visibleIdioma5=true; visibleIngles=visiblePortugues=visibleIdioma4=visibleEspaniol=false;}
	}
	public void refrescaFilaTemplate(CEtiqueta e)
	{
		BindUtils.postNotifyChange(null, null, e, "editable");
		BindUtils.postNotifyChange(null, null, e, "color");
	}
}
