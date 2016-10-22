package com.pricing.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.pricing.model.CAcceso;
import com.pricing.model.CUsuarioLogin;

public class CUsuarioLoginDAO extends CConexion implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CUsuarioLogin usuario;
	CUsuarioLogin oUsuario;
	CAcceso oAcceso;
	//====CONSTRUCTORES=====
	public CUsuarioLoginDAO(){
		super();
		usuario=new CUsuarioLogin();
		oAcceso=new CAcceso();
		oUsuario=new CUsuarioLogin();
	}
	//====GETTER AND SETTER====
	public CUsuarioLogin getUsuario() {
		return usuario;
	}
	public void setUsuario(CUsuarioLogin usuario) {
		this.usuario = usuario;
	}
	public CAcceso getoAcceso() {
		return oAcceso;
	}
	public void setoAcceso(CAcceso oAcceso) {
		this.oAcceso = oAcceso;
	}
	public CUsuarioLogin getoUsuario() {
		return oUsuario;
	}
	public void setoUsuario(CUsuarioLogin oUsuario) {
		this.oUsuario = oUsuario;
	}
	//--------------------------
	//=======METODOS==========
	public List validarLogin()
	{
		Object[] user={usuario.getcUsuarioCod(),usuario.getcClave()};
		return ejecutarProcedimiento("Pricing_sp_ValidarLogin", user);
	}
	public List recuperarUsuario(String usuario,String password)
	{
		Object[] values={usuario,password};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarUser", values);
	}
	public List obtenerSistemasPerfil(String subsistema)
	{
		return ejecutarProcedimiento("CP_sp_SistemasPerfil_Listar", new String[]{Integer.toString(usuario.getnPerfilCod()),subsistema});
	}
	public List recuperarAccesosUsuario(int codPerfil)
	{
		Object[] values={codPerfil};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_MostrarAcceso", values);
	}
	public void asignarUsuario(List lista)
	{
		Map row=(Map)lista.get(0);
		  oUsuario.setcUsuarioCod((String)row.get("cusuariocod"));
		  oUsuario.setcClave((String)row.get("cclave"));
		  oUsuario.setnPerfilCod((int)row.get("nperfilcod"));
		  oUsuario.setImgUsuario((String)row.get("imgusuario"));
		  oUsuario.setcNroDoc((String)row.get("cnrodoc"));
		  oUsuario.setcNombres((String)row.get("cnombres"));
		  oUsuario.setcSexo((String)row.get("csexo"));
		  oUsuario.setcCelular((String)row.get("ccelular"));
	}
	public void asignarAccesosUsuario(List lista)
	{
		Map row=(Map)lista.get(0);
		oAcceso.setnAccesoCod((int)row.get("naccesocod"));// int,
		oAcceso.setnPerfilCod((int)row.get("nperfilcod"));// int,
		oAcceso.setAccesoUpdateDisdo((boolean)row.get("accesoupdatedisdo"));// boolean,
		oAcceso.setAccesoEtiqueta((boolean)row.get("accesoetiqueta"));// boolean,
		oAcceso.setAccesoImpuesto((boolean)row.get("accesoimpuesto"));// boolean,
		oAcceso.setAccesoCodVisa((boolean)row.get("accesocodvisa"));// boolean,
		oAcceso.setAccesoRegUsuarios((boolean)row.get("accesoregusuarios"));// boolean,
		oAcceso.setAccesoCrearNuevoUser((boolean)row.get("accesocrearnuevouser"));// boolean,
		oAcceso.setAccesoCategorias((boolean)row.get("accesocategorias"));// boolean,
		oAcceso.setAccesoPaquetes((boolean)row.get("accesopaquetes"));// boolean,
		oAcceso.setAccesoServicios((boolean)row.get("accesoservicios"));// boolean,
		oAcceso.setAccesoSubServicios((boolean)row.get("accesosubservicios"));// boolean,
		oAcceso.setAccesoHoteles((boolean)row.get("accesohoteles"));// boolean,
		oAcceso.setAccesoDestinos((boolean)row.get("accesodestinos"));// boolean,
		oAcceso.setAccesoReporReservas((boolean)row.get("accesoreporreservas"));// boolean,
		oAcceso.setAccesoReporPagos((boolean)row.get("accesoreporpagos"));
	}
    /*METODOS REDEFINIDOS*/
    public List ejecutarProcedimiento(String procedimiento){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento);
    }
    public List ejecutarProcedimiento(String procedimiento,Object[] values){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento, values);
    }
}
