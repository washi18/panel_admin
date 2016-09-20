package com.pricing.viewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CDestinoHotelDAO;
import com.pricing.dao.CHotelDAO;
import com.pricing.model.CHotel;
import com.pricing.model.CServicio;

public class hotelesVM 
{
	//====================
	private DecimalFormat df;
	private DecimalFormatSymbols simbolos;
	//====================
	/**=============**/
	/**==ATRIBUTOS==**/
	/**=============**/
	private CHotel oHotel;
	private CHotel oHotelUpdate;
	private ArrayList<CHotel> listaHoteles;
	private CHotelDAO hotelDao;
	/**=====================**/
	/**==GETTER AND SETTER==**/
	/**=====================**/
	public CHotel getoHotel() {
		return oHotel;
	}
	public void setoHotel(CHotel oHotel) {
		this.oHotel = oHotel;
	}
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}
	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}
	public CHotelDAO getHotelDao() {
		return hotelDao;
	}
	public void setHotelDao(CHotelDAO hotelDao) {
		this.hotelDao = hotelDao;
	}
	public CHotel getoHotelUpdate() {
		return oHotelUpdate;
	}
	public void setoHotelUpdate(CHotel oHotelUpdate) {
		this.oHotelUpdate = oHotelUpdate;
	}
	/**===========**/
	/**==METODOS==**/
	/**===========**/
	
	/**
	 * Inicializa el view model de hoteles
	 */
	@Init
	public void inicializarVM()
	{
		/*******************************/
		simbolos= new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		df=new DecimalFormat("########0.00",simbolos);
		/*******************************/
		oHotel=new CHotel();
		oHotelUpdate=new CHotel();
		hotelDao=new CHotelDAO();
		listaHoteles=new ArrayList<CHotel>();
		hotelDao.asignarListaHoteles(hotelDao.recuperarHotelesBD());
		setListaHoteles(hotelDao.getListaHoteles());
	}
	/**
	 * Funcion que permite asignar la categoria
	 * al nuevo hotel a insertar en la BD
	 * @param categoria
	 */
	@Command
	public void selectCategoria(@BindingParam("categoria")String categoria)
	{
		if(categoria.equals("economico"))
			oHotel.setCategoriaHotelCod(1);
		else if(categoria.equals("turistico"))
			oHotel.setCategoriaHotelCod(2);
		else if(categoria.equals("turistico_superior"))
			oHotel.setCategoriaHotelCod(3);
		else if(categoria.equals("primera"))
			oHotel.setCategoriaHotelCod(4);
		else if(categoria.equals("primera_superior"))
			oHotel.setCategoriaHotelCod(5);
		else if(categoria.equals("lujo"))
			oHotel.setCategoriaHotelCod(6);
		else if(categoria.equals("lujo_superior"))
			oHotel.setCategoriaHotelCod(7);
	}
	@Command
	@NotifyChange({"oHotel"})
	public void InsertarHotel(@BindingParam("componente")Component componente)
	{
		/**Empezamos realizando las validaciones respectivas**/
		if(oHotel.getcHotel().equals(""))//Nombre del Hotel
		{
			Clients.showNotification("El nuevo hotel no tiene nombre", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oHotel.getCategoriaHotelCod()==0)
		{
			Clients.showNotification("Debe seleccionar una categoria para el hotel", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oHotel.getnPrecioSimple().doubleValue()==0)
		{
			Clients.showNotification("El precio de una habitacion simple no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oHotel.getnPrecioDoble().doubleValue()==0)
		{
			Clients.showNotification("El precio de una habitacion doble no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}else if(oHotel.getnPrecioTriple().doubleValue()==0)
		{
			Clients.showNotification("El precio de una habitacion Triple no puede ser $ 0.00", Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start",2700);
			return;
		}
		/**Una vez validado los datos necesarios se procede a insertar el nuevo Hotel**/
		boolean correcto=hotelDao.isOperationCorrect(hotelDao.insertarHotel(oHotel));
		if(correcto)
		{ 
			oHotel=new CHotel();
			Clients.showNotification("El Nuevo Hotel fue insertado correctamente", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
		}
		else
			Clients.showNotification("El Nuevo Hotel no fue insertado", Clients.NOTIFICATION_TYPE_INFO, componente,"before_start",2700);
	}
	@Command
	public void actualizarHotel(@BindingParam("hotel")CHotel hotel)
	{	
		System.out.println("--> "+hotel);
		hotel.setEditable(false);
		refrescaFilaTemplate(hotel);
		/**Actualizar datos de la etiqueta en la BD**/
		boolean b=hotelDao.isOperationCorrect(hotelDao.modificarHotel(hotel));
	}
	/**
	 * Funcion que permite Editar un hotel para actualizarlo
	 * desactivando previamente su su funcion editable del 
	 * anterior hotel editable "oHotelUpdate"
	 * @param h: Es el hotel que se va a actualizar
	 */
	@Command
	public void Editar(@BindingParam("hotel") CHotel h ) 
	{
		//oHotelUpdate viene a ser una variable auxiliar
		//que almacena el hotel previo editado y actualizado
		//para luego almacenar el hotel seleccionado para editar
		oHotelUpdate.setEditable(false);
		refrescaFilaTemplate(oHotelUpdate);
		oHotelUpdate=h;
		//le damos estado editable
		h.setEditable(true);	
		//lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(h);
   }
	@Command
	 public void Activar(@BindingParam("hotel") CHotel h ) 
	{
		
	}
	@Command
	 public void Desactivar(@BindingParam("hotel") CHotel h ) 
	{
		
	}
	@Command
	public void cambioIdiomas(@BindingParam("idioma")String idIdioma,@BindingParam("hotel")CHotel hotel)
	{
		if(idIdioma.equals("Espanol"))
		{
				hotel.setVisibleEspanol(true);
				hotel.setVisibleIngles(false);
				hotel.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Ingles"))
		{
				hotel.setVisibleEspanol(false);
				hotel.setVisibleIngles(true);
				hotel.setVisiblePortugues(false);
		}
		else if(idIdioma.equals("Portugues"))
		{
				hotel.setVisibleEspanol(false);
				hotel.setVisibleIngles(false);
				hotel.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, hotel, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, hotel, "visibleIngles");
		BindUtils.postNotifyChange(null, null, hotel, "visiblePortugues");
	}
	@Command
	public void cambiarCategoriaHotel(@BindingParam("categoria")String categoria,@BindingParam("hotel")CHotel oHotel)
	{
		if(categoria.equals("economico"))
			oHotel.setCategoriaHotelCod(1);
		else if(categoria.equals("turistico"))
			oHotel.setCategoriaHotelCod(2);
		else if(categoria.equals("turistico_superior"))
			oHotel.setCategoriaHotelCod(3);
		else if(categoria.equals("primera"))
			oHotel.setCategoriaHotelCod(4);
		else if(categoria.equals("primera_superior"))
			oHotel.setCategoriaHotelCod(5);
		else if(categoria.equals("lujo"))
			oHotel.setCategoriaHotelCod(6);
		else if(categoria.equals("lujo_superior"))
			oHotel.setCategoriaHotelCod(7);
	}
	@Command
	@NotifyChange({"oHotel"})
	public void changePrecios_nuevo(@BindingParam("precio")String precio,@BindingParam("hab")String hab,@BindingParam("componente")Component componente)
	{
		if(!isNumberDouble(precio))
		{
			if(hab.equals("simple"))
				oHotel.setnPrecioSimple_text(df.format(0));
			if(hab.equals("doble"))
				oHotel.setnPrecioDoble_text(df.format(0));
			if(hab.equals("triple"))
				oHotel.setnPrecioTriple_text(df.format(0));
			Clients.showNotification("Debe ser un numero de la forma ####.##",Clients.NOTIFICATION_TYPE_ERROR, componente,"before_start", 2700);
		}
		else
		{
			if(hab.equals("simple"))
				oHotel.setnPrecioSimple(Double.parseDouble(df.format(Double.parseDouble(precio))));
			if(hab.equals("doble"))
				oHotel.setnPrecioDoble(Double.parseDouble(df.format(Double.parseDouble(precio))));
			if(hab.equals("triple"))
				oHotel.setnPrecioTriple(Double.parseDouble(df.format(Double.parseDouble(precio))));
		}
	}
	public boolean isNumberDouble(String cad)
	{
		try
		 {
		   Double.parseDouble(cad);
		   return true;
		 }
		 catch(NumberFormatException nfe)
		 {
		   return false;
		 }
	}
	public void refrescaFilaTemplate(CHotel h)
	{
		BindUtils.postNotifyChange(null, null, h, "editable");
	}
}
