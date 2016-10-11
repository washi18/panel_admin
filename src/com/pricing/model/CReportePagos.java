package com.pricing.model;

import java.util.Date;

public class CReportePagos {
	//=======atributos======
	private String codPago;
	private String codReserva;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fecha;
	private String nombrePaquete;
	private int nroPersonas;
	private Number importe;
	private Number porcentaje;
	private String formaPago;
	private String estado;
	private Date fechayhoraTransaccion;
	private String codTransaccion;
	private String nombreCliente;
	private String apellidos;
	private String nombres;
	private char sexo;
	private int edad;
	private String tipoDocumento;
	private String nombrePais;
	private String nroTarjeta;
	
	//===============getter and setter=======
	public String getCodPago() {
		return codPago;
	}
	
	public void setCodPago(String codPago) {
		this.codPago = codPago;
	}
	public String getCodReserva() {
		return codReserva;
	}
	public void setCodReserva(String codReserva) {
		this.codReserva = codReserva;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombrePaquete() {
		return nombrePaquete;
	}
	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}
	public int getNroPersonas() {
		return nroPersonas;
	}
	public void setNroPersonas(int nroPersonas) {
		this.nroPersonas = nroPersonas;
	}
	public Number getImporte() {
		return importe;
	}
	public void setImporte(Number importe) {
		this.importe = importe;
	}
	public Number getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Number porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechayhoraTransaccion() {
		return fechayhoraTransaccion;
	}
	public void setFechayhoraTransaccion(Date fechayhoraTransaccion) {
		this.fechayhoraTransaccion = fechayhoraTransaccion;
	}
	public String getCodTransaccion() {
		return codTransaccion;
	}
	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public String getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	//==================constructores==================
	public CReportePagos()
	{
		this.codPago = "";
		this.codReserva = "";
		this.nombrePaquete = "";
		this.nroPersonas = 0;
		this.importe = 0;
		this.porcentaje =0;
		this.formaPago = "";
		this.estado = "";
		this.codTransaccion = "";
		this.nombreCliente = "";
		this.apellidos = "";
		this.nombres = "";
		this.edad = 0;
		this.tipoDocumento = "";
		this.nombrePais = "";
		this.nroTarjeta = "";
	}

	public CReportePagos(String codReserva, Date fechaInicio,
			Date fechaFin, Date fecha, String nombrePaquete, int nroPersonas,
			Number importe, Number porcentaje, String formaPago, String estado,
			Date fechayhoraTransaccion,
			String codTransaccion, String nombreCliente,
			String apellidos, String nombres, char sexo, int edad,
			String tipoDocumento, String nombrePais, String nroTarjeta) {
		super();
		this.codReserva = codReserva;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fecha = fecha;
		this.nombrePaquete = nombrePaquete;
		this.nroPersonas = nroPersonas;
		this.importe = importe;
		this.porcentaje = porcentaje;
		this.formaPago = formaPago;
		this.estado = estado;
		this.fechayhoraTransaccion = fechayhoraTransaccion;
		this.codTransaccion = codTransaccion;
		this.nombreCliente = nombreCliente;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.sexo = sexo;
		this.edad = edad;
		this.tipoDocumento = tipoDocumento;
		this.nombrePais = nombrePais;
		this.nroTarjeta = nroTarjeta;
	}
	
	
}
