package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CImpuestoDAO;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CImpuesto;

public class impuestoYcobrosVM 
{
	/**
	 * ATRIBUTOS
	 */
	private CImpuesto oImpuesto;
	private CImpuestoDAO impuestoDao;
	private ArrayList<CImpuesto> listaImpuestos;
	/**
	 * GETTER AND SETTER
	 */
	public CImpuesto getoImpuesto() {
		return oImpuesto;
	}
	public void setoImpuesto(CImpuesto oImpuesto) {
		this.oImpuesto = oImpuesto;
	}
	public CImpuestoDAO getImpuestoDao() {
		return impuestoDao;
	}
	public void setImpuestoDao(CImpuestoDAO impuestoDao) {
		this.impuestoDao = impuestoDao;
	}
	public ArrayList<CImpuesto> getListaImpuestos() {
		return listaImpuestos;
	}
	public void setListaImpuestos(ArrayList<CImpuesto> listaImpuestos) {
		this.listaImpuestos = listaImpuestos;
	}
	/**
	 * METODOS Y FUNCIONES DE LA CLASE
	 */
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		oImpuesto=new CImpuesto();
		impuestoDao=new CImpuestoDAO();
		listaImpuestos=new ArrayList<CImpuesto>();
		/**Obtencion de las etiquetas de la base de datos**/
		listaImpuestos.add(impuestoDao.recuperarImpuestosBD());
	}
	@Command
	public void actualizarImpuesto(@BindingParam("impuesto")CImpuesto impuesto)
	{
		impuesto.setEditable(false);
		refrescaFilaTemplate(impuesto);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=impuestoDao.isOperationCorrect(impuestoDao.modificarImpuesto(impuesto));
//		initVM();
//		System.out.println("-->"+impuesto.getCodEtiqueta());
		
	}
	@Command
	 public void Editar(@BindingParam("impuesto") CImpuesto i ) 
	{
		oImpuesto.setEditable(false);
		refrescaFilaTemplate(oImpuesto);
		oImpuesto=i;
		//le damos estado editable
		i.setEditable(!i.isEditable());	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(i);
   }
	public void refrescaFilaTemplate(CImpuesto i)
	{
		BindUtils.postNotifyChange(null, null, i, "editable");
	}
}
