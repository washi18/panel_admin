package com.pricing.viewModel;

import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.pricing.dao.CReportePagosDAO;
import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CDestino;
import com.pricing.model.CHotel;
import com.pricing.model.CPasajero;
import com.pricing.model.CReportePagos;
import com.pricing.model.CReportePagosMuestra;
import com.pricing.model.CReporteReserva;
import com.pricing.model.CServicio;
import com.pricing.model.CSubServicio;

public class reportePagosVM {
	
	//===============atributos======
	private ArrayList<CReportePagos> listaReportePagos;
	private CReportePagosDAO reportePagosDAO;
	private boolean estadoPagoPendiente;
	private boolean estadoPagoParcial;
	private boolean estadoPagoTotal;
	private String fechaInicio;
	private String fechaFinal;
	private ArrayList<CPasajero> listaPasajeros;
	private ArrayList<CReportePagosMuestra> listanuevaReportePagos;
	private CReportePagosMuestra reportepagosMuestra;
	private CReportePagosMuestra reportePagosMuestraAnterior;
	//===============getter and setter=======
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
	
	public boolean isEstadoPagoPendiente() {
		return estadoPagoPendiente;
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
	
	public CReportePagosMuestra getReportePagosMuestraAnterior() {
		return reportePagosMuestraAnterior;
	}
	public void setReportePagosMuestraAnterior(
			CReportePagosMuestra reportePagosMuestraAnterior) {
		this.reportePagosMuestraAnterior = reportePagosMuestraAnterior;
	}
	//=====================constructores======
	@Init
	public void initVM()
	{
		estadoPagoParcial=false;
		estadoPagoPendiente=false;
		estadoPagoTotal=false;
		/**Inicializando los objetos**/
		listaReportePagos=new ArrayList<CReportePagos>();
		fechaInicio="";
		fechaFinal="";
		reportePagosDAO=new CReportePagosDAO();
		reportePagosMuestraAnterior=new CReportePagosMuestra();
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
	}
	//====================metodos============
	@Command
	public void habilitarPasajerosPOP(@BindingParam("cpasajero") CReportePagosMuestra pasajero)
	{
		if(!pasajero.getCodReserva().equals(reportePagosMuestraAnterior.getCodReserva())){
			pasajero.setVisiblepasajerospop(true);
			reportePagosMuestraAnterior.setVisiblepasajerospop(false);
			reportePagosMuestraAnterior=pasajero;
		}
		else{
			pasajero.setVisiblepasajerospop(true);
		}
		BindUtils.postNotifyChange(null, null, pasajero,"visiblepasajerospop");
	}
	@Command
	public void recuperarFechaDatebox(@BindingParam("fecha")String fecha,@BindingParam("id")String id)
	{
		if(id.equals("db_desde"))
			fechaInicio=fecha;
		else
			fechaFinal=fecha;
	}
	
	@Command
	@NotifyChange({"estadoPagoParcial","estadoPagoPendiente","estadoPagoTotal"})
	public void seleccion_radio(@BindingParam("radio")String idRadio)
	{
		if(idRadio.equals("rdPagoPendiente"))
		{
			estadoPagoPendiente=true;
			estadoPagoParcial=estadoPagoTotal=false;
		}else if(idRadio.equals("rdPagoParcial"))
		{
			estadoPagoParcial=true;
			estadoPagoPendiente=estadoPagoTotal=false;
		}else if(idRadio.equals("rdPagoTotal"))
		{
			estadoPagoTotal=true;
			estadoPagoParcial=estadoPagoPendiente=false;
		}
	}
	
	@Command
	@NotifyChange({"listaReportePagos","listanuevaReportePagos","listaPasajeros"})
	public void Buscar_Pagos(@BindingParam("componente")Component componente)
	{
		if(fechaInicio.isEmpty() || fechaFinal.isEmpty())
		{
			Clients.showNotification("Las fechas DESDE-HASTA son obligatorias ", Clients.NOTIFICATION_TYPE_INFO, componente,"after_start",3700);
		}
		else if(estadoPagoParcial==true || estadoPagoPendiente==true || estadoPagoTotal==true)
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
			if(estadoPagoPendiente)
			{
				NombrePago="PENDIENTE DE PAGO";
			}else if(estadoPagoParcial){
				NombrePago="PAGO PARCIAL";
			}else if(estadoPagoTotal){
				NombrePago="PAGO TOTAL";
			}
			listaReportePagos.clear();
			System.out.println("el valor de pago es:"+fecha1);
			System.out.println("el valor de pago es:"+fecha2);
			System.out.println("el valor de pago es:"+NombrePago);
			reportePagosDAO.asignarVisaListaReportePagos(reportePagosDAO.recuperarPagosVisaBD(fecha1, fecha2,NombrePago));
			reportePagosDAO.asignarVisaListaReportePagos(reportePagosDAO.recuperarPagosPaypalBD(fecha1,fecha2,NombrePago));
			this.setListaReportePagos(reportePagosDAO.getListaReportePagos());
			System.out.println("entro aqui 2");
			System.out.println("entro aqui 3");
			String codReservaAnterior="";
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
							listaReportePagos.get(contador).getEdad(),listaReportePagos.get(contador).getNroDoc(),listaReportePagos.get(contador).getSexo()));
				}
				pasajeroAnterior=listaReportePagos.get(contador).getNombres();
				while((contador<listaReportePagos.size()) && (listaReportePagos.get(contador).getCodReserva().equals(codReservaAnterior)))
				{
					if(listaReportePagos.get(contador).getNombres()==null){
						System.out.println("sale este null");
						pasajeroAnterior = listaReportePagos.get(contador).getNombres();
					}else if(!listaReportePagos.get(contador).getNombres().equals(pasajeroAnterior)) {
						pasajeroAnterior = listaReportePagos.get(contador).getNombres();
						listaPasajeros.add(new CPasajero(listaReportePagos.get(contador).getTipoDocumento(),listaReportePagos.get(contador).getApellidos(),
								listaReportePagos.get(contador).getNombres(),listaReportePagos.get(contador).getNombrePais(),
								listaReportePagos.get(contador).getEdad(),listaReportePagos.get(contador).getNroDoc(),listaReportePagos.get(contador).getSexo()));
					}
					contador++;
					factorIncremento++;
				}
				double impuesto=Double.valueOf(listaReportePagos.get(i).getImpuesto().toString())*100;
				double valorImpuesto=(impuesto*(listaReportePagos.get(i).getImporte().doubleValue()))/100;
				String impuestoCadena=String.valueOf(impuesto);
				double porcentaje=Double.valueOf(listaReportePagos.get(i).getPorcentaje().toString())*100;
				double total=listaReportePagos.get(i).getImporte().doubleValue()+valorImpuesto;
				reportepagosMuestra=new CReportePagosMuestra();
				reportepagosMuestra.setCodPago(listaReportePagos.get(i).getCodPago());
				reportepagosMuestra.setCodReserva(listaReportePagos.get(i).getCodReserva());
				reportepagosMuestra.setFechaInicio(listaReportePagos.get(i).getFechaInicio());
				reportepagosMuestra.setFechaFin(listaReportePagos.get(i).getFechaFin());
				reportepagosMuestra.setFecha(listaReportePagos.get(i).getFecha());
				reportepagosMuestra.setNombrePaquete(listaReportePagos.get(i).getNombrePaquete());
				reportepagosMuestra.setNroPersonas(listaReportePagos.get(i).getNroPersonas());
				reportepagosMuestra.setImporte(listaReportePagos.get(i).getImporte());
				reportepagosMuestra.setPorcentaje(porcentaje);
				reportepagosMuestra.setFormaPago(listaReportePagos.get(i).getFormaPago());
				reportepagosMuestra.setEstado(listaReportePagos.get(i).getEstado());
				reportepagosMuestra.setFechayhoraTransaccion(listaReportePagos.get(i).getFechayhoraTransaccion());
				reportepagosMuestra.setCodTransaccion(listaReportePagos.get(i).getCodTransaccion());
				reportepagosMuestra.setNombreCliente(listaReportePagos.get(i).getNombreCliente());
				reportepagosMuestra.setNroTarjeta(listaReportePagos.get(i).getNroTarjeta());
				reportepagosMuestra.setEstadoReserva(listaReportePagos.get(i).getEstadoReserva());
				reportepagosMuestra.setListaPasajeros(listaPasajeros);
				reportepagosMuestra.setMontoTotal(total);
				reportepagosMuestra.setImpuesto(impuestoCadena);
				reportepagosMuestra.setValorImpuesto(valorImpuesto);
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
