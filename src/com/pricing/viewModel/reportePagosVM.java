package com.pricing.viewModel;

import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CReportePagosDAO;
import com.pricing.model.CDestino;
import com.pricing.model.CHotel;
import com.pricing.model.CPasajero;
import com.pricing.model.CReportePagos;
import com.pricing.model.CReportePagosMuestra;
import com.pricing.model.CReporteReservaMuestra;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class reportePagosVM {
	
	//===============atributos======
	private boolean estadoPagosAutorizados;
	private boolean estadoPagosDenegados;
	private ArrayList<CReportePagos> listaReportePagos;
	private CReportePagosDAO reportePagosDAO;
	private String fechaInicio;
	private String fechaFinal;
	private ArrayList<CPasajero> listaPasajeros;
	private ArrayList<CReportePagosMuestra> listanuevaReportePagos;
	private CReportePagosMuestra reportepagosMuestra;
	//===============getter and setter=======
	public boolean isEstadoPagosAutorizados() {
		return estadoPagosAutorizados;
	}
	public void setEstadoPagosAutorizados(boolean estadoPagosAutorizados) {
		this.estadoPagosAutorizados = estadoPagosAutorizados;
	}
	public boolean isEstadoPagosDenegados() {
		return estadoPagosDenegados;
	}
	public void setEstadoPagosDenegados(boolean estadoPagosDenegados) {
		this.estadoPagosDenegados = estadoPagosDenegados;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public ArrayList<CReportePagos> getListaReportePagos() {
		return listaReportePagos;
	}
	public void setListaReportePagos(ArrayList<CReportePagos> listaReportePagos) {
		this.listaReportePagos = listaReportePagos;
	}
	public CReportePagosDAO getReportePagosDAO() {
		return reportePagosDAO;
	}
	public void setReportePagosDAO(CReportePagosDAO reportePagosDAO) {
		this.reportePagosDAO = reportePagosDAO;
	}
	
	public ArrayList<CPasajero> getListaPasajeros() {
		return listaPasajeros;
	}
	public void setListaPasajeros(ArrayList<CPasajero> listaPasajeros) {
		this.listaPasajeros = listaPasajeros;
	}
	
	public ArrayList<CReportePagosMuestra> getListanuevaReportePagos() {
		return listanuevaReportePagos;
	}
	public void setListanuevaReportePagos(
			ArrayList<CReportePagosMuestra> listanuevaReportePagos) {
		this.listanuevaReportePagos = listanuevaReportePagos;
	}
	
	public CReportePagosMuestra getReportepagosMuestra() {
		return reportepagosMuestra;
	}
	public void setReportepagosMuestra(CReportePagosMuestra reportepagosMuestra) {
		this.reportepagosMuestra = reportepagosMuestra;
	}
	//=====================constructores======
	//====================metodos============
	@Command
	public void recuperarFechaDatebox(@BindingParam("fecha")String fecha,@BindingParam("id")String id)
	{
		if(id.equals("db_desde"))
			fechaInicio=fecha;
		else
			fechaFinal=fecha;
	}
	@Command
	@NotifyChange({"estadoPagosAutorizados","estadoPagosDenegados"})
	public void seleccion_radio(@BindingParam("radio")String idRadio)
	{
		if(idRadio.equals("rdPagoAutorizado"))
		{
			estadoPagosAutorizados=true;
			estadoPagosDenegados=false;
		}else if(idRadio.equals("rdPagoDenegado"))
		{
			estadoPagosDenegados=true;
			estadoPagosAutorizados=false;
		}
	}
	
	@Command
	@NotifyChange({"listaReporteReserva","listanuevaReporteReserva","listaHoteles","reporteReserva"})
	public void Buscar_Pagos(@BindingParam("componente")Component componente)
	{
		if(fechaInicio.isEmpty() || fechaFinal.isEmpty())
		{
			Clients.showNotification("Las fechas DESDE-HASTA son obligatorias ", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
		else if(estadoPagosAutorizados==true || estadoPagosDenegados==true)
		{
			//-------despedasando la fecha desde------
			String diaStart=fechaInicio.substring(0,2);
			String mesStart=cambiarFormatoMes(fechaInicio.substring(3,6));
			String anioStart=fechaInicio.substring(7,11);
			//-------despedasando la fecha hasta------
			String diaEnd=fechaFinal.substring(0,2);
			String mesEnd=cambiarFormatoMes(fechaFinal.substring(3,6));
			String anioEnd=fechaFinal.substring(7,11);
			/*************Fecha Inicio*******************/
			String fecha1=anioStart+"-"+mesStart+"-"+diaStart;
			String fecha2=anioEnd+"-"+mesEnd+"-"+diaEnd;
			/****Validando la fecha****/
			String NombrePago="";
			if(estadoPagosAutorizados)
			{
				NombrePago="INICIADO";
			}else if(estadoPagosDenegados){
				NombrePago="DENEGADO";
			}
			listaReportePagos.clear();
			System.out.println("entro aqui 1");
			reportePagosDAO.asignarVisaListaReportePagos(reportePagosDAO.recuperarPagosVisaBD(fecha1,fecha2,NombrePago));
			reportePagosDAO.asignarVisaListaReportePagos(reportePagosDAO.recuperarPagosPaypalBD(fecha1,fecha2,NombrePago));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
			System.out.println("entro aqui 2");
			String codReservaAnterior,destinoAnterior,hotelAnterior,servicioAnterior,subservicioAnterior;
			System.out.println("entro aqui 3");
			codReservaAnterior="";
			int factorIncremento=0;
			System.out.println("el nro de filas es:"+listaReportePagos.size());
			listanuevaReportePagos=new ArrayList<CReportePagosMuestra>();
			for(int i=0;i<listaReportePagos.size();i=i+factorIncremento)
			{	
				factorIncremento=0;
				codReservaAnterior=listaReportePagos.get(i).getCodReserva();
				int contador=i;
				System.out.println("el valor del contador:"+contador);
				String pasajeroAnterior="";
				System.out.println("el valor de reserva anterior es:"+codReservaAnterior);
				listaPasajeros=new ArrayList<CPasajero>();
				if(listaReportePagos.get(contador).getNombres()!=null)
				{
					listaPasajeros.add(new CPasajero(listaReportePagos.get(contador).getTipoDocumento(),listaReportePagos.get(contador).getApellidos(),
							listaReportePagos.get(contador).getNombres(),listaReportePagos.get(contador).getNombrePais(),
							listaReportePagos.get(contador).getEdad()));
				}
				while((contador<listaReportePagos.size()) && (listaReportePagos.get(contador).getCodReserva().equals(codReservaAnterior)))
				{
					if(listaReportePagos.get(contador).getNombres()==null){
						System.out.println("sale este null");
						pasajeroAnterior = listaReportePagos.get(contador).getNombres();
					}else if(!listaReportePagos.get(contador).getNombres().equals(pasajeroAnterior)) {
						pasajeroAnterior = listaReportePagos.get(contador).getNombres();
						listaPasajeros.add(new CPasajero(listaReportePagos.get(contador).getTipoDocumento(),listaReportePagos.get(contador).getApellidos(),
								listaReportePagos.get(contador).getNombres(),listaReportePagos.get(contador).getNombrePais(),
								listaReportePagos.get(contador).getEdad()));
					}
					contador++;
					factorIncremento++;
				}
				reportepagosMuestra=new CReportePagosMuestra();
				reportepagosMuestra.setCodPago(listaReportePagos.get(i).getCodPago());
				reportepagosMuestra.setCodReserva(listaReportePagos.get(i).getCodReserva());
				reportepagosMuestra.setFechaInicio(listaReportePagos.get(i).getFechaInicio());
				reportepagosMuestra.setFechaFin(listaReportePagos.get(i).getFechaFin());
				reportepagosMuestra.setFecha(listaReportePagos.get(i).getFecha());
				reportepagosMuestra.setNombrePaquete(listaReportePagos.get(i).getNombrePaquete());
				reportepagosMuestra.setNroPersonas(listaReportePagos.get(i).getNroPersonas());
				reportepagosMuestra.setImporte(listaReportePagos.get(i).getImporte());
				reportepagosMuestra.setPorcentaje(listaReportePagos.get(i).getPorcentaje());
				reportepagosMuestra.setFormaPago(listaReportePagos.get(i).getFormaPago());
				reportepagosMuestra.setEstado(listaReportePagos.get(i).getEstado());
				reportepagosMuestra.setFechayhoraTransaccion(listaReportePagos.get(i).getFechayhoraTransaccion());
				reportepagosMuestra.setCodTransaccion(listaReportePagos.get(i).getCodTransaccion());
				reportepagosMuestra.setNombreCliente(listaReportePagos.get(i).getNombreCliente());
				reportepagosMuestra.setApellidos(listaReportePagos.get(i).getApellidos());
				reportepagosMuestra.setNombres(listaReportePagos.get(i).getNombres());
				reportepagosMuestra.setEdad(listaReportePagos.get(i).getEdad());
				reportepagosMuestra.setSexo(listaReportePagos.get(i).getSexo());
				reportepagosMuestra.setTipoDocumento(listaReportePagos.get(i).getTipoDocumento());
				reportepagosMuestra.setNombrePais(listaReportePagos.get(i).getNombrePais());
				reportepagosMuestra.setNroTarjeta(listaReportePagos.get(i).getNroTarjeta());
				reportepagosMuestra.setListaPasajeros(listaPasajeros);
				listanuevaReportePagos.add(reportepagosMuestra);
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
