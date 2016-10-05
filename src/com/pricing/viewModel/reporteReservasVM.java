package com.pricing.viewModel;

import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CHotel;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CReporteReservaMuestra;

public class reporteReservasVM {
	//======atributos=====
	private ArrayList<CReporteReserva> listaReporteReserva;
	private ArrayList<CReporteReservaMuestra> listanuevaReporteReserva;
	private CReporteReservaDAO reporteReservaDAO;
	private boolean estadoPagoPendiente;
	private boolean estadoPagoParcial;
	private boolean estadoPagoTotal;
	private String FechaInicio;
	private String FechaFinal;
	private ArrayList<String> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<String> listaServicios;
	private ArrayList<String> listasubServicios;
	
	//=======getter and setter=====
	
	public boolean isEstadoPagoPendiente() {
		return estadoPagoPendiente;
	}
	
	public ArrayList<CReporteReserva> getListaReporteReserva() {
		return listaReporteReserva;
	}

	public void setListaReporteReserva(
			ArrayList<CReporteReserva> listaReporteReserva) {
		this.listaReporteReserva = listaReporteReserva;
	}

	public CReporteReservaDAO getReporteReservaDAO() {
		return reporteReservaDAO;
	}
	public void setReporteReservaDAO(CReporteReservaDAO reporteReservaDAO) {
		this.reporteReservaDAO = reporteReservaDAO;
	}
	public void setEstadoPagoPendiente(boolean estadoPagoPendiente) {
		this.estadoPagoPendiente = estadoPagoPendiente;
	}
	public boolean isEstadoPagoParcial() {
		return estadoPagoParcial;
	}
	public void setEstadoPagoParcial(boolean estadoPagoParcial) {
		this.estadoPagoParcial = estadoPagoParcial;
	}
	public boolean isEstadoPagoTotal() {
		return estadoPagoTotal;
	}
	public void setEstadoPagoTotal(boolean estadoPagoTotal) {
		this.estadoPagoTotal = estadoPagoTotal;
	}
	public String getFechaInicio() {
		return FechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public String getFechaFinal() {
		return FechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		FechaFinal = fechaFinal;
	}

	public ArrayList<CReporteReservaMuestra> getListanuevaReporteReserva() {
		return listanuevaReporteReserva;
	}

	public void setListanuevaReporteReserva(
			ArrayList<CReporteReservaMuestra> listanuevaReporteReserva) {
		this.listanuevaReporteReserva = listanuevaReporteReserva;
	}
	
	public ArrayList<String> getListaDestinos() {
		return listaDestinos;
	}

	public void setListaDestinos(ArrayList<String> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}
	
	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}

	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}

	public ArrayList<String> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(ArrayList<String> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public ArrayList<String> getListasubServicios() {
		return listasubServicios;
	}

	public void setListasubServicios(ArrayList<String> listasubServicios) {
		this.listasubServicios = listasubServicios;
	}

	//======metodos=====
	@Init
	public void initVM()
	{
		estadoPagoParcial=false;
		estadoPagoPendiente=false;
		estadoPagoTotal=false;
		/**Inicializando los objetos**/
		listaReporteReserva=new ArrayList<CReporteReserva>();
		listanuevaReporteReserva=new ArrayList<CReporteReservaMuestra>();
		reporteReservaDAO=new CReporteReservaDAO();
		listaHoteles=new ArrayList<CHotel>();
		FechaInicio="";
		FechaFinal="";
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
	}
	
	@Command
	public void recuperarFechaDatebox(@BindingParam("fecha")String fecha,@BindingParam("id")String id)
	{
		if(id.equals("db_desde"))
			FechaInicio=fecha;
		else
			FechaFinal=fecha;
	}
	@Command
	@NotifyChange({"listaReporteReserva","listanuevaReporteReserva"})
	public void Buscar_Reservas(@BindingParam("componente")Component componente)
	{
		System.out.println("La fecha inicio es: "+FechaInicio);
		System.out.println("La fecha final es: "+FechaFinal);
		System.out.println("el pago parcial es: "+estadoPagoParcial);
		System.out.println("el pago pendiente es: "+estadoPagoPendiente);
		System.out.println("el pago total es: "+estadoPagoTotal);
		if(FechaInicio.isEmpty() || FechaFinal.isEmpty())
		{
			Clients.showNotification("Las fechas DESDE-HASTA son obligatorias ", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
		else if(estadoPagoParcial==true || estadoPagoPendiente==true || estadoPagoTotal==true)
		{
			//-------despedasando la fecha desde------
			String diaStart=FechaInicio.substring(0,2);
			String mesStart=cambiarFormatoMes(FechaInicio.substring(3,6));
			String anioStart=FechaInicio.substring(7,11);
			//-------despedasando la fecha hasta------
			String diaEnd=FechaFinal.substring(0,2);
			String mesEnd=cambiarFormatoMes(FechaFinal.substring(3,6));
			String anioEnd=FechaFinal.substring(7,11);
			/*************Fecha Inicio*******************/
			String fecha1=anioStart+"-"+mesStart+"-"+diaStart;
			String fecha2=anioEnd+"-"+mesEnd+"-"+diaEnd;
			/****Validando la fecha****/
			String NombrePago="";
			if(estadoPagoPendiente)
			{
				estadoPagoPendiente=false;
				NombrePago="PENDIENTE DE PAGO";
			}else if(estadoPagoParcial){
				estadoPagoParcial=false;
				NombrePago="PAGO PARCIAL";
			}else if(estadoPagoTotal){
				estadoPagoTotal=false;
				NombrePago="PAGO TOTAL";
			}
			listaReporteReserva.clear();
			System.out.println("entro aqui 1");
			reporteReservaDAO.asignarListaReporteReservas(reporteReservaDAO.recuperarReporteReservasBD(fecha1,fecha2,NombrePago));
			this.setListaReporteReserva(reporteReservaDAO.getListaReporteReservas());
			System.out.println("entro aqui 2");
			String codReservaAnterior,destinoAnterior,hotelAnterior,servicioAnterior,subservicioAnterior;
			listaDestinos=new ArrayList<String>();
			listaServicios=new ArrayList<String>();
			listasubServicios=new ArrayList<String>();
			System.out.println("entro aqui 3");
			codReservaAnterior="";
			int factorIncremento=0;
			System.out.println("el nro de filas es:"+listaReporteReserva.size());
			for(int i=0;i<listaReporteReserva.size();i=i+factorIncremento)
			{	
				factorIncremento=0;
				codReservaAnterior=listaReporteReserva.get(i).getCodReserva();
				int contador=i;
				System.out.println("el valor del contador:"+contador);
				destinoAnterior="";
				hotelAnterior="";
				servicioAnterior="";
				subservicioAnterior="";
				System.out.println("el valor de reserva anterior es:"+codReservaAnterior);
				while((contador<listaReporteReserva.size()) && (listaReporteReserva.get(contador).getCodReserva().equals(codReservaAnterior)))
				{
					System.out.println("el valor de cod reserva:"+listaReporteReserva.get(contador).getCodReserva());
					System.out.println("el valor del destino es:"+listaReporteReserva.get(contador).getDestinos());
					if(listaReporteReserva.get(contador).getDestinos()==null){
						System.out.println("sale este null");
						destinoAnterior = listaReporteReserva.get(contador).getDestinos();
					}else if(!listaReporteReserva.get(contador).getDestinos().equals(destinoAnterior)) {
						destinoAnterior = listaReporteReserva.get(contador).getDestinos();
						listaDestinos.add(listaReporteReserva.get(contador).getDestinos().toString());
					}
					
					if(listaReporteReserva.get(contador).getHoteles()==null){
						hotelAnterior = listaReporteReserva.get(contador).getHoteles();
					}else if(!listaReporteReserva.get(contador).getHoteles().equals(hotelAnterior)) {
						hotelAnterior = listaReporteReserva.get(contador).getHoteles();
						listaHoteles.add(new CHotel(listaReporteReserva.get(contador).getHoteles()));
					}
					
					if(listaReporteReserva.get(contador).getServicios()==null){
						servicioAnterior = listaReporteReserva.get(contador).getServicios();
					}else if(!listaReporteReserva.get(contador).getServicios().equals(servicioAnterior)) {
						servicioAnterior = listaReporteReserva.get(contador).getServicios();
						listaServicios.add(listaReporteReserva.get(contador).getServicios().toString());
					}
					System.out.println("el valor del destino es:"+listaReporteReserva.get(contador).getSubServicios());
					if(listaReporteReserva.get(contador).getSubServicios()==null){
						servicioAnterior = listaReporteReserva.get(contador).getSubServicios();
					}else if (!listaReporteReserva.get(contador).getSubServicios().equals(subservicioAnterior)) {
						subservicioAnterior = listaReporteReserva.get(contador).getSubServicios();
						listasubServicios.add(listaReporteReserva.get(contador).getSubServicios());
					}
					contador++;
					System.out.println("el valor del contador:"+contador);
					factorIncremento++;
					System.out.println("valor del factor de incremento"+factorIncremento);
				}
				System.out.println("aqui esta el tamanio de listahotel "+listaHoteles.size());
				listanuevaReporteReserva.add(new CReporteReservaMuestra(listaReporteReserva.get(i).getCodReserva(),listaReporteReserva.get(i).getFechaInicio(),listaReporteReserva.get(i).getFechaFin(),
				listaReporteReserva.get(i).getFecha(),listaReporteReserva.get(i).getNombreContacto(),listaReporteReserva.get(i).getEmail(),listaReporteReserva.get(i).getTelefono(),
				listaReporteReserva.get(i).getNroPersonas(),listaReporteReserva.get(i).getPrecioPersona(),listaReporteReserva.get(i).getNombrePaquete(),listaReporteReserva.get(i).getCategoria(),
				listaDestinos,listaHoteles,listaServicios,listasubServicios,listaReporteReserva.get(i).getEstado()));
				FechaInicio="";
				FechaFinal="";
				listaDestinos.clear();
				listaHoteles.clear();
				listaServicios.clear();
				listasubServicios.clear();
				System.out.println("entro aqui 7");
			}
			
		}else{
			Clients.showNotification("Eliga un ESTADO DE PAGO", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
	}
	
	public String cambiarFormatoMes(String mes)
	{
		String nuevo="";
		switch(mes)
		{
			case "ene":nuevo="01";break;
			case "feb":nuevo="02";break;
			case "mar":nuevo="03";break;
			case "abr":nuevo="04";break;
			case "may":nuevo="05";break;
			case "jun":nuevo="06";break;
			case "jul":nuevo="07";break;
			case "ago":nuevo="08";break;
			case "sep":nuevo="09";break;
			case "oct":nuevo="10";break;
			case "nov":nuevo="11";break;
			case "dic":nuevo="12";break;
		}
		return nuevo;
	}
}
