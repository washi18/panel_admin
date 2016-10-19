package com.pricing.model;

import java.io.Serializable;
import java.sql.Date;


public class CUsuarioLogin implements Serializable {
	//======ATRIBUTOS====
	private static final long serialVersionUID = 1L;
	private String cResultado;
    private String cMensaje;
    private String cUsuarioCod;
    private String cClave;
    private String imgUsuario;
    private int nPerfilCod;
    private Date dFechaInicio;
    private Date dFechaFin;
    private String cNroDoc;
    private String cNombres;
    private boolean bEsRepLeg;
    private char cSexo;
    private Date dFechaNac;
    private String cCelular;
    //=====GETTER AND SETTER====
	public String getcResultado() {
		return cResultado;
	}
	public void setcResultado(String cResultado) {
		this.cResultado = cResultado;
	}
	public String getcMensaje() {
		return cMensaje;
	}
	public void setcMensaje(String cMensaje) {
		this.cMensaje = cMensaje;
	}
	public String getcUsuarioCod() {
		return cUsuarioCod;
	}
	public void setcUsuarioCod(String cUsuarioCod) {
		this.cUsuarioCod = cUsuarioCod;
	}
	public String getcClave() {
		return cClave;
	}
	public void setcClave(String cClave) {
		this.cClave = cClave;
	}
	public int getnPerfilCod() {
		return nPerfilCod;
	}
	public void setnPerfilCod(int nPerfilCod) {
		this.nPerfilCod = nPerfilCod;
	}
	public Date getdFechaInicio() {
		return dFechaInicio;
	}
	public void setdFechaInicio(Date dFechaInicio) {
		this.dFechaInicio = dFechaInicio;
	}
	public Date getdFechaFin() {
		return dFechaFin;
	}
	public void setdFechaFin(Date dFechaFin) {
		this.dFechaFin = dFechaFin;
	}
	public String getcNroDoc() {
		return cNroDoc;
	}
	public void setcNroDoc(String cNroDoc) {
		this.cNroDoc = cNroDoc;
	}
	public boolean isbEsRepLeg() {
		return bEsRepLeg;
	}
	public void setbEsRepLeg(boolean bEsRepLeg) {
		this.bEsRepLeg = bEsRepLeg;
	}
	public char getcSexo() {
		return cSexo;
	}
	public void setcSexo(char cSexo) {
		this.cSexo = cSexo;
	}
	public Date getdFechaNac() {
		return dFechaNac;
	}
	public void setdFechaNac(Date dFechaNac) {
		this.dFechaNac = dFechaNac;
	}
	public String getcCelular() {
		return cCelular;
	}
	public void setcCelular(String cCelular) {
		this.cCelular = cCelular;
	}
    public String getcNombres() {
		return cNombres;
	}
	public void setcNombres(String cNombres) {
		this.cNombres = cNombres;
	}
	
	public String getImgUsuario() {
		return imgUsuario;
	}
	public void setImgUsuario(String imgUsuario) {
		this.imgUsuario = imgUsuario;
	}
	//=====CONSTRUCTORES=====
	public CUsuarioLogin()
	{
		this.cResultado ="";
		this.cMensaje ="";
		this.cUsuarioCod ="";
		this.cClave ="";
		this.nPerfilCod =0;
		this.cNroDoc ="";
		this.cNombres ="";
		this.bEsRepLeg =false;
		this.cSexo =' ';
		this.cCelular ="";
		this.imgUsuario="";
	}
	public CUsuarioLogin(String cResultado, String cMensaje,
			String cUsuarioCod, String cClave, int nPerfilCod,
			Date dFechaInicio, Date dFechaFin, String cNroDoc, String cNombres,
			boolean bEsRepLeg, char cSexo, Date dFechaNac, String cCelular) {
		this.cResultado = cResultado;
		this.cMensaje = cMensaje;
		this.cUsuarioCod = cUsuarioCod;
		this.cClave = cClave;
		this.nPerfilCod = nPerfilCod;
		this.dFechaInicio = dFechaInicio;
		this.dFechaFin = dFechaFin;
		this.cNroDoc = cNroDoc;
		this.cNombres = cNombres;
		this.bEsRepLeg = bEsRepLeg;
		this.cSexo = cSexo;
		this.dFechaNac = dFechaNac;
		this.cCelular = cCelular;
	}
	
}