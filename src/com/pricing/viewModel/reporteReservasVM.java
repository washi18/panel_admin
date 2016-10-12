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
import com.pricing.model.CDestino;
import com.pricing.model.CHotel;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CReporteReservaMuestra;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class reporteReservasVM {
	//======atributos=====
	private CReporteReservaMuestra reporteReserva;
	private ArrayList<CReporteReserva> listaReporteReserva;
	private ArrayList<CReporteReservaMuestra> listanuevaReporteReserva;
	private CReporteReservaDAO reporteReservaDAO;
	private String FechaInicio;
	private String FechaFinal;
	private ArrayList<CDestino> listaDestinos;
	private ArrayList<CHotel> listaHoteles;
	private ArrayList<CServicio> listaServicios;
	private ArrayList<CSubServicio> listasubServicios;
	
	//=======getter and setter=====
	
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
	

	public ArrayList<CDestino> getListaDestinos() {
		return listaDestinos;
	}

	public void setListaDestinos(ArrayList<CDestino> listaDestinos) {
		this.listaDestinos = listaDestinos;
	}

	public ArrayList<CHotel> getListaHoteles() {
		return listaHoteles;
	}

	public void setListaHoteles(ArrayList<CHotel> listaHoteles) {
		this.listaHoteles = listaHoteles;
	}

	public ArrayList<CServicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(ArrayList<CServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public ArrayList<CSubServicio> getListasubServicios() {
		return listasubServicios;
	}

	public void setListasubServicios(ArrayList<CSubServicio> listasubServicios) {
		this.listasubServicios = listasubServicios;
	}

	public CReporteReservaMuestra getReporteReserva() {
		return reporteReserva;
	}

	public void setReporteReserva(CReporteReservaMuestra reporteReserva) {
		this.reporteReserva = reporteReserva;
	}

	//======metodos=====
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		listaReporteReserva=new ArrayList<CReporteReserva>();
		FechaInicio="";
		FechaFinal="";
		reporteReservaDAO=new CReporteReservaDAO();
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
	}
	
	@Command
	@NotifyChange({"FechaInicio","FechaFinal"})
	public void recuperarFechaDatebox(@BindingParam("fecha")String fecha,@BindingParam("id")String id)
	{
		if(id.equals("db_desde"))
			FechaInicio=fecha;
		else
			FechaFinal=fecha;
	}
	
	@Command
	@NotifyChange({"listaReporteReserva","listanuevaReporteReserva","listaHoteles","reporteReserva"})
	public void Buscar_Reservas(@BindingParam("componente")Component componente)
	{
		if(FechaInicio.isEmpty() || FechaFinal.isEmpty())
		{
			Clients.showNotification("Las fechas DESDE-HASTA son obligatorias ", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
		else
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
			listaReporteReserva.clear();
			System.out.println("entro aqui 1");
			reporteReservaDAO.asignarListaReporteReservas(reporteReservaDAO.recuperarReporteReservasBD(fecha1,fecha2));
			this.setListaReporteReserva(reporteReservaDAO.getListaReporteReservas());
			System.out.println("entro aqui 2");
			String codReservaAnterior,destinoAnterior,hotelAnterior,servicioAnterior,subservicioAnterior;
			System.out.println("entro aqui 3");
			codReservaAnterior="";
			int factorIncremento=0;
			System.out.println("el nro de filas es:"+listaReporteReserva.size());
			listanuevaReporteReserva=new ArrayList<CReporteReservaMuestra>();
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
				listaDestinos=new ArrayList<CDestino>();
				listaServicios=new ArrayList<CServicio>();
				listasubServicios=new ArrayList<CSubServicio>();
				listaHoteles=new ArrayList<CHotel>();
				boolean continuaDestinos=true;
				boolean continuaHoteles=true;
				boolean continuaServicios=true;
				boolean hotelesVacios=false;
				boolean primerosHotelesVacios=false;
				if(listaReporteReserva.get(contador).getServicios()!=null)
					listaServicios.add(new CServicio(listaReporteReserva.get(contador).getServicios()));
				if(listaReporteReserva.get(contador).getSubServicios()!=null)
					listasubServicios.add(new CSubServicio(listaReporteReserva.get(contador).getSubServicios()));
				if(listaReporteReserva.get(contador).getDestinos()!=null)
					listaDestinos.add(new CDestino(listaReporteReserva.get(contador).getDestinos()));
				hotelAnterior=listaReporteReserva.get(contador).getHoteles();
				servicioAnterior=listaReporteReserva.get(contador).getServicios();
				destinoAnterior=listaReporteReserva.get(contador).getDestinos();
				while((contador<listaReporteReserva.size()) && (listaReporteReserva.get(contador).getCodReserva().equals(codReservaAnterior)))
				{
					if(contador==0 && listaReporteReserva.get(contador).getHoteles()==null)
					{
						primerosHotelesVacios=true;
					}
					if(listaReporteReserva.get(contador).getDestinos()==null){
						System.out.println("sale este null");
						destinoAnterior = listaReporteReserva.get(contador).getDestinos();
					}else if(!listaReporteReserva.get(contador).getDestinos().equals(destinoAnterior)) {
						destinoAnterior = listaReporteReserva.get(contador).getDestinos();
						listaDestinos.add(new CDestino(listaReporteReserva.get(contador).getDestinos().toString()));
						continuaDestinos=false;
						primerosHotelesVacios=false;
					}
					if(listaReporteReserva.get(contador).getHoteles()==null){
						hotelAnterior = listaReporteReserva.get(contador).getHoteles();
					}else if(!listaReporteReserva.get(contador).getHoteles().equals(hotelAnterior)) {
						hotelAnterior = listaReporteReserva.get(contador).getHoteles();
						listaHoteles.add(new CHotel(listaReporteReserva.get(contador).getHoteles()));
						continuaHoteles=false;
					}else{
						if(continuaHoteles)
						{
							System.out.println("sigue imprimiendo esto 1");
							if(listaReporteReserva.get(contador).getServicios()==null){
								servicioAnterior = listaReporteReserva.get(contador).getServicios();
							}else if(!listaReporteReserva.get(contador).getServicios().equals(servicioAnterior)) {
								servicioAnterior = listaReporteReserva.get(contador).getServicios();
								listaServicios.add(new CServicio(listaReporteReserva.get(contador).getServicios().toString()));
								continuaServicios=false;
							}else {
								if(continuaServicios)
								{
									System.out.println("sigue imprimiendo esto 2");
									if(listaReporteReserva.get(contador).getSubServicios()==null){
										servicioAnterior = listaReporteReserva.get(contador).getSubServicios();
									}else if (!listaReporteReserva.get(contador).getSubServicios().equals(subservicioAnterior)) {
										subservicioAnterior = listaReporteReserva.get(contador).getSubServicios();
										listasubServicios.add(new CSubServicio(listaReporteReserva.get(contador).getSubServicios()));
									}
								}
							}
						}
					}
					if(continuaDestinos && primerosHotelesVacios)
					{
							System.out.println("ingresa aqui o no");
							if(listaReporteReserva.get(contador).getServicios()==null){
								servicioAnterior = listaReporteReserva.get(contador).getServicios();
							}else if(!listaReporteReserva.get(contador).getServicios().equals(servicioAnterior)) {
								servicioAnterior = listaReporteReserva.get(contador).getServicios();
								listaServicios.add(new CServicio(listaReporteReserva.get(contador).getServicios().toString()));
								continuaServicios=false;
							}
					}
					contador++;
					factorIncremento++;
				}
				Number totalPrecio=listaReporteReserva.get(i).getNroPersonas()*listaReporteReserva.get(i).getPrecioPersona().doubleValue();
				reporteReserva=new CReporteReservaMuestra();
				reporteReserva.setCodReserva(listaReporteReserva.get(i).getCodReserva());
				reporteReserva.setFechaInicio(listaReporteReserva.get(i).getFechaInicio());
				reporteReserva.setFechaFin(listaReporteReserva.get(i).getFechaFin());
				reporteReserva.setFecha(listaReporteReserva.get(i).getFecha());
				reporteReserva.setNombreContacto(listaReporteReserva.get(i).getNombreContacto());
				reporteReserva.setEmail(listaReporteReserva.get(i).getEmail());
				reporteReserva.setTelefono(listaReporteReserva.get(i).getTelefono());
				reporteReserva.setNroPersonas(listaReporteReserva.get(i).getNroPersonas());
				reporteReserva.setPrecioPersona(listaReporteReserva.get(i).getPrecioPersona());
				reporteReserva.setNombrePaquete(listaReporteReserva.get(i).getNombrePaquete());
				reporteReserva.setCategoria(listaReporteReserva.get(i).getCategoria());
				reporteReserva.setDestinos(listaDestinos);
				reporteReserva.setHoteles(listaHoteles);
				reporteReserva.setServicios(listaServicios);
				reporteReserva.setSubServicios(listasubServicios);
				reporteReserva.setEstado(listaReporteReserva.get(i).getEstado());
				reporteReserva.setPrecioTotal(totalPrecio);
				System.out.println("aqui esta el tamanio de listahotel "+listaHoteles.size());
				listanuevaReporteReserva.add(reporteReserva);
			}
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
